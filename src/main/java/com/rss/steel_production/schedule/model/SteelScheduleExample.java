package com.rss.steel_production.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SteelScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SteelScheduleExample() {
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

        public Criteria andSteelScheduleuidIsNull() {
            addCriterion("steel_scheduleUID is null");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidIsNotNull() {
            addCriterion("steel_scheduleUID is not null");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidEqualTo(String value) {
            addCriterion("steel_scheduleUID =", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidNotEqualTo(String value) {
            addCriterion("steel_scheduleUID <>", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidGreaterThan(String value) {
            addCriterion("steel_scheduleUID >", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidGreaterThanOrEqualTo(String value) {
            addCriterion("steel_scheduleUID >=", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidLessThan(String value) {
            addCriterion("steel_scheduleUID <", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidLessThanOrEqualTo(String value) {
            addCriterion("steel_scheduleUID <=", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidLike(String value) {
            addCriterion("steel_scheduleUID like", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidNotLike(String value) {
            addCriterion("steel_scheduleUID not like", value, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidIn(List<String> values) {
            addCriterion("steel_scheduleUID in", values, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidNotIn(List<String> values) {
            addCriterion("steel_scheduleUID not in", values, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidBetween(String value1, String value2) {
            addCriterion("steel_scheduleUID between", value1, value2, "steelScheduleuid");
            return (Criteria) this;
        }

        public Criteria andSteelScheduleuidNotBetween(String value1, String value2) {
            addCriterion("steel_scheduleUID not between", value1, value2, "steelScheduleuid");
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

        public Criteria andCastnoIsNull() {
            addCriterion("castNo is null");
            return (Criteria) this;
        }

        public Criteria andCastnoIsNotNull() {
            addCriterion("castNo is not null");
            return (Criteria) this;
        }

        public Criteria andCastnoEqualTo(String value) {
            addCriterion("castNo =", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoNotEqualTo(String value) {
            addCriterion("castNo <>", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoGreaterThan(String value) {
            addCriterion("castNo >", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoGreaterThanOrEqualTo(String value) {
            addCriterion("castNo >=", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoLessThan(String value) {
            addCriterion("castNo <", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoLessThanOrEqualTo(String value) {
            addCriterion("castNo <=", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoLike(String value) {
            addCriterion("castNo like", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoNotLike(String value) {
            addCriterion("castNo not like", value, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoIn(List<String> values) {
            addCriterion("castNo in", values, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoNotIn(List<String> values) {
            addCriterion("castNo not in", values, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoBetween(String value1, String value2) {
            addCriterion("castNo between", value1, value2, "castno");
            return (Criteria) this;
        }

        public Criteria andCastnoNotBetween(String value1, String value2) {
            addCriterion("castNo not between", value1, value2, "castno");
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

        public Criteria andPlanenterIsNull() {
            addCriterion("planEnter is null");
            return (Criteria) this;
        }

        public Criteria andPlanenterIsNotNull() {
            addCriterion("planEnter is not null");
            return (Criteria) this;
        }

        public Criteria andPlanenterEqualTo(Date value) {
            addCriterion("planEnter =", value, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterNotEqualTo(Date value) {
            addCriterion("planEnter <>", value, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterGreaterThan(Date value) {
            addCriterion("planEnter >", value, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterGreaterThanOrEqualTo(Date value) {
            addCriterion("planEnter >=", value, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterLessThan(Date value) {
            addCriterion("planEnter <", value, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterLessThanOrEqualTo(Date value) {
            addCriterion("planEnter <=", value, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterIn(List<Date> values) {
            addCriterion("planEnter in", values, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterNotIn(List<Date> values) {
            addCriterion("planEnter not in", values, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterBetween(Date value1, Date value2) {
            addCriterion("planEnter between", value1, value2, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanenterNotBetween(Date value1, Date value2) {
            addCriterion("planEnter not between", value1, value2, "planenter");
            return (Criteria) this;
        }

        public Criteria andPlanexitIsNull() {
            addCriterion("planExit is null");
            return (Criteria) this;
        }

        public Criteria andPlanexitIsNotNull() {
            addCriterion("planExit is not null");
            return (Criteria) this;
        }

        public Criteria andPlanexitEqualTo(Date value) {
            addCriterion("planExit =", value, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitNotEqualTo(Date value) {
            addCriterion("planExit <>", value, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitGreaterThan(Date value) {
            addCriterion("planExit >", value, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitGreaterThanOrEqualTo(Date value) {
            addCriterion("planExit >=", value, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitLessThan(Date value) {
            addCriterion("planExit <", value, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitLessThanOrEqualTo(Date value) {
            addCriterion("planExit <=", value, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitIn(List<Date> values) {
            addCriterion("planExit in", values, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitNotIn(List<Date> values) {
            addCriterion("planExit not in", values, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitBetween(Date value1, Date value2) {
            addCriterion("planExit between", value1, value2, "planexit");
            return (Criteria) this;
        }

        public Criteria andPlanexitNotBetween(Date value1, Date value2) {
            addCriterion("planExit not between", value1, value2, "planexit");
            return (Criteria) this;
        }

        public Criteria andActualenterIsNull() {
            addCriterion("actualEnter is null");
            return (Criteria) this;
        }

        public Criteria andActualenterIsNotNull() {
            addCriterion("actualEnter is not null");
            return (Criteria) this;
        }

        public Criteria andActualenterEqualTo(Date value) {
            addCriterion("actualEnter =", value, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterNotEqualTo(Date value) {
            addCriterion("actualEnter <>", value, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterGreaterThan(Date value) {
            addCriterion("actualEnter >", value, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterGreaterThanOrEqualTo(Date value) {
            addCriterion("actualEnter >=", value, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterLessThan(Date value) {
            addCriterion("actualEnter <", value, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterLessThanOrEqualTo(Date value) {
            addCriterion("actualEnter <=", value, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterIn(List<Date> values) {
            addCriterion("actualEnter in", values, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterNotIn(List<Date> values) {
            addCriterion("actualEnter not in", values, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterBetween(Date value1, Date value2) {
            addCriterion("actualEnter between", value1, value2, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualenterNotBetween(Date value1, Date value2) {
            addCriterion("actualEnter not between", value1, value2, "actualenter");
            return (Criteria) this;
        }

        public Criteria andActualexitIsNull() {
            addCriterion("actualExit is null");
            return (Criteria) this;
        }

        public Criteria andActualexitIsNotNull() {
            addCriterion("actualExit is not null");
            return (Criteria) this;
        }

        public Criteria andActualexitEqualTo(Date value) {
            addCriterion("actualExit =", value, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitNotEqualTo(Date value) {
            addCriterion("actualExit <>", value, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitGreaterThan(Date value) {
            addCriterion("actualExit >", value, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitGreaterThanOrEqualTo(Date value) {
            addCriterion("actualExit >=", value, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitLessThan(Date value) {
            addCriterion("actualExit <", value, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitLessThanOrEqualTo(Date value) {
            addCriterion("actualExit <=", value, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitIn(List<Date> values) {
            addCriterion("actualExit in", values, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitNotIn(List<Date> values) {
            addCriterion("actualExit not in", values, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitBetween(Date value1, Date value2) {
            addCriterion("actualExit between", value1, value2, "actualexit");
            return (Criteria) this;
        }

        public Criteria andActualexitNotBetween(Date value1, Date value2) {
            addCriterion("actualExit not between", value1, value2, "actualexit");
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