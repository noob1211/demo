package com.mobileappscompany.application.productapp.android.controllers.requestmodels;

public class UpdateProductRequestModel {

	private final String oldProductId;
	
	private final String newProductName;
	private final String newProductDescription;
	private final String newRegularPrice;
	private final String newSalePrice;
	private final String[] newColors;
	private final String[] newStores;
	
	public UpdateProductRequestModel(String oldProductId,
			String newProductName, String newProductDescription,
			String newRegularPrice, String newSalePrice, String[] newColors,
			String[] newStores) {
		super();
		this.oldProductId = oldProductId;
		this.newProductName = newProductName;
		this.newProductDescription = newProductDescription;
		this.newRegularPrice = newRegularPrice;
		this.newSalePrice = newSalePrice;
		this.newColors = newColors;
		this.newStores = newStores;
	}

	public String getOldProductId() {
		return oldProductId;
	}

	public String getNewProductName() {
		return newProductName;
	}

	public String getNewProductDescription() {
		return newProductDescription;
	}

	public String getNewRegularPrice() {
		return newRegularPrice;
	}

	public String getNewSalePrice() {
		return newSalePrice;
	}

	public String[] getNewColors() {
		return newColors;
	}

	public String[] getNewStores() {
		return newStores;
	}
	
	
}
