package com.mobileappscompany.application.productapp.domain.model.product;

import java.io.Serializable;

final public class ProductId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rawId;

	public ProductId(final String rawId) {
		this.setRawId(rawId);
	}

	private void setRawId(String rawId) {
		this.rawId = rawId;
	}

	/**
	 * @return String representation of this product id.
	 */
	public String idString() {
		return this.rawId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		return this.idString().equalsIgnoreCase(((ProductId) o).idString());
	}

	@Override
	public String toString() {
		return this.idString();
	}

}
