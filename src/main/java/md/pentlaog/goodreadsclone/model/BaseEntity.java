package md.pentlaog.goodreadsclone.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @CreatedDate
  @Column(name = "created")
  private Instant created;

  @LastModifiedDate
  @Column(name = "updated")
  private Instant updated;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status;
}
