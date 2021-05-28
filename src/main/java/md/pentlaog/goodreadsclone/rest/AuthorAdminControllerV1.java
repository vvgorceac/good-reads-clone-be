package md.pentlaog.goodreadsclone.rest;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.model.Author;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/authors/")
public class AuthorAdminControllerV1 {
  private final AuthorService authorService;

  @Autowired
  public AuthorAdminControllerV1(AuthorService authorService) {
    this.authorService = authorService;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('author:create')")
  public @ResponseBody Author addNewAuthor(@RequestBody Author author) {
    log.info("ADDING AUTHOR:{}", author);
    return authorService.add(author);
  }
}
