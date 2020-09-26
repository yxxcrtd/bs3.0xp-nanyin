package com.joyintech.tams.valuation.accounadjustmanage.valsztinterfdetail.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class ValSztInterfDetail001Dto extends BaseEntity implements ITableRow{

	
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6519417948438879222L;

    /**
	 * 组合代码
	 */
	private String fundId;
	
	/**
	 * 产品名称
	 */
	private String finprodName;
	
	/**
	 * 对帐结果
	 */
	private String result;
	
	/**
	 * 关联流水号
	 */
	private String refNo;
	
	/**
	 * 本方项目代码
	 */
	private String fxmdmB;
	
	/**
	 * 对方项目代码
	 */
	private String fxmdmD;
	
	/**
	 * 本方金额
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bje;
	
	/**
	 * 对方金额
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal dje;
	
	/**
	 * 本方数量
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bsl;
	
	/**
	 * 对方数量
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal dsl;
	
	/**
	 * 附言
	 */
	private String note;
	
	/**
	 * 对账类型
	 */
	private String checkFlag;
	
	/**
	 * 对账类型字符串
	 */
	private String checkFlagString;
	
	/**
	 * 处理人
	 */
	private String dealer;
	
	/**
	 * 时间
	 */
	private String time;
	
	/**
	 * 本方金额一
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bjeO;
	
	/**
	 * 对方金额一
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal djeO;
	
	/**
	 * 本方金额二
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bjeT;
	
	/**
	 * 对方金额二
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal djeT;
	
	/**
	 * 本方金额三
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bjeTh;
	
	/**
	 * 对方金额三
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal djeTh;
	
	/**
	 * 本方金额四
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bjeF;
	
	/**
	 * 对方金额四
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal djeF;
	
	/**
	 * 本方金额五
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal bjeFi;
	
	/**
	 * 对方金额五
	 */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal djeFi;
	
	/**
	 * 本方备用1
	 */
	private String bby1;
	
	/**
	 * 对方备用1
	 */
	private String dby1;
	
	/**
	 * 对账状态
	 */
	private String checkStatus;
	
	/**
	 * 对账状态字符串
	 */
	private String checkStatusString;
	
	/**
	 * 获取组合代码
	 * @return
	 */
    public String getFundId() {
		return fundId;
	}

    /**
     * 设置组合代码
     * @param fundId
     */
	public void setFundId(String fundId) {
		this.fundId = fundId == null ? null : fundId.trim();
	}
	
	/**
	 * 获取产品名称
	 * @return
	 */
	public String getFinprodName() {
		return finprodName;
	}

	/**
	 * 设置产品名称
	 * @param finprodName
	 */
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName == null ? null : finprodName.trim();
	}

	/**
	 * 获取对帐结果
	 * @return
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 设置对帐结果
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result == null ? null : result.trim();
	}

	/**
	 * 获取关联流水号
	 * @return
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * 设置关联流水号
	 * @param refNo
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo == null ? null : refNo.trim();
	}

	/**
	 * 获取本方项目代码
	 * @return
	 */
	public String getFxmdmB() {
		return fxmdmB;
	}

	/**
	 * 设置本方项目代码
	 * @param fxmdmB
	 */
	public void setFxmdmB(String fxmdmB) {
		this.fxmdmB = fxmdmB == null ? null : fxmdmB.trim();
	}

	/**
	 * 获取对方项目代码
	 * @return
	 */
	public String getFxmdmD() {
		return fxmdmD;
	}

	/**
	 * 设置对方项目代码
	 * @param fxmdmD
	 */
	public void setFxmdmD(String fxmdmD) {
		this.fxmdmD = fxmdmD == null ? null : fxmdmD.trim();
	}

	/**
	 * 获取本方金额
	 * @return
	 */
	public BigDecimal getBje() {
		if (bje != null) {
            return bje;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置本方金额
	 * @param bje
	 */
	public void setBje(BigDecimal bje) {
		this.bje = bje;
	}

	/**
	 * 获取对方金额
	 * @return
	 */
	public BigDecimal getDje() {
		if (dje != null) {
            return dje;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额
	 * @param dje
	 */
	public void setDje(BigDecimal dje) {
		this.dje = dje;
	}

	/**
	 * 获取本方数量
	 * @return
	 */
	public BigDecimal getBsl() {
		if (bsl != null) {
            return bsl;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置本方数量
	 * @param bsl
	 */
	public void setBsl(BigDecimal bsl) {
		this.bsl = bsl;
	}

	/**
	 * 获取对方数量
	 * @return
	 */
	public BigDecimal getDsl() {
		if (dsl != null) {
            return dsl;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方数量
	 * @param dsl
	 */
	public void setDsl(BigDecimal dsl) {
		this.dsl = dsl;
	}

	/**
	 * 获取附言
	 * @return
	 */
	public String getNote() {
		return note;
	}

	/**
	 * 设置附言
	 * @param note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 获取对账类型
	 * @return
	 */
	public String getCheckFlag() {
		return checkFlag;
	}

	/**
	 * 设置对账类型
	 * @param checkFlag
	 */
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * 获取对账类型字符串
	 * @return
	 */
	public String getCheckFlagString() {
		return SysDataUtil.getNameFromDataDict(DictCode.深圳通对账类型, this.getCheckFlag());
	}

	/**
	 * 获取处理人
	 * @return
	 */
	public String getDealer() {
		return dealer;
	}

	/**
	 * 设置处理人
	 * @param dealer
	 */
	public void setDealer(String dealer) {
		this.dealer = dealer == null ? null : dealer.trim();
	}

	/**
	 * 获取时间
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 设置时间
	 * @param time
	 */
	public void setTime(String time) {
		this.time = time == null ? null : time.trim();
	}

	/**
	 * 获取本方金额一
	 * @return
	 */
	public BigDecimal getBjeO() {
		if (bjeO != null) {
            return bjeO;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置本方金额一
	 * @param bjeO
	 */
	public void setBjeO(BigDecimal bjeO) {
		this.bjeO = bjeO;
	}

	/**
	 * 获取对方金额一
	 * @return
	 */
	public BigDecimal getDjeO() {
		if (djeO != null) {
            return djeO;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额一
	 * @param djeO
	 */
	public void setDjeO(BigDecimal djeO) {
		this.djeO = djeO;
	}

	/**
	 * 获取本方金额二
	 * @return
	 */
	public BigDecimal getBjeT() {
		if (bjeT != null) {
            return bjeT;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置本方金额二
	 * @param bjeT
	 */
	public void setBjeT(BigDecimal bjeT) {
		this.bjeT = bjeT;
	}

	/**
	 * 获取对方金额二
	 * @return
	 */
	public BigDecimal getDjeT() {
		if (djeT != null) {
            return djeT;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额二
	 * @param djeT
	 */
	public void setDjeT(BigDecimal djeT) {
		this.djeT = djeT;
	}

	/**
	 * 获取对方金额三
	 * @return
	 */
	public BigDecimal getBjeTh() {
		if (bjeTh != null) {
            return bjeTh;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额三
	 * @param bjeTh
	 */
	public void setBjeTh(BigDecimal bjeTh) {
		this.bjeTh = bjeTh;
	}

	/**
	 * 获取对方金额三
	 * @return
	 */
	public BigDecimal getDjeTh() {
		if (djeTh != null) {
            return djeTh;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额三
	 * @param djeTh
	 */
	public void setDjeTh(BigDecimal djeTh) {
		this.djeTh = djeTh;
	}

	/**
	 * 获取对方金额四
	 * @return
	 */
	public BigDecimal getBjeF() {
		if (bjeF != null) {
            return bjeF;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额四
	 * @param bjeF
	 */
	public void setBjeF(BigDecimal bjeF) {
		this.bjeF = bjeF;
	}

	/**
	 * 获取对方金额四
	 * @return
	 */
	public BigDecimal getDjeF() {
		if (djeF != null) {
            return djeF;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额四
	 * @param djeF
	 */
	public void setDjeF(BigDecimal djeF) {
		this.djeF = djeF;
	}

	/**
	 * 获取对方金额五
	 * @return
	 */
	public BigDecimal getBjeFi() {
		if (bjeFi != null) {
            return bjeFi;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额五
	 * @param bjeFi
	 */
	public void setBjeFi(BigDecimal bjeFi) {
		this.bjeFi = bjeFi;
	}

	/**
	 * 获取对方金额五
	 * @return
	 */
	public BigDecimal getDjeFi() {
		if (djeFi != null) {
            return djeFi;
        }
        else {
            return BigDecimal.ZERO;
        }
	}

	/**
	 * 设置对方金额五
	 * @param djeFi
	 */
	public void setDjeFi(BigDecimal djeFi) {
		this.djeFi = djeFi;
	}

	/**
	 * 获取本方备用1
	 * @return
	 */
	public String getBby1() {
		return bby1;
	}

	/**
	 * 设置本方备用1
	 * @param bby1
	 */
	public void setBby1(String bby1) {
		this.bby1 = bby1 == null ? null : bby1.trim();
	}

	/**
	 * 获取对方备用1
	 * @return
	 */
	public String getDby1() {
		return dby1;
	}

	/**
	 * 设置对方备用1
	 * @param dby1
	 */
	public void setDby1(String dby1) {
		this.dby1 = dby1 == null ? null : dby1.trim();
	}
	
	/**
	 * 获取对账状态
	 * @return
	 */
	public String getCheckStatus() {
		return checkStatus;
	}

	/**
	 * 设置对账状态
	 * @param checkStatus
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus == null ? null : checkStatus.trim();
	}

	/**
	 * 获取对账状态字符串
	 * @return
	 */
	public String getCheckStatusString() {
		return SysDataUtil.getNameFromDataDict(DictCode.深圳通对账状态, this.getCheckStatus());
	}
	
}
