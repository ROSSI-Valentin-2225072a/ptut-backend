package jfc.isis.dashboard.DTO;

import jfc.isis.dashboard.entity.Type;
import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {
    Integer id;
    Date dateEvent;
    String description;
    String nomEvent;
    String lieu;
    Type type;
}
