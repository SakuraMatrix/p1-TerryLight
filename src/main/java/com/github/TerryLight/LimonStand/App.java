package com.github.TerryLight.LimonStand;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import javax.xml.xpath.XPath;

public class App {
    public static void main(String[] args) {

        HttpServer.create()   // Prepares an HTTP server ready for configuration
                .port(8080)    // Configures the port number as zero, this will let the system pick up
                // an ephemeral port when binding the server
                .route(routes ->
                        // The server will respond only on POST requests
                        // where the path starts with /test and then there is path parameter
                        routes.get("/items", (request, response) ->
                                response.sendString(Mono.just("Hello World!")
                                        .log("http-server")))
                            .get("/items/{param}",
                                (request, response) ->
                                    response.sendString(Mono.just(request.param("param"))))
                                            //.log(http-server)))

                )
                .bindNow().onDispose().block(); // Starts the server in a blocking fashion, and waits for it to finish its initialization


    }
}
