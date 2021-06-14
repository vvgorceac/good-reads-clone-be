package md.pentlaog.goodreadsclone.service.impl;

import md.pentlaog.goodreadsclone.dto.AuthenticationRequestDTO;
import md.pentlaog.goodreadsclone.dto.LoginResponseDTO;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.repositories.UserRepository;
import md.pentlaog.goodreadsclone.security.jwt.JwtTokenProvider;
import md.pentlaog.goodreadsclone.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LoginResponseDTO login(AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            String username = authenticationRequestDTO.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username, authenticationRequestDTO.getPassword()));
            User user = userRepository.findByuserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username:" + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setUsername(username);
            loginResponseDTO.setToken(token);

            return loginResponseDTO;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid user or password");
        }
    }
}
