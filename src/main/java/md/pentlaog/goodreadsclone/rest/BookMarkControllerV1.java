package md.pentlaog.goodreadsclone.rest;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.model.BookMark;
import md.pentlaog.goodreadsclone.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/book/mark")
public class BookMarkControllerV1 {

  private final BookMarkService bookMarkService;

  @Autowired
  public BookMarkControllerV1(BookMarkService bookMarkService) {
    this.bookMarkService = bookMarkService;
  }

  @GetMapping
  public List<BookMark> getBookMarks() {
    return bookMarkService.getAll();
  }

  @PostMapping(path = "/{id}")
  public BookMark markBookAsRead(
      @PathVariable Long id,
      @CurrentSecurityContext(expression = "authentication.principal") Principal principal,
      @RequestParam int score) {

    return bookMarkService.markBook(id, score, principal.getName());
  }

  @GetMapping(path = "/{id}/rating")
  public Double getAverageRating(@PathVariable Long id) {
    return bookMarkService.getBookRating(id);
  }
}
