package md.pentlaog.goodreadsclone.controllers;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.entities.Book;
import md.pentlaog.goodreadsclone.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@Slf4j
//@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(path = "/books")
    public @ResponseBody
    Book addNewBook(@RequestParam String name) {
        Book b = new Book();
        b.setName(name);
        return bookRepository.save(b);
    }

    @GetMapping(path = "/books")
    public @ResponseBody
    Iterable<Book> getAllUsers() {
        // This returns a JSON or XML with the users
        return bookRepository.findAll();
    }

}
