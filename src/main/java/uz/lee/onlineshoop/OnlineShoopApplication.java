package uz.lee.onlineshoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlineShoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineShoopApplication.class, args);
    }

}