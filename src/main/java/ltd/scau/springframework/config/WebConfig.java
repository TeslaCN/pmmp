package ltd.scau.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Weijie Wu
 */
@EnableWebMvc
@ComponentScan("ltd.scau.*")
@Configuration
@ImportResource("classpath:spring/*Context.xml")
public class WebConfig {

    @Bean
    public HttpMessageConverter jsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter;
    }

    @Bean
    public MultipartResolver initMultipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
