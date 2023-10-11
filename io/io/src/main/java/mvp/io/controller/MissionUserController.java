package mvp.io.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mvp.io.config.auth.CustomOAuth2UserService;
import mvp.io.domain.Mission.MissionUserList;
import mvp.io.service.MissionService;
import mvp.io.service.MissionUserListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MissionUserController {
    private final MissionUserListService missionUserListService;

    @GetMapping("/missionuser")
    public String getMissionUserList(Model model){


        Long Id = 1L;
        List<MissionUserList> missionUserList = missionUserListService.getMissionUserListByEmail(Id);
        System.out.println("missionUserList = " + missionUserList);
        model.addAttribute("missionList", missionUserList);
        return "my-mission";
    }

}
