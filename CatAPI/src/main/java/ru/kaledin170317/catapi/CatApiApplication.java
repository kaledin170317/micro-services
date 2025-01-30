package ru.kaledin170317.catapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("ru.kaledin170317.DTO")
@EnableJpaRepositories("ru.kaledin170317.CatAPI.Repository")
@ComponentScan(basePackages = "ru.kaledin170317")
public class CatApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatApiApplication.class, args);
    }
}
