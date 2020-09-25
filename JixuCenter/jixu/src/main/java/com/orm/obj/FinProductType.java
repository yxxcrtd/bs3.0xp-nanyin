package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		BkCashFlow
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[实收实付表，现金的结转都被记录在该表]  
 */

@Entity 
@Table(name="FIN_PRODUCT_TYPE")
public class FinProductType extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 7373668652906777625L;

	//金融产品代码
	private String finprod_id;
	
	private int branch;
	
	//金融产品类型
//	private String finprod_type;
	
	//分类类别1
	private String type_1;
	
	//分类类别1
	private String type_2;
	
	//分类类别3
	private String type_3;

	
	@Id
	@Column(name="finprod_id")
	public String getFinprod_id() {
		return finprod_id;
	}

	public void setFinprod_id(String finprod_id) {
		this.finprod_id = finprod_id;
	}

//	@Column(name="finprod_type")
//	public String getFinprod_type() {
//		return finprod_type;
//	}
//
//	public void setFinprod_type(String finprod_type) {
//		this.finprod_type = finprod_type;
//	}

	@Column(name="type_1")
	public String getType_1() {
		return type_1;
	}

	public void setType_1(String type_1) {
		this.type_1 = type_1;
	}

	@Column(name="type_3")
	public String getType_3() {
		return type_3;
	}

	public void setType_3(String type_3) {
		this.type_3 = type_3;
	}

	@Column(name="branch")
	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public String getType_2() {
		return type_2;
	}

	public void setType_2(String type_2) {
		this.type_2 = type_2;
	}
	
}
