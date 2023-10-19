package com.example.demoNews.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoNews.model.News;
import com.example.demoNews.model.Result;
import com.example.demoNews.service.NewsService;

@RequestMapping("/News")
@RestController
public class NewsController {
	@Autowired
	private NewsService service;

	@PostMapping
	public Result<Integer> create(@RequestBody News news) {
		return service.create(news);
	}

	@GetMapping
	public Result<List<News>> getAllNews() {
		return service.getNews(true, null, null, null);
	}

	@GetMapping("/srch")
	public Result<List<News>> searchNews(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer categoryId) {
		return service.getNews(false, page, pageSize, categoryId);
	}

	@GetMapping("/{newsId}")
	public Result<News> getById(@PathVariable Integer newsId) {
		return service.getById(newsId);
	}

	@PutMapping
	public Result<Void> update(@RequestBody News news) {
		return service.update(news);
	}

	@DeleteMapping("/{newsId}")
	public Result<Void> delete(@PathVariable Integer newsId) {
		return service.delete(newsId);
	}

	@GetMapping("/count")
	public Result<Integer> getNewsCount() {;
		return service.getTotalCount();
	}

}
