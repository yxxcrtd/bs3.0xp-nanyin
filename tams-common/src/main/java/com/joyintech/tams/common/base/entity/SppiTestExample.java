package com.joyintech.tams.common.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SppiTestExample {
    /**
     * LOSSTAMS01.SPPI_TEST
     */
    protected String orderByClause;

    /**
     * LOSSTAMS01.SPPI_TEST
     */
    protected boolean distinct;

    /**
     * LOSSTAMS01.SPPI_TEST
     */
    protected List<Criteria> oredCriteria;

    public SppiTestExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andSppiTestIdIsNull() {
            addCriterion("SPPI_TEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdIsNotNull() {
            addCriterion("SPPI_TEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdEqualTo(String value) {
            addCriterion("SPPI_TEST_ID =", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdNotEqualTo(String value) {
            addCriterion("SPPI_TEST_ID <>", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdGreaterThan(String value) {
            addCriterion("SPPI_TEST_ID >", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPPI_TEST_ID >=", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdLessThan(String value) {
            addCriterion("SPPI_TEST_ID <", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdLessThanOrEqualTo(String value) {
            addCriterion("SPPI_TEST_ID <=", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdLike(String value) {
            addCriterion("SPPI_TEST_ID like", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdNotLike(String value) {
            addCriterion("SPPI_TEST_ID not like", value, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdIn(List<String> values) {
            addCriterion("SPPI_TEST_ID in", values, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdNotIn(List<String> values) {
            addCriterion("SPPI_TEST_ID not in", values, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdBetween(String value1, String value2) {
            addCriterion("SPPI_TEST_ID between", value1, value2, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andSppiTestIdNotBetween(String value1, String value2) {
            addCriterion("SPPI_TEST_ID not between", value1, value2, "sppiTestId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdIsNull() {
            addCriterion("FINPROD_ID is null");
            return (Criteria) this;
        }

        public Criteria andFinprodIdIsNotNull() {
            addCriterion("FINPROD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFinprodIdEqualTo(String value) {
            addCriterion("FINPROD_ID =", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdNotEqualTo(String value) {
            addCriterion("FINPROD_ID <>", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdGreaterThan(String value) {
            addCriterion("FINPROD_ID >", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdGreaterThanOrEqualTo(String value) {
            addCriterion("FINPROD_ID >=", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdLessThan(String value) {
            addCriterion("FINPROD_ID <", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdLessThanOrEqualTo(String value) {
            addCriterion("FINPROD_ID <=", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdLike(String value) {
            addCriterion("FINPROD_ID like", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdNotLike(String value) {
            addCriterion("FINPROD_ID not like", value, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdIn(List<String> values) {
            addCriterion("FINPROD_ID in", values, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdNotIn(List<String> values) {
            addCriterion("FINPROD_ID not in", values, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdBetween(String value1, String value2) {
            addCriterion("FINPROD_ID between", value1, value2, "finprodId");
            return (Criteria) this;
        }

        public Criteria andFinprodIdNotBetween(String value1, String value2) {
            addCriterion("FINPROD_ID not between", value1, value2, "finprodId");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeIsNull() {
            addCriterion("SPPI_TMP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeIsNotNull() {
            addCriterion("SPPI_TMP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeEqualTo(String value) {
            addCriterion("SPPI_TMP_TYPE =", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeNotEqualTo(String value) {
            addCriterion("SPPI_TMP_TYPE <>", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeGreaterThan(String value) {
            addCriterion("SPPI_TMP_TYPE >", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SPPI_TMP_TYPE >=", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeLessThan(String value) {
            addCriterion("SPPI_TMP_TYPE <", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeLessThanOrEqualTo(String value) {
            addCriterion("SPPI_TMP_TYPE <=", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeLike(String value) {
            addCriterion("SPPI_TMP_TYPE like", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeNotLike(String value) {
            addCriterion("SPPI_TMP_TYPE not like", value, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeIn(List<String> values) {
            addCriterion("SPPI_TMP_TYPE in", values, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeNotIn(List<String> values) {
            addCriterion("SPPI_TMP_TYPE not in", values, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeBetween(String value1, String value2) {
            addCriterion("SPPI_TMP_TYPE between", value1, value2, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpTypeNotBetween(String value1, String value2) {
            addCriterion("SPPI_TMP_TYPE not between", value1, value2, "sppiTmpType");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoIsNull() {
            addCriterion("SPPI_TMP_NO is null");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoIsNotNull() {
            addCriterion("SPPI_TMP_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoEqualTo(String value) {
            addCriterion("SPPI_TMP_NO =", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoNotEqualTo(String value) {
            addCriterion("SPPI_TMP_NO <>", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoGreaterThan(String value) {
            addCriterion("SPPI_TMP_NO >", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoGreaterThanOrEqualTo(String value) {
            addCriterion("SPPI_TMP_NO >=", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoLessThan(String value) {
            addCriterion("SPPI_TMP_NO <", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoLessThanOrEqualTo(String value) {
            addCriterion("SPPI_TMP_NO <=", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoLike(String value) {
            addCriterion("SPPI_TMP_NO like", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoNotLike(String value) {
            addCriterion("SPPI_TMP_NO not like", value, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoIn(List<String> values) {
            addCriterion("SPPI_TMP_NO in", values, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoNotIn(List<String> values) {
            addCriterion("SPPI_TMP_NO not in", values, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoBetween(String value1, String value2) {
            addCriterion("SPPI_TMP_NO between", value1, value2, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andSppiTmpNoNotBetween(String value1, String value2) {
            addCriterion("SPPI_TMP_NO not between", value1, value2, "sppiTmpNo");
            return (Criteria) this;
        }

        public Criteria andTestDateIsNull() {
            addCriterion("TEST_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTestDateIsNotNull() {
            addCriterion("TEST_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTestDateEqualTo(Date value) {
            addCriterionForJDBCDate("TEST_DATE =", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("TEST_DATE <>", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateGreaterThan(Date value) {
            addCriterionForJDBCDate("TEST_DATE >", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TEST_DATE >=", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateLessThan(Date value) {
            addCriterionForJDBCDate("TEST_DATE <", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TEST_DATE <=", value, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateIn(List<Date> values) {
            addCriterionForJDBCDate("TEST_DATE in", values, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("TEST_DATE not in", values, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TEST_DATE between", value1, value2, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TEST_DATE not between", value1, value2, "testDate");
            return (Criteria) this;
        }

        public Criteria andTestStatusIsNull() {
            addCriterion("TEST_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andTestStatusIsNotNull() {
            addCriterion("TEST_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andTestStatusEqualTo(String value) {
            addCriterion("TEST_STATUS =", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusNotEqualTo(String value) {
            addCriterion("TEST_STATUS <>", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusGreaterThan(String value) {
            addCriterion("TEST_STATUS >", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_STATUS >=", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusLessThan(String value) {
            addCriterion("TEST_STATUS <", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusLessThanOrEqualTo(String value) {
            addCriterion("TEST_STATUS <=", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusLike(String value) {
            addCriterion("TEST_STATUS like", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusNotLike(String value) {
            addCriterion("TEST_STATUS not like", value, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusIn(List<String> values) {
            addCriterion("TEST_STATUS in", values, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusNotIn(List<String> values) {
            addCriterion("TEST_STATUS not in", values, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusBetween(String value1, String value2) {
            addCriterion("TEST_STATUS between", value1, value2, "testStatus");
            return (Criteria) this;
        }

        public Criteria andTestStatusNotBetween(String value1, String value2) {
            addCriterion("TEST_STATUS not between", value1, value2, "testStatus");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescIsNull() {
            addCriterion("DEBT_TOOL_DESC is null");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescIsNotNull() {
            addCriterion("DEBT_TOOL_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescEqualTo(String value) {
            addCriterion("DEBT_TOOL_DESC =", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescNotEqualTo(String value) {
            addCriterion("DEBT_TOOL_DESC <>", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescGreaterThan(String value) {
            addCriterion("DEBT_TOOL_DESC >", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescGreaterThanOrEqualTo(String value) {
            addCriterion("DEBT_TOOL_DESC >=", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescLessThan(String value) {
            addCriterion("DEBT_TOOL_DESC <", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescLessThanOrEqualTo(String value) {
            addCriterion("DEBT_TOOL_DESC <=", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescLike(String value) {
            addCriterion("DEBT_TOOL_DESC like", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescNotLike(String value) {
            addCriterion("DEBT_TOOL_DESC not like", value, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescIn(List<String> values) {
            addCriterion("DEBT_TOOL_DESC in", values, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescNotIn(List<String> values) {
            addCriterion("DEBT_TOOL_DESC not in", values, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescBetween(String value1, String value2) {
            addCriterion("DEBT_TOOL_DESC between", value1, value2, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtToolDescNotBetween(String value1, String value2) {
            addCriterion("DEBT_TOOL_DESC not between", value1, value2, "debtToolDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescIsNull() {
            addCriterion("DEBT_LAYER_DESC is null");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescIsNotNull() {
            addCriterion("DEBT_LAYER_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescEqualTo(String value) {
            addCriterion("DEBT_LAYER_DESC =", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescNotEqualTo(String value) {
            addCriterion("DEBT_LAYER_DESC <>", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescGreaterThan(String value) {
            addCriterion("DEBT_LAYER_DESC >", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescGreaterThanOrEqualTo(String value) {
            addCriterion("DEBT_LAYER_DESC >=", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescLessThan(String value) {
            addCriterion("DEBT_LAYER_DESC <", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescLessThanOrEqualTo(String value) {
            addCriterion("DEBT_LAYER_DESC <=", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescLike(String value) {
            addCriterion("DEBT_LAYER_DESC like", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescNotLike(String value) {
            addCriterion("DEBT_LAYER_DESC not like", value, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescIn(List<String> values) {
            addCriterion("DEBT_LAYER_DESC in", values, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescNotIn(List<String> values) {
            addCriterion("DEBT_LAYER_DESC not in", values, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescBetween(String value1, String value2) {
            addCriterion("DEBT_LAYER_DESC between", value1, value2, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andDebtLayerDescNotBetween(String value1, String value2) {
            addCriterion("DEBT_LAYER_DESC not between", value1, value2, "debtLayerDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescIsNull() {
            addCriterion("INVEST_TYPE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescIsNotNull() {
            addCriterion("INVEST_TYPE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescEqualTo(String value) {
            addCriterion("INVEST_TYPE_DESC =", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescNotEqualTo(String value) {
            addCriterion("INVEST_TYPE_DESC <>", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescGreaterThan(String value) {
            addCriterion("INVEST_TYPE_DESC >", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("INVEST_TYPE_DESC >=", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescLessThan(String value) {
            addCriterion("INVEST_TYPE_DESC <", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescLessThanOrEqualTo(String value) {
            addCriterion("INVEST_TYPE_DESC <=", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescLike(String value) {
            addCriterion("INVEST_TYPE_DESC like", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescNotLike(String value) {
            addCriterion("INVEST_TYPE_DESC not like", value, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescIn(List<String> values) {
            addCriterion("INVEST_TYPE_DESC in", values, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescNotIn(List<String> values) {
            addCriterion("INVEST_TYPE_DESC not in", values, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescBetween(String value1, String value2) {
            addCriterion("INVEST_TYPE_DESC between", value1, value2, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andInvestTypeDescNotBetween(String value1, String value2) {
            addCriterion("INVEST_TYPE_DESC not between", value1, value2, "investTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescIsNull() {
            addCriterion("MANAGE_TYPE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescIsNotNull() {
            addCriterion("MANAGE_TYPE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescEqualTo(String value) {
            addCriterion("MANAGE_TYPE_DESC =", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescNotEqualTo(String value) {
            addCriterion("MANAGE_TYPE_DESC <>", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescGreaterThan(String value) {
            addCriterion("MANAGE_TYPE_DESC >", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGE_TYPE_DESC >=", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescLessThan(String value) {
            addCriterion("MANAGE_TYPE_DESC <", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescLessThanOrEqualTo(String value) {
            addCriterion("MANAGE_TYPE_DESC <=", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescLike(String value) {
            addCriterion("MANAGE_TYPE_DESC like", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescNotLike(String value) {
            addCriterion("MANAGE_TYPE_DESC not like", value, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescIn(List<String> values) {
            addCriterion("MANAGE_TYPE_DESC in", values, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescNotIn(List<String> values) {
            addCriterion("MANAGE_TYPE_DESC not in", values, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescBetween(String value1, String value2) {
            addCriterion("MANAGE_TYPE_DESC between", value1, value2, "manageTypeDesc");
            return (Criteria) this;
        }

        public Criteria andManageTypeDescNotBetween(String value1, String value2) {
            addCriterion("MANAGE_TYPE_DESC not between", value1, value2, "manageTypeDesc");
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

        public Criteria andCreateUserIsNull() {
            addCriterion("CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("CREATE_USER =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("CREATE_USER <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("CREATE_USER >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("CREATE_USER <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("CREATE_USER like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("CREATE_USER not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("CREATE_USER in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("CREATE_USER not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("CREATE_USER between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateDeptIsNull() {
            addCriterion("CREATE_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andCreateDeptIsNotNull() {
            addCriterion("CREATE_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDeptEqualTo(String value) {
            addCriterion("CREATE_DEPT =", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotEqualTo(String value) {
            addCriterion("CREATE_DEPT <>", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptGreaterThan(String value) {
            addCriterion("CREATE_DEPT >", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DEPT >=", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptLessThan(String value) {
            addCriterion("CREATE_DEPT <", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DEPT <=", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptLike(String value) {
            addCriterion("CREATE_DEPT like", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotLike(String value) {
            addCriterion("CREATE_DEPT not like", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptIn(List<String> values) {
            addCriterion("CREATE_DEPT in", values, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotIn(List<String> values) {
            addCriterion("CREATE_DEPT not in", values, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptBetween(String value1, String value2) {
            addCriterion("CREATE_DEPT between", value1, value2, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotBetween(String value1, String value2) {
            addCriterion("CREATE_DEPT not between", value1, value2, "createDept");
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

        public Criteria andUpdateUserIsNull() {
            addCriterion("UPDATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("UPDATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("UPDATE_USER =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("UPDATE_USER <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("UPDATE_USER >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_USER >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("UPDATE_USER <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_USER <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("UPDATE_USER like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("UPDATE_USER not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("UPDATE_USER in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("UPDATE_USER not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("UPDATE_USER between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("UPDATE_USER not between", value1, value2, "updateUser");
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