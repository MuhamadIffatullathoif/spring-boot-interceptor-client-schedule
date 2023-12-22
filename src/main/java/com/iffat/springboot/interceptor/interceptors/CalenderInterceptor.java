package com.iffat.springboot.interceptor.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalenderInterceptor implements HandlerInterceptor {

    @Value("${config.calender.open}")
    private Integer open;
    @Value("${config.calender.close}")
    private Integer close;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= open && hour < close) {
            StringBuilder message = new StringBuilder("The system open ");
            message.append(open);
            message.append(" hrs.");
            message.append(" close ");
            message.append(close);
            message.append(" .hrs");
            request.setAttribute("message", message);
            return true;
        }

        /* implement closed */
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json = new HashMap<>();
        StringBuilder message = new StringBuilder("The system closed now. ");
        message.append(close);
        message.append(" hrs.");
        json.put("message", message.toString());
        json.put("date", new Date().toString());
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(json));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
