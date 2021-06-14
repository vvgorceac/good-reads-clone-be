package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.dto.BookMarkDTO;
import md.pentlaog.goodreadsclone.model.BookMark;

import java.util.List;

public interface BookMarkService {
  List<BookMarkDTO> getAll();

  BookMarkDTO setBookScore(Long bookId, String userName, int score);

  Double getBookRating(Long id);
}
