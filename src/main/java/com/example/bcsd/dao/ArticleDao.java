package com.example.bcsd.dao;

import com.example.bcsd.Model.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ArticleDao {

    @PersistenceContext
    private EntityManager em;

    public Article save(Article article) {
        em.persist(article);
        return article;
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }

    public List<Article> findAllByBoardId(Long boardId) {
        return em.createQuery("SELECT a FROM Article a WHERE a.boardId = :boardId", Article.class)
                .setParameter("boardId", boardId)
                .getResultList();
    }

    public void delete(Article article) {
        em.remove(article);
    }
}
