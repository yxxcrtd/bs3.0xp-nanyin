/*
 * 文件名：PreTrdRepoTrade002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王伟
 * 修改时间：2019年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.pretrdrepotrade.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.lend.dto.FinTradeProductDto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.validator.PreTrdRepoTrade002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@PageId("PreTrdRepoTrade002")
@ValidLogic(targetClass = PreTrdRepoTrade002DtoValidator.class, groups = ValidateGroupEdit.class)
public class PreTrdRepoTrade002Dto extends TrdFinProductDealTotal  implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -441611713511199822L;
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 有效状态
     */
    private String validStatus;

    @NotEmpty(fieldName = "清算速度", groups = ValidateGroupEdit.class)
    private String settleSpeedText;

    private String settleSpeedStr;

    public String getSettleSpeedStr() {
        if (StringUtil.equals(this.getSettleSpeedText(),"0")){
            return DictCodeList.清算速度_T_0.getCodeName();
        }else if (StringUtil.equals(this.getSettleSpeedText(),"1")){
            return DictCodeList.清算速度_T_1.getCodeName();
        }
        return "";
    }

    public void setSettleSpeedStr(String settleSpeedStr) {
        this.settleSpeedStr = settleSpeedStr;
    }

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 画面模式
     */
    private String pageModel;

    /**
     * 工作流状态
     */
    private String workflowStatus;

    /**
     * 有效状态Text
     */
    private String validStatusText;

    /**
     * 实际占款天数
     */
    @NotEmpty(fieldName = "实际占款天数", groups = ValidateGroupEdit.class)
    private Long actCapDays;

    /**
     * 交易日历
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.calendarId", groups = ValidateGroupEdit.class)
    private String calendarId;

    /**
     * 计息基础
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.basis", groups = ValidateGroupEdit.class)
    private String basis;

    /**
     * 应计利息
     */
    private BigDecimal rebackIntAmt;

    /**
     * 到期结算金额
     */
    @NotEmpty(fieldName = "到期结算金额", groups = ValidateGroupEdit.class)
    private BigDecimal mdateSettleAmt;

    /**
     * 证券托管户
     */
    @NotEmpty(fieldName = "托管账号", groups = ValidateGroupEdit.class)
    private String secAcctId;


    /**
     * 树形菜单父节点Id
     */
    private String parentId;
    /**
     * 树形菜单子节点Id
     */
    private String childId;

    /**
     * 交易方向Text
     */
    private String psText;

    private String businessData;

    /**
     * 交易日期Text
     */
    private String tradeDateText;

    /**
     * 币种Text
     */
    private String ccyText;

    /**
     * 有效时间Text
     */
    private String effTimeText;

    private String effTimeStr;

    @NotEmpty(fieldName = "有效时间", groups = ValidateGroupEdit.class)
    public String getEffTimeStr() {
        return effTimeStr;
    }

    public void setEffTimeStr(String effTimeStr) {
        this.effTimeStr = effTimeStr;
    }

    /**
     * 债券币种
     */
    private String bondCcy;

    /**
     * 结算币种
     */
    private String settleCcy;
    /**
     * 组合名称
     */
    private String portfolioName;
    /**
     * 债券代码
     */
    private String finprodMarketId;
    /**
     * 债券名称
     */
    private String finprodAbbr;
    /**
     * 券面总额
     */
    private BigDecimal pledgeAmt;
    /**
     * 折算比例
     */
    private BigDecimal pledgeRatio;


    private List<PreTrdRepoTrade002Dto> preTrdRepoTrade002DtoList;

    /**
     * 委托代码
     */

    private String entrustId;

    private  TrdProductDeal TrdProductDeal;
    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 登记托管机构
     */
    private String registOrg;

    private String registOrgStr;

    public String getRegistOrgStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.登记托管机构,this.getRegistOrg());
    }

    public void setRegistOrgStr(String registOrgStr) {
        this.registOrgStr = registOrgStr;
    }

    /**
     * 资金账户
     */
