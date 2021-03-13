package br.com.lab.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab.model.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoriesResource {
	
	private Map<Long, Category> database;
	
	@PostConstruct
	public void after() {
		database = new HashMap<>();
	}
	
	@GetMapping(value="/{categoryId}")
	public Category getCategory(@PathVariable Long categoryId) {
		return database.get(categoryId);
		
	}
	
	@GetMapping
	public Collection<Category> getAllCategories(){
		return database.values();
	}
	
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		Long id = database.keySet().stream().count() +1;
		category.setId(id);
		
		database.put(id, category);
		return category;
	}

	
	
}
