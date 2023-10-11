package mvp.io.controller;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.Mission.Mission;
import mvp.io.domain.Mission.MissionSaveDto;
import mvp.io.domain.User.UserSaveDto;
import mvp.io.domain.User.Users;
import mvp.io.service.MissionService;
import mvp.io.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MissionController {
    private final MissionService missionService;

    @PostMapping(value = "/mission/registration", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String save(@ModelAttribute Mission mission){
        MissionSaveDto missionSaveDto =
                MissionSaveDto.builder()
                        .point(mission.getPoint())
                        .content(mission.getContent())
                        .build();
        missionService.save(missionSaveDto);
        return "redirect:/mission"; // 사용자 목록 페이지 URL로 변경하세요.
    }

    @PutMapping("/mission/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Mission mission){
        MissionSaveDto missionSaveDto =
                MissionSaveDto.builder()
                        .point(mission.getPoint())
                        .content(mission.getContent())
                        .build();

        missionService.update(id, missionSaveDto);
        return "redirect:/mission";
    }
//
    @ResponseBody
    @GetMapping("/mission/find/{id}")
    public Mission findById(@PathVariable Long id) {
        return missionService.findById(id);
    }

    @DeleteMapping("/mission/{id}")
    public String delete(@PathVariable Long id){
        missionService.delete(id);
        return "redirect:/mission";
    }

}
