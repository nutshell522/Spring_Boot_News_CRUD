package com.example.demoNews.service;

import java.util.List;

import com.example.demoNews.model.NewsCategory;
import com.example.demoNews.model.Result;

public interface NewsCategoryService {
	Result<List<NewsCategory>> getNewsCategories();
	Result<NewsCategory> getById(Integer newsCategoryId);
	Result<Integer> create(NewsCategory newsCategory); 
	Result<Void> update(NewsCategory newsCategory);
	Result<Void> delete(Integer newsCategoryId);
}
