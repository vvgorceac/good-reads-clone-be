package md.pentlaog.goodreadsclone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import md.pentlaog.goodreadsclone.model.User;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<BookDTO> readBooks;

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUserName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        if (!user.getReadBooks().isEmpty())
            userDTO.setReadBooks(user.getReadBooks().stream().map(BookDTO::fromBook).collect(Collectors.toList()));
        return userDTO;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }
}
