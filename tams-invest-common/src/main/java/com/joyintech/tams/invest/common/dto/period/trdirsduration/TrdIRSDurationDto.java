package com.joyintech.tams.invest.common.dto.period.trdirsduration;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.dto.period.common.BaseTotalTradeInfoDto;

/**
 * 利率互换存续期Dto
 * 
 * @author : renping
 * @version V1.0
 * @Project: tams-invest-parent
 * @Package com.joyintech.period.trdirsduration.dto
 * @Description: 页面查询参数
 * @date Date : 2019年08月10日
 */
public class TrdIRSDurationDto extends BaseEntity {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8887164315779376712L;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 起息日Start
     */
    private Date vdateStart;

    /**
     * 起息日End
     */
    private Date vdateEnd;

    /**
     * 到期日Start
     */
    private Date mdateStart;

    /**
     * 到期日End
     */
    private Date mdateEnd;

    /**
     * 支付日Start
     */
    private Date payDateStart;

    /**
     * 支付日End
     */
    private Date payDateEnd;

    /**
     * 投管业务类型
     */
    private String pridBussType;

    /**
     * 交易编号(画面.业务编号)
     */
    private String busiCode;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 金融产品代码(画面.交易编号)
     */
    private String finprodId;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 互换方式
     */
    private String irsType;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 期限(天)
     */
    private Long term;

    /**
     * 名义本金币种
     */
    private String ccy;

    /**
     * 名义本金（万元）
     */
    private BigDecimal prin;

    /**
     * 剩余名义本金（万元）
     */
    private BigDecimal restPrin;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 收付息类型
     */
    private String irsPsIntType;

    /**
     * 固定利率计息开始日
     */
    private Date fixVdate;

    /**
     * 固定利率计息结束日
     */
    private Date fixMdate;

    /**
     * 固定利率理论支付日
     */
    private Date fixPayDate;

    /**
     * 固定利率实际支付日
     */
    private Date fixActPayDate;

    /**
     * 固定利息
     */
    private BigDecimal fixIntAmt;

    /**
     * 固定利率现金流代码
     */
    private String fixCashId;

    /**
     * 浮动利息计息开始日
     */
    private Date floatVdate;

    /**
     * 浮动利息计息结束日
     */
    private Date floatMdate;

    /**
     * 浮动利息理论支付日
     */
    private Date floatPayDate;

    /**
     * 浮动利息实际支付日
     */
    private Date floatActPayDate;

    /**
     * 浮动利息
     */
    private BigDecimal floatIntAmt;

    /**
     * 浮动利息现金流代码
     */
    private String floatCashId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 提前终止日
     */
    private Date beforeEndDate;

    /**
     * 提前终止支付日
     */
    private Date beforeEndPayDate;

    /**
     * 提前终止名义本金（万元）
     */
    private BigDecimal beforeEndPrin;

    /**
     * 提前终止收付方向
     */
    private String beforeEndPs;

    /**
     * 提前终止日提前终止收付金额
     */
    private BigDecimal beforeEndAmt;

    /**
     * 有效状态
     */
    private String statusValid;

    /**
     * 现金流类型
     */
    private String cashType;

    /**
     * 平安业务类型
     */
    private String paBussType;

    /**
     * 是否需要跳过插入流程表数据
     */
    private Boolean needSkipInsertWfl;

    /**
     * 撤销理由
     */
    private String revokeReason;

    /**
     * 所有相关表，贯穿整个流程
     */
    private BaseTotalTradeInfoDto baseTotalTradeInfoDto;

    /**
     * 利率互换存续期dto
     */
    private TrdIRSDurDbInertDto trdIRSDurDbInertDto;

    /**
     * 获取 baseTotalTradeInfoDto
     * 
     * @return baseTotalTradeInfoDto.
     */
    public BaseTotalTradeInfoDto getBaseTotalTradeInfoDto() {
        return baseTotalTradeInfoDto;
    }

