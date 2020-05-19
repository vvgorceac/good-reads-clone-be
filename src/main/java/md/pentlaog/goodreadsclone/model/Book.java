package md.pentlaog.goodreadsclone.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "books")
@Data
public class Book extends BaseEntity {
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "author_id", nullable = false)
  @ToString.Exclude
  private Author author;

  @Column(name = "publish_year")
  private Date publishYear;

  @Column(name = "isbn")
  private String isbn;

  @Column(name = "name")
  private String name;
}
