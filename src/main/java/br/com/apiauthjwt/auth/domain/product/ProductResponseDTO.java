package br.com.apiauthjwt.auth.domain.product;

import br.com.apiauthjwt.auth.domain.product.Product;

public record ProductResponseDTO(String id, String name, Integer price) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
