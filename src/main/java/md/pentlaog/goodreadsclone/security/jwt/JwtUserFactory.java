package md.pentlaog.goodreadsclone.security.jwt;

import md.pentlaog.goodreadsclone.model.Role;
import md.pentlaog.goodreadsclone.model.Status;
import md.pentlaog.goodreadsclone.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {
  public JwtUserFactory() {}

  public static JwtUser create(User user) {
    return new JwtUser(
        user.getId(),
        user.getUserName(),
        user.getFirstName(),
        user.getLastName(),
        user.getPassword(),
        user.getEmail(),
        user.getStatus().equals(Status.ACTIVE),
        user.getUpdated(),
        mapToGrantedAuthorities(user.getRoles()));
  }

  private static Set<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
    return userRoles.stream()
        .flatMap(role -> role.getName().getAuthorities().stream())
        .collect(Collectors.toSet());
  }
}
