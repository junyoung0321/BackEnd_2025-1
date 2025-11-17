package com.example.bcsd.Controller;

import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import com.example.bcsd.Model.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Controller
public class ArticleController {

    private final Map<Long, Article> articleStorage = new ConcurrentHashMap<>();
    private final AtomicLong articleIdCounter = new AtomicLong();
    private final Map<Long, Member> memberStorage = new ConcurrentHashMap<>();
    private final AtomicLong memberIdCounter = new AtomicLong();
    private final Map<Long, Board> boardStorage = new ConcurrentHashMap<>();
    private final AtomicLong boardIdCounter = new AtomicLong();

    private Article populateArticleDetails(Article article) {
        Member author = memberStorage.getOrDefault(article.getAuthorId(),
                new Member(0L, "익명", "", ""));
        Board board = boardStorage.getOrDefault(article.getBoardId(),
                new Board(0L, "기본 게시판"));

        article.setAuthorName(author.getName());
        article.setBoardName(board.getName());
        return article;
    }

    @GetMapping("/posts")
    public String getPostsView(Model model) {
        List<Article> articles = new ArrayList<>(articleStorage.values())
                .stream()
                .map(this::populateArticleDetails)
                .collect(Collectors.toList());

        model.addAttribute("articles", articles);
        String boardName = articles.isEmpty() ? "게시판" : articles.get(0).getBoardName();
        model.addAttribute("boardName", boardName);

        return "posts";
    }

    @GetMapping("/articles")
    @ResponseBody
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = new ArrayList<>(articleStorage.values())
                .stream()
                .map(this::populateArticleDetails)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> read(@PathVariable Long id) {
        Article article = articleStorage.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(populateArticleDetails(article));
    }

    @PostMapping("/articles")
    @ResponseBody
    public ResponseEntity<Article> create(@RequestBody Article article) {
        long newId = articleIdCounter.incrementAndGet();
        article.setId(newId);
        article.setCreateTime(LocalDateTime.now());
        article.setModifiedTime(LocalDateTime.now());

        articleStorage.put(article.getId(), article);
        return ResponseEntity.ok(populateArticleDetails(article));
    }

    @PutMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article updated) {
        Article article = articleStorage.get(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        article.setTitle(updated.getTitle());
        article.setContent(updated.getContent());
        article.setModifiedTime(LocalDateTime.now());

        return ResponseEntity.ok(populateArticleDetails(article));
    }

    @DeleteMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!articleStorage.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        articleStorage.remove(id);
        return ResponseEntity.ok().build();
    }
}
