package com.mobileappscompany.application.productapp.android.controllers.requestmodels;

import java.util.Date;

/**
 * Immutable struct
 */
public class CreateProductRequestModel {
	private final String productName;
	private final String productDescription;
	private final String regularPrice;
	private final String salePrice;
	private final String[] colors;
	private final String[] stores;
	private final Date createdOn;
	private final Date updatedOn;

	public CreateProductRequestModel(String productName,
			String productDescription, String regularPrice, String salePrice,
			String[] colors, String[] stores, Date createdOn, Date updatedOn) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.regularPrice = regularPrice;
		this.salePrice = salePrice;
		this.colors = colors;
		this.stores = stores;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	
	

	public Date getCreatedOn() {
		return createdOn;
	}



	public Date getUpdatedOn() {
		return updatedOn;
	}



	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getRegularPrice() {
		return regularPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public String[] getColors() {
		return colors;
	}

	public String[] getStores() {
		return stores;
	}

	@Override
	public String toString() {
		return "product name: ".concat(this.productName)
				.concat(" color count: ")
				.concat(String.valueOf(this.colors.length))
				.concat(" store count: ")
				.concat(String.valueOf(this.stores.length));
	}

}
