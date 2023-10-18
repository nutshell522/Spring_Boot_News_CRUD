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
            return Result.failure("Failed to retrieve news categories data: " + e.getMessage());
        }
	}

	@Override
	public Result<NewsCategory> getById(Integer newsCategoryId) {
		try {
			String sql = "SELECT * FROM news_category WHERE id = :id AND status = 1";
	        MapSqlParameterSource params = new MapSqlParameterSource()
	                .addValue("id", newsCategoryId);

	        NewsCategory newsCategory = db.queryForObject(sql, params, new NewsCategoryRowMapper());

	        if (newsCategory != null) {
	            return Result.success(newsCategory);
	        } else {
	            return Result.failure("News category not found for id: " + newsCategoryId);
	        }
        } catch (Exception e) {
            return Result.failure("Failed to retrieve news category data: " + e.getMessage());
        }
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
			return Result.failure("Failed to retrieve news category by ID: " + e.getMessage());
		}
	}

	@Override
	public Result<Void> update(NewsCategory newsCategory) {
		try {
			String sql = "UPDATE news_category "
					+ " SET name = :name, description = :description , updated_at = :updated_at "
					+ " WHERE id = :id  AND status = 1;";
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("name", newsCategory.getName())
					.addValue("description", newsCategory.getDescription())
					.addValue("updated_at", currentDateTime)
					.addValue("id", newsCategory.getId());
			
			int rowsAffected = db.update(sql, params);
			
		    if (rowsAffected > 0) {
	                return Result.success();
	            } else {
	                return Result.failure("News category not found for update");
	            }
			
		} catch (Exception e) {
			return Result.failure("Failed to update news category: " + e.getMessage());
		}
	}

	@Override
	public Result<Void> delete(Integer newsCategoryId) {
		try {
			String sql = "UPDATE news_category "
					+ " SET status = 0, updated_at = :updated_at "
					+ " WHERE id = :id  AND status = 1;";
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			
			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("updated_at", currentDateTime)
					.addValue("id", newsCategoryId);
			
			int rowsAffected = db.update(sql, params);
			
			if (rowsAffected > 0) {
                return Result.success();
            } else {
                return Result.failure("News category not found for deletion");
            }
			
		} catch (Exception e) {
			return Result.failure("Failed to delete news category: " + e.getMessage());
		}
	}
}
