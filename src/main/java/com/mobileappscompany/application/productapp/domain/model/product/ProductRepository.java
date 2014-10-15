package com.mobileappscompany.application.productapp.domain.model.product;

import java.util.List;

/**
 * Provides an abstraction for our persistence 
 */
public interface ProductRepository {

	/**
	 * Finds a product by id.
	 * 
	 * @param productId
	 * @return Product
	 */
	Product find(ProductId productId);
	
	/**
	 * Finds all products.
	 * @return
	 */
	List<Product> findAll();
	
	/**
	 * Saves a given product. 
	 * @param Product aProduct
	 */
	void store(Product aProduct);
	
	/**
	 * Removes a given product. 
	 * @param Product aProduct
	 */
	void remove(Product aProduct);
	
	/**
	 * A unique, generated productId.
	 * 
	 * @return ProductId
	 */
	ProductId nextProductId();
}
