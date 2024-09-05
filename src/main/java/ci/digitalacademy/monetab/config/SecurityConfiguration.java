package ci.digitalacademy.monetab.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/css/**").permitAll() // Allow access to static resources
                        .requestMatchers("/icon/**").permitAll() // Allow access to static resources
                        .requestMatchers("/img/**").permitAll() // Allow access to static resources
                        .requestMatchers("/public/**").permitAll() // Allow access to public resources
                        .requestMatchers("/app-setting").permitAll() // Allow access to specific endpoints
                        .requestMatchers("/school-setting").permitAll() // Allow access to specific endpoints
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/auth").permitAll()
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }
}
