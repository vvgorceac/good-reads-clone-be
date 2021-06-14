package md.pentlaog.goodreadsclone.rest.book;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.BookDTO;
import md.pentlaog.goodreadsclone.model.Book;
import md.pentlaog.goodreadsclone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/admin/books")
public class BookAdminControllerV1 {
    private final BookService bookService;

    @Autowired
    public BookAdminControllerV1(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "")
    @PreAuthorize("hasAuthority('books:write')")
    public ResponseEntity<BookDTO> addNewBook(@Valid @RequestBody BookDTO book) {
        return ResponseEntity.ok().body(bookService.add(book));
    }

    @DeleteMapping(path = "/{bookId}")
    @PreAuthorize("hasAuthority('book:delete')")
    public ResponseEntity<?> removeBook(@PathVariable Long bookId) {
        var book = bookService.findById(bookId);
        if (book == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.noContent().build();
    }
}
