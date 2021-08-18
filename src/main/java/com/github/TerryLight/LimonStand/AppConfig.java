package com.github.TerryLight.LimonStand;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.TerryLight.LimonStand.repository.ItemRepository;
import com.github.TerryLight.LimonStand.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
@ComponentScan


public class AppConfig {

    @Autowired
     ItemService itemService;

    @Bean
    public CqlSession session(){
        return CqlSession.builder().build();
    }

/*    @Bean
    public ItemRepository repository() {
        return new ItemRepository(session());

    }

    @Bean
    public ItemService service() {
        return new ItemService(repository());
    }*/

    /*@Bean
    public DisposableServer server() throws URISyntaxException {
        Path indexHTML= Paths.get(App.class.getResource("/index.html").toURI());
        Path errorHTML= Paths.get(App.class.getResource("/error.html").toURI());
        // ItemService itemService = service();
        return HttpServer.create()
                .port(8080)    // Configures the port number as zero, this will let the system pick up
                // an ephemeral port when binding the server
                .route(routes ->
                        // The server will respond only on POST requests
                        // where the path starts with /test and then there is path parameter
                        routes.get("/items", (request, response) ->
                                        response.send(itemService.getAll().map(App::toByteBuf)
                                                .log("http-server")))


                                .post("/items", (request,response)->
                                        response.send(request.receive().asString()
                                                .map(App::parseItem)
                                                .map(itemService::create)
                                                .map(App::toByteBuf)
                                                .log("http-server")))

                                .get("/items/{param}",(request, response) ->
                                        response.send(itemService.get(request.param("param")).map(App::toByteBuf)
                                                .log("http-server")))

                                .get("/",(request,response) ->
                                        response.sendFile(indexHTML))

                                .get("/error",(request,response) ->
                                        response.status(404).addHeader("Message", "Goofed")
                                                .sendFile(errorHTML))

                )
                .bindNow();

    }*/
    @Bean
    public HttpServer httpServer(ApplicationContext context) {
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        return HttpServer.create().port(8080).handle(adapter);
    }

   /* @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:index.html")Resource indexHTMLFile){
        return RouterFunctions.route(RequestPredicates.GET("/"), request ->
                ServerResponse.ok().contentType(MediaType).Text_HTML.bodyvalue(indexHTMLFile));
    }*/
}


