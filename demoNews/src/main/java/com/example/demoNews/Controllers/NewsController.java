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
	public String create(@RequestBody News news) {
		Result<Integer> id = service.create(news);
		return "Create Sucessed. New StuID is " + id +"." ;
	}
	@GetMapping
	public Result<List<News>> getAll() {
		return service.getNews();
	}
	@GetMapping("/{studentId}")
	public Result<News> getById(@PathVariable Integer newsId) {
		return service.getById(newsId);
	}
	@PutMapping
	public String update(@RequestBody News news) {
		service.update(news);
		return "update successed.";
	}
	@DeleteMapping("/{newsId}")
	public String delete(@PathVariable Integer newsId) {
		service.delete(newsId);
		return "delete successed.";
	}

}
