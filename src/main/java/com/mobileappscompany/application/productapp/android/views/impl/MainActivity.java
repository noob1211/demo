package com.mobileappscompany.application.productapp.android.views.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.mobileappscompany.R;
import com.mobileappscompany.application.productapp.android.controllers.ResponseHandler;
import com.mobileappscompany.application.productapp.android.controllers.impl.MainControllerImpl;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.CreateProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.DeleteProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.ShowProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.requestmodels.UpdateProductRequestModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.CreateProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.DeleteProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.ShowProductResponseModel;
import com.mobileappscompany.application.productapp.android.controllers.responsemodels.UpdateProductResponseModel;
import com.mobileappscompany.application.productapp.android.views.MainView;
import com.mobileappscompany.application.productapp.domain.model.product.Product;

public class MainActivity extends ActionBarActivity implements MainView, ProductListFragmentDelegate {

	private static final String TAG = "MainActivity";
	
	private static final int CREATE_PRODUCT_REQUEST = 1;  // The request code for creating a product
	private static final int SHOW_PRODUCT_REQUEST = 2;  // The request code for showing a product
	private static final int MODIFY_PRODUCT_REQUEST = 3;  // The request code for modify

	
	public static final String PRODUCT_DETAILS_EXTRA = "product_extra";  // the product to show
	public static final String PRODUCT_TO_MODIFY_EXTRA = "product_modify_extra";  // the product to modify


