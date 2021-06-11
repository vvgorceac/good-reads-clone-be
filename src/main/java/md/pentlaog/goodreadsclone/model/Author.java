package md.pentlaog.goodreadsclone.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "authors")
@Data
public class Author extends BaseEntity {

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "birth_date")
    private Instant birthDate;
}