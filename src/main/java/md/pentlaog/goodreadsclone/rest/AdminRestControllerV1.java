package md.pentlaog.goodreadsclone.rest;

import md.pentlaog.goodreadsclone.dto.UserDTO;
import md.pentlaog.goodreadsclone.model.User;
import md.pentlaog.goodreadsclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

  @Autowired private UserService userService;

  @GetMapping(value = "users/{id}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id) {
    UserDTO user = userService.findById(id);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
