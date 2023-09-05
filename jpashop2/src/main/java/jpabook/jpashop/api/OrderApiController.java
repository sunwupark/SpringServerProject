package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Ord
        erApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/oroders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.f
    }
}
