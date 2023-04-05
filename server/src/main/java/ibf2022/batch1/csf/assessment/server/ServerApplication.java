package ibf2022.batch1.csf.assessment.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ibf2022.batch1.csf.assessment.server.config.EnableCors;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	// @Bean
	// public WebMvcConfigurer configurer(){
	// 	return new EnableCors("/api/*", "*");
	// }

}
