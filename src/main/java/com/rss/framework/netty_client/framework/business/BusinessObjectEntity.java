package com.rss.framework.netty_client.framework.business;

import java.util.ArrayList;

/**********************************************************
 * [系统名]      RSS平台</br>
 * [包　名]		com.rss.framework.business</br>
 * [文件名]		BusinessObjectEntity.java</br>
 * [功　能]		存储数据库中数据的公共bean类
 * </br>
 *******************************************************</br>
 * REVISION      变更日期             变更人              变更内容</br>
 *******************************************************</br>
 * v1.00             2011-3-15           胡清河                创建</br>
 * v1.01             2013-6-28           祖佳宁                修改
 *
 ***********************************************************/
public class BusinessObjectEntity {
	/**
	 * 表名
	 */
	private String objectID;
	/**
	 * 请求后台的操作(Service类中的方法名)
	 */
	private String operation;
	/**
	 * 发送方
	 */
	private String sender;

	/**
	 * 消息类型
	 */
	private String type;

	/**
	 * 单页显示的数据条数
	 */
	private String pageSize = "-1";
	/**
	 * 页码
	 */
	private String pageNo = "1";
	/**
	 * 数据总条数
	 */
	private String totalRecords = "0";
	/**
	 * 总页数
	 */
	private String totalPages = "0";
	/**
	 * 排序条件
	 */
	private String orderBy;

	private boolean isSaveLog = false;

	/**
	 * 主表数据
	 */
	private ArrayList<BusinessEntityAttribute> attributeList;
	/**
	 * 子表数据
	 */
	private ArrayList<BusinessObjectEntity> childList;
	/**
	 * 唯一性检查字段列表
	 */
	private ArrayList<String> uniqueList;

	public BusinessObjectEntity() {
		attributeList = new ArrayList<BusinessEntityAttribute>();
		uniqueList = new ArrayList<String>();
	}

	public ArrayList<BusinessEntityAttribute> getAttributeList() {
		return attributeList;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public void addAttribute(BusinessEntityAttribute attr) {
		attributeList.add(attr);
	}
	public void addAttribute(String attributeID,Object attributeValue) {
		BusinessEntityAttribute attr=new BusinessEntityAttribute();
		attr.setAttributeID(attributeID);
		attr.setAttributeValue(attributeValue);
		attributeList.add(attr);
	}


	public void setAttribute(BusinessEntityAttribute attr) {
		for (int index=0;index<attributeList.size();index++){
			BusinessEntityAttribute oldAttr=attributeList.get(index);
			if (oldAttr.getAttributeID().equals(attr.getAttributeID())){
				attributeList.set(index, attr);
				return;
			}

		}
		attributeList.add(attr);
	}

	public void setAttribute(int index, BusinessEntityAttribute attr) {
		attributeList.set(index, attr);
	}


	public void reSetAttribute(String attrID,String attrValue){

		BusinessEntityAttribute attribute = new BusinessEntityAttribute();
		attribute.setAttributeID(attrID);
		attribute.setAttributeValue(attrValue);
		this.setAttribute(attribute);

	}

	public ArrayList<BusinessObjectEntity> getChildList() {
		return childList;
	}

	public void addChild(BusinessObjectEntity child) {
		if (childList == null)
			childList = new ArrayList<BusinessObjectEntity>();
		childList.add(child);
	}

	public void addChildList(ArrayList<BusinessObjectEntity> newChildList) {
		if (childList == null)
			childList = new ArrayList<BusinessObjectEntity>();
		childList.addAll(newChildList);
	}

	public void setChild(int index, BusinessObjectEntity child) {
		if (childList == null)
			childList = new ArrayList<BusinessObjectEntity>();
		childList.set(index, child);
	}

	public ArrayList<String> getUniqueList() {
		return uniqueList;
	}

	public void addUnique(String unique) {
		if (uniqueList == null) {
			uniqueList = new ArrayList<String>();
		}
		uniqueList.add(unique);
	}

	public void addUniqueList(ArrayList<String> newUniqueList) {
		if (uniqueList == null) {
			uniqueList = new ArrayList<String>();
		}
		uniqueList.addAll(newUniqueList);
	}

	public void setUnique(int index, String unique) {
		if (uniqueList == null) {
			uniqueList = new ArrayList<String>();
		}
		uniqueList.set(index, unique);
	}

	public Object getAttributeValue(String attrID) {
		for (BusinessEntityAttribute attr : attributeList) {
			if (attr.getAttributeID().equals(attrID)) {
				return attr.getAttributeValue();
			}
		}
		return null;
	}

	public void removeAttributeByID(String attrID) {
		if (attributeList.size() != 0) {
			for (BusinessEntityAttribute attr : attributeList) {
				if (attr.getAttributeID().equals(attrID)) {
					attributeList.remove(attr);
					break;
				}
			}
		}
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isSaveLog() {
		return isSaveLog;
	}

	public void setSaveLog(boolean isSaveLog) {
		this.isSaveLog = isSaveLog;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
