package com.joyintech.tams.invest.product.productduration.addreduce.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductAddReduceBaseDto;
import com.joyintech.tams.invest.product.productduration.addreduce.validator.WflTrdProductDealValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: code
 * @Package com.joyintech.tams.invest.product.productduration.addreduce.dto
 * @date Date : 2018年09月15日 15:29
 */
@PageId("FinProductAddReduce002")
@ValidLogic(targetClass = WflTrdProductDealValidator.class, groups = {ValidateGroupEdit.class, ValidateGroupDelete.class})
public class FinProductAddReduce002Dto extends ProductAddReduceBaseDto implements ProcessEntity {
	/**
	 * 是否违约
	 */
	private String sdefault;
	
	/**
	 * 违约金
	 */
	private BigDecimal sdefaultAmt;
    /**
     * 业务编号
     */
    private String busiCode;

    /**
     * 有效状态
     */
    private String effectStatus;
    
    
    public String getEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}

	/**
     * 获取 psString**@return psString
     */

    public String getPsString() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    /**
     * 汇总交易编号
     * 
     * @return 汇总交易编号
     */
    @PrimaryKey(PrimaryKey.Field.PK_1)
    @Override
    public String getTotleTradeId() {
        return super.getTotleTradeId();
    }

    /**
     * 金融产品代码
     */
    @Override
    @NotEmpty(fieldName = "FinProductAddReduce001.prodId", groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodId() {
        return super.getFinprodId();
    }

    /**
     * 交易类型
     */
    @Override
    @NotEmpty(fieldName = "FinProductAddReduce001.dealType", groups = {ValidateGroupEdit.class})
    @PrimaryKey(PrimaryKey.Field.PK_3)
    public String getPs() {
        return super.getPs();
    }

    /**
     * 确认日
     */
    @Override
    @NotEmpty(fieldName = "FinProductAddReduce001.cfmDate", groups = {ValidateGroupEdit.class})
    public String getTradeDate() {
        return super.getTradeDate();
    }

    /**
     * 币种
     */
    @Override
    @NotEmpty(fieldName = "FinProductAddReduce002.ccy", groups = {ValidateGroupEdit.class})
    public String getCcy() {
        return super.getCcy();
    }

    /**
     * 操作类型
     */
    private String operateType;

    private String elementId;

    /**
     * 获取 busiCode
     *
     * @return busiCode
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * 设置 busiCode
     *
     * @param busiCode busiCode
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 划款指令
     */
    private List<BussTrdTransferOrderDto> bussTrdTransferOrder;

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 elementId
     *
     * @return elementId
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 elementId
     *
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 获取 bussTrdTransferOrder
     *
     * @return bussTrdTransferOrder
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    /**
     * 设置 bussTrdTransferOrder
     *
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }

	public String getSdefault() {
		return sdefault;
	}

	public void setSdefault(String sdefault) {
		this.sdefault = sdefault;
	}

	public BigDecimal getSdefaultAmt() {
		return sdefaultAmt;
	}

	public void setSdefaultAmt(BigDecimal sdefaultAmt) {
		this.sdefaultAmt = sdefaultAmt;
	}
	//add by yejiejie 2019-2-21 start
    //减少客户数
    private BigDecimal customersReduceNum;

	public BigDecimal getCustomersReduceNum() {
		return customersReduceNum;
	}

	public void setCustomersReduceNum(BigDecimal customersReduceNum) {
		this.customersReduceNum = customersReduceNum;
	}
	//add by yejiejie 2019-2-21 end
    /**
     * 交易备注
     */
    private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
