package md.pentlaog.goodreadsclone.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends BaseEntity {

  @NotNull(message = "${validation.user.username.not_null}")
  @Size(min = 6, message = "${validation.user.username.min_length}")
  @Column(name = "username")
  private String userName;

  @NotNull(message = "First Name should not be empty")
  @Size(min = 6, message = "First Name should")
  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @NotNull
  @Email
  @Column(name = "email")
  private String email;

  @NotNull(message = "Password should not be empty")
  @Size(min = 8, message = "Password should be at least 8 characters long")
  @Column(name = "password")
  private String password;

  @ManyToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(
      name = "read_books",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")})
  private List<Book> readBooks;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
  @Column(name = "roles")
  //  @JsonBackReference
  private List<Role> roles;
}
