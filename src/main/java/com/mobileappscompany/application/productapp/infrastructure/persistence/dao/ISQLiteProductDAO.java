package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.ProductId;

/**
 * Provides CRUD operation for SQLite database shipped with android-sdk
 */
public interface ISQLiteProductDAO {

	/**
	 * 
	 * @param Product aProduct
	 *            
	 * @return boolean true on success false otherwise
	 */
	public boolean insertProduct(Product aProduct);

	/**
	 * 
	 * @param Product aProduct
	 *            
	 * @return boolean true on success false otherwise
	 */
	public boolean deleteProduct(ProductId anId);

	/**
	 * 
	 * @param ProductId aProductId
	 *            
	 * @return boolean true on success false otherwise
	 */
	public Product findProduct(ProductId anId);

	/**
	 * 
	 * @param Product aProduct
	 *            
	 * @return boolean true on success false otherwise
	 */
	public boolean updateProduct(Product aProduct);
}
