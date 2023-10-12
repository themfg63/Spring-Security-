package yte.intern.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    /*
    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,CustomUserDetailsService userDetailsService) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      return http.authorizeHttpRequests(authorize ->
              authorize
                      .requestMatchers(new AntPathRequestMatcher("/login"))
                      .permitAll()
                      .anyRequest().authenticated())
              .formLogin(formLogin -> formLogin.disable())
              .logout(logout-> logout.disable())
              .csrf(csrf -> csrf.disable())  //farklı sitelerden gelen isteklerin bizim sitemiz gibi görünmesini engellemek amacıyla kullanılır.
              .build();
        /*
        return http.authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().build();
    */
    }

    @Bean
    public AuthenticationManager authenticationManager(CustomAuthenticationProvider customAuthenticationProvider){
        return new ProviderManager(customAuthenticationProvider);
    }
}
