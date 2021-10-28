package com.shalini.verma.model;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Products")
public class Products {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="pk_product_id")
  private Integer productId;
  @Column(name="unique_id")
  private String uniqueId;
  @Column(name="retail_price")
  private Integer retailPrice;
  @Column(name="discounted_price")
  private Integer discountedPrice;
  @Column(name="images")
  private String imagesList;
  @Column(name="description",length = 1500)
  private String description;
  @Column(name="product_rating")
  private Float productRating;
  @Column(name="overall_rating")
  private Float overallRating;
  @Column(name="brand")
  private String brand;
  @Column(name="product_name")
  private String productName;
  
  
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getImagesList() {
	return imagesList;
}
public void setImagesList(String imagesList) {
	this.imagesList = imagesList;
}
public Integer getProductId() {
	return productId;
}
public void setProductId(Integer productId) {
	this.productId = productId;
}
public String getUniqueId() {
	return uniqueId;
}
public void setUniqueId(String uniqueId) {
	this.uniqueId = uniqueId;
}
public Integer getRetailPrice() {
	return retailPrice;
}
public void setRetailPrice(Integer retailPrice) {
	this.retailPrice = retailPrice;
}
public Integer getDiscountedPrice() {
	return discountedPrice;
}
public void setDiscountedPrice(Integer discountedPrice) {
	this.discountedPrice = discountedPrice;
}

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Float getProductRating() {
	return productRating;
}
public void setProductRating(Float productRating) {
	this.productRating = productRating;
}
public Float getOverallRating() {
	return overallRating;
}
public void setOverallRating(Float overallRating) {
	this.overallRating = overallRating;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
  
}
