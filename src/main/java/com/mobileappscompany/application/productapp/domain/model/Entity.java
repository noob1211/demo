package com.mobileappscompany.application.productapp.domain.model;

public abstract class Entity<IdClazz> {

	public Entity() {
		super();
	}
	
	public abstract IdClazz getId();
	
}
