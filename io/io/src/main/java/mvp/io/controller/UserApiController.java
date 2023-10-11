package mvp.io.controller;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.User.UserSaveDto;
import mvp.io.domain.User.Users;
import mvp.io.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserApiController {
    private final UserService userService;

    @PostMapping(value = "/posts/registration", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String save(@ModelAttribute Users user){
        UserSaveDto userSaveDto =
                UserSaveDto.builder()
                        .name(user.getName())
                        .point(user.getPoint())
                        .birthdate(user.getBirthdate())
                        .address(user.getAddress())
                        .build();
        userService.save(userSaveDto);
        return "redirect:/posts"; // 사용자 목록 페이지 URL로 변경하세요.
    }

    @PutMapping("/posts/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Users user){
        UserSaveDto userSaveDto =
                UserSaveDto.builder()
                        .name(user.getName())
                        .point(user.getPoint())
                        .birthdate(user.getBirthdate())
                        .address(user.getAddress())
                        .build();

        userService.update(id, userSaveDto);
        return "redirect:/posts";
    }

    @ResponseBody
    @GetMapping("/posts/find/{id}")
    public Users findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/posts/{id}")
    public String delete(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/posts";
    }

//    @GetMapping("api/v1/userList")
//    public List<Users> getUserList(Model model) {
//        List<Users> userList = userService.getAllUsers(); // UserService에서 모든 User 목록을 가져오는 메서드 구현 필요
//        model.addAttribute("users", userList);
//        return userList;
//    }

}
