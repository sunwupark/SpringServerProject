package NoMetroWaiting.NMW.controller;
import NoMetroWaiting.NMW.domain.Share;
import NoMetroWaiting.NMW.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DBController {
    private final MongoTemplate mongoTemplate;

    @GetMapping("")
    public String findById(){
        System.out.println("test");
        User tc = new User();
        tc.setName("나는 서누퍽이에요");

        Share share = new Share();
        share.setUser(tc);
        share.setDestination("도착지");
        share.setCurrentRoom("현재 역");
        share.setFinalDestination("찐 도착지");
        mongoTemplate.insert(tc);
        mongoTemplate.insert(share);
        return "TEST";
    }
}
