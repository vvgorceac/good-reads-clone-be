package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByuserName(String name);
}
