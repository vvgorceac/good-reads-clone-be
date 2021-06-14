package md.pentlaog.goodreadsclone.security;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.repositories.UserRepository;
import md.pentlaog.goodreadsclone.security.jwt.JwtUser;
import md.pentlaog.goodreadsclone.security.jwt.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadUserByUsername - user with username:{} successfully loaded", username);
        return jwtUser;
    }
}
