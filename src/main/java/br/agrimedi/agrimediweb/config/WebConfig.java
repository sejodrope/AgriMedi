package br.agrimedi.agrimediweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.agrimedi.agrimediweb.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            .addPathPatterns("/**")
            .excludePathPatterns(
                "/login", 
                "/css/**", 
                "/js/**", 
                "/images/**",
                "/webjars/**",
                "/error"
            );
    }
}
