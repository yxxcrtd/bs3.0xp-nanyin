package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RepAssetAstBill extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 是否为收/受益权
     */
    private String repIsRight;

    /**
     * 是否属于买入返售
     */
    private String repIsResale;

    /**
     * 类型
     */
    private String repAstBillType;

    /**
     * 数量
     */
    private Integer repNum;

    /**
     * 合计金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repAmtSum;

    /**
     * 加权剩余期限(天)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repRemainTerm;

    /**
     * 最长剩余期限(天)
     */
    private Integer repMaxRemainTerm;

    /**
     * 最短剩余期限(天)
     */
    private Integer repMinRemainTerm;

    /**
     * 起息日
     */
    private Date repVdate;

    /**
     * 到期日
     */
    private Date repMdate;

    /**
     * 行业
     */
    private String repIndustry;

    /**
     * 行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 贴现利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repDiscRate;

    /**
     * 备注
     */
    private String repRemark;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 是否为收/受益权
     * @return 是否为收/受益权
     */
    public String getRepIsRight() {
        return repIsRight;
    }

    /**
     * 设置 是否为收/受益权
     * @param repIsRight 是否为收/受益权
     */
    public void setRepIsRight(String repIsRight) {
        this.repIsRight = repIsRight == null ? null : repIsRight.trim();
    }

    /**
     * 取得 是否属于买入返售
     * @return 是否属于买入返售
     */
    public String getRepIsResale() {
        return repIsResale;
    }

    /**
     * 设置 是否属于买入返售
     * @param repIsResale 是否属于买入返售
     */
    public void setRepIsResale(String repIsResale) {
        this.repIsResale = repIsResale == null ? null : repIsResale.trim();
    }

    /**
     * 取得 类型
     * @return 类型
     */
    public String getRepAstBillType() {
        return repAstBillType;
    }

    /**
     * 设置 类型
     * @param repAstBillType 类型
     */
    public void setRepAstBillType(String repAstBillType) {
        this.repAstBillType = repAstBillType == null ? null : repAstBillType.trim();
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public Integer getRepNum() {
        return repNum;
    }

    /**
     * 设置 数量
     * @param repNum 数量
     */
    public void setRepNum(Integer repNum) {
        this.repNum = repNum;
    }

    /**
     * 取得 合计金额
     * @return 合计金额
     */
    public BigDecimal getRepAmtSum() {
        return repAmtSum;
    }

    /**
     * 设置 合计金额
     * @param repAmtSum 合计金额
     */
    public void setRepAmtSum(BigDecimal repAmtSum) {
        this.repAmtSum = repAmtSum;
    }

    /**
     * 取得 加权剩余期限(天)
     * @return 加权剩余期限(天)
     */
    public BigDecimal getRepRemainTerm() {
        return repRemainTerm;
    }

    /**
     * 设置 加权剩余期限(天)
     * @param repRemainTerm 加权剩余期限(天)
     */
    public void setRepRemainTerm(BigDecimal repRemainTerm) {
        this.repRemainTerm = repRemainTerm;
    }

    /**
     * 取得 最长剩余期限(天)
     * @return 最长剩余期限(天)
     */
    public Integer getRepMaxRemainTerm() {
        return repMaxRemainTerm;
    }

    /**
     * 设置 最长剩余期限(天)
     * @param repMaxRemainTerm 最长剩余期限(天)
     */
    public void setRepMaxRemainTerm(Integer repMaxRemainTerm) {
        this.repMaxRemainTerm = repMaxRemainTerm;
    }

    /**
     * 取得 最短剩余期限(天)
     * @return 最短剩余期限(天)
     */
    public Integer getRepMinRemainTerm() {
        return repMinRemainTerm;
    }

    /**
     * 设置 最短剩余期限(天)
     * @param repMinRemainTerm 最短剩余期限(天)
     */
    public void setRepMinRemainTerm(Integer repMinRemainTerm) {
        this.repMinRemainTerm = repMinRemainTerm;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getRepVdate() {
        return repVdate;
    }

    /**
     * 设置 起息日
     * @param repVdate 起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepVdate(Date repVdate) {
        this.repVdate = repVdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getRepMdate() {
        return repMdate;
    }

    /**
     * 设置 到期日
     * @param repMdate 到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepMdate(Date repMdate) {
        this.repMdate = repMdate;
    }

    /**
     * 取得 行业
     * @return 行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 行业
     * @param repIndustry 行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 行业二级分类
     * @return 行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 行业二级分类
     * @param repIndustryLevel2 行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 贴现利率
     * @return 贴现利率
     */
    public BigDecimal getRepDiscRate() {
        return repDiscRate;
    }

    /**
     * 设置 贴现利率
     * @param repDiscRate 贴现利率
     */
    public void setRepDiscRate(BigDecimal repDiscRate) {
        this.repDiscRate = repDiscRate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }
}