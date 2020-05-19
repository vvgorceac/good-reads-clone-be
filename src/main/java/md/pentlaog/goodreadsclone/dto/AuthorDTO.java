package md.pentlaog.goodreadsclone.dto;

import lombok.Data;
import md.pentlaog.goodreadsclone.model.Author;

import java.time.Instant;
import java.util.Date;

@Data
public class AuthorDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private Instant birthDate;

  public Author toAuthor() {
    Author author = new Author();
    author.setId(id);
    author.setFirstName(firstName);
    author.setLastName(lastName);
    author.setBirthDate(birthDate);

    return author;
  }

  public static AuthorDTO fromAuthor(Author author) {
    AuthorDTO authorDTO = new AuthorDTO();
    authorDTO.setId(author.getId());
    authorDTO.setFirstName(author.getFirstName());
    authorDTO.setLastName(author.getLastName());
    authorDTO.setBirthDate(author.getBirthDate());

    return authorDTO;
  }
}
