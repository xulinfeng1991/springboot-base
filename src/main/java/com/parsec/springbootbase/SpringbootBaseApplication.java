package com.parsec.springbootbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ServletComponentScan //使用注解注册Servlet
@MapperScan(basePackages = "com.parsec.springbootbase.mapper") //通过使用@MapperScan可以指定要扫描的Mapper类的包的路径
public class SpringbootBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBaseApplication.class, args);
		System.out.println("hello springboot");
	}

}

