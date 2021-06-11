package md.pentlaog.goodreadsclone.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import md.pentlaog.goodreadsclone.validation.ISBN;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "books")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private Author author;

    @NotNull
    @Column(name = "publish_year")
    private Instant publishYear;

    @ISBN
    @Column(name = "isbn")
    private String isbn;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "readBooks")
    @ToString.Exclude
    private List<User> readUsers;
}
