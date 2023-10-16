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
		news.setTitle(rs.getString("title"));
		news.setContent(rs.getString("content"));
		news.setPublish_date(rs.getDate("publish_date"));
		news.setAuthor_name(rs.getString("author_name"));
		news.setStatus(rs.getBoolean("status"));
		return news;
	}
}
