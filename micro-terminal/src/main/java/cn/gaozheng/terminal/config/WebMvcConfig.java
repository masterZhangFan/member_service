package cn.gaozheng.terminal.config;

import cn.gaozheng.mini.config.BeanConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
class WebMvcConfig implements WebMvcConfigurer {

    private BeanConfig beanConfig;
    private final long MAX_AGE_SECS = 3600;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler(String.format("/%s/**", beanConfig.getUploadDir()))
                .addResourceLocations(
                        String.format("file:%s/%s/", beanConfig.getUploadPath(), beanConfig.getUploadDir()));
    }
    @Override
    public void addCorsMappings( CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
}
}