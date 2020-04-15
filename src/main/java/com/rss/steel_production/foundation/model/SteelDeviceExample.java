package com.rss.steel_production.foundation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SteelDeviceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SteelDeviceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSteelDeviceuidIsNull() {
            addCriterion("steel_deviceUID is null");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidIsNotNull() {
            addCriterion("steel_deviceUID is not null");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidEqualTo(String value) {
            addCriterion("steel_deviceUID =", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidNotEqualTo(String value) {
            addCriterion("steel_deviceUID <>", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidGreaterThan(String value) {
            addCriterion("steel_deviceUID >", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidGreaterThanOrEqualTo(String value) {
            addCriterion("steel_deviceUID >=", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidLessThan(String value) {
            addCriterion("steel_deviceUID <", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidLessThanOrEqualTo(String value) {
            addCriterion("steel_deviceUID <=", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidLike(String value) {
            addCriterion("steel_deviceUID like", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidNotLike(String value) {
            addCriterion("steel_deviceUID not like", value, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidIn(List<String> values) {
            addCriterion("steel_deviceUID in", values, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidNotIn(List<String> values) {
            addCriterion("steel_deviceUID not in", values, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidBetween(String value1, String value2) {
            addCriterion("steel_deviceUID between", value1, value2, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andSteelDeviceuidNotBetween(String value1, String value2) {
            addCriterion("steel_deviceUID not between", value1, value2, "steelDeviceuid");
            return (Criteria) this;
        }

        public Criteria andDevicenoIsNull() {
            addCriterion("deviceNo is null");
            return (Criteria) this;
        }

        public Criteria andDevicenoIsNotNull() {
            addCriterion("deviceNo is not null");
            return (Criteria) this;
        }

        public Criteria andDevicenoEqualTo(String value) {
            addCriterion("deviceNo =", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoNotEqualTo(String value) {
            addCriterion("deviceNo <>", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoGreaterThan(String value) {
            addCriterion("deviceNo >", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoGreaterThanOrEqualTo(String value) {
            addCriterion("deviceNo >=", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoLessThan(String value) {
            addCriterion("deviceNo <", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoLessThanOrEqualTo(String value) {
            addCriterion("deviceNo <=", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoLike(String value) {
            addCriterion("deviceNo like", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoNotLike(String value) {
            addCriterion("deviceNo not like", value, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoIn(List<String> values) {
            addCriterion("deviceNo in", values, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoNotIn(List<String> values) {
            addCriterion("deviceNo not in", values, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoBetween(String value1, String value2) {
            addCriterion("deviceNo between", value1, value2, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenoNotBetween(String value1, String value2) {
            addCriterion("deviceNo not between", value1, value2, "deviceno");
            return (Criteria) this;
        }

        public Criteria andDevicenameIsNull() {
            addCriterion("deviceName is null");
            return (Criteria) this;
        }

        public Criteria andDevicenameIsNotNull() {
            addCriterion("deviceName is not null");
            return (Criteria) this;
        }

        public Criteria andDevicenameEqualTo(String value) {
            addCriterion("deviceName =", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotEqualTo(String value) {
            addCriterion("deviceName <>", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameGreaterThan(String value) {
            addCriterion("deviceName >", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameGreaterThanOrEqualTo(String value) {
            addCriterion("deviceName >=", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLessThan(String value) {
            addCriterion("deviceName <", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLessThanOrEqualTo(String value) {
            addCriterion("deviceName <=", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameLike(String value) {
            addCriterion("deviceName like", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotLike(String value) {
            addCriterion("deviceName not like", value, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameIn(List<String> values) {
            addCriterion("deviceName in", values, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotIn(List<String> values) {
            addCriterion("deviceName not in", values, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameBetween(String value1, String value2) {
            addCriterion("deviceName between", value1, value2, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicenameNotBetween(String value1, String value2) {
            addCriterion("deviceName not between", value1, value2, "devicename");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNull() {
            addCriterion("deviceType is null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIsNotNull() {
            addCriterion("deviceType is not null");
            return (Criteria) this;
        }

        public Criteria andDevicetypeEqualTo(String value) {
            addCriterion("deviceType =", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotEqualTo(String value) {
            addCriterion("deviceType <>", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThan(String value) {
            addCriterion("deviceType >", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeGreaterThanOrEqualTo(String value) {
            addCriterion("deviceType >=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThan(String value) {
            addCriterion("deviceType <", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLessThanOrEqualTo(String value) {
            addCriterion("deviceType <=", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeLike(String value) {
            addCriterion("deviceType like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotLike(String value) {
            addCriterion("deviceType not like", value, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeIn(List<String> values) {
            addCriterion("deviceType in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotIn(List<String> values) {
            addCriterion("deviceType not in", values, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeBetween(String value1, String value2) {
            addCriterion("deviceType between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andDevicetypeNotBetween(String value1, String value2) {
            addCriterion("deviceType not between", value1, value2, "devicetype");
            return (Criteria) this;
        }

        public Criteria andStationnameIsNull() {
            addCriterion("stationName is null");
            return (Criteria) this;
        }

        public Criteria andStationnameIsNotNull() {
            addCriterion("stationName is not null");
            return (Criteria) this;
        }

        public Criteria andStationnameEqualTo(String value) {
            addCriterion("stationName =", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotEqualTo(String value) {
            addCriterion("stationName <>", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameGreaterThan(String value) {
            addCriterion("stationName >", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameGreaterThanOrEqualTo(String value) {
            addCriterion("stationName >=", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameLessThan(String value) {
            addCriterion("stationName <", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameLessThanOrEqualTo(String value) {
            addCriterion("stationName <=", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameLike(String value) {
            addCriterion("stationName like", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotLike(String value) {
            addCriterion("stationName not like", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameIn(List<String> values) {
            addCriterion("stationName in", values, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotIn(List<String> values) {
            addCriterion("stationName not in", values, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameBetween(String value1, String value2) {
            addCriterion("stationName between", value1, value2, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotBetween(String value1, String value2) {
            addCriterion("stationName not between", value1, value2, "stationname");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityIsNull() {
            addCriterion("processCapacity is null");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityIsNotNull() {
            addCriterion("processCapacity is not null");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityEqualTo(String value) {
            addCriterion("processCapacity =", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityNotEqualTo(String value) {
            addCriterion("processCapacity <>", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityGreaterThan(String value) {
            addCriterion("processCapacity >", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityGreaterThanOrEqualTo(String value) {
            addCriterion("processCapacity >=", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityLessThan(String value) {
            addCriterion("processCapacity <", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityLessThanOrEqualTo(String value) {
            addCriterion("processCapacity <=", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityLike(String value) {
            addCriterion("processCapacity like", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityNotLike(String value) {
            addCriterion("processCapacity not like", value, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityIn(List<String> values) {
            addCriterion("processCapacity in", values, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityNotIn(List<String> values) {
            addCriterion("processCapacity not in", values, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityBetween(String value1, String value2) {
            addCriterion("processCapacity between", value1, value2, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andProcesscapacityNotBetween(String value1, String value2) {
            addCriterion("processCapacity not between", value1, value2, "processcapacity");
            return (Criteria) this;
        }

        public Criteria andDevicestatusIsNull() {
            addCriterion("deviceStatus is null");
            return (Criteria) this;
        }

        public Criteria andDevicestatusIsNotNull() {
            addCriterion("deviceStatus is not null");
            return (Criteria) this;
        }

        public Criteria andDevicestatusEqualTo(String value) {
            addCriterion("deviceStatus =", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusNotEqualTo(String value) {
            addCriterion("deviceStatus <>", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusGreaterThan(String value) {
            addCriterion("deviceStatus >", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusGreaterThanOrEqualTo(String value) {
            addCriterion("deviceStatus >=", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusLessThan(String value) {
            addCriterion("deviceStatus <", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusLessThanOrEqualTo(String value) {
            addCriterion("deviceStatus <=", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusLike(String value) {
            addCriterion("deviceStatus like", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusNotLike(String value) {
            addCriterion("deviceStatus not like", value, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusIn(List<String> values) {
            addCriterion("deviceStatus in", values, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusNotIn(List<String> values) {
            addCriterion("deviceStatus not in", values, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusBetween(String value1, String value2) {
            addCriterion("deviceStatus between", value1, value2, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andDevicestatusNotBetween(String value1, String value2) {
            addCriterion("deviceStatus not between", value1, value2, "devicestatus");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserIsNull() {
            addCriterion("lastModifyUser is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserIsNotNull() {
            addCriterion("lastModifyUser is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserEqualTo(String value) {
            addCriterion("lastModifyUser =", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotEqualTo(String value) {
            addCriterion("lastModifyUser <>", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserGreaterThan(String value) {
            addCriterion("lastModifyUser >", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserGreaterThanOrEqualTo(String value) {
            addCriterion("lastModifyUser >=", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserLessThan(String value) {
            addCriterion("lastModifyUser <", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserLessThanOrEqualTo(String value) {
            addCriterion("lastModifyUser <=", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserLike(String value) {
            addCriterion("lastModifyUser like", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotLike(String value) {
            addCriterion("lastModifyUser not like", value, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserIn(List<String> values) {
            addCriterion("lastModifyUser in", values, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotIn(List<String> values) {
            addCriterion("lastModifyUser not in", values, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserBetween(String value1, String value2) {
            addCriterion("lastModifyUser between", value1, value2, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifyuserNotBetween(String value1, String value2) {
            addCriterion("lastModifyUser not between", value1, value2, "lastmodifyuser");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateIsNull() {
            addCriterion("lastModifyDate is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateIsNotNull() {
            addCriterion("lastModifyDate is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateEqualTo(Date value) {
            addCriterion("lastModifyDate =", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateNotEqualTo(Date value) {
            addCriterion("lastModifyDate <>", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateGreaterThan(Date value) {
            addCriterion("lastModifyDate >", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateGreaterThanOrEqualTo(Date value) {
            addCriterion("lastModifyDate >=", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateLessThan(Date value) {
            addCriterion("lastModifyDate <", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateLessThanOrEqualTo(Date value) {
            addCriterion("lastModifyDate <=", value, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateIn(List<Date> values) {
            addCriterion("lastModifyDate in", values, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateNotIn(List<Date> values) {
            addCriterion("lastModifyDate not in", values, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateBetween(Date value1, Date value2) {
            addCriterion("lastModifyDate between", value1, value2, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andLastmodifydateNotBetween(Date value1, Date value2) {
            addCriterion("lastModifyDate not between", value1, value2, "lastmodifydate");
            return (Criteria) this;
        }

        public Criteria andEntertimeIsNull() {
            addCriterion("enterTime is null");
            return (Criteria) this;
        }

        public Criteria andEntertimeIsNotNull() {
            addCriterion("enterTime is not null");
            return (Criteria) this;
        }

        public Criteria andEntertimeEqualTo(Date value) {
            addCriterion("enterTime =", value, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeNotEqualTo(Date value) {
            addCriterion("enterTime <>", value, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeGreaterThan(Date value) {
            addCriterion("enterTime >", value, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("enterTime >=", value, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeLessThan(Date value) {
            addCriterion("enterTime <", value, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeLessThanOrEqualTo(Date value) {
            addCriterion("enterTime <=", value, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeIn(List<Date> values) {
            addCriterion("enterTime in", values, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeNotIn(List<Date> values) {
            addCriterion("enterTime not in", values, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeBetween(Date value1, Date value2) {
            addCriterion("enterTime between", value1, value2, "entertime");
            return (Criteria) this;
        }

        public Criteria andEntertimeNotBetween(Date value1, Date value2) {
            addCriterion("enterTime not between", value1, value2, "entertime");
            return (Criteria) this;
        }

        public Criteria andExittimeIsNull() {
            addCriterion("exitTime is null");
            return (Criteria) this;
        }

        public Criteria andExittimeIsNotNull() {
            addCriterion("exitTime is not null");
            return (Criteria) this;
        }

        public Criteria andExittimeEqualTo(Date value) {
            addCriterion("exitTime =", value, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeNotEqualTo(Date value) {
            addCriterion("exitTime <>", value, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeGreaterThan(Date value) {
            addCriterion("exitTime >", value, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exitTime >=", value, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeLessThan(Date value) {
            addCriterion("exitTime <", value, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeLessThanOrEqualTo(Date value) {
            addCriterion("exitTime <=", value, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeIn(List<Date> values) {
            addCriterion("exitTime in", values, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeNotIn(List<Date> values) {
            addCriterion("exitTime not in", values, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeBetween(Date value1, Date value2) {
            addCriterion("exitTime between", value1, value2, "exittime");
            return (Criteria) this;
        }

        public Criteria andExittimeNotBetween(Date value1, Date value2) {
            addCriterion("exitTime not between", value1, value2, "exittime");
            return (Criteria) this;
        }

    public Criteria andStatusIsNull() {
        addCriterion("status is null");
        return (Criteria) this;
    }

    public Criteria andStatusIsNotNull() {
        addCriterion("status is not null");
        return (Criteria) this;
    }
}

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}