package com.rss.platform.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_object")
public class DataObject {
    @Id
    private String data_objectUID;
    private String objectID;
    private String objectName;
    private String objectType;
    private String appID;
    private String appName;
    private String moduleID;
    private String moduleName;
    private String operationMap;
    private String referTable;
    private String attributes;
    private String uniqueFields;
    private String conditionFields;
    private String sortFields;
    private String listFields;
    private String editFields;
    private String childObjects;
    private String lastModifyUser;
    private Date lastModifyDate;

    public String getData_objectUID() {
        return data_objectUID;
    }

    public void setData_objectUID(String data_objectUID) {
        this.data_objectUID = data_objectUID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperationMap() {
        return operationMap;
    }

    public void setOperationMap(String operationMap) {
        this.operationMap = operationMap;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getReferTable() {
        return referTable;
    }

    public void setReferTable(String referTable) {
        this.referTable = referTable;
    }

    public String getUniqueFields() {
        return uniqueFields;
    }

    public void setUniqueFields(String uniqueFields) {
        this.uniqueFields = uniqueFields;
    }

    public String getConditionFields() {
        return conditionFields;
    }

    public void setConditionFields(String conditionFields) {
        this.conditionFields = conditionFields;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getSortFields() {
        return sortFields;
    }

    public void setSortFields(String sortFields) {
        this.sortFields = sortFields;
    }

    public String getListFields() {
        return listFields;
    }

    public void setListFields(String listFields) {
        this.listFields = listFields;
    }

    public String getEditFields() {
        return editFields;
    }

    public void setEditFields(String editFields) {
        this.editFields = editFields;
    }

    public String getChildObjects() {
        return childObjects;
    }

    public void setChildObjects(String childObjects) {
        this.childObjects = childObjects;
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

    public boolean hasPopupField() {
        JSONArray arrAttribute = JSONArray.fromObject(attributes);
        for (int index = 0; index < arrAttribute.size(); index++) {
            JSONObject attribute = arrAttribute.getJSONObject(index);
            if (attribute.getString("displayType").equals("弹出编辑"))
                return true;
        }
        return false;
    }

    public List<String> getUniqueList() {
        ArrayList<String> list = new ArrayList<>();
        if (uniqueFields != null) {
            for (String unique : uniqueFields.split(";"))
                list.add(unique);
        }
        return list;
    }
}
