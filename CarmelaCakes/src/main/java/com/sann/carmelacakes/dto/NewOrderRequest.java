package com.sann.carmelacakes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.sann.carmelacakes.model.Cake;
import com.sann.carmelacakes.model.Customer;
import com.sann.carmelacakes.model.CustomerOrder;
import com.sann.carmelacakes.model.constants.OrderStatus;

public class NewOrderRequest {

	@NotBlank
	private Cake cake;
	
	@NotBlank
	private Customer customer;
	
	@NotBlank
	private Boolean isDelivery;
	
	@NotBlank
	private BigDecimal price;
	
	@NotBlank
	private LocalDate deliveryDate;
	
	@NotBlank
	private OrderStatus status;

	
	public Cake getCake() {
		return cake;
	}
	public void setCake(Cake cake) {
		this.cake = cake;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Boolean getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(Boolean isDelivery) {
		this.isDelivery = isDelivery;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}


	public CustomerOrder toCustomerOrder() {		
		return new CustomerOrder(cake, customer, isDelivery, price, deliveryDate, status);
	}
}
