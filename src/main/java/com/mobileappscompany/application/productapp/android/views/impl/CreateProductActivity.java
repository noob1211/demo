package com.mobileappscompany.application.productapp.android.views.impl;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mobileappscompany.R;

public class CreateProductActivity extends ActionBarActivity {

	private static final String TAG = "CreateProductActivity";

	public static final String EXTRA_PRODUCT_NAME = "product_name";
	public static final String EXTRA_PRODUCT_DESCRIPTION = "product_description";
	public static final String EXTRA_PRODUCT_REGULAR_PRICE = "product_regular_price";
	public static final String EXTRA_PRODUCT_SALE_PRICE = "product_sale_price";
	public static final String EXTRA_PRODUCT_COLORS = "product_colors";
	public static final String EXTRA_PRODUCT_STORES = "product_stores";

	private EditText productName;
	private EditText productDescription;

	private EditText regularPrice;
	private EditText salePrice;

	private CheckBox redColor;
	private CheckBox blueColor;
	private CheckBox yellowColor;
	private CheckBox greenColor;

	private CheckBox kohlsStore;
	private CheckBox walmartStore;
	private CheckBox targetStore;
	private CheckBox tjmaxStore;

	private Button createButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_product);

		productName = (EditText) findViewById(R.id.editItemName);
		productDescription = (EditText) findViewById(R.id.editItemDescription);
		regularPrice = (EditText) findViewById(R.id.editRegularPrice);
		salePrice = (EditText) findViewById(R.id.editSalePrice);

		redColor = (CheckBox) findViewById(R.id.checkBox1);
		blueColor = (CheckBox) findViewById(R.id.checkBox2);
		yellowColor = (CheckBox) findViewById(R.id.checkBox3);
		greenColor = (CheckBox) findViewById(R.id.checkBox4);

		kohlsStore = (CheckBox) findViewById(R.id.checkBox5);
		walmartStore = (CheckBox) findViewById(R.id.checkBox6);
		targetStore = (CheckBox) findViewById(R.id.checkBox7);
		tjmaxStore = (CheckBox) findViewById(R.id.checkBox8);

		createButton = (Button) findViewById(R.id.btnSubmit);

		this.setOnClickListeners();
	}

	private void setOnClickListeners() {
		this.onClickForSubmit();
	}

	private void onClickForSubmit() {
		createButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final ProgressDialog pd = ProgressDialog.show(
						CreateProductActivity.this, "Please Wait",
						"product is processing please wait...");

				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						Intent result = CreateProductActivity.this
								.createResult();
						setResult(RESULT_OK, result);
						pd.dismiss();
						CreateProductActivity.this.finish();
					}
				}, 2000);
			}
		});
	}

	protected Intent createResult() {
		Intent data = new Intent();
		data.putExtra(EXTRA_PRODUCT_NAME, this.productName.getText().toString());
		data.putExtra(EXTRA_PRODUCT_DESCRIPTION, this.productDescription
				.getText().toString());
		data.putExtra(EXTRA_PRODUCT_REGULAR_PRICE, this.regularPrice.getText()
				.toString());
		data.putExtra(EXTRA_PRODUCT_SALE_PRICE, this.salePrice.getText()
				.toString());
		data.putExtra(EXTRA_PRODUCT_COLORS, this.colorsAvailable());
		data.putExtra(EXTRA_PRODUCT_STORES, this.storesAvailable());

		return data;
	}

	private String[] colorsAvailable() {
		List<String> colors = new ArrayList<>();

		if (redColor.isChecked())
			colors.add(redColor.getText().toString());

		if (blueColor.isChecked())
			colors.add(blueColor.getText().toString());

		if (yellowColor.isChecked())
			colors.add(yellowColor.getText().toString());

		if (greenColor.isChecked())
			colors.add(greenColor.getText().toString());

		return colors.toArray(new String[colors.size()]);
	}

	private String[] storesAvailable() {
		List<String> stores = new ArrayList<>();

		if (kohlsStore.isChecked())
			stores.add(kohlsStore.getText().toString());

		if (walmartStore.isChecked())
			stores.add(walmartStore.getText().toString());

		if (targetStore.isChecked())
			stores.add(targetStore.getText().toString());

		if (tjmaxStore.isChecked())
			stores.add(tjmaxStore.getText().toString());

		return stores.toArray(new String[stores.size()]);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.create_product, menu);
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

	protected void setName(String name) {
		this.productName.setText(name);
	}

	protected void setDescription(String desc) {
		this.productDescription.setText(desc);
	}

	protected void setRegularPrice(String regularPrice) {
		this.regularPrice.setText(regularPrice);
	}

	protected void setSalePrice(String salePrice) {
		this.salePrice.setText(salePrice);
	}

	protected void setRedChecked(boolean isChecked) {
		this.redColor.setChecked(isChecked);
	}

	protected void setBlueChecked(boolean isChecked) {
		this.blueColor.setChecked(isChecked);

	}

	protected void setYellowChecked(boolean isChecked) {
		this.yellowColor.setChecked(isChecked);

	}

	protected void setGreenChecked(boolean isChecked) {
		this.greenColor.setChecked(isChecked);

	}

	protected void setKohlsChecked(boolean isChecked) {
		this.kohlsStore.setChecked(isChecked);

	}

	protected void setWalmartChecked(boolean isChecked) {
		this.walmartStore.setChecked(isChecked);

	}

	protected void setTargetChecked(boolean isChecked) {
		this.targetStore.setChecked(isChecked);

	}

	protected void setTJMaxChecked(boolean isChecked) {
		this.tjmaxStore.setChecked(isChecked);

	}
	
	protected Button getSubmitButton() {
		return this.createButton;
	}

}
