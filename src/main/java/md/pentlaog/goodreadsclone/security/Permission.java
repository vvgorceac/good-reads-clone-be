package md.pentlaog.goodreadsclone.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {
  USER_READ("user:read"),
  USER_WRITE("user:write"),
  USER_DELETE("user:delete"),
  USER_DISABLE("user:disable"),
  AUTHOR_READ("author:read"),
  AUTHOR_WRITE("author:write"),
  AUTHOR_DELETE("author:delete"),
  BOOKS_READ("books:read"),
  BOOKS_WRITE("books:write"),
  BOOKS_SCORE("books:score"),
  BOOKS_DELETE("books:delete");

  @Getter private final String permission;
}
