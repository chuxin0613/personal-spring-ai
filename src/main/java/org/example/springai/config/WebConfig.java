package org.example.springai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置
 * WebMvcConfigurer 实现自定义配置,避免使用 SimpleAsyncTaskExecutor,解决高负载下表现不佳的问题
 *
 * @author liu
 */
@Configuration
@EnableAsync
public class WebConfig implements WebMvcConfigurer {

    private final ThreadPoolTaskExecutor taskExecutor;

    public WebConfig(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(taskExecutor);
        configurer.setDefaultTimeout(30000L);
    }
}
