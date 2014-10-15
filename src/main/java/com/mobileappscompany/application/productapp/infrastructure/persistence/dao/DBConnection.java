package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import android.database.Cursor;
import android.database.SQLException;

public interface DBConnection {
	
	/**
	 * Begins a transaction
	 */
	public void beginTransaction();
	
	/**
	 * 
	 * @return true if successful, false otherwise.
	 */
	public boolean commit();
	
	/**
	 * Rolls back the current transaction.
	 * @return true if successful, false otherwise.
	 */
	public void rollback();
	
	/**
	 * 
	 * the SQL statement to be executed. Multiple statements separated by semicolons are not supported.
	 * 
	 * @param String SQL
	 * @return Cursor
	 * @throw SQLException
	 */
	public Cursor execute(String sql, String[] args) throws SQLException;
}
