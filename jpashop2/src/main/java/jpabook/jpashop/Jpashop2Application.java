package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jpashop2Application {
    public static void main(String[] args) {
        SpringApplication.run(Jpashop2Application.class, args);
        Hello hello = new Hello();
        hello.setData("1L");
        String data = hello.getData();
        System.out.println("data = " + data);
    }

}
