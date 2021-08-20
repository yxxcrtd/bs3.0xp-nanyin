package com.joyintech.tams.svreport.pbc.assetdebtdat.dto;

import java.math.BigDecimal;

/**
 * 描述:PBC_REPORT_PROD_DAT表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-03-04
 */
public class PbcReportProdDatDto {
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
     * 压缩包编号
     */
    private String datZipCode;

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
     * 人民币
     */
    private BigDecimal cnyPrd;

    /**
     * 美元折人民币
     */
    private BigDecimal usdToCnyPrd;

    /**
     * 欧元折人民币
     */
    private BigDecimal eurToCnyPrd;

    /**
     * 英镑折人民币
     */
    private BigDecimal gbpToCnyPrd;

    /**
     * 日元折人民币
     */
    private BigDecimal jpyToCnyPrd;

    /**
     * 其他币种(外币)折人民币
     */
    private BigDecimal othToCnyPrd;


    /**
     * 合计
     */
    private BigDecimal totalPrd;
    /**
     * 人民币
     */
    private Integer cnyDiff=0;

    /**
     * 美元折人民币
     */
    private Integer usdToCnyDiff=0;

    /**
     * 欧元折人民币
     */
    private Integer eurToCnyDiff=0;

    /**
     * 英镑折人民币
     */
    private Integer gbpToCnyDiff=0;

    /**
     * 日元折人民币
     */
    private Integer jpyToCnyDiff=0;

    /**
     * 其他币种(外币)折人民币
     */
    private Integer othToCnyDiff=0;


    /**
     * 合计
     */
    private Integer totalDiff=0;
    /**
     * 合计
     */
    private String finInstitutionCode;
    /**
     * 产品品种
     */
    private String prodVariety;

    /**
     * 期数
     */
    private String termid;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

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

    public BigDecimal getCnyPrd() {
        return cnyPrd;
    }

    public PbcReportProdDatDto setCnyPrd(BigDecimal cnyPrd) {
        this.cnyPrd = cnyPrd;
        return this;
    }

    public BigDecimal getUsdToCnyPrd() {
        return usdToCnyPrd;
    }

    public PbcReportProdDatDto setUsdToCnyPrd(BigDecimal usdToCnyPrd) {
        this.usdToCnyPrd = usdToCnyPrd;
        return this;
    }

    public BigDecimal getEurToCnyPrd() {
        return eurToCnyPrd;
    }

    public PbcReportProdDatDto setEurToCnyPrd(BigDecimal eurToCnyPrd) {
        this.eurToCnyPrd = eurToCnyPrd;
        return this;
    }

    public BigDecimal getGbpToCnyPrd() {
        return gbpToCnyPrd;
    }

    public PbcReportProdDatDto setGbpToCnyPrd(BigDecimal gbpToCnyPrd) {
        this.gbpToCnyPrd = gbpToCnyPrd;
        return this;
    }

    public BigDecimal getJpyToCnyPrd() {
        return jpyToCnyPrd;
    }

    public PbcReportProdDatDto setJpyToCnyPrd(BigDecimal jpyToCnyPrd) {
        this.jpyToCnyPrd = jpyToCnyPrd;
        return this;
    }

    public BigDecimal getOthToCnyPrd() {
        return othToCnyPrd;
    }

    public PbcReportProdDatDto setOthToCnyPrd(BigDecimal othToCnyPrd) {
        this.othToCnyPrd = othToCnyPrd;
        return this;
    }

    public BigDecimal getTotalPrd() {
        return totalPrd;
    }

    public PbcReportProdDatDto setTotalPrd(BigDecimal totalPrd) {
        this.totalPrd = totalPrd;
        return this;
    }

    public Integer getCnyDiff() {
        return cnyDiff;
    }

    public PbcReportProdDatDto setCnyDiff(Integer cnyDiff) {
        this.cnyDiff = cnyDiff;
        return this;
    }

    public Integer getUsdToCnyDiff() {
        return usdToCnyDiff;
    }

    public PbcReportProdDatDto setUsdToCnyDiff(Integer usdToCnyDiff) {
        this.usdToCnyDiff = usdToCnyDiff;
        return this;
    }

    public Integer getEurToCnyDiff() {
        return eurToCnyDiff;
    }

    public PbcReportProdDatDto setEurToCnyDiff(Integer eurToCnyDiff) {
        this.eurToCnyDiff = eurToCnyDiff;
        return this;
    }

    public Integer getGbpToCnyDiff() {
        return gbpToCnyDiff;
    }

    public PbcReportProdDatDto setGbpToCnyDiff(Integer gbpToCnyDiff) {
        this.gbpToCnyDiff = gbpToCnyDiff;
        return this;
    }

    public Integer getJpyToCnyDiff() {
        return jpyToCnyDiff;
    }

    public PbcReportProdDatDto setJpyToCnyDiff(Integer jpyToCnyDiff) {
        this.jpyToCnyDiff = jpyToCnyDiff;
        return this;
    }

    public Integer getOthToCnyDiff() {
        return othToCnyDiff;
    }

    public PbcReportProdDatDto setOthToCnyDiff(Integer othToCnyDiff) {
        this.othToCnyDiff = othToCnyDiff;
        return this;
    }

    public Integer getTotalDiff() {
        return totalDiff;
    }

    public PbcReportProdDatDto setTotalDiff(Integer totalDiff) {
        this.totalDiff = totalDiff;
        return this;
    }

    public String getDatZipCode() {
        return datZipCode;
    }

    public void setDatZipCode(String datZipCode) {
        this.datZipCode = datZipCode;
    }


    public String getFinInstitutionCode() {
        return finInstitutionCode;
    }

    public void setFinInstitutionCode(String finInstitutionCode) {
        this.finInstitutionCode = finInstitutionCode;
    }

    public String getProdVariety() {
        return prodVariety;
    }

    public void setProdVariety(String prodVariety) {
        this.prodVariety = prodVariety;
    }
    /**
     * 期数
     * @return TERMID 期数
     */
    public String getTermid() {
        return termid;
    }

    /**
     * 期数
     * @param termid 期数
     */
    public void setTermid(String termid) {
        this.termid = termid == null ? null : termid.trim();
    }

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }
}