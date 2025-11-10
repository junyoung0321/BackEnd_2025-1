package com.example.bcsd;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ArticleController {
    private final Map<Long, Article> articleRepo = new ConcurrentHashMap<>();

    //READ
    @GetMapping("/article/{id}")
    public ResponseEntity<Article> read(@PathVariable Long id) {
        Article article = articleRepo.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    //CREATE
    @PostMapping("/article")
    public ResponseEntity<Article> create(@RequestBody Article article) {
        articleRepo.put(article.getId(), article);
        return ResponseEntity.ok(article);
    }

    //UPDATE
    @PutMapping("/article/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article updated) {
        Article article = articleRepo.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        article.setTitle(updated.getTitle());
        article.setContent(updated.getContent());
        return ResponseEntity.ok(article);
    }

    //DELETE
    @DeleteMapping("/article/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!articleRepo.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        articleRepo.remove(id);
        return ResponseEntity.ok().build();
    }
}
