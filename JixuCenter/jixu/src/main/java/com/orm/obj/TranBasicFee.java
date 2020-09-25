package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		SmfDiv
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[TranFee和Tran_Basic_Fee的关系就和Tran和Tran_Basic一样，Tran_Basic_Fee是交易费用的原始数据,TranFee是经过了一定的二次计算处理]  
 */

@Entity 
@Table(name="JXT_TRAN_BASIC_FEE") 
public class TranBasicFee extends BasicEntity implements Serializable {
	//CapitalisedFeeAmount,ExpensedFeeAmount,TotalFeeAmount,AMTEXCLFROMCASH,DEFERREDFEEAMT
	private static final long serialVersionUID = 1L;
	// primary key
	private int id;

	// fields
	private double expenseFeeamt;
	private double capitalisedfeeamt;
	private double totalFeeamt;
	private double amtexclfromcash;
	private double deferredFeeamt;
	private double rcpyFeeamt;
	
	// many to one
	private TranFeeDef feeTypeID;
	private TranBasic tanum;
	private String currID;
	private double fxrate;
	private Date settleDate;
	private BkCashAcct bkCashAcct;
	private int recOrPay;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranBasicFee_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="FEEID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="expenseFeeamt")
	public double getExpenseFeeamt() {
		return expenseFeeamt;
	}

	public void setExpenseFeeamt(double expenseFeeamt) {
		this.expenseFeeamt = expenseFeeamt;
	}

	@Column(name="capitalisedfeeamt")
	public double getCapitalisedfeeamt() {
		return capitalisedfeeamt;
	}

	public void setCapitalisedfeeamt(double capitalisedfeeamt) {
		this.capitalisedfeeamt = capitalisedfeeamt;
	}

	@Column(name="totalFeeamt")
	public double getTotalFeeamt() {
		return totalFeeamt;
	}

	public void setTotalFeeamt(double totalFeeamt) {
		this.totalFeeamt = totalFeeamt;
	}

	@Column(name="amtexclfromcash")
	public double getAmtexclfromcash() {
		return amtexclfromcash;
	}

	public void setAmtexclfromcash(double amtexclfromcash) {
		this.amtexclfromcash = amtexclfromcash;
	}

	@Column(name="deferredFeeamt")
	public double getDeferredFeeamt() {
		return deferredFeeamt;
	}

	public void setDeferredFeeamt(double deferredFeeamt) {
		this.deferredFeeamt = deferredFeeamt;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TANUM")
	public TranBasic getTanum() {
		return tanum;
	}

	public void setTanum(TranBasic tanum) {
		this.tanum = tanum;
	}

	@Column(name="FEECURRENCY")
	public String getCurrID() {
		return currID;
	}

	public void setCurrID(String currID) {
		this.currID = currID;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FEETYPEID")
	public TranFeeDef getFeeTypeID() {
		return feeTypeID;
	}

	public void setFeeTypeID(TranFeeDef feeTypeID) {
		this.feeTypeID = feeTypeID;
	}

	@Column(name="fxrate")
	public double getFxrate() {
		return fxrate;
	}

	
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}

	@Column(name="rcpyFeeamt")
	public double getRcpyFeeamt() {
		return rcpyFeeamt;
	}

	public void setRcpyFeeamt(double rcpyFeeamt) {
		this.rcpyFeeamt = rcpyFeeamt;
	}

	@Column(name="settleDate")
	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bkCashAcct")
	public BkCashAcct getBkCashAcct() {
		return bkCashAcct;
	}

	public void setBkCashAcct(BkCashAcct bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}

	@Column(name="RecOrPay")
	public int getRecOrPay() {
		return recOrPay;
	}

	public void setRecOrPay(int recOrPay) {
		this.recOrPay = recOrPay;
	}
}
