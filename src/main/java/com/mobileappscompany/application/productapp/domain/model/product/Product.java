package com.mobileappscompany.application.productapp.domain.model.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.mobileappscompany.application.productapp.domain.model.Entity;


public class Product extends Entity<ProductId> implements Serializable {
	private static final long serialVersionUID = 1L;
	private ProductId id;
	private String name;
	private String description;
	private String regularPrice;
	private String salePrice;
	private ImageId imageId;
	private Color[] colors;
	private Date createDate;
	private Date updateDate;
	private List<Store> stores;
	
	public Product(ProductId id, String name, String description,
			String regularPrice, String salePrice, ImageId imageId,
			Color[] colors, List<Store> stores, Date createDate, Date updateDate) {
		super();
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setRegularPrice(regularPrice);
		this.setSalePrice(salePrice);
		this.setImageId(imageId);
		this.setColors(colors);
		this.setCreateDate(createDate);
		this.setUpdateDate(updateDate);
		this.setStores(stores);
	}

	private void setId(ProductId id) {
		this.id = id;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private void setRegularPrice(String regularPrice) {
		this.regularPrice = regularPrice;
	}

	private void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	private void setImageId(ImageId imageId) {
		this.imageId = imageId;
	}

	private void setColors(Color[] colors) {
		this.colors = colors;
	}

	private void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	private void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	private void setStores(List<Store> stores) {
		this.stores = stores;
	}

	@Override
	public ProductId getId() {
		return this.id;
	}
	
	public List<Color> availableColors() {
		return Arrays.asList(this.colors);
	}
	
	public List<Store> availableStores() {
		return new ArrayList<Store>(this.stores);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getRegularPrice() {
		return regularPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public ImageId getImageId() {
		return imageId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	
}
