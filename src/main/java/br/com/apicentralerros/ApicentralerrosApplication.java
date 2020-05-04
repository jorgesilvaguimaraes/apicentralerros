package br.com.apicentralerros;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class ApicentralerrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicentralerrosApplication.class, args);
	}


	@RestController
	@RequestMapping(path = "/")
	class Docs {

		@GetMapping(value = "/")
		public RedirectView swagger() {
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl("https://api-centralerros.herokuapp.com/swagger-ui.html");
			return redirectView;
		}

	}

}
