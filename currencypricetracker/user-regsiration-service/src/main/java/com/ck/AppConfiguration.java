package com.ck;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
public class AppConfiguration {
	
//	@Autowired
//	TemplateEngine templateEngine;
	
	
//	@Bean
//	public MessageSource messageSource() {
//	
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:templates/register-user-response-success");
//		messageSource.setCacheSeconds(0);
//		return messageSource;
////		messageSource.setDefaultEncoding(Encodin);
//	}
//	
//	 @Bean
//	    public SpringTemplateEngine templateEngine() {
//	        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		 System.out.println(templateEngine);
//	        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
////	        templateEngine.setMessageSource(messageSource());
////	        String message = messageSource().getMessage("welcomemessage", null, Locale.US);
//	        System.out.println("------------------");
////	        System.out.println(message);
//	        System.out.println("------------------");
//	        return templateEngine;
//	    }
	 
//	    @Bean
//	    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
//	        SpringResourceTemplateResolver templateResolver 
//	          = new SpringResourceTemplateResolver();
//	        
////	        Below settings should be moved to application.yml
//	        templateResolver.setPrefix("classpath:/templates/");
//	        templateResolver.setSuffix(".json");
//	        templateResolver.setCacheable(false);
////	        templateResolver.setTemplateMode("HTML5");
//	        return templateResolver;
//	    }
	
//	    @Bean
//	    @Description("Thymeleaf View Resolver")
//	    public ThymeleafViewResolver viewResolver() {
//	        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
////	        viewResolver.setTemplateEngine(templateEngine());
//	        viewResolver.setTemplateEngine((ISpringTemplateEngine) templateEngine);
//	        viewResolver.setOrder(1);
//	        return viewResolver;
//	    }

}
