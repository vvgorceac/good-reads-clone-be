package md.pentlaog.goodreadsclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.dto.BookDTO;
import md.pentlaog.goodreadsclone.model.Author;
import md.pentlaog.goodreadsclone.model.Book;
import md.pentlaog.goodreadsclone.repositories.AuthorRepository;
import md.pentlaog.goodreadsclone.repositories.BookRepository;
import md.pentlaog.goodreadsclone.repositories.UserRepository;
import md.pentlaog.goodreadsclone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(
            BookRepository bookRepository,
            AuthorRepository authorRepository,
            UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> res = bookRepository.findAll();
        log.info("in getAll- books:{}", res);
        return res.stream().map(BookDTO::fromBook).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getByAuthor(Long id) {
        return bookRepository.findBooksByAuthorId(id).stream().map(BookDTO::fromBook).collect(Collectors.toList());
    }

    @Override
    public BookDTO findByName(String name) {
        return BookDTO.fromBook(bookRepository.findByName(name));
    }

    @Override
    public BookDTO add(BookDTO book) {
        Author author = authorRepository.findById(book.getAuthorDTO().getId()).orElse(null);
        book.setAuthorDTO(AuthorDTO.fromAuthor(author));
        return BookDTO.fromBook(bookRepository.save(book.toBook()));
    }

    @Override
    public BookDTO findById(Long id) {
        Book result = bookRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("In findById - no book found by id:{}", id);
            return null;
        }
        log.info("In findById book:{} found by id:{}", result, id);
        return BookDTO.fromBook(result);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
        log.info("In Delete - book  with id:{} successfully deleted", id);
    }
}
