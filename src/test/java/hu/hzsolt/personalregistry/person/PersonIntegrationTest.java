package hu.hzsolt.personalregistry.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.hzsolt.personalregistry.address.AddressDto;
import hu.hzsolt.personalregistry.address.AddressType;
import hu.hzsolt.personalregistry.contact.ContactDto;
import hu.hzsolt.personalregistry.error.DuplicateEntryException;
import hu.hzsolt.personalregistry.error.ResourceNotExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class PersonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSavePerson_usingValidArguments_returnsOk() throws Exception {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("test1@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("test2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Budapest")
                .setStreet("Teszt street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Test City")
                .setStreet("Teszt 25")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto personDto = new PersonDto()
                .setName("TestDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;

        // Act
        MvcResult mvcResult = mockMvc
                .perform(post("/person")
                        .content(objectMapper.writeValueAsBytes(personDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int responseStatus = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        PersonCreated personCreated = objectMapper.readValue(contentAsString, PersonCreated.class);

        // Assert
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseStatus);

        MvcResult mvcResultOfGetRequest = mockMvc
                .perform(get("/person/%d".formatted(personCreated.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        String serializedPerson = mvcResultOfGetRequest.getResponse().getContentAsString();
        PersonDto personDtoResult = objectMapper.readValue(serializedPerson, PersonDto.class);

        Assertions.assertEquals(personDtoResult, personDto);
    }

    @Test
    public void testSavePerson_usingDuplicateEntryAddress_throwsDuplicateEntryException() throws Exception {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("test1@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("test2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Test City")
                .setStreet("Teszt 25")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("Test City")
                .setStreet("Teszt 25")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto personDto = new PersonDto()
                .setName("TestDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;


        // Act
        MvcResult mvcResult = mockMvc
                .perform(post("/person")
                        .content(objectMapper.writeValueAsBytes(personDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int responseStatus = mvcResult.getResponse().getStatus();
        Exception resolvedException = mvcResult.getResolvedException();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), responseStatus);
        Assertions.assertTrue(resolvedException instanceof DuplicateEntryException);
    }

    @Test
    public void testGetPerson_usingNotExistsId_throwsResourceNotExistsException() throws Exception {
        // Act
        MvcResult mvcResult = mockMvc
                .perform(get("/person/50")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int responseStatus = mvcResult.getResponse().getStatus();
        Exception resolvedException = mvcResult.getResolvedException();

        // Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), responseStatus);
        Assertions.assertTrue(resolvedException instanceof ResourceNotExistsException);
    }

    @Test
    public void testDeletePerson_usingValidId_returnsOk() throws Exception {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("delete@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("delete2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("DeleteCity")
                .setStreet("Delete street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("DeleteCity2")
                .setStreet("Delete Street 10")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto personDto = new PersonDto()
                .setName("DeleteDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;

        // Act
        MvcResult mvcResult = mockMvc
                .perform(post("/person")
                        .content(objectMapper.writeValueAsBytes(personDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        PersonCreated personCreated = objectMapper.readValue(contentAsString, PersonCreated.class);
        // Assert - to create test data for deleting
        Assertions.assertEquals(HttpStatus.CREATED.value(), status);

        // Act
        MvcResult mvcResultOfDelete = mockMvc
                .perform(delete("/person/%d".formatted(personCreated.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int responseStatus = mvcResultOfDelete.getResponse().getStatus();

        // Assert
        Assertions.assertEquals(HttpStatus.OK.value(), responseStatus);
    }

    @Test
    public void testModifyPerson_usingValidArguments_returnsOk() throws Exception {
        // Arrange
        ContactDto contactDto = new ContactDto()
                .setEmail("base@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto contactDto2 = new ContactDto()
                .setEmail("base2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto addressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("BaseCity")
                .setStreet("Base street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        AddressDto addressDto2 = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("BaseCity2")
                .setStreet("Base Street 10")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto basePersonDto = new PersonDto()
                .setName("BaseDto")
                .setAddresses(Arrays.asList(addressDto, addressDto2))
                ;

        // Act
        MvcResult mvcResult = mockMvc
                .perform(post("/person")
                        .content(objectMapper.writeValueAsBytes(basePersonDto))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        PersonCreated personCreated = objectMapper.readValue(contentAsString, PersonCreated.class);

        // Assert - to create test data for modifying
        Assertions.assertEquals(HttpStatus.CREATED.value(), status);

        ContactDto modifyContactDto = new ContactDto()
                .setEmail("modify@email.com")
                .setPhone("+36301111555")
                ;

        ContactDto modifyContactDto2 = new ContactDto()
                .setEmail("modify2@email.com")
                .setPhone("+36301441555")
                ;
        AddressDto modifyAddressDto = new AddressDto()
                .setAddressType(AddressType.PERMANENT)
                .setCity("modifyCity")
                .setStreet("Modify street 25")
                .setZipCode(1000)
                .setContacts(Arrays.asList(modifyContactDto, modifyContactDto2))
                ;
        AddressDto modifyAddressDto2 = new AddressDto()
                .setAddressType(AddressType.TEMPORARY)
                .setCity("modify City 2")
                .setStreet("modify Street 10")
                .setZipCode(5252)
                .setContacts(Arrays.asList(contactDto, contactDto2))
                ;
        PersonDto modifyPersonDto = new PersonDto()
                .setName("modify Dto")
                .setAddresses(Arrays.asList(modifyAddressDto, modifyAddressDto2))
                ;

        // Act
        MvcResult mvcResultOfDelete = mockMvc
                .perform(put("/person/%d".formatted(personCreated.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(modifyPersonDto))
                )
                .andDo(print())
                .andReturn();

        int responseStatus = mvcResultOfDelete.getResponse().getStatus();

        // Assert - to modifying is successfully
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), responseStatus);

        // Act
        MvcResult mvcResultOfGetModifying = mockMvc
                .perform(get("/person/%d".formatted(personCreated.getId()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andReturn();

        int responseStatusOfModifying = mvcResultOfGetModifying.getResponse().getStatus();
        String serializedPerson = mvcResultOfGetModifying.getResponse().getContentAsString();
        PersonDto modifiedPersonDto = objectMapper.readValue(serializedPerson, PersonDto.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK.value(), responseStatusOfModifying);
        Assertions.assertEquals(modifyPersonDto,modifiedPersonDto);
    }
}
