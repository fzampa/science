package br.com.lab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lab.entity.Attribute;
import br.com.lab.entity.Item;
import br.com.lab.exception.NoAttributeFoundException;
import br.com.lab.model.ItemTransfer;
import br.com.lab.repository.AttributeRepository;
import br.com.lab.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private Transformer transformer;

	@Autowired
	private ItemRepository repository;

	@Autowired
	private AttributeRepository attributeRepository;

	public ItemTransfer persistItem(ItemTransfer itemTransfer) {
		Item item = transformer.transformItemFromTransfer(itemTransfer);

		Optional<Attribute> maybeAttribute = attributeRepository.findById(item.getAttribute().getId());

		if (maybeAttribute.isEmpty()) {
			throw new NoAttributeFoundException(item.getAttribute().getId().toString());
		}

		item.setAttribute(maybeAttribute.get());
		return transformer.transformItemFromEntity(repository.save(item));
	}

	public List<ItemTransfer> getAllItemsForCategory(Long categoryId) {
		List<Item> items = repository.getItemsByCategoryId(categoryId);
		return transformer.transformItemsFromEntity(items);
	}
}
