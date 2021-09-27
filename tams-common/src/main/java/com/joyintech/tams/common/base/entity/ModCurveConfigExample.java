package com.joyintech.tams.common.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ModCurveConfigExample {
    /**
     * CVR30INIT_XUQIU.MOD_CURVE_CONFIG
     */
    protected String orderByClause;

    /**
     * CVR30INIT_XUQIU.MOD_CURVE_CONFIG
     */
    protected boolean distinct;

    /**
     * CVR30INIT_XUQIU.MOD_CURVE_CONFIG
     */
    protected List<Criteria> oredCriteria;

    public ModCurveConfigExample() {
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

        public Criteria andSeqnoIsNull() {
            addCriterion("SEQNO is null");
            return (Criteria) this;
        }

        public Criteria andSeqnoIsNotNull() {
            addCriterion("SEQNO is not null");
            return (Criteria) this;
        }

        public Criteria andSeqnoEqualTo(String value) {
            addCriterion("SEQNO =", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotEqualTo(String value) {
            addCriterion("SEQNO <>", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThan(String value) {
            addCriterion("SEQNO >", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoGreaterThanOrEqualTo(String value) {
            addCriterion("SEQNO >=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThan(String value) {
            addCriterion("SEQNO <", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLessThanOrEqualTo(String value) {
            addCriterion("SEQNO <=", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoLike(String value) {
            addCriterion("SEQNO like", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotLike(String value) {
            addCriterion("SEQNO not like", value, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoIn(List<String> values) {
            addCriterion("SEQNO in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotIn(List<String> values) {
            addCriterion("SEQNO not in", values, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoBetween(String value1, String value2) {
            addCriterion("SEQNO between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andSeqnoNotBetween(String value1, String value2) {
            addCriterion("SEQNO not between", value1, value2, "seqno");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelIsNull() {
            addCriterion("SEC_TYPE_MODEL is null");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelIsNotNull() {
            addCriterion("SEC_TYPE_MODEL is not null");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelEqualTo(String value) {
            addCriterion("SEC_TYPE_MODEL =", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelNotEqualTo(String value) {
            addCriterion("SEC_TYPE_MODEL <>", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelGreaterThan(String value) {
            addCriterion("SEC_TYPE_MODEL >", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelGreaterThanOrEqualTo(String value) {
            addCriterion("SEC_TYPE_MODEL >=", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelLessThan(String value) {
            addCriterion("SEC_TYPE_MODEL <", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelLessThanOrEqualTo(String value) {
            addCriterion("SEC_TYPE_MODEL <=", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelLike(String value) {
            addCriterion("SEC_TYPE_MODEL like", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelNotLike(String value) {
            addCriterion("SEC_TYPE_MODEL not like", value, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelIn(List<String> values) {
            addCriterion("SEC_TYPE_MODEL in", values, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelNotIn(List<String> values) {
            addCriterion("SEC_TYPE_MODEL not in", values, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelBetween(String value1, String value2) {
            addCriterion("SEC_TYPE_MODEL between", value1, value2, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecTypeModelNotBetween(String value1, String value2) {
            addCriterion("SEC_TYPE_MODEL not between", value1, value2, "secTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelIsNull() {
            addCriterion("SEC_DETAIL_TYPE_MODEL is null");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelIsNotNull() {
            addCriterion("SEC_DETAIL_TYPE_MODEL is not null");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelEqualTo(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL =", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelNotEqualTo(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL <>", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelGreaterThan(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL >", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelGreaterThanOrEqualTo(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL >=", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelLessThan(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL <", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelLessThanOrEqualTo(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL <=", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelLike(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL like", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelNotLike(String value) {
            addCriterion("SEC_DETAIL_TYPE_MODEL not like", value, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelIn(List<String> values) {
            addCriterion("SEC_DETAIL_TYPE_MODEL in", values, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelNotIn(List<String> values) {
            addCriterion("SEC_DETAIL_TYPE_MODEL not in", values, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelBetween(String value1, String value2) {
            addCriterion("SEC_DETAIL_TYPE_MODEL between", value1, value2, "secDetailTypeModel");
            return (Criteria) this;
        }

        public Criteria andSecDetailTypeModelNotBetween(String value1, String value2) {
            addCriterion("SEC_DETAIL_TYPE_MODEL not between", value1, value2, "secDetailTypeModel");
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

        public Criteria andFinprodNameIsNull() {
            addCriterion("FINPROD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFinprodNameIsNotNull() {
            addCriterion("FINPROD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFinprodNameEqualTo(String value) {
            addCriterion("FINPROD_NAME =", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameNotEqualTo(String value) {
            addCriterion("FINPROD_NAME <>", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameGreaterThan(String value) {
            addCriterion("FINPROD_NAME >", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameGreaterThanOrEqualTo(String value) {
            addCriterion("FINPROD_NAME >=", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameLessThan(String value) {
            addCriterion("FINPROD_NAME <", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameLessThanOrEqualTo(String value) {
            addCriterion("FINPROD_NAME <=", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameLike(String value) {
            addCriterion("FINPROD_NAME like", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameNotLike(String value) {
            addCriterion("FINPROD_NAME not like", value, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameIn(List<String> values) {
            addCriterion("FINPROD_NAME in", values, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameNotIn(List<String> values) {
            addCriterion("FINPROD_NAME not in", values, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameBetween(String value1, String value2) {
            addCriterion("FINPROD_NAME between", value1, value2, "finprodName");
            return (Criteria) this;
        }

        public Criteria andFinprodNameNotBetween(String value1, String value2) {
            addCriterion("FINPROD_NAME not between", value1, value2, "finprodName");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeIsNull() {
            addCriterion("CURVE_GROUP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeIsNotNull() {
            addCriterion("CURVE_GROUP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeEqualTo(String value) {
            addCriterion("CURVE_GROUP_CODE =", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeNotEqualTo(String value) {
            addCriterion("CURVE_GROUP_CODE <>", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeGreaterThan(String value) {
            addCriterion("CURVE_GROUP_CODE >", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CURVE_GROUP_CODE >=", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeLessThan(String value) {
            addCriterion("CURVE_GROUP_CODE <", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("CURVE_GROUP_CODE <=", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeLike(String value) {
            addCriterion("CURVE_GROUP_CODE like", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeNotLike(String value) {
            addCriterion("CURVE_GROUP_CODE not like", value, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeIn(List<String> values) {
            addCriterion("CURVE_GROUP_CODE in", values, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeNotIn(List<String> values) {
            addCriterion("CURVE_GROUP_CODE not in", values, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeBetween(String value1, String value2) {
            addCriterion("CURVE_GROUP_CODE between", value1, value2, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveGroupCodeNotBetween(String value1, String value2) {
            addCriterion("CURVE_GROUP_CODE not between", value1, value2, "curveGroupCode");
            return (Criteria) this;
        }

        public Criteria andCurveTypeIsNull() {
            addCriterion("CURVE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCurveTypeIsNotNull() {
            addCriterion("CURVE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCurveTypeEqualTo(String value) {
            addCriterion("CURVE_TYPE =", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeNotEqualTo(String value) {
            addCriterion("CURVE_TYPE <>", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeGreaterThan(String value) {
            addCriterion("CURVE_TYPE >", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CURVE_TYPE >=", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeLessThan(String value) {
            addCriterion("CURVE_TYPE <", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeLessThanOrEqualTo(String value) {
            addCriterion("CURVE_TYPE <=", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeLike(String value) {
            addCriterion("CURVE_TYPE like", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeNotLike(String value) {
            addCriterion("CURVE_TYPE not like", value, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeIn(List<String> values) {
            addCriterion("CURVE_TYPE in", values, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeNotIn(List<String> values) {
            addCriterion("CURVE_TYPE not in", values, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeBetween(String value1, String value2) {
            addCriterion("CURVE_TYPE between", value1, value2, "curveType");
            return (Criteria) this;
        }

        public Criteria andCurveTypeNotBetween(String value1, String value2) {
            addCriterion("CURVE_TYPE not between", value1, value2, "curveType");
            return (Criteria) this;
        }

        public Criteria andIssmoothIsNull() {
            addCriterion("ISSMOOTH is null");
            return (Criteria) this;
        }

        public Criteria andIssmoothIsNotNull() {
            addCriterion("ISSMOOTH is not null");
            return (Criteria) this;
        }

        public Criteria andIssmoothEqualTo(String value) {
            addCriterion("ISSMOOTH =", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothNotEqualTo(String value) {
            addCriterion("ISSMOOTH <>", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothGreaterThan(String value) {
            addCriterion("ISSMOOTH >", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothGreaterThanOrEqualTo(String value) {
            addCriterion("ISSMOOTH >=", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothLessThan(String value) {
            addCriterion("ISSMOOTH <", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothLessThanOrEqualTo(String value) {
            addCriterion("ISSMOOTH <=", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothLike(String value) {
            addCriterion("ISSMOOTH like", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothNotLike(String value) {
            addCriterion("ISSMOOTH not like", value, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothIn(List<String> values) {
            addCriterion("ISSMOOTH in", values, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothNotIn(List<String> values) {
            addCriterion("ISSMOOTH not in", values, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothBetween(String value1, String value2) {
            addCriterion("ISSMOOTH between", value1, value2, "issmooth");
            return (Criteria) this;
        }

        public Criteria andIssmoothNotBetween(String value1, String value2) {
            addCriterion("ISSMOOTH not between", value1, value2, "issmooth");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionIsNull() {
            addCriterion("SMOOTHSECTION is null");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionIsNotNull() {
            addCriterion("SMOOTHSECTION is not null");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionEqualTo(String value) {
            addCriterion("SMOOTHSECTION =", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionNotEqualTo(String value) {
            addCriterion("SMOOTHSECTION <>", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionGreaterThan(String value) {
            addCriterion("SMOOTHSECTION >", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionGreaterThanOrEqualTo(String value) {
            addCriterion("SMOOTHSECTION >=", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionLessThan(String value) {
            addCriterion("SMOOTHSECTION <", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionLessThanOrEqualTo(String value) {
            addCriterion("SMOOTHSECTION <=", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionLike(String value) {
            addCriterion("SMOOTHSECTION like", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionNotLike(String value) {
            addCriterion("SMOOTHSECTION not like", value, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionIn(List<String> values) {
            addCriterion("SMOOTHSECTION in", values, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionNotIn(List<String> values) {
            addCriterion("SMOOTHSECTION not in", values, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionBetween(String value1, String value2) {
            addCriterion("SMOOTHSECTION between", value1, value2, "smoothsection");
            return (Criteria) this;
        }

        public Criteria andSmoothsectionNotBetween(String value1, String value2) {
            addCriterion("SMOOTHSECTION not between", value1, value2, "smoothsection");
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

        public Criteria andIsBottomGradeIsNull() {
            addCriterion("IS_BOTTOM_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeIsNotNull() {
            addCriterion("IS_BOTTOM_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeEqualTo(String value) {
            addCriterion("IS_BOTTOM_GRADE =", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeNotEqualTo(String value) {
            addCriterion("IS_BOTTOM_GRADE <>", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeGreaterThan(String value) {
            addCriterion("IS_BOTTOM_GRADE >", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BOTTOM_GRADE >=", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeLessThan(String value) {
            addCriterion("IS_BOTTOM_GRADE <", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeLessThanOrEqualTo(String value) {
            addCriterion("IS_BOTTOM_GRADE <=", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeLike(String value) {
            addCriterion("IS_BOTTOM_GRADE like", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeNotLike(String value) {
            addCriterion("IS_BOTTOM_GRADE not like", value, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeIn(List<String> values) {
            addCriterion("IS_BOTTOM_GRADE in", values, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeNotIn(List<String> values) {
            addCriterion("IS_BOTTOM_GRADE not in", values, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeBetween(String value1, String value2) {
            addCriterion("IS_BOTTOM_GRADE between", value1, value2, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andIsBottomGradeNotBetween(String value1, String value2) {
            addCriterion("IS_BOTTOM_GRADE not between", value1, value2, "isBottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeIsNull() {
            addCriterion("BOTTOM_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andBottomGradeIsNotNull() {
            addCriterion("BOTTOM_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andBottomGradeEqualTo(String value) {
            addCriterion("BOTTOM_GRADE =", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeNotEqualTo(String value) {
            addCriterion("BOTTOM_GRADE <>", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeGreaterThan(String value) {
            addCriterion("BOTTOM_GRADE >", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeGreaterThanOrEqualTo(String value) {
            addCriterion("BOTTOM_GRADE >=", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeLessThan(String value) {
            addCriterion("BOTTOM_GRADE <", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeLessThanOrEqualTo(String value) {
            addCriterion("BOTTOM_GRADE <=", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeLike(String value) {
            addCriterion("BOTTOM_GRADE like", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeNotLike(String value) {
            addCriterion("BOTTOM_GRADE not like", value, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeIn(List<String> values) {
            addCriterion("BOTTOM_GRADE in", values, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeNotIn(List<String> values) {
            addCriterion("BOTTOM_GRADE not in", values, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeBetween(String value1, String value2) {
            addCriterion("BOTTOM_GRADE between", value1, value2, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andBottomGradeNotBetween(String value1, String value2) {
            addCriterion("BOTTOM_GRADE not between", value1, value2, "bottomGrade");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNull() {
            addCriterion("EFF_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNotNull() {
            addCriterion("EFF_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEffDateEqualTo(Date value) {
            addCriterionForJDBCDate("EFF_DATE =", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("EFF_DATE <>", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThan(Date value) {
            addCriterionForJDBCDate("EFF_DATE >", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFF_DATE >=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThan(Date value) {
            addCriterionForJDBCDate("EFF_DATE <", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EFF_DATE <=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateIn(List<Date> values) {
            addCriterionForJDBCDate("EFF_DATE in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("EFF_DATE not in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFF_DATE between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EFF_DATE not between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNull() {
            addCriterion("INVALID_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIsNotNull() {
            addCriterion("INVALID_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidDateEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE =", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE <>", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThan(Date value) {
            addCriterionForJDBCDate("INVALID_DATE >", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE >=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThan(Date value) {
            addCriterionForJDBCDate("INVALID_DATE <", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("INVALID_DATE <=", value, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateIn(List<Date> values) {
            addCriterionForJDBCDate("INVALID_DATE in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("INVALID_DATE not in", values, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INVALID_DATE between", value1, value2, "invalidDate");
            return (Criteria) this;
        }

        public Criteria andInvalidDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("INVALID_DATE not between", value1, value2, "invalidDate");
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