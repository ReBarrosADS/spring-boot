package br.com.apiauthjwt.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apiauthjwt.auth.domain.product.Product;
import br.com.apiauthjwt.auth.domain.product.ProductRequestDTO;
import br.com.apiauthjwt.auth.domain.product.ProductResponseDTO;
import br.com.apiauthjwt.auth.repositories.ProductRepository;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductRepository repository;
	
	@PostMapping
	public ResponseEntity postproduct(@RequestBody @Valid ProductRequestDTO body) {
		Product newProduct = new Product(body);
		
		this.repository.save(newProduct);
		return ResponseEntity.ok().build();}
		
		
	@GetMapping
	public ResponseEntity getAllProducts() {
		List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();
		
		return ResponseEntity.ok(productList);
		
	}

}
