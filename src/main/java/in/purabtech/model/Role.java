package in.purabtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;

    private String name;

    /*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<User> users;*/

    public Role(String name) {
        super();
        this.name = name;
    }
}
