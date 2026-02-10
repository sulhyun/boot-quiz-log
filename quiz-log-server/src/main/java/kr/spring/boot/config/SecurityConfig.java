package kr.spring.boot.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import kr.spring.boot.handler.LoginFailHandler;
import kr.spring.boot.model.util.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
	        .cors(cors -> cors.configurationSource(request -> {
	            CorsConfiguration config = new CorsConfiguration();
	            config.setAllowedOrigins(List.of("*")); 										// 모든 도메인 허용 (테스트용)
	            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	            config.setAllowedHeaders(List.of("*"));
	            config.setAllowCredentials(true); 												// 쿠키나 인증 헤더 허용 시 true
	            return config;
	        }))
	        .csrf(csrf ->csrf.disable())
	            .authorizeHttpRequests((requests) -> requests
	                .requestMatchers("/post/insert/*").hasAuthority(UserRole.USER.name())
	                .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
	                .anyRequest().permitAll()  				// 그 외 요청은 인증 필요
	            )
	            .formLogin((form) -> form
	                .loginPage("/member/signin")  			// 커스텀 로그인 페이지 설정
	                .permitAll()          		 			// 로그인 페이지는 접근 허용
	                .loginProcessingUrl("/member/signin")	// 로그인 요청을 처리할 URL을 설정
	                .failureHandler(new LoginFailHandler())	
	                .defaultSuccessUrl("/")
	            )
	            .logout((logout) -> logout
	        		.logoutUrl("/member/logout")
	        		.logoutSuccessUrl("/")
	        		.clearAuthentication(true)
	        		.invalidateHttpSession(true)
	        		.permitAll());  						// 로그아웃도 모두 접근 가능
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