    /**
     * 设置 baseTotalTradeInfoDto
     * 
     * @param baseTotalTradeInfoDto baseTotalTradeInfoDto
     */
    public void setBaseTotalTradeInfoDto(BaseTotalTradeInfoDto baseTotalTradeInfoDto) {
        this.baseTotalTradeInfoDto = baseTotalTradeInfoDto;
    }

    /**
     * 获取 trdIRSDurDbInertDto
     *
     * @return trdIRSDurDbInertDto
     */
    public TrdIRSDurDbInertDto getTrdIRSDurDbInertDto() {
        return trdIRSDurDbInertDto;
    }

    /**
     * 设置 trdIRSDurDbInertDto
     *
     * @param trdIRSDurDbInertDto trdIRSDurDbInertDto
     */
    public void setTrdIRSDurDbInertDto(TrdIRSDurDbInertDto trdIRSDurDbInertDto) {
        this.trdIRSDurDbInertDto = trdIRSDurDbInertDto;
    }

    /**
     * 获取 revokeReason
     *
     * @return revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }

    /**
     * 设置 revokeReason
     *
     * @param revokeReason revokeReason
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 statusValid
     *
     * @return statusValid
     */
    public String getStatusValid() {
        return statusValid;
    }

    /**
     * 设置 statusValid
     *
     * @param statusValid statusValid
     */
    public void setStatusValid(String statusValid) {
        this.statusValid = statusValid;
    }

    /**
     * 获取 busiCode
     *
     * @return busiCode
     */
    public String getBusiCode() {
        return busiCode;
    }

    /**
     * 设置 busiCode
     *
     * @param busiCode busiCode
     */
    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    /**
     * 获取 cashType
     *
     * @return cashType
     */
    public String getCashType() {
        return cashType;
    }

    /**
     * 设置 cashType
     *
     * @param cashType cashType
     */
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    /**
     * 获取 portfolioId
     *
     * @return portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 portfolioName
     *
     * @return portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     *
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 finprodId
     *
     * @return finprodId
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     *
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 paBussType
     *
     * @return paBussType
     */
    public String getPaBussType() {
        return paBussType;
    }

    /**
     * 设置 paBussType
     *
     * @param paBussType paBussType
     */
    public void setPaBussType(String paBussType) {
        this.paBussType = paBussType;
    }

    /**
     * 获取 needSkipInsertWfl
     *
     * @return needSkipInsertWfl
     */
    public Boolean getNeedSkipInsertWfl() {
        return needSkipInsertWfl;
    }

    /**
     * 设置 needSkipInsertWfl
     *
     * @param needSkipInsertWfl needSkipInsertWfl
     */
    public void setNeedSkipInsertWfl(Boolean needSkipInsertWfl) {
        this.needSkipInsertWfl = needSkipInsertWfl;
    }

    /**
     * 获取 tradeDate
     * 
     * @return tradeDate.
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 tradeDate
     * 
     * @param tradeDate tradeDate
     */
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 获取 irsType
     * 
     * @return irsType.
     */
    public String getIrsType() {
        return irsType;
    }

    /**
     * 设置 irsType
     * 
     * @param irsType irsType
     */
    public void setIrsType(String irsType) {
        this.irsType = irsType;
    }

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 mdate
     * 
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 mdate
     * 
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 term
     * 
     * @return term.
     */
    public Long getTerm() {
        return term;
    }

    /**
     * 设置 term
     * 
     * @param term term
     */
    public void setTerm(Long term) {
        this.term = term;
    }

    /**
     * 获取 prin
     * 
     * @return prin.
     */
    public BigDecimal getPrin() {
        return prin;
    }

    /**
     * 设置 prin
     * 
     * @param prin prin
     */
    public void setPrin(BigDecimal prin) {
        this.prin = prin;
    }

    /**
     * 获取 restPrin
     * 
     * @return restPrin.
     */
    public BigDecimal getRestPrin() {
        return restPrin;
    }

