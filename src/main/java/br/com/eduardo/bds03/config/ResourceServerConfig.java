package br.com.eduardo.bds03.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String[] PUBLIC = {"/ouath/token", "/h2-console/**"};
    private static final String[] OPERATOR_GET= {"/departments/**", "/employees/**"};
    //private static final String[] ADMIN = {"/users/**"};

    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private Environment environment;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //H2 - liberacao dos frames do banco
        if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET,OPERATOR_GET).hasAnyRole("OPERATOR", "ADMIN")
                .anyRequest().hasAnyRole("ADMIN");

    }
}
