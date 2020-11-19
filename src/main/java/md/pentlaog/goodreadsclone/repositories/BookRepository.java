package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
  Book findByName(String name);

  List<Book> findBooksByAuthorId(Long id);

  List<Book> findBooksByReadUsers_Id(Long id);
}