    /**
     * 获取 restPrin*10000
     * 
     * @return restPrin*10000.
     */
    public BigDecimal getRestPrinAfter() {
        return ArithmeticUtil.multiply(restPrin, new BigDecimal(10000));
    }

    /**
     * 设置 restPrin
     * 
     * @param restPrin restPrin
     */
    public void setRestPrin(BigDecimal restPrin) {
        this.restPrin = restPrin;
    }

    /**
     * 获取 counterId
     * 
     * @return counterId.
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 counterId
     * 
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    /**
     * 获取 irsPsIntType
     * 
     * @return irsPsIntType.
     */
    public String getIrsPsIntType() {
        return irsPsIntType;
    }

    /**
     * 设置 irsPsIntType
     * 
     * @param irsPsIntType irsPsIntType
     */
    public void setIrsPsIntType(String irsPsIntType) {
        this.irsPsIntType = irsPsIntType;
    }

    /**
     * 获取 fixVdate
     * 
     * @return fixVdate.
     */
    public Date getFixVdate() {
        return fixVdate;
    }

    /**
     * 设置 fixVdate
     * 
     * @param fixVdate fixVdate
     */
    public void setFixVdate(Date fixVdate) {
        this.fixVdate = fixVdate;
    }

    /**
     * 获取 fixMdate
     * 
     * @return fixMdate.
     */
    public Date getFixMdate() {
        return fixMdate;
    }

    /**
     * 设置 fixMdate
     * 
     * @param fixMdate fixMdate
     */
    public void setFixMdate(Date fixMdate) {
        this.fixMdate = fixMdate;
    }

    /**
     * 获取 fixPayDate
     * 
     * @return fixPayDate.
     */
    public Date getFixPayDate() {
        return fixPayDate;
    }

    /**
     * 设置 fixPayDate
     * 
     * @param fixPayDate fixPayDate
     */
    public void setFixPayDate(Date fixPayDate) {
        this.fixPayDate = fixPayDate;
    }

    /**
     * 获取 fixActPayDate
     * 
     * @return fixActPayDate.
     */
    public Date getFixActPayDate() {
        return fixActPayDate;
    }

    /**
     * 设置 fixActPayDate
     * 
     * @param fixActPayDate fixActPayDate
     */
    public void setFixActPayDate(Date fixActPayDate) {
        this.fixActPayDate = fixActPayDate;
    }

    /**
     * 获取 fixIntAmt
     * 
     * @return fixIntAmt.
     */
    public BigDecimal getFixIntAmt() {
        return fixIntAmt;
    }

    /**
     * 设置 fixIntAmt
     * 
     * @param fixIntAmt fixIntAmt
     */
    public void setFixIntAmt(BigDecimal fixIntAmt) {
        this.fixIntAmt = fixIntAmt;
    }

    /**
     * 获取 fixCashId
     * 
     * @return fixCashId.
     */
    public String getFixCashId() {
        return fixCashId;
    }

    /**
     * 设置 fixCashId
     * 
     * @param fixCashId fixCashId
     */
    public void setFixCashId(String fixCashId) {
        this.fixCashId = fixCashId;
    }

    /**
     * 获取 floatVdate
     * 
     * @return floatVdate.
     */
    public Date getFloatVdate() {
        return floatVdate;
    }

    /**
     * 设置 floatVdate
     * 
     * @param floatVdate floatVdate
     */
    public void setFloatVdate(Date floatVdate) {
        this.floatVdate = floatVdate;
    }

    /**
     * 获取 floatMdate
     * 
     * @return floatMdate.
     */
    public Date getFloatMdate() {
        return floatMdate;
    }

    /**
     * 设置 floatMdate
     * 
     * @param floatMdate floatMdate
     */
    public void setFloatMdate(Date floatMdate) {
        this.floatMdate = floatMdate;
    }

    /**
     * 获取 floatPayDate
     * 
     * @return floatPayDate.
     */
    public Date getFloatPayDate() {
        return floatPayDate;
    }

