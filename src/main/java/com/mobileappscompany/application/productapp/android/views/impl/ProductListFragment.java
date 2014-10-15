package com.mobileappscompany.application.productapp.android.views.impl;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobileappscompany.application.productapp.domain.model.product.Product;

public class ProductListFragment extends ListFragment {

	private static final String TAG = "MainActivity";

	ProductListFragmentDelegate delegate;
	private ArrayAdapter<Product> aa;
	private List<Product> theProducts;

	ProductListFragment(List<Product> products, ProductListFragmentDelegate delegate) {
		this.theProducts = products;
		this.delegate = delegate;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Product theProduct = (Product) l.getAdapter().getItem(position);
		delegate.onProductListItemClick(theProduct);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		aa = new ProductAdapter(getActivity(), theProducts);
		aa.setNotifyOnChange(true);
		setListAdapter(aa);
		return super.onCreateView(inflater, container, savedInstanceState);

	}
	
	public void refresh(List<Product> products) {
		this.theProducts.clear();
		this.theProducts.addAll(products);
		this.aa.notifyDataSetChanged();
	}
}
