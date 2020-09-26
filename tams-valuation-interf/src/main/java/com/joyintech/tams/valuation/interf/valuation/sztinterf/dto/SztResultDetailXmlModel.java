package com.joyintech.tams.valuation.interf.valuation.sztinterf.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

public class SztResultDetailXmlModel extends BaseEntity{
	
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

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
	private BigDecimal bje;
	
	/**
	 * 对方金额
	 */
	private BigDecimal dje;
	
	/**
	 * 本方数量
	 */
	private BigDecimal bsl;
	
	/**
	 * 对方数量
	 */
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
	private BigDecimal bjeO;
	
	/**
	 * 对方金额一
	 */
	private BigDecimal djeO;
	
	/**
	 * 本方金额二
	 */
	private BigDecimal bjeT;
	
	/**
	 * 对方金额二
	 */
	private BigDecimal djeT;
	
	/**
	 * 本方金额三
	 */
	private BigDecimal bjeTh;
	
	/**
	 * 对方金额三
	 */
	private BigDecimal djeTh;
	
	/**
	 * 本方金额四
	 */
	private BigDecimal bjeF;
	
	/**
	 * 对方金额四
	 */
	private BigDecimal djeF;
	
	/**
	 * 本方金额五
	 */
	private BigDecimal bjeFi;
	
	/**
	 * 对方金额五
	 */
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
		this.result = result;
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
		this.refNo = refNo;
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
		this.fxmdmB = fxmdmB;
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
		this.fxmdmD = fxmdmD;
	}

	/**
	 * 获取本方金额
	 * @return
	 */
	public BigDecimal getBje() {
		return bje;
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
		return dje;
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
		return bsl;
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
		return dsl;
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
		this.dealer = dealer;
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
		this.time = time;
	}

	/**
	 * 获取本方金额一
	 * @return
	 */
	public BigDecimal getBjeO() {
		return bjeO;
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
		return djeO;
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
		return bjeT;
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
		return djeT;
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
		return bjeTh;
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
		return djeTh;
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
		return bjeF;
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
		return djeF;
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
		return bjeFi;
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
		return djeFi;
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
		this.bby1 = bby1;
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
		this.dby1 = dby1;
	}
}
