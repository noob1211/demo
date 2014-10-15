package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.mobileappscompany.application.productapp.domain.model.product.Color;
import com.mobileappscompany.application.productapp.domain.model.product.ImageId;
import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.ProductId;
import com.mobileappscompany.application.productapp.domain.model.product.Store;

public class SQLiteProductDAO implements ISQLiteProductDAO {

	private static final String CREATE_PRODUCT = "INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, REGULAR_PRICE, SALE_PRICE, IMAGE_ID, CRT_DT, UP_DT) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_PRODUCT = "DELETE FROM PRODUCT WHERE ID=?";
	private static final String FIND_PRODUCT = "SELECT * FROM PRODUCT WHERE ID=?";
	private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET NAME=?, DESCRIPTION=?, REGULAR_PRICE=?, SALE_PRICE=?, IMAGE_ID=?, CRT_DT=?, UP_DT=? WHERE ID=?";
	
	private static final String CREATE_COLOR = "INSERT INTO PRODUCT_COLOR(PRODUCT_ID, NAME) VALUES(?,?)";
	private static final String UPDATE_COLOR = "UPDATE PRODUCT_COLOR SET NAME=? WHERE PRODUCT_ID=?";
	private static final String FIND_COLOR = "SELECT (NAME) FROM PRODUCT_COLOR WHERE PRODUCT_ID=?";
	private static final String DELETE_COLOR = "DELETE FROM PRODUCT_COLOR WHERE PRODUCT_ID=?";
	
	private static final String CREATE_STORE = "INSERT INTO PRODOCT_STORE(PRODUCT_ID, NAME) VALUES(?,?)";
	private static final String UPDATE_STORE = "UPDATE PRODUCT_STORE SET NAME=?, WHERE PRODUCT_ID=?";
	private static final String FIND_STORE = "SELECT (NAME) FROM PRODUCT_STORE WHERE PRODUCT_ID=?";
	private static final String DELETE_STORE = "DELETE FROM PRODUCT_STORE WHERE PRODUCT_ID=?";
	
	private static final int KEY_PROCUDT_ID = 0;
	private static final int KEY_PROCUDT_NAME = 1;
	private static final int KEY_PROCUDT_DESCRIPTION = 2;
	private static final int KEY_PROCUDT_REGULAR_PRICE = 3;
	private static final int KEY_PROCUDT_SALE_PRICE = 4;
	private static final int KEY_PROCUDT_IMAGE_ID = 5;
	private static final int KEY_PRODUCT_CRT_DT = 6;
	private static final int KEY_PRODUCT_UP_DT = 7;
	
	private static final int KEY_COLOR_NAME = 0;
	private static final int KEY_STORE_NAME = 0;
	



	private DBConnection connection;
	
	public SQLiteProductDAO(DBConnection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insertProduct(Product aProduct) {
		connection.beginTransaction();
		try {
			//save product
			connection.execute(CREATE_PRODUCT, new String[] {aProduct.getId().toString(), aProduct.getName(), aProduct.getDescription(), aProduct.getRegularPrice(),
					aProduct.getSalePrice(), aProduct.getImageId().toString(), String.valueOf(new Date().getTime()), String.valueOf((Object) aProduct.getUpdateDate().getTime())});
			
			//save colors
			for(Color anAvailableColor : aProduct.availableColors())
				connection.execute(CREATE_COLOR, new String[] {aProduct.getId().toString(), anAvailableColor.nameString()});
			
			//save stores
			for(Store aStore : aProduct.availableStores())
				connection.execute(CREATE_STORE, new String[] {aProduct.getId().toString(), aStore.nameString()});
			
			return connection.commit();
			
		} catch(SQLException ex) {
			connection.rollback();
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProduct(ProductId productId) {
		try {
			connection.execute(DELETE_PRODUCT, new String[] {productId.toString()});
			return true;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Product findProduct(ProductId anId) {
		try {
			Cursor theCursor = connection.execute(FIND_PRODUCT, new String[] {anId.toString()});
			if(connection.commit())
				return new Product(
						new ProductId(theCursor.getString(KEY_PROCUDT_ID)),
						theCursor.getString(KEY_PROCUDT_NAME),
						theCursor.getString(KEY_PROCUDT_DESCRIPTION),
						theCursor.getString(KEY_PROCUDT_REGULAR_PRICE),
						theCursor.getString(KEY_PROCUDT_SALE_PRICE),
						new ImageId(theCursor.getString(KEY_PROCUDT_IMAGE_ID)),
						this.findColorsForProduct(anId),
						this.findStoresForProduct(anId),
						new Date(Long.valueOf(theCursor.getString(KEY_PRODUCT_CRT_DT))),
						new Date(Long.valueOf(theCursor.getString(KEY_PRODUCT_UP_DT))));
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private Color[] findColorsForProduct(ProductId productId) 
	{
		List<Color> colors = new ArrayList<Color>();
		try {
			Cursor theCursor = connection.execute(FIND_COLOR, new String[] {productId.toString()});
			boolean cursorNotEmpty = theCursor.moveToNext();
			while(cursorNotEmpty) 
			{
				colors.add(new Color(theCursor.getString(KEY_COLOR_NAME)));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return colors.toArray(new Color[colors.size()]);
	}
	
	private List<Store> findStoresForProduct(ProductId productId)
	{
		List<Store> stores = new ArrayList<Store>();
		try {
			Cursor theCursor = connection.execute(FIND_STORE, new String[] {productId.toString()});
			boolean cursorNotEmpty = theCursor.moveToNext();
			while(cursorNotEmpty) 
			{
				stores.add(new Store(theCursor.getString(KEY_STORE_NAME)));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return stores;
	}

	@Override
	public boolean updateProduct(Product aProduct) {
		
		if(this.deleteProduct(aProduct.getId())) return this.insertProduct(aProduct);
		else
			return false;
		
		
	}

}
