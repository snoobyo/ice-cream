package com.ic.business.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

public class HttpUtil {

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
