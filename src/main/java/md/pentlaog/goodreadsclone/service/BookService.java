package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAll();

    List<BookDTO> getByAuthor(Long id);

    BookDTO findByName(String name);

    BookDTO add(BookDTO BookDTO);

    BookDTO findById(Long id);

    void delete(Long id);
}
