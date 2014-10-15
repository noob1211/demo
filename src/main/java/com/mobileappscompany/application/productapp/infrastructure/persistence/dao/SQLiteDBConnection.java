package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteDBConnection implements DBConnection {

	private SQLiteDatabase dbconnection;
	private DefaultSQLiteOpenHelper helper;
	
	SQLiteDBConnection(DefaultSQLiteOpenHelper helper) {
		this.helper = helper;
	}
	
	/**
	 * Begins a database transaction.
	 */
	@Override
	public void beginTransaction() {
		if(dbconnection == null)
		{
			this.openConnection();
			dbconnection.beginTransaction();

		} else
			throw new IllegalStateException("cannot begin a transaction. a transation is already open.");
		
	}

	/**
	 * 
	 * @return boolean true if commit succeeds, false otherwise.
	 */
	@Override
	public boolean commit() {
		if(dbconnection == null)
			return false;
		
		if(!dbconnection.inTransaction())
			return false;
		
		dbconnection.setTransactionSuccessful();
		dbconnection.endTransaction();
		this.closeConnection();
		return true;
	}

	@Override
	public void rollback()  throws IllegalStateException {
		if(dbconnection == null)
			throw new NullPointerException("connection not open");
		
		if(!dbconnection.inTransaction())
			throw new IllegalStateException("could not perform rollback. no transactions.");

		dbconnection.endTransaction();
		this.closeConnection();
		
	}

	/**
	 * Runs the query with the provided arguments.
	 */
	@Override
	public Cursor execute(String sql, String[] args) throws SQLException {
		boolean connectionNotAlreadyOpen = dbconnection != null && !dbconnection.isOpen();
		if(connectionNotAlreadyOpen) {
			this.openConnection();
		}
		
		return this.dbconnection.rawQuery(sql, args);
	}
	
	/**
	 * Open the database connection.
	 */
	private void openConnection() {
		if(dbconnection == null) {
			dbconnection = helper.getWritableDatabase();
		}
		else
			throw new IllegalStateException("cannot open a connection. connection is already open.");
	}
	
	/**
	 * Closes the database connection
	 */
	private void closeConnection() {
		if(dbconnection != null && dbconnection.isOpen())
		{
			dbconnection.close();
			dbconnection = null;
		} else
			throw new IllegalStateException("failed to close db connection.");
			
	}

}
