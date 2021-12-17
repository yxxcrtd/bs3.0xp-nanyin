package com.joyintech.tams.exchange.common.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuturesInstrumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuturesInstrumentExample() {
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

        public Criteria andInnerCodeIsNull() {
            addCriterion("INNER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andInnerCodeIsNotNull() {
            addCriterion("INNER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andInnerCodeEqualTo(String value) {
            addCriterion("INNER_CODE =", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeNotEqualTo(String value) {
            addCriterion("INNER_CODE <>", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeGreaterThan(String value) {
            addCriterion("INNER_CODE >", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeGreaterThanOrEqualTo(String value) {
            addCriterion("INNER_CODE >=", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeLessThan(String value) {
            addCriterion("INNER_CODE <", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeLessThanOrEqualTo(String value) {
            addCriterion("INNER_CODE <=", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeLike(String value) {
            addCriterion("INNER_CODE like", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeNotLike(String value) {
            addCriterion("INNER_CODE not like", value, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeIn(List<String> values) {
            addCriterion("INNER_CODE in", values, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeNotIn(List<String> values) {
            addCriterion("INNER_CODE not in", values, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeBetween(String value1, String value2) {
            addCriterion("INNER_CODE between", value1, value2, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInnerCodeNotBetween(String value1, String value2) {
            addCriterion("INNER_CODE not between", value1, value2, "innerCode");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdIsNull() {
            addCriterion("INSTRUMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdIsNotNull() {
            addCriterion("INSTRUMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdEqualTo(String value) {
            addCriterion("INSTRUMENT_ID =", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotEqualTo(String value) {
            addCriterion("INSTRUMENT_ID <>", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdGreaterThan(String value) {
            addCriterion("INSTRUMENT_ID >", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdGreaterThanOrEqualTo(String value) {
            addCriterion("INSTRUMENT_ID >=", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdLessThan(String value) {
            addCriterion("INSTRUMENT_ID <", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdLessThanOrEqualTo(String value) {
            addCriterion("INSTRUMENT_ID <=", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdLike(String value) {
            addCriterion("INSTRUMENT_ID like", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotLike(String value) {
            addCriterion("INSTRUMENT_ID not like", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdIn(List<String> values) {
            addCriterion("INSTRUMENT_ID in", values, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotIn(List<String> values) {
            addCriterion("INSTRUMENT_ID not in", values, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdBetween(String value1, String value2) {
            addCriterion("INSTRUMENT_ID between", value1, value2, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotBetween(String value1, String value2) {
            addCriterion("INSTRUMENT_ID not between", value1, value2, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdIsNull() {
            addCriterion("EXCHANGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andExchangeIdIsNotNull() {
            addCriterion("EXCHANGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeIdEqualTo(String value) {
            addCriterion("EXCHANGE_ID =", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdNotEqualTo(String value) {
            addCriterion("EXCHANGE_ID <>", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdGreaterThan(String value) {
            addCriterion("EXCHANGE_ID >", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXCHANGE_ID >=", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdLessThan(String value) {
            addCriterion("EXCHANGE_ID <", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdLessThanOrEqualTo(String value) {
            addCriterion("EXCHANGE_ID <=", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdLike(String value) {
            addCriterion("EXCHANGE_ID like", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdNotLike(String value) {
            addCriterion("EXCHANGE_ID not like", value, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdIn(List<String> values) {
            addCriterion("EXCHANGE_ID in", values, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdNotIn(List<String> values) {
            addCriterion("EXCHANGE_ID not in", values, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdBetween(String value1, String value2) {
            addCriterion("EXCHANGE_ID between", value1, value2, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andExchangeIdNotBetween(String value1, String value2) {
            addCriterion("EXCHANGE_ID not between", value1, value2, "exchangeId");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameIsNull() {
            addCriterion("INSTRUMENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameIsNotNull() {
            addCriterion("INSTRUMENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameEqualTo(String value) {
            addCriterion("INSTRUMENT_NAME =", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameNotEqualTo(String value) {
            addCriterion("INSTRUMENT_NAME <>", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameGreaterThan(String value) {
            addCriterion("INSTRUMENT_NAME >", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameGreaterThanOrEqualTo(String value) {
            addCriterion("INSTRUMENT_NAME >=", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameLessThan(String value) {
            addCriterion("INSTRUMENT_NAME <", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameLessThanOrEqualTo(String value) {
            addCriterion("INSTRUMENT_NAME <=", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameLike(String value) {
            addCriterion("INSTRUMENT_NAME like", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameNotLike(String value) {
            addCriterion("INSTRUMENT_NAME not like", value, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameIn(List<String> values) {
            addCriterion("INSTRUMENT_NAME in", values, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameNotIn(List<String> values) {
            addCriterion("INSTRUMENT_NAME not in", values, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameBetween(String value1, String value2) {
            addCriterion("INSTRUMENT_NAME between", value1, value2, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andInstrumentNameNotBetween(String value1, String value2) {
            addCriterion("INSTRUMENT_NAME not between", value1, value2, "instrumentName");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdIsNull() {
            addCriterion("EXCHANGE_INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdIsNotNull() {
            addCriterion("EXCHANGE_INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdEqualTo(String value) {
            addCriterion("EXCHANGE_INST_ID =", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdNotEqualTo(String value) {
            addCriterion("EXCHANGE_INST_ID <>", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdGreaterThan(String value) {
            addCriterion("EXCHANGE_INST_ID >", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXCHANGE_INST_ID >=", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdLessThan(String value) {
            addCriterion("EXCHANGE_INST_ID <", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdLessThanOrEqualTo(String value) {
            addCriterion("EXCHANGE_INST_ID <=", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdLike(String value) {
            addCriterion("EXCHANGE_INST_ID like", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdNotLike(String value) {
            addCriterion("EXCHANGE_INST_ID not like", value, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdIn(List<String> values) {
            addCriterion("EXCHANGE_INST_ID in", values, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdNotIn(List<String> values) {
            addCriterion("EXCHANGE_INST_ID not in", values, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdBetween(String value1, String value2) {
            addCriterion("EXCHANGE_INST_ID between", value1, value2, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andExchangeInstIdNotBetween(String value1, String value2) {
            addCriterion("EXCHANGE_INST_ID not between", value1, value2, "exchangeInstId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("PRODUCT_ID like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("PRODUCT_ID not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductClassIsNull() {
            addCriterion("PRODUCT_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andProductClassIsNotNull() {
            addCriterion("PRODUCT_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andProductClassEqualTo(String value) {
            addCriterion("PRODUCT_CLASS =", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassNotEqualTo(String value) {
            addCriterion("PRODUCT_CLASS <>", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassGreaterThan(String value) {
            addCriterion("PRODUCT_CLASS >", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CLASS >=", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassLessThan(String value) {
            addCriterion("PRODUCT_CLASS <", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CLASS <=", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassLike(String value) {
            addCriterion("PRODUCT_CLASS like", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassNotLike(String value) {
            addCriterion("PRODUCT_CLASS not like", value, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassIn(List<String> values) {
            addCriterion("PRODUCT_CLASS in", values, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassNotIn(List<String> values) {
            addCriterion("PRODUCT_CLASS not in", values, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassBetween(String value1, String value2) {
            addCriterion("PRODUCT_CLASS between", value1, value2, "productClass");
            return (Criteria) this;
        }

        public Criteria andProductClassNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CLASS not between", value1, value2, "productClass");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearIsNull() {
            addCriterion("DELIVERY_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearIsNotNull() {
            addCriterion("DELIVERY_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearEqualTo(Integer value) {
            addCriterion("DELIVERY_YEAR =", value, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearNotEqualTo(Integer value) {
            addCriterion("DELIVERY_YEAR <>", value, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearGreaterThan(Integer value) {
            addCriterion("DELIVERY_YEAR >", value, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("DELIVERY_YEAR >=", value, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearLessThan(Integer value) {
            addCriterion("DELIVERY_YEAR <", value, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearLessThanOrEqualTo(Integer value) {
            addCriterion("DELIVERY_YEAR <=", value, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearIn(List<Integer> values) {
            addCriterion("DELIVERY_YEAR in", values, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearNotIn(List<Integer> values) {
            addCriterion("DELIVERY_YEAR not in", values, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearBetween(Integer value1, Integer value2) {
            addCriterion("DELIVERY_YEAR between", value1, value2, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryYearNotBetween(Integer value1, Integer value2) {
            addCriterion("DELIVERY_YEAR not between", value1, value2, "deliveryYear");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthIsNull() {
            addCriterion("DELIVERY_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthIsNotNull() {
            addCriterion("DELIVERY_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthEqualTo(Integer value) {
            addCriterion("DELIVERY_MONTH =", value, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthNotEqualTo(Integer value) {
            addCriterion("DELIVERY_MONTH <>", value, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthGreaterThan(Integer value) {
            addCriterion("DELIVERY_MONTH >", value, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("DELIVERY_MONTH >=", value, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthLessThan(Integer value) {
            addCriterion("DELIVERY_MONTH <", value, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthLessThanOrEqualTo(Integer value) {
            addCriterion("DELIVERY_MONTH <=", value, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthIn(List<Integer> values) {
            addCriterion("DELIVERY_MONTH in", values, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthNotIn(List<Integer> values) {
            addCriterion("DELIVERY_MONTH not in", values, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthBetween(Integer value1, Integer value2) {
            addCriterion("DELIVERY_MONTH between", value1, value2, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andDeliveryMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("DELIVERY_MONTH not between", value1, value2, "deliveryMonth");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeIsNull() {
            addCriterion("MAX_MARKET_ORDER_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeIsNotNull() {
            addCriterion("MAX_MARKET_ORDER_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeEqualTo(Integer value) {
            addCriterion("MAX_MARKET_ORDER_VOLUME =", value, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeNotEqualTo(Integer value) {
            addCriterion("MAX_MARKET_ORDER_VOLUME <>", value, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeGreaterThan(Integer value) {
            addCriterion("MAX_MARKET_ORDER_VOLUME >", value, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MAX_MARKET_ORDER_VOLUME >=", value, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeLessThan(Integer value) {
            addCriterion("MAX_MARKET_ORDER_VOLUME <", value, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("MAX_MARKET_ORDER_VOLUME <=", value, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeIn(List<Integer> values) {
            addCriterion("MAX_MARKET_ORDER_VOLUME in", values, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeNotIn(List<Integer> values) {
            addCriterion("MAX_MARKET_ORDER_VOLUME not in", values, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeBetween(Integer value1, Integer value2) {
            addCriterion("MAX_MARKET_ORDER_VOLUME between", value1, value2, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxMarketOrderVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("MAX_MARKET_ORDER_VOLUME not between", value1, value2, "maxMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeIsNull() {
            addCriterion("MIN_MARKET_ORDER_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeIsNotNull() {
            addCriterion("MIN_MARKET_ORDER_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeEqualTo(Integer value) {
            addCriterion("MIN_MARKET_ORDER_VOLUME =", value, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeNotEqualTo(Integer value) {
            addCriterion("MIN_MARKET_ORDER_VOLUME <>", value, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeGreaterThan(Integer value) {
            addCriterion("MIN_MARKET_ORDER_VOLUME >", value, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MIN_MARKET_ORDER_VOLUME >=", value, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeLessThan(Integer value) {
            addCriterion("MIN_MARKET_ORDER_VOLUME <", value, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("MIN_MARKET_ORDER_VOLUME <=", value, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeIn(List<Integer> values) {
            addCriterion("MIN_MARKET_ORDER_VOLUME in", values, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeNotIn(List<Integer> values) {
            addCriterion("MIN_MARKET_ORDER_VOLUME not in", values, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeBetween(Integer value1, Integer value2) {
            addCriterion("MIN_MARKET_ORDER_VOLUME between", value1, value2, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinMarketOrderVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("MIN_MARKET_ORDER_VOLUME not between", value1, value2, "minMarketOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeIsNull() {
            addCriterion("MAX_LIMIT_ORDER_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeIsNotNull() {
            addCriterion("MAX_LIMIT_ORDER_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeEqualTo(Integer value) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME =", value, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeNotEqualTo(Integer value) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME <>", value, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeGreaterThan(Integer value) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME >", value, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME >=", value, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeLessThan(Integer value) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME <", value, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME <=", value, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeIn(List<Integer> values) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME in", values, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeNotIn(List<Integer> values) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME not in", values, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeBetween(Integer value1, Integer value2) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME between", value1, value2, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMaxLimitOrderVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("MAX_LIMIT_ORDER_VOLUME not between", value1, value2, "maxLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeIsNull() {
            addCriterion("MIN_LIMIT_ORDER_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeIsNotNull() {
            addCriterion("MIN_LIMIT_ORDER_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeEqualTo(Integer value) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME =", value, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeNotEqualTo(Integer value) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME <>", value, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeGreaterThan(Integer value) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME >", value, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME >=", value, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeLessThan(Integer value) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME <", value, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME <=", value, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeIn(List<Integer> values) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME in", values, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeNotIn(List<Integer> values) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME not in", values, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeBetween(Integer value1, Integer value2) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME between", value1, value2, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andMinLimitOrderVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("MIN_LIMIT_ORDER_VOLUME not between", value1, value2, "minLimitOrderVolume");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleIsNull() {
            addCriterion("VOLUME_MULTIPLE is null");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleIsNotNull() {
            addCriterion("VOLUME_MULTIPLE is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleEqualTo(Integer value) {
            addCriterion("VOLUME_MULTIPLE =", value, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleNotEqualTo(Integer value) {
            addCriterion("VOLUME_MULTIPLE <>", value, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleGreaterThan(Integer value) {
            addCriterion("VOLUME_MULTIPLE >", value, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleGreaterThanOrEqualTo(Integer value) {
            addCriterion("VOLUME_MULTIPLE >=", value, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleLessThan(Integer value) {
            addCriterion("VOLUME_MULTIPLE <", value, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleLessThanOrEqualTo(Integer value) {
            addCriterion("VOLUME_MULTIPLE <=", value, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleIn(List<Integer> values) {
            addCriterion("VOLUME_MULTIPLE in", values, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleNotIn(List<Integer> values) {
            addCriterion("VOLUME_MULTIPLE not in", values, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleBetween(Integer value1, Integer value2) {
            addCriterion("VOLUME_MULTIPLE between", value1, value2, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andVolumeMultipleNotBetween(Integer value1, Integer value2) {
            addCriterion("VOLUME_MULTIPLE not between", value1, value2, "volumeMultiple");
            return (Criteria) this;
        }

        public Criteria andPriceTickIsNull() {
            addCriterion("PRICE_TICK is null");
            return (Criteria) this;
        }

        public Criteria andPriceTickIsNotNull() {
            addCriterion("PRICE_TICK is not null");
            return (Criteria) this;
        }

        public Criteria andPriceTickEqualTo(BigDecimal value) {
            addCriterion("PRICE_TICK =", value, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickNotEqualTo(BigDecimal value) {
            addCriterion("PRICE_TICK <>", value, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickGreaterThan(BigDecimal value) {
            addCriterion("PRICE_TICK >", value, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE_TICK >=", value, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickLessThan(BigDecimal value) {
            addCriterion("PRICE_TICK <", value, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE_TICK <=", value, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickIn(List<BigDecimal> values) {
            addCriterion("PRICE_TICK in", values, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickNotIn(List<BigDecimal> values) {
            addCriterion("PRICE_TICK not in", values, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE_TICK between", value1, value2, "priceTick");
            return (Criteria) this;
        }

        public Criteria andPriceTickNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE_TICK not between", value1, value2, "priceTick");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateIsNull() {
            addCriterion("OPEN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOpenDateIsNotNull() {
            addCriterion("OPEN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOpenDateEqualTo(String value) {
            addCriterion("OPEN_DATE =", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotEqualTo(String value) {
            addCriterion("OPEN_DATE <>", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateGreaterThan(String value) {
            addCriterion("OPEN_DATE >", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_DATE >=", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLessThan(String value) {
            addCriterion("OPEN_DATE <", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLessThanOrEqualTo(String value) {
            addCriterion("OPEN_DATE <=", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLike(String value) {
            addCriterion("OPEN_DATE like", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotLike(String value) {
            addCriterion("OPEN_DATE not like", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateIn(List<String> values) {
            addCriterion("OPEN_DATE in", values, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotIn(List<String> values) {
            addCriterion("OPEN_DATE not in", values, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateBetween(String value1, String value2) {
            addCriterion("OPEN_DATE between", value1, value2, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotBetween(String value1, String value2) {
            addCriterion("OPEN_DATE not between", value1, value2, "openDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateIsNull() {
            addCriterion("EXPIRE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExpireDateIsNotNull() {
            addCriterion("EXPIRE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExpireDateEqualTo(String value) {
            addCriterion("EXPIRE_DATE =", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotEqualTo(String value) {
            addCriterion("EXPIRE_DATE <>", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateGreaterThan(String value) {
            addCriterion("EXPIRE_DATE >", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateGreaterThanOrEqualTo(String value) {
            addCriterion("EXPIRE_DATE >=", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLessThan(String value) {
            addCriterion("EXPIRE_DATE <", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLessThanOrEqualTo(String value) {
            addCriterion("EXPIRE_DATE <=", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateLike(String value) {
            addCriterion("EXPIRE_DATE like", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotLike(String value) {
            addCriterion("EXPIRE_DATE not like", value, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateIn(List<String> values) {
            addCriterion("EXPIRE_DATE in", values, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotIn(List<String> values) {
            addCriterion("EXPIRE_DATE not in", values, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateBetween(String value1, String value2) {
            addCriterion("EXPIRE_DATE between", value1, value2, "expireDate");
            return (Criteria) this;
        }

        public Criteria andExpireDateNotBetween(String value1, String value2) {
            addCriterion("EXPIRE_DATE not between", value1, value2, "expireDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateIsNull() {
            addCriterion("START_DELIV_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateIsNotNull() {
            addCriterion("START_DELIV_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateEqualTo(String value) {
            addCriterion("START_DELIV_DATE =", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateNotEqualTo(String value) {
            addCriterion("START_DELIV_DATE <>", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateGreaterThan(String value) {
            addCriterion("START_DELIV_DATE >", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DELIV_DATE >=", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateLessThan(String value) {
            addCriterion("START_DELIV_DATE <", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateLessThanOrEqualTo(String value) {
            addCriterion("START_DELIV_DATE <=", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateLike(String value) {
            addCriterion("START_DELIV_DATE like", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateNotLike(String value) {
            addCriterion("START_DELIV_DATE not like", value, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateIn(List<String> values) {
            addCriterion("START_DELIV_DATE in", values, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateNotIn(List<String> values) {
            addCriterion("START_DELIV_DATE not in", values, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateBetween(String value1, String value2) {
            addCriterion("START_DELIV_DATE between", value1, value2, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andStartDelivDateNotBetween(String value1, String value2) {
            addCriterion("START_DELIV_DATE not between", value1, value2, "startDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateIsNull() {
            addCriterion("END_DELIV_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateIsNotNull() {
            addCriterion("END_DELIV_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateEqualTo(String value) {
            addCriterion("END_DELIV_DATE =", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateNotEqualTo(String value) {
            addCriterion("END_DELIV_DATE <>", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateGreaterThan(String value) {
            addCriterion("END_DELIV_DATE >", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DELIV_DATE >=", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateLessThan(String value) {
            addCriterion("END_DELIV_DATE <", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateLessThanOrEqualTo(String value) {
            addCriterion("END_DELIV_DATE <=", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateLike(String value) {
            addCriterion("END_DELIV_DATE like", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateNotLike(String value) {
            addCriterion("END_DELIV_DATE not like", value, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateIn(List<String> values) {
            addCriterion("END_DELIV_DATE in", values, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateNotIn(List<String> values) {
            addCriterion("END_DELIV_DATE not in", values, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateBetween(String value1, String value2) {
            addCriterion("END_DELIV_DATE between", value1, value2, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andEndDelivDateNotBetween(String value1, String value2) {
            addCriterion("END_DELIV_DATE not between", value1, value2, "endDelivDate");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseIsNull() {
            addCriterion("INST_LIFE_PHASE is null");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseIsNotNull() {
            addCriterion("INST_LIFE_PHASE is not null");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseEqualTo(String value) {
            addCriterion("INST_LIFE_PHASE =", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseNotEqualTo(String value) {
            addCriterion("INST_LIFE_PHASE <>", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseGreaterThan(String value) {
            addCriterion("INST_LIFE_PHASE >", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseGreaterThanOrEqualTo(String value) {
            addCriterion("INST_LIFE_PHASE >=", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseLessThan(String value) {
            addCriterion("INST_LIFE_PHASE <", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseLessThanOrEqualTo(String value) {
            addCriterion("INST_LIFE_PHASE <=", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseLike(String value) {
            addCriterion("INST_LIFE_PHASE like", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseNotLike(String value) {
            addCriterion("INST_LIFE_PHASE not like", value, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseIn(List<String> values) {
            addCriterion("INST_LIFE_PHASE in", values, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseNotIn(List<String> values) {
            addCriterion("INST_LIFE_PHASE not in", values, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseBetween(String value1, String value2) {
            addCriterion("INST_LIFE_PHASE between", value1, value2, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andInstLifePhaseNotBetween(String value1, String value2) {
            addCriterion("INST_LIFE_PHASE not between", value1, value2, "instLifePhase");
            return (Criteria) this;
        }

        public Criteria andIsTradingIsNull() {
            addCriterion("IS_TRADING is null");
            return (Criteria) this;
        }

        public Criteria andIsTradingIsNotNull() {
            addCriterion("IS_TRADING is not null");
            return (Criteria) this;
        }

        public Criteria andIsTradingEqualTo(boolean value) {
            addCriterion("IS_TRADING =", value, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingNotEqualTo(boolean value) {
            addCriterion("IS_TRADING <>", value, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingGreaterThan(boolean value) {
            addCriterion("IS_TRADING >", value, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingGreaterThanOrEqualTo(boolean value) {
            addCriterion("IS_TRADING >=", value, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingLessThan(boolean value) {
            addCriterion("IS_TRADING <", value, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingLessThanOrEqualTo(boolean value) {
            addCriterion("IS_TRADING <=", value, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingIn(List<Boolean> values) {
            addCriterion("IS_TRADING in", values, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingNotIn(List<Boolean> values) {
            addCriterion("IS_TRADING not in", values, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingBetween(boolean value1, boolean value2) {
            addCriterion("IS_TRADING between", value1, value2, "isTrading");
            return (Criteria) this;
        }

        public Criteria andIsTradingNotBetween(boolean value1, boolean value2) {
            addCriterion("IS_TRADING not between", value1, value2, "isTrading");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIsNull() {
            addCriterion("POSITION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIsNotNull() {
            addCriterion("POSITION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPositionTypeEqualTo(String value) {
            addCriterion("POSITION_TYPE =", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotEqualTo(String value) {
            addCriterion("POSITION_TYPE <>", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeGreaterThan(String value) {
            addCriterion("POSITION_TYPE >", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("POSITION_TYPE >=", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLessThan(String value) {
            addCriterion("POSITION_TYPE <", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLessThanOrEqualTo(String value) {
            addCriterion("POSITION_TYPE <=", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeLike(String value) {
            addCriterion("POSITION_TYPE like", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotLike(String value) {
            addCriterion("POSITION_TYPE not like", value, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeIn(List<String> values) {
            addCriterion("POSITION_TYPE in", values, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotIn(List<String> values) {
            addCriterion("POSITION_TYPE not in", values, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeBetween(String value1, String value2) {
            addCriterion("POSITION_TYPE between", value1, value2, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionTypeNotBetween(String value1, String value2) {
            addCriterion("POSITION_TYPE not between", value1, value2, "positionType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeIsNull() {
            addCriterion("POSITION_DATE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeIsNotNull() {
            addCriterion("POSITION_DATE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeEqualTo(String value) {
            addCriterion("POSITION_DATE_TYPE =", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeNotEqualTo(String value) {
            addCriterion("POSITION_DATE_TYPE <>", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeGreaterThan(String value) {
            addCriterion("POSITION_DATE_TYPE >", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("POSITION_DATE_TYPE >=", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeLessThan(String value) {
            addCriterion("POSITION_DATE_TYPE <", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeLessThanOrEqualTo(String value) {
            addCriterion("POSITION_DATE_TYPE <=", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeLike(String value) {
            addCriterion("POSITION_DATE_TYPE like", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeNotLike(String value) {
            addCriterion("POSITION_DATE_TYPE not like", value, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeIn(List<String> values) {
            addCriterion("POSITION_DATE_TYPE in", values, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeNotIn(List<String> values) {
            addCriterion("POSITION_DATE_TYPE not in", values, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeBetween(String value1, String value2) {
            addCriterion("POSITION_DATE_TYPE between", value1, value2, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andPositionDateTypeNotBetween(String value1, String value2) {
            addCriterion("POSITION_DATE_TYPE not between", value1, value2, "positionDateType");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioIsNull() {
            addCriterion("LONG_MARGIN_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioIsNotNull() {
            addCriterion("LONG_MARGIN_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioEqualTo(BigDecimal value) {
            addCriterion("LONG_MARGIN_RATIO =", value, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioNotEqualTo(BigDecimal value) {
            addCriterion("LONG_MARGIN_RATIO <>", value, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioGreaterThan(BigDecimal value) {
            addCriterion("LONG_MARGIN_RATIO >", value, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LONG_MARGIN_RATIO >=", value, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioLessThan(BigDecimal value) {
            addCriterion("LONG_MARGIN_RATIO <", value, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LONG_MARGIN_RATIO <=", value, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioIn(List<BigDecimal> values) {
            addCriterion("LONG_MARGIN_RATIO in", values, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioNotIn(List<BigDecimal> values) {
            addCriterion("LONG_MARGIN_RATIO not in", values, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LONG_MARGIN_RATIO between", value1, value2, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LONG_MARGIN_RATIO not between", value1, value2, "longMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioIsNull() {
            addCriterion("SHORT_MARGIN_RATIO is null");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioIsNotNull() {
            addCriterion("SHORT_MARGIN_RATIO is not null");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioEqualTo(BigDecimal value) {
            addCriterion("SHORT_MARGIN_RATIO =", value, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioNotEqualTo(BigDecimal value) {
            addCriterion("SHORT_MARGIN_RATIO <>", value, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioGreaterThan(BigDecimal value) {
            addCriterion("SHORT_MARGIN_RATIO >", value, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SHORT_MARGIN_RATIO >=", value, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioLessThan(BigDecimal value) {
            addCriterion("SHORT_MARGIN_RATIO <", value, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SHORT_MARGIN_RATIO <=", value, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioIn(List<BigDecimal> values) {
            addCriterion("SHORT_MARGIN_RATIO in", values, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioNotIn(List<BigDecimal> values) {
            addCriterion("SHORT_MARGIN_RATIO not in", values, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SHORT_MARGIN_RATIO between", value1, value2, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SHORT_MARGIN_RATIO not between", value1, value2, "shortMarginRatio");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmIsNull() {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM is null");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmIsNotNull() {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM is not null");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmEqualTo(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM =", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmNotEqualTo(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM <>", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmGreaterThan(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM >", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM >=", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmLessThan(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM <", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmLessThanOrEqualTo(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM <=", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmLike(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM like", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmNotLike(String value) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM not like", value, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmIn(List<String> values) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM in", values, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmNotIn(List<String> values) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM not in", values, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmBetween(String value1, String value2) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM between", value1, value2, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andMaxMarginSIdeAlgorithmNotBetween(String value1, String value2) {
            addCriterion("MAX_MARGIN_S_IDE_ALGORITHM not between", value1, value2, "maxMarginSIdeAlgorithm");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdIsNull() {
            addCriterion("UNDERLYING_INSTR_ID is null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdIsNotNull() {
            addCriterion("UNDERLYING_INSTR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdEqualTo(String value) {
            addCriterion("UNDERLYING_INSTR_ID =", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdNotEqualTo(String value) {
            addCriterion("UNDERLYING_INSTR_ID <>", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdGreaterThan(String value) {
            addCriterion("UNDERLYING_INSTR_ID >", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNDERLYING_INSTR_ID >=", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdLessThan(String value) {
            addCriterion("UNDERLYING_INSTR_ID <", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdLessThanOrEqualTo(String value) {
            addCriterion("UNDERLYING_INSTR_ID <=", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdLike(String value) {
            addCriterion("UNDERLYING_INSTR_ID like", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdNotLike(String value) {
            addCriterion("UNDERLYING_INSTR_ID not like", value, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdIn(List<String> values) {
            addCriterion("UNDERLYING_INSTR_ID in", values, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdNotIn(List<String> values) {
            addCriterion("UNDERLYING_INSTR_ID not in", values, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdBetween(String value1, String value2) {
            addCriterion("UNDERLYING_INSTR_ID between", value1, value2, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andUnderlyingInstrIdNotBetween(String value1, String value2) {
            addCriterion("UNDERLYING_INSTR_ID not between", value1, value2, "underlyingInstrId");
            return (Criteria) this;
        }

        public Criteria andStrikePriceIsNull() {
            addCriterion("STRIKE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andStrikePriceIsNotNull() {
            addCriterion("STRIKE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andStrikePriceEqualTo(BigDecimal value) {
            addCriterion("STRIKE_PRICE =", value, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceNotEqualTo(BigDecimal value) {
            addCriterion("STRIKE_PRICE <>", value, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceGreaterThan(BigDecimal value) {
            addCriterion("STRIKE_PRICE >", value, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STRIKE_PRICE >=", value, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceLessThan(BigDecimal value) {
            addCriterion("STRIKE_PRICE <", value, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STRIKE_PRICE <=", value, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceIn(List<BigDecimal> values) {
            addCriterion("STRIKE_PRICE in", values, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceNotIn(List<BigDecimal> values) {
            addCriterion("STRIKE_PRICE not in", values, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STRIKE_PRICE between", value1, value2, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andStrikePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STRIKE_PRICE not between", value1, value2, "strikePrice");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeIsNull() {
            addCriterion("OPTIONS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeIsNotNull() {
            addCriterion("OPTIONS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeEqualTo(String value) {
            addCriterion("OPTIONS_TYPE =", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotEqualTo(String value) {
            addCriterion("OPTIONS_TYPE <>", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeGreaterThan(String value) {
            addCriterion("OPTIONS_TYPE >", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OPTIONS_TYPE >=", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeLessThan(String value) {
            addCriterion("OPTIONS_TYPE <", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeLessThanOrEqualTo(String value) {
            addCriterion("OPTIONS_TYPE <=", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeLike(String value) {
            addCriterion("OPTIONS_TYPE like", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotLike(String value) {
            addCriterion("OPTIONS_TYPE not like", value, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeIn(List<String> values) {
            addCriterion("OPTIONS_TYPE in", values, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotIn(List<String> values) {
            addCriterion("OPTIONS_TYPE not in", values, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeBetween(String value1, String value2) {
            addCriterion("OPTIONS_TYPE between", value1, value2, "optionsType");
            return (Criteria) this;
        }

        public Criteria andOptionsTypeNotBetween(String value1, String value2) {
            addCriterion("OPTIONS_TYPE not between", value1, value2, "optionsType");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleIsNull() {
            addCriterion("UNDERLYING_MULTIPLE is null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleIsNotNull() {
            addCriterion("UNDERLYING_MULTIPLE is not null");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleEqualTo(BigDecimal value) {
            addCriterion("UNDERLYING_MULTIPLE =", value, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleNotEqualTo(BigDecimal value) {
            addCriterion("UNDERLYING_MULTIPLE <>", value, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleGreaterThan(BigDecimal value) {
            addCriterion("UNDERLYING_MULTIPLE >", value, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("UNDERLYING_MULTIPLE >=", value, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleLessThan(BigDecimal value) {
            addCriterion("UNDERLYING_MULTIPLE <", value, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("UNDERLYING_MULTIPLE <=", value, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleIn(List<BigDecimal> values) {
            addCriterion("UNDERLYING_MULTIPLE in", values, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleNotIn(List<BigDecimal> values) {
            addCriterion("UNDERLYING_MULTIPLE not in", values, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UNDERLYING_MULTIPLE between", value1, value2, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andUnderlyingMultipleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UNDERLYING_MULTIPLE not between", value1, value2, "underlyingMultiple");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeIsNull() {
            addCriterion("COMBINATION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeIsNotNull() {
            addCriterion("COMBINATION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeEqualTo(String value) {
            addCriterion("COMBINATION_TYPE =", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeNotEqualTo(String value) {
            addCriterion("COMBINATION_TYPE <>", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeGreaterThan(String value) {
            addCriterion("COMBINATION_TYPE >", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COMBINATION_TYPE >=", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeLessThan(String value) {
            addCriterion("COMBINATION_TYPE <", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeLessThanOrEqualTo(String value) {
            addCriterion("COMBINATION_TYPE <=", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeLike(String value) {
            addCriterion("COMBINATION_TYPE like", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeNotLike(String value) {
            addCriterion("COMBINATION_TYPE not like", value, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeIn(List<String> values) {
            addCriterion("COMBINATION_TYPE in", values, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeNotIn(List<String> values) {
            addCriterion("COMBINATION_TYPE not in", values, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeBetween(String value1, String value2) {
            addCriterion("COMBINATION_TYPE between", value1, value2, "combinationType");
            return (Criteria) this;
        }

        public Criteria andCombinationTypeNotBetween(String value1, String value2) {
            addCriterion("COMBINATION_TYPE not between", value1, value2, "combinationType");
            return (Criteria) this;
        }

        public Criteria andBuyUnitIsNull() {
            addCriterion("BUY_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andBuyUnitIsNotNull() {
            addCriterion("BUY_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andBuyUnitEqualTo(BigDecimal value) {
            addCriterion("BUY_UNIT =", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitNotEqualTo(BigDecimal value) {
            addCriterion("BUY_UNIT <>", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitGreaterThan(BigDecimal value) {
            addCriterion("BUY_UNIT >", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BUY_UNIT >=", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitLessThan(BigDecimal value) {
            addCriterion("BUY_UNIT <", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BUY_UNIT <=", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitIn(List<BigDecimal> values) {
            addCriterion("BUY_UNIT in", values, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitNotIn(List<BigDecimal> values) {
            addCriterion("BUY_UNIT not in", values, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUY_UNIT between", value1, value2, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BUY_UNIT not between", value1, value2, "buyUnit");
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