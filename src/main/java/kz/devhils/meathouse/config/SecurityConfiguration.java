//package kz.devhils.meathouse.config;
//
//import kz.devhils.meathouse.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .authorizeRequests()
//                    .antMatchers("/css/**", "/js/**").permitAll()
//                    .antMatchers("/admin**").hasRole("ADMIN")
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginProcessingUrl("/signin").permitAll()
//                    .loginPage("/login").permitAll()
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .defaultSuccessUrl("/cms")
//                    .failureUrl("/login?error")
//                    .and()
//                .logout().permitAll()
//                    .logoutSuccessUrl("/login")
//                    .logoutUrl("/exit").permitAll()
//                    .and()
//                .csrf().disable();
//
//
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userService);
//    }
//}
