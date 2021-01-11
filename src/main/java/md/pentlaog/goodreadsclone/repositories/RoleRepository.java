package md.pentlaog.goodreadsclone.repositories;

import md.pentlaog.goodreadsclone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
