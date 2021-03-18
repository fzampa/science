package br.com.lab.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lab.constant.AttributeType;
import br.com.lab.entity.Attribute;
import br.com.lab.entity.Category;
import br.com.lab.entity.Item;
import br.com.lab.model.AttributeTransfer;
import br.com.lab.model.CategoryTransfer;
import br.com.lab.model.ItemTransfer;

@ExtendWith(MockitoExtension.class)
public class TransformerTest {

	@InjectMocks
	private Transformer transformer;

	@Test
	public void shouldTransformAllDataFromCategoryEntityToTransfer() {
		CategoryTransfer category = transformer.transformCategoryFromEntity(getCategoryEntity());
		assertThat(category.getId()).isEqualTo(1);
		assertThat(category.getName()).isEqualTo("CategoryEntity");
		List<AttributeTransfer> attributes = category.getAttributes();
		assertThat(attributes).hasSize(2);
		AttributeTransfer attribute = attributes.get(0);
		assertThat(attribute.getId()).isEqualTo(2);
		assertThat(attribute.getName()).isEqualTo("AttributeEntity");
		assertThat(attribute.getNullable()).isEqualTo(true);
		assertThat(attribute.getType()).isEqualTo(AttributeType.INTEGER);
	}

	@Test
	public void shouldTransformAllDataFromCategoryTransferToEntity() {
		Category category = transformer.transformCategoryFromTransfer(getCategoryTransfer());
		assertThat(category.getId()).isEqualTo(3L);
		assertThat(category.getName()).isEqualTo("CategoryTransfer");
		List<Attribute> attributes = category.getAttributes();
		assertThat(attributes).hasSize(1);
		Attribute attribute = attributes.get(0);
		assertThat(attribute.getId()).isEqualTo(4L);
		assertThat(attribute.getName()).isEqualTo("AttributeTransfer");
		assertThat(attribute.getNullable()).isEqualTo(false);
		assertThat(attribute.getType()).isEqualTo(AttributeType.DOUBLE);
	}

	@Test
	public void shouldTransformAllDataFromAttributeEntityToTransfer() {
		List<AttributeTransfer> attributes = transformer.transformAttributesFromEntity(getAttributesEntity());
		assertThat(attributes).hasSize(2);
		AttributeTransfer attribute = attributes.get(0);
		assertThat(attribute.getId()).isEqualTo(2L);
		assertThat(attribute.getName()).isEqualTo("AttributeEntity");
		assertThat(attribute.getNullable()).isEqualTo(true);
		assertThat(attribute.getType()).isEqualTo(AttributeType.INTEGER);
	}

	@Test
	public void shouldTransformAllDataFromAttributeTransferToEntity() {
		List<Attribute> attributes = transformer.transformAttributesFromTransfer(getAttributesTransfer(), getCategoryEntity());
		assertThat(attributes).hasSize(1);
		Attribute attribute = attributes.get(0);
		assertThat(attribute.getId()).isEqualTo(4L);
		assertThat(attribute.getName()).isEqualTo("AttributeTransfer");
		assertThat(attribute.getNullable()).isEqualTo(false);
		assertThat(attribute.getType()).isEqualTo(AttributeType.DOUBLE);
	}

	@Test
	public void shouldNotFailIfCategoryEntityHasNoAttributes() {
		Category category = getCategoryEntity();
		category.setAttributes(null);
		
		CategoryTransfer categoryTransfer = transformer.transformCategoryFromEntity(category);
		assertThat(categoryTransfer.getAttributes()).isNull();
		assertThat(categoryTransfer.getId()).isEqualTo(1L);
		assertThat(categoryTransfer.getName()).isEqualTo("CategoryEntity");
	}

	@Test
	public void shouldNotFailIfCategoryTransferHasNoAttributes() {
		CategoryTransfer category = getCategoryTransfer();
		category.setAttributes(null);
		
		Category categoryTransfer = transformer.transformCategoryFromTransfer(category);
		assertThat(categoryTransfer.getAttributes()).isNull();
		assertThat(categoryTransfer.getId()).isEqualTo(3L);
		assertThat(categoryTransfer.getName()).isEqualTo("CategoryTransfer");
	}
	
	@Test
	void shouldTransformItemTransferToItem() {
		ItemTransfer itemTransfer = ItemTransfer.builder().
		attribute(getTextAttribute()).
		value("TEXT").build();

		Item item = transformer.transformItemFromTransfer(itemTransfer);

		assertThat(item).isNotNull();
		assertThat(item.getItemValue().getValue()).isEqualTo("TEXT" );
		assertThat(item.getAttribute().getType()).isEqualTo(AttributeType.TEXT);
	}

	private AttributeTransfer getTextAttribute() {
		return AttributeTransfer.builder().id(1L).name("txtAttr").nullable(true)
				.type(AttributeType.TEXT).build();
	}


	private Category getCategoryEntity() {
		Category category = new Category();
		category.setId(1L);
		category.setName("CategoryEntity");
		category.setAttributes(getAttributesEntity());
		return category;
	}

	private List<Attribute> getAttributesEntity() {
		List<Attribute> attributes = new ArrayList<>();
		Attribute attribute = new Attribute();
		attribute.setId(2L);
		attribute.setName("AttributeEntity");
		attribute.setNullable(true);
		attribute.setType(AttributeType.INTEGER);

		attributes.add(attribute);
		attributes.add(attribute);
		return attributes;
	}

	private CategoryTransfer getCategoryTransfer() {
		return CategoryTransfer.builder().id(3L).name("CategoryTransfer").attributes(getAttributesTransfer()).build();
	}

	private List<AttributeTransfer> getAttributesTransfer() {
		List<AttributeTransfer> attributes = new ArrayList<>();

		attributes.add(AttributeTransfer.builder().id(4L).name("AttributeTransfer").nullable(false)
				.type(AttributeType.DOUBLE).build());
		return attributes;
	}
}
