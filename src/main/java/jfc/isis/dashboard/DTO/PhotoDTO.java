package jfc.isis.dashboard.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PhotoDTO implements Serializable {
    Integer photoId;
    Date photoLastUse;
    String description;
    String photoBase64;
    String photoName;


}
