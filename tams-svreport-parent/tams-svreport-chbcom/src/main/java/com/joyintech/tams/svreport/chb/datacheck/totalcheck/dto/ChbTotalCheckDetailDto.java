package com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 描述:CHB_TOTALCHECK_DETAIL表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-04-19
 */
public class ChbTotalCheckDetailDto extends BaseEntity{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键:自动生成uuid
     */
    private String seqno;

    /**
     * 关联检查uuid:CHB_TOTALCHECK.SEQNO
     */
    private String totalseqno;

    /**
     * 资产二级类型:
     */
    private String assetType2;

    /**
     * 资产负债编码:
     */
    private String assetCode;

    /**
     * 理财产品代码:
     */
    private String prdId;

    /**
     * 理财产品名称:
     */
    private String prdName;

    /**
     * 理财产品存续只数:
     */
    private BigDecimal prdNum;

    /**
     * 理财产品存续金额(万元):
     */
    private BigDecimal prdAmt;

    /**
     * 月度统计表存续只数:
     */
    private BigDecimal monthPrdNum;

    /**
     * 月度统计表存续余额（万元）:
     */
    private BigDecimal monthPrdAmt;

    /**
     * 偏离度:
     */
    private BigDecimal deviation;

    /**
     * 偏离度:
     */
    private String deviationStr;

    /**
     * 理财产品存续只数(人民币产品):
     */
    private BigDecimal prdNumCny;

    /**
     * 理财产品存续金额(万元、人民币产品):
     */
    private BigDecimal prdAmtCny;

    /**
     * 月度统计表存续只数(人民币产品):
     */
    private BigDecimal monthPrdNumCny;

    /**
     * 月度统计表存续余额(万元、人民币产品):
     */
    private BigDecimal monthPrdAmtCny;

    /**
     * 偏离度(人民币):
     */
    private BigDecimal deviationCny;

    /**
     * 偏离度(人民币):
     */
    private String deviationCnyStr;

    /**
     * 合格率:计算结果保留俩位小数:
     */
    private BigDecimal detailYield;

    /**
     * 合格率:计算结果保留俩位小数:
     */
    private String detailYieldStr;

    /**
     * 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型  :
     */
    private String operateMode;

    /**
     * 产品存续金额:
     */
    private BigDecimal holdAmt;

    /**
     * 产品资产持仓金额:
     */
    private BigDecimal prdAstholdAmt;

    /**
     * 产品负债持仓金额:
     */
    private BigDecimal prdBalholdAmt;

    /**
     * 产品现金持仓金额:
     */
    private BigDecimal prdCashholdAmt;

    /**
     * 持仓日期:
     */
    private Date prdHolddate;

    /**
     * 行内标识码:
     */
    private String prdInsId;

    /**
     * 产品起息日:
     */
    private Date prdVdate;

    /**
     * 募集起始日:
     */
    private Date raiseStartdate;

    /**
     * 募集结束日:
     */
    private Date raiseEnddate;

    /**
     * 募集登记日:
     */
    private Date raiseRegdate;

    /**
     * 是否已登记:01:是 02：否
     */
    private String isReg;

    /**
     * 募集总量是否登记不及时:01:是 02：否
     */
    private String raiseIsDelay;

    /**
     * 底层持仓当前发行总数量:
     */
    private BigDecimal botIssueNum;

    /**
     * 底层持仓当前总发行金额:
     */
    private BigDecimal botIssueAmt;

    /**
     * 底层持仓未投资头寸:
     */
    private BigDecimal botUninvestCash;

    /**
     * 底层总资产市值:
     */
    private BigDecimal botAssetAmt;

    /**
     * 底层总负债市值:
     */
    private BigDecimal botDebtAmt;

    /**
     * 资产持仓数量:
     */
    private BigDecimal astHoldNum;

    /**
     * 资产持仓是否计入统计:01:是 02：否
     */
    private String astHoldIsCount;

    /**
     * 资产交易最大单价:
     */
    private BigDecimal astMaxUprice;

    /**
     * 资产交易最小单价:
     */
    private BigDecimal astMinUprice;

    /**
     * 是否偏差过大:01:是 02：否
     */
    private String isDeviation;

    /**
     * 检查日期：登记表中数据日期:
     */
    private Date checkDate;

    /**
     * 发行机构代码:
     */
    private String issuePartyId;

