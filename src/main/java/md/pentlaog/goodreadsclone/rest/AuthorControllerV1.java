package md.pentlaog.goodreadsclone.rest;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
@RequestMapping(path = "/api/v1/authors/")
public class AuthorControllerV1 {
  private final AuthorService authorService;

  @Autowired
  public AuthorControllerV1(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('author:read')")
  public @ResponseBody List<AuthorDTO> getAllAuthors() {
    return authorService.getAll().stream().map(AuthorDTO::fromAuthor).collect(Collectors.toList());
  }
}
