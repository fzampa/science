package br.com.lab.entity.typeditems;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.com.lab.entity.ItemValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class DateTimeItemValue extends ItemValue<LocalDateTime>{
	
	private LocalDateTime value;

}