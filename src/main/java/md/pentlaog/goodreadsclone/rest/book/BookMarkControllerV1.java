package md.pentlaog.goodreadsclone.rest.book;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.BookMarkDTO;
import md.pentlaog.goodreadsclone.model.BookMark;
import md.pentlaog.goodreadsclone.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<BookMarkDTO> getBookMarks() {
        return bookMarkService.getAll();
    }

    @PostMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('books:score')")
    public ResponseEntity<?> markBookAsRead(
            @PathVariable Long id,
            @CurrentSecurityContext(expression = "authentication.principal") Principal principal,
            @RequestParam int score) {
        try {
            return ResponseEntity.ok().body(bookMarkService.setBookScore(id, principal.getName(), score));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping(path = "/{id}/rating")
    public Double getAverageRating(@PathVariable Long id) {
        return bookMarkService.getBookRating(id);
    }
}
