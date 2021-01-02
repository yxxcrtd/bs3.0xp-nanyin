package com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigDto;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.validator.FeeProvisionSetting001Validator;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.util.List;

/**
 * 费用预提设置DTO层
 * @author
 *
 */
@PageId(value = "FeeProvisionSetting002")
@ValidLogic(targetClass = FeeProvisionSetting001Validator.class)
public class FeeProvisionSetting001Dto extends TrdAccountmfConfigDto implements ProcessEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 费用明细数据
	 */
	private List<TrdAccountmfConfigdetailDto> trdAccountmfConfigdetail;

	public List<TrdAccountmfConfigdetailDto> getTrdAccountmfConfigdetail() {
		return trdAccountmfConfigdetail;
	}

	public void setTrdAccountmfConfigdetail(List<TrdAccountmfConfigdetailDto> trdAccountmfConfigdetail) {
		this.trdAccountmfConfigdetail = trdAccountmfConfigdetail;
	}

	/**
	 * 操作类型 - 工作流参数
	 */
	private String operateType;

	/**
	 * 选择数据
	 */
	private List<TrdAccountmfConfigDto> selectedItem;

	/**
	 * 要素id
	 */
	private String elementId;

	/**
	 * 画面处理模式
	 */
	private String pageModel;

	/**
	 * 状态 字符串
	 */
	private String dealStatusStr;

	/**
	 * 费用类型字符串
	 */
	private String feeTypeStr;

	/**
	 * 计提开始日字符串
	 */
	private String positionStartDateStr;

	/**
	 * 计提结束日字符串
	 */
	private String positionEndDateStr;

	public String getPositionStartDateStr() {
		return DateUtil.format(super.getPositionStartDate()) ;
	}

	public String getPositionEndDateStr() {
		return DateUtil.format(super.getPositionEndDate()) ;
	}

	public String getDealStatusStr() {
		return  SysDataUtil.getNameFromDataDict(DictCode.有效状态, super.getDealStatus());
	}

	public String getFeeTypeStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCode.费用类型, super.getFeeType());
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public List<TrdAccountmfConfigDto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<TrdAccountmfConfigDto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
	}

}
