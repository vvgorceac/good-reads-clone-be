package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.model.BookMark;

import java.util.List;

public interface BookMarkService {
  List<BookMark> getAll();

  BookMark setBookScore(Long bookId, String userName, int score);

  Double getBookRating(Long id);
}
