package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;
    private String name;

    private String nickname;

    @OneToOne(mappedBy = "member")
    private List<Share> share = new ArrayList<>();
}
