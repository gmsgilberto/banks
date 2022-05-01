package br.com.gms.banks.gmsbanks.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * @author gilberto
 */
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class DocumentedAPI {
	
	@Value("${banks.application.title}")
	private String title;
	
	@Value("${banks.application.version}")
	private String version;
	
	@Value("${banks.application.source-control-uri}")
	private String sourceControl;
	

	@Bean
	public OpenAPI openApi() {
		
		return new OpenAPI()
						.info(apiInfo());
		
	}

	private Info apiInfo() {
		
		return new Info()
						.version(version)
						.title(title.toUpperCase())
						.contact(new Contact()
										.email("gms.gilberto@gmail.com")
										.name("Gilberto Souza"));
		
	}
	
}
