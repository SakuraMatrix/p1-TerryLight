package com.github.TerryLight.LimonStand.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.TerryLight.LimonStand.domain.Order;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository

public class OrderRepository {
    private CqlSession session;

    public OrderRepository(CqlSession session) {
        this.session = session;
    }
    public Flux<Order> getAll(){
        return Flux.from(session.executeReactive("Select * FROM lightnfresh.orders"))
                .map(row -> new Order(row.getInt("order_id"), row.getString("CustomerName"),row.getString("DrinkName"),row.getString("CupSize"),row.getDouble("price")));

    }

    public Mono<Order> get(int id){
        return Mono.from(session.executeReactive("SELECT * FROM lightnfresh.orders WHERE order_id = " + id))
                .map(row -> new Order(row.getInt("order_id"), row.getString("CustomerName"),row.getString("DrinkName"),row.getString("CupSize"),row.getDouble("price")));
    }

   /* public Mono<Order> get(double price){
        return Mono.from(session.executeReactive("SELECT * FROM lightnfresh.orders WHERE price = " + price))
                .map(row -> new Order(row.getInt("order_id"), row.getString("CustomerName"),row.getString("DrinkName"),row.getString("CupSize"),row.getDouble("price")));
    }*/


    public Order create(Order order){
        SimpleStatement stmt = SimpleStatement.builder("INSERT INTO lightnfresh.orders(order_id, customerName, drinkName, cupSize, price) values (?,?,?,?,?)")
                .addPositionalValues(order.getId(),order.getCustomerName(),order.getDrinkName(),order.getCupSize(),order.getPrice())
                .build();
        Flux.from(session.executeReactive(stmt)).share();
        return order;
    }

    /*public Mono<Integer> deleteOrder(int uuid) {
      //  log.info("Attempting to delete");
        Flux.from(
                session.executeReactive(
                        SimpleStatement.builder("DELETE FROM lightnfresh.orders WHERE order_id = ?")
                                .addPositionalValue(uuid)
                                .build()))
                .subscribe();
        return Mono.just(uuid);
    }*/

}
