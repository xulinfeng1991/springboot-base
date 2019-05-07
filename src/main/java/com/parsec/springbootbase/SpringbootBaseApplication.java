package com.parsec.springbootbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.parsec.*"})
@org.mybatis.spring.annotation.MapperScan("com.parsec.*")
@tk.mybatis.spring.annotation.MapperScan("com.parsec.*")
public class SpringbootBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBaseApplication.class, args);
        System.out.println("hello springboot");
    }

}

