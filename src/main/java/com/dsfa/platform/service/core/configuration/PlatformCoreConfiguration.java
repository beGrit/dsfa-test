package com.dsfa.platform.service.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName PlatformCoreConfiguration
 * @Author 文进
 * @Date 2020/10/30 上午10:28
 * @Version 1.0.0
 * @Description TODO
 **/
@Configuration
public class PlatformCoreConfiguration implements WebMvcConfigurer {

    /**
     * 允许跨域访问
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                .allowedHeaders("*")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*");
    }
}
