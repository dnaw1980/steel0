package com.rss.steel_production.foundation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProcessStandardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProcessStandardExample() {
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

        public Criteria andProcessStandarduidIsNull() {
            addCriterion("process_standardUID is null");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidIsNotNull() {
            addCriterion("process_standardUID is not null");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidEqualTo(String value) {
            addCriterion("process_standardUID =", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidNotEqualTo(String value) {
            addCriterion("process_standardUID <>", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidGreaterThan(String value) {
            addCriterion("process_standardUID >", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidGreaterThanOrEqualTo(String value) {
            addCriterion("process_standardUID >=", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidLessThan(String value) {
            addCriterion("process_standardUID <", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidLessThanOrEqualTo(String value) {
            addCriterion("process_standardUID <=", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidLike(String value) {
            addCriterion("process_standardUID like", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidNotLike(String value) {
            addCriterion("process_standardUID not like", value, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidIn(List<String> values) {
            addCriterion("process_standardUID in", values, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidNotIn(List<String> values) {
            addCriterion("process_standardUID not in", values, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidBetween(String value1, String value2) {
            addCriterion("process_standardUID between", value1, value2, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andProcessStandarduidNotBetween(String value1, String value2) {
            addCriterion("process_standardUID not between", value1, value2, "processStandarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidIsNull() {
            addCriterion("standardUID is null");
            return (Criteria) this;
        }

        public Criteria andStandarduidIsNotNull() {
            addCriterion("standardUID is not null");
            return (Criteria) this;
        }

        public Criteria andStandarduidEqualTo(String value) {
            addCriterion("standardUID =", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidNotEqualTo(String value) {
            addCriterion("standardUID <>", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidGreaterThan(String value) {
            addCriterion("standardUID >", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidGreaterThanOrEqualTo(String value) {
            addCriterion("standardUID >=", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidLessThan(String value) {
            addCriterion("standardUID <", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidLessThanOrEqualTo(String value) {
            addCriterion("standardUID <=", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidLike(String value) {
            addCriterion("standardUID like", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidNotLike(String value) {
            addCriterion("standardUID not like", value, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidIn(List<String> values) {
            addCriterion("standardUID in", values, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidNotIn(List<String> values) {
            addCriterion("standardUID not in", values, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidBetween(String value1, String value2) {
            addCriterion("standardUID between", value1, value2, "standarduid");
            return (Criteria) this;
        }

        public Criteria andStandarduidNotBetween(String value1, String value2) {
            addCriterion("standardUID not between", value1, value2, "standarduid");
            return (Criteria) this;
        }

        public Criteria andItemidIsNull() {
            addCriterion("itemID is null");
            return (Criteria) this;
        }

        public Criteria andItemidIsNotNull() {
            addCriterion("itemID is not null");
            return (Criteria) this;
        }

        public Criteria andItemidEqualTo(String value) {
            addCriterion("itemID =", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotEqualTo(String value) {
            addCriterion("itemID <>", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThan(String value) {
            addCriterion("itemID >", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThanOrEqualTo(String value) {
            addCriterion("itemID >=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThan(String value) {
            addCriterion("itemID <", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThanOrEqualTo(String value) {
            addCriterion("itemID <=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLike(String value) {
            addCriterion("itemID like", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotLike(String value) {
            addCriterion("itemID not like", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidIn(List<String> values) {
            addCriterion("itemID in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotIn(List<String> values) {
            addCriterion("itemID not in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidBetween(String value1, String value2) {
            addCriterion("itemID between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotBetween(String value1, String value2) {
            addCriterion("itemID not between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemnameIsNull() {
            addCriterion("itemName is null");
            return (Criteria) this;
        }

        public Criteria andItemnameIsNotNull() {
            addCriterion("itemName is not null");
            return (Criteria) this;
        }

        public Criteria andItemnameEqualTo(String value) {
            addCriterion("itemName =", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotEqualTo(String value) {
            addCriterion("itemName <>", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameGreaterThan(String value) {
            addCriterion("itemName >", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameGreaterThanOrEqualTo(String value) {
            addCriterion("itemName >=", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLessThan(String value) {
            addCriterion("itemName <", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLessThanOrEqualTo(String value) {
            addCriterion("itemName <=", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameLike(String value) {
            addCriterion("itemName like", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotLike(String value) {
            addCriterion("itemName not like", value, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameIn(List<String> values) {
            addCriterion("itemName in", values, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotIn(List<String> values) {
            addCriterion("itemName not in", values, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameBetween(String value1, String value2) {
            addCriterion("itemName between", value1, value2, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemnameNotBetween(String value1, String value2) {
            addCriterion("itemName not between", value1, value2, "itemname");
            return (Criteria) this;
        }

        public Criteria andItemtypeIsNull() {
            addCriterion("itemType is null");
            return (Criteria) this;
        }

        public Criteria andItemtypeIsNotNull() {
            addCriterion("itemType is not null");
            return (Criteria) this;
        }

        public Criteria andItemtypeEqualTo(String value) {
            addCriterion("itemType =", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotEqualTo(String value) {
            addCriterion("itemType <>", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeGreaterThan(String value) {
            addCriterion("itemType >", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeGreaterThanOrEqualTo(String value) {
            addCriterion("itemType >=", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeLessThan(String value) {
            addCriterion("itemType <", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeLessThanOrEqualTo(String value) {
            addCriterion("itemType <=", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeLike(String value) {
            addCriterion("itemType like", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotLike(String value) {
            addCriterion("itemType not like", value, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeIn(List<String> values) {
            addCriterion("itemType in", values, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotIn(List<String> values) {
            addCriterion("itemType not in", values, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeBetween(String value1, String value2) {
            addCriterion("itemType between", value1, value2, "itemtype");
            return (Criteria) this;
        }

        public Criteria andItemtypeNotBetween(String value1, String value2) {
            addCriterion("itemType not between", value1, value2, "itemtype");
            return (Criteria) this;
        }

        public Criteria andSteelgradeIsNull() {
            addCriterion("steelGrade is null");
            return (Criteria) this;
        }

        public Criteria andSteelgradeIsNotNull() {
            addCriterion("steelGrade is not null");
            return (Criteria) this;
        }

        public Criteria andSteelgradeEqualTo(String value) {
            addCriterion("steelGrade =", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeNotEqualTo(String value) {
            addCriterion("steelGrade <>", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeGreaterThan(String value) {
            addCriterion("steelGrade >", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeGreaterThanOrEqualTo(String value) {
            addCriterion("steelGrade >=", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeLessThan(String value) {
            addCriterion("steelGrade <", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeLessThanOrEqualTo(String value) {
            addCriterion("steelGrade <=", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeLike(String value) {
            addCriterion("steelGrade like", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeNotLike(String value) {
            addCriterion("steelGrade not like", value, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeIn(List<String> values) {
            addCriterion("steelGrade in", values, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeNotIn(List<String> values) {
            addCriterion("steelGrade not in", values, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeBetween(String value1, String value2) {
            addCriterion("steelGrade between", value1, value2, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andSteelgradeNotBetween(String value1, String value2) {
            addCriterion("steelGrade not between", value1, value2, "steelgrade");
            return (Criteria) this;
        }

        public Criteria andMeasureunitIsNull() {
            addCriterion("measureUnit is null");
            return (Criteria) this;
        }

        public Criteria andMeasureunitIsNotNull() {
            addCriterion("measureUnit is not null");
            return (Criteria) this;
        }

        public Criteria andMeasureunitEqualTo(String value) {
            addCriterion("measureUnit =", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitNotEqualTo(String value) {
            addCriterion("measureUnit <>", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitGreaterThan(String value) {
            addCriterion("measureUnit >", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitGreaterThanOrEqualTo(String value) {
            addCriterion("measureUnit >=", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitLessThan(String value) {
            addCriterion("measureUnit <", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitLessThanOrEqualTo(String value) {
            addCriterion("measureUnit <=", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitLike(String value) {
            addCriterion("measureUnit like", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitNotLike(String value) {
            addCriterion("measureUnit not like", value, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitIn(List<String> values) {
            addCriterion("measureUnit in", values, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitNotIn(List<String> values) {
            addCriterion("measureUnit not in", values, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitBetween(String value1, String value2) {
            addCriterion("measureUnit between", value1, value2, "measureunit");
            return (Criteria) this;
        }

        public Criteria andMeasureunitNotBetween(String value1, String value2) {
            addCriterion("measureUnit not between", value1, value2, "measureunit");
            return (Criteria) this;
        }

        public Criteria andStandardvalueIsNull() {
            addCriterion("standardValue is null");
            return (Criteria) this;
        }

        public Criteria andStandardvalueIsNotNull() {
            addCriterion("standardValue is not null");
            return (Criteria) this;
        }

        public Criteria andStandardvalueEqualTo(Double value) {
            addCriterion("standardValue =", value, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueNotEqualTo(Double value) {
            addCriterion("standardValue <>", value, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueGreaterThan(Double value) {
            addCriterion("standardValue >", value, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueGreaterThanOrEqualTo(Double value) {
            addCriterion("standardValue >=", value, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueLessThan(Double value) {
            addCriterion("standardValue <", value, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueLessThanOrEqualTo(Double value) {
            addCriterion("standardValue <=", value, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueIn(List<Double> values) {
            addCriterion("standardValue in", values, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueNotIn(List<Double> values) {
            addCriterion("standardValue not in", values, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueBetween(Double value1, Double value2) {
            addCriterion("standardValue between", value1, value2, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andStandardvalueNotBetween(Double value1, Double value2) {
            addCriterion("standardValue not between", value1, value2, "standardvalue");
            return (Criteria) this;
        }

        public Criteria andLowerlimitIsNull() {
            addCriterion("lowerLimit is null");
            return (Criteria) this;
        }

        public Criteria andLowerlimitIsNotNull() {
            addCriterion("lowerLimit is not null");
            return (Criteria) this;
        }

        public Criteria andLowerlimitEqualTo(Double value) {
            addCriterion("lowerLimit =", value, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitNotEqualTo(Double value) {
            addCriterion("lowerLimit <>", value, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitGreaterThan(Double value) {
            addCriterion("lowerLimit >", value, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitGreaterThanOrEqualTo(Double value) {
            addCriterion("lowerLimit >=", value, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitLessThan(Double value) {
            addCriterion("lowerLimit <", value, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitLessThanOrEqualTo(Double value) {
            addCriterion("lowerLimit <=", value, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitIn(List<Double> values) {
            addCriterion("lowerLimit in", values, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitNotIn(List<Double> values) {
            addCriterion("lowerLimit not in", values, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitBetween(Double value1, Double value2) {
            addCriterion("lowerLimit between", value1, value2, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andLowerlimitNotBetween(Double value1, Double value2) {
            addCriterion("lowerLimit not between", value1, value2, "lowerlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitIsNull() {
            addCriterion("upperLimit is null");
            return (Criteria) this;
        }

        public Criteria andUpperlimitIsNotNull() {
            addCriterion("upperLimit is not null");
            return (Criteria) this;
        }

        public Criteria andUpperlimitEqualTo(Double value) {
            addCriterion("upperLimit =", value, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitNotEqualTo(Double value) {
            addCriterion("upperLimit <>", value, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitGreaterThan(Double value) {
            addCriterion("upperLimit >", value, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitGreaterThanOrEqualTo(Double value) {
            addCriterion("upperLimit >=", value, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitLessThan(Double value) {
            addCriterion("upperLimit <", value, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitLessThanOrEqualTo(Double value) {
            addCriterion("upperLimit <=", value, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitIn(List<Double> values) {
            addCriterion("upperLimit in", values, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitNotIn(List<Double> values) {
            addCriterion("upperLimit not in", values, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitBetween(Double value1, Double value2) {
            addCriterion("upperLimit between", value1, value2, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andUpperlimitNotBetween(Double value1, Double value2) {
            addCriterion("upperLimit not between", value1, value2, "upperlimit");
            return (Criteria) this;
        }

        public Criteria andValidperiodIsNull() {
            addCriterion("validPeriod is null");
            return (Criteria) this;
        }

        public Criteria andValidperiodIsNotNull() {
            addCriterion("validPeriod is not null");
            return (Criteria) this;
        }

        public Criteria andValidperiodEqualTo(String value) {
            addCriterion("validPeriod =", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodNotEqualTo(String value) {
            addCriterion("validPeriod <>", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodGreaterThan(String value) {
            addCriterion("validPeriod >", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodGreaterThanOrEqualTo(String value) {
            addCriterion("validPeriod >=", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodLessThan(String value) {
            addCriterion("validPeriod <", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodLessThanOrEqualTo(String value) {
            addCriterion("validPeriod <=", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodLike(String value) {
            addCriterion("validPeriod like", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodNotLike(String value) {
            addCriterion("validPeriod not like", value, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodIn(List<String> values) {
            addCriterion("validPeriod in", values, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodNotIn(List<String> values) {
            addCriterion("validPeriod not in", values, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodBetween(String value1, String value2) {
            addCriterion("validPeriod between", value1, value2, "validperiod");
            return (Criteria) this;
        }

        public Criteria andValidperiodNotBetween(String value1, String value2) {
            addCriterion("validPeriod not between", value1, value2, "validperiod");
            return (Criteria) this;
        }

        public Criteria andStandardstatusIsNull() {
            addCriterion("standardStatus is null");
            return (Criteria) this;
        }

        public Criteria andStandardstatusIsNotNull() {
            addCriterion("standardStatus is not null");
            return (Criteria) this;
        }

        public Criteria andStandardstatusEqualTo(String value) {
            addCriterion("standardStatus =", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusNotEqualTo(String value) {
            addCriterion("standardStatus <>", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusGreaterThan(String value) {
            addCriterion("standardStatus >", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusGreaterThanOrEqualTo(String value) {
            addCriterion("standardStatus >=", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusLessThan(String value) {
            addCriterion("standardStatus <", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusLessThanOrEqualTo(String value) {
            addCriterion("standardStatus <=", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusLike(String value) {
            addCriterion("standardStatus like", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusNotLike(String value) {
            addCriterion("standardStatus not like", value, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusIn(List<String> values) {
            addCriterion("standardStatus in", values, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusNotIn(List<String> values) {
            addCriterion("standardStatus not in", values, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusBetween(String value1, String value2) {
            addCriterion("standardStatus between", value1, value2, "standardstatus");
            return (Criteria) this;
        }

        public Criteria andStandardstatusNotBetween(String value1, String value2) {
            addCriterion("standardStatus not between", value1, value2, "standardstatus");
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