//    @NotEmpty(fieldName = "资金账户", groups = ValidateGroupEdit.class)
    private String acctNo;
    /**
     * 资金账户名
     */
    private String acctName;

    /**
     * 资金开户行
     */
    private String acctBankDep;

    /**
     * 资金开户行支付系统行号
     */
    private String acctBankDepoPayno;

    /**
     * 撤销说明
     */
    private String revokeReason;

    /**
     * 委托的金额
     */
    private BigDecimal entrustAmt;

    /**
     * 委托的回购利率
     */
    @NotEmpty(fieldName = "回购利率", groups = ValidateGroupEdit.class)
    private BigDecimal entrustPrice;

    private BigDecimal entrustPriceBack;

    /**
     * 委托的回购起息日
     */
    private Date entrustVdate;

    /**
     * 委托的回购到期日
     */
    private Date entrustMdate;

    /**
     * 回购期限
     */
    @NotEmpty(fieldName = "回购期限", groups = ValidateGroupEdit.class)
    private Long repoTerm;
    /**
     * 期限品种
     */
    @NotEmpty(fieldName = "期限品种", groups = ValidateGroupEdit.class)
    private String termType;

    /**
     * 首次结算方式
     */
    @NotEmpty(fieldName = "首次结算方式", groups = ValidateGroupEdit.class)
    private String deliveryType;
    /**
     * 到期结算方式
     */
    @NotEmpty(fieldName = "到期结算方式", groups = ValidateGroupEdit.class)
    private String mDeliveryType;

    /**
     * 清算类型
     */
    @NotEmpty(fieldName = "清算类型", groups = ValidateGroupEdit.class)
    private String clearType;

    /**
     * 交易场所
     */
    @NotEmpty(fieldName = "交易场所", groups = ValidateGroupEdit.class)
    private String tradeMarket;

    /**
     * 补充条款
     */
    private String remark;
    /**
     * 质押券信息
     */
    private BussRepoSecDto bussRepoSecPtl;

    /**
     * 质押券信息列表
     */
    private List<PledgeBondInfoDto> pledgeBondList;
    /**
     * 质押券信息列表明细
     */
    private List<PledgeBondInfoDto> pledgeBondListDetailed;
    /**
     * 组合交易
     */
    private List<PtlTrade> ptlTrade;

    /**
     * 风险试算实体类
     */
    private List<RiskTrialRetDto> riskTrialRetDto;

    /**
     * 金融产品交易汇总业务流程
     * */
    private WflTrdFinProductDealTotal wflTrdFinProductDealTotal;

    /**
     * 金融产品交易（预录入）业务流程
     */
    private WflTrdProductDealPre wflTrdProductDealPre;

    /**
     * 金融产品交易（预录入）业务流程附属表信息
     */
    private WflTrdProductDealAddPre wflTrdProductDealAddPre;

    /**
     * 金融产品交易（预录入）业务流程其他信息
     */
    private WflTrdProductDealOthPre wflTrdProductDealOthPre;

    /**
     * 金融产品交易汇总
     * */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 金融产品交易（预录入）
     */
    private TrdProductDealPre trdProductDealPre;

    /**
     * 金融产品交易（预录入）附属表信息
     */
    private TrdProductDealAddPre trdProductDealAddPre;

    /**
     * 金融产品分类
     */
    private FinProductType finProductType;

    /**
     * 交易类金融产品
     */
    @Valid
    private FinTradeProductDto finTradeProduct;

    /**
     * 交易类金融产品附属表信息
     */
    private FinTradeProductAdd finTradeProductAdd;
    /**
     * 回购类型：质押式协议回购，质押式竞价回购
     */

    private String repoType;

    /**
     * 取得 汇总交易编号
     * @return 汇总交易编号
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getTotleTradeId() {
        return super.getTotleTradeId();
    }


    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }
    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     *
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    @Override
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getFinprodType2() {
        return super.getFinprodType2();
    }

    /**
     * 取得 交易日期
     *
     * @return 交易日期
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.tradeDate", groups = ValidateGroupEdit.class)
    @Override
    public Date getTradeDate() {
        return super.getTradeDate();
    }

    public List<PledgeBondInfoDto> getPledgeBondListDetailed() {
        return pledgeBondListDetailed;
    }

    public void setPledgeBondListDetailed(List<PledgeBondInfoDto> pledgeBondListDetailed) {
        this.pledgeBondListDetailed = pledgeBondListDetailed;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }


    /**
     * 取得 起息日
     *
     * @return 起息日
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.vdate", groups = ValidateGroupEdit.class)
    @Override
    public Date getVdate() {
        return super.getVdate();
    }


    /**
     * 取得 到期日
     *
     * @return 到期日
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.mdate", groups = ValidateGroupEdit.class)
    @Override
    public Date getMdate() {
        return super.getMdate();
    }

    /**
     * 获取 交易币种
     *
     * @rturn ccy
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.ccy", groups = ValidateGroupEdit.class)
    @Override
    public String getCcy() {
        return super.getCcy();
    }

    /**
     * 获取 交易方向
     *
     * @rturn ps
     */
    @NotEmpty(fieldName = "TrdRepoTrade002.ps", groups = ValidateGroupEdit.class)
    @Override
    public String getPs() {
        return super.getPs();
    }

    /**
     * 获取 交易金额
     * @return tradeAmt
     */

    @Override
    public BigDecimal getTradeAmt() {
        return super.getTradeAmt();
    }

    /**
     * 获取 交易对手
     * @return counterId
     */
    @NotEmpty(fieldName = "交易对手", groups = ValidateGroupEdit.class)
    @Override
    public String getCounterId() {
        return super.getCounterId();
    }

    /**
     * 获取 对手方交易员
     * @return counterTrader
     */

    @Override
    public String getCounterTrader() {
        return super.getCounterTrader();
    }

   

    /**
     * 获取 有效状态
     * @rturn 有效状态
     */
    public String getValidStatus() {
        return this.validStatus;
    }

    /**
     * 设置 有效状态
     * @param validStatus 有效状态
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public com.joyintech.tams.common.base.entity.TrdProductDeal getTrdProductDeal() {
        return TrdProductDeal;
    }

    public void setTrdProductDeal(com.joyintech.tams.common.base.entity.TrdProductDeal trdProductDeal) {
        TrdProductDeal = trdProductDeal;
    }

    /**
     * 获取 操作类型
     * @rturn 操作类型
     */
    public String getOperateType() {
        return this.operateType;
    }

    /**
     * 设置 操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取 画面模式
     * @rturn 画面模式
     */
    public String getPageModel() {
        return this.pageModel;
    }

    /**
     * 设置 画面模式
     * @param pageModel 画面模式
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 工作流状态
     * @rturn 工作流状态
     */
    public String getWorkflowStatus() {
        return this.workflowStatus;
    }

    /**
     * 设置 工作流状态
     * @param workflowStatus 工作流状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    /**
     * 获取 实际占款天数
     * @return actCapDays.
     */
    public Long getActCapDays() {
        return actCapDays;
    }

    /**
     * 设置 实际占款天数
     * @param actCapDays actCapDays
     */
    public void setActCapDays(Long actCapDays) {
        this.actCapDays = actCapDays;
    }

    /**
     * 获取 交易日历
     * @rturn calendarId
     */
    public String getCalendarId() {
        return this.calendarId;
    }

    /**
     * 设置 交易日历
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 计息基础
     * @rturn basis
     */
    public String getBasis() {
        return this.basis;
    }

    /**
     * 设置 计息基础
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }

    /**
     * 获取 应计利息
     * @rturn rebackIntAmt
     */
    public BigDecimal getRebackIntAmt() {
        return this.rebackIntAmt;
    }

    /**
     * 设置 应计利息
     * @param rebackIntAmt rebackIntAmt
     */
    public void setRebackIntAmt(BigDecimal rebackIntAmt) {
        this.rebackIntAmt = rebackIntAmt;
    }

    /**
     * 获取 到期结算金额
     * @rturn mdateSettleAmt
     */
    public BigDecimal getMdateSettleAmt() {
        return this.mdateSettleAmt;
    }

    /**
     * 设置 到期结算金额
     * @param mdateSettleAmt mdateSettleAmt
     */
    public void setMdateSettleAmt(BigDecimal mdateSettleAmt) {
        this.mdateSettleAmt = mdateSettleAmt;
    }

    /**
     * 获取 证券托管户
     * @rturn secAcctId
     */
    public String getSecAcctId() {
        return this.secAcctId;
    }

    /**
     * 设置 证券托管户
     * @param secAcctId secAcctId
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    /**
     * 获取 证券账户名称
     * @return
     */
    public String getSecAcctName() {
        return secAcctName;
    }

    /**
     * 设置 证券账户名称
     * @param secAcctName
     */
    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    /**
     * 获取 登记托管机构
     * @return
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg;
    }

    /**
     * 获取 资金账户
     * @return
     */
    public String getAcctNo() {
        return acctNo;
    }

    /**
     * 设置 资金账户
     * @param acctNo
     */
    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    /**
     * 获取 资金账户名
     * @return
     */
    public String getAcctName() {
        return acctName;
    }

    /**
     * 设置 资金账户名
     * @param acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    /**
     * 获取 资金开户行
     * @return
     */
    public String getAcctBankDep() {
        return acctBankDep;
    }

    /**
     * 设置 资金开户行
     * @param acctBankDep
     */
    public void setAcctBankDep(String acctBankDep) {
        this.acctBankDep = acctBankDep;
    }

    /**
     * 获取 资金开户行支付系统行号
     * @return
     */
    public String getAcctBankDepoPayno() {
        return acctBankDepoPayno;
    }

    /**
     * 设置 资金开户行支付系统行号
     * @param acctBankDepoPayno
     */
    public void setAcctBankDepoPayno(String acctBankDepoPayno) {
        this.acctBankDepoPayno = acctBankDepoPayno;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    /**
     * 获取 撤销说明
     * @rturn 撤销说明
     */
    public String getRevokeReason() {
        return this.revokeReason;
    }

    /**
     * 设置 撤销说明
     * @param revokeReason 撤销说明
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }

    /**
     * 获取 委托的金额
     * @rturn 委托的金额
     */
    public BigDecimal getEntrustAmt() {
        return this.entrustAmt;
    }

    /**
     * 设置 委托的金额
     * @param entrustAmt 委托的金额
     */
    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    /**
     * 获取 委托的回购利率
     * @rturn entrustPrice
     */
    public BigDecimal getEntrustPrice() {
        return this.entrustPrice;
    }

    /**
     * 设置 委托的回购利率
     * @param  entrustPrice
     */
    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getEntrustPriceBack() {
        return entrustPriceBack;
    }

    public void setEntrustPriceBack(BigDecimal entrustPriceBack) {
        this.entrustPriceBack = entrustPriceBack;
    }

    /**
     * 获取 委托的回购起息日
     * @rturn entrustVdate
     */
    public Date getEntrustVdate() {
        return this.entrustVdate;
    }

    /**
     * 设置 委托的回购起息日
     * @param entrustVdate entrustVdate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEntrustVdate(Date entrustVdate) {
        this.entrustVdate = entrustVdate;
    }

    /**
     * 获取 委托的回购到期日
     * @rturn 委托的回购到期日
     */
    public Date getEntrustMdate() {
        return this.entrustMdate;
    }

    /**
     * 设置 委托的回购到期日
     * @param entrustMdate entrustMdate
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEntrustMdate(Date entrustMdate) {
        this.entrustMdate = entrustMdate;
    }

    @Override
    public Long getRepoTerm() {
        return repoTerm;
    }

    public void setRepoTerm(Long repoTerm) {
        this.repoTerm = repoTerm;
    }

    @Override
    public String getTermType() {
        return termType;
    }

    @Override
    public void setTermType(String termType) {
        this.termType = termType;
    }

    @Override
    public String getDeliveryType() {
        return deliveryType;
    }

    @Override
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String getmDeliveryType() {
        return mDeliveryType;
    }

    @Override
    public void setmDeliveryType(String mDeliveryType) {
        this.mDeliveryType = mDeliveryType;
    }

    public String getDeliveryTypeText() {
        return SysDataUtil.getNameFromDataDict(DictCode.结算方式, getDeliveryType());
    }

    public String getmDeliveryTypeText() {
        return SysDataUtil.getNameFromDataDict(DictCode.结算方式, getmDeliveryType());
    }

    @Override
    public String getClearType() {
        return clearType;
    }

    @Override
    public void setClearType(String clearType) {
        this.clearType = clearType;
    }

    public String getClearTypeText(){
        return SysDataUtil.getNameFromDataDict(DictCode.清算类型, getClearType());
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPsText() {
        return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
    }
    /**
     * 取得 有效状态
     * @return 有效状态
     */
    public String getValidStatusText() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, validStatus);
    }
    /**
     * 获取 有效时间
     * @return effTime
     */
    @Override
    public Date getEffTime() {
        return super.getEffTime();
    }

    @Override
    public String getTradeMarket() {
        return tradeMarket;
    }

    @Override
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 质押券信息
     * @rturn 质押券信息
     */
    public BussRepoSecDto getBussRepoSecPtl() {
        return this.bussRepoSecPtl;
    }

    /**
     * 设置 质押券信息
     * @param bussRepoSecPtl 质押券信息
     */
    public void setBussRepoSecPtl(BussRepoSecDto bussRepoSecPtl) {
        this.bussRepoSecPtl = bussRepoSecPtl;
    }

    /**
     * 获取 组合交易
     * @rturn 组合交易
     */
    public List<PtlTrade> getPtlTrade() {
        return this.ptlTrade;
    }

    /**
     * 设置 组合交易
     * @param ptlTrade 组合交易
     */
    public void setPtlTrade(List<PtlTrade> ptlTrade) {
        this.ptlTrade = ptlTrade;
    }

    /**
     * 获取 风险试算实体类
     * @rturn 风险试算实体类
     */
    public List<RiskTrialRetDto> getRiskTrialRetDto() {
        return this.riskTrialRetDto;
    }

    /**
     * 设置 风险试算实体类
     * @param riskTrialRetDto 风险试算实体类
     */
    public void setRiskTrialRetDto(List<RiskTrialRetDto> riskTrialRetDto) {
        this.riskTrialRetDto = riskTrialRetDto;
    }

    /**
     * 获取 质押券信息列表
     * @return 质押券信息列表.
     */
    public List<PledgeBondInfoDto> getPledgeBondList() {
        return pledgeBondList;
    }

    /**
     * 设置 质押券信息列表
     * @param pledgeBondList 质押券信息列表
     */
    public void setPledgeBondList(List<PledgeBondInfoDto> pledgeBondList) {
        this.pledgeBondList = pledgeBondList;
    }

    /**
     * 取得 金融产品交易汇总业务流程
     * @return 金融产品交易汇总业务流程
     */
    public WflTrdFinProductDealTotal getWflTrdFinProductDealTotal() {
        return wflTrdFinProductDealTotal;
    }

    /**
     * 设置 金融产品交易汇总业务流程
     * @param wflTrdFinProductDealTotal 金融产品交易汇总业务流程
     */
    public void setWflTrdFinProductDealTotal(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
        this.wflTrdFinProductDealTotal = wflTrdFinProductDealTotal;
    }

    /**
     * 取得 金融产品交易（预录入）业务流程
     * @return 金融产品交易（预录入）业务流程
     */
    public WflTrdProductDealPre getWflTrdProductDealPre() {
        return wflTrdProductDealPre;
    }

    /**
     * 设置 金融产品交易（预录入）业务流程
     * @param wflTrdProductDealPre 金融产品交易（预录入）业务流程
     */
    public void setWflTrdProductDealPre(WflTrdProductDealPre wflTrdProductDealPre) {
        this.wflTrdProductDealPre = wflTrdProductDealPre;
    }

    public String getSettleSpeedText() {
        return settleSpeedText;
    }

    public void setSettleSpeedText(String settleSpeedText){
        this.settleSpeedText = settleSpeedText;
    }

    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    public void setPsText(String psText) {
        this.psText = psText;
    }

    /**
     * 取得 金融产品交易（预录入）业务流程附属表信息
     * @return 金融产品交易（预录入）业务流程附属表信息
     */
    public WflTrdProductDealAddPre getWflTrdProductDealAddPre() {
        return wflTrdProductDealAddPre;
    }

    /**
     * 设置 金融产品交易（预录入）业务流程附属表信息
     * @param wflTrdProductDealAddPre 金融产品交易（预录入）业务流程附属表信息
     */
    public void setWflTrdProductDealAddPre(WflTrdProductDealAddPre wflTrdProductDealAddPre) {
        this.wflTrdProductDealAddPre = wflTrdProductDealAddPre;
    }

    /**
     * 取得 金融产品交易（预录入）业务流程其他信息
     * @return 金融产品交易（预录入）业务流程其他信息
     */
    public WflTrdProductDealOthPre getWflTrdProductDealOthPre() {
        return wflTrdProductDealOthPre;
    }

    /**
     * 设置 金融产品交易（预录入）业务流程其他信息
     * @param wflTrdProductDealOthPre 金融产品交易（预录入）业务流程其他信息
     */
    public void setWflTrdProductDealOthPre(WflTrdProductDealOthPre wflTrdProductDealOthPre) {
        this.wflTrdProductDealOthPre = wflTrdProductDealOthPre;
    }

    /**
     * 取得 金融产品交易汇总
     * @return 金融产品交易汇总
     */
    public TrdFinProductDealTotal getTrdFinProductDealTotal() {
        return trdFinProductDealTotal;
    }

    /**
     * 设置 金融产品交易汇总
     * @param trdFinProductDealTotal 金融产品交易汇总
     */
    public void setTrdFinProductDealTotal(TrdFinProductDealTotal trdFinProductDealTotal) {
        this.trdFinProductDealTotal = trdFinProductDealTotal;
    }

    /**
     * 取得 金融产品交易（预录入）
     * @return 金融产品交易（预录入）
     */
    public TrdProductDealPre getTrdProductDealPre() {
        return trdProductDealPre;
    }

    /**
     * 设置 金融产品交易（预录入）
     * @param trdProductDealPre 金融产品交易（预录入）
     */
    public void setTrdProductDealPre(TrdProductDealPre trdProductDealPre) {
        this.trdProductDealPre = trdProductDealPre;
    }

    /**
     * 取得 金融产品交易（预录入）附属表信息
     * @return 金融产品交易（预录入）附属表信息
     */
    public TrdProductDealAddPre getTrdProductDealAddPre() {
        return trdProductDealAddPre;
    }

    /**
     * 设置 金融产品交易（预录入）附属表信息
     * @param trdProductDealAddPre 金融产品交易（预录入）附属表信息
     */
    public void setTrdProductDealAddPre(TrdProductDealAddPre trdProductDealAddPre) {
        this.trdProductDealAddPre = trdProductDealAddPre;
    }

    /**
     * 取得 金融产品分类
     * @return 金融产品分类
     */
    public FinProductType getFinProductType() {
        return finProductType;
    }

    /**
     * 设置 金融产品分类
     * @param finProductType 金融产品分类
     */
    public void setFinProductType(FinProductType finProductType) {
        this.finProductType = finProductType;
    }

    /**
     * 取得 交易类金融产品
     * @return 交易类金融产品
     */
    public FinTradeProductDto getFinTradeProduct() {
        return finTradeProduct;
    }

    /**
     * 设置 交易类金融产品
     * @param finTradeProduct 交易类金融产品
     */
    public void setFinTradeProduct(FinTradeProductDto finTradeProduct) {
        this.finTradeProduct = finTradeProduct;
    }

    /**
     * 取得 交易类金融产品附属表信息
     * @return 交易类金融产品附属表信息
     */
    public FinTradeProductAdd getFinTradeProductAdd() {
        return finTradeProductAdd;
    }

    /**
     * 设置 交易类金融产品附属表信息
     * @param finTradeProductAdd 交易类金融产品附属表信息
     */
    public void setFinTradeProductAdd(FinTradeProductAdd finTradeProductAdd) {
        this.finTradeProductAdd = finTradeProductAdd;
    }

    //List<金融产品交易（预录入）业务流程dto>
    List<WflTrdProductDealPre> wflTrdProductDealPreList;
    //List<金融产品交易（预录入）业务流程附属表dto>
    List<WflTrdProductDealAddPre> wflTrdProductDealAddPreList;
    //List<金融产品交易（预录入）业务流程其他信息dto>
    List<WflTrdProductDealOthPre>  wflTrdProductDealOthPreList;
    //List<金融产品交易（预录入）dto>
    List<TrdProductDealPre> trdProductDealPreList;
    //List<金融产品交易（预录入）附属表dto>
    List<TrdProductDealAddPre> trdProductDealAddPreList;
    //预录入交易回购质押品
    List<FinDealPledgePre> finDealPledgePreList;

    public List<WflTrdProductDealPre> getWflTrdProductDealPreList() {
        return wflTrdProductDealPreList;
    }

    public void setWflTrdProductDealPreList(List<WflTrdProductDealPre> wflTrdProductDealPreList) {
        this.wflTrdProductDealPreList = wflTrdProductDealPreList;
    }

    public List<WflTrdProductDealAddPre> getWflTrdProductDealAddPreList() {
        return wflTrdProductDealAddPreList;
    }

    public void setWflTrdProductDealAddPreList(List<WflTrdProductDealAddPre> wflTrdProductDealAddPreList) {
        this.wflTrdProductDealAddPreList = wflTrdProductDealAddPreList;
    }

    public List<WflTrdProductDealOthPre> getWflTrdProductDealOthPreList() {
        return wflTrdProductDealOthPreList;
    }

    public void setWflTrdProductDealOthPreList(List<WflTrdProductDealOthPre> wflTrdProductDealOthPreList) {
        this.wflTrdProductDealOthPreList = wflTrdProductDealOthPreList;
    }

    public List<TrdProductDealPre> getTrdProductDealPreList() {
        return trdProductDealPreList;
    }

    public void setTrdProductDealPreList(List<TrdProductDealPre> trdProductDealPreList) {
        this.trdProductDealPreList = trdProductDealPreList;
    }

    public List<TrdProductDealAddPre> getTrdProductDealAddPreList() {
        return trdProductDealAddPreList;
    }

    public void setTrdProductDealAddPreList(List<TrdProductDealAddPre> trdProductDealAddPreList) {
        this.trdProductDealAddPreList = trdProductDealAddPreList;
    }

    public List<FinDealPledgePre> getFinDealPledgePreList() {
        return finDealPledgePreList;
    }

    public void setFinDealPledgePreList(List<FinDealPledgePre> finDealPledgePreList) {
        this.finDealPledgePreList = finDealPledgePreList;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    public String getTradeDateText() {
        return tradeDateText;
    }

    public void setTradeDateText(String tradeDateText) {
        this.tradeDateText = tradeDateText;
    }

    public String getCcyText() {
        return ccyText;
    }

    public void setCcyText(String ccyText) {
        this.ccyText = ccyText;
    }

    /**
     * 取得 有效时间
     * @return 有效时间
     */
    public String getEffTimeText() {
        if (StringUtil.isNotEmpty(getEffTime())){
            return DateUtil.formatDateTime(getEffTime()).substring(11,16);
        }
        return effTimeText;
    }

    /**
     * 设置 有效时间
     * @param effTimeText 有效时间
     */
    public void setEffTimeText(String effTimeText) {
        this.effTimeText = effTimeText;
    }


    public String getBondCcy() {
        return bondCcy;
    }

    public void setBondCcy(String bondCcy) {
        this.bondCcy = bondCcy;
    }

    public String getSettleCcy() {
        return settleCcy;
    }

    public void setSettleCcy(String settleCcy) {
        this.settleCcy = settleCcy;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    public List<PreTrdRepoTrade002Dto> getPreTrdRepoTrade002DtoList() {
        return preTrdRepoTrade002DtoList;
    }

    public void setPreTrdRepoTrade002DtoList(List<PreTrdRepoTrade002Dto> preTrdRepoTrade002DtoList) {
        this.preTrdRepoTrade002DtoList = preTrdRepoTrade002DtoList;
    }
}
