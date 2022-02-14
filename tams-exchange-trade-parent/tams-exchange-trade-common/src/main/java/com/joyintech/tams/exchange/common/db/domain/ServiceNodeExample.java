package com.joyintech.tams.exchange.common.db.domain;

import java.util.ArrayList;
import java.util.List;

public class ServiceNodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServiceNodeExample() {
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

        public Criteria andNodeNameIsNull() {
            addCriterion("NODE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("NODE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("NODE_NAME =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("NODE_NAME <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("NODE_NAME >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("NODE_NAME >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("NODE_NAME <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("NODE_NAME <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("NODE_NAME like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("NODE_NAME not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("NODE_NAME in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("NODE_NAME not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("NODE_NAME between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("NODE_NAME not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeAddressIsNull() {
            addCriterion("NODE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andNodeAddressIsNotNull() {
            addCriterion("NODE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andNodeAddressEqualTo(String value) {
            addCriterion("NODE_ADDRESS =", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressNotEqualTo(String value) {
            addCriterion("NODE_ADDRESS <>", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressGreaterThan(String value) {
            addCriterion("NODE_ADDRESS >", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("NODE_ADDRESS >=", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressLessThan(String value) {
            addCriterion("NODE_ADDRESS <", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressLessThanOrEqualTo(String value) {
            addCriterion("NODE_ADDRESS <=", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressLike(String value) {
            addCriterion("NODE_ADDRESS like", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressNotLike(String value) {
            addCriterion("NODE_ADDRESS not like", value, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressIn(List<String> values) {
            addCriterion("NODE_ADDRESS in", values, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressNotIn(List<String> values) {
            addCriterion("NODE_ADDRESS not in", values, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressBetween(String value1, String value2) {
            addCriterion("NODE_ADDRESS between", value1, value2, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeAddressNotBetween(String value1, String value2) {
            addCriterion("NODE_ADDRESS not between", value1, value2, "nodeAddress");
            return (Criteria) this;
        }

        public Criteria andNodeWeightIsNull() {
            addCriterion("NODE_WEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andNodeWeightIsNotNull() {
            addCriterion("NODE_WEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andNodeWeightEqualTo(int value) {
            addCriterion("NODE_WEIGHT =", value, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightNotEqualTo(int value) {
            addCriterion("NODE_WEIGHT <>", value, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightGreaterThan(int value) {
            addCriterion("NODE_WEIGHT >", value, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightGreaterThanOrEqualTo(int value) {
            addCriterion("NODE_WEIGHT >=", value, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightLessThan(int value) {
            addCriterion("NODE_WEIGHT <", value, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightLessThanOrEqualTo(int value) {
            addCriterion("NODE_WEIGHT <=", value, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightIn(List<Integer> values) {
            addCriterion("NODE_WEIGHT in", values, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightNotIn(List<Integer> values) {
            addCriterion("NODE_WEIGHT not in", values, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightBetween(int value1, int value2) {
            addCriterion("NODE_WEIGHT between", value1, value2, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeWeightNotBetween(int value1, int value2) {
            addCriterion("NODE_WEIGHT not between", value1, value2, "nodeWeight");
            return (Criteria) this;
        }

        public Criteria andNodeTypeIsNull() {
            addCriterion("NODE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andNodeTypeIsNotNull() {
            addCriterion("NODE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andNodeTypeEqualTo(String value) {
            addCriterion("NODE_TYPE =", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotEqualTo(String value) {
            addCriterion("NODE_TYPE <>", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeGreaterThan(String value) {
            addCriterion("NODE_TYPE >", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("NODE_TYPE >=", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeLessThan(String value) {
            addCriterion("NODE_TYPE <", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeLessThanOrEqualTo(String value) {
            addCriterion("NODE_TYPE <=", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeLike(String value) {
            addCriterion("NODE_TYPE like", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotLike(String value) {
            addCriterion("NODE_TYPE not like", value, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeIn(List<String> values) {
            addCriterion("NODE_TYPE in", values, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotIn(List<String> values) {
            addCriterion("NODE_TYPE not in", values, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeBetween(String value1, String value2) {
            addCriterion("NODE_TYPE between", value1, value2, "nodeType");
            return (Criteria) this;
        }

        public Criteria andNodeTypeNotBetween(String value1, String value2) {
            addCriterion("NODE_TYPE not between", value1, value2, "nodeType");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeIsNull() {
            addCriterion("LAST_LIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeIsNotNull() {
            addCriterion("LAST_LIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeEqualTo(long value) {
            addCriterion("LAST_LIVE_TIME =", value, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeNotEqualTo(long value) {
            addCriterion("LAST_LIVE_TIME <>", value, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeGreaterThan(long value) {
            addCriterion("LAST_LIVE_TIME >", value, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeGreaterThanOrEqualTo(long value) {
            addCriterion("LAST_LIVE_TIME >=", value, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeLessThan(long value) {
            addCriterion("LAST_LIVE_TIME <", value, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeLessThanOrEqualTo(long value) {
            addCriterion("LAST_LIVE_TIME <=", value, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeIn(List<Long> values) {
            addCriterion("LAST_LIVE_TIME in", values, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeNotIn(List<Long> values) {
            addCriterion("LAST_LIVE_TIME not in", values, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeBetween(long value1, long value2) {
            addCriterion("LAST_LIVE_TIME between", value1, value2, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andLastLiveTimeNotBetween(long value1, long value2) {
            addCriterion("LAST_LIVE_TIME not between", value1, value2, "lastLiveTime");
            return (Criteria) this;
        }

        public Criteria andMasterNodeIsNull() {
            addCriterion("MASTER_NODE is null");
            return (Criteria) this;
        }

        public Criteria andMasterNodeIsNotNull() {
            addCriterion("MASTER_NODE is not null");
            return (Criteria) this;
        }

        public Criteria andMasterNodeEqualTo(int value) {
            addCriterion("MASTER_NODE =", value, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeNotEqualTo(int value) {
            addCriterion("MASTER_NODE <>", value, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeGreaterThan(int value) {
            addCriterion("MASTER_NODE >", value, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeGreaterThanOrEqualTo(int value) {
            addCriterion("MASTER_NODE >=", value, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeLessThan(int value) {
            addCriterion("MASTER_NODE <", value, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeLessThanOrEqualTo(int value) {
            addCriterion("MASTER_NODE <=", value, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeIn(List<Integer> values) {
            addCriterion("MASTER_NODE in", values, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeNotIn(List<Integer> values) {
            addCriterion("MASTER_NODE not in", values, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeBetween(int value1, int value2) {
            addCriterion("MASTER_NODE between", value1, value2, "masterNode");
            return (Criteria) this;
        }

        public Criteria andMasterNodeNotBetween(int value1, int value2) {
            addCriterion("MASTER_NODE not between", value1, value2, "masterNode");
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