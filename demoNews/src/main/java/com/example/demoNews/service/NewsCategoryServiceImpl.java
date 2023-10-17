package com.example.demoNews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoNews.dao.NewsCategoryDao;
import com.example.demoNews.model.NewsCategory;
import com.example.demoNews.model.Result;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

	@Autowired
	private NewsCategoryDao repo;
	
	@Override
	public Result<List<NewsCategory>> getNewsCategories() {
		return repo.getNewsCategories();
	}

	@Override
	public Result<NewsCategory> getById(Integer newsCategoryId) {
		return repo.getById(newsCategoryId);
	}

	@Override
	public Result<Integer> create(NewsCategory newsCategory) {
		return repo.create(newsCategory);
	}

	@Override
	public Result<Void> update(NewsCategory newsCategory) {
		return repo.update(newsCategory);
	}

	@Override
	public Result<Void> delete(Integer newsCategoryId) {
		return repo.delete(newsCategoryId);
	}
	
}
