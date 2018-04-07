package financeiro.projeto.web;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(AppConfig.class);
		dispatcherContext.setServletContext(servletContext);
		servletContext.addListener(new ContextLoaderListener(dispatcherContext));
		javax.servlet.ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.addMapping("/services/*");
		dispatcher.setMultipartConfig(new MultipartConfigElement("/tmp",1024 * 1024 * 5, 1024 * 1024 * 5 * 5, 1024 * 1024));
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{AppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/financeiro"};
	}
}

