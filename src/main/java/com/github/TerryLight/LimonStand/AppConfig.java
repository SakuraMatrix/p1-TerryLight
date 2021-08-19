package com.github.TerryLight.LimonStand;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.TerryLight.LimonStand.repository.OrderRepository;
import com.github.TerryLight.LimonStand.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
@ComponentScan


public class AppConfig {

    @Autowired
    OrderService orderService;

    @Bean
    public CqlSession session(){
        return CqlSession.builder().build();
    }

    @Bean
    public HttpServer httpServer(ApplicationContext context) throws URISyntaxException{
       // Path indexHTML= Paths.get(App.class.getResource("/index.html").toURI());
        //Path errorHTML= Paths.get(App.class.getResource("/error.html").toURI());
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        return HttpServer.create().port(8080).handle(adapter);
    }
}
