package com.example.transactiondependent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

//@SpringBootApplication
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})

public class RecidentDependentApplication {

    public static void main(String[] args) {
        System.out.println("*****Application Running Successfully*****");
        SpringApplication.run(RecidentDependentApplication.class, args);
    }
//    @Bean
//   public RestTemplate restTemplate(){
//        return new RestTemplate();
//   }
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:1881").build();
    }
}
