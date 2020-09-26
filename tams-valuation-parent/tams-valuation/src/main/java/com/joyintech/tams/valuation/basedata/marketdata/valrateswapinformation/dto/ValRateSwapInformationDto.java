package com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MstIrsQuotation;
import com.joyintech.tams.common.base.entity.RateSwapInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.valuation.basedata.marketdata.valrateswapinformation.validator.ValRateSwapInformationValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("ValRateSwapInformation002")
@ValidLogic(targetClass = ValRateSwapInformationValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class ValRateSwapInformationDto extends MstIrsQuotation implements ProcessEntity {

    private String businessId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 保存时是否进行重复性验证
     */
    private boolean validExist;

    /**
     * 是否可編輯
     */
    private boolean disabled;
    /**
     * 按钮id
     */
    private String elementId;
    
    /**
     * 收益率，改为单个价格类型录入，只check收益率
     */
    private BigDecimal yield;
    
	public BigDecimal getYield() {
		return yield;
	}
	
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

    /**
     * 利率互换行情信息列表
     */
    private List<ValRateSwapInformationDto> rateSwapInformationDtoList;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public boolean isValidExist() {
        return validExist;
    }

    public void setValidExist(boolean validExist) {
        this.validExist = validExist;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public List<ValRateSwapInformationDto> getValRateSwapInformationDtoList() {
        return rateSwapInformationDtoList;
    }

    public void setValRateSwapInformationDtoList(List<ValRateSwapInformationDto> rateSwapInformationDtoList) {
        this.rateSwapInformationDtoList = rateSwapInformationDtoList;
    }

    @Override
    @NotEmpty(fieldName = "RateSwapInformation002.curveCode", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getCurveCode() {
        return super.getCurveCode();
    }

    @NotEmpty(fieldName = "RateSwapInformation002.curveDate", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    public String getCurveDateStr() {
        return DateUtil.format(super.getCurveDate());
    }

    public String getBusCurveCode(){
        return SysDataUtil.getNameFromDataDict(DictCode.利率互换曲线类型, super.getCurveCode());
    }

    public String getBusCurveDate(){
        return DateUtil.format(super.getCurveDate());
    }

    public String getBusTermTypeIrs(){
        return SysDataUtil.getNameFromDataDict(DictCode.期限品种_利率互换, super.getTermTypeIrs());
    }

    /**
     * 取得 报买
     * @return 报买
     */
    public String getBusBidPrice() {
        return super.getBidPrice() == null ? "" : super.getBidPrice().setScale(4).toString();
    }

    /**
     * 取得 报卖
     * @return 报卖
     */
    public String getBusOfferPrice() {
        return super.getOfferPrice() == null ? "" : super.getOfferPrice().setScale(4).toString();
    }


    /**
     * 取得 均值
     * @return 均值
     */
    public String getBusAvgPrice() {
        return super.getAvgPrice() == null ? "" : super.getAvgPrice().setScale(4).toString();
    }
}
