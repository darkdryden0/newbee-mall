package ltd.newbee.mall.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ltd.newbee.mall.interceptor.CustomAuthFilter;

import javax.annotation.Resource;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final String[] exceptWebPattern = {};
    private final String[] exceptAuthPattern = {};

    @Resource
    public CustomAuthFilter customAuthFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers(exceptWebPattern)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(exceptAuthPattern).permitAll() // 인증 예외 설정
                .anyRequest().hasAuthority("ADMIN") // 그외 호출 인증
                .and()
                .formLogin().disable();
        http.addFilterBefore(customAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
