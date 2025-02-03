package com.md.apitemplate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/template")
public class ApiTemplateController {

    @GetMapping("/success")
    public ResponseEntity<String> getSuccess() {
        log.info("GET /api/success");
        return ResponseEntity.ok("Hello from success!");
    }

    @GetMapping("/error")
    public ResponseEntity<String> getError() {
        log.info("GET /api/error");
        throw new RuntimeException("Hello from error!");
    }
}
