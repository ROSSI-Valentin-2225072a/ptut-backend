package jfc.isis.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quoteId;

    @Basic
    @Column
    private Date quoteLastUse;

    @Basic(optional = false)
    private String quote;
}
