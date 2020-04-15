package com.rss.steel_production.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargePlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChargePlanExample() {
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

        public Criteria andChargePlanuidIsNull() {
            addCriterion("charge_planUID is null");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidIsNotNull() {
            addCriterion("charge_planUID is not null");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidEqualTo(String value) {
            addCriterion("charge_planUID =", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidNotEqualTo(String value) {
            addCriterion("charge_planUID <>", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidGreaterThan(String value) {
            addCriterion("charge_planUID >", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidGreaterThanOrEqualTo(String value) {
            addCriterion("charge_planUID >=", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidLessThan(String value) {
            addCriterion("charge_planUID <", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidLessThanOrEqualTo(String value) {
            addCriterion("charge_planUID <=", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidLike(String value) {
            addCriterion("charge_planUID like", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidNotLike(String value) {
            addCriterion("charge_planUID not like", value, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidIn(List<String> values) {
            addCriterion("charge_planUID in", values, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidNotIn(List<String> values) {
            addCriterion("charge_planUID not in", values, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidBetween(String value1, String value2) {
            addCriterion("charge_planUID between", value1, value2, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargePlanuidNotBetween(String value1, String value2) {
            addCriterion("charge_planUID not between", value1, value2, "chargePlanuid");
            return (Criteria) this;
        }

        public Criteria andChargenoIsNull() {
            addCriterion("chargeNo is null");
            return (Criteria) this;
        }

        public Criteria andChargenoIsNotNull() {
            addCriterion("chargeNo is not null");
            return (Criteria) this;
        }

        public Criteria andChargenoEqualTo(String value) {
            addCriterion("chargeNo =", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoNotEqualTo(String value) {
            addCriterion("chargeNo <>", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoGreaterThan(String value) {
            addCriterion("chargeNo >", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoGreaterThanOrEqualTo(String value) {
            addCriterion("chargeNo >=", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoLessThan(String value) {
            addCriterion("chargeNo <", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoLessThanOrEqualTo(String value) {
            addCriterion("chargeNo <=", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoLike(String value) {
            addCriterion("chargeNo like", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoNotLike(String value) {
            addCriterion("chargeNo not like", value, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoIn(List<String> values) {
            addCriterion("chargeNo in", values, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoNotIn(List<String> values) {
            addCriterion("chargeNo not in", values, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoBetween(String value1, String value2) {
            addCriterion("chargeNo between", value1, value2, "chargeno");
            return (Criteria) this;
        }

        public Criteria andChargenoNotBetween(String value1, String value2) {
            addCriterion("chargeNo not between", value1, value2, "chargeno");
            return (Criteria) this;
        }

        public Criteria andPlantimeIsNull() {
            addCriterion("planTime is null");
            return (Criteria) this;
        }

        public Criteria andPlantimeIsNotNull() {
            addCriterion("planTime is not null");
            return (Criteria) this;
        }

        public Criteria andPlantimeEqualTo(Date value) {
            addCriterion("planTime =", value, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeNotEqualTo(Date value) {
            addCriterion("planTime <>", value, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeGreaterThan(Date value) {
            addCriterion("planTime >", value, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeGreaterThanOrEqualTo(Date value) {
            addCriterion("planTime >=", value, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeLessThan(Date value) {
            addCriterion("planTime <", value, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeLessThanOrEqualTo(Date value) {
            addCriterion("planTime <=", value, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeIn(List<Date> values) {
            addCriterion("planTime in", values, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeNotIn(List<Date> values) {
            addCriterion("planTime not in", values, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeBetween(Date value1, Date value2) {
            addCriterion("planTime between", value1, value2, "plantime");
            return (Criteria) this;
        }

        public Criteria andPlantimeNotBetween(Date value1, Date value2) {
            addCriterion("planTime not between", value1, value2, "plantime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeIsNull() {
            addCriterion("prepareTime is null");
            return (Criteria) this;
        }

        public Criteria andPreparetimeIsNotNull() {
            addCriterion("prepareTime is not null");
            return (Criteria) this;
        }

        public Criteria andPreparetimeEqualTo(Date value) {
            addCriterion("prepareTime =", value, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeNotEqualTo(Date value) {
            addCriterion("prepareTime <>", value, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeGreaterThan(Date value) {
            addCriterion("prepareTime >", value, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("prepareTime >=", value, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeLessThan(Date value) {
            addCriterion("prepareTime <", value, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeLessThanOrEqualTo(Date value) {
            addCriterion("prepareTime <=", value, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeIn(List<Date> values) {
            addCriterion("prepareTime in", values, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeNotIn(List<Date> values) {
            addCriterion("prepareTime not in", values, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeBetween(Date value1, Date value2) {
            addCriterion("prepareTime between", value1, value2, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparetimeNotBetween(Date value1, Date value2) {
            addCriterion("prepareTime not between", value1, value2, "preparetime");
            return (Criteria) this;
        }

        public Criteria andPreparestaffIsNull() {
            addCriterion("prepareStaff is null");
            return (Criteria) this;
        }

        public Criteria andPreparestaffIsNotNull() {
            addCriterion("prepareStaff is not null");
            return (Criteria) this;
        }

        public Criteria andPreparestaffEqualTo(String value) {
            addCriterion("prepareStaff =", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffNotEqualTo(String value) {
            addCriterion("prepareStaff <>", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffGreaterThan(String value) {
            addCriterion("prepareStaff >", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffGreaterThanOrEqualTo(String value) {
            addCriterion("prepareStaff >=", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffLessThan(String value) {
            addCriterion("prepareStaff <", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffLessThanOrEqualTo(String value) {
            addCriterion("prepareStaff <=", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffLike(String value) {
            addCriterion("prepareStaff like", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffNotLike(String value) {
            addCriterion("prepareStaff not like", value, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffIn(List<String> values) {
            addCriterion("prepareStaff in", values, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffNotIn(List<String> values) {
            addCriterion("prepareStaff not in", values, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffBetween(String value1, String value2) {
            addCriterion("prepareStaff between", value1, value2, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andPreparestaffNotBetween(String value1, String value2) {
            addCriterion("prepareStaff not between", value1, value2, "preparestaff");
            return (Criteria) this;
        }

        public Criteria andApprovetimeIsNull() {
            addCriterion("approveTime is null");
            return (Criteria) this;
        }

        public Criteria andApprovetimeIsNotNull() {
            addCriterion("approveTime is not null");
            return (Criteria) this;
        }

        public Criteria andApprovetimeEqualTo(Date value) {
            addCriterion("approveTime =", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeNotEqualTo(Date value) {
            addCriterion("approveTime <>", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeGreaterThan(Date value) {
            addCriterion("approveTime >", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("approveTime >=", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeLessThan(Date value) {
            addCriterion("approveTime <", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeLessThanOrEqualTo(Date value) {
            addCriterion("approveTime <=", value, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeIn(List<Date> values) {
            addCriterion("approveTime in", values, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeNotIn(List<Date> values) {
            addCriterion("approveTime not in", values, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeBetween(Date value1, Date value2) {
            addCriterion("approveTime between", value1, value2, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovetimeNotBetween(Date value1, Date value2) {
            addCriterion("approveTime not between", value1, value2, "approvetime");
            return (Criteria) this;
        }

        public Criteria andApprovestaffIsNull() {
            addCriterion("approveStaff is null");
            return (Criteria) this;
        }

        public Criteria andApprovestaffIsNotNull() {
            addCriterion("approveStaff is not null");
            return (Criteria) this;
        }

        public Criteria andApprovestaffEqualTo(String value) {
            addCriterion("approveStaff =", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffNotEqualTo(String value) {
            addCriterion("approveStaff <>", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffGreaterThan(String value) {
            addCriterion("approveStaff >", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffGreaterThanOrEqualTo(String value) {
            addCriterion("approveStaff >=", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffLessThan(String value) {
            addCriterion("approveStaff <", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffLessThanOrEqualTo(String value) {
            addCriterion("approveStaff <=", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffLike(String value) {
            addCriterion("approveStaff like", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffNotLike(String value) {
            addCriterion("approveStaff not like", value, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffIn(List<String> values) {
            addCriterion("approveStaff in", values, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffNotIn(List<String> values) {
            addCriterion("approveStaff not in", values, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffBetween(String value1, String value2) {
            addCriterion("approveStaff between", value1, value2, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andApprovestaffNotBetween(String value1, String value2) {
            addCriterion("approveStaff not between", value1, value2, "approvestaff");
            return (Criteria) this;
        }

        public Criteria andOrderlistIsNull() {
            addCriterion("orderList is null");
            return (Criteria) this;
        }

        public Criteria andOrderlistIsNotNull() {
            addCriterion("orderList is not null");
            return (Criteria) this;
        }

        public Criteria andOrderlistEqualTo(String value) {
            addCriterion("orderList =", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistNotEqualTo(String value) {
            addCriterion("orderList <>", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistGreaterThan(String value) {
            addCriterion("orderList >", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistGreaterThanOrEqualTo(String value) {
            addCriterion("orderList >=", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistLessThan(String value) {
            addCriterion("orderList <", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistLessThanOrEqualTo(String value) {
            addCriterion("orderList <=", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistLike(String value) {
            addCriterion("orderList like", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistNotLike(String value) {
            addCriterion("orderList not like", value, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistIn(List<String> values) {
            addCriterion("orderList in", values, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistNotIn(List<String> values) {
            addCriterion("orderList not in", values, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistBetween(String value1, String value2) {
            addCriterion("orderList between", value1, value2, "orderlist");
            return (Criteria) this;
        }

        public Criteria andOrderlistNotBetween(String value1, String value2) {
            addCriterion("orderList not between", value1, value2, "orderlist");
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

        public Criteria andBilletlengthEqualTo(String value) {
            addCriterion("billetLength =", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotEqualTo(String value) {
            addCriterion("billetLength <>", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthGreaterThan(String value) {
            addCriterion("billetLength >", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthGreaterThanOrEqualTo(String value) {
            addCriterion("billetLength >=", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthLessThan(String value) {
            addCriterion("billetLength <", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthLessThanOrEqualTo(String value) {
            addCriterion("billetLength <=", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthLike(String value) {
            addCriterion("billetLength like", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotLike(String value) {
            addCriterion("billetLength not like", value, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthIn(List<String> values) {
            addCriterion("billetLength in", values, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotIn(List<String> values) {
            addCriterion("billetLength not in", values, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthBetween(String value1, String value2) {
            addCriterion("billetLength between", value1, value2, "billetlength");
            return (Criteria) this;
        }

        public Criteria andBilletlengthNotBetween(String value1, String value2) {
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

        public Criteria andBilletwidthEqualTo(String value) {
            addCriterion("billetWidth =", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotEqualTo(String value) {
            addCriterion("billetWidth <>", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthGreaterThan(String value) {
            addCriterion("billetWidth >", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthGreaterThanOrEqualTo(String value) {
            addCriterion("billetWidth >=", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthLessThan(String value) {
            addCriterion("billetWidth <", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthLessThanOrEqualTo(String value) {
            addCriterion("billetWidth <=", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthLike(String value) {
            addCriterion("billetWidth like", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotLike(String value) {
            addCriterion("billetWidth not like", value, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthIn(List<String> values) {
            addCriterion("billetWidth in", values, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotIn(List<String> values) {
            addCriterion("billetWidth not in", values, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthBetween(String value1, String value2) {
            addCriterion("billetWidth between", value1, value2, "billetwidth");
            return (Criteria) this;
        }

        public Criteria andBilletwidthNotBetween(String value1, String value2) {
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

        public Criteria andBilletthickEqualTo(String value) {
            addCriterion("billetThick =", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotEqualTo(String value) {
            addCriterion("billetThick <>", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickGreaterThan(String value) {
            addCriterion("billetThick >", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickGreaterThanOrEqualTo(String value) {
            addCriterion("billetThick >=", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickLessThan(String value) {
            addCriterion("billetThick <", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickLessThanOrEqualTo(String value) {
            addCriterion("billetThick <=", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickLike(String value) {
            addCriterion("billetThick like", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotLike(String value) {
            addCriterion("billetThick not like", value, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickIn(List<String> values) {
            addCriterion("billetThick in", values, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotIn(List<String> values) {
            addCriterion("billetThick not in", values, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickBetween(String value1, String value2) {
            addCriterion("billetThick between", value1, value2, "billetthick");
            return (Criteria) this;
        }

        public Criteria andBilletthickNotBetween(String value1, String value2) {
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

        public Criteria andTargettimeEqualTo(String value) {
            addCriterion("targetTime =", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotEqualTo(String value) {
            addCriterion("targetTime <>", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeGreaterThan(String value) {
            addCriterion("targetTime >", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeGreaterThanOrEqualTo(String value) {
            addCriterion("targetTime >=", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeLessThan(String value) {
            addCriterion("targetTime <", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeLessThanOrEqualTo(String value) {
            addCriterion("targetTime <=", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeLike(String value) {
            addCriterion("targetTime like", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotLike(String value) {
            addCriterion("targetTime not like", value, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeIn(List<String> values) {
            addCriterion("targetTime in", values, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotIn(List<String> values) {
            addCriterion("targetTime not in", values, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeBetween(String value1, String value2) {
            addCriterion("targetTime between", value1, value2, "targettime");
            return (Criteria) this;
        }

        public Criteria andTargettimeNotBetween(String value1, String value2) {
            addCriterion("targetTime not between", value1, value2, "targettime");
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

        public Criteria andAssigncastIsNull() {
            addCriterion("assignCast is null");
            return (Criteria) this;
        }

        public Criteria andAssigncastIsNotNull() {
            addCriterion("assignCast is not null");
            return (Criteria) this;
        }

        public Criteria andAssigncastEqualTo(String value) {
            addCriterion("assignCast =", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastNotEqualTo(String value) {
            addCriterion("assignCast <>", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastGreaterThan(String value) {
            addCriterion("assignCast >", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastGreaterThanOrEqualTo(String value) {
            addCriterion("assignCast >=", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastLessThan(String value) {
            addCriterion("assignCast <", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastLessThanOrEqualTo(String value) {
            addCriterion("assignCast <=", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastLike(String value) {
            addCriterion("assignCast like", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastNotLike(String value) {
            addCriterion("assignCast not like", value, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastIn(List<String> values) {
            addCriterion("assignCast in", values, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastNotIn(List<String> values) {
            addCriterion("assignCast not in", values, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastBetween(String value1, String value2) {
            addCriterion("assignCast between", value1, value2, "assigncast");
            return (Criteria) this;
        }

        public Criteria andAssigncastNotBetween(String value1, String value2) {
            addCriterion("assignCast not between", value1, value2, "assigncast");
            return (Criteria) this;
        }

        public Criteria andCastseqIsNull() {
            addCriterion("castSeq is null");
            return (Criteria) this;
        }

        public Criteria andCastseqIsNotNull() {
            addCriterion("castSeq is not null");
            return (Criteria) this;
        }

        public Criteria andCastseqEqualTo(String value) {
            addCriterion("castSeq =", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqNotEqualTo(String value) {
            addCriterion("castSeq <>", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqGreaterThan(String value) {
            addCriterion("castSeq >", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqGreaterThanOrEqualTo(String value) {
            addCriterion("castSeq >=", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqLessThan(String value) {
            addCriterion("castSeq <", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqLessThanOrEqualTo(String value) {
            addCriterion("castSeq <=", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqLike(String value) {
            addCriterion("castSeq like", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqNotLike(String value) {
            addCriterion("castSeq not like", value, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqIn(List<String> values) {
            addCriterion("castSeq in", values, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqNotIn(List<String> values) {
            addCriterion("castSeq not in", values, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqBetween(String value1, String value2) {
            addCriterion("castSeq between", value1, value2, "castseq");
            return (Criteria) this;
        }

        public Criteria andCastseqNotBetween(String value1, String value2) {
            addCriterion("castSeq not between", value1, value2, "castseq");
            return (Criteria) this;
        }

        public Criteria andIrontimeIsNull() {
            addCriterion("ironTime is null");
            return (Criteria) this;
        }

        public Criteria andIrontimeIsNotNull() {
            addCriterion("ironTime is not null");
            return (Criteria) this;
        }

        public Criteria andIrontimeEqualTo(Date value) {
            addCriterion("ironTime =", value, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeNotEqualTo(Date value) {
            addCriterion("ironTime <>", value, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeGreaterThan(Date value) {
            addCriterion("ironTime >", value, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ironTime >=", value, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeLessThan(Date value) {
            addCriterion("ironTime <", value, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeLessThanOrEqualTo(Date value) {
            addCriterion("ironTime <=", value, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeIn(List<Date> values) {
            addCriterion("ironTime in", values, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeNotIn(List<Date> values) {
            addCriterion("ironTime not in", values, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeBetween(Date value1, Date value2) {
            addCriterion("ironTime between", value1, value2, "irontime");
            return (Criteria) this;
        }

        public Criteria andIrontimeNotBetween(Date value1, Date value2) {
            addCriterion("ironTime not between", value1, value2, "irontime");
            return (Criteria) this;
        }

        public Criteria andIronnoIsNull() {
            addCriterion("IronNo is null");
            return (Criteria) this;
        }

        public Criteria andIronnoIsNotNull() {
            addCriterion("IronNo is not null");
            return (Criteria) this;
        }

        public Criteria andIronnoEqualTo(String value) {
            addCriterion("IronNo =", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoNotEqualTo(String value) {
            addCriterion("IronNo <>", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoGreaterThan(String value) {
            addCriterion("IronNo >", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoGreaterThanOrEqualTo(String value) {
            addCriterion("IronNo >=", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoLessThan(String value) {
            addCriterion("IronNo <", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoLessThanOrEqualTo(String value) {
            addCriterion("IronNo <=", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoLike(String value) {
            addCriterion("IronNo like", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoNotLike(String value) {
            addCriterion("IronNo not like", value, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoIn(List<String> values) {
            addCriterion("IronNo in", values, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoNotIn(List<String> values) {
            addCriterion("IronNo not in", values, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoBetween(String value1, String value2) {
            addCriterion("IronNo between", value1, value2, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronnoNotBetween(String value1, String value2) {
            addCriterion("IronNo not between", value1, value2, "IronNo");
            return (Criteria) this;
        }

        public Criteria andIronseqIsNull() {
            addCriterion("IronSeq is null");
            return (Criteria) this;
        }

        public Criteria andIronseqIsNotNull() {
            addCriterion("IronSeq is not null");
            return (Criteria) this;
        }

        public Criteria andIronseqEqualTo(String value) {
            addCriterion("IronSeq =", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqNotEqualTo(String value) {
            addCriterion("IronSeq <>", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqGreaterThan(String value) {
            addCriterion("IronSeq >", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqGreaterThanOrEqualTo(String value) {
            addCriterion("IronSeq >=", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqLessThan(String value) {
            addCriterion("IronSeq <", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqLessThanOrEqualTo(String value) {
            addCriterion("IronSeq <=", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqLike(String value) {
            addCriterion("IronSeq like", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqNotLike(String value) {
            addCriterion("IronSeq not like", value, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqIn(List<String> values) {
            addCriterion("IronSeq in", values, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqNotIn(List<String> values) {
            addCriterion("IronSeq not in", values, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqBetween(String value1, String value2) {
            addCriterion("IronSeq between", value1, value2, "IronSeq");
            return (Criteria) this;
        }

        public Criteria andIronseqNotBetween(String value1, String value2) {
            addCriterion("IronSeq not between", value1, value2, "IronSeq");
            return (Criteria) this;
        }


        public Criteria andPlanstatusIsNull() {
            addCriterion("planStatus is null");
            return (Criteria) this;
        }

        public Criteria andPlanstatusIsNotNull() {
            addCriterion("planStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPlanstatusEqualTo(String value) {
            addCriterion("planStatus =", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusNotEqualTo(String value) {
            addCriterion("planStatus <>", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusGreaterThan(String value) {
            addCriterion("planStatus >", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusGreaterThanOrEqualTo(String value) {
            addCriterion("planStatus >=", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusLessThan(String value) {
            addCriterion("planStatus <", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusLessThanOrEqualTo(String value) {
            addCriterion("planStatus <=", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusLike(String value) {
            addCriterion("planStatus like", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusNotLike(String value) {
            addCriterion("planStatus not like", value, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusIn(List<String> values) {
            addCriterion("planStatus in", values, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusNotIn(List<String> values) {
            addCriterion("planStatus not in", values, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusBetween(String value1, String value2) {
            addCriterion("planStatus between", value1, value2, "planstatus");
            return (Criteria) this;
        }

        public Criteria andPlanstatusNotBetween(String value1, String value2) {
            addCriterion("planStatus not between", value1, value2, "planstatus");
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