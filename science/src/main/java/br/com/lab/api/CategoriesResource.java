package br.com.lab.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab.model.CategoryTransfer;
import br.com.lab.service.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesResource {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(value="/{categoryId}")
	public CategoryTransfer getCategory(@PathVariable Long categoryId) {
		return categoryService.findById(categoryId);
	}
	
	@GetMapping
	public Collection<CategoryTransfer> getAllCategories(){
		return categoryService.findAll();
	}
	
	@PostMapping
	public CategoryTransfer addCategory(@RequestBody CategoryTransfer categoryTransfer) {
		return categoryService.persistCategory(categoryTransfer);
	}	
}
