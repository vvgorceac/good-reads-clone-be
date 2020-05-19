package md.pentlaog.goodreadsclone.configuration;

import md.pentlaog.goodreadsclone.security.jwt.JwtConfigurer;
import md.pentlaog.goodreadsclone.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final JwtTokenProvider jwtTokenProvider;

  private static final String ADMIN_ENDPOINTS = "/api/v1/admin/**";
  private static final String[] USER_ENDPOINTS = {"/api/v1/books/**", "/api/v1/authors/**"};
  private static final String LOGIN_ENDPOINTS = "/api/v1/auth/login";

  @Autowired
  public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .httpBasic()
        .disable()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(LOGIN_ENDPOINTS)
        .permitAll()
        .antMatchers(ADMIN_ENDPOINTS)
        .hasRole("ADMIN")
        .antMatchers(USER_ENDPOINTS)
        .hasAnyRole("ADMIN", "USER")
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
    configuration.setAllowedMethods(Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT"));
    configuration.setAllowedHeaders(
        Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
