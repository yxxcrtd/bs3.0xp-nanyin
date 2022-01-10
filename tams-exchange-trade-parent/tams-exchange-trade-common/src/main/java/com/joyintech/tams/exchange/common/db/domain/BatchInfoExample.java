package com.joyintech.tams.exchange.common.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BatchInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BatchInfoExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andPriceModeIsNull() {
            addCriterion("PRICE_MODE is null");
            return (Criteria) this;
        }

        public Criteria andPriceModeIsNotNull() {
            addCriterion("PRICE_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceModeEqualTo(int value) {
            addCriterion("PRICE_MODE =", value, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeNotEqualTo(int value) {
            addCriterion("PRICE_MODE <>", value, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeGreaterThan(int value) {
            addCriterion("PRICE_MODE >", value, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeGreaterThanOrEqualTo(int value) {
            addCriterion("PRICE_MODE >=", value, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeLessThan(int value) {
            addCriterion("PRICE_MODE <", value, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeLessThanOrEqualTo(int value) {
            addCriterion("PRICE_MODE <=", value, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeIn(List<Integer> values) {
            addCriterion("PRICE_MODE in", values, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeNotIn(List<Integer> values) {
            addCriterion("PRICE_MODE not in", values, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeBetween(int value1, int value2) {
            addCriterion("PRICE_MODE between", value1, value2, "priceMode");
            return (Criteria) this;
        }

        public Criteria andPriceModeNotBetween(int value1, int value2) {
            addCriterion("PRICE_MODE not between", value1, value2, "priceMode");
            return (Criteria) this;
        }

        public Criteria andBeginPriceIsNull() {
            addCriterion("BEGIN_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andBeginPriceIsNotNull() {
            addCriterion("BEGIN_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andBeginPriceEqualTo(double value) {
            addCriterion("BEGIN_PRICE =", value, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceNotEqualTo(double value) {
            addCriterion("BEGIN_PRICE <>", value, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceGreaterThan(double value) {
            addCriterion("BEGIN_PRICE >", value, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceGreaterThanOrEqualTo(double value) {
            addCriterion("BEGIN_PRICE >=", value, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceLessThan(double value) {
            addCriterion("BEGIN_PRICE <", value, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceLessThanOrEqualTo(double value) {
            addCriterion("BEGIN_PRICE <=", value, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceIn(List<Double> values) {
            addCriterion("BEGIN_PRICE in", values, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceNotIn(List<Double> values) {
            addCriterion("BEGIN_PRICE not in", values, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceBetween(double value1, double value2) {
            addCriterion("BEGIN_PRICE between", value1, value2, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andBeginPriceNotBetween(double value1, double value2) {
            addCriterion("BEGIN_PRICE not between", value1, value2, "beginPrice");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetIsNull() {
            addCriterion("PRICE_OFFSET is null");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetIsNotNull() {
            addCriterion("PRICE_OFFSET is not null");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetEqualTo(double value) {
            addCriterion("PRICE_OFFSET =", value, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetNotEqualTo(double value) {
            addCriterion("PRICE_OFFSET <>", value, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetGreaterThan(double value) {
            addCriterion("PRICE_OFFSET >", value, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetGreaterThanOrEqualTo(double value) {
            addCriterion("PRICE_OFFSET >=", value, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetLessThan(double value) {
            addCriterion("PRICE_OFFSET <", value, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetLessThanOrEqualTo(double value) {
            addCriterion("PRICE_OFFSET <=", value, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetIn(List<Double> values) {
            addCriterion("PRICE_OFFSET in", values, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetNotIn(List<Double> values) {
            addCriterion("PRICE_OFFSET not in", values, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetBetween(double value1, double value2) {
            addCriterion("PRICE_OFFSET between", value1, value2, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andPriceOffsetNotBetween(double value1, double value2) {
            addCriterion("PRICE_OFFSET not between", value1, value2, "priceOffset");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeIsNull() {
            addCriterion("BEGIN_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeIsNotNull() {
            addCriterion("BEGIN_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeEqualTo(int value) {
            addCriterion("BEGIN_VOLUME =", value, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeNotEqualTo(int value) {
            addCriterion("BEGIN_VOLUME <>", value, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeGreaterThan(int value) {
            addCriterion("BEGIN_VOLUME >", value, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeGreaterThanOrEqualTo(int value) {
            addCriterion("BEGIN_VOLUME >=", value, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeLessThan(int value) {
            addCriterion("BEGIN_VOLUME <", value, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeLessThanOrEqualTo(int value) {
            addCriterion("BEGIN_VOLUME <=", value, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeIn(List<Integer> values) {
            addCriterion("BEGIN_VOLUME in", values, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeNotIn(List<Integer> values) {
            addCriterion("BEGIN_VOLUME not in", values, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeBetween(int value1, int value2) {
            addCriterion("BEGIN_VOLUME between", value1, value2, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andBeginVolumeNotBetween(int value1, int value2) {
            addCriterion("BEGIN_VOLUME not between", value1, value2, "beginVolume");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetIsNull() {
            addCriterion("VOLUME_OFFSET is null");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetIsNotNull() {
            addCriterion("VOLUME_OFFSET is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetEqualTo(int value) {
            addCriterion("VOLUME_OFFSET =", value, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetNotEqualTo(int value) {
            addCriterion("VOLUME_OFFSET <>", value, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetGreaterThan(int value) {
            addCriterion("VOLUME_OFFSET >", value, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetGreaterThanOrEqualTo(int value) {
            addCriterion("VOLUME_OFFSET >=", value, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetLessThan(int value) {
            addCriterion("VOLUME_OFFSET <", value, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetLessThanOrEqualTo(int value) {
            addCriterion("VOLUME_OFFSET <=", value, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetIn(List<Integer> values) {
            addCriterion("VOLUME_OFFSET in", values, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetNotIn(List<Integer> values) {
            addCriterion("VOLUME_OFFSET not in", values, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetBetween(int value1, int value2) {
            addCriterion("VOLUME_OFFSET between", value1, value2, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andVolumeOffsetNotBetween(int value1, int value2) {
            addCriterion("VOLUME_OFFSET not between", value1, value2, "volumeOffset");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesIsNull() {
            addCriterion("ENTRUST_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesIsNotNull() {
            addCriterion("ENTRUST_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesEqualTo(BigDecimal value) {
            addCriterion("ENTRUST_TIMES =", value, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesNotEqualTo(BigDecimal value) {
            addCriterion("ENTRUST_TIMES <>", value, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesGreaterThan(BigDecimal value) {
            addCriterion("ENTRUST_TIMES >", value, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ENTRUST_TIMES >=", value, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesLessThan(BigDecimal value) {
            addCriterion("ENTRUST_TIMES <", value, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ENTRUST_TIMES <=", value, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesIn(List<BigDecimal> values) {
            addCriterion("ENTRUST_TIMES in", values, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesNotIn(List<BigDecimal> values) {
            addCriterion("ENTRUST_TIMES not in", values, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENTRUST_TIMES between", value1, value2, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andEntrustTimesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ENTRUST_TIMES not between", value1, value2, "entrustTimes");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNull() {
            addCriterion("TOTAL_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNotNull() {
            addCriterion("TOTAL_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeEqualTo(int value) {
            addCriterion("TOTAL_VOLUME =", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotEqualTo(int value) {
            addCriterion("TOTAL_VOLUME <>", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThan(int value) {
            addCriterion("TOTAL_VOLUME >", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThanOrEqualTo(int value) {
            addCriterion("TOTAL_VOLUME >=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThan(int value) {
            addCriterion("TOTAL_VOLUME <", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThanOrEqualTo(int value) {
            addCriterion("TOTAL_VOLUME <=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIn(List<Integer> values) {
            addCriterion("TOTAL_VOLUME in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotIn(List<Integer> values) {
            addCriterion("TOTAL_VOLUME not in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeBetween(int value1, int value2) {
            addCriterion("TOTAL_VOLUME between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotBetween(int value1, int value2) {
            addCriterion("TOTAL_VOLUME not between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeIsNull() {
            addCriterion("ORDER_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeIsNotNull() {
            addCriterion("ORDER_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeEqualTo(int value) {
            addCriterion("ORDER_VOLUME =", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotEqualTo(int value) {
            addCriterion("ORDER_VOLUME <>", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeGreaterThan(int value) {
            addCriterion("ORDER_VOLUME >", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeGreaterThanOrEqualTo(int value) {
            addCriterion("ORDER_VOLUME >=", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeLessThan(int value) {
            addCriterion("ORDER_VOLUME <", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeLessThanOrEqualTo(int value) {
            addCriterion("ORDER_VOLUME <=", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeIn(List<Integer> values) {
            addCriterion("ORDER_VOLUME in", values, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotIn(List<Integer> values) {
            addCriterion("ORDER_VOLUME not in", values, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeBetween(int value1, int value2) {
            addCriterion("ORDER_VOLUME between", value1, value2, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotBetween(int value1, int value2) {
            addCriterion("ORDER_VOLUME not between", value1, value2, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andStepTimeIsNull() {
            addCriterion("STEP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStepTimeIsNotNull() {
            addCriterion("STEP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStepTimeEqualTo(int value) {
            addCriterion("STEP_TIME =", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotEqualTo(int value) {
            addCriterion("STEP_TIME <>", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeGreaterThan(int value) {
            addCriterion("STEP_TIME >", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeGreaterThanOrEqualTo(int value) {
            addCriterion("STEP_TIME >=", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeLessThan(int value) {
            addCriterion("STEP_TIME <", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeLessThanOrEqualTo(int value) {
            addCriterion("STEP_TIME <=", value, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeIn(List<Integer> values) {
            addCriterion("STEP_TIME in", values, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotIn(List<Integer> values) {
            addCriterion("STEP_TIME not in", values, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeBetween(int value1, int value2) {
            addCriterion("STEP_TIME between", value1, value2, "stepTime");
            return (Criteria) this;
        }

        public Criteria andStepTimeNotBetween(int value1, int value2) {
            addCriterion("STEP_TIME not between", value1, value2, "stepTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesIsNull() {
            addCriterion("COMPLETE_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesIsNotNull() {
            addCriterion("COMPLETE_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesEqualTo(int value) {
            addCriterion("COMPLETE_TIMES =", value, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesNotEqualTo(int value) {
            addCriterion("COMPLETE_TIMES <>", value, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesGreaterThan(int value) {
            addCriterion("COMPLETE_TIMES >", value, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesGreaterThanOrEqualTo(int value) {
            addCriterion("COMPLETE_TIMES >=", value, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesLessThan(int value) {
            addCriterion("COMPLETE_TIMES <", value, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesLessThanOrEqualTo(int value) {
            addCriterion("COMPLETE_TIMES <=", value, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesIn(List<Integer> values) {
            addCriterion("COMPLETE_TIMES in", values, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesNotIn(List<Integer> values) {
            addCriterion("COMPLETE_TIMES not in", values, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesBetween(int value1, int value2) {
            addCriterion("COMPLETE_TIMES between", value1, value2, "completeTimes");
            return (Criteria) this;
        }

        public Criteria andCompleteTimesNotBetween(int value1, int value2) {
            addCriterion("COMPLETE_TIMES not between", value1, value2, "completeTimes");
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