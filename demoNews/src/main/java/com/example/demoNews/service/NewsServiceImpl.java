package com.example.demoNews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoNews.dao.NewsDao;
import com.example.demoNews.model.News;
import com.example.demoNews.model.Result;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsDao repo;
	@Override
	public Result<List<News>> getNews(boolean getAll, Integer page, Integer pageSize, Integer categoryId) {
		return repo.getNews(getAll, page, pageSize, categoryId);
	}

	@Override
	public Result<News> getById(Integer newsId) {
		return repo.getById(newsId);
	}

	@Override
	public Result<Integer> create(News news) {
		return repo.create(news);
	}

	@Override
	public Result<Void> update(News news) {
		return repo.update(news);
	}

	@Override
	public Result<Void> delete(Integer newsId) {
		return repo.delete(newsId);
	}
	
}
