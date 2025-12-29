package com.example.bcsd.service;

import com.example.bcsd.Model.Article;
import com.example.bcsd.repository.ArticleRepository;
import com.example.bcsd.dto.ArticleCreateRequest;
import com.example.bcsd.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Article article = new Article();
        article.setAuthorId(request.getAuthorId());
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        return articleRepository.save(article);
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 게시글입니다. id=" + id));
    }

    public List<Article> getArticlesByBoardId(Long boardId) {
        return articleRepository.findAllByBoardId(boardId);
    }

    @Transactional
    public Article updateArticle(Long id, Article updateParam) {
        Article article = getArticle(id);
        article.setTitle(updateParam.getTitle());
        article.setContent(updateParam.getContent());
        return article;
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = getArticle(id);
        articleRepository.delete(article);
    }
}
