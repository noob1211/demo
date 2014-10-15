package com.mobileappscompany.application.productapp.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mobileappscompany.application.productapp.domain.model.product.Color;
import com.mobileappscompany.application.productapp.domain.model.product.Product;
import com.mobileappscompany.application.productapp.domain.model.product.ProductRepository;
import com.mobileappscompany.application.productapp.domain.model.product.Store;
import com.mobileappscompany.application.productapp.infrastructure.persistence.InMemoryProductRepository;

public class DomainRegistry {

	private static ProductRepository productRepository;
	private static final DomainRegistry instance = new DomainRegistry();

	private DomainRegistry() {
		// singleton
	}

	public static DomainRegistry instance() {
		return instance;
	}

	public ProductRepository productRepository() {
		return DomainRegistry.lazyInitProductRepository();
	}
	
	private static final ProductRepository lazyInitProductRepository()
	{
		if(productRepository == null) {
			productRepository = new InMemoryProductRepository();
			DomainRegistry.addFakeProducts(productRepository);
		}
		
		return productRepository;
	}

	/**
	 * Initialize the repository with the 3 fake products once
	 */
	private static final void addFakeProducts(ProductRepository repo) {
		List<Store> stores1 = new ArrayList<Store>();
		stores1.add(new Store("store1"));
		List<Store> stores2 = new ArrayList<Store>();
		stores2.add(new Store("store2"));
		List<Store> stores3 = new ArrayList<Store>();
		stores3.add(new Store("store3"));

		Product product1 = new Product(repo.nextProductId(), "sample name one",
				"sample description one", "10.00", "5.00", null, new Color[] {
						new Color("blue"), new Color("green"),
						new Color("yellow") }, stores1, new Date(), null);
		Product product2 = new Product(repo.nextProductId(), "sample name two",
				"sample description two", "20.00", "10.00", null, new Color[] {
						new Color("blue"), new Color("green"),
						new Color("yellow") }, stores2, new Date(), null);
		Product product3 = new Product(repo.nextProductId(),
				"sample name three", "sample description  three", "30.00",
				"15.00", null, new Color[] { new Color("blue"),
						new Color("green"), new Color("yellow") }, stores3,
				new Date(), null);
		Product product4 = new Product(repo.nextProductId(),
				"sample name four", "sample description  four", "30.00",
				"15.00", null, new Color[] { new Color("blue"),
						new Color("red"), new Color("yellow") }, stores3,
				new Date(), null);
		Product product5 = new Product(repo.nextProductId(),
				"sample name five", "sample description  five", "30.00",
				"15.00", null, new Color[] { new Color("blue"),
						new Color("purple"), new Color("yellow") }, stores3,
				new Date(), null);
		Product product6 = new Product(repo.nextProductId(),
				"sample name six", "sample description  six", "30.00",
				"15.00", null, new Color[] { new Color("blue"),
						new Color("pink"), new Color("yellow") }, stores3,
				new Date(), null);
		Product product7 = new Product(repo.nextProductId(),
				"sample name seven", "sample description  seven", "30.00",
				"15.00", null, new Color[] { new Color("blue"),
						new Color("orange"), new Color("yellow") }, stores3,
				new Date(), null);
		
		repo.store(product1);
		repo.store(product2);
		repo.store(product3);
		repo.store(product4);
		repo.store(product5);
		repo.store(product6);
		repo.store(product7);

	}
}
