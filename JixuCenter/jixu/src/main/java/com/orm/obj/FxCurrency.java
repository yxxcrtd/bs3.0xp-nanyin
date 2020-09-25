package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		ExchangeCfg
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[货币]  
 */

@Entity
@Table(name = "JXM_FX_CURRENCY")
public class FxCurrency extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// primary key
	private String id;

	// fields
	private String currCode;
	private String currName;
	private int active;
	// collections
	private Set<FxRate> fxRates;
	private Set<FxRate> fxRates2;
	private Set<TranBasicFee> tranFasicFee;
	private Set<BkCashRcpy> bkCashRcpy;
	private Set<BkCashFlow> bkCashFlow;
	private Set<TranBasic> tranTasic;
	private Set<TranBasic> tranTasic2;
	private Set<TranBasic> tranBasicSettle;
	
	public FxCurrency() {
		// TODO Auto-generated constructor stub
	}
	
	public FxCurrency(String id) {
		this.setId(id);
	}
	
	@Id
	//@SequenceGenerator(name="seq_generator", sequenceName="FxCurrency_ID_SEQ", allocationSize=1, initialValue=23)
	//@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="CURR_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="CURR_CODE")
	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	@Column(name="CURR_NAME")
	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	@OneToMany(mappedBy = "currID", cascade = CascadeType.REMOVE)
	public Set<FxRate> getFxRates() {
		return fxRates;
	}

	public void setFxRates(Set<FxRate> fxRates) {
		this.fxRates = fxRates;
	}
	
	@OneToMany(mappedBy = "baseCurrID", cascade = CascadeType.REMOVE)
	public Set<FxRate> getFxRates2() {
		return fxRates2;
	}

	public void setFxRates2(Set<FxRate> fxRates2) {
		this.fxRates2 = fxRates2;
	}

	@OneToMany(mappedBy = "currID", cascade = CascadeType.REMOVE)
	public Set<TranBasicFee> getTranFasicFee() {
		return tranFasicFee;
	}

	public void setTranFasicFee(Set<TranBasicFee> tranFasicFee) {
		this.tranFasicFee = tranFasicFee;
	}

	@OneToMany(mappedBy = "fxcurrency", cascade = CascadeType.REMOVE)
	public Set<BkCashRcpy> getBkCashRcpy() {
		return bkCashRcpy;
	}

	public void setBkCashRcpy(Set<BkCashRcpy> bkCashRcpy) {
		this.bkCashRcpy = bkCashRcpy;
	}

	@OneToMany(mappedBy = "fxcurrency", cascade = CascadeType.REMOVE)
	public Set<BkCashFlow> getBkCashFlow() {
		return bkCashFlow;
	}

	public void setBkCashFlow(Set<BkCashFlow> bkCashFlow) {
		this.bkCashFlow = bkCashFlow;
	}
	
	@Column(name="active")
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@OneToMany(mappedBy = "fxcurrency", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTranTasic() {
		return tranTasic;
	}

	public void setTranTasic(Set<TranBasic> tranTasic) {
		this.tranTasic = tranTasic;
	}

	@OneToMany(mappedBy = "fxcurrency2", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTranTasic2() {
		return tranTasic2;
	}

	public void setTranTasic2(Set<TranBasic> tranTasic2) {
		this.tranTasic2 = tranTasic2;
	}

	@OneToMany(mappedBy = "settleCurrency", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTranBasicSettle() {
		return tranBasicSettle;
	}

	public void setTranBasicSettle(Set<TranBasic> tranBasicSettle) {
		this.tranBasicSettle = tranBasicSettle;
	}
}
