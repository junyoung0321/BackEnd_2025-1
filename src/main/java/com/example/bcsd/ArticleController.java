package com.example.bcsd;


import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ArticleController {
    private final Map<String, Article> articleRepo = new ConcurrentHashMap<>();

    //READ
    @GetMapping("/article/{id}")

    //CREATE
    @PostMapping("/article")

    //UPDATE
    @PutMapping("/article/{id}")

    //DELETE
    @DeleteMapping("/article/{id}")
}
