package com.joyintech.tams.invest.basicdata.futuresdepositpercent.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.validator.FundSecuritiesAccountValidator;
import com.joyintech.tams.invest.basicdata.futuresdepositpercent.valid.FuturesDepositPercentValidation;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname FuturesDepositPercentDto
 * @Description TODO
 * @author zhuhanlu 
 * @date 2020/11/12 14:31 
 * @version 1.0 
 */
@PageId("FuturesDepositPercent002")
@ValidLogic(targetClass = FuturesDepositPercentValidation.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class FuturesDepositPercentDto extends BaseEntity implements ITableRow, ProcessEntity {



    /**
     * 期货类型
     */
    @NotEmpty(fieldName = "FuturesDepositPercent001.futuresType",groups = {ValidateGroupEdit.class})
    private String futuresType;

    /**
     * 页面元素
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面类型
     */
    private String pageModel;

    /**
     * 合约标的
     */
    @NotEmpty(fieldName = "FuturesDepositPercent001.futuresContractId",groups = {ValidateGroupEdit.class})
    private String futuresContractId;

    /**
     * 期货公司
     */
    @NotEmpty(fieldName = "FuturesDepositPercent001.futuresCompany",groups = {ValidateGroupEdit.class})
    private String futuresCompany;

    /**
     * 生效日
     */
    @NotEmpty(fieldName = "FuturesDepositPercent001.effDate",groups = {ValidateGroupEdit.class})
    private Date effDate;

    /**
     * 生效日Str
     */
    private String effDateStr;

    /**
     * 保证金比例
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    @NotEmpty(fieldName = "FuturesDepositPercent002.depositPercent",groups = {ValidateGroupEdit.class})
    private BigDecimal depositPercent;

    /**
     * 期货公司明长城
     */
    private String futuresCompanyName;
    
    /**
     * 期货类型名
     */
    private String futuresTypeName;
    
    /**
     * 合约标的名
     */
    private String futuresContractIdName;

    public String getFuturesCompanyName() {
        return futuresCompanyName;
    }

    public void setFuturesCompanyName(String futuresCompanyName) {
        this.futuresCompanyName = futuresCompanyName;
    }

    /**
     * 选中的数据
     */
    private transient List<FuturesDepositPercentDto> selectedItem;

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public List<FuturesDepositPercentDto> getSelectedItem() {
        return selectedItem;
    }



    public void setSelectedItem(List<FuturesDepositPercentDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @PrimaryKey(value = PrimaryKey.Field.PK_3)
    public String getEffDateStr() {
        return DateUtil.format(this.getEffDate(), "yyyy-MM-dd");
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    @PrimaryKey(value = PrimaryKey.Field.PK_4)
    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getFuturesType() {
        return futuresType;
    }

    public void setFuturesType(String futuresType) {
        this.futuresType = futuresType;
    }

    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getFuturesContractId() {
        return futuresContractId;
    }

    public void setFuturesContractId(String futuresContractId) {
        this.futuresContractId = futuresContractId;
    }


    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    public String getFuturesCompany() {
        return futuresCompany;
    }

    public void setFuturesCompany(String futuresCompany) {
        this.futuresCompany = futuresCompany;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public BigDecimal getDepositPercent() {
        return depositPercent;
    }

    public void setDepositPercent(BigDecimal depositPercent) {
        this.depositPercent = depositPercent;
    }

    public String getFuturesTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.期货类型, getFuturesType());
    }
    public String getFuturesContractIdStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.合约标的, getFuturesContractId());
    }

	public String getFuturesTypeName() {
		return futuresTypeName;
	}

	public void setFuturesTypeName(String futuresTypeName) {
		this.futuresTypeName = futuresTypeName;
	}

	public String getFuturesContractIdName() {
		return futuresContractIdName;
	}

	public void setFuturesContractIdName(String futuresContractIdName) {
		this.futuresContractIdName = futuresContractIdName;
	}
}
