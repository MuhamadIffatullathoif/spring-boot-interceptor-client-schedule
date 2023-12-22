package com.iffat.springboot.interceptor.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {


    @GetMapping("/foo")
    public ResponseEntity<?> foo() {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Welcome to the service system");
        data.put("time", new Date());
        return ResponseEntity.ok(data);
    }
}
