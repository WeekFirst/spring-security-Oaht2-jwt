package com.mca.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * @Author an Stark
 * @ClassName SecurityConfig
 * @Description 授权服务器
 * @date 2021/6/24 下午12:52
 * @Version 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    //授权码模式
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    //认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("tokenServices")
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    @Qualifier("myClientDetailsService")
    private ClientDetailsService clientDetailsService;

    /**
     * 客户端详情服务
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //数据库方案
        clients.withClientDetails(clientDetailsService);

        //查询客户端信息 内存方案
//        clients.inMemory()
//                //客户端id
//                .withClient("client")
//                //客户端秘钥
//                //容器会在你传输明文秘钥时自动进行BCrypt 加密
//                .secret(passwordEncoder.encode("123456"))
//                //资源服务器id
//                .resourceIds("rs1")
//                //重定向地址
//                .redirectUris("http://www.baidu.com")
//                //授权范围
//                .scopes("all")
//                //false 跳转授权页面
//                .autoApprove(false)
//                //授权类型
//                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token");
    }


    /**
     * 令牌访问端点和令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //认证管理器
                .authenticationManager(authenticationManager)
                //授权码服务
                .authorizationCodeServices(authorizationCodeServices)
                //令牌管理服务
                .tokenServices(authorizationServerTokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
        ;
    }

    /**
     * 安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //公有秘钥端点 oauth/token_key
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                //表单认证
                .allowFormAuthenticationForClients();
    }


}
