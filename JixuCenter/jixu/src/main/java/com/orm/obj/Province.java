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
 * @ClassName 		PortfolioInterfaceMap
 * @author  		Pan Wei 
 * @Date 			2016年9月16日 下午10:18:06 	
 * @Description		[省份表]  
 */


@Entity 
@Table(name="JXM_PROVINCE") 
public class Province extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 449559482479522438L;

	private int proID;
	
	private String proName;
	
	private String country;
	
	private Set<PortfolioHdr> portfolios;

	public Province() {
	}
	
	public Province(int proID) {
		this.proID=proID;
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="Province_ID_SEQ", allocationSize=1, initialValue=35)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="proID")
	public int getProID() {
		return proID;
	}

	public void setProID(int proID) {
		this.proID = proID;
	}

	@Column(name="proName")
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@OneToMany(mappedBy = "province", cascade = CascadeType.REMOVE)
	public Set<PortfolioHdr> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Set<PortfolioHdr> portfolios) {
		this.portfolios = portfolios;
	}
}
