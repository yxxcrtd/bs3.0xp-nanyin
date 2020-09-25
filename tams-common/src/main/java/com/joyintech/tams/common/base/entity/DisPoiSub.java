package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class DisPoiSub extends BaseEntity {
    /**
     * 规则编号
     */
    private String id;

    /**
     * 模板编号
     */
    private String tabNum;

    /**
     * 子表名称
     */
    private String subName;

    /**
     * 模板类型 (W-word,X-excel),默认W
     */
    private String subType;

    /**
     * 表格类型(01-单表,02多列表,03-图表)
     */
    private String subFormType;

    /**
     * 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     */
    private String subWidth;

    /**
     * 表格样式(01-参考模板,99-无)
     */
    private String subTmpType;

    /**
     * 图表类型(01-柱状图,02-饼图......)
     */
    private String subChartType;

    /**
     * 如果错误或者为空展示信息
     */
    private String subDefualt;

    /**
     * 图表配置(表格类型为03时需要，json格式字符串)
     */
    private String subChartConf;

    /**
     * 取得 规则编号
     * @return 规则编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 规则编号
     * @param id 规则编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 模板编号
     * @return 模板编号
     */
    public String getTabNum() {
        return tabNum;
    }

    /**
     * 设置 模板编号
     * @param tabNum 模板编号
     */
    public void setTabNum(String tabNum) {
        this.tabNum = tabNum == null ? null : tabNum.trim();
    }

    /**
     * 取得 子表名称
     * @return 子表名称
     */
    public String getSubName() {
        return subName;
    }

    /**
     * 设置 子表名称
     * @param subName 子表名称
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    /**
     * 取得 模板类型 (W-word,X-excel),默认W
     * @return 模板类型 (W-word,X-excel),默认W
     */
    public String getSubType() {
        return subType;
    }

    /**
     * 设置 模板类型 (W-word,X-excel),默认W
     * @param subType 模板类型 (W-word,X-excel),默认W
     */
    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    /**
     * 取得 表格类型(01-单表,02多列表,03-图表)
     * @return 表格类型(01-单表,02多列表,03-图表)
     */
    public String getSubFormType() {
        return subFormType;
    }

    /**
     * 设置 表格类型(01-单表,02多列表,03-图表)
     * @param subFormType 表格类型(01-单表,02多列表,03-图表)
     */
    public void setSubFormType(String subFormType) {
        this.subFormType = subFormType == null ? null : subFormType.trim();
    }

    /**
     * 取得 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     * @return 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     */
    public String getSubWidth() {
        return subWidth;
    }

    /**
     * 设置 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     * @param subWidth 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     */
    public void setSubWidth(String subWidth) {
        this.subWidth = subWidth == null ? null : subWidth.trim();
    }

    /**
     * 取得 表格样式(01-参考模板,99-无)
     * @return 表格样式(01-参考模板,99-无)
     */
    public String getSubTmpType() {
        return subTmpType;
    }

    /**
     * 设置 表格样式(01-参考模板,99-无)
     * @param subTmpType 表格样式(01-参考模板,99-无)
     */
    public void setSubTmpType(String subTmpType) {
        this.subTmpType = subTmpType == null ? null : subTmpType.trim();
    }

    /**
     * 取得 图表类型(01-柱状图,02-饼图......)
     * @return 图表类型(01-柱状图,02-饼图......)
     */
    public String getSubChartType() {
        return subChartType;
    }

    /**
     * 设置 图表类型(01-柱状图,02-饼图......)
     * @param subChartType 图表类型(01-柱状图,02-饼图......)
     */
    public void setSubChartType(String subChartType) {
        this.subChartType = subChartType == null ? null : subChartType.trim();
    }

    /**
     * 取得 如果错误或者为空展示信息
     * @return 如果错误或者为空展示信息
     */
    public String getSubDefualt() {
        return subDefualt;
    }

    /**
     * 设置 如果错误或者为空展示信息
     * @param subDefualt 如果错误或者为空展示信息
     */
    public void setSubDefualt(String subDefualt) {
        this.subDefualt = subDefualt == null ? null : subDefualt.trim();
    }

    /**
     * 取得 图表配置(表格类型为03时需要，json格式字符串)
     * @return 图表配置(表格类型为03时需要，json格式字符串)
     */
    public String getSubChartConf() {
        return subChartConf;
    }

    /**
     * 设置 图表配置(表格类型为03时需要，json格式字符串)
     * @param subChartConf 图表配置(表格类型为03时需要，json格式字符串)
     */
    public void setSubChartConf(String subChartConf) {
        this.subChartConf = subChartConf == null ? null : subChartConf.trim();
    }
}