package NoMetroWaiting.NMW.controller;

import NoMetroWaiting.NMW.Book;
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
        Book tc = new Book();
        tc.setId(1L);
        tc.setName("나는 서누퍽이에요");
        mongoTemplate.insert(tc);
        return "TEST";
    }
}
