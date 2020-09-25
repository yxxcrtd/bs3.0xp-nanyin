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
@Table(name="BOK_VOUCH_MAIN") 
public class BokVouchMain extends BasicEntity implements Serializable {

	private static final long serialVersionUID = -1662685503837781160L;
	
	//凭证编号
	private String vouch_num;
	
	//业务凭证编号
	private String bus_vouchnum;
	
	//账套代码
	private String bookset_id;
	
	//交易编号
	private String trade_id;
	
	//入账日期
	private Date book_date;
	
	//合并场景代码
	private String com_table_id;

	//凭证说明
	private String vouch_remark;

	//冲正标识
	private String offset_flag;
	
	//年份
	private int vouch_year;
	
	//月份
	private int vouch_month;
	
	//编号位数
	private long num;
	
	//凭证类型
	private String book_type;
	
	/**	
	 * 	凭证类型 :
		7: 估值增值凭证
		8: 开支凭证
		9: 申赎凭证
		14: 现金凭证
		15: 交易凭证
		17: 收入凭证
		18: 结算凭证
		19: 分红凭证
		99: 手工凭证*/
	private int journalSourceCode;
	
	//头寸编号
	private String position_id;

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "book_sequence"),
			@Parameter(name = "short_name", value = "BK")})
	@Column(name="VOUCH_NUM")
	public String getVouch_num() {
		return vouch_num;
	}

	public void setVouch_num(String vouch_num) {
		this.vouch_num = vouch_num;
	}

	@Column(name="BUS_VOUCHNUM")
	public String getBus_vouchnum() {
		return bus_vouchnum;
	}

	public void setBus_vouchnum(String bus_vouchnum) {
		this.bus_vouchnum = bus_vouchnum;
	}

	@Column(name="BOOKSET_ID")
	public String getBookset_id() {
		return bookset_id;
	}

	public void setBookset_id(String bookset_id) {
		this.bookset_id = bookset_id;
	}

	@Column(name="TRADE_ID")
	public String getTrade_id() {
		return trade_id;
	}

	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}

	@Column(name="BOOK_DATE")
	public Date getBook_date() {
		return book_date;
	}

	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}

	@Column(name="COM_TABLE_ID")
	public String getCom_table_id() {
		return com_table_id;
	}

	public void setCom_table_id(String com_table_id) {
		this.com_table_id = com_table_id;
	}

	@Column(name="VOUCH_REMARK")
	public String getVouch_remark() {
		return vouch_remark;
	}

	public void setVouch_remark(String vouch_remark) {
		this.vouch_remark = vouch_remark;
	}

	@Column(name="OFFSET_FLAG")
	public String getOffset_flag() {
		return offset_flag;
	}

	public void setOffset_flag(String offset_flag) {
		this.offset_flag = offset_flag;
	}

	@Column(name="VOUCH_YEAR")
	public int getVouch_year() {
		return vouch_year;
	}

	public void setVouch_year(int vouch_year) {
		this.vouch_year = vouch_year;
	}

	@Column(name="VOUCH_MONTH")
	public int getVouch_month() {
		return vouch_month;
	}

	public void setVouch_month(int vouch_month) {
		this.vouch_month = vouch_month;
	}

	@Column(name="NUM")
	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	@Column(name="BOOK_TYPE")
	public String getBook_type() {
		return book_type;
	}

	public void setBook_type(String book_type) {
		this.book_type = book_type;
	}

	@Column(name="journalSourceCode")
	public int getJournalSourceCode() {
		return journalSourceCode;
	}

	public void setJournalSourceCode(int journalSourceCode) {
		this.journalSourceCode = journalSourceCode;
	}

	@Column(name="position_id")
	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
}
