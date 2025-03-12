package jfc.isis.dashboard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false)
    private String lastName;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false)
    private Date birthday;


}
