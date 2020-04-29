package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
