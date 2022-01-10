package com.joyintech.tams.exchange.common.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstrumentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstrumentExample() {
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

        public Criteria andMarketGreaterThanOrEqualTo(String value) {
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

        public Criteria andStockNameIsNull() {
            addCriterion("STOCK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStockNameIsNotNull() {
            addCriterion("STOCK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStockNameEqualTo(String value) {
            addCriterion("STOCK_NAME =", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotEqualTo(String value) {
            addCriterion("STOCK_NAME <>", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThan(String value) {
            addCriterion("STOCK_NAME >", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameGreaterThanOrEqualTo(String value) {
            addCriterion("STOCK_NAME >=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThan(String value) {
            addCriterion("STOCK_NAME <", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLessThanOrEqualTo(String value) {
            addCriterion("STOCK_NAME <=", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameLike(String value) {
            addCriterion("STOCK_NAME like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotLike(String value) {
            addCriterion("STOCK_NAME not like", value, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameIn(List<String> values) {
            addCriterion("STOCK_NAME in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotIn(List<String> values) {
            addCriterion("STOCK_NAME not in", values, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameBetween(String value1, String value2) {
            addCriterion("STOCK_NAME between", value1, value2, "stockName");
            return (Criteria) this;
        }

        public Criteria andStockNameNotBetween(String value1, String value2) {
            addCriterion("STOCK_NAME not between", value1, value2, "stockName");
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

        public Criteria andBuyUnitEqualTo(Integer value) {
            addCriterion("BUY_UNIT =", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitNotEqualTo(Integer value) {
            addCriterion("BUY_UNIT <>", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitGreaterThan(Integer value) {
            addCriterion("BUY_UNIT >", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("BUY_UNIT >=", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitLessThan(Integer value) {
            addCriterion("BUY_UNIT <", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitLessThanOrEqualTo(Integer value) {
            addCriterion("BUY_UNIT <=", value, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitIn(List<Integer> values) {
            addCriterion("BUY_UNIT in", values, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitNotIn(List<Integer> values) {
            addCriterion("BUY_UNIT not in", values, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitBetween(Integer value1, Integer value2) {
            addCriterion("BUY_UNIT between", value1, value2, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andBuyUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("BUY_UNIT not between", value1, value2, "buyUnit");
            return (Criteria) this;
        }

        public Criteria andPriceStepIsNull() {
            addCriterion("PRICE_STEP is null");
            return (Criteria) this;
        }

        public Criteria andPriceStepIsNotNull() {
            addCriterion("PRICE_STEP is not null");
            return (Criteria) this;
        }

        public Criteria andPriceStepEqualTo(BigDecimal value) {
            addCriterion("PRICE_STEP =", value, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepNotEqualTo(BigDecimal value) {
            addCriterion("PRICE_STEP <>", value, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepGreaterThan(BigDecimal value) {
            addCriterion("PRICE_STEP >", value, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE_STEP >=", value, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepLessThan(BigDecimal value) {
            addCriterion("PRICE_STEP <", value, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRICE_STEP <=", value, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepIn(List<BigDecimal> values) {
            addCriterion("PRICE_STEP in", values, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepNotIn(List<BigDecimal> values) {
            addCriterion("PRICE_STEP not in", values, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE_STEP between", value1, value2, "priceStep");
            return (Criteria) this;
        }

        public Criteria andPriceStepNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRICE_STEP not between", value1, value2, "priceStep");
            return (Criteria) this;
        }

        public Criteria andStoreUnitIsNull() {
            addCriterion("STORE_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andStoreUnitIsNotNull() {
            addCriterion("STORE_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andStoreUnitEqualTo(Integer value) {
            addCriterion("STORE_UNIT =", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotEqualTo(Integer value) {
            addCriterion("STORE_UNIT <>", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitGreaterThan(Integer value) {
            addCriterion("STORE_UNIT >", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("STORE_UNIT >=", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitLessThan(Integer value) {
            addCriterion("STORE_UNIT <", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitLessThanOrEqualTo(Integer value) {
            addCriterion("STORE_UNIT <=", value, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitIn(List<Integer> values) {
            addCriterion("STORE_UNIT in", values, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotIn(List<Integer> values) {
            addCriterion("STORE_UNIT not in", values, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitBetween(Integer value1, Integer value2) {
            addCriterion("STORE_UNIT between", value1, value2, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andStoreUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("STORE_UNIT not between", value1, value2, "storeUnit");
            return (Criteria) this;
        }

        public Criteria andUpPriceIsNull() {
            addCriterion("UP_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andUpPriceIsNotNull() {
            addCriterion("UP_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andUpPriceEqualTo(BigDecimal value) {
            addCriterion("UP_PRICE =", value, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceNotEqualTo(BigDecimal value) {
            addCriterion("UP_PRICE <>", value, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceGreaterThan(BigDecimal value) {
            addCriterion("UP_PRICE >", value, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("UP_PRICE >=", value, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceLessThan(BigDecimal value) {
            addCriterion("UP_PRICE <", value, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("UP_PRICE <=", value, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceIn(List<BigDecimal> values) {
            addCriterion("UP_PRICE in", values, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceNotIn(List<BigDecimal> values) {
            addCriterion("UP_PRICE not in", values, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UP_PRICE between", value1, value2, "upPrice");
            return (Criteria) this;
        }

        public Criteria andUpPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("UP_PRICE not between", value1, value2, "upPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceIsNull() {
            addCriterion("DOWN_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andDownPriceIsNotNull() {
            addCriterion("DOWN_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andDownPriceEqualTo(BigDecimal value) {
            addCriterion("DOWN_PRICE =", value, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceNotEqualTo(BigDecimal value) {
            addCriterion("DOWN_PRICE <>", value, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceGreaterThan(BigDecimal value) {
            addCriterion("DOWN_PRICE >", value, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DOWN_PRICE >=", value, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceLessThan(BigDecimal value) {
            addCriterion("DOWN_PRICE <", value, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DOWN_PRICE <=", value, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceIn(List<BigDecimal> values) {
            addCriterion("DOWN_PRICE in", values, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceNotIn(List<BigDecimal> values) {
            addCriterion("DOWN_PRICE not in", values, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DOWN_PRICE between", value1, value2, "downPrice");
            return (Criteria) this;
        }

        public Criteria andDownPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DOWN_PRICE not between", value1, value2, "downPrice");
            return (Criteria) this;
        }

        public Criteria andHighVolumeIsNull() {
            addCriterion("HIGH_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andHighVolumeIsNotNull() {
            addCriterion("HIGH_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andHighVolumeEqualTo(Integer value) {
            addCriterion("HIGH_VOLUME =", value, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeNotEqualTo(Integer value) {
            addCriterion("HIGH_VOLUME <>", value, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeGreaterThan(Integer value) {
            addCriterion("HIGH_VOLUME >", value, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("HIGH_VOLUME >=", value, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeLessThan(Integer value) {
            addCriterion("HIGH_VOLUME <", value, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("HIGH_VOLUME <=", value, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeIn(List<Integer> values) {
            addCriterion("HIGH_VOLUME in", values, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeNotIn(List<Integer> values) {
            addCriterion("HIGH_VOLUME not in", values, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeBetween(Integer value1, Integer value2) {
            addCriterion("HIGH_VOLUME between", value1, value2, "highVolume");
            return (Criteria) this;
        }

        public Criteria andHighVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("HIGH_VOLUME not between", value1, value2, "highVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeIsNull() {
            addCriterion("LOW_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andLowVolumeIsNotNull() {
            addCriterion("LOW_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andLowVolumeEqualTo(Integer value) {
            addCriterion("LOW_VOLUME =", value, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeNotEqualTo(Integer value) {
            addCriterion("LOW_VOLUME <>", value, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeGreaterThan(Integer value) {
            addCriterion("LOW_VOLUME >", value, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOW_VOLUME >=", value, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeLessThan(Integer value) {
            addCriterion("LOW_VOLUME <", value, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("LOW_VOLUME <=", value, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeIn(List<Integer> values) {
            addCriterion("LOW_VOLUME in", values, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeNotIn(List<Integer> values) {
            addCriterion("LOW_VOLUME not in", values, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeBetween(Integer value1, Integer value2) {
            addCriterion("LOW_VOLUME between", value1, value2, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andLowVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("LOW_VOLUME not between", value1, value2, "lowVolume");
            return (Criteria) this;
        }

        public Criteria andAssetTypeIsNull() {
            addCriterion("ASSET_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAssetTypeIsNotNull() {
            addCriterion("ASSET_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAssetTypeEqualTo(String value) {
            addCriterion("ASSET_TYPE =", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeNotEqualTo(String value) {
            addCriterion("ASSET_TYPE <>", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeGreaterThan(String value) {
            addCriterion("ASSET_TYPE >", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSET_TYPE >=", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeLessThan(String value) {
            addCriterion("ASSET_TYPE <", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeLessThanOrEqualTo(String value) {
            addCriterion("ASSET_TYPE <=", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeLike(String value) {
            addCriterion("ASSET_TYPE like", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeNotLike(String value) {
            addCriterion("ASSET_TYPE not like", value, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeIn(List<String> values) {
            addCriterion("ASSET_TYPE in", values, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeNotIn(List<String> values) {
            addCriterion("ASSET_TYPE not in", values, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeBetween(String value1, String value2) {
            addCriterion("ASSET_TYPE between", value1, value2, "assetType");
            return (Criteria) this;
        }

        public Criteria andAssetTypeNotBetween(String value1, String value2) {
            addCriterion("ASSET_TYPE not between", value1, value2, "assetType");
            return (Criteria) this;
        }

        public Criteria andTradPlatIsNull() {
            addCriterion("TRAD_PLAT is null");
            return (Criteria) this;
        }

        public Criteria andTradPlatIsNotNull() {
            addCriterion("TRAD_PLAT is not null");
            return (Criteria) this;
        }

        public Criteria andTradPlatEqualTo(String value) {
            addCriterion("TRAD_PLAT =", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatNotEqualTo(String value) {
            addCriterion("TRAD_PLAT <>", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatGreaterThan(String value) {
            addCriterion("TRAD_PLAT >", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatGreaterThanOrEqualTo(String value) {
            addCriterion("TRAD_PLAT >=", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatLessThan(String value) {
            addCriterion("TRAD_PLAT <", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatLessThanOrEqualTo(String value) {
            addCriterion("TRAD_PLAT <=", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatLike(String value) {
            addCriterion("TRAD_PLAT like", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatNotLike(String value) {
            addCriterion("TRAD_PLAT not like", value, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatIn(List<String> values) {
            addCriterion("TRAD_PLAT in", values, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatNotIn(List<String> values) {
            addCriterion("TRAD_PLAT not in", values, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatBetween(String value1, String value2) {
            addCriterion("TRAD_PLAT between", value1, value2, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andTradPlatNotBetween(String value1, String value2) {
            addCriterion("TRAD_PLAT not between", value1, value2, "tradPlat");
            return (Criteria) this;
        }

        public Criteria andParValueIsNull() {
            addCriterion("PAR_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andParValueIsNotNull() {
            addCriterion("PAR_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andParValueEqualTo(BigDecimal value) {
            addCriterion("PAR_VALUE =", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueNotEqualTo(BigDecimal value) {
            addCriterion("PAR_VALUE <>", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueGreaterThan(BigDecimal value) {
            addCriterion("PAR_VALUE >", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PAR_VALUE >=", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueLessThan(BigDecimal value) {
            addCriterion("PAR_VALUE <", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PAR_VALUE <=", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueIn(List<BigDecimal> values) {
            addCriterion("PAR_VALUE in", values, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueNotIn(List<BigDecimal> values) {
            addCriterion("PAR_VALUE not in", values, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAR_VALUE between", value1, value2, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PAR_VALUE not between", value1, value2, "parValue");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeIsNull() {
            addCriterion("STBTRANS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeIsNotNull() {
            addCriterion("STBTRANS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeEqualTo(String value) {
            addCriterion("STBTRANS_TYPE =", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeNotEqualTo(String value) {
            addCriterion("STBTRANS_TYPE <>", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeGreaterThan(String value) {
            addCriterion("STBTRANS_TYPE >", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STBTRANS_TYPE >=", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeLessThan(String value) {
            addCriterion("STBTRANS_TYPE <", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeLessThanOrEqualTo(String value) {
            addCriterion("STBTRANS_TYPE <=", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeLike(String value) {
            addCriterion("STBTRANS_TYPE like", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeNotLike(String value) {
            addCriterion("STBTRANS_TYPE not like", value, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeIn(List<String> values) {
            addCriterion("STBTRANS_TYPE in", values, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeNotIn(List<String> values) {
            addCriterion("STBTRANS_TYPE not in", values, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeBetween(String value1, String value2) {
            addCriterion("STBTRANS_TYPE between", value1, value2, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransTypeNotBetween(String value1, String value2) {
            addCriterion("STBTRANS_TYPE not between", value1, value2, "stbtransType");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusIsNull() {
            addCriterion("STBTRANS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusIsNotNull() {
            addCriterion("STBTRANS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusEqualTo(String value) {
            addCriterion("STBTRANS_STATUS =", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusNotEqualTo(String value) {
            addCriterion("STBTRANS_STATUS <>", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusGreaterThan(String value) {
            addCriterion("STBTRANS_STATUS >", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STBTRANS_STATUS >=", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusLessThan(String value) {
            addCriterion("STBTRANS_STATUS <", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusLessThanOrEqualTo(String value) {
            addCriterion("STBTRANS_STATUS <=", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusLike(String value) {
            addCriterion("STBTRANS_STATUS like", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusNotLike(String value) {
            addCriterion("STBTRANS_STATUS not like", value, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusIn(List<String> values) {
            addCriterion("STBTRANS_STATUS in", values, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusNotIn(List<String> values) {
            addCriterion("STBTRANS_STATUS not in", values, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusBetween(String value1, String value2) {
            addCriterion("STBTRANS_STATUS between", value1, value2, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransStatusNotBetween(String value1, String value2) {
            addCriterion("STBTRANS_STATUS not between", value1, value2, "stbtransStatus");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeIsNull() {
            addCriterion("SUB_STOCK_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeIsNotNull() {
            addCriterion("SUB_STOCK_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeEqualTo(String value) {
            addCriterion("SUB_STOCK_TYPE =", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeNotEqualTo(String value) {
            addCriterion("SUB_STOCK_TYPE <>", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeGreaterThan(String value) {
            addCriterion("SUB_STOCK_TYPE >", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUB_STOCK_TYPE >=", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeLessThan(String value) {
            addCriterion("SUB_STOCK_TYPE <", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeLessThanOrEqualTo(String value) {
            addCriterion("SUB_STOCK_TYPE <=", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeLike(String value) {
            addCriterion("SUB_STOCK_TYPE like", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeNotLike(String value) {
            addCriterion("SUB_STOCK_TYPE not like", value, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeIn(List<String> values) {
            addCriterion("SUB_STOCK_TYPE in", values, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeNotIn(List<String> values) {
            addCriterion("SUB_STOCK_TYPE not in", values, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeBetween(String value1, String value2) {
            addCriterion("SUB_STOCK_TYPE between", value1, value2, "subStockType");
            return (Criteria) this;
        }

        public Criteria andSubStockTypeNotBetween(String value1, String value2) {
            addCriterion("SUB_STOCK_TYPE not between", value1, value2, "subStockType");
            return (Criteria) this;
        }

        public Criteria andLowBalanceIsNull() {
            addCriterion("LOW_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andLowBalanceIsNotNull() {
            addCriterion("LOW_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andLowBalanceEqualTo(BigDecimal value) {
            addCriterion("LOW_BALANCE =", value, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceNotEqualTo(BigDecimal value) {
            addCriterion("LOW_BALANCE <>", value, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceGreaterThan(BigDecimal value) {
            addCriterion("LOW_BALANCE >", value, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOW_BALANCE >=", value, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceLessThan(BigDecimal value) {
            addCriterion("LOW_BALANCE <", value, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOW_BALANCE <=", value, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceIn(List<BigDecimal> values) {
            addCriterion("LOW_BALANCE in", values, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceNotIn(List<BigDecimal> values) {
            addCriterion("LOW_BALANCE not in", values, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOW_BALANCE between", value1, value2, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andLowBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOW_BALANCE not between", value1, value2, "lowBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceIsNull() {
            addCriterion("HIGH_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andHighBalanceIsNotNull() {
            addCriterion("HIGH_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andHighBalanceEqualTo(BigDecimal value) {
            addCriterion("HIGH_BALANCE =", value, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceNotEqualTo(BigDecimal value) {
            addCriterion("HIGH_BALANCE <>", value, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceGreaterThan(BigDecimal value) {
            addCriterion("HIGH_BALANCE >", value, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("HIGH_BALANCE >=", value, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceLessThan(BigDecimal value) {
            addCriterion("HIGH_BALANCE <", value, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("HIGH_BALANCE <=", value, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceIn(List<BigDecimal> values) {
            addCriterion("HIGH_BALANCE in", values, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceNotIn(List<BigDecimal> values) {
            addCriterion("HIGH_BALANCE not in", values, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIGH_BALANCE between", value1, value2, "highBalance");
            return (Criteria) this;
        }

        public Criteria andHighBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("HIGH_BALANCE not between", value1, value2, "highBalance");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagIsNull() {
            addCriterion("EXDIVIDEND_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagIsNotNull() {
            addCriterion("EXDIVIDEND_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagEqualTo(String value) {
            addCriterion("EXDIVIDEND_FLAG =", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagNotEqualTo(String value) {
            addCriterion("EXDIVIDEND_FLAG <>", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagGreaterThan(String value) {
            addCriterion("EXDIVIDEND_FLAG >", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagGreaterThanOrEqualTo(String value) {
            addCriterion("EXDIVIDEND_FLAG >=", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagLessThan(String value) {
            addCriterion("EXDIVIDEND_FLAG <", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagLessThanOrEqualTo(String value) {
            addCriterion("EXDIVIDEND_FLAG <=", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagLike(String value) {
            addCriterion("EXDIVIDEND_FLAG like", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagNotLike(String value) {
            addCriterion("EXDIVIDEND_FLAG not like", value, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagIn(List<String> values) {
            addCriterion("EXDIVIDEND_FLAG in", values, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagNotIn(List<String> values) {
            addCriterion("EXDIVIDEND_FLAG not in", values, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagBetween(String value1, String value2) {
            addCriterion("EXDIVIDEND_FLAG between", value1, value2, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andExdividendFlagNotBetween(String value1, String value2) {
            addCriterion("EXDIVIDEND_FLAG not between", value1, value2, "exdividendFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagIsNull() {
            addCriterion("BONDINV_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagIsNotNull() {
            addCriterion("BONDINV_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagEqualTo(String value) {
            addCriterion("BONDINV_FLAG =", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagNotEqualTo(String value) {
            addCriterion("BONDINV_FLAG <>", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagGreaterThan(String value) {
            addCriterion("BONDINV_FLAG >", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BONDINV_FLAG >=", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagLessThan(String value) {
            addCriterion("BONDINV_FLAG <", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagLessThanOrEqualTo(String value) {
            addCriterion("BONDINV_FLAG <=", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagLike(String value) {
            addCriterion("BONDINV_FLAG like", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagNotLike(String value) {
            addCriterion("BONDINV_FLAG not like", value, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagIn(List<String> values) {
            addCriterion("BONDINV_FLAG in", values, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagNotIn(List<String> values) {
            addCriterion("BONDINV_FLAG not in", values, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagBetween(String value1, String value2) {
            addCriterion("BONDINV_FLAG between", value1, value2, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andBondinvFlagNotBetween(String value1, String value2) {
            addCriterion("BONDINV_FLAG not between", value1, value2, "bondinvFlag");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusIsNull() {
            addCriterion("STKCODE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusIsNotNull() {
            addCriterion("STKCODE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusEqualTo(String value) {
            addCriterion("STKCODE_STATUS =", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusNotEqualTo(String value) {
            addCriterion("STKCODE_STATUS <>", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusGreaterThan(String value) {
            addCriterion("STKCODE_STATUS >", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STKCODE_STATUS >=", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusLessThan(String value) {
            addCriterion("STKCODE_STATUS <", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusLessThanOrEqualTo(String value) {
            addCriterion("STKCODE_STATUS <=", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusLike(String value) {
            addCriterion("STKCODE_STATUS like", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusNotLike(String value) {
            addCriterion("STKCODE_STATUS not like", value, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusIn(List<String> values) {
            addCriterion("STKCODE_STATUS in", values, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusNotIn(List<String> values) {
            addCriterion("STKCODE_STATUS not in", values, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusBetween(String value1, String value2) {
            addCriterion("STKCODE_STATUS between", value1, value2, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStkcodeStatusNotBetween(String value1, String value2) {
            addCriterion("STKCODE_STATUS not between", value1, value2, "stkcodeStatus");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagIsNull() {
            addCriterion("STBTRANS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagIsNotNull() {
            addCriterion("STBTRANS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagEqualTo(String value) {
            addCriterion("STBTRANS_FLAG =", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagNotEqualTo(String value) {
            addCriterion("STBTRANS_FLAG <>", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagGreaterThan(String value) {
            addCriterion("STBTRANS_FLAG >", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagGreaterThanOrEqualTo(String value) {
            addCriterion("STBTRANS_FLAG >=", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagLessThan(String value) {
            addCriterion("STBTRANS_FLAG <", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagLessThanOrEqualTo(String value) {
            addCriterion("STBTRANS_FLAG <=", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagLike(String value) {
            addCriterion("STBTRANS_FLAG like", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagNotLike(String value) {
            addCriterion("STBTRANS_FLAG not like", value, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagIn(List<String> values) {
            addCriterion("STBTRANS_FLAG in", values, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagNotIn(List<String> values) {
            addCriterion("STBTRANS_FLAG not in", values, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagBetween(String value1, String value2) {
            addCriterion("STBTRANS_FLAG between", value1, value2, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andStbtransFlagNotBetween(String value1, String value2) {
            addCriterion("STBTRANS_FLAG not between", value1, value2, "stbtransFlag");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeIsNull() {
            addCriterion("MARKET_LOW_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeIsNotNull() {
            addCriterion("MARKET_LOW_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeEqualTo(Integer value) {
            addCriterion("MARKET_LOW_VOLUME =", value, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeNotEqualTo(Integer value) {
            addCriterion("MARKET_LOW_VOLUME <>", value, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeGreaterThan(Integer value) {
            addCriterion("MARKET_LOW_VOLUME >", value, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MARKET_LOW_VOLUME >=", value, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeLessThan(Integer value) {
            addCriterion("MARKET_LOW_VOLUME <", value, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("MARKET_LOW_VOLUME <=", value, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeIn(List<Integer> values) {
            addCriterion("MARKET_LOW_VOLUME in", values, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeNotIn(List<Integer> values) {
            addCriterion("MARKET_LOW_VOLUME not in", values, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeBetween(Integer value1, Integer value2) {
            addCriterion("MARKET_LOW_VOLUME between", value1, value2, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketLowVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("MARKET_LOW_VOLUME not between", value1, value2, "marketLowVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeIsNull() {
            addCriterion("MARKET_HIGH_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeIsNotNull() {
            addCriterion("MARKET_HIGH_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeEqualTo(Integer value) {
            addCriterion("MARKET_HIGH_VOLUME =", value, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeNotEqualTo(Integer value) {
            addCriterion("MARKET_HIGH_VOLUME <>", value, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeGreaterThan(Integer value) {
            addCriterion("MARKET_HIGH_VOLUME >", value, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("MARKET_HIGH_VOLUME >=", value, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeLessThan(Integer value) {
            addCriterion("MARKET_HIGH_VOLUME <", value, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("MARKET_HIGH_VOLUME <=", value, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeIn(List<Integer> values) {
            addCriterion("MARKET_HIGH_VOLUME in", values, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeNotIn(List<Integer> values) {
            addCriterion("MARKET_HIGH_VOLUME not in", values, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeBetween(Integer value1, Integer value2) {
            addCriterion("MARKET_HIGH_VOLUME between", value1, value2, "marketHighVolume");
            return (Criteria) this;
        }

        public Criteria andMarketHighVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("MARKET_HIGH_VOLUME not between", value1, value2, "marketHighVolume");
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