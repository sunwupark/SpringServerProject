package mvp.io.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mvp.io.config.auth.CustomOAuth2UserService;
import mvp.io.config.auth.dto.SessionUser;
import mvp.io.domain.Mission.MissionUserList;
import mvp.io.domain.User.Users;
import mvp.io.service.MissionService;
import mvp.io.service.MissionUserListService;
import mvp.io.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MissionUserController {
    private final MissionUserListService missionUserListService;
    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/missionuser")
    public String getMissionUserList(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        Users findUser = userService.findByEmail(user.getEmail());
        List<MissionUserList> missionUserList = missionUserListService.getMissionUserListById(findUser.getId());
        model.addAttribute("missionList", missionUserList);
        System.out.println("missionUserList = " + missionUserList);
        return "mission/my-missionLisit";
    }

    @PostMapping("/missionuser/registration/{missionId}")
    public String PostMissionUserList(@PathVariable Long missionId){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        Users findUser = userService.findByEmail(user.getEmail());

        Long order = missionUserListService.order(findUser.getId(), missionId);
        System.out.println("order = " + order);
        System.out.println("missionId = " + missionId);
        return "redirect:/missionuser";
    }

}
