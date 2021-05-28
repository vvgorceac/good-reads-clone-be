package md.pentlaog.goodreadsclone.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
  @Column(name = "name")
  @Enumerated(EnumType.STRING)
  private md.pentlaog.goodreadsclone.security.Role name;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  @ToString.Exclude
  //  @JsonManagedReference
  private List<User> users;
}

