package jfc.isis.dashboard.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QuoteDTO implements Serializable {
    Integer quoteId;
    Date quoteLastUse;
    String quote;

}
