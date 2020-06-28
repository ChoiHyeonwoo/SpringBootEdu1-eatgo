package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.UserService;
import kr.co.fastcampus.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //1. user List
    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    //2. user Create
    @PostMapping("/users")
    public ResponseEntity<?> create(
            @RequestBody User resource
    ) throws URISyntaxException {
        String email = resource.getEmail();
        String name = resource.getName();

        User user = userService.addUser(email, name);

        String url = "/users/"+ user.getId();

        return ResponseEntity.created(new URI(url)).body("{}");
    }
    //3. user Update
    @PatchMapping("/users/{userId}")
    public String update(
            @PathVariable("userId") Long id
            ,@RequestBody User resource
    ) {
        userService.updateUser(id, resource.getEmail(), resource.getName(), resource.getLevel());

        return "{}";
    }

    //4. user Delete -> level: 0 으로 update -> 아무것도 못함
    // (level -> 1:customer, 2: restaurant Owner, 3: admin )
    @DeleteMapping("/users/{userId}")
    public String delete(@PathVariable("userId") Long id){
        userService.deactiveUser(id);

        return "{}";
    }

}
