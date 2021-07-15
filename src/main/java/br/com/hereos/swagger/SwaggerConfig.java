package br.com.hereos.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.hereos"))
				.paths(PathSelectors.ant("/**"))
				.build();
	}

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfoBuilder().title("API do gerenciador de Hereos")
				.description("Essa é uma API desenvolvida aplicando os conhecimentos adquiridos no"
						+ "bootcamp do Santander FullStack Developer")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.termsOfServiceUrl("/service.html").version("1.0.0").contact(new Contact("Wandes",
						"https://www.linkedin.com/in/wandes-gomes-aa825a171/", "wandessilva@outlook.com"))
				.build();

		return apiInfo;
	}
}