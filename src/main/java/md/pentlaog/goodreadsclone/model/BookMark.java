package md.pentlaog.goodreadsclone.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "book_marks")
@Data
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BookMark extends BaseEntity {

  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name="book_id", nullable = false)
  private Book book;

  @Column(name = "mark")
  private int mark;
}


