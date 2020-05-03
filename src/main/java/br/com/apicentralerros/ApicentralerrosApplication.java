package br.com.apicentralerros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApicentralerrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicentralerrosApplication.class, args);
	}

}
