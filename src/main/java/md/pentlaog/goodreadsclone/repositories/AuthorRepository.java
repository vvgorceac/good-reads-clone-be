package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.entities.Author;
import md.pentlaog.goodreadsclone.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
