package com.example.demoNews.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demoNews.model.News;


public class NewsRowMapper implements RowMapper<News> {

	@Override
	public News mapRow(ResultSet rs, int rowNum) throws SQLException {
		News news = new News();
		news.setId(rs.getInt("id"));
		news.setTopic(rs.getString("topic"));
		news.setContent(rs.getString("content"));
		news.setCategoryName(rs.getString("category_name"));
		news.setPublishDate(rs.getTimestamp("publish_date"));
		news.setAuthorName(rs.getString("author_name"));
		news.setCreatedAt(rs.getTimestamp("created_at"));
		news.setUpdatedAt(rs.getTimestamp("updated_at"));
		news.setStatus(rs.getBoolean("status"));
		news.setCategoryId(rs.getInt("fk_category_id"));
		
		return news;
	}
}
