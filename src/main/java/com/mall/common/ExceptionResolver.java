package com.mall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sjyjs on 2018/12/19.
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("{} Exception", httpServletRequest.getRequestURI(), e);
        //当使用jackson2.x的时候使用MappingJackson2JsonView，该项目中使用的是1.9。
        ModelAndView modelAndView = new ModelAndView(new MappingJacksonJsonView());
        //与ServerResponse统一返回类格式相同
        modelAndView.addObject("status", ResponseCode.ERROR.getCode());
        modelAndView.addObject("msg","接口异常，详情请查看服务端日志的异常信息");
        modelAndView.addObject("data",e.toString());
        return modelAndView;
    }
}
