package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Transactional // 쓰기 작업: 트랜잭션 적용
    public Article createArticle(Article article) {
        return articleDao.save(article);
    }

    @Transactional(readOnly = true) // 읽기 작업
    public Article getArticle(Long id) {
        return articleDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByBoardId(Long boardId) {
        return articleDao.findAllByBoardId(boardId);
    }

    @Transactional
    public Article updateArticle(Long id, Article article) {
        article.setId(id);
        return articleDao.update(article);
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleDao.delete(id);
    }
}
