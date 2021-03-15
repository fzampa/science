package br.com.lab.model;

import br.com.lab.constant.AttributeType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttributeTransfer {

	private Long id;
	private AttributeType type;
	private String name;
	private Boolean nullable;

}
