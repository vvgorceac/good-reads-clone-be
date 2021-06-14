package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.dto.AuthorDTO;

import java.util.List;

public interface AuthorService {
    AuthorDTO add(AuthorDTO author);

    List<AuthorDTO> getAll();

    AuthorDTO findByFirstName(String authorName);

    AuthorDTO findById(Long id);

    void delete(Long id);
}
