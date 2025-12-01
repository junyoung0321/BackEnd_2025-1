package com.example.bcsd.dao;

import com.example.bcsd.Model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Article> articleRowMapper = (rs, rowNum) -> {
        Article article = new Article();
        article.setId(rs.getLong("id"));
        article.setAuthorId(rs.getLong("author_id"));
        article.setBoardId(rs.getLong("board_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setCreatedDate(rs.getTimestamp("created_date"));
        article.setModifiedDate(rs.getTimestamp("modified_date"));
        return article;
    };

    // 1INSERT
    public Article save(Article article) {
        String sql = "INSERT INTO article (author_id, board_id, title, content, created_date, modified_date) VALUES (?, ?, ?, ?, NOW(), NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, article.getAuthorId());
            ps.setLong(2, article.getBoardId());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getContent());
            return ps;
        }, keyHolder);

        article.setId(keyHolder.getKey().longValue());
        return article;
    }

    // SELECT
    public Article findById(Long id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, articleRowMapper, id);
    }

    // SELECT List
    public List<Article> findAllByBoardId(Long boardId) {
        String sql = "SELECT * FROM article WHERE board_id = ?";
        return jdbcTemplate.query(sql, articleRowMapper, boardId);
    }

    // UPDATE
    public Article update(Article article) {
        String sql = "UPDATE article SET title = ?, content = ?, modified_date = NOW() WHERE id = ?";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getId());
        return findById(article.getId()); // 수정된 내용 반환
    }

    // DELETE
    public void delete(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
