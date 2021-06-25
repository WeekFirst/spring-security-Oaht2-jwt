package com.mca.client.manager.handler;

import com.mca.client.manager.util.RCode;
import com.mca.client.manager.util.ResponseUtil;
import com.mca.client.manager.util.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author an Stark
 * @ClassName MyAccessDeniedHandler
 * @Description 权限不足处理器
 * @date 2021/6/25 上午11:00
 * @Version 1.0
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.responseOut(httpServletResponse, Result.fail(RCode.FORBIDDEN));
    }
}
