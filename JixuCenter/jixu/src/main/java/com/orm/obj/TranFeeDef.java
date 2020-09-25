package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		SmfDiv
 * @author  		Pan Wei 
 * @Date 			2017年8月10日 上午10:50:01 	
 * @Description		[费用的配置模块，通过配置费用的成本化、费用化比例，来算出各项费用金额。再通过各个总账栏配置，结合证券的总账组，找到相应的科目号]  
 */


@Entity 
@Table(name="JXT_TRAN_FEE_DEF") 
public class TranFeeDef extends BasicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// primary key

	private int id;
	// fields
	private String feename;
	
	//费用化比例
	private double unCapitalizedPercent;
	
	//现金付款比例
	private double cashPercent;
	
	//成本总账栏
	private int costGlCol;
	
	//费用总账栏
	private int feeGlCol;
	
	//证券清算款总账栏
	private int secPayGlCol;
	
	//应收应付总账栏
	private int rcpyGlCol;
	
	private String shortname;

	private int status;
	
	private int fixedFlag;
	
	private int oneTimeFeeFlag;
	
	private String cashFlowID="0";
	
	private Set<TranFeeTypeMap> tranFeeTypeMaps;
	
	private Set<TranBasicFee> tranBasicFeeSet;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranFeeDef_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="FEETYPEID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="FEENAME")
	public String getFeename() {
		return feename;
	}

	public void setFeename(String feename) {
		this.feename = feename;
	}

	@Column(name="SHORTNAME")
	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@OneToMany(mappedBy = "feeTypeID", cascade = CascadeType.REMOVE)
	public Set<TranBasicFee> getTranBasicFeeSet() {
		return tranBasicFeeSet;
	}
	
	public void setTranBasicFeeSet(Set<TranBasicFee> tranBasicFeeSet) {
		this.tranBasicFeeSet = tranBasicFeeSet;
	}

	@Column(name="unCapitalizedPercent")
	public double getUnCapitalizedPercent() {
		return unCapitalizedPercent;
	}

	public void setUnCapitalizedPercent(double unCapitalizedPercent) {
		this.unCapitalizedPercent = unCapitalizedPercent;
	}

	@Column(name="cashPercent")
	public double getCashPercent() {
		return cashPercent;
	}

	public void setCashPercent(double cashPercent) {
		this.cashPercent = cashPercent;
	}

	@Column(name="costGlCol")
	public int getCostGlCol() {
		return costGlCol;
	}

	public void setCostGlCol(int costGlCol) {
		this.costGlCol = costGlCol;
	}

	@Column(name="feeGlCol")
	public int getFeeGlCol() {
		return feeGlCol;
	}

	public void setFeeGlCol(int feeGlCol) {
		this.feeGlCol = feeGlCol;
	}

	@Column(name="secPayGlCol")
	public int getSecPayGlCol() {
		return secPayGlCol;
	}

	public void setSecPayGlCol(int secPayGlCol) {
		this.secPayGlCol = secPayGlCol;
	}

	@Column(name="rcpyGlCol")
	public int getRcpyGlCol() {
		return rcpyGlCol;
	}

	public void setRcpyGlCol(int rcpyGlCol) {
		this.rcpyGlCol = rcpyGlCol;
	}

	@OneToMany(mappedBy = "tranFeeTypeCode", cascade = CascadeType.REMOVE)
	public Set<TranFeeTypeMap> getTranFeeTypeMaps() {
		return tranFeeTypeMaps;
	}

	public void setTranFeeTypeMaps(Set<TranFeeTypeMap> tranFeeTypeMaps) {
		this.tranFeeTypeMaps = tranFeeTypeMaps;
	}

	@Column(name="fixedFlag")
	public int getFixedFlag() {
		return fixedFlag;
	}

	public void setFixedFlag(int fixedFlag) {
		this.fixedFlag = fixedFlag;
	}

	@Column(name="oneTimeFeeFlag")
	public int getOneTimeFeeFlag() {
		return oneTimeFeeFlag;
	}

	public void setOneTimeFeeFlag(int oneTimeFeeFlag) {
		this.oneTimeFeeFlag = oneTimeFeeFlag;
	}

	@Column(name="cashFlowID")
	public String getCashFlowID() {
		return cashFlowID;
	}

	public void setCashFlowID(String cashFlowID) {
		this.cashFlowID = cashFlowID;
	}

}
