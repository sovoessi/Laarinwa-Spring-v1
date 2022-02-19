package com.example.aktivist;

import com.example.aktivist.mugshot.Mugshot;
import com.example.aktivist.mugshot.MugshotService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.print.Book;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class AktivistApplication {

    public static void main(String[] args) {
        SpringApplication.run(AktivistApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(MugshotService mugshotService) {
//        return args -> {
//            // read json and write to db
//            ObjectMapper mapper = new ObjectMapper();
//            TypeReference<List<Mugshot>> typeReference = new TypeReference<>() {
//            };
//            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/associes.json");
//            try {
//                List<Mugshot> mugshots = mapper.readValue(inputStream, typeReference);
////                System.out.println(mugshots);
//                mugshotService.saveAll(mugshots);
//                System.out.println("Mugshots Saved!");
//            } catch (IOException e) {
//                System.out.println("Unable to save mugshots: " + e.getMessage());
//            }
//        };
//    }

}
