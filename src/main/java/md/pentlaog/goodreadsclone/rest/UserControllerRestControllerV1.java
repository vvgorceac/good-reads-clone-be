package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserControllerRestControllerV1 {

  @Autowired private UserService userService;

  @GetMapping(value = "{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
    User user = userService.findById(id);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    UserDTO res = UserDTO.fromUser(user);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
