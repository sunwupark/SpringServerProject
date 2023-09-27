package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String user(){
        return "get users";
    }
    @PostMapping
    public String addUser(){
        return "posot users";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get Users = " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update Users = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete Users = " + userId;
    }
}
