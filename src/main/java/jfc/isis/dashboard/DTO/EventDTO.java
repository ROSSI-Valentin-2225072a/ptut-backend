package jfc.isis.dashboard.DTO;

import jfc.isis.dashboard.entity.Type;
import lombok.Data;

import java.util.List;

@Data
public class EventDTO {
    Integer eventId;
    String eventDate;
    String eventDescription;
    String nomEvent;
    Type type;
}
