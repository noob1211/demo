package com.mobileappscompany.application.productapp.infrastructure.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.ProductId;
import com.mobileappscompany.application.productapp.infrastructure.persistence.InMemoryProductRepository;

public class InMemoryProductRepositoryTest {

	private InMemoryProductRepository repo;

	@Before
	public void setUp() throws Exception {
		this.repo = new InMemoryProductRepository();
	}

	@Test
	public void afterCreationNoProducts() {

		assertEquals(
				"repository should not contain any products after creation", 0,
				repo.findAll().size());
	}

	@Test
	public void testStoreAProduct() {
		Product theProduct = this.mockProduct();

		this.repo.store(theProduct);

		assertEquals("repository should contain only one product", 1, this.repo
				.findAll().size());
		assertEquals(theProduct, this.repo.find(theProduct.getId()));
	}

	@Test
	public void testStoreAProductTwice() {
		Product theProduct = this.mockProduct();

		this.repo.store(theProduct);
		this.repo.store(theProduct);

		assertEquals(
				"storing the same product twice should not create duplicates in the repository",
				1, this.repo.findAll().size());
		assertEquals(theProduct, this.repo.find(theProduct.getId()));
	}

	@Test
	public void testRemoveAProduct() {
		Product theProduct = this.mockProduct();
		
		this.repo.store(theProduct);
		this.repo.remove(theProduct);
		
		assertEquals(0, this.repo.findAll().size());
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionForStoringNull() {
		
		this.repo.store(null);
	}
	
	private Product mockProduct()
	{
		Product theProduct = Mockito.mock(Product.class);
		ProductId theProductId = new ProductId("ANY_UNIQUE_ID");
		Mockito.when(theProduct.getId()).thenReturn(theProductId);
		
		return theProduct;
	}

}
