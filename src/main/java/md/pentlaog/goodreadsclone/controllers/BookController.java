package md.pentlaog.goodreadsclone.controllers;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.entities.Book;
import md.pentlaog.goodreadsclone.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@Slf4j
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(path = "/")
    public @ResponseBody
    Book addNewBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
