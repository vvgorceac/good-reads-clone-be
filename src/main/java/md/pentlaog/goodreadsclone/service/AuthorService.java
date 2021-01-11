package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.model.Author;

import java.util.List;

public interface AuthorService {
  Author add(Author author);

  List<Author> getAll();

  Author findByFirstName(String authorName);

  Author findById(Long id);

  void delete(Long id);
}
