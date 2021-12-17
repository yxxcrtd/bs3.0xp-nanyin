package com.joyintech.tams.exchange.common.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlgorithmHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AlgorithmHistoryExample() {
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

        public Criteria andAlgorithmIdIsNull() {
            addCriterion("ALGORITHM_ID is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdIsNotNull() {
            addCriterion("ALGORITHM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdEqualTo(String value) {
            addCriterion("ALGORITHM_ID =", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdNotEqualTo(String value) {
            addCriterion("ALGORITHM_ID <>", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdGreaterThan(String value) {
            addCriterion("ALGORITHM_ID >", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdGreaterThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_ID >=", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdLessThan(String value) {
            addCriterion("ALGORITHM_ID <", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdLessThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_ID <=", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdLike(String value) {
            addCriterion("ALGORITHM_ID like", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdNotLike(String value) {
            addCriterion("ALGORITHM_ID not like", value, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdIn(List<String> values) {
            addCriterion("ALGORITHM_ID in", values, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdNotIn(List<String> values) {
            addCriterion("ALGORITHM_ID not in", values, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdBetween(String value1, String value2) {
            addCriterion("ALGORITHM_ID between", value1, value2, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmIdNotBetween(String value1, String value2) {
            addCriterion("ALGORITHM_ID not between", value1, value2, "algorithmId");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameIsNull() {
            addCriterion("ALGORITHM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameIsNotNull() {
            addCriterion("ALGORITHM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameEqualTo(String value) {
            addCriterion("ALGORITHM_NAME =", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameNotEqualTo(String value) {
            addCriterion("ALGORITHM_NAME <>", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameGreaterThan(String value) {
            addCriterion("ALGORITHM_NAME >", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameGreaterThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_NAME >=", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameLessThan(String value) {
            addCriterion("ALGORITHM_NAME <", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameLessThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_NAME <=", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameLike(String value) {
            addCriterion("ALGORITHM_NAME like", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameNotLike(String value) {
            addCriterion("ALGORITHM_NAME not like", value, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameIn(List<String> values) {
            addCriterion("ALGORITHM_NAME in", values, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameNotIn(List<String> values) {
            addCriterion("ALGORITHM_NAME not in", values, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameBetween(String value1, String value2) {
            addCriterion("ALGORITHM_NAME between", value1, value2, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andAlgorithmNameNotBetween(String value1, String value2) {
            addCriterion("ALGORITHM_NAME not between", value1, value2, "algorithmName");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusIsNull() {
            addCriterion("ALGORITHM_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusIsNotNull() {
            addCriterion("ALGORITHM_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusEqualTo(String value) {
            addCriterion("ALGORITHM_STATUS =", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusNotEqualTo(String value) {
            addCriterion("ALGORITHM_STATUS <>", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusGreaterThan(String value) {
            addCriterion("ALGORITHM_STATUS >", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_STATUS >=", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusLessThan(String value) {
            addCriterion("ALGORITHM_STATUS <", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusLessThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_STATUS <=", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusLike(String value) {
            addCriterion("ALGORITHM_STATUS like", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusNotLike(String value) {
            addCriterion("ALGORITHM_STATUS not like", value, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusIn(List<String> values) {
            addCriterion("ALGORITHM_STATUS in", values, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusNotIn(List<String> values) {
            addCriterion("ALGORITHM_STATUS not in", values, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusBetween(String value1, String value2) {
            addCriterion("ALGORITHM_STATUS between", value1, value2, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andAlgorithmStatusNotBetween(String value1, String value2) {
            addCriterion("ALGORITHM_STATUS not between", value1, value2, "algorithmStatus");
            return (Criteria) this;
        }

        public Criteria andParameterIsNull() {
            addCriterion("PARAMETER is null");
            return (Criteria) this;
        }

        public Criteria andParameterIsNotNull() {
            addCriterion("PARAMETER is not null");
            return (Criteria) this;
        }

        public Criteria andParameterEqualTo(String value) {
            addCriterion("PARAMETER =", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotEqualTo(String value) {
            addCriterion("PARAMETER <>", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThan(String value) {
            addCriterion("PARAMETER >", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMETER >=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThan(String value) {
            addCriterion("PARAMETER <", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLessThanOrEqualTo(String value) {
            addCriterion("PARAMETER <=", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterLike(String value) {
            addCriterion("PARAMETER like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotLike(String value) {
            addCriterion("PARAMETER not like", value, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterIn(List<String> values) {
            addCriterion("PARAMETER in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotIn(List<String> values) {
            addCriterion("PARAMETER not in", values, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterBetween(String value1, String value2) {
            addCriterion("PARAMETER between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andParameterNotBetween(String value1, String value2) {
            addCriterion("PARAMETER not between", value1, value2, "parameter");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNull() {
            addCriterion("STOCK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNotNull() {
            addCriterion("STOCK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStockCodeEqualTo(String value) {
            addCriterion("STOCK_CODE =", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotEqualTo(String value) {
            addCriterion("STOCK_CODE <>", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThan(String value) {
            addCriterion("STOCK_CODE >", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK_CODE >=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThan(String value) {
            addCriterion("STOCK_CODE <", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThanOrEqualTo(String value) {
            addCriterion("STOCK_CODE <=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLike(String value) {
            addCriterion("STOCK_CODE like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotLike(String value) {
            addCriterion("STOCK_CODE not like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIn(List<String> values) {
            addCriterion("STOCK_CODE in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotIn(List<String> values) {
            addCriterion("STOCK_CODE not in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeBetween(String value1, String value2) {
            addCriterion("STOCK_CODE between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotBetween(String value1, String value2) {
            addCriterion("STOCK_CODE not between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(Long value) {
            addCriterion("VOLUME =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(Long value) {
            addCriterion("VOLUME <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(Long value) {
            addCriterion("VOLUME >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(Long value) {
            addCriterion("VOLUME >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(Long value) {
            addCriterion("VOLUME <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(Long value) {
            addCriterion("VOLUME <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<Long> values) {
            addCriterion("VOLUME in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<Long> values) {
            addCriterion("VOLUME not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(Long value1, Long value2) {
            addCriterion("VOLUME between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(Long value1, Long value2) {
            addCriterion("VOLUME not between", value1, value2, "volume");
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

        public Criteria andEntrustBsGreaterThanOrEqualTo(String value) {
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

        public Criteria andOffsetFlagIsNull() {
            addCriterion("OFFSET_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagIsNotNull() {
            addCriterion("OFFSET_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagEqualTo(String value) {
            addCriterion("OFFSET_FLAG =", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagNotEqualTo(String value) {
            addCriterion("OFFSET_FLAG <>", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagGreaterThan(String value) {
            addCriterion("OFFSET_FLAG >", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagGreaterThanOrEqualTo(String value) {
            addCriterion("OFFSET_FLAG >=", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagLessThan(String value) {
            addCriterion("OFFSET_FLAG <", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagLessThanOrEqualTo(String value) {
            addCriterion("OFFSET_FLAG <=", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagLike(String value) {
            addCriterion("OFFSET_FLAG like", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagNotLike(String value) {
            addCriterion("OFFSET_FLAG not like", value, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagIn(List<String> values) {
            addCriterion("OFFSET_FLAG in", values, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagNotIn(List<String> values) {
            addCriterion("OFFSET_FLAG not in", values, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagBetween(String value1, String value2) {
            addCriterion("OFFSET_FLAG between", value1, value2, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andOffsetFlagNotBetween(String value1, String value2) {
            addCriterion("OFFSET_FLAG not between", value1, value2, "offsetFlag");
            return (Criteria) this;
        }

        public Criteria andLimitActionIsNull() {
            addCriterion("LIMIT_ACTION is null");
            return (Criteria) this;
        }

        public Criteria andLimitActionIsNotNull() {
            addCriterion("LIMIT_ACTION is not null");
            return (Criteria) this;
        }

        public Criteria andLimitActionEqualTo(Integer value) {
            addCriterion("LIMIT_ACTION =", value, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionNotEqualTo(Integer value) {
            addCriterion("LIMIT_ACTION <>", value, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionGreaterThan(Integer value) {
            addCriterion("LIMIT_ACTION >", value, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionGreaterThanOrEqualTo(Integer value) {
            addCriterion("LIMIT_ACTION >=", value, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionLessThan(Integer value) {
            addCriterion("LIMIT_ACTION <", value, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionLessThanOrEqualTo(Integer value) {
            addCriterion("LIMIT_ACTION <=", value, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionIn(List<Integer> values) {
            addCriterion("LIMIT_ACTION in", values, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionNotIn(List<Integer> values) {
            addCriterion("LIMIT_ACTION not in", values, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionBetween(Integer value1, Integer value2) {
            addCriterion("LIMIT_ACTION between", value1, value2, "limitAction");
            return (Criteria) this;
        }

        public Criteria andLimitActionNotBetween(Integer value1, Integer value2) {
            addCriterion("LIMIT_ACTION not between", value1, value2, "limitAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionIsNull() {
            addCriterion("AFTER_ACTION is null");
            return (Criteria) this;
        }

        public Criteria andAfterActionIsNotNull() {
            addCriterion("AFTER_ACTION is not null");
            return (Criteria) this;
        }

        public Criteria andAfterActionEqualTo(Integer value) {
            addCriterion("AFTER_ACTION =", value, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionNotEqualTo(Integer value) {
            addCriterion("AFTER_ACTION <>", value, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionGreaterThan(Integer value) {
            addCriterion("AFTER_ACTION >", value, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionGreaterThanOrEqualTo(Integer value) {
            addCriterion("AFTER_ACTION >=", value, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionLessThan(Integer value) {
            addCriterion("AFTER_ACTION <", value, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionLessThanOrEqualTo(Integer value) {
            addCriterion("AFTER_ACTION <=", value, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionIn(List<Integer> values) {
            addCriterion("AFTER_ACTION in", values, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionNotIn(List<Integer> values) {
            addCriterion("AFTER_ACTION not in", values, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionBetween(Integer value1, Integer value2) {
            addCriterion("AFTER_ACTION between", value1, value2, "afterAction");
            return (Criteria) this;
        }

        public Criteria andAfterActionNotBetween(Integer value1, Integer value2) {
            addCriterion("AFTER_ACTION not between", value1, value2, "afterAction");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeIsNull() {
            addCriterion("TRADED_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeIsNotNull() {
            addCriterion("TRADED_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeEqualTo(Long value) {
            addCriterion("TRADED_VOLUME =", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeNotEqualTo(Long value) {
            addCriterion("TRADED_VOLUME <>", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeGreaterThan(Long value) {
            addCriterion("TRADED_VOLUME >", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeGreaterThanOrEqualTo(Long value) {
            addCriterion("TRADED_VOLUME >=", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeLessThan(Long value) {
            addCriterion("TRADED_VOLUME <", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeLessThanOrEqualTo(Long value) {
            addCriterion("TRADED_VOLUME <=", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeIn(List<Long> values) {
            addCriterion("TRADED_VOLUME in", values, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeNotIn(List<Long> values) {
            addCriterion("TRADED_VOLUME not in", values, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeBetween(Long value1, Long value2) {
            addCriterion("TRADED_VOLUME between", value1, value2, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeNotBetween(Long value1, Long value2) {
            addCriterion("TRADED_VOLUME not between", value1, value2, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andExternalIdIsNull() {
            addCriterion("EXTERNAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andExternalIdIsNotNull() {
            addCriterion("EXTERNAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExternalIdEqualTo(String value) {
            addCriterion("EXTERNAL_ID =", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotEqualTo(String value) {
            addCriterion("EXTERNAL_ID <>", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdGreaterThan(String value) {
            addCriterion("EXTERNAL_ID >", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXTERNAL_ID >=", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLessThan(String value) {
            addCriterion("EXTERNAL_ID <", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLessThanOrEqualTo(String value) {
            addCriterion("EXTERNAL_ID <=", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdLike(String value) {
            addCriterion("EXTERNAL_ID like", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotLike(String value) {
            addCriterion("EXTERNAL_ID not like", value, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdIn(List<String> values) {
            addCriterion("EXTERNAL_ID in", values, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotIn(List<String> values) {
            addCriterion("EXTERNAL_ID not in", values, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdBetween(String value1, String value2) {
            addCriterion("EXTERNAL_ID between", value1, value2, "externalId");
            return (Criteria) this;
        }

        public Criteria andExternalIdNotBetween(String value1, String value2) {
            addCriterion("EXTERNAL_ID not between", value1, value2, "externalId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIsNull() {
            addCriterion("INSTRUCTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIsNotNull() {
            addCriterion("INSTRUCTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionIdEqualTo(String value) {
            addCriterion("INSTRUCTION_ID =", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotEqualTo(String value) {
            addCriterion("INSTRUCTION_ID <>", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThan(String value) {
            addCriterion("INSTRUCTION_ID >", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdGreaterThanOrEqualTo(String value) {
            addCriterion("INSTRUCTION_ID >=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThan(String value) {
            addCriterion("INSTRUCTION_ID <", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLessThanOrEqualTo(String value) {
            addCriterion("INSTRUCTION_ID <=", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdLike(String value) {
            addCriterion("INSTRUCTION_ID like", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotLike(String value) {
            addCriterion("INSTRUCTION_ID not like", value, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdIn(List<String> values) {
            addCriterion("INSTRUCTION_ID in", values, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotIn(List<String> values) {
            addCriterion("INSTRUCTION_ID not in", values, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdBetween(String value1, String value2) {
            addCriterion("INSTRUCTION_ID between", value1, value2, "instructionId");
            return (Criteria) this;
        }

        public Criteria andInstructionIdNotBetween(String value1, String value2) {
            addCriterion("INSTRUCTION_ID not between", value1, value2, "instructionId");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNull() {
            addCriterion("ERROR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNotNull() {
            addCriterion("ERROR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeEqualTo(Integer value) {
            addCriterion("ERROR_CODE =", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotEqualTo(Integer value) {
            addCriterion("ERROR_CODE <>", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThan(Integer value) {
            addCriterion("ERROR_CODE >", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ERROR_CODE >=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThan(Integer value) {
            addCriterion("ERROR_CODE <", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThanOrEqualTo(Integer value) {
            addCriterion("ERROR_CODE <=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIn(List<Integer> values) {
            addCriterion("ERROR_CODE in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotIn(List<Integer> values) {
            addCriterion("ERROR_CODE not in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeBetween(Integer value1, Integer value2) {
            addCriterion("ERROR_CODE between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("ERROR_CODE not between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNull() {
            addCriterion("ERROR_MSG is null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNotNull() {
            addCriterion("ERROR_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgEqualTo(String value) {
            addCriterion("ERROR_MSG =", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotEqualTo(String value) {
            addCriterion("ERROR_MSG <>", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThan(String value) {
            addCriterion("ERROR_MSG >", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_MSG >=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThan(String value) {
            addCriterion("ERROR_MSG <", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThanOrEqualTo(String value) {
            addCriterion("ERROR_MSG <=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLike(String value) {
            addCriterion("ERROR_MSG like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotLike(String value) {
            addCriterion("ERROR_MSG not like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIn(List<String> values) {
            addCriterion("ERROR_MSG in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotIn(List<String> values) {
            addCriterion("ERROR_MSG not in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgBetween(String value1, String value2) {
            addCriterion("ERROR_MSG between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotBetween(String value1, String value2) {
            addCriterion("ERROR_MSG not between", value1, value2, "errorMsg");
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

        public Criteria andTradingDayIsNull() {
            addCriterion("TRADING_DAY is null");
            return (Criteria) this;
        }

        public Criteria andTradingDayIsNotNull() {
            addCriterion("TRADING_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andTradingDayEqualTo(String value) {
            addCriterion("TRADING_DAY =", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayNotEqualTo(String value) {
            addCriterion("TRADING_DAY <>", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayGreaterThan(String value) {
            addCriterion("TRADING_DAY >", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayGreaterThanOrEqualTo(String value) {
            addCriterion("TRADING_DAY >=", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayLessThan(String value) {
            addCriterion("TRADING_DAY <", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayLessThanOrEqualTo(String value) {
            addCriterion("TRADING_DAY <=", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayLike(String value) {
            addCriterion("TRADING_DAY like", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayNotLike(String value) {
            addCriterion("TRADING_DAY not like", value, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayIn(List<String> values) {
            addCriterion("TRADING_DAY in", values, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayNotIn(List<String> values) {
            addCriterion("TRADING_DAY not in", values, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayBetween(String value1, String value2) {
            addCriterion("TRADING_DAY between", value1, value2, "tradingDay");
            return (Criteria) this;
        }

        public Criteria andTradingDayNotBetween(String value1, String value2) {
            addCriterion("TRADING_DAY not between", value1, value2, "tradingDay");
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