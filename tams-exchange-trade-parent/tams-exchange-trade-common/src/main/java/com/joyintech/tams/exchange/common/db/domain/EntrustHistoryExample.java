package com.joyintech.tams.exchange.common.db.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntrustHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EntrustHistoryExample() {
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

        public Criteria andParentOrderIdIsNull() {
            addCriterion("PARENT_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdIsNotNull() {
            addCriterion("PARENT_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdEqualTo(String value) {
            addCriterion("PARENT_ORDER_ID =", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdNotEqualTo(String value) {
            addCriterion("PARENT_ORDER_ID <>", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdGreaterThan(String value) {
            addCriterion("PARENT_ORDER_ID >", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ORDER_ID >=", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdLessThan(String value) {
            addCriterion("PARENT_ORDER_ID <", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ORDER_ID <=", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdLike(String value) {
            addCriterion("PARENT_ORDER_ID like", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdNotLike(String value) {
            addCriterion("PARENT_ORDER_ID not like", value, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdIn(List<String> values) {
            addCriterion("PARENT_ORDER_ID in", values, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdNotIn(List<String> values) {
            addCriterion("PARENT_ORDER_ID not in", values, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdBetween(String value1, String value2) {
            addCriterion("PARENT_ORDER_ID between", value1, value2, "parentOrderId");
            return (Criteria) this;
        }

        public Criteria andParentOrderIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ORDER_ID not between", value1, value2, "parentOrderId");
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

        public Criteria andPortfolioIdIsNull() {
            addCriterion("PORTFOLIO_ID is null");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdIsNotNull() {
            addCriterion("PORTFOLIO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdEqualTo(String value) {
            addCriterion("PORTFOLIO_ID =", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdNotEqualTo(String value) {
            addCriterion("PORTFOLIO_ID <>", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdGreaterThan(String value) {
            addCriterion("PORTFOLIO_ID >", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdGreaterThanOrEqualTo(String value) {
            addCriterion("PORTFOLIO_ID >=", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdLessThan(String value) {
            addCriterion("PORTFOLIO_ID <", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdLessThanOrEqualTo(String value) {
            addCriterion("PORTFOLIO_ID <=", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdLike(String value) {
            addCriterion("PORTFOLIO_ID like", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdNotLike(String value) {
            addCriterion("PORTFOLIO_ID not like", value, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdIn(List<String> values) {
            addCriterion("PORTFOLIO_ID in", values, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdNotIn(List<String> values) {
            addCriterion("PORTFOLIO_ID not in", values, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdBetween(String value1, String value2) {
            addCriterion("PORTFOLIO_ID between", value1, value2, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andPortfolioIdNotBetween(String value1, String value2) {
            addCriterion("PORTFOLIO_ID not between", value1, value2, "portfolioId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
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

        public Criteria andAccountNameIsNull() {
            addCriterion("ACCOUNT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("ACCOUNT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("ACCOUNT_NAME =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("ACCOUNT_NAME <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("ACCOUNT_NAME >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NAME >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("ACCOUNT_NAME <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NAME <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("ACCOUNT_NAME like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("ACCOUNT_NAME not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("ACCOUNT_NAME in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("ACCOUNT_NAME not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NAME between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NAME not between", value1, value2, "accountName");
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

        public Criteria andEntrustTypeIsNull() {
            addCriterion("ENTRUST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeIsNotNull() {
            addCriterion("ENTRUST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeEqualTo(String value) {
            addCriterion("ENTRUST_TYPE =", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeNotEqualTo(String value) {
            addCriterion("ENTRUST_TYPE <>", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeGreaterThan(String value) {
            addCriterion("ENTRUST_TYPE >", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ENTRUST_TYPE >=", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeLessThan(String value) {
            addCriterion("ENTRUST_TYPE <", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeLessThanOrEqualTo(String value) {
            addCriterion("ENTRUST_TYPE <=", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeLike(String value) {
            addCriterion("ENTRUST_TYPE like", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeNotLike(String value) {
            addCriterion("ENTRUST_TYPE not like", value, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeIn(List<String> values) {
            addCriterion("ENTRUST_TYPE in", values, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeNotIn(List<String> values) {
            addCriterion("ENTRUST_TYPE not in", values, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeBetween(String value1, String value2) {
            addCriterion("ENTRUST_TYPE between", value1, value2, "entrustType");
            return (Criteria) this;
        }

        public Criteria andEntrustTypeNotBetween(String value1, String value2) {
            addCriterion("ENTRUST_TYPE not between", value1, value2, "entrustType");
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

        public Criteria andEntrustPropGreaterThanOrEqualTo(String value) {
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

        public Criteria andBsFlagIsNull() {
            addCriterion("BS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBsFlagIsNotNull() {
            addCriterion("BS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBsFlagEqualTo(String value) {
            addCriterion("BS_FLAG =", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotEqualTo(String value) {
            addCriterion("BS_FLAG <>", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagGreaterThan(String value) {
            addCriterion("BS_FLAG >", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BS_FLAG >=", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagLessThan(String value) {
            addCriterion("BS_FLAG <", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagLessThanOrEqualTo(String value) {
            addCriterion("BS_FLAG <=", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagLike(String value) {
            addCriterion("BS_FLAG like", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotLike(String value) {
            addCriterion("BS_FLAG not like", value, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagIn(List<String> values) {
            addCriterion("BS_FLAG in", values, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotIn(List<String> values) {
            addCriterion("BS_FLAG not in", values, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagBetween(String value1, String value2) {
            addCriterion("BS_FLAG between", value1, value2, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andBsFlagNotBetween(String value1, String value2) {
            addCriterion("BS_FLAG not between", value1, value2, "bsFlag");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeIsNull() {
            addCriterion("ENTRUST_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeIsNotNull() {
            addCriterion("ENTRUST_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeEqualTo(long value) {
            addCriterion("ENTRUST_VOLUME =", value, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeNotEqualTo(long value) {
            addCriterion("ENTRUST_VOLUME <>", value, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeGreaterThan(long value) {
            addCriterion("ENTRUST_VOLUME >", value, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("ENTRUST_VOLUME >=", value, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeLessThan(long value) {
            addCriterion("ENTRUST_VOLUME <", value, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeLessThanOrEqualTo(long value) {
            addCriterion("ENTRUST_VOLUME <=", value, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeIn(List<Long> values) {
            addCriterion("ENTRUST_VOLUME in", values, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeNotIn(List<Long> values) {
            addCriterion("ENTRUST_VOLUME not in", values, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeBetween(long value1, long value2) {
            addCriterion("ENTRUST_VOLUME between", value1, value2, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustVolumeNotBetween(long value1, long value2) {
            addCriterion("ENTRUST_VOLUME not between", value1, value2, "entrustVolume");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceIsNull() {
            addCriterion("ENTRUST_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceIsNotNull() {
            addCriterion("ENTRUST_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceEqualTo(double value) {
            addCriterion("ENTRUST_PRICE =", value, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceNotEqualTo(double value) {
            addCriterion("ENTRUST_PRICE <>", value, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceGreaterThan(double value) {
            addCriterion("ENTRUST_PRICE >", value, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceGreaterThanOrEqualTo(double value) {
            addCriterion("ENTRUST_PRICE >=", value, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceLessThan(double value) {
            addCriterion("ENTRUST_PRICE <", value, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceLessThanOrEqualTo(double value) {
            addCriterion("ENTRUST_PRICE <=", value, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceIn(List<Double> values) {
            addCriterion("ENTRUST_PRICE in", values, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceNotIn(List<Double> values) {
            addCriterion("ENTRUST_PRICE not in", values, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceBetween(double value1, double value2) {
            addCriterion("ENTRUST_PRICE between", value1, value2, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andEntrustPriceNotBetween(double value1, double value2) {
            addCriterion("ENTRUST_PRICE not between", value1, value2, "entrustPrice");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceIsNull() {
            addCriterion("FROZEN_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceIsNotNull() {
            addCriterion("FROZEN_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceEqualTo(double value) {
            addCriterion("FROZEN_BALANCE =", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceNotEqualTo(double value) {
            addCriterion("FROZEN_BALANCE <>", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceGreaterThan(double value) {
            addCriterion("FROZEN_BALANCE >", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceGreaterThanOrEqualTo(double value) {
            addCriterion("FROZEN_BALANCE >=", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceLessThan(double value) {
            addCriterion("FROZEN_BALANCE <", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceLessThanOrEqualTo(double value) {
            addCriterion("FROZEN_BALANCE <=", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceIn(List<Double> values) {
            addCriterion("FROZEN_BALANCE in", values, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceNotIn(List<Double> values) {
            addCriterion("FROZEN_BALANCE not in", values, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceBetween(double value1, double value2) {
            addCriterion("FROZEN_BALANCE between", value1, value2, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceNotBetween(double value1, double value2) {
            addCriterion("FROZEN_BALANCE not between", value1, value2, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeIsNull() {
            addCriterion("FROZEN_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeIsNotNull() {
            addCriterion("FROZEN_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeEqualTo(long value) {
            addCriterion("FROZEN_VOLUME =", value, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeNotEqualTo(long value) {
            addCriterion("FROZEN_VOLUME <>", value, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeGreaterThan(long value) {
            addCriterion("FROZEN_VOLUME >", value, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("FROZEN_VOLUME >=", value, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeLessThan(long value) {
            addCriterion("FROZEN_VOLUME <", value, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeLessThanOrEqualTo(long value) {
            addCriterion("FROZEN_VOLUME <=", value, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeIn(List<Long> values) {
            addCriterion("FROZEN_VOLUME in", values, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeNotIn(List<Long> values) {
            addCriterion("FROZEN_VOLUME not in", values, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeBetween(long value1, long value2) {
            addCriterion("FROZEN_VOLUME between", value1, value2, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andFrozenVolumeNotBetween(long value1, long value2) {
            addCriterion("FROZEN_VOLUME not between", value1, value2, "frozenVolume");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceIsNull() {
            addCriterion("TRADED_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceIsNotNull() {
            addCriterion("TRADED_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceEqualTo(double value) {
            addCriterion("TRADED_BALANCE =", value, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceNotEqualTo(double value) {
            addCriterion("TRADED_BALANCE <>", value, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceGreaterThan(double value) {
            addCriterion("TRADED_BALANCE >", value, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceGreaterThanOrEqualTo(double value) {
            addCriterion("TRADED_BALANCE >=", value, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceLessThan(double value) {
            addCriterion("TRADED_BALANCE <", value, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceLessThanOrEqualTo(double value) {
            addCriterion("TRADED_BALANCE <=", value, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceIn(List<Double> values) {
            addCriterion("TRADED_BALANCE in", values, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceNotIn(List<Double> values) {
            addCriterion("TRADED_BALANCE not in", values, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceBetween(double value1, double value2) {
            addCriterion("TRADED_BALANCE between", value1, value2, "tradedBalance");
            return (Criteria) this;
        }

        public Criteria andTradedBalanceNotBetween(double value1, double value2) {
            addCriterion("TRADED_BALANCE not between", value1, value2, "tradedBalance");
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

        public Criteria andTradedVolumeEqualTo(long value) {
            addCriterion("TRADED_VOLUME =", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeNotEqualTo(long value) {
            addCriterion("TRADED_VOLUME <>", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeGreaterThan(long value) {
            addCriterion("TRADED_VOLUME >", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("TRADED_VOLUME >=", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeLessThan(long value) {
            addCriterion("TRADED_VOLUME <", value, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeLessThanOrEqualTo(long value) {
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

        public Criteria andTradedVolumeBetween(long value1, long value2) {
            addCriterion("TRADED_VOLUME between", value1, value2, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedVolumeNotBetween(long value1, long value2) {
            addCriterion("TRADED_VOLUME not between", value1, value2, "tradedVolume");
            return (Criteria) this;
        }

        public Criteria andTradedPriceIsNull() {
            addCriterion("TRADED_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andTradedPriceIsNotNull() {
            addCriterion("TRADED_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andTradedPriceEqualTo(double value) {
            addCriterion("TRADED_PRICE =", value, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceNotEqualTo(double value) {
            addCriterion("TRADED_PRICE <>", value, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceGreaterThan(double value) {
            addCriterion("TRADED_PRICE >", value, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceGreaterThanOrEqualTo(double value) {
            addCriterion("TRADED_PRICE >=", value, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceLessThan(double value) {
            addCriterion("TRADED_PRICE <", value, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceLessThanOrEqualTo(double value) {
            addCriterion("TRADED_PRICE <=", value, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceIn(List<Double> values) {
            addCriterion("TRADED_PRICE in", values, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceNotIn(List<Double> values) {
            addCriterion("TRADED_PRICE not in", values, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceBetween(double value1, double value2) {
            addCriterion("TRADED_PRICE between", value1, value2, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andTradedPriceNotBetween(double value1, double value2) {
            addCriterion("TRADED_PRICE not between", value1, value2, "tradedPrice");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeIsNull() {
            addCriterion("CANCEL_VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeIsNotNull() {
            addCriterion("CANCEL_VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeEqualTo(long value) {
            addCriterion("CANCEL_VOLUME =", value, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeNotEqualTo(long value) {
            addCriterion("CANCEL_VOLUME <>", value, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeGreaterThan(long value) {
            addCriterion("CANCEL_VOLUME >", value, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeGreaterThanOrEqualTo(long value) {
            addCriterion("CANCEL_VOLUME >=", value, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeLessThan(long value) {
            addCriterion("CANCEL_VOLUME <", value, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeLessThanOrEqualTo(long value) {
            addCriterion("CANCEL_VOLUME <=", value, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeIn(List<Long> values) {
            addCriterion("CANCEL_VOLUME in", values, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeNotIn(List<Long> values) {
            addCriterion("CANCEL_VOLUME not in", values, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeBetween(long value1, long value2) {
            addCriterion("CANCEL_VOLUME between", value1, value2, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andCancelVolumeNotBetween(long value1, long value2) {
            addCriterion("CANCEL_VOLUME not between", value1, value2, "cancelVolume");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNull() {
            addCriterion("REPORT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNotNull() {
            addCriterion("REPORT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReportTimeEqualTo(String value) {
            addCriterion("REPORT_TIME =", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotEqualTo(String value) {
            addCriterion("REPORT_TIME <>", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThan(String value) {
            addCriterion("REPORT_TIME >", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_TIME >=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThan(String value) {
            addCriterion("REPORT_TIME <", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThanOrEqualTo(String value) {
            addCriterion("REPORT_TIME <=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLike(String value) {
            addCriterion("REPORT_TIME like", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotLike(String value) {
            addCriterion("REPORT_TIME not like", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeIn(List<String> values) {
            addCriterion("REPORT_TIME in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotIn(List<String> values) {
            addCriterion("REPORT_TIME not in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeBetween(String value1, String value2) {
            addCriterion("REPORT_TIME between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotBetween(String value1, String value2) {
            addCriterion("REPORT_TIME not between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNull() {
            addCriterion("TRADE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("TRADE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(String value) {
            addCriterion("TRADE_TIME =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(String value) {
            addCriterion("TRADE_TIME <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(String value) {
            addCriterion("TRADE_TIME >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_TIME >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(String value) {
            addCriterion("TRADE_TIME <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(String value) {
            addCriterion("TRADE_TIME <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLike(String value) {
            addCriterion("TRADE_TIME like", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotLike(String value) {
            addCriterion("TRADE_TIME not like", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<String> values) {
            addCriterion("TRADE_TIME in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<String> values) {
            addCriterion("TRADE_TIME not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(String value1, String value2) {
            addCriterion("TRADE_TIME between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(String value1, String value2) {
            addCriterion("TRADE_TIME not between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNull() {
            addCriterion("CANCEL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNotNull() {
            addCriterion("CANCEL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeEqualTo(String value) {
            addCriterion("CANCEL_TIME =", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotEqualTo(String value) {
            addCriterion("CANCEL_TIME <>", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThan(String value) {
            addCriterion("CANCEL_TIME >", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_TIME >=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThan(String value) {
            addCriterion("CANCEL_TIME <", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_TIME <=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLike(String value) {
            addCriterion("CANCEL_TIME like", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotLike(String value) {
            addCriterion("CANCEL_TIME not like", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIn(List<String> values) {
            addCriterion("CANCEL_TIME in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotIn(List<String> values) {
            addCriterion("CANCEL_TIME not in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeBetween(String value1, String value2) {
            addCriterion("CANCEL_TIME between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotBetween(String value1, String value2) {
            addCriterion("CANCEL_TIME not between", value1, value2, "cancelTime");
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

        public Criteria andErrorCodeEqualTo(int value) {
            addCriterion("ERROR_CODE =", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotEqualTo(int value) {
            addCriterion("ERROR_CODE <>", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThan(int value) {
            addCriterion("ERROR_CODE >", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThanOrEqualTo(int value) {
            addCriterion("ERROR_CODE >=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThan(int value) {
            addCriterion("ERROR_CODE <", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThanOrEqualTo(int value) {
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

        public Criteria andErrorCodeBetween(int value1, int value2) {
            addCriterion("ERROR_CODE between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotBetween(int value1, int value2) {
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

        public Criteria andLocalEntrustIdIsNull() {
            addCriterion("LOCAL_ENTRUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdIsNotNull() {
            addCriterion("LOCAL_ENTRUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdEqualTo(String value) {
            addCriterion("LOCAL_ENTRUST_ID =", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdNotEqualTo(String value) {
            addCriterion("LOCAL_ENTRUST_ID <>", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdGreaterThan(String value) {
            addCriterion("LOCAL_ENTRUST_ID >", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_ENTRUST_ID >=", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdLessThan(String value) {
            addCriterion("LOCAL_ENTRUST_ID <", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_ENTRUST_ID <=", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdLike(String value) {
            addCriterion("LOCAL_ENTRUST_ID like", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdNotLike(String value) {
            addCriterion("LOCAL_ENTRUST_ID not like", value, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdIn(List<String> values) {
            addCriterion("LOCAL_ENTRUST_ID in", values, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdNotIn(List<String> values) {
            addCriterion("LOCAL_ENTRUST_ID not in", values, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdBetween(String value1, String value2) {
            addCriterion("LOCAL_ENTRUST_ID between", value1, value2, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andLocalEntrustIdNotBetween(String value1, String value2) {
            addCriterion("LOCAL_ENTRUST_ID not between", value1, value2, "localEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdIsNull() {
            addCriterion("BROKER_ENTRUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdIsNotNull() {
            addCriterion("BROKER_ENTRUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdEqualTo(String value) {
            addCriterion("BROKER_ENTRUST_ID =", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdNotEqualTo(String value) {
            addCriterion("BROKER_ENTRUST_ID <>", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdGreaterThan(String value) {
            addCriterion("BROKER_ENTRUST_ID >", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdGreaterThanOrEqualTo(String value) {
            addCriterion("BROKER_ENTRUST_ID >=", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdLessThan(String value) {
            addCriterion("BROKER_ENTRUST_ID <", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdLessThanOrEqualTo(String value) {
            addCriterion("BROKER_ENTRUST_ID <=", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdLike(String value) {
            addCriterion("BROKER_ENTRUST_ID like", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdNotLike(String value) {
            addCriterion("BROKER_ENTRUST_ID not like", value, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdIn(List<String> values) {
            addCriterion("BROKER_ENTRUST_ID in", values, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdNotIn(List<String> values) {
            addCriterion("BROKER_ENTRUST_ID not in", values, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdBetween(String value1, String value2) {
            addCriterion("BROKER_ENTRUST_ID between", value1, value2, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andBrokerEntrustIdNotBetween(String value1, String value2) {
            addCriterion("BROKER_ENTRUST_ID not between", value1, value2, "brokerEntrustId");
            return (Criteria) this;
        }

        public Criteria andIpMacIsNull() {
            addCriterion("IP_MAC is null");
            return (Criteria) this;
        }

        public Criteria andIpMacIsNotNull() {
            addCriterion("IP_MAC is not null");
            return (Criteria) this;
        }

        public Criteria andIpMacEqualTo(String value) {
            addCriterion("IP_MAC =", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacNotEqualTo(String value) {
            addCriterion("IP_MAC <>", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacGreaterThan(String value) {
            addCriterion("IP_MAC >", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacGreaterThanOrEqualTo(String value) {
            addCriterion("IP_MAC >=", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacLessThan(String value) {
            addCriterion("IP_MAC <", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacLessThanOrEqualTo(String value) {
            addCriterion("IP_MAC <=", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacLike(String value) {
            addCriterion("IP_MAC like", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacNotLike(String value) {
            addCriterion("IP_MAC not like", value, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacIn(List<String> values) {
            addCriterion("IP_MAC in", values, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacNotIn(List<String> values) {
            addCriterion("IP_MAC not in", values, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacBetween(String value1, String value2) {
            addCriterion("IP_MAC between", value1, value2, "ipMac");
            return (Criteria) this;
        }

        public Criteria andIpMacNotBetween(String value1, String value2) {
            addCriterion("IP_MAC not between", value1, value2, "ipMac");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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

        public Criteria andEntrustStatusIsNull() {
            addCriterion("ENTRUST_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusIsNotNull() {
            addCriterion("ENTRUST_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusEqualTo(int value) {
            addCriterion("ENTRUST_STATUS =", value, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusNotEqualTo(int value) {
            addCriterion("ENTRUST_STATUS <>", value, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusGreaterThan(int value) {
            addCriterion("ENTRUST_STATUS >", value, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusGreaterThanOrEqualTo(int value) {
            addCriterion("ENTRUST_STATUS >=", value, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusLessThan(int value) {
            addCriterion("ENTRUST_STATUS <", value, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusLessThanOrEqualTo(int value) {
            addCriterion("ENTRUST_STATUS <=", value, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusIn(List<Integer> values) {
            addCriterion("ENTRUST_STATUS in", values, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusNotIn(List<Integer> values) {
            addCriterion("ENTRUST_STATUS not in", values, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusBetween(int value1, int value2) {
            addCriterion("ENTRUST_STATUS between", value1, value2, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andEntrustStatusNotBetween(int value1, int value2) {
            addCriterion("ENTRUST_STATUS not between", value1, value2, "entrustStatus");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdIsNull() {
            addCriterion("ORIG_ENTRUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdIsNotNull() {
            addCriterion("ORIG_ENTRUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdEqualTo(String value) {
            addCriterion("ORIG_ENTRUST_ID =", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdNotEqualTo(String value) {
            addCriterion("ORIG_ENTRUST_ID <>", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdGreaterThan(String value) {
            addCriterion("ORIG_ENTRUST_ID >", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORIG_ENTRUST_ID >=", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdLessThan(String value) {
            addCriterion("ORIG_ENTRUST_ID <", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdLessThanOrEqualTo(String value) {
            addCriterion("ORIG_ENTRUST_ID <=", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdLike(String value) {
            addCriterion("ORIG_ENTRUST_ID like", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdNotLike(String value) {
            addCriterion("ORIG_ENTRUST_ID not like", value, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdIn(List<String> values) {
            addCriterion("ORIG_ENTRUST_ID in", values, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdNotIn(List<String> values) {
            addCriterion("ORIG_ENTRUST_ID not in", values, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdBetween(String value1, String value2) {
            addCriterion("ORIG_ENTRUST_ID between", value1, value2, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andOrigEntrustIdNotBetween(String value1, String value2) {
            addCriterion("ORIG_ENTRUST_ID not between", value1, value2, "origEntrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdIsNull() {
            addCriterion("ENTRUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andEntrustIdIsNotNull() {
            addCriterion("ENTRUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEntrustIdEqualTo(String value) {
            addCriterion("ENTRUST_ID =", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotEqualTo(String value) {
            addCriterion("ENTRUST_ID <>", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdGreaterThan(String value) {
            addCriterion("ENTRUST_ID >", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdGreaterThanOrEqualTo(String value) {
            addCriterion("ENTRUST_ID >=", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdLessThan(String value) {
            addCriterion("ENTRUST_ID <", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdLessThanOrEqualTo(String value) {
            addCriterion("ENTRUST_ID <=", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdLike(String value) {
            addCriterion("ENTRUST_ID like", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotLike(String value) {
            addCriterion("ENTRUST_ID not like", value, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdIn(List<String> values) {
            addCriterion("ENTRUST_ID in", values, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotIn(List<String> values) {
            addCriterion("ENTRUST_ID not in", values, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdBetween(String value1, String value2) {
            addCriterion("ENTRUST_ID between", value1, value2, "entrustId");
            return (Criteria) this;
        }

        public Criteria andEntrustIdNotBetween(String value1, String value2) {
            addCriterion("ENTRUST_ID not between", value1, value2, "entrustId");
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