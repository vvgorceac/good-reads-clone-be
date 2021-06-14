package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.AuthenticationRequestDTO;
import md.pentlaog.goodreadsclone.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final LoginService loginService;

    @Autowired
    public AuthenticationRestControllerV1(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(
            @Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
        try {
            var response = loginService.login(authenticationRequestDTO);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException | BadCredentialsException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
