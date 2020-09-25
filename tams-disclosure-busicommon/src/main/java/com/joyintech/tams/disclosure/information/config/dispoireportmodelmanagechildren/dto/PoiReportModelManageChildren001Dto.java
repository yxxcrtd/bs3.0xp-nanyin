package com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.disclosure.information.config.dispoireportmodelmanagechildren.validator.PoiReportModelManageChildren001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.util.List;


/**
 * 报表子表实体
 *
 * @author kx
 */
@PageId("PoiReportModelManageChildren002")
@ValidLogic(targetClass = PoiReportModelManageChildren001Validator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class PoiReportModelManageChildren001Dto extends BaseEntity {
    /**
     * 规则编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
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
     * 表格类型为03时需要，json格式字符串
     */
    private String subChartConf;

    private String subTypeStr;

    private String subFormTypeStr;

    private String subWidthStr;

    private String subChartTypeStr;

    private String subTmpTypeStr;

    private String statusStr;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private List<PoiReportModelManageChildren001Dto> selectedItem;

    /**
     * 模板名称
     */
    private String tempName;
    
    /**
     * 
     */
    private String pageModel;

    /**
     * 取得 规则编号
     *
     * @return 规则编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 规则编号
     *
     * @param id 规则编号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 模板编号
     *
     * @return 模板编号
     */
    public String getTabNum() {
        return tabNum;
    }

    /**
     * 设置 模板编号
     *
     * @param tabNum 模板编号
     */
    public void setTabNum(String tabNum) {
        this.tabNum = tabNum == null ? null : tabNum.trim();
    }

    /**
     * 取得 子表名称
     *
     * @return 子表名称
     */
    public String getSubName() {
        return subName;
    }

    /**
     * 设置 子表名称
     *
     * @param subName 子表名称
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    /**
     * 取得 模板类型 (W-word,X-excel),默认W
     *
     * @return 模板类型 (W-word,X-excel),默认W
     */
    public String getSubType() {
        return subType;
    }

    /**
     * 设置 模板类型 (W-word,X-excel),默认W
     *
     * @param subType 模板类型 (W-word,X-excel),默认W
     */
    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    /**
     * 取得 表格类型(01-单表,02多列表,03-图表)
     *
     * @return 表格类型(01 - 单表, 02多列表, 03 - 图表)
     */
    public String getSubFormType() {
        return subFormType;
    }

    /**
     * 设置 表格类型(01-单表,02多列表,03-图表)
     *
     * @param subFormType 表格类型(01-单表,02多列表,03-图表)
     */
    public void setSubFormType(String subFormType) {
        this.subFormType = subFormType == null ? null : subFormType.trim();
    }

    /**
     * 取得 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     *
     * @return 表格宽度(N - 窄边距, M - 适中边距, E - 宽边距)
     */
    public String getSubWidth() {
        return subWidth;
    }

    /**
     * 设置 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     *
     * @param subWidth 表格宽度(N-窄边距,M-适中边距,E-宽边距)
     */
    public void setSubWidth(String subWidth) {
        this.subWidth = subWidth == null ? null : subWidth.trim();
    }

    /**
     * 取得 表格样式(01-参考模板,99-无)
     *
     * @return 表格样式(01 - 参考模板, 99 - 无)
     */
    public String getSubTmpType() {
        return subTmpType;
    }

    /**
     * 设置 表格样式(01-参考模板,99-无)
     *
     * @param subTmpType 表格样式(01-参考模板,99-无)
     */
    public void setSubTmpType(String subTmpType) {
        this.subTmpType = subTmpType == null ? null : subTmpType.trim();
    }

    /**
     * 取得 图表类型(01-柱状图,02-饼图......)
     *
     * @return 图表类型(01 - 柱状图, 02 - 饼图......)
     */
    public String getSubChartType() {
        return subChartType;
    }

    /**
     * 设置 图表类型(01-柱状图,02-饼图......)
     *
     * @param subChartType 图表类型(01-柱状图,02-饼图......)
     */
    public void setSubChartType(String subChartType) {
        this.subChartType = subChartType == null ? null : subChartType.trim();
    }

    /**
     * 取得 如果错误或者为空展示信息
     *
     * @return 如果错误或者为空展示信息
     */
    public String getSubDefualt() {
        return subDefualt;
    }

    /**
     * 设置 如果错误或者为空展示信息
     *
     * @param subDefualt 如果错误或者为空展示信息
     */
    public void setSubDefualt(String subDefualt) {
        this.subDefualt = subDefualt == null ? null : subDefualt.trim();
    }

    public String getSubTypeStr() {
        return subTypeStr;
    }

    public void setSubTypeStr(String subTypeStr) {
        this.subTypeStr = subTypeStr;
    }

    public String getSubFormTypeStr() {
        return subFormTypeStr;
    }

    public void setSubFormTypeStr(String subFormTypeStr) {
        this.subFormTypeStr = subFormTypeStr;
    }

    public String getSubWidthStr() {
        return subWidthStr;
    }

    public void setSubWidthStr(String subWidthStr) {
        this.subWidthStr = subWidthStr;
    }

    public String getSubChartTypeStr() {
        return subChartTypeStr;
    }

    public void setSubChartTypeStr(String subChartTypeStr) {
        this.subChartTypeStr = subChartTypeStr;
    }

    public String getSubTmpTypeStr() {
        return subTmpTypeStr;
    }

    public void setSubTmpTypeStr(String subTmpTypeStr) {
        this.subTmpTypeStr = subTmpTypeStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<PoiReportModelManageChildren001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<PoiReportModelManageChildren001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSubChartConf() {
        return subChartConf;
    }

    public void setSubChartConf(String subChartConf) {
        this.subChartConf = subChartConf;
    }

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

}