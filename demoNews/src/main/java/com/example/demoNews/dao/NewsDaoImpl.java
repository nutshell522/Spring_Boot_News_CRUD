package com.example.demoNews.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demoNews.mapper.NewsCategoryRowMapper;
import com.example.demoNews.mapper.NewsRowMapper;
import com.example.demoNews.model.News;
import com.example.demoNews.model.NewsCategory;
import com.example.demoNews.model.Result;

@Repository
public class NewsDaoImpl implements NewsDao {

	@Autowired
	private NamedParameterJdbcTemplate db;

	@Override
	public Result<List<News>> getNews(boolean getAll ,Integer page, Integer pageSize, Integer categoryId) {
		if (!getAll && categoryId == null && (page == null || pageSize == null )) {
	        return Result.failure("Parameter error.");
	    }
		
		try {
	        String sql = "SELECT n.id, n.topic, n.content, n.fk_category_id, n.publish_date, "
	                + " n.author_name, n.created_at, n.updated_at, n.status, nc.name as category_name "
	                + " FROM news AS n "
	                + " LEFT JOIN news_category AS nc ON nc.id = n.fk_category_id "
	                + " WHERE n.status = 1 ";

	        if (categoryId != null) {
	            sql += " AND n.fk_category_id = :categoryId "; 
	        }

	        sql += " ORDER BY n.publish_date DESC, n.id DESC ";

	        if (page != null && pageSize != null) {
	            int offset = (page - 1) * pageSize;
	            sql += " OFFSET " + offset + " ROWS FETCH NEXT " + pageSize + " ROWS ONLY ";
	        }

	        MapSqlParameterSource params = new MapSqlParameterSource();
	        if (categoryId != null) {
	            params.addValue("categoryId", categoryId);
	        }

	        List<News> newsList = db.query(sql, params, new NewsRowMapper());
	        return Result.success(newsList);
	    } catch (Exception e) {
	        return Result.failure("Failed to retrieve news data: " + e.getMessage());
	    }
	}


    @Override
    public Result<News> getById(Integer newsId) {
    	try {
			String sql = "SELECT n.id, n.topic, n.content, n.fk_category_id, n.publish_date, "
	                + " n.author_name, n.created_at, n.updated_at, n.status, nc.name as category_name "
	                + " FROM news AS n "
	                + " LEFT JOIN news_category AS nc ON nc.id = n.fk_category_id "
	                + " WHERE n.status = 1 AND n.id = :id ";
			
			MapSqlParameterSource params = new MapSqlParameterSource()
	                .addValue("id", newsId);

	        News news = db.queryForObject(sql, params, new NewsRowMapper());

	        if (news != null) {
	            return Result.success(news);
	        } else {
	            return Result.failure("News category not found for id: " + newsId);
	        }
        } catch (Exception e) {
            return Result.failure("Failed to retrieve news category data: " + e.getMessage());
        }
    }

    @Override
    public Result<Integer> create(News news) {
        try {
        	String sql = "INSERT INTO news (topic, content, fk_category_id, publish_date, author_name, "
        			+ " created_at, status) "
        			+ " VALUES (:topic, :content, :fk_category_id, :publish_date, :author_name, "
        			+ ":created_at, 1);";
        	LocalDateTime currentDateTime = LocalDateTime.now();

			MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("topic", news.getTopic())
					.addValue("content", news.getContent())
					.addValue("fk_category_id", news.getCategoryId())					
					.addValue("publish_date", news.getPublishDate())
					.addValue("author_name", news.getAuthorName())
					.addValue("created_at", currentDateTime);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			db.update(sql, params, keyHolder);
			Integer newId = keyHolder.getKey().intValue();
					
			return Result.success(newId);
        } catch (Exception e) {
            return Result.failure("Failed to create news: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> update(News news) {
        try {
            String sql = "UPDATE news "
            		+ " SET topic = :topic, content = :content, fk_category_id = :fk_category_id , publish_date = :publish_date, updated_at = :updated_at "
            		+ " WHERE id = :id AND status = 1";
            LocalDateTime currentDateTime = LocalDateTime.now();
            
            MapSqlParameterSource params = new MapSqlParameterSource()
            		.addValue("topic", news.getTopic())
            		.addValue("content", news.getContent())
            		.addValue("fk_category_id", news.getCategoryId())
            		.addValue("publish_date", news.getPublishDate())
					.addValue("updated_at", currentDateTime)
					.addValue("id", news.getId());
            
            int rowsAffected = db.update(sql, params);
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
        	String sql = "UPDATE news "
					+ " SET status = 0, updated_at = :updated_at "
					+ " WHERE id = :id  AND status = 1;";
        	
        	LocalDateTime currentDateTime = LocalDateTime.now();
        	
        	MapSqlParameterSource params = new MapSqlParameterSource()
					.addValue("updated_at", currentDateTime)
					.addValue("id", newsId);
        	
        	
            int rowsAffected = db.update(sql, params);
            if (rowsAffected > 0) {
                return Result.success();
            } else {
                return Result.failure("News not found for deletion");
            }
        } catch (Exception e) {
            return Result.failure("Failed to delete news: " + e.getMessage());
        }
    }


	@Override
	public Result<Integer> getTotalCount() {
		try {
	        String sql = "SELECT COUNT(*) FROM news WHERE status = 1";

	        MapSqlParameterSource params = new MapSqlParameterSource();
	        Integer totalCount = db.queryForObject(sql, params, Integer.class);

	        if (totalCount != null) {
	            return Result.success(totalCount);
	        } else {
	            return Result.failure("Failed to retrieve total count.");
	        }
	    } catch (Exception e) {
	        return Result.failure("An error occurred while retrieving total count.");
	    }
	}
}
