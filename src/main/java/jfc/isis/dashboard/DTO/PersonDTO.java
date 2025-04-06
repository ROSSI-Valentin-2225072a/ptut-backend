package jfc.isis.dashboard.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonDTO implements Serializable {
    Integer personId;
    String firstName;
    String lastName;
    Date birthday;
}
