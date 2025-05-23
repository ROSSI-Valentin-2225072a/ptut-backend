package jfc.isis.dashboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "NOM_EVENT"}))
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Basic
    private String nomEvent;

    @Basic
    private Date dateEvent;

    @Size(max = 512)
    @Column(length = 512)
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    private String lieu;

    @OneToOne
    private Type type;
}
