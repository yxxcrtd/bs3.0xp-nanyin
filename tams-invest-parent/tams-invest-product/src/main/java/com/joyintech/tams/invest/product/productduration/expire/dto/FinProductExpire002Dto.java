/*
 * 文件名：FinProductExpire002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.expire.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.productduration.expire.validator.FinProductExpire002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 产品到期确认
 * 
 * @author zhangliang
 * @version 1.0 2019年1月9日
 * @see FinProductExpire002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = FinProductExpire002Validator.class)
@PageId("FinProductExpire002")
public class FinProductExpire002Dto extends FinProductExpire001Dto implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 3019330667931939050L;

    /**
     * 交易类型：赎回、到期、提前终止
     */
    private String ps;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    /**
     * 元素Id
     */
    private String elementId;
    
    /**
     * 撤销原因
     */
    private String revokeReason;
    
    /**
     * 交易编号
     */
    private String tradeId;
    
    /**
     * 总划款金额
     */
    private BigDecimal totalTransAmt;
    
    /**
     * 总费用金额
     */
    private BigDecimal totalFeeAmt;
    
    /**
     * 分行
     */
    private String branchInfo;
    
    /**
     * 到期客户赎回金额
     */
//    @NotEmpty(fieldName = "FinProductExpire002.mInfo.mCusRevAmt")
    private BigDecimal mCusRevAmt;
    
    /**
     * 本期客户赎回本金 
     */
    @NotEmpty(fieldName = "FinProductExpire002.mInfo.reduceAmt")
    private BigDecimal reduceAmt;
     
    /**
     * 应付客户收益
     */
    private BigDecimal cusProfit;
    
    /**
     * 实付客户收益
     */
    @NotEmpty(fieldName = "FinProductExpire002.mInfo.actCusProfit")
    private BigDecimal actCusProfit;
    
    /**
     * 到期信息列表
     */
    @Valid
    private transient List<ReduceInfoDto> finProductExpireMInfoDtoList;
    
    /**
     * 费用信息列表
     */
    @Valid
    private transient List<FinProductExpireFeeInfoDto> finProductExpireFeeInfoDtoList;
    
    /**
     * 分行信息列表
     */
    @Valid
    private transient List<FinProductExpireBranchInfoDto> finProductExpireBranchInfoDtoList;
    
    /**
     * 划款指令
     */
    private transient List<BussTrdTransferOrderDto> bussTrdTransferOrder;
    /**
     * 交易备注
     */
    private String remark;
    /**
     * 目标客户一级分类
     */
    private String targetCustomer1;
    
    private FinProductExpireTrdDto finProductExpireTrdDto;
    
    /**
     * 由于框架原因流程备注中引用不到finproduc中的finprodMarketId，定义一个变量起转换作用。
     */
    private String commentMarketId;
    
    public String getTargetCustomer1() {
		return targetCustomer1;
	}


	public void setTargetCustomer1(String targetCustomer1) {
		this.targetCustomer1 = targetCustomer1;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
     * 获取 mCusRevAmt
     * @return mCusRevAmt.
     */
    public BigDecimal getmCusRevAmt() {
        return mCusRevAmt;
    }

    
    /**
     * 设置 mCusRevAmt
     * @param mCusRevAmt mCusRevAmt
     */
    public void setmCusRevAmt(BigDecimal mCusRevAmt) {
        this.mCusRevAmt = mCusRevAmt;
    }

    
    /**
     * 获取 cusProfit
     * @return cusProfit.
     */
    public BigDecimal getCusProfit() {
        return cusProfit;
    }

    
    /**
     * 设置 cusProfit
     * @param cusProfit cusProfit
     */
    public void setCusProfit(BigDecimal cusProfit) {
        this.cusProfit = cusProfit;
    }

    
    /**
     * 获取 actCusProfit
     * @return actCusProfit.
     */
    public BigDecimal getActCusProfit() {
        return actCusProfit;
    }

    
    /**
     * 设置 actCusProfit
     * @param actCusProfit actCusProfit
     */
    public void setActCusProfit(BigDecimal actCusProfit) {
        this.actCusProfit = actCusProfit;
    }

    
    /**
     * 获取 finProductExpireMInfoDtoList
     * @return finProductExpireMInfoDtoList.
     */
    public List<ReduceInfoDto> getFinProductExpireMInfoDtoList() {
        return finProductExpireMInfoDtoList;
    }

    
    /**
     * 设置 finProductExpireMInfoDtoList
     * @param finProductExpireMInfoDtoList finProductExpireMInfoDtoList
     */
    public void setFinProductExpireMInfoDtoList(List<ReduceInfoDto> finProductExpireMInfoDtoList) {
        this.finProductExpireMInfoDtoList = finProductExpireMInfoDtoList;
    }

    
    /**
     * 获取 finProductExpireFeeInfoDtoList
     * @return finProductExpireFeeInfoDtoList.
     */
    public List<FinProductExpireFeeInfoDto> getFinProductExpireFeeInfoDtoList() {
        return finProductExpireFeeInfoDtoList;
    }

    
    /**
     * 设置 finProductExpireFeeInfoDtoList
     * @param finProductExpireFeeInfoDtoList finProductExpireFeeInfoDtoList
     */
    public void setFinProductExpireFeeInfoDtoList(List<FinProductExpireFeeInfoDto> finProductExpireFeeInfoDtoList) {
        this.finProductExpireFeeInfoDtoList = finProductExpireFeeInfoDtoList;
    }

    
    /**
     * 获取 finProductExpireBranchInfoDtoList
     * @return finProductExpireBranchInfoDtoList.
     */
    public List<FinProductExpireBranchInfoDto> getFinProductExpireBranchInfoDtoList() {
        return finProductExpireBranchInfoDtoList;
    }

    
    /**
     * 设置 finProductExpireBranchInfoDtoList
     * @param finProductExpireBranchInfoDtoList finProductExpireBranchInfoDtoList
     */
    public void setFinProductExpireBranchInfoDtoList(List<FinProductExpireBranchInfoDto> finProductExpireBranchInfoDtoList) {
        this.finProductExpireBranchInfoDtoList = finProductExpireBranchInfoDtoList;
    }

    
    /**
     * 获取 bussTrdTransferOrder
     * @return bussTrdTransferOrder.
     */
    public List<BussTrdTransferOrderDto> getBussTrdTransferOrder() {
        return bussTrdTransferOrder;
    }

    
    /**
     * 设置 bussTrdTransferOrder
     * @param bussTrdTransferOrder bussTrdTransferOrder
     */
    public void setBussTrdTransferOrder(List<BussTrdTransferOrderDto> bussTrdTransferOrder) {
        this.bussTrdTransferOrder = bussTrdTransferOrder;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @NotEmpty(fieldName = "FinProductExpire002.payDate")
    public Date getPayDate() {
        return super.getPayDate();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @PrimaryKey(Field.PK_1)
    public String getFinprodId() {
        return super.getFinprodId();
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }


    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    
    /**
     * 获取 tradeId
     * @return tradeId.
     */
    public String getTradeId() {
        return tradeId;
    }


    
    /**
     * 设置 tradeId
     * @param tradeId tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }


    
    /**
     * 获取 totalTransAmt
     * @return totalTransAmt.
     */
    public BigDecimal getTotalTransAmt() {
        return totalTransAmt;
    }


    
    /**
     * 设置 totalTransAmt
     * @param totalTransAmt totalTransAmt
     */
    public void setTotalTransAmt(BigDecimal totalTransAmt) {
        this.totalTransAmt = totalTransAmt;
    }


    
    /**
     * 获取 totalFeeAmt
     * @return totalFeeAmt.
     */
    public BigDecimal getTotalFeeAmt() {
        return totalFeeAmt;
    }


    
    /**
     * 设置 totalFeeAmt
     * @param totalFeeAmt totalFeeAmt
     */
    public void setTotalFeeAmt(BigDecimal totalFeeAmt) {
        this.totalFeeAmt = totalFeeAmt;
    }


    
    /**
     * 获取 branch
     * @return branch.
     */
    public String getBranchInfo() {
        return branchInfo;
    }


    
    /**
     * 设置 branch
     * @param branchInfo branchInfo
     */
    public void setBranchInfo(String branchInfo) {
        this.branchInfo = branchInfo;
    }


    
    /**
     * 获取 revokeReason
     * @return revokeReason.
     */
    public String getRevokeReason() {
        return revokeReason;
    }


    
    /**
     * 设置 revokeReason
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    @PrimaryKey(Field.PK_3)
	public String getPs() {
        if(StringUtil.isNotEmpty(ps)){
            return ps;
        }
		return getDealType();
	}


	public void setPs(String ps) {
		this.ps = ps;
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
     * 获取 reduceAmt
     * @return reduceAmt.
     */
    public BigDecimal getReduceAmt() {
        return reduceAmt;
    }


    
    /**
     * 设置 reduceAmt
     * @param reduceAmt reduceAmt
     */
    public void setReduceAmt(BigDecimal reduceAmt) {
        this.reduceAmt = reduceAmt;
    }


    
    /**
     * 获取 finProductExpireTrdDto
     * @return finProductExpireTrdDto.
     */
    public FinProductExpireTrdDto getFinProductExpireTrdDto() {
        return finProductExpireTrdDto;
    }


    
    /**
     * 设置 finProductExpireTrdDto
     * @param finProductExpireTrdDto finProductExpireTrdDto
     */
    public void setFinProductExpireTrdDto(FinProductExpireTrdDto finProductExpireTrdDto) {
        this.finProductExpireTrdDto = finProductExpireTrdDto;
    }


	public String getCommentMarketId() {
		return commentMarketId;
	}


	public void setCommentMarketId(String commentMarketId) {
		this.commentMarketId = commentMarketId;
	}

    /**
     * 取得 收益类型(风险接口使用,默认收益型产品)
     * @return 收益类型
     */
	@Override
    public String getProfitType() {
        return StringUtil.isNotEmpty(super.getProfitType())?super.getProfitType():DictCodeList.收益类型_收益型.getValue();
    }


}
