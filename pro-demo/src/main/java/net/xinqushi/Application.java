package net.xinqushi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan
@EnableFeignClients
@EnableCircuitBreaker
@ImportResource("/applicationContext.xml")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application {
	
	@RequestMapping(value = "/test")
	public String hello(){
		return "welcome to test springboot !";
	}
	
	public static void main(String[] args) {
		/** 热部署配置3开始:新增监控目录*/
//		System.setProperty("spring.devtools.restart.additional-paths", "/target");
		/** 热部署配置3结束*/
		SpringApplication.run(Application.class, args);
	}
}
