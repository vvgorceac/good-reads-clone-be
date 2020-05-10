package md.pentlaog.goodreadsclone.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer authorId;

    private String firstName;
    private String lastName;
    private Date birthDate;

}
