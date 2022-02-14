

package com.joyintech.tams.exchange.service.trade.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lifangpeng
 * @date 2021/10/12
 */
public class TerminalAgentInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TerminalAgentInfoExample() {
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

        public Criteria andAgentTypeIsNull() {
            addCriterion("AGENT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAgentTypeIsNotNull() {
            addCriterion("AGENT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAgentTypeEqualTo(String value) {
            addCriterion("AGENT_TYPE =", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotEqualTo(String value) {
            addCriterion("AGENT_TYPE <>", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeGreaterThan(String value) {
            addCriterion("AGENT_TYPE >", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeeGreaterThanOrEqualTo(String value) {
            addCriterion("AGENT_TYPE >=", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeLessThan(String value) {
            addCriterion("AGENT_TYPE <", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeLessThanOrEqualTo(String value) {
            addCriterion("AGENT_TYPE <=", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeLike(String value) {
            addCriterion("AGENT_TYPE like", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotLike(String value) {
            addCriterion("AGENT_TYPE not like", value, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeIn(List<String> values) {
            addCriterion("AGENT_TYPE in", values, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotIn(List<String> values) {
            addCriterion("AGENT_TYPE not in", values, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeBetween(String value1, String value2) {
            addCriterion("AGENT_TYPE between", value1, value2, "agentType");
            return (Criteria) this;
        }

        public Criteria andAgentTypeNotBetween(String value1, String value2) {
            addCriterion("AGENT_TYPE not between", value1, value2, "agentType");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNull() {
            addCriterion("FUNCTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNotNull() {
            addCriterion("FUNCTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeEqualTo(int value) {
            addCriterion("FUNCTION_CODE =", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotEqualTo(int value) {
            addCriterion("FUNCTION_CODE <>", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThan(int value) {
            addCriterion("FUNCTION_CODE >", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThanOrEqualTo(int value) {
            addCriterion("FUNCTION_CODE >=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThan(int value) {
            addCriterion("FUNCTION_CODE <", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThanOrEqualTo(int value) {
            addCriterion("FUNCTION_CODE <=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andTerminalTypeIn(List<Integer> values) {
            addCriterion("FUNCTION_CODE in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotIn(List<Integer> values) {
            addCriterion("FUNCTION_CODE not in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeBetween(int value1, int value2) {
            addCriterion("FUNCTION_CODE between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotBetween(int value1, int value2) {
            addCriterion("FUNCTION_CODE not between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andEntrustPropIsNull() {
            addCriterion("ENTRUST_PROP is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPropIsNotNull() {
            addCriterion("ENTRUST_PROP is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPropEqualTo(String value) {
            addCriterion("ENTRUST_PROP =", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropNotEqualTo(String value) {
            addCriterion("ENTRUST_PROP <>", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropGreaterThan(String value) {
            addCriterion("ENTRUST_PROP >", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropeGreaterThanOrEqualTo(String value) {
            addCriterion("ENTRUST_PROP >=", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropLessThan(String value) {
            addCriterion("ENTRUST_PROP <", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropLessThanOrEqualTo(String value) {
            addCriterion("ENTRUST_PROP <=", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropLike(String value) {
            addCriterion("ENTRUST_PROP like", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropNotLike(String value) {
            addCriterion("ENTRUST_PROP not like", value, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropIn(List<String> values) {
            addCriterion("ENTRUST_PROP in", values, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropNotIn(List<String> values) {
            addCriterion("ENTRUST_PROP not in", values, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropBetween(String value1, String value2) {
            addCriterion("ENTRUST_PROP between", value1, value2, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andEntrustPropNotBetween(String value1, String value2) {
            addCriterion("ENTRUST_PROP not between", value1, value2, "entrustProp");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeIsNull() {
            addCriterion("QUOTE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeIsNotNull() {
            addCriterion("QUOTE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeEqualTo(int value) {
            addCriterion("QUOTE_TYPE =", value, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeNotEqualTo(int value) {
            addCriterion("QUOTE_TYPE <>", value, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeGreaterThan(int value) {
            addCriterion("QUOTE_TYPE >", value, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeGreaterThanOrEqualTo(int value) {
            addCriterion("QUOTE_TYPE >=", value, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeLessThan(int value) {
            addCriterion("QUOTE_TYPE <", value, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeLessThanOrEqualTo(int value) {
            addCriterion("QUOTE_TYPE <=", value, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeIn(List<Integer> values) {
            addCriterion("QUOTE_TYPE in", values, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeNotIn(List<Integer> values) {
            addCriterion("QUOTE_TYPE not in", values, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeBetween(int value1, int value2) {
            addCriterion("QUOTE_TYPE between", value1, value2, "quoteType");
            return (Criteria) this;
        }

        public Criteria andQuoteTypeNotBetween(int value1, int value2) {
            addCriterion("QUOTE_TYPE not between", value1, value2, "quoteType");
            return (Criteria) this;
        }

        public Criteria andMarketIsNull() {
            addCriterion("MARKET is null");
            return (Criteria) this;
        }

        public Criteria andMarketIsNotNull() {
            addCriterion("MARKET is not null");
            return (Criteria) this;
        }

        public Criteria andMarketEqualTo(String value) {
            addCriterion("MARKET =", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketNotEqualTo(String value) {
            addCriterion("MARKET <>", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketGreaterThan(String value) {
            addCriterion("MARKET >", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketeGreaterThanOrEqualTo(String value) {
            addCriterion("MARKET >=", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketLessThan(String value) {
            addCriterion("MARKET <", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketLessThanOrEqualTo(String value) {
            addCriterion("MARKET <=", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketLike(String value) {
            addCriterion("MARKET like", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketNotLike(String value) {
            addCriterion("MARKET not like", value, "market");
            return (Criteria) this;
        }

        public Criteria andMarketIn(List<String> values) {
            addCriterion("MARKET in", values, "market");
            return (Criteria) this;
        }

        public Criteria andMarketNotIn(List<String> values) {
            addCriterion("MARKET not in", values, "market");
            return (Criteria) this;
        }

        public Criteria andMarketBetween(String value1, String value2) {
            addCriterion("MARKET between", value1, value2, "market");
            return (Criteria) this;
        }

        public Criteria andMarketNotBetween(String value1, String value2) {
            addCriterion("MARKET not between", value1, value2, "market");
            return (Criteria) this;
        }

        public Criteria andEntrustBsIsNull() {
            addCriterion("ENTRUST_BS is null");
            return (Criteria) this;
        }

        public Criteria andEntrustBsIsNotNull() {
            addCriterion("ENTRUST_BS is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustBsEqualTo(String value) {
            addCriterion("ENTRUST_BS =", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsNotEqualTo(String value) {
            addCriterion("ENTRUST_BS <>", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsGreaterThan(String value) {
            addCriterion("ENTRUST_BS >", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBseGreaterThanOrEqualTo(String value) {
            addCriterion("ENTRUST_BS >=", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsLessThan(String value) {
            addCriterion("ENTRUST_BS <", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsLessThanOrEqualTo(String value) {
            addCriterion("ENTRUST_BS <=", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsLike(String value) {
            addCriterion("ENTRUST_BS like", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsNotLike(String value) {
            addCriterion("ENTRUST_BS not like", value, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsIn(List<String> values) {
            addCriterion("ENTRUST_BS in", values, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsNotIn(List<String> values) {
            addCriterion("ENTRUST_BS not in", values, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsBetween(String value1, String value2) {
            addCriterion("ENTRUST_BS between", value1, value2, "entrustBs");
            return (Criteria) this;
        }

        public Criteria andEntrustBsNotBetween(String value1, String value2) {
            addCriterion("ENTRUST_BS not between", value1, value2, "entrustBs");
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

        public Criteria andStatusEqualTo(int value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(int value) {
            addCriterion("STATUS <>", value, "status");
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
