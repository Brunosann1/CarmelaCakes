package com.sann.carmelacakes.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.sann.carmelacakes.model.constants.CakeShape;
import com.sann.carmelacakes.model.constants.CakeSize;
import com.sann.carmelacakes.model.constants.DoughType;
import com.sann.carmelacakes.model.constants.FillingType;
import com.sann.carmelacakes.model.constants.Toppings;

@Entity
public class Cake {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Enumerated(EnumType.STRING)
	private DoughType dough;

	@NotBlank
	@Enumerated(EnumType.STRING)
	private FillingType filling;

	@NotBlank
	@Enumerated(EnumType.STRING)
	private CakeSize size;

	@NotBlank
	@Enumerated(EnumType.STRING)
	private CakeShape shape;

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Toppings.class)
	private List<Toppings> toppings;

	public Long getId() {
		return this.id;
	}

	public DoughType getDough() {
		return dough;
	}
	public void setDough(DoughType dough) {
		this.dough = dough;
	}
	public FillingType getFilling() {
		return filling;
	}
	public void setFilling(FillingType filling) {
		this.filling = filling;
	}
	public CakeSize getSize() {
		return size;
	}
	public void setSize(CakeSize size) {
		this.size = size;
	}
	public CakeShape getShape() {
		return shape;
	}
	public void setShape(CakeShape shape) {
		this.shape = shape;
	}
	public List<Toppings> getToppings() {
		return toppings;
	}
	public void setToppings(List<Toppings> toppings) {
		this.toppings = toppings;
	}
}
