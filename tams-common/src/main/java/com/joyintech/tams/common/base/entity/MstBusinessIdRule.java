package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstBusinessIdRule extends BaseEntity {
    /**
     * 业务编号规则序号
     */
    private String seqNo;

    /**
     * 共通业务类型
     */
    private String commonBusinessType;

    /**
     * 一级分类
     */
    private String type1;

    /**
     * 二级分类
     */
    private String type2;

    /**
     * 三级分类
     */
    private String type3;

    /**
     * 四级分类
     */
    private String type4;

    /**
     * 五级分类
     */
    private String type5;

    /**
     * 六级分类
     */
    private String type6;

    /**
     * 业务编号生成规则
     */
    private String businessIdRuleType;

    /**
     * 前缀名
     */
    private String prefixName;

    /**
     * 前缀生成用序列名称
     */
    private String prefixSequenceName;

    /**
     * 前缀生成用序列长度
     */
    private Long prefixSequenceLen;

    /**
     * 后缀名
     */
    private String postfixName;

    /**
     * 后缀生成用序列名称
     */
    private String postfixSequenceName;

    /**
     * 后缀生成用序列长度
     */
    private Long postfixSequenceLen;

    /**
     * 日期规则
     */
    private String dateRuleType;

    /**
     * 业务编号生成类名（业务编号生成规则为05:独特规则时必输）
     */
    private String businessIdServiceName;

    /**
     * 业务编号生成方法
     */
    private String businessIdMethodName;

    /**
     * 取得 业务编号规则序号
     * @return 业务编号规则序号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 业务编号规则序号
     * @param seqNo 业务编号规则序号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 共通业务类型
     * @return 共通业务类型
     */
    public String getCommonBusinessType() {
        return commonBusinessType;
    }

    /**
     * 设置 共通业务类型
     * @param commonBusinessType 共通业务类型
     */
    public void setCommonBusinessType(String commonBusinessType) {
        this.commonBusinessType = commonBusinessType == null ? null : commonBusinessType.trim();
    }

    /**
     * 取得 一级分类
     * @return 一级分类
     */
    public String getType1() {
        return type1;
    }

    /**
     * 设置 一级分类
     * @param type1 一级分类
     */
    public void setType1(String type1) {
        this.type1 = type1 == null ? null : type1.trim();
    }

    /**
     * 取得 二级分类
     * @return 二级分类
     */
    public String getType2() {
        return type2;
    }

    /**
     * 设置 二级分类
     * @param type2 二级分类
     */
    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    /**
     * 取得 三级分类
     * @return 三级分类
     */
    public String getType3() {
        return type3;
    }

    /**
     * 设置 三级分类
     * @param type3 三级分类
     */
    public void setType3(String type3) {
        this.type3 = type3 == null ? null : type3.trim();
    }

    /**
     * 取得 四级分类
     * @return 四级分类
     */
    public String getType4() {
        return type4;
    }

    /**
     * 设置 四级分类
     * @param type4 四级分类
     */
    public void setType4(String type4) {
        this.type4 = type4 == null ? null : type4.trim();
    }

    /**
     * 取得 五级分类
     * @return 五级分类
     */
    public String getType5() {
        return type5;
    }

    /**
     * 设置 五级分类
     * @param type5 五级分类
     */
    public void setType5(String type5) {
        this.type5 = type5 == null ? null : type5.trim();
    }

    /**
     * 取得 六级分类
     * @return 六级分类
     */
    public String getType6() {
        return type6;
    }

    /**
     * 设置 六级分类
     * @param type6 六级分类
     */
    public void setType6(String type6) {
        this.type6 = type6 == null ? null : type6.trim();
    }

    /**
     * 取得 业务编号生成规则
     * @return 业务编号生成规则
     */
    public String getBusinessIdRuleType() {
        return businessIdRuleType;
    }

    /**
     * 设置 业务编号生成规则
     * @param businessIdRuleType 业务编号生成规则
     */
    public void setBusinessIdRuleType(String businessIdRuleType) {
        this.businessIdRuleType = businessIdRuleType == null ? null : businessIdRuleType.trim();
    }

    /**
     * 取得 前缀名
     * @return 前缀名
     */
    public String getPrefixName() {
        return prefixName;
    }

    /**
     * 设置 前缀名
     * @param prefixName 前缀名
     */
    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName == null ? null : prefixName.trim();
    }

    /**
     * 取得 前缀生成用序列名称
     * @return 前缀生成用序列名称
     */
    public String getPrefixSequenceName() {
        return prefixSequenceName;
    }

    /**
     * 设置 前缀生成用序列名称
     * @param prefixSequenceName 前缀生成用序列名称
     */
    public void setPrefixSequenceName(String prefixSequenceName) {
        this.prefixSequenceName = prefixSequenceName == null ? null : prefixSequenceName.trim();
    }

    /**
     * 取得 前缀生成用序列长度
     * @return 前缀生成用序列长度
     */
    public Long getPrefixSequenceLen() {
        return prefixSequenceLen;
    }

    /**
     * 设置 前缀生成用序列长度
     * @param prefixSequenceLen 前缀生成用序列长度
     */
    public void setPrefixSequenceLen(Long prefixSequenceLen) {
        this.prefixSequenceLen = prefixSequenceLen;
    }

    /**
     * 取得 后缀名
     * @return 后缀名
     */
    public String getPostfixName() {
        return postfixName;
    }

    /**
     * 设置 后缀名
     * @param postfixName 后缀名
     */
    public void setPostfixName(String postfixName) {
        this.postfixName = postfixName == null ? null : postfixName.trim();
    }

    /**
     * 取得 后缀生成用序列名称
     * @return 后缀生成用序列名称
     */
    public String getPostfixSequenceName() {
        return postfixSequenceName;
    }

    /**
     * 设置 后缀生成用序列名称
     * @param postfixSequenceName 后缀生成用序列名称
     */
    public void setPostfixSequenceName(String postfixSequenceName) {
        this.postfixSequenceName = postfixSequenceName == null ? null : postfixSequenceName.trim();
    }

    /**
     * 取得 后缀生成用序列长度
     * @return 后缀生成用序列长度
     */
    public Long getPostfixSequenceLen() {
        return postfixSequenceLen;
    }

    /**
     * 设置 后缀生成用序列长度
     * @param postfixSequenceLen 后缀生成用序列长度
     */
    public void setPostfixSequenceLen(Long postfixSequenceLen) {
        this.postfixSequenceLen = postfixSequenceLen;
    }

    /**
     * 取得 日期规则
     * @return 日期规则
     */
    public String getDateRuleType() {
        return dateRuleType;
    }

    /**
     * 设置 日期规则
     * @param dateRuleType 日期规则
     */
    public void setDateRuleType(String dateRuleType) {
        this.dateRuleType = dateRuleType == null ? null : dateRuleType.trim();
    }

    /**
     * 取得 业务编号生成类名（业务编号生成规则为05:独特规则时必输）
     * @return 业务编号生成类名（业务编号生成规则为05:独特规则时必输）
     */
    public String getBusinessIdServiceName() {
        return businessIdServiceName;
    }

    /**
     * 设置 业务编号生成类名（业务编号生成规则为05:独特规则时必输）
     * @param businessIdServiceName 业务编号生成类名（业务编号生成规则为05:独特规则时必输）
     */
    public void setBusinessIdServiceName(String businessIdServiceName) {
        this.businessIdServiceName = businessIdServiceName == null ? null : businessIdServiceName.trim();
    }

    /**
     * 取得 业务编号生成方法
     * @return 业务编号生成方法
     */
    public String getBusinessIdMethodName() {
        return businessIdMethodName;
    }

    /**
     * 设置 业务编号生成方法
     * @param businessIdMethodName 业务编号生成方法
     */
    public void setBusinessIdMethodName(String businessIdMethodName) {
        this.businessIdMethodName = businessIdMethodName == null ? null : businessIdMethodName.trim();
    }
}