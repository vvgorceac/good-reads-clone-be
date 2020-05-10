package md.pentlaog.goodreadsclone.controllers;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.entities.Author;
import md.pentlaog.goodreadsclone.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping(path = "/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping(path = "/")
    public @ResponseBody
    Author addNewAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }


    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }


}
