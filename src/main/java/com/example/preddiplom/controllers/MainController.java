package com.example.preddiplom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class MainController {
    @RequestMapping("/hello")
    public Object authorization() {
        return new HashMap<String, String>() {
            {
                put("status", "Hello World!");
            }
        };
    }
}
