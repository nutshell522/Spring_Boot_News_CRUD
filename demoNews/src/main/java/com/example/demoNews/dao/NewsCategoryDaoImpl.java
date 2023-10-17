package com.example.demoNews.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demoNews.mapper.NewsCategoryRowMapper;
import com.example.demoNews.model.NewsCategory;
import com.example.demoNews.model.Result;

@Repository
public class NewsCategoryDaoImpl implements NewsCategoryDao {

	@Autowired
	private NamedParameterJdbcTemplate db;

	@Override
	public Result<List<NewsCategory>> getNewsCategories() {
		try {
			String sql = "SELECT * FROM news_category WHERE status = 1";
            List<NewsCategory> newsCategoryList = db.query(sql, new NewsCategoryRowMapper());
            return Result.success(newsCategoryList);
        } catch (Exception e) {
            return Result.failure("Failed to retrieve newsCategories data: " + e.getMessage());
        }
	}

	@Override
	public Result<NewsCategory> getById(Integer newsCategoryId) {
		// TODO 自動產生的方法 Stub
		return null;
	}

	@Override
	public Result<Integer> create(NewsCategory newsCategory) {
		try {
			String sql = "INSERT INTO news_category (name, description) " + " VALUES (:name, :description);";

			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("name", newsCategory.getName())
					.addValue("description", newsCategory.getDescription());

			KeyHolder keyHolder = new GeneratedKeyHolder();
			db.update(sql, params, keyHolder);
			Integer newId = keyHolder.getKey().intValue();
					
			return Result.success(newId);
		} catch (Exception e) {
			return Result.failure("Failed to retrieve newsCategory by ID: " + e.getMessage());
		}
	}

	@Override
	public Result<Void> update(NewsCategory newsCategory) {
		try {
			String sql = "UPDATE news_category "
					+ " SET name = :name, description = :description , updated_at = :updated_at "
					+ " WHERE id = :id ;";
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("name", newsCategory.getName())
					.addValue("description", newsCategory.getDescription())
					.addValue("updated_at", currentDateTime)
					.addValue("id", newsCategory.getId());
			
			db.update(sql, params);
			
			return Result.success(null);
			
		} catch (Exception e) {
			return Result.failure("Failed to delete newsCategory: " + e.getMessage());
		}
	}

	@Override
	public Result<Void> delete(Integer newsCategoryId) {
		try {
			String sql = "UPDATE news_category "
					+ " SET status = 0, updated_at = :updated_at "
					+ " WHERE id = :id ;";
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("updated_at", currentDateTime)
					.addValue("id", newsCategoryId);
			
			db.update(sql, params);
			
			return Result.success(null);
			
		} catch (Exception e) {
			return Result.failure("Failed to delete newsCategory: " + e.getMessage());
		}
	}
}
