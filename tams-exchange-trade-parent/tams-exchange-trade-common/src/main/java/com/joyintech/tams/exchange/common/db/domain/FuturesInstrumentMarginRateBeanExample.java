package com.joyintech.tams.exchange.common.db.domain;

import java.util.ArrayList;
import java.util.List;

public class FuturesInstrumentMarginRateBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FuturesInstrumentMarginRateBeanExample() {
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

        public Criteria andBrokerIdIsNull() {
            addCriterion("BROKER_ID is null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIsNotNull() {
            addCriterion("BROKER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerIdEqualTo(String value) {
            addCriterion("BROKER_ID =", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotEqualTo(String value) {
            addCriterion("BROKER_ID <>", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThan(String value) {
            addCriterion("BROKER_ID >", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdGreaterThanOrEqualTo(String value) {
            addCriterion("BROKER_ID >=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThan(String value) {
            addCriterion("BROKER_ID <", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLessThanOrEqualTo(String value) {
            addCriterion("BROKER_ID <=", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdLike(String value) {
            addCriterion("BROKER_ID like", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotLike(String value) {
            addCriterion("BROKER_ID not like", value, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdIn(List<String> values) {
            addCriterion("BROKER_ID in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotIn(List<String> values) {
            addCriterion("BROKER_ID not in", values, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdBetween(String value1, String value2) {
            addCriterion("BROKER_ID between", value1, value2, "brokerId");
            return (Criteria) this;
        }

        public Criteria andBrokerIdNotBetween(String value1, String value2) {
            addCriterion("BROKER_ID not between", value1, value2, "brokerId");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeIsNull() {
            addCriterion("INVESTOR_RANGE is null");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeIsNotNull() {
            addCriterion("INVESTOR_RANGE is not null");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeEqualTo(String value) {
            addCriterion("INVESTOR_RANGE =", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeNotEqualTo(String value) {
            addCriterion("INVESTOR_RANGE <>", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeGreaterThan(String value) {
            addCriterion("INVESTOR_RANGE >", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeGreaterThanOrEqualTo(String value) {
            addCriterion("INVESTOR_RANGE >=", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeLessThan(String value) {
            addCriterion("INVESTOR_RANGE <", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeLessThanOrEqualTo(String value) {
            addCriterion("INVESTOR_RANGE <=", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeLike(String value) {
            addCriterion("INVESTOR_RANGE like", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeNotLike(String value) {
            addCriterion("INVESTOR_RANGE not like", value, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeIn(List<String> values) {
            addCriterion("INVESTOR_RANGE in", values, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeNotIn(List<String> values) {
            addCriterion("INVESTOR_RANGE not in", values, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeBetween(String value1, String value2) {
            addCriterion("INVESTOR_RANGE between", value1, value2, "investorRange");
            return (Criteria) this;
        }

        public Criteria andInvestorRangeNotBetween(String value1, String value2) {
            addCriterion("INVESTOR_RANGE not between", value1, value2, "investorRange");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagIsNull() {
            addCriterion("HEDGE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagIsNotNull() {
            addCriterion("HEDGE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagEqualTo(String value) {
            addCriterion("HEDGE_FLAG =", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagNotEqualTo(String value) {
            addCriterion("HEDGE_FLAG <>", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagGreaterThan(String value) {
            addCriterion("HEDGE_FLAG >", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("HEDGE_FLAG >=", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagLessThan(String value) {
            addCriterion("HEDGE_FLAG <", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagLessThanOrEqualTo(String value) {
            addCriterion("HEDGE_FLAG <=", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagLike(String value) {
            addCriterion("HEDGE_FLAG like", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagNotLike(String value) {
            addCriterion("HEDGE_FLAG not like", value, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagIn(List<String> values) {
            addCriterion("HEDGE_FLAG in", values, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagNotIn(List<String> values) {
            addCriterion("HEDGE_FLAG not in", values, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagBetween(String value1, String value2) {
            addCriterion("HEDGE_FLAG between", value1, value2, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andHedgeFlagNotBetween(String value1, String value2) {
            addCriterion("HEDGE_FLAG not between", value1, value2, "hedgeFlag");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyIsNull() {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyIsNotNull() {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY =", value, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyNotEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY <>", value, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyGreaterThan(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY >", value, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyGreaterThanOrEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY >=", value, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyLessThan(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY <", value, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyLessThanOrEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY <=", value, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyIn(List<Double> values) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY in", values, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyNotIn(List<Double> values) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY not in", values, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyBetween(double value1, double value2) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY between", value1, value2, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByMoneyNotBetween(double value1, double value2) {
            addCriterion("LONG_MARGIN_RATIO_BY_MONEY not between", value1, value2, "longMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeIsNull() {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeIsNotNull() {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME =", value, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeNotEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME <>", value, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeGreaterThan(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME >", value, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeGreaterThanOrEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME >=", value, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeLessThan(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME <", value, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeLessThanOrEqualTo(double value) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME <=", value, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeIn(List<Double> values) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME in", values, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeNotIn(List<Double> values) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME not in", values, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeBetween(double value1, double value2) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME between", value1, value2, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andLongMarginRatioByVolumeNotBetween(double value1, double value2) {
            addCriterion("LONG_MARGIN_RATIO_BY_VOLUME not between", value1, value2, "longMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyIsNull() {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyIsNotNull() {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY =", value, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyNotEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY <>", value, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyGreaterThan(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY >", value, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyGreaterThanOrEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY >=", value, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyLessThan(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY <", value, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyLessThanOrEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY <=", value, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyIn(List<Double> values) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY in", values, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyNotIn(List<Double> values) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY not in", values, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyBetween(double value1, double value2) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY between", value1, value2, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByMoneyNotBetween(double value1, double value2) {
            addCriterion("SHORT_MARGIN_RATIO_BY_MONEY not between", value1, value2, "shortMarginRatioByMoney");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeIsNull() {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeIsNotNull() {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME =", value, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeNotEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME <>", value, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeGreaterThan(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME >", value, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeGreaterThanOrEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME >=", value, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeLessThan(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME <", value, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeLessThanOrEqualTo(double value) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME <=", value, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeIn(List<Double> values) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME in", values, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeNotIn(List<Double> values) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME not in", values, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeBetween(double value1, double value2) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME between", value1, value2, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andShortMarginRatioByVolumeNotBetween(double value1, double value2) {
            addCriterion("SHORT_MARGIN_RATIO_BY_VOLUME not between", value1, value2, "shortMarginRatioByVolume");
            return (Criteria) this;
        }

        public Criteria andIsRelativeIsNull() {
            addCriterion("IS_RELATIVE is null");
            return (Criteria) this;
        }

        public Criteria andIsRelativeIsNotNull() {
            addCriterion("IS_RELATIVE is not null");
            return (Criteria) this;
        }

        public Criteria andIsRelativeEqualTo(Short value) {
            addCriterion("IS_RELATIVE =", value, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeNotEqualTo(Short value) {
            addCriterion("IS_RELATIVE <>", value, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeGreaterThan(Short value) {
            addCriterion("IS_RELATIVE >", value, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_RELATIVE >=", value, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeLessThan(Short value) {
            addCriterion("IS_RELATIVE <", value, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeLessThanOrEqualTo(Short value) {
            addCriterion("IS_RELATIVE <=", value, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeIn(List<Short> values) {
            addCriterion("IS_RELATIVE in", values, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeNotIn(List<Short> values) {
            addCriterion("IS_RELATIVE not in", values, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeBetween(Short value1, Short value2) {
            addCriterion("IS_RELATIVE between", value1, value2, "isRelative");
            return (Criteria) this;
        }

        public Criteria andIsRelativeNotBetween(Short value1, Short value2) {
            addCriterion("IS_RELATIVE not between", value1, value2, "isRelative");
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

        public Criteria andInvestUnitIdIsNull() {
            addCriterion("INVEST_UNIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdIsNotNull() {
            addCriterion("INVEST_UNIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdEqualTo(String value) {
            addCriterion("INVEST_UNIT_ID =", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdNotEqualTo(String value) {
            addCriterion("INVEST_UNIT_ID <>", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdGreaterThan(String value) {
            addCriterion("INVEST_UNIT_ID >", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("INVEST_UNIT_ID >=", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdLessThan(String value) {
            addCriterion("INVEST_UNIT_ID <", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdLessThanOrEqualTo(String value) {
            addCriterion("INVEST_UNIT_ID <=", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdLike(String value) {
            addCriterion("INVEST_UNIT_ID like", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdNotLike(String value) {
            addCriterion("INVEST_UNIT_ID not like", value, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdIn(List<String> values) {
            addCriterion("INVEST_UNIT_ID in", values, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdNotIn(List<String> values) {
            addCriterion("INVEST_UNIT_ID not in", values, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdBetween(String value1, String value2) {
            addCriterion("INVEST_UNIT_ID between", value1, value2, "investUnitId");
            return (Criteria) this;
        }

        public Criteria andInvestUnitIdNotBetween(String value1, String value2) {
            addCriterion("INVEST_UNIT_ID not between", value1, value2, "investUnitId");
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