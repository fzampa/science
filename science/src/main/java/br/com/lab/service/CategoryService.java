package br.com.lab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lab.entity.Category;
import br.com.lab.model.CategoryTransfer;
import br.com.lab.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryTransfer findById(Long id) {
		Optional<Category> maybeCategory = categoryRepository.findById(id);
			Category category = maybeCategory.orElse(null);
			return transformFromEntity(category);
	}

	public CategoryTransfer persistCategory(CategoryTransfer categoryTransfer) {
		Category category = transformFromTransfer(categoryTransfer);
		return transformFromEntity( categoryRepository.save(category));
	}

	private Category transformFromTransfer(CategoryTransfer categoryTransfer) {
		Category category = new Category();
		category.setId(categoryTransfer.getId());
		category.setName(categoryTransfer.getName());
		return category;
	}

	private CategoryTransfer transformFromEntity(Category category) {
		if (category == null) {
			return null;
		}
		return CategoryTransfer.builder().id(category.getId()).name(category.getName()).build();
	}

	public List<CategoryTransfer> findAll() {
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.map(category -> CategoryTransfer.builder().id(category.getId()).name(category.getName()).build())
				.collect(Collectors.toList());
	}
}