    /**
     * 设置 floatPayDate
     * 
     * @param floatPayDate floatPayDate
     */
    public void setFloatPayDate(Date floatPayDate) {
        this.floatPayDate = floatPayDate;
    }

    /**
     * 获取 floatActPayDate
     * 
     * @return floatActPayDate.
     */
    public Date getFloatActPayDate() {
        return floatActPayDate;
    }

    /**
     * 设置 floatActPayDate
     * 
     * @param floatActPayDate floatActPayDate
     */
    public void setFloatActPayDate(Date floatActPayDate) {
        this.floatActPayDate = floatActPayDate;
    }

    /**
     * 获取 floatIntAmt
     * 
     * @return floatIntAmt.
     */
    public BigDecimal getFloatIntAmt() {
        return floatIntAmt;
    }

    /**
     * 设置 floatIntAmt
     * 
     * @param floatIntAmt floatIntAmt
     */
    public void setFloatIntAmt(BigDecimal floatIntAmt) {
        this.floatIntAmt = floatIntAmt;
    }

    /**
     * 获取 floatCashId
     * 
     * @return floatCashId.
     */
    public String getFloatCashId() {
        return floatCashId;
    }

    /**
     * 设置 floatCashId
     * 
     * @param floatCashId floatCashId
     */
    public void setFloatCashId(String floatCashId) {
        this.floatCashId = floatCashId;
    }

