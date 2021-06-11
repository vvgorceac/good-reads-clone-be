package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.service.BookService;
import md.pentlaog.goodreadsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestControllerV1 {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public UserRestControllerV1(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO res = UserDTO.fromUser(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        if (userService.findByUsername(user.getUserName()) != null)
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        userService.register(user);
        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @PostMapping(path = "/read/{bookId}")
    @PreAuthorize("hasAuthority('books:read')")
    public ResponseEntity<?> getBooksByAuthor(@PathVariable Long bookId, @CurrentSecurityContext(expression = "authentication.principal") Principal principal) {
        if (bookService.findById(bookId) == null)
            return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(UserDTO.fromUser(userService.readBook(principal.getName(), bookId)));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
