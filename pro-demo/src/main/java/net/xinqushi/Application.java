package net.xinqushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ImportResource("applicationContext.xml")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application {
	
	@RequestMapping(value = "/test")
	public String hello(){
		return "welcome to test springboot !";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
