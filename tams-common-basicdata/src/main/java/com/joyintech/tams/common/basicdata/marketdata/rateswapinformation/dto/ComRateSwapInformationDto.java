package com.joyintech.tams.common.basicdata.marketdata.rateswapinformation.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.MstIrsQuotation;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.common.basicdata.marketdata.rateswapinformation.validator.ComRateSwapInformationValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.List;

@ValidLogic(targetClass = ComRateSwapInformationValidator.class , groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class ComRateSwapInformationDto extends MstIrsQuotation implements ProcessEntity {

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
    private List<ComRateSwapInformationDto> comRateSwapInformationDtoList;

    private List<ComRateSwapInformationDto> selectedItem;

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

    public List<ComRateSwapInformationDto> getComRateSwapInformationDtoList() {
        return comRateSwapInformationDtoList;
    }

    public void setComRateSwapInformationDtoList(List<ComRateSwapInformationDto> comRateSwapInformationDtoList) {
        this.comRateSwapInformationDtoList = comRateSwapInformationDtoList;
    }

    @Override
    @NotEmpty(fieldName = "ComRateSwapInformation002.curveCode", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value = PrimaryKey.Field.PK_1)
    public String getCurveCode() {
        return super.getCurveCode();
    }

    @NotEmpty(fieldName = "ComRateSwapInformation002.curveDate", groups = {ValidateGroupEdit.class})
    @PrimaryKey(value = PrimaryKey.Field.PK_2)
    public String getCurveDateStr() {
        return DateUtil.format(super.getCurveDate());
    }

    public String getBusCurveCode(){
        if (StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.利率互换曲线类型, super.getCurveCode()))){
            return SysDataUtil.getNameFromDataDict(DictCode.利率互换曲线类型, super.getCurveCode());
        }else {
            // 01 定盘曲线  02  收盘曲线
            String busCurveCode = "";
            if (StringUtil.equals("01",this.getCurveIntType())){
                busCurveCode = "利率互换-定盘曲线-" +  this.getBaseIntCode();
            }else {
                busCurveCode = "利率互换-收盘曲线-" + this.getBaseIntCode();
            }
            if (StringUtil.equals(this.getPriceType(), DictCodeListExt2.价格类型_报买.getValue())){
                busCurveCode = busCurveCode + "-报买";
            }
            if (StringUtil.equals(this.getPriceType(), DictCodeListExt2.价格类型_报卖.getValue())){
                busCurveCode = busCurveCode + "-报卖";
            }
            if (StringUtil.equals(this.getPriceType(), DictCodeListExt2.价格类型_均值.getValue())){
                busCurveCode = busCurveCode + "-均值";
            }
            return busCurveCode;
        }
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
        if (StringUtil.equals(this.getPriceType(), DictCodeListExt2.价格类型_报买.getValue())){
            super.setBidPrice(this.getYield());
        }
        return super.getBidPrice() == null ? "" : super.getBidPrice().setScale(4).toString();
    }

    /**
     * 取得 报卖
     * @return 报卖
     */
    public String getBusOfferPrice() {
        if (StringUtil.equals(this.getPriceType(), DictCodeListExt2.价格类型_报卖.getValue())){
            super.setOfferPrice(this.getYield());
        }
        return super.getOfferPrice() == null ? "" : super.getOfferPrice().setScale(4).toString();
    }


    /**
     * 取得 均值
     * @return 均值
     */
    public String getBusAvgPrice() {
        if (StringUtil.equals(this.getPriceType(), DictCodeListExt2.价格类型_均值.getValue())){
            super.setAvgPrice(this.getYield());
        }
        return super.getAvgPrice() == null ? "" : super.getAvgPrice().setScale(4).toString();
    }
    private String curveType;

    private String curveIntType;

    private String baseIntCode;

    private String priceType;

    public String getCurveType() {
        return curveType;
    }

    public void setCurveType(String curveType) {
        this.curveType = curveType;
    }
    public List<ComRateSwapInformationDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<ComRateSwapInformationDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getBaseIntCode() {
        return baseIntCode;
    }

    public void setBaseIntCode(String baseIntCode) {
        this.baseIntCode = baseIntCode;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getCurveIntType() {
        return curveIntType;
    }

    public void setCurveIntType(String curveIntType) {
        this.curveIntType = curveIntType;
    }
    // 品种中文名
    private String pinzhongStr;

    public String getPinzhongStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.曲线类别, getCurveType()) +"-"+SysDataUtil.getNameFromDataDict(DictCode.利率互换曲线, getCurveIntType())
                +"-"+getBaseIntCode()+"-"+SysDataUtil.getNameFromDataDict(DictCode.价格类型, getPriceType());
    }

    public void setPinzhongStr(String pinzhongStr) {
        this.pinzhongStr = pinzhongStr;
    }
}
