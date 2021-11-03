/*
 * 文件名：BussCombDistribtDto.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.bankuplink.bond.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="组合分配dto",description="组合分配dto")
public class CombDistribtDto {


    /**
     * 指令代码
     */
	@ApiModelProperty(name="entrustId",value="指令代码",example="", required = true)
    private String entrustId;
    
    /**
     * 组合代码
     */
	@ApiModelProperty(name="portfolioId",value="组合代码",example="", required = true)
    private String portfolioId;
    
    /**
     * 组合名称
     */
	@ApiModelProperty(name="portfolioName",value="组合名称",example="", required = true)
    private String portfolioName;
    
    /**
     * 券面总额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    @ApiModelProperty(name="entrustAmt",value="券面总额",example="", required = true)
    private BigDecimal entrustAmt;
    
    /**
     * 本方券券面总额
     */
    /**
     * 融入券券面总额
     */
    /**
     * 卖空购回券面总额
     */
    /**
     * 交易手续费
     */
    
    /**
     * 结算服务费
     */
    
    
    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @ApiModelProperty(name="cpriceAmt",value="净价总额",example="", required = true)
    private BigDecimal cpriceAmt;
    
    /**
     * 应计利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @ApiModelProperty(name="intAmt",value="应计利息总额",example="", required = true)
    private BigDecimal intAmt;
    
    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    @ApiModelProperty(name="fpriceAmt",value="全价总额",example="", required = true)
    private BigDecimal fpriceAmt;
    
    /**
     * 价格模式
     */
    @ApiModelProperty(name="priceModel",value="价格模式",example="", required = true)
    private String priceModel;
    
    /**
     *业务模式
     */
    @ApiModelProperty(name="businessModel",value="业务模式",example="", required = true)
    private String businessModel;
    
    /**
     * 证券账户
     */
    @ApiModelProperty(name="secManageAcctId",value="证券账户",example="", required = true)
    private String secManageAcctId;
    
    /**
     * 证券账户名称
     */
    @ApiModelProperty(name="secAcctName",value="证券账户名称",example="", required = true)
    private String secAcctName;
    
    /**
    * 净价
    */
   @JsonSerialize(using=BigDecimalSerializer.class)
   @ApiModelProperty(name="price",value="净价",example="", required = true)
   private BigDecimal price;
   
   /**
    * 到期收益率
    */
   @JsonSerialize(using = BigDecimalSerializer.class)
   @ApiModelProperty(name="matureYield",value="到期收益率",example="", required = true)
   private BigDecimal matureYield;
   
   /**
    * 行权收益率
    */
   @ApiModelProperty(name="exerYield",value="行权收益率",example="", required = true)
   private BigDecimal exerYield;
    
    
}
