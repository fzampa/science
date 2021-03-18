package br.com.lab.entity.typeditems;

import javax.persistence.Entity;

import br.com.lab.entity.ItemValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class BooleanItemValue extends ItemValue<Boolean>{
	
	private Boolean value;

}
