package com.mobileappscompany.application.productapp.android.controllers.responsemodels;

import com.mobileappscompany.application.productapp.domain.model.product.Product;

public class ShowProductResponseModel {

	private boolean isSuccessful;
	private String errMsg;
	private Product theProductToShow;

	public ShowProductResponseModel(boolean isSuccessful, String errMsg,
			Product theProductToShow) {
		super();
		this.isSuccessful = isSuccessful;
		this.errMsg = errMsg;
		this.theProductToShow = theProductToShow;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public String errMsg() {
		return errMsg;
	}

	public Product getTheProductToShow() {
		return theProductToShow;
	}
	

}
