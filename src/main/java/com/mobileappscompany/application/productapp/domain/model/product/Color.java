package com.mobileappscompany.application.productapp.domain.model.product;

import java.io.Serializable;

/**
 * A product is available in multiple colors.
 */
final public class Color implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;

	public Color(final String name) {
		this.setName(name);
	}

	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @return String representation of this color.
	 */
	public String nameString() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		
		if (o == null || getClass() != o.getClass())
			return false;
		
		return ((Color) o).nameString().equalsIgnoreCase(this.nameString());
	}
	
	

}
