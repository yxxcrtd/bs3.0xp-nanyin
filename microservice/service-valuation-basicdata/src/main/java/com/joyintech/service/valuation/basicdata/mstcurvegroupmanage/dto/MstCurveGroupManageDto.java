
package com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;

import java.util.List;

/**
 * @Description:收益率曲线族实体类
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
public class MstCurveGroupManageDto extends BaseEntity implements ITableRow{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5143537191208289654L;

    /**
     * 曲线代码
     */
    private String curveGroupCode;

    /**
     * 曲线名称
     */
    private String curveGroupName;

    /**
     * 曲线来源
     */
    private String valueSource;
    /**
     * 备注
     */
    private String remark;


    /**
     * 操作类型
     */
    private String operateType;

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String pageModel;


    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

    private List<MstCurveGroupManageDto> selectedItem;

    public String getCurveGroupCode() {
        return curveGroupCode;
    }

    public void setCurveGroupCode(String curveGroupCode) {
        this.curveGroupCode = curveGroupCode;
    }

    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurveGroupName() {
        return curveGroupName;
    }

    public void setCurveGroupName(String curveGroupName) {
        this.curveGroupName = curveGroupName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<MstCurveGroupManageDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstCurveGroupManageDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
