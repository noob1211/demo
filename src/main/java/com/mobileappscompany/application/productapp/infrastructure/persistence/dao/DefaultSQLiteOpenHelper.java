package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DefaultSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mobileappscompanydemoapp.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE_PRODUCT_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCT(ID TEXT PRIMARY KEY, NAME TEXT, DESCRIPTION TEXT, REGULAR_PRICE TEXT, SALE_PRICE TEXT, IMAGE_ID TEXT, CRT_DT TEXT, UP_DT TEXT DEFAULT NULL);";

	private static final String DATABASE_CREATE_PRODUCT_COLOR_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCT_COLOR(PRODUCT_ID TEXT REFERENCES PRODUCT(ID) ON DELETE CASCADE, NAME TEXT NOT NULL);";
	private static final String DATABASE_CREATE_PRODUCT_STORE_TABLE = "CREATE TABLE IF NOT EXISTS PRODUCT_STORE(PRODUCT_ID TEXT REFERENCES PRODUCT(ID) ON DELETE CASCADE, NAME TEXT NOT NULL);";

	public DefaultSQLiteOpenHelper(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_PRODUCT_TABLE);
		db.execSQL(DATABASE_CREATE_PRODUCT_COLOR_TABLE);
		db.execSQL(DATABASE_CREATE_PRODUCT_STORE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do nothing...
	}

}