    /**
     * 发行机构名称:
     */
    private String issuePartyName;

    /**
     * 机构代码:
     */
    private String orgCode;

    /**
     * 创建人:
     */
    private String createUser;

    /**
     * 创建部门:
     */
    private String createDept;

    /**
     * 创建时间:
     */
    private Date createTime;

    /**
     * 更新人:
     */
    private String updateUser;

    /**
     * 更新时间:
     */
    private Date updateTime;
    
    /**
     * 是否为存续产品
     */
    private String isIssueReg;
    
    /**
     * 认购币种及金额SUBSCRIBE_CCY_AMT:原币种，原币种金额，折算人民币金额
     */
    private String subscribeCcyAmt;
    
    /**
     * 资产交易金额（未折算）
     */
    private BigDecimal prdAssetAmt;
    
    /**
     * 资产交易币种
     */
    private String prdAssetCcy;
    
    /**
     * 资产负债持仓币种
     */
    private String assetHoldCcy;

    /**
     * 初始产品剩余现金
     */
    private BigDecimal prdInitAmt;
    
    /**
     * 存续期交易币种及申购兑付金额
     */
    private String ccyPurchase;
    
    /**
     * 初始现金持仓币种
     */
    private String initCcy;
    
    /**
     * 主键:自动生成uuid
     * @return SEQNO 主键:自动生成uuid
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 主键:自动生成uuid
     * @param seqno 主键:自动生成uuid
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 关联检查uuid:CHB_TOTALCHECK.SEQNO
     * @return TOTALSEQNO 关联检查uuid:CHB_TOTALCHECK.SEQNO
     */
    public String getTotalseqno() {
        return totalseqno;
    }

    /**
     * 关联检查uuid:CHB_TOTALCHECK.SEQNO
     * @param totalseqno 关联检查uuid:CHB_TOTALCHECK.SEQNO
     */
    public void setTotalseqno(String totalseqno) {
        this.totalseqno = totalseqno == null ? null : totalseqno.trim();
    }

    /**
     * 资产二级类型:
     * @return ASSET_TYPE2 资产二级类型:
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 资产二级类型:
     * @param assetType2 资产二级类型:
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 资产负债编码:
     * @return ASSET_CODE 资产负债编码:
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 资产负债编码:
     * @param assetCode 资产负债编码:
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 理财产品代码:
     * @return PRD_ID 理财产品代码:
     */
    public String getPrdId() {
        return prdId;
    }

    /**
     * 理财产品代码:
     * @param prdId 理财产品代码:
     */
    public void setPrdId(String prdId) {
        this.prdId = prdId == null ? null : prdId.trim();
    }

    /**
     * 理财产品名称:
     * @return PRD_NAME 理财产品名称:
     */
    public String getPrdName() {
        return prdName;
    }

    /**
     * 理财产品名称:
     * @param prdName 理财产品名称:
     */
    public void setPrdName(String prdName) {
        this.prdName = prdName == null ? null : prdName.trim();
    }

    /**
     * 理财产品存续只数:
     * @return PRD_NUM 理财产品存续只数:
     */
    public BigDecimal getPrdNum() {
        return prdNum;
    }

    /**
     * 理财产品存续只数:
     * @param prdNum 理财产品存续只数:
     */
    public void setPrdNum(BigDecimal prdNum) {
        this.prdNum = prdNum;
    }

    /**
     * 理财产品存续金额(万元):
     * @return PRD_AMT 理财产品存续金额(万元):
     */
    public BigDecimal getPrdAmt() {
        return prdAmt;
    }

    /**
     * 理财产品存续金额(万元):
     * @param prdAmt 理财产品存续金额(万元):
     */
    public void setPrdAmt(BigDecimal prdAmt) {
        this.prdAmt = prdAmt;
    }

    /**
     * 月度统计表存续只数:
     * @return MONTH_PRD_NUM 月度统计表存续只数:
     */
    public BigDecimal getMonthPrdNum() {
        return monthPrdNum;
    }

    /**
     * 月度统计表存续只数:
     * @param monthPrdNum 月度统计表存续只数:
     */
    public void setMonthPrdNum(BigDecimal monthPrdNum) {
        this.monthPrdNum = monthPrdNum;
    }

    /**
     * 月度统计表存续余额（万元）:
     * @return MONTH_PRD_AMT 月度统计表存续余额（万元）:
     */
    public BigDecimal getMonthPrdAmt() {
        return monthPrdAmt;
    }

