package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.User;

import java.util.List;

public interface UserService {
    UserDTO register(UserDTO user);

    List<UserDTO> getAll();

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

    void delete(Long id);

    UserDTO readBook(String username, Long bookId);
}
