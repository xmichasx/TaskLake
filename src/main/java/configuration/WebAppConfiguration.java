package configuration;

import com.lyncode.jtwig.mvc.JtwigViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// TODO review: niepotrzebne importy
// TODO review: integracja Jtwig dla Spring Boot: http://jtwig.org/documentation/reference/web/spring-boot
@Configuration
public class WebAppConfiguration {
    @Bean
    public ViewResolver viewResolver() {
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setPrefix("classpath:views/");
        jtwigViewResolver.setSuffix(".twig");
        return jtwigViewResolver;
        // TODO review: śmieci
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/Views/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        return resolver;
    }
}