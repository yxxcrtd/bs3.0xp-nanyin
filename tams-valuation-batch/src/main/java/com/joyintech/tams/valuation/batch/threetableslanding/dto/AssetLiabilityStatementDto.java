/*
 * 文件名：AssetLiabilityStatementQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.threetableslanding.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.valuation.batch.threetableslanding.common.AssetLiabilityStatementEnum;

/**
 * 资产负债表
 * 
 * @author zhangwei
 * @version 1.0 2018年9月18日
 * @see AssetLiabilityStatementDto
 * @since 1.0
 */
public class AssetLiabilityStatementDto extends NeedToWriteEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 项目名
     */
    private String itemName;

    /**
     * 项目Code
     */
    private String itemCode;

    /**
     * 项目类型
     */
    private String itemType;

    /**
     * 资产余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal balance = BigDecimal.ZERO;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 科目号
     */
    private String subjectNo;

    /**
     * 科目类型列表
     */
    private List<AssetLiabilityStatementEnum> subjectTypeList = new ArrayList<>();

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 余额日期
     */
    private Date balanceDate;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 使用金额类型
     */
    private String useAmtType;

    /**
     * 原币余额
     */
/*    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oAmt;*/

    /**
     * 原币余额贷方
     */
/*    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oCAmt;*/

    /**
     * 原币余额借方
     */
/*
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal oDAmt;
*/
    /**
     * 本位币余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAmt;

    /**
     * 本位币余额贷方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCAmt;

    /**
     * 本位币余额借方
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bDAmt;
    public AssetLiabilityStatementDto() {
        super();
    }

    public AssetLiabilityStatementDto(String itemName, String itemCode) {
        super();
        this.itemName = itemName;
        this.itemCode = itemCode;
    }

    /**
     * @param balFlag 余额方向
     * @param useAmtType 金额类型
     * @param assetType 资产类型
     * @param enums 枚举值
     */
    public AssetLiabilityStatementDto(String balFlag, String useAmtType, String assetType,
                                      AssetLiabilityStatementEnum... enums) {
        super();
        this.balFlag = balFlag;
        this.useAmtType = useAmtType;
        this.subjectTypeList = Arrays.asList(enums);
        this.itemName = enums[0].getAssetName();
        this.itemCode = enums[0].getAssetCode();
        this.itemType = assetType;
    }

    public String getUseAmtType() {
        return useAmtType;
    }

    public void setUseAmtType(String useAmtType) {
        this.useAmtType = useAmtType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取 资产余额
     * 
     * @return 资产余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置 资产余额
     * 
     * @param balance 资产余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(Date balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getBalFlag() {
        return balFlag;
    }

    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    public BigDecimal getbAmt() {
        return bAmt;
    }

    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }

    public BigDecimal getbCAmt() {
        return bCAmt;
    }

    public void setbCAmt(BigDecimal bCAmt) {
        this.bCAmt = bCAmt;
    }

    public BigDecimal getbDAmt() {
        return bDAmt;
    }

    public void setbDAmt(BigDecimal bDAmt) {
        this.bDAmt = bDAmt;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<AssetLiabilityStatementEnum> getSubjectTypeList() {
        return subjectTypeList;
    }

    public void setSubjectTypeList(List<AssetLiabilityStatementEnum> subjectTypeList) {
        this.subjectTypeList = subjectTypeList;
    }

    /**
     * 获取 项目Code
     * 
     * @return 项目Code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * 设置 项目Code
     * 
     * @param itemCode 项目Code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * 获取 项目类型
     * 
     * @return 项目类型
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 设置 项目类型
     * 
     * @param itemType 项目类型
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

}
