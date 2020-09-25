package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		BkInteAcct
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[银行利息配置表，具体利率在Bk_Inte_Ration表]  
 */

@Entity 
@Table(name="JXB_BK_INTE_ACCT") 
public class BkInteAcct extends BasicEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// primary key
	private int id;

	// fields
	private BkInfo bkinfo;
	private String cashflowtype;
	private int intperiodconv;
	private String masterrecname;
	
	private CashFlowCfg cashFlowCfg;
	
	// collections
	private Set<BkInteRatio> bkInteRatios;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkInteAcct_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="BK_INTE_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BANKID")
	public BkInfo getBkinfo() {
		return bkinfo;
	}

	public void setBkinfo(BkInfo bkinfo) {
		this.bkinfo = bkinfo;
	}

	@Column(name="CASHFLOWTYPE")
	public String getCashflowtype() {
		return cashflowtype;
	}

	public void setCashflowtype(String cashflowtype) {
		this.cashflowtype = cashflowtype;
	}

	@Column(name="INTPERIODCONV")
	public int getIntperiodconv() {
		return intperiodconv;
	}

	public void setIntperiodconv(int intperiodconv) {
		this.intperiodconv = intperiodconv;
	}

	@Column(name="MASTERRECNAME")
	public String getMasterrecname() {
		return masterrecname;
	}

	public void setMasterrecname(String masterrecname) {
		this.masterrecname = masterrecname;
	}

	@OneToMany(mappedBy = "bkInteAcct", cascade = CascadeType.REMOVE)
	public Set<BkInteRatio> getBkInteRatios() {
		return bkInteRatios;
	}

	public void setBkInteRatios(Set<BkInteRatio> bkInteRatios) {
		this.bkInteRatios = bkInteRatios;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cashFlowCfg")
	public CashFlowCfg getCashFlowCfg() {
		return cashFlowCfg;
	}

	public void setCashFlowCfg(CashFlowCfg cashFlowCfg) {
		this.cashFlowCfg = cashFlowCfg;
	}
}
