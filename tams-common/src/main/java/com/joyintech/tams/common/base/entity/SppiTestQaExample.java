package com.joyintech.tams.common.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SppiTestQaExample {
    /**
     * LOSSTAMS01.SPPI_TEST_QA
     */
    protected String orderByClause;

    /**
     * LOSSTAMS01.SPPI_TEST_QA
     */
    protected boolean distinct;

    /**
     * LOSSTAMS01.SPPI_TEST_QA
     */
    protected List<Criteria> oredCriteria;

    public SppiTestQaExample() {
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

        public Criteria andQuestionNoIsNull() {
            addCriterion("QUESTION_NO is null");
            return (Criteria) this;
        }

        public Criteria andQuestionNoIsNotNull() {
            addCriterion("QUESTION_NO is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionNoEqualTo(String value) {
            addCriterion("QUESTION_NO =", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoNotEqualTo(String value) {
            addCriterion("QUESTION_NO <>", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoGreaterThan(String value) {
            addCriterion("QUESTION_NO >", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_NO >=", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoLessThan(String value) {
            addCriterion("QUESTION_NO <", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_NO <=", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoLike(String value) {
            addCriterion("QUESTION_NO like", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoNotLike(String value) {
            addCriterion("QUESTION_NO not like", value, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoIn(List<String> values) {
            addCriterion("QUESTION_NO in", values, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoNotIn(List<String> values) {
            addCriterion("QUESTION_NO not in", values, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoBetween(String value1, String value2) {
            addCriterion("QUESTION_NO between", value1, value2, "questionNo");
            return (Criteria) this;
        }

        public Criteria andQuestionNoNotBetween(String value1, String value2) {
            addCriterion("QUESTION_NO not between", value1, value2, "questionNo");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeIsNull() {
            addCriterion("ANSWER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeIsNotNull() {
            addCriterion("ANSWER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeEqualTo(String value) {
            addCriterion("ANSWER_TYPE =", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotEqualTo(String value) {
            addCriterion("ANSWER_TYPE <>", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeGreaterThan(String value) {
            addCriterion("ANSWER_TYPE >", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_TYPE >=", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeLessThan(String value) {
            addCriterion("ANSWER_TYPE <", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_TYPE <=", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeLike(String value) {
            addCriterion("ANSWER_TYPE like", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotLike(String value) {
            addCriterion("ANSWER_TYPE not like", value, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeIn(List<String> values) {
            addCriterion("ANSWER_TYPE in", values, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotIn(List<String> values) {
            addCriterion("ANSWER_TYPE not in", values, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeBetween(String value1, String value2) {
            addCriterion("ANSWER_TYPE between", value1, value2, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerTypeNotBetween(String value1, String value2) {
            addCriterion("ANSWER_TYPE not between", value1, value2, "answerType");
            return (Criteria) this;
        }

        public Criteria andAnswerValueIsNull() {
            addCriterion("ANSWER_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andAnswerValueIsNotNull() {
            addCriterion("ANSWER_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerValueEqualTo(String value) {
            addCriterion("ANSWER_VALUE =", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueNotEqualTo(String value) {
            addCriterion("ANSWER_VALUE <>", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueGreaterThan(String value) {
            addCriterion("ANSWER_VALUE >", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_VALUE >=", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueLessThan(String value) {
            addCriterion("ANSWER_VALUE <", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_VALUE <=", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueLike(String value) {
            addCriterion("ANSWER_VALUE like", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueNotLike(String value) {
            addCriterion("ANSWER_VALUE not like", value, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueIn(List<String> values) {
            addCriterion("ANSWER_VALUE in", values, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueNotIn(List<String> values) {
            addCriterion("ANSWER_VALUE not in", values, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueBetween(String value1, String value2) {
            addCriterion("ANSWER_VALUE between", value1, value2, "answerValue");
            return (Criteria) this;
        }

        public Criteria andAnswerValueNotBetween(String value1, String value2) {
            addCriterion("ANSWER_VALUE not between", value1, value2, "answerValue");
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