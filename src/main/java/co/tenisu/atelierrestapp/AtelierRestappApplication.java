package co.tenisu.atelierrestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.tenisu.atelierrestapp"})
public class AtelierRestappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtelierRestappApplication.class, args);
	}

}
