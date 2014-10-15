package com.mobileappscompany.application.productapp.android.controllers.requestmodels;


public class ShowProductRequestModel 
{
	private String productId;

	public ShowProductRequestModel(String productId) {
		super();
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
	
}
