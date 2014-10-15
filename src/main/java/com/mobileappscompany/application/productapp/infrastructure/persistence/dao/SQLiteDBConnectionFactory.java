package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import android.content.Context;

public class SQLiteDBConnectionFactory implements DBConnectionFactory {

	private Context context;
	
	/**
	 * @param Context context should be an application scoped context
	 */
	SQLiteDBConnectionFactory(Context context) {
		this.context = context;
	}
	
	@Override
	public DBConnection createConnection() {
		return new SQLiteDBConnection(new DefaultSQLiteOpenHelper(context));
	}



}
