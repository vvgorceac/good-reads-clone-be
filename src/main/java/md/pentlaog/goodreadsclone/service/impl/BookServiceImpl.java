package md.pentlaog.goodreadsclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.dto.BookDTO;
import md.pentlaog.goodreadsclone.model.Author;
import md.pentlaog.goodreadsclone.model.Book;
import md.pentlaog.goodreadsclone.repositories.AuthorRepository;
import md.pentlaog.goodreadsclone.repositories.BookRepository;
import md.pentlaog.goodreadsclone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  @Override
  public List<Book> getAll() {
    List<Book> res = bookRepository.findAll();
    log.info("in getAll- books:{}", res);
    return res;
  }

  @Override
  public List<Book> getByAuthor(Long id) {
    return bookRepository.findBooksByAuthorId(id);
  }

  @Override
  public Book findByName(String name) {
    return bookRepository.findByName(name);
  }

  @Override
  public Book add(BookDTO book) {
    Author author = authorRepository.findById(book.getAuthorDTO().getId()).orElse(null);
    book.setAuthorDTO(AuthorDTO.fromAuthor(author));
    return bookRepository.save(book.toBook());
  }

  @Override
  public Book findById(Long id) {
    Book result = bookRepository.findById(id).orElse(null);
    if (result == null) {
      log.warn("In findById - no book found by id:{}", id);
      return null;
    }
    log.info("In findById book:{} found by id:{}", result, id);
    return result;
  }

  @Override
  public void delete(Long id) {
    bookRepository.deleteById(id);
    log.info("In Delete - book  with id:{} successfully deleted", id);
  }
}
