package com.mobileappscompany.application.productapp.android.controllers;

import com.mobileappscompany.application.productapp.domain.model.DomainRegistry;

public abstract class AbstractController {

	protected DomainRegistry getDomainRegistry() {
		return DomainRegistry.instance();
	}
	
	
}
