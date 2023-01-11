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
import javax.validation.constraints.NotNull;

import com.sann.carmelacakes.model.constants.OrderStatus;

@Entity
public class CakeOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	private Cake cake;

	@NotNull
	@ManyToOne
	private Customer customer;

	@NotNull
	private Boolean isDelivery;

	@NotNull
	private BigDecimal price;

	@NotNull
	private LocalDate orderDate = LocalDate.now();

	@NotNull
	private LocalDate deliveryDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public CakeOrder(Cake cake, Customer customer, Boolean isDelivery, BigDecimal price, LocalDate deliveryDate,
			OrderStatus status) {
		this.cake = cake;
		this.customer = customer;
		this.isDelivery = isDelivery;
		this.price = price;
		this.deliveryDate = deliveryDate;
		this.status = status;
	}

	public CakeOrder() {
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

	public LocalDate getOrderDate() {
		return orderDate;
	}
}
