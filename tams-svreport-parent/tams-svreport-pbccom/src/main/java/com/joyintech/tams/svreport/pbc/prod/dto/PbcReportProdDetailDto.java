package com.joyintech.tams.svreport.pbc.prod.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_PROD表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportProdDetailDto {
    /**
     * 主键
     */
    private String detailuuid;
    /**
     * 报表ID
     */
    private String reportuuid;
    /**
     * 属性代码
     */
    private String propertyCode;

    /**
     * 报表类型：MJYE-募集余额报表,MJDF-募集兑付报表,ZSQK-只数情况报表,MJYEFDQ-募集余额报表分地区报表,TQJYQDF-提前及延期兑付报表,DQWDF-到期未兑付报表
     */
    private String reporttype;

    /**
     * 分组类型：DQMJ-当期募集,DQDF-当期兑付,QMCX-期末存续,DQXF-当期新发,DQZZ-当期终止,TQDF-提前兑付,YQDF-延期兑付,DQWDF-到期未兑付
     */
    private String grouptype;

    /**
     * 人民币
     */
    private BigDecimal cny;

    /**
     * 美元折人民币
     */
    private BigDecimal usdToCny;

    /**
     * 欧元折人民币
     */
    private BigDecimal eurToCny;

    /**
     * 英镑折人民币
     */
    private BigDecimal gbpToCny;

    /**
     * 日元折人民币
     */
    private BigDecimal jpyToCny;

    /**
     * 其他币种(外币)折人民币
     */
    private BigDecimal othToCny;

    /**
     * 只数
     */
    private Long prodcounts;

    /**
     * 合计		
     */
    private BigDecimal total;

    /**
     * 主键
     * @return DETAILUUID 主键
     */
    public String getDetailuuid() {
        return detailuuid;
    }

    /**
     * 主键
     * @param detailuuid 主键
     */
    public void setDetailuuid(String detailuuid) {
        this.detailuuid = detailuuid == null ? null : detailuuid.trim();
    }

	public String getReportuuid() {
		return reportuuid;
	}

	public void setReportuuid(String reportuuid) {
		this.reportuuid = reportuuid;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	/**
     * 报表类型：MJYE-募集余额报表,MJDF-募集兑付报表,ZSQK-只数情况报表,MJYEFDQ-募集余额报表分地区报表,TQJYQDF-提前及延期兑付报表,DQWDF-到期未兑付报表
     * @return REPORTTYPE 报表类型：MJYE-募集余额报表,MJDF-募集兑付报表,ZSQK-只数情况报表,MJYEFDQ-募集余额报表分地区报表,TQJYQDF-提前及延期兑付报表,DQWDF-到期未兑付报表
     */
    public String getReporttype() {
        return reporttype;
    }

    /**
     * 报表类型：MJYE-募集余额报表,MJDF-募集兑付报表,ZSQK-只数情况报表,MJYEFDQ-募集余额报表分地区报表,TQJYQDF-提前及延期兑付报表,DQWDF-到期未兑付报表
     * @param reporttype 报表类型：MJYE-募集余额报表,MJDF-募集兑付报表,ZSQK-只数情况报表,MJYEFDQ-募集余额报表分地区报表,TQJYQDF-提前及延期兑付报表,DQWDF-到期未兑付报表
     */
    public void setReporttype(String reporttype) {
        this.reporttype = reporttype == null ? null : reporttype.trim();
    }

    /**
     * 分组类型：DQMJ-当期募集,DQDF-当期兑付,QMCX-期末存续,DQXF-当期新发,DQZZ-当期终止,TQDF-提前兑付,YQDF-延期兑付,DQWDF-到期未兑付
     * @return GROUPTYPE 分组类型：DQMJ-当期募集,DQDF-当期兑付,QMCX-期末存续,DQXF-当期新发,DQZZ-当期终止,TQDF-提前兑付,YQDF-延期兑付,DQWDF-到期未兑付
     */
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * 分组类型：DQMJ-当期募集,DQDF-当期兑付,QMCX-期末存续,DQXF-当期新发,DQZZ-当期终止,TQDF-提前兑付,YQDF-延期兑付,DQWDF-到期未兑付
     * @param grouptype 分组类型：DQMJ-当期募集,DQDF-当期兑付,QMCX-期末存续,DQXF-当期新发,DQZZ-当期终止,TQDF-提前兑付,YQDF-延期兑付,DQWDF-到期未兑付
     */
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype == null ? null : grouptype.trim();
    }

    /**
     * 人民币
     * @return CNY 人民币
     */
    public BigDecimal getCny() {
        return cny;
    }

    /**
     * 人民币
     * @param cny 人民币
     */
    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    /**
     * 美元折人民币
     * @return USD_TO_CNY 美元折人民币
     */
    public BigDecimal getUsdToCny() {
        return usdToCny;
    }

    /**
     * 美元折人民币
     * @param usdToCny 美元折人民币
     */
    public void setUsdToCny(BigDecimal usdToCny) {
        this.usdToCny = usdToCny;
    }

    /**
     * 欧元折人民币
     * @return EUR_TO_CNY 欧元折人民币
     */
    public BigDecimal getEurToCny() {
        return eurToCny;
    }

    /**
     * 欧元折人民币
     * @param eurToCny 欧元折人民币
     */
    public void setEurToCny(BigDecimal eurToCny) {
        this.eurToCny = eurToCny;
    }

    /**
     * 英镑折人民币
     * @return GBP_TO_CNY 英镑折人民币
     */
    public BigDecimal getGbpToCny() {
        return gbpToCny;
    }

    /**
     * 英镑折人民币
     * @param gbpToCny 英镑折人民币
     */
    public void setGbpToCny(BigDecimal gbpToCny) {
        this.gbpToCny = gbpToCny;
    }

    /**
     * 日元折人民币
     * @return JPY_TO_CNY 日元折人民币
     */
    public BigDecimal getJpyToCny() {
        return jpyToCny;
    }

    /**
     * 日元折人民币
     * @param jpyToCny 日元折人民币
     */
    public void setJpyToCny(BigDecimal jpyToCny) {
        this.jpyToCny = jpyToCny;
    }

    /**
     * 其他币种(外币)折人民币
     * @return OTH_TO_CNY 其他币种(外币)折人民币
     */
    public BigDecimal getOthToCny() {
        return othToCny;
    }

    /**
     * 其他币种(外币)折人民币
     * @param othToCny 其他币种(外币)折人民币
     */
    public void setOthToCny(BigDecimal othToCny) {
        this.othToCny = othToCny;
    }

    /**
     * 只数
     * @return PRODCOUNTS 只数
     */
    public Long getProdcounts() {
        return prodcounts;
    }

    /**
     * 只数
     * @param prodcounts 只数
     */
    public void setProdcounts(Long prodcounts) {
        this.prodcounts = prodcounts;
    }

    /**
     * 合计		
     * @return TOTAL 合计		
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 合计		
     * @param total 合计		
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}