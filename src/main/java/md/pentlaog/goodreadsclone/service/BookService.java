package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    List<Book> getByAuthor(Long id);

    Book findByName(String name);

    Book add(Book book);

    Book findById(Long id);

    void delete(Long id);
}
