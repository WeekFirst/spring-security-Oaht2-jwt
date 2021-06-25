package com.mca.client.manager.handler;

import com.mca.client.manager.util.ResponseUtil;
import com.mca.client.manager.util.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author an Stark
 * @ClassName MyAuthenticationEnterPointHandler
 * @Description TODO
 * @date 2021/6/25 上午11:03
 * @Version 1.0
 */
public class MyAuthenticationEnterPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.responseOut(httpServletResponse, Result.unAuth());
    }
}
