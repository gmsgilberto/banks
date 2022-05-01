package br.com.gms.banks.gmsbanks.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author gilberto
 */
@Configuration
@EnableSwagger2
@NoArgsConstructor
@AllArgsConstructor
public class DocumentedAPI {
	
	@Value("${banks.application.title}")
	private String title;
	
	@Value("${banks.application.version}")
	private String version;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
						.select()
							.apis(RequestHandlerSelectors.basePackage("br.com.gms.banks.gmsbanks"))
							.paths(PathSelectors.any())
						.build()
						.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
					.version(version)
					.title(title.toUpperCase())
					.contact(new Contact("Gilberto Souza", "https://github.com/gmsgilberto", "gmsgilberto"))
					.build();
	}
	
}
