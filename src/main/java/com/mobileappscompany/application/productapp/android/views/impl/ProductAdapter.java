package com.mobileappscompany.application.productapp.android.views.impl;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileappscompany.R;
import com.mobileappscompany.application.productapp.domain.model.product.Product;

public class ProductAdapter extends ArrayAdapter<Product> {

	private static final String TAG = "ProductAdapter";

	private Context context;

	public ProductAdapter(Context context, List<Product> products) {
		super(context, R.layout.list_item, products);
		this.context = context;
	}

	class MyViewHolder {
		ImageView image;
		TextView description;
		TextView title;

		MyViewHolder(View v) {
			image = (ImageView) v.findViewById(R.id.listImage);
			title = (TextView) v.findViewById(R.id.listTitle);
			description = (TextView) v.findViewById(R.id.listDescription);

		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyViewHolder viewHolder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, null);
			viewHolder = new MyViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MyViewHolder) convertView.getTag();
		}

		Product theProduct = getItem(position);

		this.setImage(viewHolder, theProduct);
		this.setTitle(viewHolder, theProduct);
		this.setDescription(viewHolder, theProduct);

		return convertView;
	}

	private void setImage(MyViewHolder viewHolder, Product theProduct) {
		viewHolder.image.setImageResource(R.drawable.logo_mobile_apps);
	}

	private void setTitle(MyViewHolder viewHolder, Product theProduct) {
		viewHolder.title.setText(theProduct.getName());

	}

	private void setDescription(MyViewHolder viewHolder, Product theProduct) {
		String desc = theProduct.getDescription();
		String upTo100Chars = desc.substring(0, Math.min(desc.length(), 100));
		if (upTo100Chars.length() == 100) {
			viewHolder.description.setText(upTo100Chars + "...");
		} else
			viewHolder.description.setText(upTo100Chars);
	}

}