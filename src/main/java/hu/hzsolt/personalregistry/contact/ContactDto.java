package hu.hzsolt.personalregistry.contact;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @author zshorvath
 * created on 31/10/2022
 */
public class ContactDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String phone;

    public String getEmail() {
        return email;
    }

    public ContactDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ContactDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDto that = (ContactDto) o;
        return Objects.equals(email, that.email) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }
}
