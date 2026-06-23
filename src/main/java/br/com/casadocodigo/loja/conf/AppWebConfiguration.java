package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;

/*
 * Essa classe expõe para a Servlet do Spring MVc
 * quais são as classes que devem ser lidas e carregadas
 */
 


@EnableWebMvc
@ComponentScan(basePackageClasses= {HomeController.class}) 
public class AppWebConfiguration {
	
	/* 	guarda as guarda as configurações da pasta base e do sufixo que devem
		ser adicionados para qualquer caminho que seja retornado por métodos dos
		controllers
	*/
	@Bean // indica para o Spring que o retorno desse método deve ser registrado como um objeto gerenciado pelo container
	public InternalResourceViewResolver internalResourceViewResolver() { 
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
}
