package md.pentlaog.goodreadsclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.AuthorDTO;
import md.pentlaog.goodreadsclone.model.Author;
import md.pentlaog.goodreadsclone.repositories.AuthorRepository;
import md.pentlaog.goodreadsclone.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImp(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDTO add(AuthorDTO author) {
        return AuthorDTO.fromAuthor(this.authorRepository.save(author.toAuthor()));
    }

    @Override
    public List<AuthorDTO> getAll() {
        List<Author> result = authorRepository.findAll();
        log.info("In getAll - {} authors found", result.size());
        return result.stream().map(AuthorDTO::fromAuthor).collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findByFirstName(String firstName) {
        return AuthorDTO.fromAuthor(authorRepository.findByFirstName(firstName));
    }

    @Override
    public AuthorDTO findById(Long id) {
        Author result = authorRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("In findById - no author found by id:{}", id);
            return null;
        }
        log.info("In findById author:{} found by id:{}", result, id);
        return AuthorDTO.fromAuthor(result);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
        log.info("In Delete - user with id:{} successfully deleted", id);
    }
}
