package md.pentlaog.goodreadsclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.model.Author;
import md.pentlaog.goodreadsclone.repositories.AuthorRepository;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorServiceImp implements AuthorService {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorServiceImp(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public Author add(Author author) {
    return this.authorRepository.save(author);
  }

  @Override
  public List<Author> getAll() {
    List<Author> result = authorRepository.findAll();
    log.info("In getAll - {} authors found", result.size());
    return result;
  }

  @Override
  public Author findByFirstName(String firstName) {
    return authorRepository.findByFirstName(firstName);
  }

  @Override
  public Author findById(Long id) {
    Author result = authorRepository.findById(id).orElse(null);
    if (result == null) {
      log.warn("In findById - no author found by id:{}", id);
      return null;
    }
    log.info("In findById author:{} found by id:{}", result, id);
    return result;
  }

  @Override
  public void delete(Long id) {
    authorRepository.deleteById(id);
    log.info("In Delete - user with id:{} successfully deleted", id);
  }
}
