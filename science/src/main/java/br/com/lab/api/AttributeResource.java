package br.com.lab.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lab.constant.AttributeType;
import br.com.lab.model.AttributeTransfer;

@RestController
@RequestMapping(value="/attributes")
public class AttributeResource {
	
	@GetMapping("/types")
	public List<AttributeType> getAttributeTypes(){
		return Arrays.asList(AttributeType.values());
	}
	
	@GetMapping("/category/{categoryId}")
	public List<AttributeTransfer> getAttributesByCategory(@PathVariable Long categoryId){
		return null;
	}
}
