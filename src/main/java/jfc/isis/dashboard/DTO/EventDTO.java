package jfc.isis.dashboard.DTO;

import jfc.isis.dashboard.entity.Type;
import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {
    Integer eventId;
    Date eventDate;
    String eventDescription;
    String nomEvent;
    Type type;
}
