package com.joyintech.tams.disclosure.information.config.repRelDeal.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepRelDeal;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.repProdIndex.dto.RepProdIndexDto;
import com.joyintech.tams.disclosure.information.config.repRelDeal.validator.RepRelDealValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("RepRelDeal002")
@ValidLogic(targetClass = RepRelDealValidator.class, groups = { ValidateGroupEdit.class, ValidateGroupDelete.class })
public class RepRelDealDto extends RepRelDeal implements ITableRow, ProcessEntity {

    /**
     * 操作类型
     */
    private String operateType;
    /**
     * 页面类型
     */
    private String pageModel;
    /**
     * 要素id
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<RepRelDealDto> selectedItem;

    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 导入时记录excel中的行号
     * @return
     */
    private String excelRowNum;

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getUuid() {
        return super.getUuid();
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

    public List<RepRelDealDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<RepRelDealDto> selectedItem) {
        this.selectedItem = selectedItem;
    }


    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getExcelRowNum() {
        return excelRowNum;
    }

    public void setExcelRowNum(String excelRowNum) {
        this.excelRowNum = excelRowNum;
    }

    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, this.getStatus());
    }

    public String getPubDateStr() {
        return this.getPubDate()==null?null: DateUtil.format(this.getPubDate(), "yyyy-MM-dd");
    }

    public String getEffdateStr() {
        return this.getEffdate()==null?null: DateUtil.format(this.getEffdate(), "yyyy-MM-dd");
    }

    public String getEffdelidateStr() {
        return this.getEffdelidate()==null?null: DateUtil.format(this.getEffdelidate(), "yyyy-MM-dd");
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }
}
