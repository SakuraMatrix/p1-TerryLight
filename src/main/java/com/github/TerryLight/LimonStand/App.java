package com.github.TerryLight.LimonStand;

import com.github.TerryLight.LimonStand.domain.Order;
import com.github.TerryLight.LimonStand.repository.OrderRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
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
