package com.example.bcsd.Controller;

import com.example.bcsd.Model.Article;
import com.example.bcsd.dto.ArticleCreateRequest;
import com.example.bcsd.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // GET
    @GetMapping
    public List<Article> getArticles(@RequestParam Long boardId) {
        return articleService.getArticlesByBoardId(boardId);
    }

    // GET
    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    // POST
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody @Valid ArticleCreateRequest request) {
        return ResponseEntity.ok(articleService.createArticle(request));
    }

    // PUT
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
