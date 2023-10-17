package com.example.demoNews.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.example.demoNews.model.NewsCategory;

public class NewsCategoryRowMapper implements RowMapper<NewsCategory> {

	@Override
	public NewsCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		NewsCategory newsCategory = new NewsCategory();
		newsCategory.setId(rs.getInt("id"));
		newsCategory.setName(rs.getString("name"));
		newsCategory.setDescription(rs.getString("description"));
		newsCategory.setCreatedAt(rs.getDate("created_at"));
		newsCategory.setUpdatedAt(rs.getDate("updated_at"));
		newsCategory.setStatus(rs.getBoolean("status"));		
		return newsCategory;
	}

}