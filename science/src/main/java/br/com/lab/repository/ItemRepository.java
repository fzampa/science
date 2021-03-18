package br.com.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.lab.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

	@Query("select item from Item item inner join item.attribute attr inner join attr.category cat where cat.id =:categoryId")
	public List<Item> getItemsByCategoryId(Long categoryId);

}
