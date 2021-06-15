package md.pentlaog.goodreadsclone.rest.author;

import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorControllerV1Test {
    @Mock
    private AuthorService authorService;


    @InjectMocks
    private AuthorControllerV1 authorControllerV1;

    private List<AuthorDTO> authors;

    @BeforeEach
    public void setUp() {
        authors = new ArrayList<>();
        AuthorDTO authorDTO1 = new AuthorDTO();
        AuthorDTO authorDTO2 = new AuthorDTO();
        AuthorDTO authorDTO3 = new AuthorDTO();

        authorDTO1.setLastName("Makrovich");
        authorDTO1.setFirstName("Arasd");
        authorDTO1.setBirthDate(Instant.now());
        authorDTO1.setId(1L);

        authorDTO2.setLastName("Stimermann");
        authorDTO2.setFirstName("Link");
        authorDTO2.setBirthDate(Instant.now());
        authorDTO2.setId(2L);

        authorDTO3.setLastName("Ivanov");
        authorDTO3.setFirstName("Popov");
        authorDTO3.setBirthDate(Instant.now());
        authorDTO3.setId(3L);
    }

    @Test
    @DisplayName("should be able to get all authors")
    void getAllAuthors() {
        when(authorService.getAll()).thenReturn(authors);
        ResponseEntity<List<AuthorDTO>> response = authorControllerV1.getAllAuthors();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(authors);
    }
}