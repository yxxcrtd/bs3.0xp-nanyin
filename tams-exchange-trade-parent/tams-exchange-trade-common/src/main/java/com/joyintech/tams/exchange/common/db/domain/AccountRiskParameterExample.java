package com.joyintech.tams.exchange.common.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountRiskParameterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccountRiskParameterExample() {
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

        public Criteria andAccountIdIsNull() {
            addCriterion("ACCOUNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("ACCOUNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(String value) {
            addCriterion("ACCOUNT_ID =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(String value) {
            addCriterion("ACCOUNT_ID <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(String value) {
            addCriterion("ACCOUNT_ID >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_ID >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(String value) {
            addCriterion("ACCOUNT_ID <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_ID <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLike(String value) {
            addCriterion("ACCOUNT_ID like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotLike(String value) {
            addCriterion("ACCOUNT_ID not like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<String> values) {
            addCriterion("ACCOUNT_ID in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<String> values) {
            addCriterion("ACCOUNT_ID not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(String value1, String value2) {
            addCriterion("ACCOUNT_ID between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_ID not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andFlowIsNull() {
            addCriterion("FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFlowIsNotNull() {
            addCriterion("FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFlowEqualTo(BigDecimal value) {
            addCriterion("FLOW =", value, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowNotEqualTo(BigDecimal value) {
            addCriterion("FLOW <>", value, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowGreaterThan(BigDecimal value) {
            addCriterion("FLOW >", value, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("FLOW >=", value, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowLessThan(BigDecimal value) {
            addCriterion("FLOW <", value, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowLessThanOrEqualTo(BigDecimal value) {
            addCriterion("FLOW <=", value, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowIn(List<BigDecimal> values) {
            addCriterion("FLOW in", values, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowNotIn(List<BigDecimal> values) {
            addCriterion("FLOW not in", values, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FLOW between", value1, value2, "flow");
            return (Criteria) this;
        }

        public Criteria andFlowNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("FLOW not between", value1, value2, "flow");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNull() {
            addCriterion("TOTAL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("TOTAL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COUNT =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COUNT <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(BigDecimal value) {
            addCriterion("TOTAL_COUNT >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COUNT >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(BigDecimal value) {
            addCriterion("TOTAL_COUNT <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TOTAL_COUNT <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<BigDecimal> values) {
            addCriterion("TOTAL_COUNT in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<BigDecimal> values) {
            addCriterion("TOTAL_COUNT not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_COUNT between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TOTAL_COUNT not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andCancelRatioIsNull() {
            addCriterion("CANCEL_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andCancelRatioIsNotNull() {
            addCriterion("CANCEL_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andCancelRatioEqualTo(BigDecimal value) {
            addCriterion("CANCEL_RATIO =", value, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioNotEqualTo(BigDecimal value) {
            addCriterion("CANCEL_RATIO <>", value, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioGreaterThan(BigDecimal value) {
            addCriterion("CANCEL_RATIO >", value, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CANCEL_RATIO >=", value, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioLessThan(BigDecimal value) {
            addCriterion("CANCEL_RATIO <", value, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CANCEL_RATIO <=", value, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioIn(List<BigDecimal> values) {
            addCriterion("CANCEL_RATIO in", values, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioNotIn(List<BigDecimal> values) {
            addCriterion("CANCEL_RATIO not in", values, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CANCEL_RATIO between", value1, value2, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andCancelRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CANCEL_RATIO not between", value1, value2, "cancelRatio");
            return (Criteria) this;
        }

        public Criteria andSelfTradeIsNull() {
            addCriterion("SELF_TRADE is null");
            return (Criteria) this;
        }

        public Criteria andSelfTradeIsNotNull() {
            addCriterion("SELF_TRADE is not null");
            return (Criteria) this;
        }

        public Criteria andSelfTradeEqualTo(boolean value) {
            addCriterion("SELF_TRADE =", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeNotEqualTo(boolean value) {
            addCriterion("SELF_TRADE <>", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeGreaterThan(boolean value) {
            addCriterion("SELF_TRADE >", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeGreaterThanOrEqualTo(boolean value) {
            addCriterion("SELF_TRADE >=", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeLessThan(boolean value) {
            addCriterion("SELF_TRADE <", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeLessThanOrEqualTo(boolean value) {
            addCriterion("SELF_TRADE <=", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeLike(boolean value) {
            addCriterion("SELF_TRADE like", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeNotLike(boolean value) {
            addCriterion("SELF_TRADE not like", value, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeIn(List<Boolean> values) {
            addCriterion("SELF_TRADE in", values, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeNotIn(List<Boolean> values) {
            addCriterion("SELF_TRADE not in", values, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeBetween(boolean value1, boolean value2) {
            addCriterion("SELF_TRADE between", value1, value2, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andSelfTradeNotBetween(boolean value1, boolean value2) {
            addCriterion("SELF_TRADE not between", value1, value2, "selfTrade");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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