package com.joyintech.tams.disclosure.information.config.accountamount.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.disclosure.information.config.accountamount.validator.AccountAmountValidator;
import com.joyintech.tams.disclosure.information.config.subbalanceinfopro.dto.SubBalanceInfoPro001Dto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("AccountAmount002")
@ValidLogic(targetClass = AccountAmountValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class AccountAmount001Dto extends BokBalance implements ITableRow, ProcessEntity {
    /**
     * excle行
     */
    private String excelNum;
    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 选中的数据
     */
    private transient List<AccountAmount001Dto> selectedItem;

    /**
     * 元素ID
     */
    private String elementId;
    /**
     * 产品主代码
     */
    private String booksetId;

    private Date dateStart;
    private Date dateEnd;

    private String dateStartStr;
    private String dateEndStr;
    private String subjectNo;
    private String oCcy;
    /**
     * 科目发生额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal tdyODAmt;

    private String errormsg;

    private String status;

    public String getExcelNum() {
        return excelNum;
    }

    public void setExcelNum(String excelNum) {
        this.excelNum = excelNum;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public List<AccountAmount001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<AccountAmount001Dto> selectedItem) {
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
    public String getBooksetId() {
        return booksetId;
    }

    @Override
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }


    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_4)
    public String getSubjectNo() {
        return subjectNo;
    }

    @Override
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    @Override
    public String getoCcy() {
        return oCcy;
    }

    @Override
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    @Override
    public BigDecimal getTdyODAmt() {
        return tdyODAmt;
    }

    @Override
    public void setTdyODAmt(BigDecimal tdyODAmt) {
        this.tdyODAmt = tdyODAmt;
    }

    @Override
    public String getErrormsg() {
        return errormsg;
    }

    @Override
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatusStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.维护数据状态,this.getStatus());
    }

    public String getUpdateTimeStr() {
        return this.getUpdateTime()==null?null: DateUtil.format(this.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
    }

    public void setDateStartStr(String dateStartStr) {
        this.dateStartStr = dateStartStr;
    }

    public void setDateEndStr(String dateEndStr) {
        this.dateEndStr = dateEndStr;
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getDateStartStr() {
        if (StringUtil.isNotEmpty(this.dateStartStr)) {
            return this.dateStartStr;
        } else {
            return DateUtil.format(this.getDateStart());
        }
    }
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getDateEndStr() {
        if (StringUtil.isNotEmpty(this.dateEndStr)) {
            return this.dateEndStr;
        } else {
            return DateUtil.format(this.getDateEnd());
        }
    }



    }
