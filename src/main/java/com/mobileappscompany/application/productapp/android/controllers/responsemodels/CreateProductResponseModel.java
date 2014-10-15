package com.mobileappscompany.application.productapp.android.controllers.responsemodels;

import java.util.Date;

public class CreateProductResponseModel {

	private boolean successful;
	private String productID;
	private Date createdOn;
	private String errorMsg;

	public CreateProductResponseModel(boolean successful, String productID,
			String errMsg, Date createdOn) {
		super();
		this.successful = successful;
		this.productID = productID;
		this.createdOn = createdOn;
		this.errorMsg = errMsg;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public String getProductID() {
		return productID;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
