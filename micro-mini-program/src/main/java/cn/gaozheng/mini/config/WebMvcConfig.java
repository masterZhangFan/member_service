package cn.gaozheng.mini.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
class WebMvcConfig implements WebMvcConfigurer {

    private BeanConfig beanConfig;

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
}