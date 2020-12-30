package com.joyintech.service.report.regulatoryreporting.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ShareFilePbcProdOrgYld extends BaseEntity {
    /**
     * 明细统计uuid
     */
    private String detailuuid;

    /**
     * 报表类型,B_3
     */
    private String reporttype;

    /**
     * 分组类型
     */
    private String grouptype;

    /**
     * 属性代码
     */
    private String propertycode;

    /**
     * 主表主键
     */
    private String reportuuid;

    /**
     * 新发产品最高收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal xhighYield;

    /**
     * 新发产品最低收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal xlowYield;

    /**
     * 新发产品平均收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal xexpYield;

    /**
     * 存续产品最高收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cxhighYield;

    /**
     * 存续产品最低收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cxlowYield;

    /**
     * 存续产品平均收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cxexpYield;

    /**
     * 到期产品兑付收益率%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqYield;

    /**
     * 到期产品按最低兑付率占比%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqhighYield;

    /**
     * 到期产品按最高兑付率占比%
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dqlowYield;

    /**
     * 取得 明细统计uuid
     * @return 明细统计uuid
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 设置 明细统计uuid
     * @param detailuuid 明细统计uuid
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

    /**
     * 取得 报表类型,B_3
     * @return 报表类型,B_3
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 设置 报表类型,B_3
     * @param reporttype 报表类型,B_3
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 取得 分组类型
     * @return 分组类型
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 设置 分组类型
     * @param grouptype 分组类型
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
    }

    /**
     * 取得 属性代码
     * @return 属性代码
     */
    public String getPropertycode() {
        return propertycode;
    }

    /**
     * 设置 属性代码
     * @param propertycode 属性代码
     */
    public void setPropertycode(String propertycode) {
        this.propertycode = propertycode == null ? null : propertycode.trim();
    }

    /**
     * 取得 主表主键
     * @return 主表主键
     */
    public String getReportuuid() {
        return reportuuid;
    }

    /**
     * 设置 主表主键
     * @param reportuuid 主表主键
     */
    public void setReportuuid(String reportuuid) {
        this.reportuuid = reportuuid == null ? null : reportuuid.trim();
    }

    /**
     * 取得 新发产品最高收益率%
     * @return 新发产品最高收益率%
     */
    public BigDecimal getXhighYield() {
        return xhighYield;
    }

    /**
     * 设置 新发产品最高收益率%
     * @param xhighYield 新发产品最高收益率%
     */
    public void setXhighYield(BigDecimal xhighYield) {
        this.xhighYield = xhighYield;
    }

    /**
     * 取得 新发产品最低收益率%
     * @return 新发产品最低收益率%
     */
    public BigDecimal getXlowYield() {
        return xlowYield;
    }

    /**
     * 设置 新发产品最低收益率%
     * @param xlowYield 新发产品最低收益率%
     */
    public void setXlowYield(BigDecimal xlowYield) {
        this.xlowYield = xlowYield;
    }

    /**
     * 取得 新发产品平均收益率%
     * @return 新发产品平均收益率%
     */
    public BigDecimal getXexpYield() {
        return xexpYield;
    }

    /**
     * 设置 新发产品平均收益率%
     * @param xexpYield 新发产品平均收益率%
     */
    public void setXexpYield(BigDecimal xexpYield) {
        this.xexpYield = xexpYield;
    }

    /**
     * 取得 存续产品最高收益率%
     * @return 存续产品最高收益率%
     */
    public BigDecimal getCxhighYield() {
        return cxhighYield;
    }

    /**
     * 设置 存续产品最高收益率%
     * @param cxhighYield 存续产品最高收益率%
     */
    public void setCxhighYield(BigDecimal cxhighYield) {
        this.cxhighYield = cxhighYield;
    }

    /**
     * 取得 存续产品最低收益率%
     * @return 存续产品最低收益率%
     */
    public BigDecimal getCxlowYield() {
        return cxlowYield;
    }

    /**
     * 设置 存续产品最低收益率%
     * @param cxlowYield 存续产品最低收益率%
     */
    public void setCxlowYield(BigDecimal cxlowYield) {
        this.cxlowYield = cxlowYield;
    }

    /**
     * 取得 存续产品平均收益率%
     * @return 存续产品平均收益率%
     */
    public BigDecimal getCxexpYield() {
        return cxexpYield;
    }

    /**
     * 设置 存续产品平均收益率%
     * @param cxexpYield 存续产品平均收益率%
     */
    public void setCxexpYield(BigDecimal cxexpYield) {
        this.cxexpYield = cxexpYield;
    }

    /**
     * 取得 到期产品兑付收益率%
     * @return 到期产品兑付收益率%
     */
    public BigDecimal getDqYield() {
        return dqYield;
    }

    /**
     * 设置 到期产品兑付收益率%
     * @param dqYield 到期产品兑付收益率%
     */
    public void setDqYield(BigDecimal dqYield) {
        this.dqYield = dqYield;
    }

    /**
     * 取得 到期产品按最低兑付率占比%
     * @return 到期产品按最低兑付率占比%
     */
    public BigDecimal getDqhighYield() {
        return dqhighYield;
    }

    /**
     * 设置 到期产品按最低兑付率占比%
     * @param dqhighYield 到期产品按最低兑付率占比%
     */
    public void setDqhighYield(BigDecimal dqhighYield) {
        this.dqhighYield = dqhighYield;
    }

    /**
     * 取得 到期产品按最高兑付率占比%
     * @return 到期产品按最高兑付率占比%
     */
    public BigDecimal getDqlowYield() {
        return dqlowYield;
    }

    /**
     * 设置 到期产品按最高兑付率占比%
     * @param dqlowYield 到期产品按最高兑付率占比%
     */
    public void setDqlowYield(BigDecimal dqlowYield) {
        this.dqlowYield = dqlowYield;
    }
}