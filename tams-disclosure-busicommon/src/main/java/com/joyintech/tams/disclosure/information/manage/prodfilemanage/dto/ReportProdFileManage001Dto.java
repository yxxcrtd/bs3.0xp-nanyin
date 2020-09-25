package com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.RepInvassetkindDto;
import com.joyintech.tams.common.base.entity.RepProdFileFeeInfoDto;
import com.joyintech.tams.common.base.entity.RepProdFileProdInfoDto;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.validator.ReportProdFileManage001Validator;

/**
 * 产品文件管理Dto层
 * @author hpp
 *
 */
@PageId("ReportProdFileManage002")
@ValidLogic(targetClass = ReportProdFileManage001Validator.class, groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ReportProdFileManage001Dto extends RepProdFileProdInfoDto implements ProcessEntity{

	List<RepInvassetkindDto> investLists;
	
	/**
     * 编号(投资种类)
     */
    private String code;
    /**
     * 比例上限
     */
    private String tLimit;

    /**
     * 比例下限
     */
    private String dLimit;
    
	//节假日是否开放申购
	private String purHolidayFlag;
	//节假日是否开放赎回
	private String redHolidayFlag;
//	//投资种类
//	private String investTypes;
//	//投资比例
//	private String investPropors;
	
	List<RepProdFileFeeInfoDto> feeLists;
	/**
     * 费用品种代码
     */
    private String feeId;
    /**
     * 费率
     */
    private BigDecimal feerate;

    /**
     * 基数类型，本金、资产净值、实收资本等
     */
    private String basicbill;

    /**
     * 计息基础，A/360，A/365
     */
    private String basis;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    /**
     * 选中的数据
     */
    private List<ReportProdFileManage001Dto > selectedItem;
    
    /**
     * 数据状态
     */
    private String dataStatusStr;
    /**
     * 募集起始日
     */
    private String raiseVdateStr;
    /**
     * 募集结束日
     */
    private String raiseEnddateStr;
    /**
     * 产品起息日
     */
    private String vdateStr;
    
	@PrimaryKey(PrimaryKey.Field.PK_1)
	@Override
	public String getProduuid() {
        return super.getProduuid();
    }
	
	public String getDataStatusStr() {
		return dataStatusStr;
	}

	public void setDataStatusStr(String dataStatusStr) {
		this.dataStatusStr = dataStatusStr;
	}

	public String getRaiseVdateStr() {
		return raiseVdateStr;
	}

	public void setRaiseVdateStr(String raiseVdateStr) {
		this.raiseVdateStr = raiseVdateStr;
	}

	public String getRaiseEnddateStr() {
		return raiseEnddateStr;
	}

	public void setRaiseEnddateStr(String raiseEnddateStr) {
		this.raiseEnddateStr = raiseEnddateStr;
	}

	public String getVdateStr() {
		return vdateStr;
	}

	public void setVdateStr(String vdateStr) {
		this.vdateStr = vdateStr;
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

	public List<ReportProdFileManage001Dto> getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(List<ReportProdFileManage001Dto> selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	public void setWorkflowId(String workflowId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		
	}

	public String getFeeId() {
		return feeId;
	}

	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}

	public BigDecimal getFeerate() {
		return feerate;
	}

	public void setFeerate(BigDecimal feerate) {
		this.feerate = feerate;
	}

	public String getBasicbill() {
		return basicbill;
	}

	public void setBasicbill(String basicbill) {
		this.basicbill = basicbill;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

//	public String getInvestTypes() {
//		return investTypes;
//	}
//
//	public void setInvestTypes(String investTypes) {
//		this.investTypes = investTypes;
//	}
//
//	public String getInvestPropors() {
//		return investPropors;
//	}
//
//	public void setInvestPropors(String investPropors) {
//		this.investPropors = investPropors;
//	}

//	public List<RepProdFileFeeInfoDto> getInvestLists() {
//		return investLists;
//	}
//
//	public void setInvestLists(List<RepProdFileFeeInfoDto> investLists) {
//		this.investLists = investLists;
//	}

	public List<RepProdFileFeeInfoDto> getFeeLists() {
		return feeLists;
	}

	public void setFeeLists(List<RepProdFileFeeInfoDto> feeLists) {
		this.feeLists = feeLists;
	}

	public String getPurHolidayFlag() {
		return purHolidayFlag;
	}

	public void setPurHolidayFlag(String purHolidayFlag) {
		this.purHolidayFlag = purHolidayFlag;
	}

	public String getRedHolidayFlag() {
		return redHolidayFlag;
	}

	public void setRedHolidayFlag(String redHolidayFlag) {
		this.redHolidayFlag = redHolidayFlag;
	}

	public List<RepInvassetkindDto> getInvestLists() {
		return investLists;
	}

	public void setInvestLists(List<RepInvassetkindDto> investLists) {
		this.investLists = investLists;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String gettLimit() {
		return tLimit;
	}

	public void settLimit(String tLimit) {
		this.tLimit = tLimit;
	}

	public String getdLimit() {
		return dLimit;
	}

	public void setdLimit(String dLimit) {
		this.dLimit = dLimit;
	}
	
}
