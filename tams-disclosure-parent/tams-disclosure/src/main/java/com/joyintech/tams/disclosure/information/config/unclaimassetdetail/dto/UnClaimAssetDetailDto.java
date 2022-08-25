package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisNostdCreditor;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.validator.UnClaimAssetDetailValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
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
     * excle行
     */
    private String excelNum;
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
     *
     * @return
     */
    private String updateTimeStr;

//    public String getUpdateTimeStr() {
//        return updateTimeStr;
//    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    /**
     * 该产品下非标资产列表
     */
    private List<UnClaimAssetDetailDto> unclaimList;
    /**
     * 选中的数据
     */
    private transient List<UnClaimAssetDetailDto> selectedItem;

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
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getEffDateStr() {
        if (StringUtil.isNotEmpty(this.effDateStr)) {
            return this.effDateStr;
        } else {
            return DateUtil.format(super.getEffDate());
        }
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    public void setEffEndDateStr(String effEndDateStr) {
        this.effEndDateStr = effEndDateStr;
    }

    public String effDateStr;
    public String effEndDateStr;


    public Date effEndDateO;
    public Date effDateO;
    public Date effDateOStr;

    public Date getEffDateO() {
        return effDateO;
    }

    public void setEffDateO(Date effDateO) {
        this.effDateO = effDateO;
    }

    public String getEffDateOStr() {
        return DateUtil.format(this.getEffDateO());
    }
    public String effEndDateOStr;
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getEffEndDateStr() {
        if (StringUtil.isNotEmpty(this.effEndDateStr)) {
            return this.effEndDateStr;
        } else {
            return DateUtil.format(super.getEffEndDate());
        }
    }

    //    获取数据状态枚举
    public String getDataStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getDataStatus());
    }


    @NotEmpty(fieldName = "产品名称", groups = {ValidateGroupEdit.class})
    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public List<UnClaimAssetDetailDto> getUnclaimList() {
        return unclaimList;
    }

    public void setUnclaimList(List<UnClaimAssetDetailDto> unclaimList) {
        this.unclaimList = unclaimList;
    }

    public List<UnClaimAssetDetailDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<UnClaimAssetDetailDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    @Override
    @NotEmpty(fieldName = "产品主代码", groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdId() {
        return super.getProdId();
    }

    @Override
    @NotEmpty(fieldName = "生效日", groups = {ValidateGroupEdit.class})
    public Date getEffDate() {
        return super.getEffDate();
    }

    @Override
    @NotEmpty(fieldName = "生效截止日期", groups = {ValidateGroupEdit.class})
    public Date getEffEndDate() {
        return super.getEffEndDate();
    }
    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public Date getEffEndDateO() {
        return effEndDateO;
    }

    public void setEffEndDateO(Date effEndDateO) {
        this.effEndDateO = effEndDateO;
    }
    public String getEffEndDateOStr() {
        return DateUtil.format(this.getEffEndDateO());
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }
}