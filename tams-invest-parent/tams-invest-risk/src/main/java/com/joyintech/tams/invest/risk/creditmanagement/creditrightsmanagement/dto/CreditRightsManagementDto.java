package com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.RiskWeight;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.risk.creditmanagement.creditrightsmanagement.validator.CreditRightsManagementValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;
@PageId("CreditRightsManagement002") 
@ValidLogic(targetClass = CreditRightsManagementValidator.class, groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})
public class CreditRightsManagementDto extends RiskWeight implements ProcessEntity{
	
	/**
	 * 修改前生效日
	 */
	private Date effDateOld;
	
	/**
	 * 被选中的数据
	 */
	private List<CreditRightsManagementDto> selectedItem;
	
	/**
	 * 操作类型
	 */
	private String operateType;
	
	/**
     * 页面提交模式
     */
    private String pageModel;
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private String elementId;

	public List<CreditRightsManagementDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<CreditRightsManagementDto> selectedItem) {
		this.selectedItem = selectedItem;
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
    
    public String getEffDateStr(){
    	return DateUtil.format(super.getEffDate());
    }
    
    public String getAssetTypeOneStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, super.getAssetTypeOne());
    }
    
    public String getAssetTypeTwoStr(){
        if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_债券.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.WIND一级分类_含债券一级分类等, super.getAssetTypeTwo());
        }
        else if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_基金.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.基金类资产类型, super.getAssetTypeTwo());
        }
        else if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_存款.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.存款类资产类型, super.getAssetTypeTwo());
        }
        else if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_计划类资产_净值型.getValue())
            || StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_计划类资产_利率型.getValue())
            || StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_计划类资产_货币型.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.计划类资产一级分类, super.getAssetTypeTwo());
        }
        else if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_项目类资产.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.项目类一级分类, super.getAssetTypeTwo());
        }
        else if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_质押式回购.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.回购类型, super.getAssetTypeTwo());
        }
        else if(StringUtil.equals(super.getAssetTypeOne(), DictCodeList.平安资产类型_拆借.getValue())){
            return SysDataUtil.getNameFromDataDict(DictCode.拆借类型, super.getAssetTypeTwo());
        }
        return null;
    }
    
    @PrimaryKey(value = Field.PK_2)
    @NotEmpty(fieldName = "CreditRightsManagement001.assetTypeOne", groups = {ValidateGroupEdit.class})
    public String getAssetTypeOne(){
    	return super.getAssetTypeOne();
    }
    
//    @NotEmpty(fieldName = "CreditRightsManagement001.assetTypeTwo", groups = {ValidateGroupEdit.class})
    public String getAssetTypeTwo(){
    	return super.getAssetTypeTwo();
    }
    
    @PrimaryKey(value = Field.PK_3)
    public String getAssetTypeTwoPK(){
    	return StringUtil.isEmpty(super.getAssetTypeTwo()) ? "PK_3":super.getAssetTypeTwo() ;
    }
    //add by gaopeng 增加风险权数和生效日必填验证 20191014 start 
	@NotEmpty(fieldName = "CreditRightsManagement001.effDate", groups = {ValidateGroupEdit.class})
	 public Date getEffDate() {
        return super.getEffDate();
    }
	@NotEmpty(fieldName = "CreditRightsManagement001.riskWeight", groups = {ValidateGroupEdit.class})
	 public BigDecimal getRiskWeight() {
	        return super.getRiskWeight();
	    }
	//add by gaopeng 增加风险权数和生效日必填验证 20191014 end 
    public String getSeqno(){
    	return super.getSeqno();
    }
	public Date getEffDateOld() {
		return effDateOld;
	}
	@PrimaryKey(value = Field.PK_1)
	public String getEffDateOldPK() {
		return DateUtil.format(super.getEffDate());
	}

	public void setEffDateOld(Date effDateOld) {
		this.effDateOld = effDateOld;
	}
    
    
}
