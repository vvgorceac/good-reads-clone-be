package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  Author findByFirstName(String authorName);
}
