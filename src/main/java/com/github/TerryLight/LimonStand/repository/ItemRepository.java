package com.github.TerryLight.LimonStand.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.TerryLight.LimonStand.domain.Item;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*import java.util.Arrays;
import java.util.List;*/

@Repository

public class ItemRepository {
    private CqlSession session;

    public ItemRepository(CqlSession session) {
        this.session = session;
    }
    public Flux<Item> getAll(){
        return Flux.from(session.executeReactive("Select * FROM limonstand.items"))
            .map(row -> new Item(row.getInt("item_id"), row.getString("name"),row.getDouble("price")));

    }

    public Mono<Item> get(int id){
        return Mono.from(session.executeReactive("SELECT * FROM limonstand.items WHERE item_id = " + id))
            .map(row -> new Item(row.getInt("item_id"), row.getString("name"),row.getDouble("price")));
    }

    public Item create(Item item){
        SimpleStatement stmt = SimpleStatement.builder("INSERT INTO limonstand.items(item_id, name, price) values (?, ?, ?)")
                        .addPositionalValues(item.getId(),item.getName(),item.getPrice())
                                .build();
        Flux.from(session.executeReactive(stmt)).share();
        return item;
    }
}
/*    static List<Item> cache = Arrays.asList( new Item( 1, "Strawberry", 5), new Item(2, "Blueberry", 6));


    public List<Item> getAll() {//{return Arrays.asList(new Item( 1, "Strawberry", 5), new Item(2, "Blueberry", 6));}
        return cache;
    }
    public Item get(String id) {
        int itemId = Integer.parseInt(id);
        return cache.get(itemId - 1);*/
