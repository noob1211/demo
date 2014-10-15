package com.mobileappscompany.application.productapp.infrastructure.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.ProductId;
import com.mobileappscompany.application.productapp.domain.model.product.ProductRepository;


public class InMemoryProductRepository implements ProductRepository {

	private final HashMap<String, Product> products;

	public InMemoryProductRepository() {
		super();
		this.products = new LinkedHashMap<String, Product>();
	}

	@Override
	public Product find(ProductId productId) {
		return this.products.get(productId.idString());
	}

	@Override
	public List<Product> findAll() {
		return new ArrayList<Product>(products.values());
	}

	@Override
	public void store(Product aProduct) {
		if(aProduct != null)
			this.products.put(aProduct.getId().idString(), aProduct);
		else
			throw new IllegalArgumentException("product cannot be null");
	}

	@Override
	public ProductId nextProductId() {
		return new ProductId(UUID.randomUUID().toString());
	}

	@Override
	public void remove(Product aProduct) {
		if(aProduct != null)
			this.products.remove(aProduct.getId().idString());
	}

}
