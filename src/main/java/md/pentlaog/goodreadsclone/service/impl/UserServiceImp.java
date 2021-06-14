package md.pentlaog.goodreadsclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.Role;
import md.pentlaog.goodreadsclone.model.Status;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.repositories.BookRepository;
import md.pentlaog.goodreadsclone.repositories.RoleRepository;
import md.pentlaog.goodreadsclone.repositories.UserRepository;
import md.pentlaog.goodreadsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(
            UserRepository userRepository,
            BookRepository bookRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO register(UserDTO user) {
        User newUser = new User();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(userRoles);
        newUser.setStatus(Status.ACTIVE);
        UserDTO registeredUser = UserDTO.fromUser(userRepository.save(newUser));
        registeredUser.setPassword(null);
        log.info("In register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> result = userRepository.findAll();
        log.info("In getAll - {} users found", result.size());
        return result.stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {
        User result = userRepository.findByuserName(username);
        log.info("In findByUsername - user:{} found by username:{}", result, username);
        return UserDTO.fromUser(result);
    }

    @Override
    public UserDTO findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("In findById - no user find by id:{}", id);
            return null;
        }
        log.info("In findById user:{} found by id:{}", result, id);
        return UserDTO.fromUser(result);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("In Delete - user with id:{} successfully deleted", id);
    }

    @Override
    public UserDTO readBook(String userName, Long bookId) {
        var user = userRepository.findByuserName(userName);
        var book = bookRepository.findById(bookId).orElse(null);
        if (!user.getReadBooks().contains(book)) {
            user.getReadBooks().add(book);
            return UserDTO.fromUser(userRepository.save(user));
        } else
            throw new RuntimeException("Book is already ridden");

    }

}
