package co.tenisu.atelierrestapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Country {
    @Id
    private String code;
    private String picture;
}