package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.model.BookMark;

import java.util.List;

public interface BookMarkService {
  List<BookMark> getAll();

  BookMark markBook(Long bookId, int score, String userName);

  Double getBookRating(Long id);
}
