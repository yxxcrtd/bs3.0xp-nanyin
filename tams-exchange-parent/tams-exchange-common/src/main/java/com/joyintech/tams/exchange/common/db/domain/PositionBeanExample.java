package com.joyintech.tams.exchange.common.db.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PositionBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PositionBeanExample() {
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

        public Criteria andFinanceAccountIsNull() {
            addCriterion("FINANCE_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountIsNotNull() {
            addCriterion("FINANCE_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountEqualTo(String value) {
            addCriterion("FINANCE_ACCOUNT =", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountNotEqualTo(String value) {
            addCriterion("FINANCE_ACCOUNT <>", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountGreaterThan(String value) {
            addCriterion("FINANCE_ACCOUNT >", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountGreaterThanOrEqualTo(String value) {
            addCriterion("FINANCE_ACCOUNT >=", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountLessThan(String value) {
            addCriterion("FINANCE_ACCOUNT <", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountLessThanOrEqualTo(String value) {
            addCriterion("FINANCE_ACCOUNT <=", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountLike(String value) {
            addCriterion("FINANCE_ACCOUNT like", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountNotLike(String value) {
            addCriterion("FINANCE_ACCOUNT not like", value, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountIn(List<String> values) {
            addCriterion("FINANCE_ACCOUNT in", values, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountNotIn(List<String> values) {
            addCriterion("FINANCE_ACCOUNT not in", values, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountBetween(String value1, String value2) {
            addCriterion("FINANCE_ACCOUNT between", value1, value2, "financeAccount");
            return (Criteria) this;
        }

        public Criteria andFinanceAccountNotBetween(String value1, String value2) {
            addCriterion("FINANCE_ACCOUNT not between", value1, value2, "financeAccount");
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

        public Criteria andCostPriceIsNull() {
            addCriterion("COST_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andCostPriceIsNotNull() {
            addCriterion("COST_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andCostPriceEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE =", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE <>", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThan(BigDecimal value) {
            addCriterion("COST_PRICE >", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE >=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThan(BigDecimal value) {
            addCriterion("COST_PRICE <", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("COST_PRICE <=", value, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceIn(List<BigDecimal> values) {
            addCriterion("COST_PRICE in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotIn(List<BigDecimal> values) {
            addCriterion("COST_PRICE not in", values, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST_PRICE between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andCostPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("COST_PRICE not between", value1, value2, "costPrice");
            return (Criteria) this;
        }

        public Criteria andMarkValueIsNull() {
            addCriterion("MARK_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andMarkValueIsNotNull() {
            addCriterion("MARK_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andMarkValueEqualTo(BigDecimal value) {
            addCriterion("MARK_VALUE =", value, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueNotEqualTo(BigDecimal value) {
            addCriterion("MARK_VALUE <>", value, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueGreaterThan(BigDecimal value) {
            addCriterion("MARK_VALUE >", value, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MARK_VALUE >=", value, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueLessThan(BigDecimal value) {
            addCriterion("MARK_VALUE <", value, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MARK_VALUE <=", value, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueIn(List<BigDecimal> values) {
            addCriterion("MARK_VALUE in", values, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueNotIn(List<BigDecimal> values) {
            addCriterion("MARK_VALUE not in", values, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MARK_VALUE between", value1, value2, "markValue");
            return (Criteria) this;
        }

        public Criteria andMarkValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MARK_VALUE not between", value1, value2, "markValue");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeIsNull() {
            addCriterion("HOLD_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeIsNotNull() {
            addCriterion("HOLD_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeEqualTo(long value) {
            addCriterion("HOLD_VOLUME =", value, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeNotEqualTo(long value) {
            addCriterion("HOLD_VOLUME <>", value, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeGreaterThan(long value) {
            addCriterion("HOLD_VOLUME >", value, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("HOLD_VOLUME >=", value, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeLessThan(long value) {
            addCriterion("HOLD_VOLUME <", value, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeLessThanOrEqualTo(long value) {
            addCriterion("HOLD_VOLUME <=", value, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeIn(List<Long> values) {
            addCriterion("HOLD_VOLUME in", values, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeNotIn(List<Long> values) {
            addCriterion("HOLD_VOLUME not in", values, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeBetween(long value1, long value2) {
            addCriterion("HOLD_VOLUME between", value1, value2, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andHoldVolumeNotBetween(long value1, long value2) {
            addCriterion("HOLD_VOLUME not between", value1, value2, "holdVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeIsNull() {
            addCriterion("ENABLE_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeIsNotNull() {
            addCriterion("ENABLE_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeEqualTo(long value) {
            addCriterion("ENABLE_VOLUME =", value, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeNotEqualTo(long value) {
            addCriterion("ENABLE_VOLUME <>", value, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeGreaterThan(long value) {
            addCriterion("ENABLE_VOLUME >", value, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("ENABLE_VOLUME >=", value, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeLessThan(long value) {
            addCriterion("ENABLE_VOLUME <", value, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeLessThanOrEqualTo(long value) {
            addCriterion("ENABLE_VOLUME <=", value, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeIn(List<Long> values) {
            addCriterion("ENABLE_VOLUME in", values, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeNotIn(List<Long> values) {
            addCriterion("ENABLE_VOLUME not in", values, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeBetween(long value1, long value2) {
            addCriterion("ENABLE_VOLUME between", value1, value2, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andEnableVolumeNotBetween(long value1, long value2) {
            addCriterion("ENABLE_VOLUME not between", value1, value2, "enableVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeIsNull() {
            addCriterion("SELL_FROZEN_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeIsNotNull() {
            addCriterion("SELL_FROZEN_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeEqualTo(long value) {
            addCriterion("SELL_FROZEN_VOLUME =", value, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeNotEqualTo(long value) {
            addCriterion("SELL_FROZEN_VOLUME <>", value, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeGreaterThan(long value) {
            addCriterion("SELL_FROZEN_VOLUME >", value, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("SELL_FROZEN_VOLUME >=", value, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeLessThan(long value) {
            addCriterion("SELL_FROZEN_VOLUME <", value, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeLessThanOrEqualTo(long value) {
            addCriterion("SELL_FROZEN_VOLUME <=", value, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeIn(List<Long> values) {
            addCriterion("SELL_FROZEN_VOLUME in", values, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeNotIn(List<Long> values) {
            addCriterion("SELL_FROZEN_VOLUME not in", values, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeBetween(long value1, long value2) {
            addCriterion("SELL_FROZEN_VOLUME between", value1, value2, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andSellFrozenVolumeNotBetween(long value1, long value2) {
            addCriterion("SELL_FROZEN_VOLUME not between", value1, value2, "sellFrozenVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeIsNull() {
            addCriterion("TODAY_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeIsNotNull() {
            addCriterion("TODAY_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeEqualTo(long value) {
            addCriterion("TODAY_VOLUME =", value, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeNotEqualTo(long value) {
            addCriterion("TODAY_VOLUME <>", value, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeGreaterThan(long value) {
            addCriterion("TODAY_VOLUME >", value, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("TODAY_VOLUME >=", value, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeLessThan(long value) {
            addCriterion("TODAY_VOLUME <", value, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeLessThanOrEqualTo(long value) {
            addCriterion("TODAY_VOLUME <=", value, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeIn(List<Long> values) {
            addCriterion("TODAY_VOLUME in", values, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeNotIn(List<Long> values) {
            addCriterion("TODAY_VOLUME not in", values, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeBetween(long value1, long value2) {
            addCriterion("TODAY_VOLUME between", value1, value2, "todayVolume");
            return (Criteria) this;
        }

        public Criteria andTodayVolumeNotBetween(long value1, long value2) {
            addCriterion("TODAY_VOLUME not between", value1, value2, "todayVolume");
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