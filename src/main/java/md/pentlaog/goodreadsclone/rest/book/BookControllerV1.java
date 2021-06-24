package md.pentlaog.goodreadsclone.rest.book;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.BookDTO;
import md.pentlaog.goodreadsclone.dto.BookRequestDTO;
import md.pentlaog.goodreadsclone.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<BookDTO> getAllBooks(@RequestBody BookRequestDTO bookSortingRequestDTO) {
        return bookService.getAll();
    }

    @GetMapping(path = "/author")
    @PreAuthorize("hasAuthority('books:read')")
    public @ResponseBody
    List<BookDTO> getBooksByAuthor(@RequestParam Long id) {
        return bookService.getByAuthor(id);
    }


}
