//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package jpabook.jpashop;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
class InitService {
  private final EntityManager em;

  public void dbInit1() {
    System.out.println("Init1" + this.getClass());
    Member member = this.createMember("userA", "서울", "1", "1111");
    this.em.persist(member);
    Book book1 = this.createBook("JPA1 BOOK", 10000, 100);
    this.em.persist(book1);
    Book book2 = this.createBook("JPA2 BOOK", 20000, 100);
    this.em.persist(book2);
    OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
    OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
    Delivery delivery = this.createDelivery(member);
    Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
    this.em.persist(order);
  }

  public void dbInit2() {
    Member member = this.createMember("userB", "진주", "2", "2222");
    this.em.persist(member);
    Book book1 = this.createBook("SPRING1 BOOK", 20000, 200);
    this.em.persist(book1);
    Book book2 = this.createBook("SPRING2 BOOK", 40000, 300);
    this.em.persist(book2);
    OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
    OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
    Delivery delivery = this.createDelivery(member);
    Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
    this.em.persist(order);
  }

  private Member createMember(String name, String city, String street, String zipcode) {
    Member member = new Member();
    member.setName(name);
    member.setAddress(new Address(city, street, zipcode));
    return member;
  }

  private Book createBook(String name, int price, int stockQuantity) {
    Book book1 = new Book();
    book1.setName(name);
    book1.setPrice(price);
    book1.setStockQuantity(stockQuantity);
    return book1;
  }

  private Delivery createDelivery(Member member) {
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());
    return delivery;
  }

  public InitService(final EntityManager em) {
    this.em = em;
  }
}