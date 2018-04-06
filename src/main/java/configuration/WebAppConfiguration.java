package configuration;

import com.lyncode.jtwig.mvc.JtwigViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class WebAppConfiguration  {
    @Bean
    public ViewResolver viewResolver() {
        JtwigViewResolver jtwigViewResolver = new JtwigViewResolver();
        jtwigViewResolver.setPrefix("classpath:views/");
        jtwigViewResolver.setSuffix(".twig");
        return jtwigViewResolver;
    }


}