    /**
     * 月度统计表存续余额（万元）:
     * @param monthPrdAmt 月度统计表存续余额（万元）:
     */
    public void setMonthPrdAmt(BigDecimal monthPrdAmt) {
        this.monthPrdAmt = monthPrdAmt;
    }

    /**
     * 偏离度:
     * @return DEVIATION 偏离度:
     */
    public BigDecimal getDeviation() {
        return deviation;
    }

    /**
     * 偏离度:
     * @param deviation 偏离度:
     */
    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    /**
     * 理财产品存续只数(人民币产品):
     * @return PRD_NUM_CNY 理财产品存续只数(人民币产品):
     */
    public BigDecimal getPrdNumCny() {
        return prdNumCny;
    }

    /**
     * 理财产品存续只数(人民币产品):
     * @param prdNumCny 理财产品存续只数(人民币产品):
     */
    public void setPrdNumCny(BigDecimal prdNumCny) {
        this.prdNumCny = prdNumCny;
    }

    /**
     * 理财产品存续金额(万元、人民币产品):
     * @return PRD_AMT_CNY 理财产品存续金额(万元、人民币产品):
     */
    public BigDecimal getPrdAmtCny() {
        return prdAmtCny;
    }

    /**
     * 理财产品存续金额(万元、人民币产品):
     * @param prdAmtCny 理财产品存续金额(万元、人民币产品):
     */
    public void setPrdAmtCny(BigDecimal prdAmtCny) {
        this.prdAmtCny = prdAmtCny;
    }

    /**
     * 月度统计表存续只数(人民币产品):
     * @return MONTH_PRD_NUM_CNY 月度统计表存续只数(人民币产品):
     */
    public BigDecimal getMonthPrdNumCny() {
        return monthPrdNumCny;
    }

    /**
     * 月度统计表存续只数(人民币产品):
     * @param monthPrdNumCny 月度统计表存续只数(人民币产品):
     */
    public void setMonthPrdNumCny(BigDecimal monthPrdNumCny) {
        this.monthPrdNumCny = monthPrdNumCny;
    }

    /**
     * 月度统计表存续余额(万元、人民币产品):
     * @return MONTH_PRD_AMT_CNY 月度统计表存续余额(万元、人民币产品):
     */
    public BigDecimal getMonthPrdAmtCny() {
        return monthPrdAmtCny;
    }

    /**
     * 月度统计表存续余额(万元、人民币产品):
     * @param monthPrdAmtCny 月度统计表存续余额(万元、人民币产品):
     */
    public void setMonthPrdAmtCny(BigDecimal monthPrdAmtCny) {
        this.monthPrdAmtCny = monthPrdAmtCny;
    }

    /**
     * 偏离度(人民币):
     * @return DEVIATION_CNY 偏离度(人民币):
     */
    public BigDecimal getDeviationCny() {
        return deviationCny;
    }

    /**
     * 偏离度(人民币):
     * @param deviationCny 偏离度(人民币):
     */
    public void setDeviationCny(BigDecimal deviationCny) {
        this.deviationCny = deviationCny;
    }

    /**
     * 合格率:计算结果保留俩位小数:
     * @return DETAIL_YIELD 合格率:计算结果保留俩位小数:
     */
    public BigDecimal getDetailYield() {
        return detailYield;
    }

    /**
     * 合格率:计算结果保留俩位小数:
     * @param detailYield 合格率:计算结果保留俩位小数:
     */
    public void setDetailYield(BigDecimal detailYield) {
        this.detailYield = detailYield;
    }

    /**
     * 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型  :
     * @return OPERATE_MODE 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型  :
     */
    public String getOperateMode() {
        return operateMode;
    }

    /**
     * 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型  :
     * @param operateMode 产品运作模式  01 封闭式净值型/02 封闭式非净值型/03 开放式净值型/04 开放式非净值型  :
     */
    public void setOperateMode(String operateMode) {
        this.operateMode = operateMode == null ? null : operateMode.trim();
    }

    /**
     * 产品存续金额:
     * @return HOLD_AMT 产品存续金额:
     */
    public BigDecimal getHoldAmt() {
        return holdAmt;
    }

    /**
     * 产品存续金额:
     * @param holdAmt 产品存续金额:
     */
    public void setHoldAmt(BigDecimal holdAmt) {
        this.holdAmt = holdAmt;
    }

