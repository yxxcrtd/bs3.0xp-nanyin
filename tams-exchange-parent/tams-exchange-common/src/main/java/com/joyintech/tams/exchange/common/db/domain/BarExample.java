package com.joyintech.tams.exchange.common.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BarExample() {
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

        public Criteria andBarTypeIsNull() {
            addCriterion("BAR_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBarTypeIsNotNull() {
            addCriterion("BAR_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBarTypeEqualTo(Integer value) {
            addCriterion("BAR_TYPE =", value, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeNotEqualTo(Integer value) {
            addCriterion("BAR_TYPE <>", value, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeGreaterThan(Integer value) {
            addCriterion("BAR_TYPE >", value, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("BAR_TYPE >=", value, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeLessThan(Integer value) {
            addCriterion("BAR_TYPE <", value, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeLessThanOrEqualTo(Integer value) {
            addCriterion("BAR_TYPE <=", value, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeIn(List<Integer> values) {
            addCriterion("BAR_TYPE in", values, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeNotIn(List<Integer> values) {
            addCriterion("BAR_TYPE not in", values, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeBetween(Integer value1, Integer value2) {
            addCriterion("BAR_TYPE between", value1, value2, "barType");
            return (Criteria) this;
        }

        public Criteria andBarTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("BAR_TYPE not between", value1, value2, "barType");
            return (Criteria) this;
        }

        public Criteria andStrTimeIsNull() {
            addCriterion("STR_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStrTimeIsNotNull() {
            addCriterion("STR_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStrTimeEqualTo(String value) {
            addCriterion("STR_TIME =", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeNotEqualTo(String value) {
            addCriterion("STR_TIME <>", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeGreaterThan(String value) {
            addCriterion("STR_TIME >", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeGreaterThanOrEqualTo(String value) {
            addCriterion("STR_TIME >=", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeLessThan(String value) {
            addCriterion("STR_TIME <", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeLessThanOrEqualTo(String value) {
            addCriterion("STR_TIME <=", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeLike(String value) {
            addCriterion("STR_TIME like", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeNotLike(String value) {
            addCriterion("STR_TIME not like", value, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeIn(List<String> values) {
            addCriterion("STR_TIME in", values, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeNotIn(List<String> values) {
            addCriterion("STR_TIME not in", values, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeBetween(String value1, String value2) {
            addCriterion("STR_TIME between", value1, value2, "strTime");
            return (Criteria) this;
        }

        public Criteria andStrTimeNotBetween(String value1, String value2) {
            addCriterion("STR_TIME not between", value1, value2, "strTime");
            return (Criteria) this;
        }

        public Criteria andOpenPriceIsNull() {
            addCriterion("OPEN_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andOpenPriceIsNotNull() {
            addCriterion("OPEN_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andOpenPriceEqualTo(BigDecimal value) {
            addCriterion("OPEN_PRICE =", value, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceNotEqualTo(BigDecimal value) {
            addCriterion("OPEN_PRICE <>", value, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceGreaterThan(BigDecimal value) {
            addCriterion("OPEN_PRICE >", value, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OPEN_PRICE >=", value, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceLessThan(BigDecimal value) {
            addCriterion("OPEN_PRICE <", value, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OPEN_PRICE <=", value, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceIn(List<BigDecimal> values) {
            addCriterion("OPEN_PRICE in", values, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceNotIn(List<BigDecimal> values) {
            addCriterion("OPEN_PRICE not in", values, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OPEN_PRICE between", value1, value2, "openPrice");
            return (Criteria) this;
        }

        public Criteria andOpenPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OPEN_PRICE not between", value1, value2, "openPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceIsNull() {
            addCriterion("HIGH_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andHighPriceIsNotNull() {
            addCriterion("HIGH_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andHighPriceEqualTo(BigDecimal value) {
            addCriterion("HIGH_PRICE =", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceNotEqualTo(BigDecimal value) {
            addCriterion("HIGH_PRICE <>", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceGreaterThan(BigDecimal value) {
            addCriterion("HIGH_PRICE >", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIGH_PRICE >=", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceLessThan(BigDecimal value) {
            addCriterion("HIGH_PRICE <", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIGH_PRICE <=", value, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceIn(List<BigDecimal> values) {
            addCriterion("HIGH_PRICE in", values, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceNotIn(List<BigDecimal> values) {
            addCriterion("HIGH_PRICE not in", values, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIGH_PRICE between", value1, value2, "highPrice");
            return (Criteria) this;
        }

        public Criteria andHighPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIGH_PRICE not between", value1, value2, "highPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceIsNull() {
            addCriterion("LOW_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andLowPriceIsNotNull() {
            addCriterion("LOW_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andLowPriceEqualTo(BigDecimal value) {
            addCriterion("LOW_PRICE =", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceNotEqualTo(BigDecimal value) {
            addCriterion("LOW_PRICE <>", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceGreaterThan(BigDecimal value) {
            addCriterion("LOW_PRICE >", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOW_PRICE >=", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceLessThan(BigDecimal value) {
            addCriterion("LOW_PRICE <", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOW_PRICE <=", value, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceIn(List<BigDecimal> values) {
            addCriterion("LOW_PRICE in", values, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceNotIn(List<BigDecimal> values) {
            addCriterion("LOW_PRICE not in", values, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOW_PRICE between", value1, value2, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andLowPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOW_PRICE not between", value1, value2, "lowPrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceIsNull() {
            addCriterion("CLOSE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andClosePriceIsNotNull() {
            addCriterion("CLOSE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andClosePriceEqualTo(BigDecimal value) {
            addCriterion("CLOSE_PRICE =", value, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceNotEqualTo(BigDecimal value) {
            addCriterion("CLOSE_PRICE <>", value, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceGreaterThan(BigDecimal value) {
            addCriterion("CLOSE_PRICE >", value, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CLOSE_PRICE >=", value, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceLessThan(BigDecimal value) {
            addCriterion("CLOSE_PRICE <", value, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CLOSE_PRICE <=", value, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceIn(List<BigDecimal> values) {
            addCriterion("CLOSE_PRICE in", values, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceNotIn(List<BigDecimal> values) {
            addCriterion("CLOSE_PRICE not in", values, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CLOSE_PRICE between", value1, value2, "closePrice");
            return (Criteria) this;
        }

        public Criteria andClosePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CLOSE_PRICE not between", value1, value2, "closePrice");
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

        public Criteria andVolumeEqualTo(BigDecimal value) {
            addCriterion("VOLUME =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(BigDecimal value) {
            addCriterion("VOLUME <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(BigDecimal value) {
            addCriterion("VOLUME >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("VOLUME >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(BigDecimal value) {
            addCriterion("VOLUME <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("VOLUME <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<BigDecimal> values) {
            addCriterion("VOLUME in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<BigDecimal> values) {
            addCriterion("VOLUME not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOLUME between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("VOLUME not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andTurnoverIsNull() {
            addCriterion("TURNOVER is null");
            return (Criteria) this;
        }

        public Criteria andTurnoverIsNotNull() {
            addCriterion("TURNOVER is not null");
            return (Criteria) this;
        }

        public Criteria andTurnoverEqualTo(BigDecimal value) {
            addCriterion("TURNOVER =", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotEqualTo(BigDecimal value) {
            addCriterion("TURNOVER <>", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverGreaterThan(BigDecimal value) {
            addCriterion("TURNOVER >", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TURNOVER >=", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverLessThan(BigDecimal value) {
            addCriterion("TURNOVER <", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TURNOVER <=", value, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverIn(List<BigDecimal> values) {
            addCriterion("TURNOVER in", values, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotIn(List<BigDecimal> values) {
            addCriterion("TURNOVER not in", values, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TURNOVER between", value1, value2, "turnover");
            return (Criteria) this;
        }

        public Criteria andTurnoverNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TURNOVER not between", value1, value2, "turnover");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceIsNull() {
            addCriterion("PRE_CLOSE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceIsNotNull() {
            addCriterion("PRE_CLOSE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceEqualTo(BigDecimal value) {
            addCriterion("PRE_CLOSE_PRICE =", value, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceNotEqualTo(BigDecimal value) {
            addCriterion("PRE_CLOSE_PRICE <>", value, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceGreaterThan(BigDecimal value) {
            addCriterion("PRE_CLOSE_PRICE >", value, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRE_CLOSE_PRICE >=", value, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceLessThan(BigDecimal value) {
            addCriterion("PRE_CLOSE_PRICE <", value, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRE_CLOSE_PRICE <=", value, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceIn(List<BigDecimal> values) {
            addCriterion("PRE_CLOSE_PRICE in", values, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceNotIn(List<BigDecimal> values) {
            addCriterion("PRE_CLOSE_PRICE not in", values, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRE_CLOSE_PRICE between", value1, value2, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPreClosePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRE_CLOSE_PRICE not between", value1, value2, "preClosePrice");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("POSITION is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(BigDecimal value) {
            addCriterion("POSITION =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(BigDecimal value) {
            addCriterion("POSITION <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(BigDecimal value) {
            addCriterion("POSITION >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("POSITION >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(BigDecimal value) {
            addCriterion("POSITION <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("POSITION <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<BigDecimal> values) {
            addCriterion("POSITION in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<BigDecimal> values) {
            addCriterion("POSITION not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("POSITION between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("POSITION not between", value1, value2, "position");
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