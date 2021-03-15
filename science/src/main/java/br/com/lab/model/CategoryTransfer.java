package br.com.lab.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryTransfer {

	private Long id;
	private String name;
	private List<AttributeTransfer> attributes;

}
