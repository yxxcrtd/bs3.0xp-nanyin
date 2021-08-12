/*
 * 文件名：CreditLinesDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.CreditUsingCustomerAjust;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.validator.ObjectLimitOccupation002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 
 * 主体额度占用调整
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupationDto
 * @since 1.0
 */
@PageId("ObjectLimitOccupation002")
@ValidLogic(targetClass = ObjectLimitOccupation002Validator.class, groups = {ValidateGroupEdit.class,
	    ValidateGroupDelete.class})
public class ObjectLimitOccupationDto  extends CreditUsingCustomerAjust implements ITableRow,ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    private int xuhao;
    
    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 额度名称
     */
    private String creditType;
    
    /**
     * 最大投资期限
     */
    private BigDecimal term;

    /**
     * 期限单位，日，月，年
     */
    private String termUnit;
    
    /**
     * 调整金额
     */
    private BigDecimal totleamount;
    
    /**
     * 用户名
     */
    private String realName;

    

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public BigDecimal getTotleamount() {
		return totleamount;
	}

	public void setTotleamount(BigDecimal totleamount) {
		this.totleamount = totleamount;
	}

	public String getTermUnitName() {
		return term + (this.getTermUnit() == null ? ""
				: SysDataUtil.getNameFromDataDict(DictCode.期限种类, this.getTermUnit()));
	}
    
	public String getAjustTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.额度调整类型, super.getAjustType());
    }
	
    /**
     * 取得 最大投资期限
     * @return 最大投资期限
     */
    public BigDecimal getTerm() {
        return term;
    }

    /**
     * 设置 最大投资期限
     * @param term 最大投资期限
     */
    public void setTerm(BigDecimal term) {
        this.term = term;
    }

    /**
     * 取得 期限单位，日，月，年
     * @return 期限单位，日，月，年
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * 设置 期限单位，日，月，年
     * @param termUnit 期限单位，日，月，年
     */
    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit == null ? null : termUnit.trim();
    }

    public String getCreditTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.额度分类, this.getCreditType());
    }


    public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getXuhao() {
		return xuhao;
	}

	public void setXuhao(int xuhao) {
		this.xuhao = xuhao;
	}

	/**
     * 画面处理模式
     */
    private String pageModel;

   /**
	* 选中列表
	*/
	 private List<ObjectLimitOccupationDto> selectedItem;
	
	 public List<ObjectLimitOccupationDto> getSelectedItem() {
		return selectedItem;
	 }

	 public void setSelectedItem(List<ObjectLimitOccupationDto> selectedItem) {
		this.selectedItem = selectedItem;
	 }
	
    public String getPageModel() {
		return pageModel;
	}

	public void setPageModel(String pageModel) {
		this.pageModel = pageModel;
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

	/**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    /**
     * 取得 授信标签代码
     * @return 授信标签代码
     */
    @PrimaryKey(value=Field.PK_1)
    public String getSeqno() {
        return super.getSeqno();
    }
    
    /**
     * 取得 调整类型
     * @return 调整类型
     */
    @NotEmpty(fieldName = "ObjectLimitOccupation001.ajustType", groups = {ValidateGroupEdit.class})
    public String getAjustType() {
        return super.getAjustType();
    }
    
    /**
     * 取得 占用日期
     * @return 占用日期
     */
    @NotEmpty(fieldName = "ObjectLimitOccupation001.cdate", groups = {ValidateGroupEdit.class})
    public Date getCdate() {
        return super.getCdate();
    }

    /**
     * 取得 占用日期
     * @return 占用日期
     */
    @NotEmpty(fieldName = "ObjectLimitOccupation001.rdate", groups = {ValidateGroupEdit.class})
    public Date getRdate() {
        return super.getRdate();
    }
    
}
