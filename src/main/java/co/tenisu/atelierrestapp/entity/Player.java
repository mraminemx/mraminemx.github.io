package co.tenisu.atelierrestapp.entity;

import javax.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Player {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String shortname;
    private String sex;
    @OneToOne(cascade = CascadeType.ALL)
    private Country country;
    private String picture;
    @Embedded
    private PlayerData data;
}

@Embeddable
@Data
class PlayerData {
    private Integer rank; // Le classement du joueur
    private Integer points;
    private Integer weight;
    private Integer height;
    private Integer age;
    @ElementCollection
    private List<Integer> last;
}