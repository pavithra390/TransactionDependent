package com.example.transactiondependent.Controller;
import com.example.transactiondependent.Entity.RecidentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
public class RecidentController {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;


    @PostMapping("/posting/onerecord")
    //http://localhost:1122/posting/onerecord
    public Mono<String> addingOneRecord(@RequestBody RecidentEntity recident) {
        return webClient.post()
                .uri("/posting/recident/record")
                .bodyValue(recident)
                .retrieve()   //execute the request and get the response object back."
                .bodyToMono(String.class)
                .map(response -> "Record Saved Successfully");
    }

    @PostMapping("/posting/allrecords")
    //http://localhost:1122/posting/allrecords
    public Mono<String> postAllRecords(@RequestBody Iterable<RecidentEntity> recidents) {
        return webClient.post()
                .uri("/saving/allrecords")
                .bodyValue(recidents)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> "All records saved successfully");
    }

    @GetMapping("/getting/onerecord/{resident_id}")
    //http://localhost:1122/getting/onerecord/7
    public Mono<RecidentEntity> gettingOneRecord(@PathVariable("resident_id") Integer id) {
        return webClient.get()
                .uri("/getting/recident/info/{id}", id)
                .retrieve()
                .bodyToMono(RecidentEntity.class);
        /*no .map() is needed because the data received from the RecidentDetailsApplication
        is exactly
       what you want to send back to the client of the RecidentDependentApplication.
*/
    }

    @GetMapping("/getting/allrecords")
    //http://localhost:1122/getting/allrecords
    public Flux<RecidentEntity> gettingAllRecord() {
        return webClient.get()
                .uri("/getting/allrecident/info")
                .retrieve()
                .bodyToFlux(RecidentEntity.class);
    }

    @DeleteMapping("/deleting/onerecord/{recident_id}")
    //http://localhost:1122/deleting/onerecord/7
    public Mono<String> deletingOneRecord(@PathVariable("recident_id") Integer id) {
        return webClient.delete()
                .uri("/deleting/recident/info/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .then(Mono.just("Record Deleted Successfully"));
    }

    @DeleteMapping("/delete/allrecords")
    //http://localhost:1122/delete/allrecords
    public Mono<String> deletingAllRecords() {
        return webClient.delete()
                .uri("/deleting/allrecident/info")
                .retrieve()
                .bodyToMono(Void.class)
                .then(Mono.just("All records deleted Successfully"));
    }
}








//    @PostMapping("/posting/onerecord")
//    //http://localhost:1888/posting/onerecord
//    public String addingOneRecord(@RequestBody RecidentEntity recident) {
//        String url="http://localhost:1991/posting/recident/record";
//        restTemplate.postForEntity(url,recident,String.class);
//        return "Record Saved Successfully";
//
//    }
//    @PostMapping("/posting/allrecords")
//    //http://localhost:1122/posting/allrecords
//    public String postAllRecords(@RequestBody Iterable<RecidentEntity> recidents){
//        String url="http://localhost:1991/saving/allrecords";
//        restTemplate.postForEntity(url,recidents,String.class);
//        return "All records saved successfully";
//    }
//    @GetMapping("/getting/onerecord/{resident_id}")
//    //http://localhost:1122/getting/onerecord/7
//    public RecidentEntity gettingOneRecord(@PathVariable("resident_id")Integer id){
//        String url="http://localhost:1991/getting/recident/info/"+id;
//        ResponseEntity<RecidentEntity> recident=restTemplate.getForEntity(url,RecidentEntity.class);
//        return recident.getBody();
//
//    }
//    @GetMapping("/getting/allrecords")
//    //http://localhost:1122/getting/allrecords
//    public Iterable<RecidentEntity> gettingAllRecord(){
//        String url="http://localhost:1991/getting/allrecident/info";
//        ResponseEntity<RecidentEntity[]> recident=restTemplate.getForEntity(url, RecidentEntity[].class);
//        return List.of(recident.getBody());
//
//    }
//    @DeleteMapping("/deleting/onerecord/{recident_id}")
//    //http://localhost:1122/deleting/onerecord
//    public String deletingOneRecord(@PathVariable("recident_id")Integer id){
//        String url="http://localhost:1991/deleting/recident/info/"+id;
//        restTemplate.delete(url);
//        return "Record Deleted Successfully";
//    }
//@DeleteMapping("/delete/allrecords")
//    //http://localhost:1122/delete/allrecords
//    public String deletingAllRecords(){
//        //String url="http://localhost:1991/deleting/allrecident/info";
//        restTemplate.delete("http://localhost:1991/deleting/allrecident/info");
//        return "All records deleted Successfully";
//}




