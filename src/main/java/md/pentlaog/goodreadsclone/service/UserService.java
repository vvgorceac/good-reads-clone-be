package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    User readBook(String username, Long bookId);
}
