package com.joyintech.tams.disclosure.information.config.prodfeediscount.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.DisProdFeediscount;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.prodfeediscount.validator.ProdFeediscountValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@PageId("ProdFeediscount001")
@ValidLogic(targetClass = ProdFeediscountValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class ProdFeediscountDto extends DisProdFeediscount implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;

    private String operateType ;

    /**
     * 选中的数据
     */
    private transient List<ProdFeediscountDto> selectedItem;
    /**
     * 元素ID
     */
    private String elementId;

    private String dataStatusName;


    private String prodName;

    private String updateDateStr;

    /**
     * 优化起始时间
     */
    private String startDateStr;

    /**
     * 优惠截至时间
     */
    private String endDateStr;

    /**
     * 报告发布日期
     */
    private String rptIssueDateStr;


    public String getUpdateDateStr() {
       // return updateDateStr;
        if(StringUtils.isNotBlank(this.updateDateStr)){
            return this.updateDateStr ;
        }else{
            return this.updateDateStr = this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd hh:mm:ss");
        }
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    public String getStartDateStr() {
        //return startDateStr;
        if(StringUtils.isNotBlank(this.startDateStr)){
            return this.startDateStr ;
        }else{
            return this.startDateStr = this.getStartDate()==null?null: DateUtil.format(this.getStartDate(), "yyyy-MM-dd");
        }

    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
       // return endDateStr;
        if(StringUtils.isNotBlank(this.endDateStr)){
            return this.endDateStr ;
        }else{
            return this.endDateStr = this.getEndDate()==null?null: DateUtil.format(this.getEndDate(), "yyyy-MM-dd");
        }
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getRptIssueDateStr() {
        if(StringUtils.isNotEmpty(this.rptIssueDateStr)){
            return this.rptIssueDateStr ;
        }else{
            return this.rptIssueDateStr = this.getRptIssueDate()==null?null: DateUtil.format(this.getRptIssueDate(), "yyyy-MM-dd");
        }
    }

    public void setRptIssueDateStr(String rptIssueDateStr) {
        this.rptIssueDateStr = rptIssueDateStr;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getDataStatusName() {
        if(StringUtils.isNotBlank(this.dataStatusName)){
            return this.dataStatusName ;
        }else{
            return this.dataStatusName = SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态ED, this.getDataStatus());
        }
    }

    public void setDataStatusName(String dataStatusName) {
        this.dataStatusName = dataStatusName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<ProdFeediscountDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ProdFeediscountDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getProdDiscountId() {
        return super.getProdDiscountId();
    }

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }
}