package com.github.TerryLight.LimonStand;

import com.github.TerryLight.LimonStand.repository.ItemRepository;
import com.github.TerryLight.LimonStand.service.ItemService;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import javax.xml.xpath.XPath;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws URISyntaxException {
        Path errorHTML= Paths.get(App.class.getResource("/error.html").toURI());

        ItemRepository itemRepository = new ItemRepository();
        ItemService itemService= new ItemService(itemRepository);
        HttpServer.create()   // Prepares an HTTP server ready for configuration
                .port(8080)    // Configures the port number as zero, this will let the system pick up
                // an ephemeral port when binding the server
                .route(routes ->
                        // The server will respond only on POST requests
                        // where the path starts with /test and then there is path parameter
                        routes.get("/items", (request, response) ->
                                response.sendString(Mono.just("Hello World!")
                                        .log("http-server")))
                            .get("/items/{param}",(request, response) ->
                                    response.sendString(Mono.just(request.param("param"))
                                            .log("http-server")))
                                            //.log(http-server)))
                            .get("/test",(request,response) ->
                                    response.status(404).addHeader("Message", "Goofed")
                                            .sendFile(errorHTML))

                )
                .bindNow().onDispose().block(); // Starts the server in a blocking fashion, and waits for it to finish its initialization


    }
}
