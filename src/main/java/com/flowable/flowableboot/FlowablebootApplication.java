package com.flowable.flowableboot;

import com.flowable.flowableboot.service.FlowableService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(proxyBeanMethods = false)
public class FlowablebootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowablebootApplication.class, args);
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Workflow Engine API")
						.description("Example for workflow engine api interactions")
						.version("v0.0.1"));
	}

	@Bean
	public CommandLineRunner init(final FlowableService flowableService) {
		return null;
	}
}

