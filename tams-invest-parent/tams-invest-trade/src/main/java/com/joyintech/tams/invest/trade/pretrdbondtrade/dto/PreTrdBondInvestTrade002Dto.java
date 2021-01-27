/*
 * 文件名：PreTrdRepoTrade002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：王伟
 * 修改时间：2019年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.trade.pretrdbondtrade.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDealAddPre;
import com.joyintech.tams.common.base.entity.TrdProductDealPre;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAddPre;
import com.joyintech.tams.common.base.entity.WflTrdProductDealPre;
import com.joyintech.tams.invest.common.dto.common.BussTransDto;
import com.joyintech.tams.invest.common.dto.common.TrdTransfenpeiDto;
import com.joyintech.tams.invest.common.risktrial.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.trade.pretrdbondtrade.validator.PreTrdBondInvestTrade002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 债券交易预录入新建界面dto
 * @author 王伟
 * @version 1.0 2020年1月6日
 * @see PreTrdBondInvestTrade002Dto
 * @since 1.0
 */
@PageId("PreTrdBondInvestTrade002")
@ValidLogic(targetClass = PreTrdBondInvestTrade002DtoValidator.class, groups = {ValidateGroupEdit.class,
        ValidateGroupDelete.class})
public class PreTrdBondInvestTrade002Dto extends TrdFinProductDealTotal implements ProcessEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -441611713511199822L;

    
    /**
     * 交易编号
     */
    private String tradeId;
    /**
     * 指令类型
     */
    private String zhilingtype;
    
    private String type9;
    
    private String finprodName;
    
    /**
     * 委托代码
     */
    
    private String entrustId;
    /**
     * 清算速度
     */
  
    private String settleSpeedStr;
    
    /**
     * 有效状态Text
     */
    private String settleSpeedText;
    
    private String eff_time;
    
    /**
     * 清算类型
     */
    @NotEmpty(fieldName = "PreTrdBondInvestTrade002.clearType", groups = ValidateGroupEdit.class)
    private String clearType;
    
    /**
     * 有效状态
     */
    private String validStatus;

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
     * 证券托管户
     */
    @NotEmpty(fieldName = "PreTrdBondInvestTrade002.secAcctId", groups = ValidateGroupEdit.class)
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 资金账户
     */
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
     * 组合信息
     */
    private BussTransDto bussTransfenpei;
    

    /**
     * 撤销说明
     */
    private String revokeReason;
    
    
    
    private List<TrdTransfenpeiDto> trdTransfenpeiDto;

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
    private List<WflTrdProductDealPre> wflTrdProductDealPreList;

    /**
     * 金融产品交易（预录入）业务流程附属表信息
     */
    private List<WflTrdProductDealAddPre> wflTrdProductDealAddPreList;


    /**
     * 金融产品交易汇总
     * */
    private TrdFinProductDealTotal trdFinProductDealTotal;

    /**
     * 金融产品交易（预录入）
     */
    private List<TrdProductDealPre> trdProductDealPreList;

    /**
     * 金融产品交易（预录入）附属表信息
     */
    private List<TrdProductDealAddPre> trdProductDealAddPreList;
    
    /**
     * 金融产品交易（预录入）业务流程
     */
    private WflTrdProductDealPre wflTrdProductDealPre;

    /**
     * 金融产品交易（预录入）业务流程附属表信息
     */
    private WflTrdProductDealAddPre wflTrdProductDealAddPre;


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

    public String getZhilingtype() {
		return zhilingtype;
	}

	public void setZhilingtype(String zhilingtype) {
		this.zhilingtype = zhilingtype;
	}

	public String getType9() {
		return type9;
	}

	public void setType9(String type9) {
		this.type9 = type9;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getEff_time() {
		return eff_time;
	}

	public void setEff_time(String eff_time) {
		this.eff_time = eff_time;
	}
	
	@Override
    public String getClearType() {
        return clearType;
    }

    @Override
    public void setClearType(String clearType) {
        this.clearType = clearType;
    }

    
	public String getSettleSpeedStr() {
		return settleSpeedStr;
	}

	public void setSettleSpeedStr(String settleSpeedStr) {
		this.settleSpeedStr = settleSpeedStr;
	}

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
//    @NotEmpty(fieldName = "TrdRepoTrade002.tradeAmt", groups = ValidateGroupEdit.class)
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
    @NotEmpty(fieldName = "对手方交易员", groups = ValidateGroupEdit.class)
    @Override
    public String getCounterTrader() {
        return super.getCounterTrader();
    }

    /**
     * 获取 清算速度
     * @return settleSpeed
     */
    @Override
    public Long getSettleSpeed() {
        return super.getSettleSpeed();
    }


    /**
     * 获取 回购期限
     * @return repoTerm
     */
   
    @Override
    public Long getRepoTerm() {
        return super.getRepoTerm();
    }

    /**
     * 获取 期限品种
     * @return termType
     */
    
    @Override
    public String getTermType() {
        return super.getTermType();
    }

    /**
     * 获取 首次结算方式
     * @return deliveryType
     */
    @NotEmpty(fieldName = "PreTrdBondInvestTrade002.deliveryType", groups = ValidateGroupEdit.class)
    @Override
    public String getDeliveryType() {
        return super.getDeliveryType();
    }
    
    /**
     * 获取 有效时间
     * @return effTime
     */
    @NotEmpty(fieldName = "有效时间", groups = ValidateGroupEdit.class)
    @Override
    public Date getEffTime() {
        return super.getEffTime();
    }

    /**
     * 获取 有效时间
     * @return yeild
     */
    @NotEmpty(fieldName = "TrdBondInvestTrade002.yield", groups = ValidateGroupEdit.class)
    @Override
    public BigDecimal getYield() {
        return super.getYield();
    }
    
    /**
     * 获取 到期结算方式
     * @return mDeliveryType
     */
    
    @Override
    public String getmDeliveryType() {
        return super.getmDeliveryType();
    }

   

    /**
     * 获取 回购利率
     * @return rate
     */
   
    @Override
    public BigDecimal getRate() {
        return super.getRate();
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
    public List<WflTrdProductDealPre> getWflTrdProductDealPreList() {
        return wflTrdProductDealPreList;
    }

    /**
     * 设置 金融产品交易（预录入）业务流程
     * @param wflTrdProductDealPreList 金融产品交易（预录入）业务流程
     */
    public void setWflTrdProductDealPreList(List<WflTrdProductDealPre> wflTrdProductDealPreList) {
        this.wflTrdProductDealPreList = wflTrdProductDealPreList;
    }

    /**
     * 取得 金融产品交易（预录入）业务流程附属表信息
     * @return 金融产品交易（预录入）业务流程附属表信息
     */
    public List<WflTrdProductDealAddPre> getWflTrdProductDealAddPreList() {
        return wflTrdProductDealAddPreList;
    }

    /**
     * 设置 金融产品交易（预录入）业务流程附属表信息
     * @param wflTrdProductDealAddPreList 金融产品交易（预录入）业务流程附属表信息
     */
    public void setWflTrdProductDealAddPreList(List<WflTrdProductDealAddPre> wflTrdProductDealAddPreList) {
        this.wflTrdProductDealAddPreList = wflTrdProductDealAddPreList;
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
    public List<TrdProductDealPre> getTrdProductDealPreList() {
        return trdProductDealPreList;
    }

    /**
     * 设置 金融产品交易（预录入）
     */
    public void setTrdProductDealPreList(List<TrdProductDealPre> trdProductDealPreList) {
        this.trdProductDealPreList = trdProductDealPreList;
    }

    /**
     * 取得 金融产品交易（预录入）附属表信息
     * @return 金融产品交易（预录入）附属表信息
     */
    public List<TrdProductDealAddPre> getTrdProductDealAddPreList() {
        return trdProductDealAddPreList;
    }

    /**
     * 设置 金融产品交易（预录入）附属表信息
     * @param trdProductDealAddPreList 金融产品交易（预录入）附属表信息
     */
    public void setTrdProductDealAddPreList(List<TrdProductDealAddPre> trdProductDealAddPreList) {
        this.trdProductDealAddPreList = trdProductDealAddPreList;
    }

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public WflTrdProductDealPre getWflTrdProductDealPre() {
		return wflTrdProductDealPre;
	}

	public void setWflTrdProductDealPre(WflTrdProductDealPre wflTrdProductDealPre) {
		this.wflTrdProductDealPre = wflTrdProductDealPre;
	}

	public WflTrdProductDealAddPre getWflTrdProductDealAddPre() {
		return wflTrdProductDealAddPre;
	}

	public void setWflTrdProductDealAddPre(WflTrdProductDealAddPre wflTrdProductDealAddPre) {
		this.wflTrdProductDealAddPre = wflTrdProductDealAddPre;
	}

	public TrdProductDealPre getTrdProductDealPre() {
		return trdProductDealPre;
	}

	public void setTrdProductDealPre(TrdProductDealPre trdProductDealPre) {
		this.trdProductDealPre = trdProductDealPre;
	}

	public TrdProductDealAddPre getTrdProductDealAddPre() {
		return trdProductDealAddPre;
	}

	public void setTrdProductDealAddPre(TrdProductDealAddPre trdProductDealAddPre) {
		this.trdProductDealAddPre = trdProductDealAddPre;
	}

	public FinProductType getFinProductType() {
		return finProductType;
	}

	public void setFinProductType(FinProductType finProductType) {
		this.finProductType = finProductType;
	}

	@NotEmpty(fieldName = "清算速度", groups = ValidateGroupEdit.class)
	public String getSettleSpeedText() {
		return settleSpeedText;
	}

	public void setSettleSpeedText(String settleSpeedText) {
		this.settleSpeedText = settleSpeedText;
	}

	public List<TrdTransfenpeiDto> getTrdTransfenpeiDto() {
		return trdTransfenpeiDto;
	}

	public void setTrdTransfenpeiDto(List<TrdTransfenpeiDto> trdTransfenpeiDto) {
		this.trdTransfenpeiDto = trdTransfenpeiDto;
	}

	public BussTransDto getBussTransfenpei() {
		return bussTransfenpei;
	}

	public void setBussTransfenpei(BussTransDto bussTransfenpei) {
		this.bussTransfenpei = bussTransfenpei;
	}

	
}
