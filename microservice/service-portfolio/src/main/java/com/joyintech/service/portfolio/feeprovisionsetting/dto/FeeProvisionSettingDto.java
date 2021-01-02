package com.joyintech.service.portfolio.feeprovisionsetting.dto;

import com.joyintech.tams.common.base.entity.TrdAccountmfConfigDto;
import com.joyintech.tams.common.base.entity.TrdAccountmfConfigdetailDto;

import java.util.List;

/**
 * 费用预提设置DTO层
 * @author 
 *
 */
public class FeeProvisionSettingDto extends TrdAccountmfConfigDto{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public List<TrdAccountmfConfigdetailDto> getTrdAccountmfConfigdetail() {
		return trdAccountmfConfigdetail;
	}

	public void setTrdAccountmfConfigdetail(List<TrdAccountmfConfigdetailDto> trdAccountmfConfigdetail) {
		this.trdAccountmfConfigdetail = trdAccountmfConfigdetail;
	}

	/**
	 * 费用明细数据
	 */
	private List<TrdAccountmfConfigdetailDto> trdAccountmfConfigdetail;

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
