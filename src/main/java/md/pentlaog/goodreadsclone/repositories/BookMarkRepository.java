package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.model.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
  List<BookMark> findBookMarkByBookId(Long bookId);

  List<BookMark> findBookMarkByUserId(Long userId);

  @Query(value = "SELECT avg(mark) from book_marks where book_id = ?1", nativeQuery = true)
  double getMaxAgeMinus(Long bookId);
}