    /**
     * 产品资产持仓金额:
     * @return PRD_ASTHOLD_AMT 产品资产持仓金额:
     */
    public BigDecimal getPrdAstholdAmt() {
        return prdAstholdAmt;
    }

    /**
     * 产品资产持仓金额:
     * @param prdAstholdAmt 产品资产持仓金额:
     */
    public void setPrdAstholdAmt(BigDecimal prdAstholdAmt) {
        this.prdAstholdAmt = prdAstholdAmt;
    }

    /**
     * 产品负债持仓金额:
     * @return PRD_BALHOLD_AMT 产品负债持仓金额:
     */
    public BigDecimal getPrdBalholdAmt() {
        return prdBalholdAmt;
    }

    /**
     * 产品负债持仓金额:
     * @param prdBalholdAmt 产品负债持仓金额:
     */
    public void setPrdBalholdAmt(BigDecimal prdBalholdAmt) {
        this.prdBalholdAmt = prdBalholdAmt;
    }

    /**
     * 产品现金持仓金额:
     * @return PRD_CASHHOLD_AMT 产品现金持仓金额:
     */
    public BigDecimal getPrdCashholdAmt() {
        return prdCashholdAmt;
    }

    /**
     * 产品现金持仓金额:
     * @param prdCashholdAmt 产品现金持仓金额:
     */
    public void setPrdCashholdAmt(BigDecimal prdCashholdAmt) {
        this.prdCashholdAmt = prdCashholdAmt;
    }

    /**
     * 持仓日期:
     * @return PRD_HOLDDATE 持仓日期:
     */
    public Date getPrdHolddate() {
        return prdHolddate;
    }

    /**
     * 持仓日期:
     * @param prdHolddate 持仓日期:
     */
    public void setPrdHolddate(Date prdHolddate) {
        this.prdHolddate = prdHolddate;
    }

    /**
     * 行内标识码:
     * @return PRD_REGCODE 行内标识码:
     */
    public String getPrdInsId() {
        return prdInsId;
    }

    /**
     * 行内标识码:
     * @param prdInsId 行内标识码:
     */
    public void setPrdInsId(String prdInsId) {
        this.prdInsId = prdInsId == null ? null : prdInsId.trim();
    }

    /**
     * 产品起息日:
     * @return PRD_VDATE 产品起息日:
     */
    public Date getPrdVdate() {
        return prdVdate;
    }

    /**
     * 产品起息日:
     * @param prdVdate 产品起息日:
     */
    public void setPrdVdate(Date prdVdate) {
        this.prdVdate = prdVdate;
    }

    /**
     * 募集起始日:
     * @return RAISE_STARTDATE 募集起始日:
     */
    public Date getRaiseStartdate() {
        return raiseStartdate;
    }

    /**
     * 募集起始日:
     * @param raiseStartdate 募集起始日:
     */
    public void setRaiseStartdate(Date raiseStartdate) {
        this.raiseStartdate = raiseStartdate;
    }

    /**
     * 募集结束日:
     * @return RAISE_ENDDATE 募集结束日:
     */
    public Date getRaiseEnddate() {
        return raiseEnddate;
    }

    /**
     * 募集结束日:
     * @param raiseEnddate 募集结束日:
     */
    public void setRaiseEnddate(Date raiseEnddate) {
        this.raiseEnddate = raiseEnddate;
    }

    /**
     * 募集登记日:
     * @return RAISE_REGDATE 募集登记日:
     */
    public Date getRaiseRegdate() {
        return raiseRegdate;
    }

    /**
     * 募集登记日:
     * @param raiseRegdate 募集登记日:
     */
    public void setRaiseRegdate(Date raiseRegdate) {
        this.raiseRegdate = raiseRegdate;
    }

    /**
     * 募集总量是否已登记:01:是 02：否
     * @return RAISE_IS_REG 募集总量是否已登记:01:是 02：否
     */
    public String getIsReg() {
        return isReg;
    }

    /**
     * 募集总量是否已登记:01:是 02：否
     * @param raiseIsReg 募集总量是否已登记:01:是 02：否
     */
    public void setIsReg(String isReg) {
        this.isReg = isReg == null ? null : isReg.trim();
    }

    /**
     * 募集总量是否登记不及时:01:是 02：否
     * @return RAISE_IS_DELAY 募集总量是否登记不及时:01:是 02：否
     */
    public String getRaiseIsDelay() {
        return raiseIsDelay;
    }

