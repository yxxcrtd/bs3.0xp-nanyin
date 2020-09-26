package com.joyintech.tams.valuation.batch.positionmake.dto;
/**
 * @author yanya
 * @create 2019-12-12 14:55
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 * @ClassName PtlSecReceivablesDto
 * @Description: 应收款科目余额Dto
 * @Author yanya
 * @Date 2019/12/25
 * @Version V1.0
 **/
public class PtlSecReceivablesDto extends BaseEntity {

    private static final long serialVersionUID = -2927471124662579942L;

    /** 组合代码**/
    private String portfolioId;
    /** 通道ID**/
    private String channelId;
    /** 账套代码**/
    private String booksetId;
    /** 业务明细代码**/
    private String bokDetailId;
    /** 金融产品代码**/
    private String finprodId;
    /** 金融产品类型**/
    private String finprodType;
    /** 投资目的**/
    private String invAim;
    /** 分支序号**/
    private Long branch;
    /** 原币**/
    private String oCcy;
    /** 余额方向（发生日）**/
    private String balFlagHappenDate;
    /**
     * 原币余额（发生日）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal oAmtHappenDate;
    /** 基础科目性子**/
    private String baseSubjectNature;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getInvAim() {
        return invAim;
    }

    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }

    public String getoCcy() {
        return oCcy;
    }

    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    public String getBalFlagHappenDate() {
        return balFlagHappenDate;
    }

    public void setBalFlagHappenDate(String balFlagHappenDate) {
        this.balFlagHappenDate = balFlagHappenDate;
    }

    public BigDecimal getoAmtHappenDate() {
        return oAmtHappenDate;
    }

    public void setoAmtHappenDate(BigDecimal oAmtHappenDate) {
        this.oAmtHappenDate = oAmtHappenDate;
    }

    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }
}
