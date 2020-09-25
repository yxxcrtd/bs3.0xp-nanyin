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
@Table(name="BOK_BOOKSET")
public class BokBookSet extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 7373668652906777625L;

	//账套代码
	private String bookset_id;
	
	//账套名称
	private String bookset_name;
	
	//账套类别
	private String bookset_type;

	@Id
	@Column(name="bookset_id")
	public String getBookset_id() {
		return bookset_id;
	}

	public void setBookset_id(String bookset_id) {
		this.bookset_id = bookset_id;
	}

	@Column(name="bookset_name")
	public String getBookset_name() {
		return bookset_name;
	}

	public void setBookset_name(String bookset_name) {
		this.bookset_name = bookset_name;
	}

	@Column(name="BOOKSET_TYPE")
	public String getBookset_type() {
		return bookset_type;
	}

	public void setBookset_type(String bookset_type) {
		this.bookset_type = bookset_type;
	}
}
