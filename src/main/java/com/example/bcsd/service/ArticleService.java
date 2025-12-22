package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.dto.ArticleCreateRequest;
import com.example.bcsd.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Article article = new Article();
        article.setAuthorId(request.getAuthorId());
        article.setBoardId(request.getBoardId());
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        return articleDao.save(article);
    }

    public Article getArticle(Long id) {
        Article article = articleDao.findById(id);
        if (article == null) {
            throw new ResourceNotFoundException("존재하지 않는 게시글입니다. id=" + id);
        }
        return article;
    }

    public List<Article> getArticlesByBoardId(Long boardId) {
        return articleDao.findAllByBoardId(boardId);
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
        articleDao.delete(article);
    }
}
