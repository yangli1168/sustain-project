package net.xinqushi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.xinqushi.config.Configuration;

@RestController
@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan
@EnableFeignClients
@EnableCircuitBreaker
@ImportResource("/applicationContext.xml")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class Application {
	
	@Autowired
	private Configuration conf; 
	
	@RequestMapping(value = "/test")
	public String hello(){
		return "welcome to test springboot !";
	}
	
	@RequestMapping(value = "{mode:share|charter}/test")
	public String hello2(@PathVariable("mode")String mode){
		if (mode.equals("share")) {
			return "share:welcome to test springboot !";
		} else if (mode.equals("charter")) {
			return "charter:welcome to test springboot !";
		}
		return "welcome to check request path !";
	}
	
	@RequestMapping(value = "/test/config")
	public String getFig(){
		StringBuilder sb = new StringBuilder();
		sb.append(conf.getRedisHost())
			.append(" -> ")
			.append(conf.getRedisPwd())
			.append(" -> ")
			.append(conf.getSpecialPhone());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		/** 热部署配置3开始:新增监控目录*/
//		System.setProperty("spring.devtools.restart.additional-paths", "/target");
		/** 热部署配置3结束*/
		SpringApplication.run(Application.class, args);
	}
}
