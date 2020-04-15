package com.rss.steel_production.foundation.model;

import java.util.Date;
import javax.persistence.*;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "steel_product")
public class SteelProduct {
	@Id
	private String steel_productUID;
	private String productCode;
	private String productName;
	private String productType;
	private String productDesc;
	private String productSpec;
	private String processPurpose;
	private String deliverState;
	private String techCondiiton;
	private String interControl;
	private String materialState;
	private String productProperty;
	private String techStandard;
	private String steelGrade;
	private String gradeType;
	private String processType;
	private String smeltMethod;
	private String techTerm;
	private String processAttach;
	private String processRoute;
	private String lastModifyUser;
	private Date lastModifyDate;
	public String getSteel_productUID() {
		return steel_productUID;
	}
	public void setSteel_productUID(String steel_productUID) {
		this.steel_productUID = steel_productUID;
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
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public String getProcessPurpose() {
		return processPurpose;
	}
	public void setProcessPurpose(String processPurpose) {
		this.processPurpose = processPurpose;
	}
	public String getDeliverState() {
		return deliverState;
	}
	public void setDeliverState(String deliverState) {
		this.deliverState = deliverState;
	}
	public String getTechCondiiton() {
		return techCondiiton;
	}
	public void setTechCondiiton(String techCondiiton) {
		this.techCondiiton = techCondiiton;
	}
	public String getInterControl() {
		return interControl;
	}
	public void setInterControl(String interControl) {
		this.interControl = interControl;
	}
	public String getMaterialState() {
		return materialState;
	}
	public void setMaterialState(String materialState) {
		this.materialState = materialState;
	}
	public String getProductProperty() {
		return productProperty;
	}
	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}
	public String getTechStandard() {
		return techStandard;
	}
	public void setTechStandard(String techStandard) {
		this.techStandard = techStandard;
	}
	public String getSteelGrade() {
		return steelGrade;
	}
	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade;
	}
	public String getGradeType() {
		return gradeType;
	}
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getSmeltMethod() {
		return smeltMethod;
	}
	public void setSmeltMethod(String smeltMethod) {
		this.smeltMethod = smeltMethod;
	}
	public String getTechTerm() {
		return techTerm;
	}
	public void setTechTerm(String techTerm) {
		this.techTerm = techTerm;
	}
	public String getProcessAttach() {
		return processAttach;
	}
	public void setProcessAttach(String processAttach) {
		this.processAttach = processAttach;
	}
	public String getProcessRoute() {
		return processRoute;
	}
	public void setProcessRoute(String processRoute) {
		this.processRoute = processRoute;
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

