package br.com.lab.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lab.constant.AttributeType;
import br.com.lab.entity.Attribute;
import br.com.lab.entity.Category;
import br.com.lab.model.AttributeTransfer;
import br.com.lab.model.CategoryTransfer;
import br.com.lab.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;

	@Spy
	private CategoryTransformer transformer;

	@InjectMocks
	private CategoryService categoryService;

	@Test
	public void whenPersistingACategoryTheResponseShouldBeComplete() {
		List<AttributeTransfer> attributesTransfer = new ArrayList<>();
		attributesTransfer
				.add(AttributeTransfer.builder().id(2L).name("attr").nullable(true).type(AttributeType.TEXT).build());
		CategoryTransfer transfer = CategoryTransfer.builder().id(1L).name("name").attributes(attributesTransfer)
				.build();

		Category category = getCategory();

		when(categoryRepository.save(any())).thenReturn(category);

		CategoryTransfer response = categoryService.persistCategory(transfer);

		assertThat(response.getId()).isEqualTo(1L);
		assertThat(response.getName()).isEqualTo("name");
		assertThat(response.getAttributes()).hasSize(1);
		assertThat(response.getAttributes()).hasSize(1);
		AttributeTransfer attributeTransfer = response.getAttributes().get(0);
		assertThat(attributeTransfer.getId()).isEqualTo(2L);
		assertThat(attributeTransfer.getName()).isEqualTo("attr");
		assertThat(attributeTransfer.getNullable()).isEqualTo(true);
		assertThat(attributeTransfer.getType()).isEqualTo(AttributeType.TEXT);
	}

	@Test
	public void whenListingDataAllDataShouldBePresent() {
		List<Category> categories = new ArrayList<>();
		categories.add(getCategory());
		when(categoryRepository.findAll()).thenReturn(categories);
		List<CategoryTransfer> response = categoryService.findAll();
		assertThat(response).hasSize(1);
		CategoryTransfer categoryTransfer = response.get(0);
		assertThat(categoryTransfer.getId()).isEqualTo(1L);
		assertThat(categoryTransfer.getName()).isEqualTo("name");
		assertThat(categoryTransfer.getAttributes()).hasSize(1);
	}

	private Category getCategory() {
		Category category = new Category();
		category.setId(1L);
		category.setName("name");
		List<Attribute> attributes = new ArrayList<>();
		Attribute attribute = new Attribute();
		attribute.setId(2L);
		attribute.setName("attr");
		attribute.setNullable(true);
		attribute.setType(AttributeType.TEXT);
		attributes.add(attribute);
		category.setAttributes(attributes);
		return category;
	}
}
