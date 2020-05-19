package md.pentlaog.goodreadsclone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import md.pentlaog.goodreadsclone.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO {
  private Long id;
  private String username;
  private String firstName;
  private String lastName;
  private String email;

  public User toUser() {
    User user = new User();
    user.setId(id);
    user.setUserName(username);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);

    return user;
  }

  public static UserDTO fromUser(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setUsername(user.getUserName());
    userDTO.setFirstName(user.getFirstName());
    userDTO.setLastName(user.getLastName());
    userDTO.setEmail(user.getEmail());

    return userDTO;
  }
}
