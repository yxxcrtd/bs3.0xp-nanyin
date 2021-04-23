package com.joyintech.tams.invest.basicdata.mstseatinfo.dto;

import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.basicdata.mstseatinfo.validator.MstSeatInfo001Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;

@PageId("MstSeatInfo002")
@ValidLogic(targetClass = MstSeatInfo001Validator.class, groups = { ValidateGroupEdit.class,
		ValidateGroupDelete.class })
public class MstSeatInfoDto extends MstSeatDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String operateType;
	
	/**
	 * 
	 */
	private String  elementId;



	private transient List<MstSeatInfoDto> selectedItem;

	@Override
	@NotEmpty(fieldName = "MstSeatInfo001.seatId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSeatInfo001.seatId", max = 50, groups = {ValidateGroupEdit.class})
	@PrimaryKey(value=Field.PK_1)
	public String getSeatId() {
		return super.getSeatId();
	}
	@Override
	@NotEmpty(fieldName = "MstSeatInfo002.customerId", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSeatInfo002.customerId", max = 32, groups = {ValidateGroupEdit.class})
	public String getCustomerId() {
		return super.getCustomerId();
	}
	@Override
	@NotEmpty(fieldName = "MstSeatInfo002.tradeMarket", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "MstSeatInfo002.tradeMarket", max = 50, groups = {ValidateGroupEdit.class})
	public String getTradeMarket() {
		return super.getTradeMarket();
	}
	
	@Override
    @Length(fieldName = "MstSeatInfo002.remark", max = 1024, groups = {ValidateGroupEdit.class})
	public String getRemark() {
		return super.getRemark();
	}
	

	public List<MstSeatInfoDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<MstSeatInfoDto> selectedItem) {
		this.selectedItem = selectedItem;
	}



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
	
	public String getTradeMarketName() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
	}

}