    /**
     * 获取 irsTypeName
     * 
     * @return irsTypeName.
     */
    public String getIrsTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.互换方式, this.getIrsType());
    }

    /**
     * 获取 irsPsIntTypeName
     * 
     * @return irsPsIntTypeName.
     */
    public String getIrsPsIntTypeName() {
        return SysDataUtil.getNameFromDataDict(DictCode.利率互换收付息类型, this.getIrsPsIntType());
    }

    /**
     * 获取 pridBussTypeName
     * 
     * @return pridBussTypeName.
     */
    public String getPridBussTypeName() {
        String code = this.getPridBussType();
        // 利率互换存续期类型数据字典转为投管业务类型
        if (StringUtil.equals(code, DictCodeListExt2.平安业务类型_利率互换中间收付息.getValue())) {
            code = DictCodeListExt2.利率互换存续期类型_中间收付息.getValue();
        }
        else if (StringUtil.equals(code, DictCodeListExt2.平安业务类型_利率互换到期.getValue())) {
            code = DictCodeListExt2.利率互换存续期类型_到期.getValue();
        }
        else if (StringUtil.equals(code, DictCodeListExt2.平安业务类型_利率互换提前终止.getValue())) {
            code = DictCodeListExt2.利率互换存续期类型_提前终止.getValue();
        }
        return SysDataUtil.getNameFromDataDict(DictCode.利率互换存续期类型, code);
    }

    /**
     * 获取 ccyName
     * 
     * @return ccyName.
     */
    public String getCcyName() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }

    /**
     * 获取 beforeEndDate
     * 
     * @return beforeEndDate.
     */
    public Date getBeforeEndDate() {
        return beforeEndDate;
    }

    /**
     * 设置 beforeEndDate
     * 
     * @param beforeEndDate beforeEndDate
     */
    public void setBeforeEndDate(Date beforeEndDate) {
        this.beforeEndDate = beforeEndDate;
    }

    /**
     * 获取 beforeEndPayDate
     * 
     * @return beforeEndPayDate.
     */
    public Date getBeforeEndPayDate() {
        return beforeEndPayDate;
    }

    /**
     * 设置 beforeEndPayDate
     * 
     * @param beforeEndPayDate beforeEndPayDate
     */
    public void setBeforeEndPayDate(Date beforeEndPayDate) {
        this.beforeEndPayDate = beforeEndPayDate;
    }

    /**
     * 获取 beforeEndPrin
     * 
     * @return beforeEndPrin.
     */
    public BigDecimal getBeforeEndPrin() {
        return beforeEndPrin;
    }
    
    /**
     * 获取 beforeEndPrin*10000
     * 
     * @return beforeEndPrin*10000.
     */
    public BigDecimal getBeforeEndPrinAfter() {
        return ArithmeticUtil.multiply(beforeEndPrin,new BigDecimal(10000));
    }

    /**
     * 设置 beforeEndPrin
     * 
     * @param beforeEndPrin beforeEndPrin
     */
    public void setBeforeEndPrin(BigDecimal beforeEndPrin) {
        this.beforeEndPrin = beforeEndPrin;
    }

    /**
     * 获取 beforeEndPs
     * 
     * @return beforeEndPs.
     */
    public String getBeforeEndPs() {
        return beforeEndPs;
    }

    /**
     * 设置 beforeEndPs
     * 
     * @param beforeEndPs beforeEndPs
     */
    public void setBeforeEndPs(String beforeEndPs) {
        this.beforeEndPs = beforeEndPs;
    }

    /**
     * 获取 beforeEndAmt
     * 
     * @return beforeEndAmt.
     */
    public BigDecimal getBeforeEndAmt() {
        return beforeEndAmt;
    }

    /**
     * 设置 beforeEndAmt
     * 
     * @param beforeEndAmt beforeEndAmt
     */
    public void setBeforeEndAmt(BigDecimal beforeEndAmt) {
        this.beforeEndAmt = beforeEndAmt;
    }

    /**
     * 获取 vdateStart
     * 
     * @return vdateStart.
     */
    public Date getVdateStart() {
        return vdateStart;
    }

    /**
     * 设置 vdateStart
     * 
     * @param vdateStart vdateStart
     */
    public void setVdateStart(Date vdateStart) {
        this.vdateStart = vdateStart;
    }

    /**
     * 获取 vdateEnd
     * 
     * @return vdateEnd.
     */
    public Date getVdateEnd() {
        return vdateEnd;
    }

    /**
     * 设置 vdateEnd
     * 
     * @param vdateEnd vdateEnd
     */
    public void setVdateEnd(Date vdateEnd) {
        this.vdateEnd = vdateEnd;
    }

    /**
     * 获取 mdateStart
     * 
     * @return mdateStart.
     */
    public Date getMdateStart() {
        return mdateStart;
    }

    /**
     * 设置 mdateStart
     * 
     * @param mdateStart mdateStart
     */
    public void setMdateStart(Date mdateStart) {
        this.mdateStart = mdateStart;
    }

    /**
     * 获取 mdateEnd
     * 
     * @return mdateEnd.
     */
    public Date getMdateEnd() {
        return mdateEnd;
    }

    /**
     * 设置 mdateEnd
     * 
     * @param mdateEnd mdateEnd
     */
    public void setMdateEnd(Date mdateEnd) {
        this.mdateEnd = mdateEnd;
    }

    /**
     * 获取 payDateStart
     * 
     * @return payDateStart.
     */
    public Date getPayDateStart() {
        return payDateStart;
    }

    /**
     * 设置 payDateStart
     * 
     * @param payDateStart payDateStart
     */
    public void setPayDateStart(Date payDateStart) {
        this.payDateStart = payDateStart;
    }

    /**
     * 获取 payDateEnd
     * 
     * @return payDateEnd.
     */
    public Date getPayDateEnd() {
        return payDateEnd;
    }

    /**
     * 设置 payDateEnd
     * 
     * @param payDateEnd payDateEnd
     */
    public void setPayDateEnd(Date payDateEnd) {
        this.payDateEnd = payDateEnd;
    }

    /**
     * 获取 pridBussType
     * 
     * @return pridBussType.
     */
    public String getPridBussType() {
        return pridBussType;
    }

    /**
     * 设置 pridBussType
     * 
     * @param pridBussType pridBussType
     */
    public void setPridBussType(String pridBussType) {
        this.pridBussType = pridBussType;
    }

}
