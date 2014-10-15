package com.mobileappscompany.application.productapp.android.views.impl;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.mobileappscompany.application.productapp.domain.model.product.Color;
import com.mobileappscompany.application.productapp.domain.model.product.Colors;
import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.Store;
import com.mobileappscompany.application.productapp.domain.model.product.Stores;

public class ModifyProductActivity extends CreateProductActivity {

	public static final String EXTRA_OLD_PRODUCT = "old_product";
	
	public static final String EXTRA_PRODUCT_NAME = "product_name";
	public static final String EXTRA_PRODUCT_DESCRIPTION = "product_description";
	public static final String EXTRA_PRODUCT_REGULAR_PRICE = "product_regular_price";
	public static final String EXTRA_PRODUCT_SALE_PRICE = "product_sale_price";
	public static final String EXTRA_PRODUCT_COLORS = "product_colors";
	public static final String EXTRA_PRODUCT_STORES = "product_stores";
	
	//read-only
	private Product theProductToModify;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		theProductToModify = (Product) getIntent()
				.getSerializableExtra(MainActivity.PRODUCT_TO_MODIFY_EXTRA);
		
		this.setName(theProductToModify.getName());
		this.setDescription(theProductToModify.getDescription());
		this.setRegularPrice(theProductToModify.getRegularPrice());
		this.setSalePrice(theProductToModify.getSalePrice());
		this.setColorsChecked(theProductToModify);
		this.setStoresChecked(theProductToModify);
		
		this.setOnClickListeners();
	}
	
	private void setOnClickListeners() {
		this.onClickForSubmit();
	}

	private void onClickForSubmit() {
		this.getSubmitButton().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final ProgressDialog pd = ProgressDialog.show(
						ModifyProductActivity.this, "Please Wait", "modifying product please wait...");

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						Intent result = ModifyProductActivity.this.createResultForModify();
						setResult(RESULT_OK, result);
						pd.dismiss();
						ModifyProductActivity.this.finish();
					}
				}, 2000);
			}
		});
	}
	
	private Intent createResultForModify() {
		Intent data = this.createResult();
		data.putExtra(EXTRA_OLD_PRODUCT, theProductToModify);
		return data;
	}

	private void setColorsChecked(Product theProduct) {
		List<Color> colors = theProduct.availableColors();
		if (colors.contains(new Color(Colors.RED.toString())))
			this.setRedChecked(true);

		if (colors.contains(new Color(Colors.BLUE.toString())))
			this.setBlueChecked(true);

		if (colors.contains(new Color(Colors.YELLOW.toString())))
			this.setYellowChecked(true);

		if (colors.contains(new Color(Colors.GREEN.toString())))
			this.setGreenChecked(true);
	}
	
	private void setStoresChecked(Product theProduct) {
		List<Store> stores = theProduct.availableStores();
		
		if (stores.contains(new Store(Stores.KOHLS.toString())))
			this.setKohlsChecked(true);
		
		if (stores.contains(new Store(Stores.WALMART.toString())))
			this.setWalmartChecked(true);
		
		if (stores.contains(new Store(Stores.TARGET.toString())))
			this.setTargetChecked(true);
		
		if (stores.contains(new Store(Stores.TJMAX.toString())))
			this.setTJMaxChecked(true);
		
	}

	
	
	
	
	
	
	
	
	
	
}
