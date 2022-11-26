package com.sann.carmelacakes.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.sann.carmelacakes.model.constants.OrderStatus;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Cake cake;

	@ManyToOne
	private Customer customer;

	@NotBlank
	private Boolean isDelivery;

	@NotBlank
	private BigDecimal price;

	private LocalDate orderDate = LocalDate.now();

	@NotBlank
	private LocalDate deliveryDate;

	@NotBlank
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public CustomerOrder(Cake cake, Customer client, Boolean isDelivery, BigDecimal price, LocalDate deliveryDate,
			OrderStatus status) {
		this.cake = cake;
		this.customer = client;
		this.isDelivery = isDelivery;
		this.price = price;
		this.deliveryDate = deliveryDate;
		this.status = status;
	}

	public CustomerOrder() {
	}

	public Long getId() {
		return id;
	}

	public Cake getCake() {
		return cake;
	}

	public void setCake(Cake cake) {
		this.cake = cake;
	}

	public Customer getClient() {
		return customer;
	}

	public void setClient(Customer customer) {
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

	public LocalDate getOrderDate() {
		return orderDate;
	}
}
