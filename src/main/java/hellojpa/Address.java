package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter//값 타입은 절대로 setter를 public으로 선언하면 사고난다. 무조건 new로 만들도록 유도. 생성자로만 값을 셋팅가능하도록 구현할 것.
@Embeddable
public class Address {

    private String city;

    private String street;

    private String zipcode;

    public Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getCity().equals(address.getCity()) && getStreet().equals(address.getStreet()) && getZipcode().equals(address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
