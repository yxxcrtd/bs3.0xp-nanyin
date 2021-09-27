

package com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.validator.MstCurveGroupManageDtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @Description:收益率曲线族dto
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@PageId("MstCurveDefManage002")
@ValidLogic(targetClass = MstCurveGroupManageDtoValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class MstCurveGroupManageDto extends BaseEntity implements ITableRow , ProcessEntity {

    /**
     * 曲线代码
     */
    private String curveGroupCode;
    /**
     * 曲线来源
     */
    private String valueSource;

    private String valueSourceName;
    /**
     * 备注/描述
     */
    private String remark;

    /**
     * 曲线名称
     */
    private String curveGroupName;

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

    @PrimaryKey(PrimaryKey.Field.PK_1)
    @NotBlank(message = "曲线代码不能为空!", groups = ValidateGroupEdit.class)
    public String getCurveGroupCode() {
        return curveGroupCode;
    }

    public void setCurveGroupCode(String curveGroupCode) {
        this.curveGroupCode = curveGroupCode;
    }


    @NotBlank(message = "曲线来源不能为空!", groups = ValidateGroupEdit.class)
    public String getValueSource() {
        return valueSource;
    }

    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    @NotBlank(message = "描述不能为空!", groups = ValidateGroupEdit.class)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @NotBlank(message = "曲线名称不能为空!", groups = ValidateGroupEdit.class)
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

    public String getValueSourceName() {
        return SysDataUtil.getNameFromDataDict(DictCode.曲线来源, this.getValueSource());
    }

    public void setValueSourceName(String valueSourceName) {
        this.valueSourceName = valueSourceName;
    }

    public List<MstCurveGroupManageDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<MstCurveGroupManageDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


}
