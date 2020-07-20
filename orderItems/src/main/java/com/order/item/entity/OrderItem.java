package com.order.item.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.order.item.constants.DbConstants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = DbConstants.TABLE_ORDER_ITEM)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 6498574018670240073L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=DbConstants.COLUMN_ORDER_ITEM_ID)
	private Integer orderItemId;
	
	@NotBlank(message = "Product Code is mandatory")
	@Column(name=DbConstants.COLUMN_PRODUCT_CODE)
	private String productCode;
	
	@NotBlank(message = "Product Name is mandatory")
	@Column(name=DbConstants.COLUMN_PRODUCT_NAME)
	private String productName;
	
	
	@Column(name=DbConstants.COLUMN_QUANTITY)
	private Integer quantity;
	

}
