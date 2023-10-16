package com.example.demoNews.dao;

import java.util.List;

import com.example.demoNews.model.News;
import com.example.demoNews.model.Result;

public interface NewsDao {
    Result<List<News>> getNews();
    Result<News> getById(Integer newsId);
    Result<Integer> create(News news);
    Result<Void> update(News news);
    Result<Void> delete(Integer newsId);
}

