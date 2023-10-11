package br.com.apiauthjwt.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apiauthjwt.auth.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
