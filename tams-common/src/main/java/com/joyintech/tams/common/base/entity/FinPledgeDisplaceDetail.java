package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;

import java.math.BigDecimal;

public class FinPledgeDisplaceDetail extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal branch;

    /**
     * 置换方向
     */
    private String tradeType;

    /**
     * 换出面额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeDisplaceAmt;

    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scale;

    /**
     * 首次净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal firstNetprice;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 业务编号
     * @return 业务编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 业务编号
     * @param tradeId 业务编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public BigDecimal getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(BigDecimal branch) {
        this.branch = branch;
    }

    /**
     * 取得 置换方向
     * @return 置换方向
     */
    public String getTradeType() {
        return tradeType;
    }
    public String getTradeTypeName() {

        if(tradeType.equals(DictCodeListExt3.质押券置换_置换方向_换入.getValue()))
        {
            return DictCodeListExt3.质押券置换_置换方向_换入.getCodeName();
        }
        if(tradeType.equals(DictCodeListExt3.质押券置换_置换方向_换出.getValue()))
        {
            return DictCodeListExt3.质押券置换_置换方向_换出.getCodeName();
        }
        return tradeType;
    }
    /**
     * 设置 置换方向
     * @param tradeType 置换方向
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 换出面额
     * @return 换出面额
     */
    public BigDecimal getPledgeDisplaceAmt() {
        return pledgeDisplaceAmt;
    }

    /**
     * 设置 换出面额
     * @param pledgeDisplaceAmt 换出面额
     */
    public void setPledgeDisplaceAmt(BigDecimal pledgeDisplaceAmt) {
        this.pledgeDisplaceAmt = pledgeDisplaceAmt;
    }

    /**
     * 取得 折算比例
     * @return 折算比例
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 设置 折算比例
     * @param scale 折算比例
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }

    /**
     * 取得 首次净价
     * @return 首次净价
     */
    public BigDecimal getFirstNetprice() {
        return firstNetprice;
    }

    /**
     * 设置 首次净价
     * @param firstNetprice 首次净价
     */
    public void setFirstNetprice(BigDecimal firstNetprice) {
        this.firstNetprice = firstNetprice;
    }
}