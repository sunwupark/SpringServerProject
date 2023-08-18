package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        Member member = createMember();
        Book book = createBook("시골 jpa", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품주문시 상태는 Order: ", OrderStatus.ORDER, getOrder.getStatus());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setAddress(new Address("seoul", "river", "123--123"));
        member.setName("회원1");
        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception{
        Member member = createMember();
        Book item = createBook("시골 jpa", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);
        Order getOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals("주문취소시 상태는 Cancel이", OrderStatus.CANCEL, getOrder.getStatus());

    }
    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        Member member = createMember();
        Book item = createBook("시골 jpa", 10000, 10);
        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);

        fail("재고수량 부족 오류가 발생해야 한다");


    }
}