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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorAdminControllerV1Test {
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorAdminControllerV1 authorAdminControllerV1;

    private AuthorDTO authorDTO;

    @BeforeEach
    public void beforeEach() {
        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setBirthDate(Instant.now());
        authorDTO.setFirstName("Mihai");
        authorDTO.setLastName("Cornelius");
    }

    @Test
    @DisplayName("Should be able to add new Author")
    void addNewAuthor() {
        when(authorService.add(authorDTO)).thenReturn(authorDTO);
        ResponseEntity<AuthorDTO> response = authorAdminControllerV1.addNewAuthor(authorDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(authorDTO);
    }
}