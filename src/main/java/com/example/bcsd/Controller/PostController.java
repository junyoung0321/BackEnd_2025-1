package com.example.bcsd.Controller;

import com.example.bcsd.Model.Article;
import com.example.bcsd.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostController {

    private final ArticleService articleService;

    @Autowired
    public PostController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String getBoardPosts(@RequestParam Long boardId, Model model) {
        List<Article> articles = articleService.getArticlesByBoardId(boardId);

        String boardName = "자유게시판";

        model.addAttribute("boardName", boardName);
        model.addAttribute("articles", articles);

        return "board"; // board.html 템플릿 호출
    }
}
