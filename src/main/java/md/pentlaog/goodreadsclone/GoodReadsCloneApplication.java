package md.pentlaog.goodreadsclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoodReadsCloneApplication {

  public static void main(String[] args) {
    SpringApplication.run(GoodReadsCloneApplication.class, args);
  }
}
