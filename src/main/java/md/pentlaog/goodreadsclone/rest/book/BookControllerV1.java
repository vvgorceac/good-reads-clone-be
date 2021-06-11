package md.pentlaog.goodreadsclone.rest.book;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.BookDTO;
import md.pentlaog.goodreadsclone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/books")
public class BookControllerV1 {
    private final BookService bookService;

    @Autowired
    public BookControllerV1(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "")
    @PreAuthorize("hasAuthority('books:read')")
    public @ResponseBody
    List<BookDTO> getAllBooks() {
        return bookService.getAll().stream().map(BookDTO::fromBook).collect(Collectors.toList());
    }

    @GetMapping(path = "/author")
    @PreAuthorize("hasAuthority('books:read')")
    public @ResponseBody
    List<BookDTO> getBooksByAuthor(@RequestParam Long id) {
        return bookService.getByAuthor(id).stream().map(BookDTO::fromBook).collect(Collectors.toList());
    }
}
