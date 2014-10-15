package com.mobileappscompany.application.productapp.infrastructure.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mobileappscompany.application.productapp.domain.model.product.ProductId;

public class ProductIdTest {

	private static final String ID = "SOME_ID";
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testEquals() {
		ProductId pid1 = new ProductId(ID);
		ProductId pid2 = new ProductId(ID);
		
		boolean shouldBeEqual =  (pid1.equals(pid2)) ? true : false;
		assertTrue("product ids  should be same", shouldBeEqual);
	}
	
}
