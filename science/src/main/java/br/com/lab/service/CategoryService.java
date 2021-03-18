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
	private CategoryRepository repository;
	
	@Autowired
	private Transformer transformer;

	public CategoryTransfer findById(Long id) {
		Optional<Category> maybeCategory = repository.findById(id);
		Category category = maybeCategory.orElse(null);
		return transformer.transformCategoryFromEntity(category);
	}

	public CategoryTransfer persistCategory(CategoryTransfer categoryTransfer) {
		Category category = transformer.transformCategoryFromTransfer(categoryTransfer);
		return transformer.transformCategoryFromEntity(repository.save(category));
	}

	public List<CategoryTransfer> findAll() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(category -> transformer.transformCategoryFromEntity(category))
				.collect(Collectors.toList());
	}
}