	MainControllerImpl mainController = new MainControllerImpl(this);
	ProductListFragment productListFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new ProductListFragment(new ArrayList<Product>(), this)).commit();
		}

		this.setOnClickListeners();
		mainController.onViewCreate();
	}

	private void setOnClickListeners() {
		this.setOnClickListenerForCreateProduct();
		this.setOnClickListenerForModifyProduct();
		this.setOnClickListenerForRemoveProduct();
	}

	private void setOnClickListenerForCreateProduct() {
		this.findViewById(R.id.btnCreate).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						MainActivity.this.showCreateProductScreenForProductInfo();
					}
				});
	}
	
	private void showCreateProductScreenForProductInfo()
	{
		Intent i = new Intent(this, CreateProductActivity.class);
		startActivityForResult(i, MainActivity.CREATE_PRODUCT_REQUEST);
		
	}

	private void setOnClickListenerForModifyProduct() {

		this.findViewById(R.id.btnUpdate).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						//do nothing..

					}
				});
	}

	private void setOnClickListenerForRemoveProduct() {
		this.findViewById(R.id.btnDelete).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						//do nothing...
					}
				});
	}

	@Override
	protected void onStop() {
		super.onStop();
		mainController.onViewStop();
	}

	@Override
	protected void onDestroy() {
		super.onStop();
		mainController.onViewDestroy();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch(requestCode)
		{
			case MainActivity.CREATE_PRODUCT_REQUEST:
				this.handleCreateProductActivityResponse(resultCode, data);
				break;
			case MainActivity.SHOW_PRODUCT_REQUEST:
				this.handleShowProductActivityResponse(resultCode, data);
				break;
			case MainActivity.MODIFY_PRODUCT_REQUEST:
				this.handleModifyProductActivityResponse(resultCode, data);
				break;
		}
	}
	
	private void handleModifyProductActivityResponse(int resultCode, Intent data) 
	{
		if(resultCode == RESULT_OK)
		{
			Product oldProduct = (Product) data.getSerializableExtra(ModifyProductActivity.EXTRA_OLD_PRODUCT);
			String poductName = data.getStringExtra(ModifyProductActivity.EXTRA_PRODUCT_NAME);
			String poductDescription = data.getStringExtra(ModifyProductActivity.EXTRA_PRODUCT_DESCRIPTION);
			String poductRegularPrice = data.getStringExtra(ModifyProductActivity.EXTRA_PRODUCT_REGULAR_PRICE);
			String poductSalePrice = data.getStringExtra(ModifyProductActivity.EXTRA_PRODUCT_SALE_PRICE);
			String[] colors = (String[]) data.getSerializableExtra(ModifyProductActivity.EXTRA_PRODUCT_COLORS);
			String[] stores = (String[]) data.getSerializableExtra(ModifyProductActivity.EXTRA_PRODUCT_STORES);
			
			UpdateProductRequestModel request = new UpdateProductRequestModel(oldProduct.getId().idString(),
					poductName, poductDescription, poductRegularPrice, poductSalePrice, colors, stores);
			
			this.mainController.updateProduct(request, new ResponseHandler() {

				@Override
				public void onProductUpdate(UpdateProductResponseModel response) {
					super.onProductUpdate(response);
					
					if(response.isSuccessful())
						Toast.makeText(MainActivity.this, "successfully updated product!", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(MainActivity.this, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
				}
				
			});
		}
	}
	
	private void handleShowProductActivityResponse(int resultCode, Intent data)
	{
		if(resultCode == RESULT_OK) 
		{
			boolean shouldDelete = data.getBooleanExtra(ProductDetailsActivity.EXTRA_SHOULD_DELETE, false);
			
			boolean shouldModify = data.getBooleanExtra(ProductDetailsActivity.EXTRA_SHOULD_MODIFY, false);

			if(shouldDelete)
				this.handleDelete(data);
			
			if(shouldModify)
				this.handleModify(data);
			
			
		} 

	}
	
	private void handleDelete(Intent data)
	{
		DeleteProductRequestModel request = this.createDeleteRequestModel(data);
		this.mainController.deleteProduct(request, new ResponseHandler() {

			@Override
			public void onProductDeleted(DeleteProductResponseModel response) {
				super.onProductDeleted(response);
				
				if(!response.isSuccessful())
					Toast.makeText(MainActivity.this, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
				else {
					Toast.makeText(MainActivity.this, "successfully deleted product with id ".concat(response.getProductID()), Toast.LENGTH_SHORT).show();
				}

			}
			
		});
	}
	
	private DeleteProductRequestModel createDeleteRequestModel(Intent data)
	{
		Product theProductToDelete = (Product) data.getSerializableExtra(ProductDetailsActivity.EXTRA_PRODUCT);
		return new DeleteProductRequestModel(theProductToDelete.getId().idString());
	}
	
	/**
	 * Do this when the user decides he should modify a product 
	 */
	private void handleModify(Intent data)
	{
		Product theProductToModify = (Product) data.getSerializableExtra(ProductDetailsActivity.EXTRA_PRODUCT);
		Intent i = new Intent(this, ModifyProductActivity.class);
		i.putExtra(PRODUCT_TO_MODIFY_EXTRA, theProductToModify);
		startActivityForResult(i, MODIFY_PRODUCT_REQUEST);
	}
	
	
	private void handleCreateProductActivityResponse(int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK) 
		{
			CreateProductRequestModel request = this.createRequestModel(data);
			this.mainController.createProduct(request, new ResponseHandler() {

				@Override
				public void onProductCreated(CreateProductResponseModel response) {
					super.onProductCreated(response);
					if(response.isSuccessful())
						Toast.makeText(MainActivity.this, "successfully created product", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(MainActivity.this, response.getErrorMsg(), Toast.LENGTH_SHORT).show();
				}
				
			});
		}
		else
			Toast.makeText(this, "product not created", Toast.LENGTH_SHORT).show();
			
	}
	
	private CreateProductRequestModel createRequestModel(Intent data)
	{
		String productName = data.getStringExtra(CreateProductActivity.EXTRA_PRODUCT_NAME);
		String productDescription = data.getStringExtra(CreateProductActivity.EXTRA_PRODUCT_DESCRIPTION);
		String regularPrice = data.getStringExtra(CreateProductActivity.EXTRA_PRODUCT_REGULAR_PRICE);
		String salePrice = data.getStringExtra(CreateProductActivity.EXTRA_PRODUCT_SALE_PRICE);
		String[] colors = data.getStringArrayExtra(CreateProductActivity.EXTRA_PRODUCT_COLORS);
		String[] stores = data.getStringArrayExtra(CreateProductActivity.EXTRA_PRODUCT_STORES);
		
		return new CreateProductRequestModel(productName, productDescription, regularPrice, salePrice, colors, stores, null, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void showProducts(List<Product> products) {
		if(productListFragment == null) {
			productListFragment = new ProductListFragment(products, this);
			getSupportFragmentManager().beginTransaction().add(R.id.container,  productListFragment).commit();

		} else
			productListFragment.refresh(products);
	}

	@Override
	public void onProductListItemClick(Product theProductSelected) {
		
		mainController.showProduct(new ShowProductRequestModel(theProductSelected.getId().idString()), new ResponseHandler() {

			@Override
			public void onProductShow(ShowProductResponseModel response) {
				super.onProductShow(response);
				
				if(response.isSuccessful()) {
					MainActivity.this.showProductDetails(response.getTheProductToShow());
				}
				else
					Toast.makeText(MainActivity.this, response.errMsg(), Toast.LENGTH_SHORT).show();
			}
			
		});
	}
	
	private void showProductDetails(Product theProduct)
	{
		Intent i = new Intent(this, ProductDetailsActivity.class);
		i.putExtra(PRODUCT_DETAILS_EXTRA, theProduct);
		startActivityForResult(i, MainActivity.SHOW_PRODUCT_REQUEST);
	}


}
