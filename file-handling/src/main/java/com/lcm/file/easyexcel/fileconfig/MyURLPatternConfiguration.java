package com.lcm.file.easyexcel.fileconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 主动设置URL匹配路径
 *
 * @author JustryDeng
 * @date 2018年8月4日 上午1:36:38
 */
@Configuration
public class MyURLPatternConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fileData/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
