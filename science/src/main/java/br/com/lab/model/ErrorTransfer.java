package br.com.lab.model;

import liquibase.pro.packaged.B;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorTransfer {
	
	private String errorMessage;

}
