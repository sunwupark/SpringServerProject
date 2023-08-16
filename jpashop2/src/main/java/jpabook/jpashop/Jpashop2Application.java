package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jpashop2Application {

    Hello hello = new Hello();
    hello.setData("dfdf");
    String data = hello.getData();

    public static void main(String[] args) {
        SpringApplication.run(Jpashop2Application.class, args);
    }

}