    /**
     * 募集总量是否登记不及时:01:是 02：否
     * @param raiseIsDelay 募集总量是否登记不及时:01:是 02：否
     */
    public void setRaiseIsDelay(String raiseIsDelay) {
        this.raiseIsDelay = raiseIsDelay == null ? null : raiseIsDelay.trim();
    }

    /**
     * 底层持仓当前发行总数量:
     * @return BOT_ISSUE_NUM 底层持仓当前发行总数量:
     */
    public BigDecimal getBotIssueNum() {
        return botIssueNum;
    }

    /**
     * 底层持仓当前发行总数量:
     * @param botIssueNum 底层持仓当前发行总数量:
     */
    public void setBotIssueNum(BigDecimal botIssueNum) {
        this.botIssueNum = botIssueNum;
    }

    /**
     * 底层持仓当前总发行金额:
     * @return BOT_ISSUE_AMT 底层持仓当前总发行金额:
     */
    public BigDecimal getBotIssueAmt() {
        return botIssueAmt;
    }

    /**
     * 底层持仓当前总发行金额:
     * @param botIssueAmt 底层持仓当前总发行金额:
     */
    public void setBotIssueAmt(BigDecimal botIssueAmt) {
        this.botIssueAmt = botIssueAmt;
    }

    /**
     * 底层持仓未投资头寸:
     * @return BOT_UNINVEST_CASH 底层持仓未投资头寸:
     */
    public BigDecimal getBotUninvestCash() {
        return botUninvestCash;
    }

    /**
     * 底层持仓未投资头寸:
     * @param botUninvestCash 底层持仓未投资头寸:
     */
    public void setBotUninvestCash(BigDecimal botUninvestCash) {
        this.botUninvestCash = botUninvestCash;
    }

    /**
     * 底层总资产市值:
     * @return BOT_ASSET_AMT 底层总资产市值:
     */
    public BigDecimal getBotAssetAmt() {
        return botAssetAmt;
    }

    /**
     * 底层总资产市值:
     * @param botAssetAmt 底层总资产市值:
     */
    public void setBotAssetAmt(BigDecimal botAssetAmt) {
        this.botAssetAmt = botAssetAmt;
    }

    /**
     * 底层总负债市值:
     * @return BOT_DEBT_AMT 底层总负债市值:
     */
    public BigDecimal getBotDebtAmt() {
        return botDebtAmt;
    }

    /**
     * 底层总负债市值:
     * @param botDebtAmt 底层总负债市值:
     */
    public void setBotDebtAmt(BigDecimal botDebtAmt) {
        this.botDebtAmt = botDebtAmt;
    }

    /**
     * 资产持仓数量:
     * @return AST_HOLD_NUM 资产持仓数量:
     */
    public BigDecimal getAstHoldNum() {
        return astHoldNum;
    }

    /**
     * 资产持仓数量:
     * @param astHoldNum 资产持仓数量:
     */
    public void setAstHoldNum(BigDecimal astHoldNum) {
        this.astHoldNum = astHoldNum;
    }

    /**
     * 资产持仓是否计入统计:01:是 02：否
     * @return AST_HOLD_IS_COUNT 资产持仓是否计入统计:01:是 02：否
     */
    public String getAstHoldIsCount() {
        return astHoldIsCount;
    }

    /**
     * 资产持仓是否计入统计:01:是 02：否
     * @param astHoldIsCount 资产持仓是否计入统计:01:是 02：否
     */
    public void setAstHoldIsCount(String astHoldIsCount) {
        this.astHoldIsCount = astHoldIsCount == null ? null : astHoldIsCount.trim();
    }

    /**
     * 资产交易最大单价:
     * @return AST_MIN_UPRICE 资产交易最大单价:
     */
    public BigDecimal getAstMinUprice() {
        return astMinUprice;
    }

    /**
     * 资产交易最大单价:
     * @param astMinUprice 资产交易最大单价:
     */
    public void setAstMinUprice(BigDecimal astMinUprice) {
        this.astMinUprice = astMinUprice;
    }

    /**
     * 资产交易最小单价:
     * @return AST_MAX_UPRICE 资产交易最小单价:
     */
    public BigDecimal getAstMaxUprice() {
        return astMaxUprice;
    }

