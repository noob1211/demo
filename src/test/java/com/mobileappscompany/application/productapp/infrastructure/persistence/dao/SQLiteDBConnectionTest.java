package com.mobileappscompany.application.productapp.infrastructure.persistence.dao;

import android.test.AndroidTestCase;

public class SQLiteDBConnectionTest extends AndroidTestCase {

	private static final String TAG = "SQLiteDBConnectionTest";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testBeginningTransactionMulitpleTimeBeforeCommitShouldThrowIllegalState()
	{
		
		DefaultSQLiteOpenHelper helper = new DefaultSQLiteOpenHelper(getContext());
		
		SQLiteDBConnection connection = new SQLiteDBConnection(helper);
		
		try {
			connection.beginTransaction();
			connection.beginTransaction();
			fail("should have thrown an exception");
		} catch (IllegalStateException ex) {
			//passes
		}
	}


}
