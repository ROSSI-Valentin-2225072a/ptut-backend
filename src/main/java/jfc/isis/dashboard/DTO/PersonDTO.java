package jfc.isis.dashboard.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {
    Integer personId;
    String firstName;
    String lastName;
    String birthday;
}
