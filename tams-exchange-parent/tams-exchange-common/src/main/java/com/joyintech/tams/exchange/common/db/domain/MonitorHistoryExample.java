package com.joyintech.tams.exchange.common.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonitorHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MonitorHistoryExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNull() {
            addCriterion("RECORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("RECORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("RECORD_ID =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("RECORD_ID <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("RECORD_ID >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_ID >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("RECORD_ID <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("RECORD_ID <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("RECORD_ID like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("RECORD_ID not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("RECORD_ID in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("RECORD_ID not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("RECORD_ID between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
            addCriterion("RECORD_ID not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNull() {
            addCriterion("RECORD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIsNotNull() {
            addCriterion("RECORD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRecordTimeEqualTo(String value) {
            addCriterion("RECORD_TIME =", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotEqualTo(String value) {
            addCriterion("RECORD_TIME <>", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThan(String value) {
            addCriterion("RECORD_TIME >", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_TIME >=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThan(String value) {
            addCriterion("RECORD_TIME <", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLessThanOrEqualTo(String value) {
            addCriterion("RECORD_TIME <=", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeLike(String value) {
            addCriterion("RECORD_TIME like", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotLike(String value) {
            addCriterion("RECORD_TIME not like", value, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeIn(List<String> values) {
            addCriterion("RECORD_TIME in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotIn(List<String> values) {
            addCriterion("RECORD_TIME not in", values, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeBetween(String value1, String value2) {
            addCriterion("RECORD_TIME between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordTimeNotBetween(String value1, String value2) {
            addCriterion("RECORD_TIME not between", value1, value2, "recordTime");
            return (Criteria) this;
        }

        public Criteria andRecordNameIsNull() {
            addCriterion("RECORD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRecordNameIsNotNull() {
            addCriterion("RECORD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRecordNameEqualTo(String value) {
            addCriterion("RECORD_NAME =", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameNotEqualTo(String value) {
            addCriterion("RECORD_NAME <>", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameGreaterThan(String value) {
            addCriterion("RECORD_NAME >", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_NAME >=", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameLessThan(String value) {
            addCriterion("RECORD_NAME <", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameLessThanOrEqualTo(String value) {
            addCriterion("RECORD_NAME <=", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameLike(String value) {
            addCriterion("RECORD_NAME like", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameNotLike(String value) {
            addCriterion("RECORD_NAME not like", value, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameIn(List<String> values) {
            addCriterion("RECORD_NAME in", values, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameNotIn(List<String> values) {
            addCriterion("RECORD_NAME not in", values, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameBetween(String value1, String value2) {
            addCriterion("RECORD_NAME between", value1, value2, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordNameNotBetween(String value1, String value2) {
            addCriterion("RECORD_NAME not between", value1, value2, "recordName");
            return (Criteria) this;
        }

        public Criteria andRecordValueIsNull() {
            addCriterion("RECORD_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andRecordValueIsNotNull() {
            addCriterion("RECORD_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andRecordValueEqualTo(Integer value) {
            addCriterion("RECORD_VALUE =", value, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueNotEqualTo(Integer value) {
            addCriterion("RECORD_VALUE <>", value, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueGreaterThan(Integer value) {
            addCriterion("RECORD_VALUE >", value, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("RECORD_VALUE >=", value, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueLessThan(Integer value) {
            addCriterion("RECORD_VALUE <", value, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueLessThanOrEqualTo(Integer value) {
            addCriterion("RECORD_VALUE <=", value, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueIn(List<Integer> values) {
            addCriterion("RECORD_VALUE in", values, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueNotIn(List<Integer> values) {
            addCriterion("RECORD_VALUE not in", values, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueBetween(Integer value1, Integer value2) {
            addCriterion("RECORD_VALUE between", value1, value2, "recordValue");
            return (Criteria) this;
        }

        public Criteria andRecordValueNotBetween(Integer value1, Integer value2) {
            addCriterion("RECORD_VALUE not between", value1, value2, "recordValue");
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