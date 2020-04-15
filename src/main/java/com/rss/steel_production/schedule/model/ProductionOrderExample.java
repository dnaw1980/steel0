package com.rss.steel_production.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProductionOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductionOrderExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andProductionOrderuidIsNull() {
            addCriterion("product_orderUID is null");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidIsNotNull() {
            addCriterion("product_orderUID is not null");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidEqualTo(String value) {
            addCriterion("product_orderUID =", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidNotEqualTo(String value) {
            addCriterion("product_orderUID <>", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidGreaterThan(String value) {
            addCriterion("product_orderUID >", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidGreaterThanOrEqualTo(String value) {
            addCriterion("product_orderUID >=", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidLessThan(String value) {
            addCriterion("product_orderUID <", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidLessThanOrEqualTo(String value) {
            addCriterion("product_orderUID <=", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidLike(String value) {
            addCriterion("product_orderUID like", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidNotLike(String value) {
            addCriterion("product_orderUID not like", value, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidIn(List<String> values) {
            addCriterion("product_orderUID in", values, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidNotIn(List<String> values) {
            addCriterion("product_orderUID not in", values, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidBetween(String value1, String value2) {
            addCriterion("product_orderUID between", value1, value2, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andProductionOrderuidNotBetween(String value1, String value2) {
            addCriterion("product_orderUID not between", value1, value2, "productOrderuid");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNull() {
            addCriterion("orderNo is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("orderNo is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("orderNo =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("orderNo <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("orderNo >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("orderNo >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("orderNo <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("orderNo <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("orderNo like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("orderNo not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("orderNo in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("orderNo not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("orderNo between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("orderNo not between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrderdateIsNull() {
            addCriterion("orderDate is null");
            return (Criteria) this;
        }

        public Criteria andOrderdateIsNotNull() {
            addCriterion("orderDate is not null");
            return (Criteria) this;
        }

        public Criteria andOrderdateEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate =", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate <>", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateGreaterThan(Date value) {
            addCriterionForJDBCDate("orderDate >", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate >=", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateLessThan(Date value) {
            addCriterionForJDBCDate("orderDate <", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("orderDate <=", value, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateIn(List<Date> values) {
            addCriterionForJDBCDate("orderDate in", values, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("orderDate not in", values, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orderDate between", value1, value2, "orderdate");
            return (Criteria) this;
        }

        public Criteria andOrderdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("orderDate not between", value1, value2, "orderdate");
            return (Criteria) this;
        }

        public Criteria andProductcodeIsNull() {
            addCriterion("productCode is null");
            return (Criteria) this;
        }

        public Criteria andProductcodeIsNotNull() {
            addCriterion("productCode is not null");
            return (Criteria) this;
        }

        public Criteria andProductcodeEqualTo(String value) {
            addCriterion("productCode =", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeNotEqualTo(String value) {
            addCriterion("productCode <>", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeGreaterThan(String value) {
            addCriterion("productCode >", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeGreaterThanOrEqualTo(String value) {
            addCriterion("productCode >=", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeLessThan(String value) {
            addCriterion("productCode <", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeLessThanOrEqualTo(String value) {
            addCriterion("productCode <=", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeLike(String value) {
            addCriterion("productCode like", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeNotLike(String value) {
            addCriterion("productCode not like", value, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeIn(List<String> values) {
            addCriterion("productCode in", values, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeNotIn(List<String> values) {
            addCriterion("productCode not in", values, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeBetween(String value1, String value2) {
            addCriterion("productCode between", value1, value2, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductcodeNotBetween(String value1, String value2) {
            addCriterion("productCode not between", value1, value2, "productcode");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNull() {
            addCriterion("productName is null");
            return (Criteria) this;
        }

        public Criteria andProductnameIsNotNull() {
            addCriterion("productName is not null");
            return (Criteria) this;
        }

        public Criteria andProductnameEqualTo(String value) {
            addCriterion("productName =", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotEqualTo(String value) {
            addCriterion("productName <>", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThan(String value) {
            addCriterion("productName >", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameGreaterThanOrEqualTo(String value) {
            addCriterion("productName >=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThan(String value) {
            addCriterion("productName <", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLessThanOrEqualTo(String value) {
            addCriterion("productName <=", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameLike(String value) {
            addCriterion("productName like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotLike(String value) {
            addCriterion("productName not like", value, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameIn(List<String> values) {
            addCriterion("productName in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotIn(List<String> values) {
            addCriterion("productName not in", values, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameBetween(String value1, String value2) {
            addCriterion("productName between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProductnameNotBetween(String value1, String value2) {
            addCriterion("productName not between", value1, value2, "productname");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNull() {
            addCriterion("productType is null");
            return (Criteria) this;
        }

        public Criteria andProducttypeIsNotNull() {
            addCriterion("productType is not null");
            return (Criteria) this;
        }

        public Criteria andProducttypeEqualTo(String value) {
            addCriterion("productType =", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotEqualTo(String value) {
            addCriterion("productType <>", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThan(String value) {
            addCriterion("productType >", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeGreaterThanOrEqualTo(String value) {
            addCriterion("productType >=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThan(String value) {
            addCriterion("productType <", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLessThanOrEqualTo(String value) {
            addCriterion("productType <=", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeLike(String value) {
            addCriterion("productType like", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotLike(String value) {
            addCriterion("productType not like", value, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeIn(List<String> values) {
            addCriterion("productType in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotIn(List<String> values) {
            addCriterion("productType not in", values, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeBetween(String value1, String value2) {
            addCriterion("productType between", value1, value2, "producttype");
            return (Criteria) this;
        }

        public Criteria andProducttypeNotBetween(String value1, String value2) {
            addCriterion("productType not between", value1, value2, "producttype");
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

        public Criteria andProductlevelIsNull() {
            addCriterion("productLevel is null");
            return (Criteria) this;
        }

        public Criteria andProductlevelIsNotNull() {
            addCriterion("productLevel is not null");
            return (Criteria) this;
        }

        public Criteria andProductlevelEqualTo(String value) {
            addCriterion("productLevel =", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelNotEqualTo(String value) {
            addCriterion("productLevel <>", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelGreaterThan(String value) {
            addCriterion("productLevel >", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelGreaterThanOrEqualTo(String value) {
            addCriterion("productLevel >=", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelLessThan(String value) {
            addCriterion("productLevel <", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelLessThanOrEqualTo(String value) {
            addCriterion("productLevel <=", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelLike(String value) {
            addCriterion("productLevel like", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelNotLike(String value) {
            addCriterion("productLevel not like", value, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelIn(List<String> values) {
            addCriterion("productLevel in", values, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelNotIn(List<String> values) {
            addCriterion("productLevel not in", values, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelBetween(String value1, String value2) {
            addCriterion("productLevel between", value1, value2, "productlevel");
            return (Criteria) this;
        }

        public Criteria andProductlevelNotBetween(String value1, String value2) {
            addCriterion("productLevel not between", value1, value2, "productlevel");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardIsNull() {
            addCriterion("technicalStandard is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardIsNotNull() {
            addCriterion("technicalStandard is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardEqualTo(String value) {
            addCriterion("technicalStandard =", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardNotEqualTo(String value) {
            addCriterion("technicalStandard <>", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardGreaterThan(String value) {
            addCriterion("technicalStandard >", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardGreaterThanOrEqualTo(String value) {
            addCriterion("technicalStandard >=", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardLessThan(String value) {
            addCriterion("technicalStandard <", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardLessThanOrEqualTo(String value) {
            addCriterion("technicalStandard <=", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardLike(String value) {
            addCriterion("technicalStandard like", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardNotLike(String value) {
            addCriterion("technicalStandard not like", value, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardIn(List<String> values) {
            addCriterion("technicalStandard in", values, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardNotIn(List<String> values) {
            addCriterion("technicalStandard not in", values, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardBetween(String value1, String value2) {
            addCriterion("technicalStandard between", value1, value2, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andTechnicalstandardNotBetween(String value1, String value2) {
            addCriterion("technicalStandard not between", value1, value2, "technicalstandard");
            return (Criteria) this;
        }

        public Criteria andBilletlengthIsNull() {
            addCriterion("billetLength is null");
            return (Criteria) this;
        }

        public Criteria andBilletlengthIsNotNull() {
            addCriterion("billetLength is not null");
            return (Criteria) this;
        }

        public Criteria andBilletlengthEqualTo(Double value) {
            addCriterion("billetLength =", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotEqualTo(Double value) {
            addCriterion("billetLength <>", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthGreaterThan(Double value) {
            addCriterion("billetLength >", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthGreaterThanOrEqualTo(Double value) {
            addCriterion("billetLength >=", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthLessThan(Double value) {
            addCriterion("billetLength <", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthLessThanOrEqualTo(Double value) {
            addCriterion("billetLength <=", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthIn(List<Double> values) {
            addCriterion("billetLength in", values, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotIn(List<Double> values) {
            addCriterion("billetLength not in", values, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthBetween(Double value1, Double value2) {
            addCriterion("billetLength between", value1, value2, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotBetween(Double value1, Double value2) {
            addCriterion("billetLength not between", value1, value2, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletwidthIsNull() {
            addCriterion("billetWidth is null");
            return (Criteria) this;
        }

        public Criteria andBilletwidthIsNotNull() {
            addCriterion("billetWidth is not null");
            return (Criteria) this;
        }

        public Criteria andBilletwidthEqualTo(Double value) {
            addCriterion("billetWidth =", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotEqualTo(Double value) {
            addCriterion("billetWidth <>", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthGreaterThan(Double value) {
            addCriterion("billetWidth >", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthGreaterThanOrEqualTo(Double value) {
            addCriterion("billetWidth >=", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthLessThan(Double value) {
            addCriterion("billetWidth <", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthLessThanOrEqualTo(Double value) {
            addCriterion("billetWidth <=", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthIn(List<Double> values) {
            addCriterion("billetWidth in", values, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotIn(List<Double> values) {
            addCriterion("billetWidth not in", values, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthBetween(Double value1, Double value2) {
            addCriterion("billetWidth between", value1, value2, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotBetween(Double value1, Double value2) {
            addCriterion("billetWidth not between", value1, value2, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletthickIsNull() {
            addCriterion("billetThick is null");
            return (Criteria) this;
        }

        public Criteria andBilletthickIsNotNull() {
            addCriterion("billetThick is not null");
            return (Criteria) this;
        }

        public Criteria andBilletthickEqualTo(Double value) {
            addCriterion("billetThick =", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotEqualTo(Double value) {
            addCriterion("billetThick <>", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickGreaterThan(Double value) {
            addCriterion("billetThick >", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickGreaterThanOrEqualTo(Double value) {
            addCriterion("billetThick >=", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickLessThan(Double value) {
            addCriterion("billetThick <", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickLessThanOrEqualTo(Double value) {
            addCriterion("billetThick <=", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickIn(List<Double> values) {
            addCriterion("billetThick in", values, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotIn(List<Double> values) {
            addCriterion("billetThick not in", values, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickBetween(Double value1, Double value2) {
            addCriterion("billetThick between", value1, value2, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotBetween(Double value1, Double value2) {
            addCriterion("billetThick not between", value1, value2, "billetthick");
            return (Criteria) this;
        }

        public Criteria andTargetweightIsNull() {
            addCriterion("targetWeight is null");
            return (Criteria) this;
        }

        public Criteria andTargetweightIsNotNull() {
            addCriterion("targetWeight is not null");
            return (Criteria) this;
        }

        public Criteria andTargetweightEqualTo(Double value) {
            addCriterion("targetWeight =", value, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightNotEqualTo(Double value) {
            addCriterion("targetWeight <>", value, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightGreaterThan(Double value) {
            addCriterion("targetWeight >", value, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightGreaterThanOrEqualTo(Double value) {
            addCriterion("targetWeight >=", value, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightLessThan(Double value) {
            addCriterion("targetWeight <", value, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightLessThanOrEqualTo(Double value) {
            addCriterion("targetWeight <=", value, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightIn(List<Double> values) {
            addCriterion("targetWeight in", values, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightNotIn(List<Double> values) {
            addCriterion("targetWeight not in", values, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightBetween(Double value1, Double value2) {
            addCriterion("targetWeight between", value1, value2, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetweightNotBetween(Double value1, Double value2) {
            addCriterion("targetWeight not between", value1, value2, "targetweight");
            return (Criteria) this;
        }

        public Criteria andTargetquantityIsNull() {
            addCriterion("targetQuantity is null");
            return (Criteria) this;
        }

        public Criteria andTargetquantityIsNotNull() {
            addCriterion("targetQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andTargetquantityEqualTo(Double value) {
            addCriterion("targetQuantity =", value, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityNotEqualTo(Double value) {
            addCriterion("targetQuantity <>", value, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityGreaterThan(Double value) {
            addCriterion("targetQuantity >", value, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityGreaterThanOrEqualTo(Double value) {
            addCriterion("targetQuantity >=", value, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityLessThan(Double value) {
            addCriterion("targetQuantity <", value, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityLessThanOrEqualTo(Double value) {
            addCriterion("targetQuantity <=", value, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityIn(List<Double> values) {
            addCriterion("targetQuantity in", values, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityNotIn(List<Double> values) {
            addCriterion("targetQuantity not in", values, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityBetween(Double value1, Double value2) {
            addCriterion("targetQuantity between", value1, value2, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargetquantityNotBetween(Double value1, Double value2) {
            addCriterion("targetQuantity not between", value1, value2, "targetquantity");
            return (Criteria) this;
        }

        public Criteria andTargettimeIsNull() {
            addCriterion("targetTime is null");
            return (Criteria) this;
        }

        public Criteria andTargettimeIsNotNull() {
            addCriterion("targetTime is not null");
            return (Criteria) this;
        }

        public Criteria andTargettimeEqualTo(Date value) {
            addCriterion("targetTime =", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotEqualTo(Date value) {
            addCriterion("targetTime <>", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeGreaterThan(Date value) {
            addCriterion("targetTime >", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeGreaterThanOrEqualTo(Date value) {
            addCriterion("targetTime >=", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeLessThan(Date value) {
            addCriterion("targetTime <", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeLessThanOrEqualTo(Date value) {
            addCriterion("targetTime <=", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeIn(List<Date> values) {
            addCriterion("targetTime in", values, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotIn(List<Date> values) {
            addCriterion("targetTime not in", values, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeBetween(Date value1, Date value2) {
            addCriterion("targetTime between", value1, value2, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotBetween(Date value1, Date value2) {
            addCriterion("targetTime not between", value1, value2, "targettime");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffIsNull() {
            addCriterion("preparedStaff is null");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffIsNotNull() {
            addCriterion("preparedStaff is not null");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffEqualTo(String value) {
            addCriterion("preparedStaff =", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffNotEqualTo(String value) {
            addCriterion("preparedStaff <>", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffGreaterThan(String value) {
            addCriterion("preparedStaff >", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffGreaterThanOrEqualTo(String value) {
            addCriterion("preparedStaff >=", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffLessThan(String value) {
            addCriterion("preparedStaff <", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffLessThanOrEqualTo(String value) {
            addCriterion("preparedStaff <=", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffLike(String value) {
            addCriterion("preparedStaff like", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffNotLike(String value) {
            addCriterion("preparedStaff not like", value, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffIn(List<String> values) {
            addCriterion("preparedStaff in", values, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffNotIn(List<String> values) {
            addCriterion("preparedStaff not in", values, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffBetween(String value1, String value2) {
            addCriterion("preparedStaff between", value1, value2, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedstaffNotBetween(String value1, String value2) {
            addCriterion("preparedStaff not between", value1, value2, "preparedstaff");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeIsNull() {
            addCriterion("preparedTime is null");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeIsNotNull() {
            addCriterion("preparedTime is not null");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeEqualTo(Date value) {
            addCriterion("preparedTime =", value, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeNotEqualTo(Date value) {
            addCriterion("preparedTime <>", value, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeGreaterThan(Date value) {
            addCriterion("preparedTime >", value, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("preparedTime >=", value, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeLessThan(Date value) {
            addCriterion("preparedTime <", value, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeLessThanOrEqualTo(Date value) {
            addCriterion("preparedTime <=", value, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeIn(List<Date> values) {
            addCriterion("preparedTime in", values, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeNotIn(List<Date> values) {
            addCriterion("preparedTime not in", values, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeBetween(Date value1, Date value2) {
            addCriterion("preparedTime between", value1, value2, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andPreparedtimeNotBetween(Date value1, Date value2) {
            addCriterion("preparedTime not between", value1, value2, "preparedtime");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderIsNull() {
            addCriterion("technicalLeader is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderIsNotNull() {
            addCriterion("technicalLeader is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderEqualTo(String value) {
            addCriterion("technicalLeader =", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderNotEqualTo(String value) {
            addCriterion("technicalLeader <>", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderGreaterThan(String value) {
            addCriterion("technicalLeader >", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderGreaterThanOrEqualTo(String value) {
            addCriterion("technicalLeader >=", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderLessThan(String value) {
            addCriterion("technicalLeader <", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderLessThanOrEqualTo(String value) {
            addCriterion("technicalLeader <=", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderLike(String value) {
            addCriterion("technicalLeader like", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderNotLike(String value) {
            addCriterion("technicalLeader not like", value, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderIn(List<String> values) {
            addCriterion("technicalLeader in", values, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderNotIn(List<String> values) {
            addCriterion("technicalLeader not in", values, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderBetween(String value1, String value2) {
            addCriterion("technicalLeader between", value1, value2, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicalleaderNotBetween(String value1, String value2) {
            addCriterion("technicalLeader not between", value1, value2, "technicalleader");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeIsNull() {
            addCriterion("technicalTime is null");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeIsNotNull() {
            addCriterion("technicalTime is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeEqualTo(Date value) {
            addCriterion("technicalTime =", value, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeNotEqualTo(Date value) {
            addCriterion("technicalTime <>", value, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeGreaterThan(Date value) {
            addCriterion("technicalTime >", value, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("technicalTime >=", value, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeLessThan(Date value) {
            addCriterion("technicalTime <", value, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeLessThanOrEqualTo(Date value) {
            addCriterion("technicalTime <=", value, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeIn(List<Date> values) {
            addCriterion("technicalTime in", values, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeNotIn(List<Date> values) {
            addCriterion("technicalTime not in", values, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeBetween(Date value1, Date value2) {
            addCriterion("technicalTime between", value1, value2, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andTechnicaltimeNotBetween(Date value1, Date value2) {
            addCriterion("technicalTime not between", value1, value2, "technicaltime");
            return (Criteria) this;
        }

        public Criteria andProductleaderIsNull() {
            addCriterion("productLeader is null");
            return (Criteria) this;
        }

        public Criteria andProductleaderIsNotNull() {
            addCriterion("productLeader is not null");
            return (Criteria) this;
        }

        public Criteria andProductleaderEqualTo(String value) {
            addCriterion("productLeader =", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderNotEqualTo(String value) {
            addCriterion("productLeader <>", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderGreaterThan(String value) {
            addCriterion("productLeader >", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderGreaterThanOrEqualTo(String value) {
            addCriterion("productLeader >=", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderLessThan(String value) {
            addCriterion("productLeader <", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderLessThanOrEqualTo(String value) {
            addCriterion("productLeader <=", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderLike(String value) {
            addCriterion("productLeader like", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderNotLike(String value) {
            addCriterion("productLeader not like", value, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderIn(List<String> values) {
            addCriterion("productLeader in", values, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderNotIn(List<String> values) {
            addCriterion("productLeader not in", values, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderBetween(String value1, String value2) {
            addCriterion("productLeader between", value1, value2, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductleaderNotBetween(String value1, String value2) {
            addCriterion("productLeader not between", value1, value2, "productleader");
            return (Criteria) this;
        }

        public Criteria andProductiontimeIsNull() {
            addCriterion("productionTime is null");
            return (Criteria) this;
        }

        public Criteria andProductiontimeIsNotNull() {
            addCriterion("productionTime is not null");
            return (Criteria) this;
        }

        public Criteria andProductiontimeEqualTo(Date value) {
            addCriterion("productionTime =", value, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeNotEqualTo(Date value) {
            addCriterion("productionTime <>", value, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeGreaterThan(Date value) {
            addCriterion("productionTime >", value, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("productionTime >=", value, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeLessThan(Date value) {
            addCriterion("productionTime <", value, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeLessThanOrEqualTo(Date value) {
            addCriterion("productionTime <=", value, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeIn(List<Date> values) {
            addCriterion("productionTime in", values, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeNotIn(List<Date> values) {
            addCriterion("productionTime not in", values, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeBetween(Date value1, Date value2) {
            addCriterion("productionTime between", value1, value2, "productiontime");
            return (Criteria) this;
        }

        public Criteria andProductiontimeNotBetween(Date value1, Date value2) {
            addCriterion("productionTime not between", value1, value2, "productiontime");
            return (Criteria) this;
        }

        public Criteria andReleasestaffIsNull() {
            addCriterion("releaseStaff is null");
            return (Criteria) this;
        }

        public Criteria andReleasestaffIsNotNull() {
            addCriterion("releaseStaff is not null");
            return (Criteria) this;
        }

        public Criteria andReleasestaffEqualTo(String value) {
            addCriterion("releaseStaff =", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffNotEqualTo(String value) {
            addCriterion("releaseStaff <>", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffGreaterThan(String value) {
            addCriterion("releaseStaff >", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffGreaterThanOrEqualTo(String value) {
            addCriterion("releaseStaff >=", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffLessThan(String value) {
            addCriterion("releaseStaff <", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffLessThanOrEqualTo(String value) {
            addCriterion("releaseStaff <=", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffLike(String value) {
            addCriterion("releaseStaff like", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffNotLike(String value) {
            addCriterion("releaseStaff not like", value, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffIn(List<String> values) {
            addCriterion("releaseStaff in", values, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffNotIn(List<String> values) {
            addCriterion("releaseStaff not in", values, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffBetween(String value1, String value2) {
            addCriterion("releaseStaff between", value1, value2, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasestaffNotBetween(String value1, String value2) {
            addCriterion("releaseStaff not between", value1, value2, "releasestaff");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIsNull() {
            addCriterion("releaseTime is null");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIsNotNull() {
            addCriterion("releaseTime is not null");
            return (Criteria) this;
        }

        public Criteria andReleasetimeEqualTo(Date value) {
            addCriterion("releaseTime =", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotEqualTo(Date value) {
            addCriterion("releaseTime <>", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeGreaterThan(Date value) {
            addCriterion("releaseTime >", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("releaseTime >=", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeLessThan(Date value) {
            addCriterion("releaseTime <", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeLessThanOrEqualTo(Date value) {
            addCriterion("releaseTime <=", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIn(List<Date> values) {
            addCriterion("releaseTime in", values, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotIn(List<Date> values) {
            addCriterion("releaseTime not in", values, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeBetween(Date value1, Date value2) {
            addCriterion("releaseTime between", value1, value2, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotBetween(Date value1, Date value2) {
            addCriterion("releaseTime not between", value1, value2, "releasetime");
            return (Criteria) this;
        }

        public Criteria andProcessrouteIsNull() {
            addCriterion("processRoute is null");
            return (Criteria) this;
        }

        public Criteria andProcessrouteIsNotNull() {
            addCriterion("processRoute is not null");
            return (Criteria) this;
        }

        public Criteria andProcessrouteEqualTo(String value) {
            addCriterion("processRoute =", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteNotEqualTo(String value) {
            addCriterion("processRoute <>", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteGreaterThan(String value) {
            addCriterion("processRoute >", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteGreaterThanOrEqualTo(String value) {
            addCriterion("processRoute >=", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteLessThan(String value) {
            addCriterion("processRoute <", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteLessThanOrEqualTo(String value) {
            addCriterion("processRoute <=", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteLike(String value) {
            addCriterion("processRoute like", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteNotLike(String value) {
            addCriterion("processRoute not like", value, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteIn(List<String> values) {
            addCriterion("processRoute in", values, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteNotIn(List<String> values) {
            addCriterion("processRoute not in", values, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteBetween(String value1, String value2) {
            addCriterion("processRoute between", value1, value2, "processroute");
            return (Criteria) this;
        }

        public Criteria andProcessrouteNotBetween(String value1, String value2) {
            addCriterion("processRoute not between", value1, value2, "processroute");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNull() {
            addCriterion("orderStatus is null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNotNull() {
            addCriterion("orderStatus is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusEqualTo(String value) {
            addCriterion("orderStatus =", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotEqualTo(String value) {
            addCriterion("orderStatus <>", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThan(String value) {
            addCriterion("orderStatus >", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThanOrEqualTo(String value) {
            addCriterion("orderStatus >=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThan(String value) {
            addCriterion("orderStatus <", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThanOrEqualTo(String value) {
            addCriterion("orderStatus <=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLike(String value) {
            addCriterion("orderStatus like", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotLike(String value) {
            addCriterion("orderStatus not like", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIn(List<String> values) {
            addCriterion("orderStatus in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotIn(List<String> values) {
            addCriterion("orderStatus not in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusBetween(String value1, String value2) {
            addCriterion("orderStatus between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotBetween(String value1, String value2) {
            addCriterion("orderStatus not between", value1, value2, "orderstatus");
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