package md.pentlaog.goodreadsclone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import md.pentlaog.goodreadsclone.model.Book;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookDTO {
  private Long id;
  private AuthorDTO authorDTO;
  private Instant publishYear;
  private String isbn;
  private String name;

  public Book toBook() {
    Book book = new Book();
    book.setId(id);
    book.setAuthor(authorDTO.toAuthor());
    book.setPublishYear(publishYear);
    book.setIsbn(isbn);
    book.setName(name);
    return book;
  }

  public static BookDTO fromBook(Book book) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setId(book.getId());
    bookDTO.setAuthorDTO(AuthorDTO.fromAuthor(book.getAuthor()));
    bookDTO.setPublishYear(book.getPublishYear());
    bookDTO.setIsbn(book.getIsbn());
    bookDTO.setName(book.getName());

    return bookDTO;
  }
}
