package br.com.lab.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.lab.entity.Attribute;
import br.com.lab.entity.Category;
import br.com.lab.model.AttributeTransfer;
import br.com.lab.model.CategoryTransfer;

@Component
public class CategoryTransformer {
	public Category transformCategoryFromTransfer(CategoryTransfer categoryTransfer) {
		Category category = new Category();
		category.setId(categoryTransfer.getId());
		category.setName(categoryTransfer.getName());
		category.setAttributes(transformAttributesFromTransfer(categoryTransfer.getAttributes(), category));
		return category;
	}

	public List<Attribute> transformAttributesFromTransfer(List<AttributeTransfer> attributes, Category category) {
		if (CollectionUtils.isEmpty(attributes)) {
			return null;
		}

		return attributes.stream()
				.map(attribute -> Attribute.builder().id(attribute.getId()).name(attribute.getName())
						.nullable(attribute.getNullable()).type(attribute.getType()).category(category).build())
				.collect(Collectors.toList());
	}

	public List<AttributeTransfer> transformAttributesFromEntity(List<Attribute> attributes) {
		if (CollectionUtils.isEmpty(attributes)) {
			return null;
		}

		return attributes.stream()
				.map(attribute -> AttributeTransfer.builder().id(attribute.getId()).name(attribute.getName())
						.nullable(attribute.getNullable()).type(attribute.getType()).build())
				.collect(Collectors.toList());
	}

	public CategoryTransfer transformCategoryFromEntity(Category category) {
		if (category == null) {
			return null;
		}
		return CategoryTransfer.builder().id(category.getId()).name(category.getName())
				.attributes(transformAttributesFromEntity(category.getAttributes())).build();
	}
}
