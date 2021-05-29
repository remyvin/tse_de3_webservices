package tse.de3.time_manager_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TimeManagerRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeManagerRestApiApplication.class, args);
	}

}
