package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.validator.UnClaimAssetDetailValidator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.Date;
import java.util.List;

/**
 * 非标债权类资产明细维护DTO
 *
 * @author Marui
 * @version 1.0 2022年7月13日
 * @see UnClaimAssetDetailDto
 * @since 1.0
 */
@PageId("UnClaimAssetDetail002")
@ValidLogic(targetClass = UnClaimAssetDetailValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class UnClaimAssetDetailDto extends DisNostdCreditor implements ITableRow, ProcessEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面model
     */
    private String pageModel;
    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 更新时间str
     * @return
     */
    private String updateTimeStr;

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    /**
     * 选中的记录
     */
    private transient List<UnClaimAssetDetailDto> uncliamList;




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

    public String getEffDateStr() {
         return DateUtil.format(super.getEffDate());
    }

    public String getEffEndDateStr() {
       return DateUtil.format(super.getEffEndDate());
    }
//    获取数据状态枚举
    public String getDataStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态,this.getDataStatus());
    }



    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 取得选择的数据
     *
     * @return list
     */
    public List<UnClaimAssetDetailDto> getUncliamList() {
        return uncliamList;
    }

    public void setUncliamList(List<UnClaimAssetDetailDto> uncliamList) {
        this.uncliamList = uncliamList;
    }
}
