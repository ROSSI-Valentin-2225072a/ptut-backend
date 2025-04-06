package jfc.isis.dashboard.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Enabled
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"LIBELLE"}))
public class Type {

    @Id
    @NonNull
    private String libelle;


}
