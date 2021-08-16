package com.github.TerryLight.LimonStand;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TerryLight.LimonStand.repository.ItemRepository;
import com.github.TerryLight.LimonStand.service.ItemService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import javax.xml.xpath.XPath;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App {
    public static void main(String[] args) throws URISyntaxException {
        Path indexHTML= Paths.get(App.class.getResource("/index.html").toURI());
        Path errorHTML= Paths.get(App.class.getResource("/error.html").toURI());


        CqlSession session = CqlSession.builder().build();
        ItemRepository itemRepository = new ItemRepository(session);
        ItemService itemService= new ItemService(itemRepository);
        HttpServer.create()   // Prepares an HTTP server ready for configuration
                .port(8080)    // Configures the port number as zero, this will let the system pick up
                // an ephemeral port when binding the server
                .route(routes ->
                        // The server will respond only on POST requests
                        // where the path starts with /test and then there is path parameter
                        routes.get("/items", (request, response) ->
                                response.send(itemService.getAll().map(App::toByteBuf)
                                        .log("http-server")))
                                /*.post("/items", (request,response)->
                                        request.receive().then().map()*/
                            .get("/items/{param}",(request, response) ->
                                    response.send(itemService.get(request.param("param")).map(App::toByteBuf)
                                            .log("http-server")))

                                .get("/",(request,response) ->
                                                response.sendFile(indexHTML))

                                .get("/error",(request,response) ->
                                    response.status(404).addHeader("Message", "Goofed")
                                            .sendFile(errorHTML))

                )
                .bindNow()
                .onDispose()
                .block(); // Starts the server in a blocking fashion, and waits for it to finish its initialization


    }

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static ByteBuf toByteBuf(Object o){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }
}
