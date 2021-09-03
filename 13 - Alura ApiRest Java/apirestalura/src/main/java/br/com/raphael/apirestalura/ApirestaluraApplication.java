package br.com.raphael.apirestalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport //serve para que o Spring captura automaticamente o Pageable nos argumentos do endpoint
@EnableCaching
public class ApirestaluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestaluraApplication.class, args);
	}

}
