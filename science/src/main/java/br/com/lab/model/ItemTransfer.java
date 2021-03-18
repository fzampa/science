package br.com.lab.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemTransfer {
	
	private AttributeTransfer attribute;
	
	private Object value;
	
	private ErrorTransfer error;
}
