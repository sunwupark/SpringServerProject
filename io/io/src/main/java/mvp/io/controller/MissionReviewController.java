package mvp.io.controller;

import lombok.RequiredArgsConstructor;
import mvp.io.domain.Mission.MissionReview;
import mvp.io.repository.UserRepository;
import mvp.io.service.MissionReviewService;
import mvp.io.service.MissionService;
import mvp.io.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MissionReviewController {
    private final MissionReviewService missionReviewService;
    private final UserService userService;
    private final MissionService missionService;

    @GetMapping("/missionReviews")
    public String getAllReview(Model model){
        List<MissionReview> allMissions = missionReviewService.getAllMissionReviews();
        model.addAttribute("missionreviews", allMissions);
        return "mission/misison-reviews";
    }
}
