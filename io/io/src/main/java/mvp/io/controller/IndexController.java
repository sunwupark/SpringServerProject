package mvp.io.controller;
import lombok.RequiredArgsConstructor;
import mvp.io.config.auth.LoginUser;
import mvp.io.config.auth.dto.SessionUser;
import mvp.io.domain.Mission.Mission;
import mvp.io.domain.User.Users;
import mvp.io.service.MissionService;
import mvp.io.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;
    private final MissionService missionService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/mission")
    public String missionGet(Model model){
        List<Mission> missionList = missionService.getAllMissions(); // UserService에서 모든 User 목록을 가져오는 메서드 구현 필요
        model.addAttribute("mission", missionList);
        System.out.println("missionList = " + missionList);
        return "mission/my-mission";
    }

    @GetMapping("/mission/registration")
    public String missionRegistrationGet(Model model){
        Mission mission = new Mission(); // 빈 User 객체를 생성하거나 필요에 따라 초기화
        model.addAttribute("mission", mission); // 모델에 "users" 객체를 추가
        return "mission/mission-form"; // Thymeleaf 템플릿 이름 반환
    }

    @GetMapping("/posts")
    public String postsGet(Model model){
        List<Users> userList = userService.getAllUsers(); // UserService에서 모든 User 목록을 가져오는 메서드 구현 필요
        model.addAttribute("users", userList);
        return "user-list";
    }

    @GetMapping("/posts/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        Users user = userService.findById(id);
        System.out.println("user.getName() = " + user.getPoint());
        model.addAttribute("users", user);
        return "user-update";
    }

    @GetMapping("/posts/registration")
    public String postsSaveForm(Model model) {
        Users user = new Users(); // 빈 User 객체를 생성하거나 필요에 따라 초기화
        model.addAttribute("users", user); // 모델에 "users" 객체를 추가
        return "user-form"; // Thymeleaf 템플릿 이름 반환
    }
}
