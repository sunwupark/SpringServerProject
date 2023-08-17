package NoMetroWaiting.NMW.controller;

import NoMetroWaiting.NMW.service.MemberService;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
    @PersistenceContext
    private final MemberService memberService;

    @GetMapping("")
    public String findById(@PathVariable String title){
        System.out.println("test");
        return null;
        // return (ShelterNameSearchResponsseDto) shelterService.getShelterList(title);
    }
}