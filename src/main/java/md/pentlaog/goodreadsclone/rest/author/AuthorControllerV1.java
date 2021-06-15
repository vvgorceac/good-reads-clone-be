package md.pentlaog.goodreadsclone.rest.author;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAll());
    }
}
