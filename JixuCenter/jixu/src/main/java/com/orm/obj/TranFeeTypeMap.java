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

/** 
 * @ClassName 		TranFeeTypeMap
 * @author  		Pan Wei 
 * @Date 			2016年9月1日 上午10:54:57 	
 * @Description		[在界面上显示，哪些交易可以配置的自定义费用类型。在费用界面的每个费用类型里，可以选择被使用的交易类型]  
 */

@Entity 
@Table(name="JXT_Tran_Fee_TypeMap") 
public class TranFeeTypeMap extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -3223612091361471244L;

	private int id;
	
	private String assetType;
	
	private TranFeeDef tranFeeTypeCode;

	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="TranFeeTypeMap_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="assetType")
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TranFeeTypeCode")
	public TranFeeDef getTranFeeTypeCode() {
		return tranFeeTypeCode;
	}
	public void setTranFeeTypeCode(TranFeeDef tranFeeTypeCode) {
		this.tranFeeTypeCode = tranFeeTypeCode;
	}
}
