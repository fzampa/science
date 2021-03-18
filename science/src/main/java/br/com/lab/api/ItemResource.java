package br.com.lab.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.lab.model.ErrorTransfer;
import br.com.lab.model.ItemTransfer;
import br.com.lab.service.ItemService;

@RestController
@RequestMapping(value = "/items")
public class ItemResource {

	@Autowired
	private ItemService service;

	@PostMapping
	public ItemTransfer addItem(@RequestBody ItemTransfer itemTransfer) {
		try {
			return service.persistItem(itemTransfer);
		} catch (Exception e) {
			ErrorTransfer errorTransfer = ErrorTransfer.builder()
					.errorMessage("Error while persisting item: " + e.getMessage()).build();
			return ItemTransfer.builder().error(errorTransfer).build();
		}
	}
	
	@GetMapping("/category/{categoryId}")
	public List<ItemTransfer> getAllItemsForCategory(@PathVariable Long categoryId){
		return service.getAllItemsForCategory(categoryId);
	}
}