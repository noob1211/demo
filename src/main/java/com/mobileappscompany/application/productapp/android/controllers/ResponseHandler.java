package com.mobileappscompany.application.productapp.android.controllers;

import com.mobileappscompany.application.productapp.android.controllers.responsemodels.CreateProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.DeleteProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.ShowProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.UpdateProductResponseModel;

public abstract class ResponseHandler {

	public void onProductCreated(CreateProductResponseModel response){}
	public void onProductDeleted(DeleteProductResponseModel response){}
	public void onProductShow(ShowProductResponseModel response){}
	public void onProductUpdate(UpdateProductResponseModel response){}

}
