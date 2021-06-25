package com.mca.client.manager.config;

import com.mca.client.manager.handler.MyAccessDeniedHandler;
import com.mca.client.manager.handler.MyAuthenticationEnterPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author an Stark
 * @ClassName ResourceConfig
 * @Description TODO
 * @date 2021/6/24 下午1:07
 * @Version 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    //资源id
    public static final String RESOURCE_ID = "client_manager";

    //本地token校验
    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //资源id
                .resourceId(RESOURCE_ID)
                //令牌服务
                //本地校验token
                .tokenStore(tokenStore)
                //远程校验token
//                .tokenServices(tokenServer)
                .stateless(true)
        ;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //swagger 放行
                .antMatchers("/",
                        "/public/**",
                        "/oauth/public/**",
                        "/webjars/**",
                        "webjars/springfox-swagger-ui/**",
                        "webjars/springfox-swagger-ui",
                        "/configuration/**",
                        "/swagger-ui.html",
                        "/static/**",
                        "/v2/api-docs**",
                        "/swagger-resources/**",
                        "/druid/**",
                        "/oauth/**",
                        "/actuator/**",
                        "/csrf", "/login").permitAll()

                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .anyRequest()
                .permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //自定义异常
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthenticationEnterPointHandler())


        ;

    }

}
