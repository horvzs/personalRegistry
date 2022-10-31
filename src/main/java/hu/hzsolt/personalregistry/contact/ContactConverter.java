package hu.hzsolt.personalregistry.contact;

import org.springframework.stereotype.Component;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
@Component
public class ContactConverter {

    public Contact convertToEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setEmail(contactDto.getEmail());
        contact.setPhone(contactDto.getPhone());
        return contact;
    }


    public ContactDto convertToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setEmail(contact.getEmail());
        contactDto.setPhone(contact.getPhone());
        return contactDto;
    }

}
