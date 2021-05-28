package md.pentlaog.goodreadsclone.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {
  ADMIN(Arrays.stream(Permission.values()).collect(Collectors.toSet())),
  CONTENT_MANAGER(
      Set.of(
          Permission.AUTHOR_READ,
          Permission.AUTHOR_WRITE,
          Permission.BOOKS_WRITE,
          Permission.BOOKS_READ)),
  USER(Set.of(Permission.BOOKS_SCORE, Permission.BOOKS_READ, Permission.AUTHOR_READ));

  @Getter private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    return getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toList());
  }
}
