package br.com.apiauthjwt.auth.domain.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

import org.hibernate.annotations.GenericGenerator;

@Table(name = "product")
@Entity(name = "product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	
	private String id;
	private String name;
	private Integer price;
	
	public Product(ProductRequestDTO data) {
		this.price = data.price();
		this.name = data.name();
		
	}

	public String getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	

	public Integer getPrice() {
		return price;
	}

	

	
}

