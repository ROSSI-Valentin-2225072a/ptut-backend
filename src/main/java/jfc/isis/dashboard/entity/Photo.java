package jfc.isis.dashboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photoId;

    @NonNull
    @Basic(optional = false)
    @Column(nullable = false)
    private String photoUrl;

    @Basic(optional = false)
    @Column(nullable = false)
    private Date photoLastUse;

    @Size(max = 512)
    @Column(length = 512)
    private String description;
}
