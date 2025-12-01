package com.example.bcsd.service;

import com.example.bcsd.dao.ArticleDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.dto.ArticleCreateRequest;
import com.example.bcsd.exception.InvalidRequestException;
import com.example.bcsd.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Transactional
    public Article createArticle(ArticleCreateRequest request) {
        Article article = new Article();

        article.setAuthorId(request.getAuthorId());
        article.setBoardId(request.getBoardId());
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        return articleDao.save(article);
    }

    @Transactional(readOnly = true)
    public Article getArticle(Long id) {
        try {
            return articleDao.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("존재하지 않는 게시글입니다. id=" + id);
        }
    }

    @Transactional(readOnly = true)
    public List<Article> getArticlesByBoardId(Long boardId) {
        return articleDao.findAllByBoardId(boardId);
    }

    @Transactional
    public Article updateArticle(Long id, Article article) {

        Article existingArticle = getArticle(id);

        existingArticle.setTitle(article.getTitle());
        existingArticle.setContent(article.getContent());

        return articleDao.update(existingArticle);
    }

    @Transactional
    public void deleteArticle(Long id) {
        // 존재하는지 확인 (없으면 getArticle에서 404 터짐)
        getArticle(id);

        articleDao.delete(id);
    }
}
