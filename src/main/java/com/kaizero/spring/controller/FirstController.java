package com.kaizero.spring.controller;

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
    public void addRoom(Model model) {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = servletRequest.getHeader("X-FORWARDED-FOR");
        int port = servletRequest.getRemotePort();


    }
}
