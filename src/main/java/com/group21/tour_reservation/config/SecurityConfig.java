package com.group21.tour_reservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import com.group21.tour_reservation.repository.AccountRepository;
import com.group21.tour_reservation.service.AccountService;
import com.group21.tour_reservation.service.CustomUserDetailsService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity // Nếu bạn cần hỗ trợ @PreAuthorize, @Secured
public class SecurityConfig {
        @Autowired
        private AccountRepository accountRepository;

        @Autowired
        private CustomerOAuth2UserService customerOAuth2UserService;

        @Bean
        public AuthenticationSuccessHandler customSuccessHandler() {
                return new CustomSuccessHandler();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService(AccountService userService) {
                return new CustomUserDetailsService(userService);
        }

        @Bean
        public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
                return new HttpSessionSecurityContextRepository();
        }

        @Bean
        public SpringSessionRememberMeServices rememberMeServices() {
                SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
                // optionally customize
                rememberMeServices.setAlwaysRemember(true);
                return rememberMeServices;
        }

        @Bean
        public DaoAuthenticationProvider authProvider(
                        PasswordEncoder passwordEncoder,
                        UserDetailsService userDetailsService) {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userDetailsService);
                authProvider.setPasswordEncoder(passwordEncoder);
                authProvider.setHideUserNotFoundExceptions(false);
                return authProvider;
        }

        @Bean
        SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                                                DispatcherType.INCLUDE)
                                                .permitAll()

                                                .requestMatchers("/", "/login", "/register", "/static/**",
                                                                "/templates/**", "/client/**", "/css/**",
                                                                "/js/**", "/images/**", "/api/check-username","/api/client/filter-tour","/admin/images/**"
                                                        ,"/reserve/**","/tour/**","/client/js/**", "/api/client/**", "/admin/js/**", "/admin/css/**",
                                                        "/client/css/**", "/client/fonts/**",
                                                        "/admin/fonts/**", "/api/payment/create_payment", "/payment/**", "/api/payment/**"
                                                                ,"/confirm_info/**"
                                                )
                                                .permitAll()

                                                .requestMatchers("/admin/**").hasRole("ADMIN")

                                                .anyRequest().authenticated())

                                .oauth2Login(oauth2Login -> oauth2Login
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/", true)
                                                .userInfoEndpoint(userInfo -> userInfo
                                                                .userService(customerOAuth2UserService)))
                                
                                .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
                                .sessionManagement((sessionManagement) -> sessionManagement
                                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                                                .invalidSessionUrl("/logout?expired")
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(false))
                                .logout(logout -> logout
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true))
                                .formLogin(formLogin -> formLogin
                                                .loginPage("/login")
                                                .failureUrl("/login?error")
                                                .successHandler(customSuccessHandler())
                                                .permitAll())
                                .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"))
                                .csrf(csrf -> csrf.disable());
                http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/check-username","/api/client/filter-tour","/api/client/reserve", "/api/payment/create_payment"));

                return http.build();
        }

}
