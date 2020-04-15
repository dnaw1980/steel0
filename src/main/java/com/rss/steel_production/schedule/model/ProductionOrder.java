package com.rss.steel_production.schedule.model;

import java.util.Date;
import javax.persistence.*;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_order")
public class ProductionOrder {
	@Id
	private String product_orderUID;
	private String orderNo;
	private Date orderDate;
	private String productCode;
	private String productName;
	private String productType;
	private String steelGrade;
	private String productSpec;
	private String productLevel;
	private String technicalStandard;
	private Double billetLength;
	private Double billetWidth;
	private Double billetThick;
	private Double targetWeight;
	private Double targetQuantity;
	private Date targetTime;
	private String preparedStaff;
	private Date preparedTime;
	private String technicalLeader;
	private Date technicalTime;
	private String productLeader;
	private Date productionTime;
	private String releaseStaff;
	private Date releaseTime;
	private String processRoute;
	private String orderStatus;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getProduct_orderUID() {
		return product_orderUID;
	}
	public void setProduct_orderUID(String product_orderUID) {
		this.product_orderUID = product_orderUID;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getSteelGrade() {
		return steelGrade;
	}
	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductLevel() {
		return productLevel;
	}
	public void setProductLevel(String productLevel) {
		this.productLevel = productLevel;
	}
	public String getTechnicalStandard() {
		return technicalStandard;
	}
	public void setTechnicalStandard(String technicalStandard) {
		this.technicalStandard = technicalStandard;
	}
	public Double getBilletLength() {
		return billetLength;
	}
	public void setBilletLength(Double billetLength) {
		this.billetLength = billetLength;
	}
	public Double getBilletWidth() {
		return billetWidth;
	}
	public void setBilletWidth(Double billetWidth) {
		this.billetWidth = billetWidth;
	}
	public Double getBilletThick() {
		return billetThick;
	}
	public void setBilletThick(Double billetThick) {
		this.billetThick = billetThick;
	}
	public Double getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(Double targetWeight) {
		this.targetWeight = targetWeight;
	}
	public Double getTargetQuantity() {
		return targetQuantity;
	}
	public void setTargetQuantity(Double targetQuantity) {
		this.targetQuantity = targetQuantity;
	}
	public Date getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(Date targetTime) {
		this.targetTime = targetTime;
	}
	public String getPreparedStaff() {
		return preparedStaff;
	}
	public void setPreparedStaff(String preparedStaff) {
		this.preparedStaff = preparedStaff;
	}
	public Date getPreparedTime() {
		return preparedTime;
	}
	public void setPreparedTime(Date preparedTime) {
		this.preparedTime = preparedTime;
	}
	public String getTechnicalLeader() {
		return technicalLeader;
	}
	public void setTechnicalLeader(String technicalLeader) {
		this.technicalLeader = technicalLeader;
	}
	public Date getTechnicalTime() {
		return technicalTime;
	}
	public void setTechnicalTime(Date technicalTime) {
		this.technicalTime = technicalTime;
	}
	public String getProductLeader() {
		return productLeader;
	}
	public void setProductLeader(String productLeader) {
		this.productLeader = productLeader;
	}
	public Date getProductionTime() {
		return productionTime;
	}
	public void setProductionTime(Date productionTime) {
		this.productionTime = productionTime;
	}
	public String getReleaseStaff() {
		return releaseStaff;
	}
	public void setReleaseStaff(String releaseStaff) {
		this.releaseStaff = releaseStaff;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getProcessRoute() {
		return processRoute;
	}
	public void setProcessRoute(String processRoute) {
		this.processRoute = processRoute;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getLastModifyUser() {
		return lastModifyUser;
	}
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	}

