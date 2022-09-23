package com.challenge.druid.configuration;

import com.challenge.druid.jwt.JwtFilterRequest;
import com.challenge.druid.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//con perPostEnabled se usa para indicar a que métodos puede acceder solo el admin
// Los métodos que no lleven anotación pueden acceder el admin como un generic user
// @preauthorized solo puede acceder el admin
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private JwtFilterRequest jwtTokenFilterRequest;

  /**
   * Encripta el pasword
   * @return pasword ecriptado
   */
  @Bean
  public PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //Desactivamos cookies ya que enviamos un token
    // cada vez que hacemos una petición
    http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/api/auth/**", "/v3/api-docs", "/swagger-resources/**",
                    "/swagger-ui/**","/v2/api-docs/**").permitAll()
            .anyRequest().fullyAuthenticated()
            .and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    http.addFilterBefore(jwtTokenFilterRequest, UsernamePasswordAuthenticationFilter.class);
  }
}
