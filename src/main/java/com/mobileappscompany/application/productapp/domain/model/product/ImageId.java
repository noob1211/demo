package com.mobileappscompany.application.productapp.domain.model.product;

import java.io.Serializable;

final public class ImageId implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rawId;

	public ImageId(String rawId) {
		this.setRawId(rawId);
	}

	private void setRawId(final String rawId) {
		this.rawId = rawId;
	}

	/**
	 * @return String representation of this image id.
	 */
	public String idString() {
		return this.rawId;
	}
}
