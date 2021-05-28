package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserRestControllerV1 {

  private final UserService userService;

  @Autowired
  public UserRestControllerV1(UserService userService) {
    this.userService = userService;
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

  @PostMapping
  public void updateUserRole() {}
}
