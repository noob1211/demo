package com.mobileappscompany.application.productapp.android.controllers;

import com.mobileappscompany.application.productapp.android.controllers.requestmodels.CreateProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.DeleteProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.ShowProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.UpdateProductRequestModel;

/**
 * Handles input from the view, and returns a response.
 * In the event of a response, the user __MAY__ be redirected to another view.
 * 
 */
public interface MainController {
	
	public void showProduct(ShowProductRequestModel request, ResponseHandler callback);

	public void createProduct(CreateProductRequestModel request, ResponseHandler callback);

	public void updateProduct(UpdateProductRequestModel request, ResponseHandler callback);

	public void deleteProduct(DeleteProductRequestModel request, ResponseHandler callback);
}
