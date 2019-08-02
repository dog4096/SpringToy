package com.kaizero.spring.controller;

import com.kaizero.spring.entity.Response.IPCheckResponse;
import com.kaizero.spring.entity.Response.ResponseData;
import lombok.Getter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class FirstController {

    @RequestMapping(value = "/room", method = RequestMethod.PUT)
    public ResponseData addRoom() {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = servletRequest.getHeader("X-FORWARDED-FOR");

        if (ip == null) {
            ip = servletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = servletRequest.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = servletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = servletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = servletRequest.getRemoteAddr();
        }

        int port = servletRequest.getRemotePort();

        IPCheckResponse response = new IPCheckResponse();
        response.setIp(ip);
        response.setPort(port);
        return response;
    }
}
