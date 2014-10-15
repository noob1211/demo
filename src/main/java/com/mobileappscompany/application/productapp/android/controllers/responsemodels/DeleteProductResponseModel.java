package com.mobileappscompany.application.productapp.android.controllers.responsemodels;

import java.util.Date;

public class DeleteProductResponseModel {

	private boolean successful;

	/**
	 * the product id for the product that was deleted
	 */
	private String productID;
	private Date deletedOn;
	private String errorMsg;

	public DeleteProductResponseModel(boolean successful, String productID,
			Date createdOn, String errorMsg) {
		super();
		this.successful = successful;
		this.productID = productID;
		this.deletedOn = createdOn;
		this.errorMsg = errorMsg;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public String getProductID() {
		return productID;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
