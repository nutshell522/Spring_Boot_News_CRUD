package com.example.demoNews.service;

import java.util.List;

import com.example.demoNews.model.News;
import com.example.demoNews.model.Result;

public interface NewsService {
	Result<List<News>> getNews();
    Result<News> getById(Integer newsId);
    Result<Integer> create(News news);
    Result<Void> update(News news);
    Result<Void> delete(Integer newsId);
}
