package com.endsound.trpg.security;

import com.endsound.trpg.security.component.JwtComponent;
import com.endsound.trpg.security.dao.RoleDao;
import com.endsound.trpg.security.dao.UserDao;
import com.endsound.trpg.security.entryPoint.DefaultEntryPoint;
import com.endsound.trpg.security.filter.AuthenticationFilter;
import com.endsound.trpg.security.filter.ExceptionFilter;
import com.endsound.trpg.security.filter.TokenFilter;
import com.endsound.trpg.security.handler.DefaultAuthenticationFailureHandler;
import com.endsound.trpg.security.handler.DefaultAuthenticationSuccessHandler;
import com.endsound.trpg.security.handler.DefualtAccessDeniedHandler;
import com.endsound.trpg.security.provider.DefaultAuthenticationProvider;
import com.endsound.trpg.security.service.DefaultDbUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.GenericFilterBean;

import java.util.Arrays;
import java.util.List;

@Configuration
@ConditionalOnWebApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfiguration {
    @Configuration
    @Order(0)
    public static class OauthConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationEntryPoint authenticationEntryPoint;
        @Autowired
        private CorsConfigurationSource corsConfigurationSource;
        @Autowired
        private GenericFilterBean exceptionFilter;
        @Autowired
        private GenericFilterBean authenticationFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.mvcMatcher("/oauth/**")
                    .authorizeRequests().anyRequest().permitAll().and()
                    .httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .cors().configurationSource(corsConfigurationSource).and()
                    .csrf().disable()
                    .addFilterBefore(exceptionFilter, AbstractPreAuthenticatedProcessingFilter.class)
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }

    @Configuration
    @Order(1)
    public static class BackendConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private AuthenticationEntryPoint authenticationEntryPoint;
        @Autowired
        private CorsConfigurationSource corsConfigurationSource;
        @Autowired
        private AccessDeniedHandler accessDeniedHandler;
        @Autowired
        private GenericFilterBean exceptionFilter;
        @Autowired
        private GenericFilterBean tokenFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.mvcMatcher("/backend/api/**")
                    .httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().cors().configurationSource(corsConfigurationSource).and()
                    .csrf().disable()
                    .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                    .addFilterBefore(exceptionFilter, AbstractPreAuthenticatedProcessingFilter.class)
                    .addFilterAfter(tokenFilter, AbstractPreAuthenticatedProcessingFilter.class);
        }
    }

    @Bean
    public AuthenticationEntryPoint defaultAuthenticationEntryPoint(){
        return new DefaultEntryPoint();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsConfiguration corsConfiguration){
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

    @Bean
    public CorsConfiguration corsConfiguration(SecurityProperties securityProperties){
        SecurityProperties.Cors corsProperties = securityProperties.getCors();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedOrigins(corsProperties.getAllowedOrigins());
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        return corsConfiguration;
    }

    @Bean
    public GenericFilterBean exceptionFilter(AuthenticationEntryPoint authenticationEntryPoint){
        return new ExceptionFilter(authenticationEntryPoint);
    }

    @Bean
    public GenericFilterBean authenticationFilter(
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler authenticationSuccessHandler,
            AuthenticationFailureHandler authenticationFailureHandler
    ){
        AuthenticationFilter authenticationFilter =  new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager);
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return authenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(List<AuthenticationProvider> authenticationProviders){
        return new ProviderManager(authenticationProviders);
    }

    @Bean
    public AuthenticationProvider defaultAuthenticationProvider(UserDetailsService userDetailsService){
        return new DefaultAuthenticationProvider(userDetailsService);
    }

    @Bean
    public UserDao userDao(org.jooq.Configuration configuration){
        return new UserDao(configuration);
    }

    @Bean
    public RoleDao roleDao(org.jooq.Configuration configuration){
        return new RoleDao(configuration);
    }

    @Bean
    public UserDetailsService defaultDbUserDetailsService(UserDao userDao, RoleDao roleDao){
        return new DefaultDbUserDetailsService(userDao, roleDao);
    }


    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(AuthenticationEntryPoint authenticationEntryPoint){
        return new DefaultAuthenticationFailureHandler(authenticationEntryPoint);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(JwtComponent jwtComponent){
        return new DefaultAuthenticationSuccessHandler(jwtComponent);
    }

    @Bean
    public JwtComponent jwtComponent(SecurityProperties securityProperties){
        return new JwtComponent(securityProperties.getJwt());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new DefualtAccessDeniedHandler();
    }

    @Bean
    public GenericFilterBean tokenFilter(JwtComponent jwtComponent){
        return new TokenFilter(jwtComponent);
    }
}
