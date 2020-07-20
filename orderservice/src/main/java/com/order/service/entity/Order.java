package com.order.service.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.order.service.constants.DbConstants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name=DbConstants.TABLE_ORDER)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name=DbConstants.COLUMN_ORDER_ID)
	private Integer orderId;
	
	@Column(name=DbConstants.COLUMN_CUSTOMER_NAME)
	private String customerName;
	
	@Column(name=DbConstants.COLUMN_ORDER_DATE)
	private Date orderDate;
	
	@Column(name=DbConstants.COLUMN_SHIPPING_ADDRESS)
	private String shippingAddress;
	
	@Column(name=DbConstants.COLUMN_TOTAL_PRIE)
	private double total;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<OrderServiceItem> orderServiceItem;

}
