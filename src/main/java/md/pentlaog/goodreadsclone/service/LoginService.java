package md.pentlaog.goodreadsclone.service;

import md.pentlaog.goodreadsclone.dto.AuthenticationRequestDTO;
import md.pentlaog.goodreadsclone.dto.LoginResponseDTO;

public interface LoginService {
    public LoginResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO);
}
