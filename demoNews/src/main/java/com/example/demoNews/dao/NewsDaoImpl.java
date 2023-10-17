package com.example.demoNews.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demoNews.mapper.NewsRowMapper;
import com.example.demoNews.model.News;
import com.example.demoNews.model.Result;

@Repository
public class NewsDaoImpl implements NewsDao {

	@Autowired
	private NamedParameterJdbcTemplate db;

    @Override
    public Result<List<News>> getNews() {
        try {
            List<News> newsList = db.query("SELECT * FROM news", new NewsRowMapper());
            return Result.success(newsList);
        } catch (Exception e) {
            return Result.failure("Failed to retrieve news data: " + e.getMessage());
        }
    }

    @Override
    public Result<News> getById(Integer newsId) {
        try {
            News news = db.queryForObject("SELECT * FROM news WHERE id = ?", new Object[]{newsId}, new NewsRowMapper());
            return Result.success(news);
        } catch (Exception e) {
            return Result.failure("Failed to retrieve news by ID: " + e.getMessage());
        }
    }

    @Override
    public Result<Integer> create(News news) {
        try {
            String sql = "INSERT INTO news (title, content, publish_date) VALUES (?, ?, ?)";
            int rowsAffected = db.update(sql, news.getTitle(), news.getContent(), news.getPublish_date());
            return Result.success(rowsAffected);
        } catch (Exception e) {
            return Result.failure("Failed to create news: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> update(News news) {
        try {
            String sql = "UPDATE news SET title = ?, content = ?, publish_date = ? WHERE id = ?";
            int rowsAffected = db.update(sql, news.getTitle(), news.getContent(), news.getPublish_date(), news.getId());
            if (rowsAffected > 0) {
                return Result.success();
            } else {
                return Result.failure("News not found for update");
            }
        } catch (Exception e) {
            return Result.failure("Failed to update news: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> delete(Integer newsId) {
        try {
            String sql = "DELETE FROM news WHERE id = ?";
            int rowsAffected = db.update(sql, newsId);
            if (rowsAffected > 0) {
                return Result.success();
            } else {
                return Result.failure("News not found for deletion");
            }
        } catch (Exception e) {
            return Result.failure("Failed to delete news: " + e.getMessage());
        }
    }
	
}
