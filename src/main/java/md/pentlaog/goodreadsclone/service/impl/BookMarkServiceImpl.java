package md.pentlaog.goodreadsclone.service.impl;

import md.pentlaog.goodreadsclone.exceptions.BookNotFoundException;
import md.pentlaog.goodreadsclone.model.BookMark;
import md.pentlaog.goodreadsclone.repositories.BookMarkRepository;
import md.pentlaog.goodreadsclone.repositories.BookRepository;
import md.pentlaog.goodreadsclone.repositories.UserRepository;
import md.pentlaog.goodreadsclone.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookMarkServiceImpl implements BookMarkService {

  private final BookMarkRepository bookMarkRepository;
  private final UserRepository userRepository;
  private final BookRepository bookRepository;

  @Autowired
  public BookMarkServiceImpl(
      BookMarkRepository bookMarkRepository,
      UserRepository userRepository,
      BookRepository bookRepository) {
    this.bookMarkRepository = bookMarkRepository;
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  @Override
  public List<BookMark> getAll() {
    return bookMarkRepository.findAll();
  }

  @Override
  public BookMark markBookAsRead(Long bookId, String userName) {
    var user = userRepository.findByuserName(userName);
    if (user == null) {
      throw new UsernameNotFoundException("User with username:" + userName + " not found");
    }

    var book = bookRepository.findById(bookId).orElse(null);
    if (book == null) {
      throw new BookNotFoundException(bookId);
    }

    var bookMark = new BookMark();

    bookMark.setUser(user);
    bookMark.setBook(book);
    return bookMarkRepository.save(bookMark);
  }

  @Override
  public Double getBookRating(Long id) {
    return bookMarkRepository.getMaxAgeMinus(id);
  }
}
