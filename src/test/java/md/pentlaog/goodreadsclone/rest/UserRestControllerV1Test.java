package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.User;
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

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRestControllerV1Test {
  @Mock private UserService userService;

  @InjectMocks private UserRestControllerV1 userRestControllerV1;

  private final User user = new User();

  @BeforeEach
  void setMockOutput() {
    user.setUserName("UserName1");
    user.setEmail("username@email.com");
  }

  @Test
  @DisplayName("Should return a user")
  public void shouldReturnAUser() {
    when(userService.findById(anyLong())).thenReturn(user);
    ResponseEntity<UserDTO> result = userRestControllerV1.getUserById(anyLong());
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    assertThat(Objects.requireNonNull(result.getBody()).toUser()).isEqualTo(user);
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
}
