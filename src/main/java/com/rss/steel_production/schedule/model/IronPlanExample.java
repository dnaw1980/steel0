package com.rss.steel_production.schedule.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IronPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IronPlanExample() {
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

        public Criteria andIronPlanuidIsNull() {
            addCriterion("iron_planUID is null");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidIsNotNull() {
            addCriterion("iron_planUID is not null");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidEqualTo(String value) {
            addCriterion("iron_planUID =", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidNotEqualTo(String value) {
            addCriterion("iron_planUID <>", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidGreaterThan(String value) {
            addCriterion("iron_planUID >", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidGreaterThanOrEqualTo(String value) {
            addCriterion("iron_planUID >=", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidLessThan(String value) {
            addCriterion("iron_planUID <", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidLessThanOrEqualTo(String value) {
            addCriterion("iron_planUID <=", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidLike(String value) {
            addCriterion("iron_planUID like", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidNotLike(String value) {
            addCriterion("iron_planUID not like", value, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidIn(List<String> values) {
            addCriterion("iron_planUID in", values, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidNotIn(List<String> values) {
            addCriterion("iron_planUID not in", values, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidBetween(String value1, String value2) {
            addCriterion("iron_planUID between", value1, value2, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronPlanuidNotBetween(String value1, String value2) {
            addCriterion("iron_planUID not between", value1, value2, "ironPlanuid");
            return (Criteria) this;
        }

        public Criteria andIronnoIsNull() {
            addCriterion("ironNo is null");
            return (Criteria) this;
        }

        public Criteria andIronnoIsNotNull() {
            addCriterion("ironNo is not null");
            return (Criteria) this;
        }

        public Criteria andIronnoEqualTo(String value) {
            addCriterion("ironNo =", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoNotEqualTo(String value) {
            addCriterion("ironNo <>", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoGreaterThan(String value) {
            addCriterion("ironNo >", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoGreaterThanOrEqualTo(String value) {
            addCriterion("ironNo >=", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoLessThan(String value) {
            addCriterion("ironNo <", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoLessThanOrEqualTo(String value) {
            addCriterion("ironNo <=", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoLike(String value) {
            addCriterion("ironNo like", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoNotLike(String value) {
            addCriterion("ironNo not like", value, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoIn(List<String> values) {
            addCriterion("ironNo in", values, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoNotIn(List<String> values) {
            addCriterion("ironNo not in", values, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoBetween(String value1, String value2) {
            addCriterion("ironNo between", value1, value2, "ironno");
            return (Criteria) this;
        }

        public Criteria andIronnoNotBetween(String value1, String value2) {
            addCriterion("ironNo not between", value1, value2, "ironno");
            return (Criteria) this;
        }

        public Criteria andSeqnoIsNull() {
            addCriterion("seqNo is null");
            return (Criteria) this;
        }

        public Criteria andSeqnoIsNotNull() {
            addCriterion("seqNo is not null");
            return (Criteria) this;
        }

        public Criteria andSeqnoEqualTo(String value) {
            addCriterion("seqNo =", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotEqualTo(String value) {
            addCriterion("seqNo <>", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThan(String value) {
            addCriterion("seqNo >", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThanOrEqualTo(String value) {
            addCriterion("seqNo >=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThan(String value) {
            addCriterion("seqNo <", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThanOrEqualTo(String value) {
            addCriterion("seqNo <=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLike(String value) {
            addCriterion("seqNo like", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotLike(String value) {
            addCriterion("seqNo not like", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoIn(List<String> values) {
            addCriterion("seqNo in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotIn(List<String> values) {
            addCriterion("seqNo not in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoBetween(String value1, String value2) {
            addCriterion("seqNo between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotBetween(String value1, String value2) {
            addCriterion("seqNo not between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andTappingtimeIsNull() {
            addCriterion("tappingTime is null");
            return (Criteria) this;
        }

        public Criteria andTappingtimeIsNotNull() {
            addCriterion("tappingTime is not null");
            return (Criteria) this;
        }

        public Criteria andTappingtimeEqualTo(Date value) {
            addCriterion("tappingTime =", value, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeNotEqualTo(Date value) {
            addCriterion("tappingTime <>", value, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeGreaterThan(Date value) {
            addCriterion("tappingTime >", value, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tappingTime >=", value, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeLessThan(Date value) {
            addCriterion("tappingTime <", value, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeLessThanOrEqualTo(Date value) {
            addCriterion("tappingTime <=", value, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeIn(List<Date> values) {
            addCriterion("tappingTime in", values, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeNotIn(List<Date> values) {
            addCriterion("tappingTime not in", values, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeBetween(Date value1, Date value2) {
            addCriterion("tappingTime between", value1, value2, "tappingtime");
            return (Criteria) this;
        }

        public Criteria andTappingtimeNotBetween(Date value1, Date value2) {
            addCriterion("tappingTime not between", value1, value2, "tappingtime");
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

        public Criteria andIrondirectionIsNull() {
            addCriterion("ironDirection is null");
            return (Criteria) this;
        }

        public Criteria andIrondirectionIsNotNull() {
            addCriterion("ironDirection is not null");
            return (Criteria) this;
        }

        public Criteria andIrondirectionEqualTo(String value) {
            addCriterion("ironDirection =", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionNotEqualTo(String value) {
            addCriterion("ironDirection <>", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionGreaterThan(String value) {
            addCriterion("ironDirection >", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionGreaterThanOrEqualTo(String value) {
            addCriterion("ironDirection >=", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionLessThan(String value) {
            addCriterion("ironDirection <", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionLessThanOrEqualTo(String value) {
            addCriterion("ironDirection <=", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionLike(String value) {
            addCriterion("ironDirection like", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionNotLike(String value) {
            addCriterion("ironDirection not like", value, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionIn(List<String> values) {
            addCriterion("ironDirection in", values, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionNotIn(List<String> values) {
            addCriterion("ironDirection not in", values, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionBetween(String value1, String value2) {
            addCriterion("ironDirection between", value1, value2, "irondirection");
            return (Criteria) this;
        }

        public Criteria andIrondirectionNotBetween(String value1, String value2) {
            addCriterion("ironDirection not between", value1, value2, "irondirection");
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