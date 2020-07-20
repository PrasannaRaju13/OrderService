package com.order.item.model;

import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemsDto {
	
	private int orderItemId;
	
	@NotBlank(message = "Product Code is mandatory")
	private String productCode;
	
	@NotBlank(message = "Product Name is mandatory")
	private String productName;
	
	private int quantity;
	

}
