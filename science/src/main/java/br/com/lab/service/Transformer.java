package br.com.lab.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.lab.constant.AttributeType;
import br.com.lab.entity.Attribute;
import br.com.lab.entity.Category;
import br.com.lab.entity.Item;
import br.com.lab.entity.ItemValue;
import br.com.lab.entity.typeditems.BooleanItemValue;
import br.com.lab.entity.typeditems.DateTimeItemValue;
import br.com.lab.entity.typeditems.DoubleItemValue;
import br.com.lab.entity.typeditems.IntegerItemValue;
import br.com.lab.entity.typeditems.TextualItemValue;
import br.com.lab.model.AttributeTransfer;
import br.com.lab.model.CategoryTransfer;
import br.com.lab.model.ItemTransfer;

@Component
public class Transformer {
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

	public Item transformItemFromTransfer(ItemTransfer itemTransfer) {
		Attribute attribute = transformAttributeFromTransfer(itemTransfer.getAttribute());

		Item item = new Item();
		item.setAttribute(attribute);
		item.setItemValue(getItemValue(itemTransfer.getValue(), attribute.getType()));

		return item;
	}
	
	public List<ItemTransfer> transformItemsFromEntity(List<Item> items) {
		return items.stream().map(item -> transformItemFromEntity(item)).collect(Collectors.toList());
	}

	public ItemTransfer transformItemFromEntity(Item item) {
		AttributeTransfer attributeTransfer = transformAttributeFromEntity(item.getAttribute());
		ItemTransfer itemTransfer = ItemTransfer.builder().attribute(attributeTransfer).value(item.getItemValue().getValue()).build();

		return itemTransfer;
	}

	private AttributeTransfer transformAttributeFromEntity(Attribute attribute) {
		return AttributeTransfer.builder().id(attribute.getId()).name(attribute.getName())
				.nullable(attribute.getNullable()).type(attribute.getType()).build();
	}

	private ItemValue<?> getItemValue(Object value, AttributeType type) {

		switch (type) {
		case BOOLEAN:
			BooleanItemValue booleanIV = new BooleanItemValue();
			booleanIV.setValue((Boolean) value);
			return booleanIV;
		case TEXT:
			TextualItemValue textualIV = new TextualItemValue();
			textualIV.setValue((String) value);
			return textualIV;
		case INTEGER:
			IntegerItemValue integerIV = new IntegerItemValue();
			integerIV.setValue((Integer) value);
			return integerIV;
		case DOUBLE:
			DoubleItemValue doubleIV = new DoubleItemValue();
			doubleIV.setValue((Double) value);
			return doubleIV;
		case DATETIME:
			DateTimeItemValue dateTimeIV = new DateTimeItemValue();
			dateTimeIV.setValue((LocalDateTime) value);
			return dateTimeIV;
		}
		throw new IllegalArgumentException("Wrong AttributeType");
	}

	public Attribute transformAttributeFromTransfer(AttributeTransfer attributeTransfer) {
		Attribute attribute = new Attribute();
		attribute.setId(attributeTransfer.getId());
		attribute.setName(attributeTransfer.getName());
		attribute.setNullable(attributeTransfer.getNullable());
		attribute.setType(attributeTransfer.getType());
		return attribute;
	}
}
