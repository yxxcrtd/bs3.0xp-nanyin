package com.joyintech.tams.invest.batch.overduesecpositionforward.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OverdueSecPositionForwardDto extends NeedOperateEntity {
    /**
     * 资产到期日
     */
    private Date assetMdate;
    /**
     * 临时key
     */
    private String id;
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 分支类型
     */
    private String branchType;

    /**
     * 分类8
     */
    private String type8;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 计划支付日
     */
    private Date payDate;

    /**
     *  中间还本现金流类型
     */
    private  String cashTypeMid;

    /**
     * 中间还本计划支付日
     */
    private  Date payDateMid;

    /**
     * 中间还本计算结束日
     */
    private  Date calMDateMid;

    /**
     * 中间还本是否支付利息
     */
    private String isPayIntMid;

    /**
     * 投资目的
     */
    private  String invAim;

    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 证券账户entity
     */
    private List<SecManageAcctInfoDto> secManageAcctInfoDtoList;

    /**
     * 获取 金融产品代码
     *
     * @return finprodId 金融产品代码
     */
    public String getFinprodId() {
        return this.finprodId;
    }

    /**
     * 设置 金融产品代码
     *
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 分支序号
     *
     * @return branch 分支序号
     */
    public Long getBranch() {
        return this.branch;
    }

    /**
     * 设置 分支序号
     *
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 交易币种
     *
     * @return ccy 交易币种
     */
    public String getCcy() {
        return this.ccy;
    }

    /**
     * 设置 交易币种
     *
     * @param ccy 交易币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 到期日
     *
     * @return mdate 到期日
     */
    public Date getMdate() {
        return this.mdate;
    }

    /**
     * 设置 到期日
     *
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 分支类型
     *
     * @return branchType 分支类型
     */
    public String getBranchType() {
        return this.branchType;
    }

    /**
     * 设置 分支类型
     *
     * @param branchType 分支类型
     */
    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    /**
     * 获取 分类8
     *
     * @return type8 分类8
     */
    public String getType8() {
        return this.type8;
    }

    /**
     * 设置 分类8
     *
     * @param type8 分类8
     */
    public void setType8(String type8) {
        this.type8 = type8;
    }

    /**
     * 获取 现金流类型
     *
     * @return cashType 现金流类型
     */
    public String getCashType() {
        return this.cashType;
    }

    /**
     * 设置 现金流类型
     *
     * @param cashType 现金流类型
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    /**
     * 获取 计划支付日
     *
     * @return payDate 计划支付日
     */
    public Date getPayDate() {
        return this.payDate;
    }

    /**
     * 设置 计划支付日
     *
     * @param payDate 计划支付日
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }


    /**
     * 获取 投资目的
     *
     * @return invAim 投资目的
     */
    public String getInvAim() {
        return this.invAim;
    }

    /**
     * 设置 投资目的
     *
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * 获取 组合代码
     *
     * @return portfolioId 组合代码
     */
    public String getPortfolioId() {
        return this.portfolioId;
    }

    /**
     * 设置 组合代码
     *
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 证券账户entity
     *
     * @return secManageAcctInfoDtoList 证券账户entity
     */
    public List<SecManageAcctInfoDto> getSecManageAcctInfoDtoList() {
        return this.secManageAcctInfoDtoList;
    }

    /**
     * 设置 证券账户entity
     *
     * @param secManageAcctInfoDtoList 证券账户entity
     */
    public void setSecManageAcctInfoDtoList(List<SecManageAcctInfoDto> secManageAcctInfoDtoList) {
        this.secManageAcctInfoDtoList = secManageAcctInfoDtoList;
    }

    /**
     * 获取 临时key
     *
     * @return id 临时key
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置 临时key
     *
     * @param id 临时key
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 中间还本现金流类型
     *
     * @return cashTypeMid 中间还本现金流类型
     */
    public String getCashTypeMid() {
        return this.cashTypeMid;
    }

    /**
     * 设置 中间还本现金流类型
     *
     * @param cashTypeMid 中间还本现金流类型
     */
    public void setCashTypeMid(String cashTypeMid) {
        this.cashTypeMid = cashTypeMid;
    }

    /**
     * 获取 中间还本计划支付日
     *
     * @return payDateMid 中间还本计划支付日
     */
    public Date getPayDateMid() {
        return this.payDateMid;
    }

    /**
     * 设置 中间还本计划支付日
     *
     * @param payDateMid 中间还本计划支付日
     */
    public void setPayDateMid(Date payDateMid) {
        this.payDateMid = payDateMid;
    }

    /**
     * 获取 中间还本计算结束日
     *
     * @return calMDateMid 中间还本计算结束日
     */
    public Date getCalMDateMid() {
        return this.calMDateMid;
    }

    /**
     * 设置 中间还本计算结束日
     *
     * @param calMDateMid 中间还本计算结束日
     */
    public void setCalMDateMid(Date calMDateMid) {
        this.calMDateMid = calMDateMid;
    }

    /**
     * 获取 中间还本是否支付利息
     *
     * @return isPayIntMid 中间还本是否支付利息
     */
    public String getIsPayIntMid() {
        return this.isPayIntMid;
    }

    /**
     * 设置 中间还本是否支付利息
     *
     * @param isPayIntMid 中间还本是否支付利息
     */
    public void setIsPayIntMid(String isPayIntMid) {
        this.isPayIntMid = isPayIntMid;
    }

    /**
     * 获取 资产到期日
     *
     * @return assetMdate 资产到期日
     */
    public Date getAssetMdate() {
        return this.assetMdate;
    }

    /**
     * 设置 资产到期日
     *
     * @param assetMdate 资产到期日
     */
    public void setAssetMdate(Date assetMdate) {
        this.assetMdate = assetMdate;
    }
}