    /**
     * 资产交易最小单价:
     * @param astMaxUprice 资产交易最小单价:
     */
    public void setAstMaxUprice(BigDecimal astMaxUprice) {
        this.astMaxUprice = astMaxUprice;
    }

    /**
     * 是否偏差过大:01:是 02：否
     * @return IS_DEVIATION 是否偏差过大:01:是 02：否
     */
    public String getIsDeviation() {
        return isDeviation;
    }

    /**
     * 是否偏差过大:01:是 02：否
     * @param isDeviation 是否偏差过大:01:是 02：否
     */
    public void setIsDeviation(String isDeviation) {
        this.isDeviation = isDeviation == null ? null : isDeviation.trim();
    }

    /**
     * 检查日期：登记表中数据日期:
     * @return CHECK_DATE 检查日期：登记表中数据日期:
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 检查日期：登记表中数据日期:
     * @param checkDate 检查日期：登记表中数据日期:
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 发行机构代码:
     * @return ISSUE_PARTY_ID 发行机构代码:
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 发行机构代码:
     * @param issuePartyId 发行机构代码:
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 发行机构名称:
     * @return ISSUE_PARTY_NAME 发行机构名称:
     */
    public String getIssuePartyName() {
        return issuePartyName;
    }

    /**
     * 发行机构名称:
     * @param issuePartyName 发行机构名称:
     */
    public void setIssuePartyName(String issuePartyName) {
        this.issuePartyName = issuePartyName == null ? null : issuePartyName.trim();
    }

    /**
     * 机构代码:
     * @return ORG_CODE 机构代码:
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 机构代码:
     * @param orgCode 机构代码:
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 创建人:
     * @return CREATE_USER 创建人:
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 创建人:
     * @param createUser 创建人:
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 创建部门:
     * @return CREATE_DEPT 创建部门:
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * 创建部门:
     * @param createDept 创建部门:
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * 创建时间:
     * @return CREATE_TIME 创建时间:
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间:
     * @param createTime 创建时间:
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新人:
     * @return UPDATE_USER 更新人:
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 更新人:
     * @param updateUser 更新人:
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 更新时间:
     * @return UPDATE_TIME 更新时间:
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间:
     * @param updateTime 更新时间:
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeviationStr() {
        return deviationStr;
    }

    public void setDeviationStr(String deviationStr) {
        this.deviationStr = deviationStr;
    }

    public String getDeviationCnyStr() {
        return deviationCnyStr;
    }

    public void setDeviationCnyStr(String deviationCnyStr) {
        this.deviationCnyStr = deviationCnyStr;
    }

    public String getDetailYieldStr() {
        return detailYieldStr;
    }

    public void setDetailYieldStr(String detailYieldStr) {
        this.detailYieldStr = detailYieldStr;
    }

    public String getIsIssueReg() {
        return isIssueReg;
    }

    public void setIsIssueReg(String isIssueReg) {
        this.isIssueReg = isIssueReg;
    }

    public String getSubscribeCcyAmt() {
        return subscribeCcyAmt;
    }

    public void setSubscribeCcyAmt(String subscribeCcyAmt) {
        this.subscribeCcyAmt = subscribeCcyAmt;
    }

    public BigDecimal getPrdAssetAmt() {
        return prdAssetAmt;
    }

    public void setPrdAssetAmt(BigDecimal prdAssetAmt) {
        this.prdAssetAmt = prdAssetAmt;
    }

    public String getPrdAssetCcy() {
        return prdAssetCcy;
    }

    public void setPrdAssetCcy(String prdAssetCcy) {
        this.prdAssetCcy = prdAssetCcy;
    }

    public String getAssetHoldCcy() {
        return assetHoldCcy;
    }

    public void setAssetHoldCcy(String assetHoldCcy) {
        this.assetHoldCcy = assetHoldCcy;
    }

    public BigDecimal getPrdInitAmt() {
        return prdInitAmt;
    }

    public void setPrdInitAmt(BigDecimal prdInitAmt) {
        this.prdInitAmt = prdInitAmt;
    }

    public String getCcyPurchase() {
        return ccyPurchase;
    }

    public void setCcyPurchase(String ccyPurchase) {
        this.ccyPurchase = ccyPurchase;
    }

    public String getInitCcy() {
        return initCcy;
    }

    public void setInitCcy(String initCcy) {
        this.initCcy = initCcy;
    }

}