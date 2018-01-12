package org.spring.springboot.commons.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.commons.base.Result;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ThinkPad on 2018/1/11.
 */
public class ApiInterceptor implements HandlerInterceptor{

    private static Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        String appid = request.getParameter("appid");
        String key =  request.getParameter("key");
        String server_appid = "";//conf.get("server.api.appid", "");
        String server_key = ""; //conf.get("server.api.key", "");
        //签名机制的DEMO，并未实现功能，自己要实现啊！！
        if (!appid.equals(server_appid)) {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(Result.error("token invalid").toString());
            response.getWriter().flush();
            response.getWriter().close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
