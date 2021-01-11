package md.pentlaog.goodreadsclone.rest;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.model.Author;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/authors/")
public class AuthorController {
  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @PostMapping
  public @ResponseBody Author addNewAuthor(@RequestBody Author author) {
    log.info("ADDING AUTHOR:{}", author);
    return authorService.add(author);
  }

  @GetMapping
  public @ResponseBody List<AuthorDTO> getAllAuthors() {
    return authorService.getAll().stream().map(AuthorDTO::fromAuthor).collect(Collectors.toList());
  }
}
