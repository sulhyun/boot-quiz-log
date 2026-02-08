package kr.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setCacheManager(null); // 캐시 매니저 설정을 null로 지정
        
        // 레이아웃 다이얼렉트를 추가해야 layout:decorate 등이 작동합니다.
        templateEngine.addDialect(new LayoutDialect());
        
        return templateEngine;
    }
}
