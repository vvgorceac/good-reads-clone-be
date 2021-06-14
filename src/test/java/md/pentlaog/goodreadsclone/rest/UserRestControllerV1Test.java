package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.BookDTO;
import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.Book;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.service.BookService;
import md.pentlaog.goodreadsclone.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRestControllerV1Test {
    @Mock
    private UserService userService;
    @Mock
    private BookService bookService;
    @Mock
    private Principal principal;


    @InjectMocks
    private UserRestControllerV1 userRestControllerV1;

    private final UserDTO user = new UserDTO();

    @BeforeEach
    void setMockOutput() {
        user.setUsername("UserName1");
        user.setEmail("username@email.com");
    }

    @Test
    @DisplayName("Should return a user")
    public void shouldReturnAUser() {
        when(userService.findById(anyLong())).thenReturn(user);
        ResponseEntity<UserDTO> result = userRestControllerV1.getUserById(anyLong());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(Objects.requireNonNull(result.getBody())).isEqualTo(user);
    }

    @Test
    @DisplayName("Should return no content")
    public void shouldReturnNoContentIfNoUser() {
        when(userService.findById(anyLong())).thenReturn(null);
        ResponseEntity<UserDTO> result = userRestControllerV1.getUserById(anyLong());

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody()).isNull();
    }

    @Test
    @DisplayName("Should register a new user")
    public void shouldRegisterNewUser() {
        when(userService.findByUsername(anyString())).thenReturn(null);
        ResponseEntity<String> response = userRestControllerV1.register(user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("");
    }

    @Test
    @DisplayName("Should not register an existing user")
    public void shouldNotRegisterExistingUser() {
        when(userService.findByUsername(anyString())).thenReturn(user);
        ResponseEntity<String> response = userRestControllerV1.register(user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("User already exists");
    }

    @Test
    @DisplayName("Should be able to mark book as read")
    public void shouldBeAbleMarkBookAsRead() {
        var isbn = "1234567";
        var bookName = "Book Name 1";
        var booksList = new ArrayList<BookDTO>();
        var book = new BookDTO();
        book.setIsbn(isbn);
        book.setName(bookName);
        booksList.add(book);
        user.setReadBooks(booksList);
        when(userService.readBook(anyString(), anyLong()))
                .thenReturn(user);
        when(bookService.findById(anyLong())).thenReturn(book);
        when(principal.getName()).thenReturn("vgorceac");

        ResponseEntity<UserDTO> response = (ResponseEntity<UserDTO>) userRestControllerV1.readBook(anyLong(), principal);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(user);
    }

    @Test
    @DisplayName("Should not be able to read nonexistent book")
    public void shouldNotBeAbleToReadNonexistentBook() {
        when(bookService.findById(anyLong())).thenReturn(null);
        ResponseEntity<?> response = userRestControllerV1.readBook(anyLong(), principal);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
