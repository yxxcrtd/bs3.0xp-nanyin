package com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.standardcouponreconversionrate.validator.StandardCouponReConversionRateValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;


@PageId("StandardCouponReConversionRate002")
@ValidLogic(targetClass = StandardCouponReConversionRateValidator.class, groups = { ValidateGroupEdit.class,
        ValidateGroupDelete.class })
public class StandardCouponReConversionRateDto extends BaseEntity implements ITableRow,ProcessEntity {


    private transient List<StandardCouponReConversionRateDto> selectedItem;
    private String operateType;

    private String elementId;


    private String brokerId;//券商

    private String brokerName; //券商

    public String getBrokerName() {
        if(this.getBrokerId ().equals ("BROKER")){
            return "所有";
        }
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    private String accountType;//资金账户

    private String accountTypeStr;//资金账户

    private String tandardRate;//标准券再折算率

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @NotEmpty(fieldName = "StandardCouponReConversionRate001.broker", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value= PrimaryKey.Field.PK_1)
    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    @NotEmpty(fieldName = "StandardCouponReConversionRate001.accountType", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value= PrimaryKey.Field.PK_2)
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountTypeStr() {
        if(this.getAccountType ().equals ("BROKER")){
            return "所有";
        }
        return accountTypeStr;
    }

    public void setAccountTypeStr(String accountTypeStr) {
        this.accountTypeStr = accountTypeStr;
    }

    @NotEmpty(fieldName = "StandardCouponReConversionRate002.tandardRate", groups = {ValidateGroupEdit.class})
    public String getTandardRate() {
        return tandardRate;
    }

    public void setTandardRate(String tandardRate) {
        this.tandardRate = tandardRate;
    }

    public List<StandardCouponReConversionRateDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<StandardCouponReConversionRateDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
