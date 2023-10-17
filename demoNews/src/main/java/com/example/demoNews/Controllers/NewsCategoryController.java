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

import com.example.demoNews.model.NewsCategory;
import com.example.demoNews.model.Result;
import com.example.demoNews.service.NewsCategoryService;

@RequestMapping("/NewsCategory")
@RestController
public class NewsCategoryController {
	@Autowired
	private NewsCategoryService service;
	
	@PostMapping
	public Result<Integer> create(@RequestBody NewsCategory newsCategory){
		return service.create(newsCategory);
	}
	
	@DeleteMapping("/{newsCategoryId}")
	public Result<Void> delete(@PathVariable Integer newsCategoryId){
		return service.delete(newsCategoryId);
	}
	
	@PutMapping
	public Result<Void> update(@RequestBody NewsCategory newsCategory){
		return service.update(newsCategory);
	}
	
	@GetMapping
    public Result<List<NewsCategory>> getAllNewsCategories() {
        return service.getNewsCategories();
    }

    @GetMapping("/{id}")
    public Result<NewsCategory> getNewsCategoryById(@PathVariable Integer id) {
        return service.getById(id);
    }

}
