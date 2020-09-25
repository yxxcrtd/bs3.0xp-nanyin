package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstBillSub extends ChbAstBillSubKey {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 二级资产类别 参照数据字典
     */
    private String assetType2;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 交易流通场所  参照数据字典
     */
    private String tradeMarket;

    /**
     * 是否为收/受益权 参照数据字典
     */
    private String isRight;

    /**
     * 是否属于买入返售 参照数据字典
     */
    private String isResale;

    /**
     * 类型 参照数据字典
     */
    private String type;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 合计金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amtSum;

    /**
     * 加权剩余期限(天)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainTerm;

    /**
     * 最长剩余期限(天)
     */
    private Integer maxRemainTerm;

    /**
     * 最短剩余期限(天)
     */
    private Integer minRemainTerm;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 行业 参照数据字典
     */
    private String industry;

    /**
     * 行业二级分类 参照数据字典
     */
    private String industryLevel2;

    /**
     * 贴现利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal discRate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 二级资产类别 参照数据字典
     * @return 二级资产类别 参照数据字典
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 二级资产类别 参照数据字典
     * @param assetType2 二级资产类别 参照数据字典
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易流通场所  参照数据字典
     * @return 交易流通场所  参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所  参照数据字典
     * @param tradeMarket 交易流通场所  参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 是否为收/受益权 参照数据字典
     * @return 是否为收/受益权 参照数据字典
     */
    public String getIsRight() {
        return isRight;
    }

    /**
     * 设置 是否为收/受益权 参照数据字典
     * @param isRight 是否为收/受益权 参照数据字典
     */
    public void setIsRight(String isRight) {
        this.isRight = isRight == null ? null : isRight.trim();
    }

    /**
     * 取得 是否属于买入返售 参照数据字典
     * @return 是否属于买入返售 参照数据字典
     */
    public String getIsResale() {
        return isResale;
    }

    /**
     * 设置 是否属于买入返售 参照数据字典
     * @param isResale 是否属于买入返售 参照数据字典
     */
    public void setIsResale(String isResale) {
        this.isResale = isResale == null ? null : isResale.trim();
    }

    /**
     * 取得 类型 参照数据字典
     * @return 类型 参照数据字典
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 类型 参照数据字典
     * @param type 类型 参照数据字典
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置 数量
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 取得 合计金额
     * @return 合计金额
     */
    public BigDecimal getAmtSum() {
        return amtSum;
    }

    /**
     * 设置 合计金额
     * @param amtSum 合计金额
     */
    public void setAmtSum(BigDecimal amtSum) {
        this.amtSum = amtSum;
    }

    /**
     * 取得 加权剩余期限(天)
     * @return 加权剩余期限(天)
     */
    public BigDecimal getRemainTerm() {
        return remainTerm;
    }

    /**
     * 设置 加权剩余期限(天)
     * @param remainTerm 加权剩余期限(天)
     */
    public void setRemainTerm(BigDecimal remainTerm) {
        this.remainTerm = remainTerm;
    }

    /**
     * 取得 最长剩余期限(天)
     * @return 最长剩余期限(天)
     */
    public Integer getMaxRemainTerm() {
        return maxRemainTerm;
    }

    /**
     * 设置 最长剩余期限(天)
     * @param maxRemainTerm 最长剩余期限(天)
     */
    public void setMaxRemainTerm(Integer maxRemainTerm) {
        this.maxRemainTerm = maxRemainTerm;
    }

    /**
     * 取得 最短剩余期限(天)
     * @return 最短剩余期限(天)
     */
    public Integer getMinRemainTerm() {
        return minRemainTerm;
    }

    /**
     * 设置 最短剩余期限(天)
     * @param minRemainTerm 最短剩余期限(天)
     */
    public void setMinRemainTerm(Integer minRemainTerm) {
        this.minRemainTerm = minRemainTerm;
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 行业 参照数据字典
     * @return 行业 参照数据字典
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 行业 参照数据字典
     * @param industry 行业 参照数据字典
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 行业二级分类 参照数据字典
     * @return 行业二级分类 参照数据字典
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 行业二级分类 参照数据字典
     * @param industryLevel2 行业二级分类 参照数据字典
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
    }

    /**
     * 取得 贴现利率
     * @return 贴现利率
     */
    public BigDecimal getDiscRate() {
        return discRate;
    }

    /**
     * 设置 贴现利率
     * @param discRate 贴现利率
     */
    public void setDiscRate(BigDecimal discRate) {
        this.discRate = discRate;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}