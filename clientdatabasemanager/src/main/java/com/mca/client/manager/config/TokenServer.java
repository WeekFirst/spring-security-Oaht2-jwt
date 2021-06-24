package com.mca.client.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author an Stark
 * @ClassName TokenServer
 * @Description 令牌服务配置
 * @date 2021/6/24 下午1:58
 * @Version 1.0
 */
@Configuration
public class TokenServer {

    private String SIGNING_KEY = "server121";


    @Bean
    public TokenStore tokenStore() {
        //使用jwt存储方案
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * jwt 配置
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }
}
