package com.tech.society.residents.util;

import com.tech.society.residents.dto.RequestContext;
import jakarta.servlet.http.HttpServletRequest;

public class ApplicationUtils {

    public static RequestContext getRequestContext(HttpServletRequest request) {
        RequestContext req = new RequestContext(request.getHeader("societyIdentifier"),
                request.getRemoteAddr(),
                request.getHeader("Request-Time"),
                request.getHeader("username"),
                request.getHeader("userId"),
                request.getHeader("userType"));
        return req;
    }
}
