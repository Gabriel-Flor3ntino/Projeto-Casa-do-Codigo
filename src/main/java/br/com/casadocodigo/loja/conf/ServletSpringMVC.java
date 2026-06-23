package br.com.casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { // Será retornada uma ou mais classe responsáveis por indicar quais outras
		// TODO Auto-generated method stub           // classes devem ser lidas durante o carregamento da aplicação web
		return new Class[] {AppWebConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() { // Diz qual é o padrão de endereço que vai ser delegado para o Servelet Spring
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	
}
