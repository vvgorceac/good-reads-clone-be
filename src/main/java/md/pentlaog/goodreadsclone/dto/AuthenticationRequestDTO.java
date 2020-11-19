package md.pentlaog.goodreadsclone.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDTO {
  @NotNull(message = "Username shouldn't be empty")
  private String username;

  @NotNull(message = "Password shouldn't be empty")
  @Size(min = 4)
  private String password;
}
