package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		UserRoleGroupMap
 * @author  		Pan Wei 
 * @Date 			2017年1月4日 上午10:07:43 	
 * @Description		[凭证表主表 Voucher和VoucherDetails两表组成了凭证表。Voucher表是纲领，VoucherDetails表是具体数据内容。一条Voucher记录对应N条VoucherDetails记录]
 */

@Entity 
@Table(name="BOK_VOUCH_DETAIL") 
public class BokVouchDetail extends BasicEntity implements Serializable {

	
	private static final long serialVersionUID = 3699728658569606221L;

	//凭证子表编号
	private String vouch_subnum;
	
	//凭证编号
	private String vouch_num;
	
	//业务凭证子表编号
	private String bus_vouchsubnum;
	
	//发生日期
	private Date happen_date;
	
	//场景代码
	private String table_id;
	
	//借贷方向
	private String cd_falg;
	
	//科目号
	private String subject_no;
	
	//发生额
	private double happen_amt;
	
	//发生额_本币
	private double happen_amt_local;
	
	//发生数量
	private double happen_number;
	
	//业务明细代码
	private String busi_id;
	
	//币种
	private String ccy;
	
	//交易编号
	private String trade_id;
	
	//四级科目号
	private String detail_subject_no;
	
	//凭证类型
	private String book_type;
	
	//汇率
	private double fx_rate;
	
	//价格
	private double price;

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "book_sequence"),
			@Parameter(name = "short_name", value = "BK")})
	@Column(name="VOUCH_SUBNUM")
	public String getVouch_subnum() {
		return vouch_subnum;
	}

	public void setVouch_subnum(String vouch_subnum) {
		this.vouch_subnum = vouch_subnum;
	}

	@Column(name="VOUCH_NUM")
	public String getVouch_num() {
		return vouch_num;
	}

	public void setVouch_num(String vouch_num) {
		this.vouch_num = vouch_num;
	}

	@Column(name="BUS_VOUCHSUBNUM")
	public String getBus_vouchsubnum() {
		return bus_vouchsubnum;
	}

	public void setBus_vouchsubnum(String bus_vouchsubnum) {
		this.bus_vouchsubnum = bus_vouchsubnum;
	}

	@Column(name="HAPPEN_DATE")
	public Date getHappen_date() {
		return happen_date;
	}

	public void setHappen_date(Date happen_date) {
		this.happen_date = happen_date;
	}

	@Column(name="TABLE_ID")
	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}

	@Column(name="CD_FLAG")
	public String getCd_falg() {
		return cd_falg;
	}

	public void setCd_falg(String cd_falg) {
		this.cd_falg = cd_falg;
	}

	@Column(name="SUBJECT_NO")
	public String getSubject_no() {
		return subject_no;
	}

	public void setSubject_no(String subject_no) {
		this.subject_no = subject_no;
	}

	@Column(name="HAPPEN_AMT")
	public double getHappen_amt() {
		return happen_amt;
	}

	public void setHappen_amt(double happen_amt) {
		this.happen_amt = happen_amt;
	}

	@Column(name="HAPPEN_AMT_LOCAL")
	public double getHappen_amt_local() {
		return happen_amt_local;
	}

	public void setHappen_amt_local(double happen_amt_local) {
		this.happen_amt_local = happen_amt_local;
	}

	@Column(name="HAPPEN_NUMBER")
	public double getHappen_number() {
		return happen_number;
	}

	public void setHappen_number(double happen_number) {
		this.happen_number = happen_number;
	}

	@Column(name="BUSI_ID")
	public String getBusi_id() {
		return busi_id;
	}

	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}

	@Column(name="CCY")
	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	@Column(name="TRADE_ID")
	public String getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}

	@Column(name="DETAIL_SUBJECT_NO")
	public String getDetail_subject_no() {
		return detail_subject_no;
	}

	public void setDetail_subject_no(String detail_subject_no) {
		this.detail_subject_no = detail_subject_no;
	}

	@Column(name="BOOK_TYPE")
	public String getBook_type() {
		return book_type;
	}

	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}

	@Column(name="FX_RATE")
	public double getFx_rate() {
		return fx_rate;
	}

	public void setFx_rate(double fx_rate) {
		this.fx_rate = fx_rate;
	}

	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
