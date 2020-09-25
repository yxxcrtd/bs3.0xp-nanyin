package com.orm.obj;

import java.io.Serializable;

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
import com.jixu.common.pojo.InterfaceSource;

/** 
 * @ClassName 		PortfolioInterfaceMap
 * @author  		Pan Wei 
 * @Date 			2016年9月16日 下午10:18:06 	
 * @Description		[投资组合的接口映射表,来决定哪些接口配置可以被该投资组合所使用。对于证券交易:产品编号-接口编号-证券类型(普通股、优先股等)三者决定该交易使
 					用哪个接口数据源。对于汇率,价格等:则是产品编号-接口编号两者决定使用哪个接口数据源]  
 */

@Entity 
@Table(name="JXS_PortfolioInterfaceMap") 
public class PortfolioInterfaceMap extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 3423994167702228398L;
	private int id;
	private PortfolioHdr portfolio;
	private InterfaceSource ineterfaceSource;
	private int defaultImportType;
	private int rpSecType;
	private int operationStep;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="PortfolioInterfaceMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="portfolio")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="InterfaceSourceID")
	public InterfaceSource getIneterfaceSource() {
		return ineterfaceSource;
	}
	public void setIneterfaceSource(InterfaceSource ineterfaceSource) {
		this.ineterfaceSource = ineterfaceSource;
	}
	
	@Column(name="defaultImportType")
	public int getDefaultImportType() {
		return defaultImportType;
	}
	public void setDefaultImportType(int defaultImportType) {
		this.defaultImportType = defaultImportType;
	}
	
	@Column(name="operationStep")
	public int getOperationStep() {
		return operationStep;
	}
	
	public void setOperationStep(int operationStep) {
		this.operationStep = operationStep;
	}
	
	@Column(name="RpSecType")
	public int getRpSecType() {
		return rpSecType;
	}
	
	public void setRpSecType(int rpSecType) {
		this.rpSecType = rpSecType;
	}

	
}
