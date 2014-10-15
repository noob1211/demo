package com.mobileappscompany.application.productapp.android.controllers.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mobileappscompany.application.productapp.android.controllers.AbstractController;
import com.mobileappscompany.application.productapp.android.controllers.IObserveViewLifecycle;
import com.mobileappscompany.application.productapp.android.controllers.MainController;
import com.mobileappscompany.application.productapp.android.controllers.ResponseHandler;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.CreateProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.DeleteProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.ShowProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.UpdateProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.CreateProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.DeleteProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.ShowProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.UpdateProductResponseModel;
import com.mobileappscompany.application.productapp.android.views.MainView;
import com.mobileappscompany.application.productapp.domain.model.product.Color;
import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.ProductId;
import com.mobileappscompany.application.productapp.domain.model.product.Store;

public class MainControllerImpl extends AbstractController implements MainController, IObserveViewLifecycle {

	private static final String TAG = "MainControllerImpl";
	private MainView view;
	
	public MainControllerImpl(MainView view) {
		super();
		this.view = view;
	}
	
	@Override
	public void showProduct(ShowProductRequestModel request, ResponseHandler callback) {
		Product product = this.getDomainRegistry().productRepository().find(new ProductId(request.getProductId()));
		if(product != null )
			callback.onProductShow(new ShowProductResponseModel(true, null, product));
		else
			callback.onProductShow(new ShowProductResponseModel(false, "could not find product.", null));
	}

	@Override
	public void createProduct(CreateProductRequestModel request,
			ResponseHandler callback) {
		try{
			ProductId id = this.getDomainRegistry().productRepository().nextProductId();
			Date createDate = (request.getCreatedOn() != null)? request.getCreatedOn() : new Date();
			Date updatedOn = (request.getUpdatedOn() != null) ? request.getUpdatedOn() : null;
			Product theNewProduct = new Product(id,request.getProductName(), request.getProductDescription(), request.getRegularPrice(),
					request.getSalePrice(), null, this.createColorsFromArray(request.getColors()), this.createStoresFromArray(request.getStores()), createDate, updatedOn);
			
			this.getDomainRegistry().productRepository().store(theNewProduct);
			this.view.showProducts(this.getDomainRegistry().productRepository().findAll());
			
			callback.onProductCreated(new CreateProductResponseModel(true, id.idString(), null, new Date()));

		} catch(Exception ex){
			ex.printStackTrace();
			callback.onProductCreated(new CreateProductResponseModel(false, null, ex.getMessage(), null));
		}
		
		
	}
	
	private Color[] createColorsFromArray(String[] arr)
	{
		List<Color> colors = new ArrayList<Color>();
		for(int i = 0; i < arr.length; i++)
		{
			colors.add(new Color(arr[i]));
		}
		
		return colors.toArray(new Color[colors.size()]);
	}
	
	private List<Store> createStoresFromArray(String[] arr)
	{
		List<Store> stores = new ArrayList<Store>();
		for(int i = 0; i < arr.length; i++)
		{
			stores.add(new Store(arr[i]));
		}
		return stores;
	}

	@Override
	public void updateProduct(final UpdateProductRequestModel requestToModifyProduct, final ResponseHandler callback) {
	
		final Product product = this.getDomainRegistry().productRepository().find(new ProductId(requestToModifyProduct.getOldProductId()));
		
		if(product == null)
			callback.onProductUpdate(new UpdateProductResponseModel(false,null, "could not find product.", null));
		
		this.deleteProduct(new DeleteProductRequestModel(product.getId().idString()), new ResponseHandler() {

			@Override
			public void onProductDeleted(DeleteProductResponseModel response) {
				super.onProductDeleted(response);
				
				if(!response.isSuccessful())
					callback.onProductUpdate(new UpdateProductResponseModel(false,null, "could not update product.", null));
				else {
					CreateProductRequestModel request = new CreateProductRequestModel(requestToModifyProduct.getNewProductName(), requestToModifyProduct.getNewProductDescription(), 
							requestToModifyProduct.getNewRegularPrice(), requestToModifyProduct.getNewSalePrice(), requestToModifyProduct.getNewColors(),
							requestToModifyProduct.getNewStores(), product.getCreateDate(), new Date());
					
					MainControllerImpl.this.createProduct(request, new ResponseHandler() {

						@Override
						public void onProductCreated(
								CreateProductResponseModel response) {
							super.onProductCreated(response);
							
							if(response.isSuccessful())
								callback.onProductUpdate(new UpdateProductResponseModel(true, response.getProductID(), null, new Date()));
							else
								callback.onProductUpdate(new UpdateProductResponseModel(false,null, "could update product.", null));
						}
						
					});
				}

			}
			
		});

		
	}

	@Override
	public void deleteProduct(DeleteProductRequestModel request,
			ResponseHandler callback) {
		Product product = this.getDomainRegistry().productRepository().find(new ProductId(request.getProductId()));
		if(product != null ) {
			this.getDomainRegistry().productRepository().remove(product);
			this.view.showProducts(this.getDomainRegistry().productRepository().findAll());
			callback.onProductDeleted(new DeleteProductResponseModel(true, product.getId().idString(),new Date(), null));
		}
		else
			callback.onProductShow(new ShowProductResponseModel(false, "could not find product.", null));
		
	}

	@Override
	public void onViewCreate() {
		Toast.makeText((Context) view, "main view created", Toast.LENGTH_SHORT).show();
		view.showProducts(this.getDomainRegistry().productRepository().findAll());
		
	}

	@Override
	public void onViewStop() {
		Toast.makeText((Context) view, "main view stopped", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onViewDestroy() {
		Toast.makeText((Context) view, "main view destroyed", Toast.LENGTH_SHORT).show();
		
	}

}
