package com.github.TerryLight.LimonStand;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TerryLight.LimonStand.domain.Item;
import com.github.TerryLight.LimonStand.repository.ItemRepository;
import com.github.TerryLight.LimonStand.service.ItemService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import javax.xml.xpath.XPath;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class App {
    public static void main(String[] args) throws URISyntaxException {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(HttpServer.class).bindUntilJavaShutdown(Duration.ofSeconds(60), null);

       // CqlSession session = applicationContext.getBean(CqlSession.class);

       // ItemService itemService= applicationContext.getBean(ItemService.class);


      //  applicationContext.getBean(DisposableServer.class)
        //        .onDispose()
          //      .block(); // Starts the server in a blocking fashion, and waits for it to finish its initialization

    }

//    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /*  static ByteBuf toByteBuf(Object o){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }*/


/*static Item parseItem(String str){
    Item item = null;
    try {
        item = OBJECT_MAPPER.readValue(str, Item.class);
    } catch (JsonProcessingException ex) {
        String[] params = str.split("&");
        int id = Integer.parseInt(params[0].split("=")[1]);
        String name = params[1].split("=")[1];
        double price = Double.parseDouble(params[2].split("=")[1]);
        item = new Item(id, name, price);
    }
    return item;
}*/


}
