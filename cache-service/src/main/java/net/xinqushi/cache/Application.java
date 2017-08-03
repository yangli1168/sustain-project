package net.xinqushi.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan
@ImportResource("/applicationContext.xml")
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}
}
