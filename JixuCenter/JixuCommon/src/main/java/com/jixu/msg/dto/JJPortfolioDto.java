package com.jixu.msg.dto;

import java.util.Date;

/**
 * joyin调用jixu接口创建投资组合
 * @author 桃汰猪
 *
 */
public class JJPortfolioDto {
	//账套
	private String bookSet_id;
	
	//货币
	private String standard_ccy;
	
	//账套模板
	private String bookset_id_tmpl;
	
	//账套创建日期
	private Date start_date;
	
	//账套全名
	private String bookset_fullname;
	
	//账套名
	private String bookset_name;
	
	//账套节假日配置
	private String calendar_id;

	public String getBookSet_id() {
		return bookSet_id;
	}

	public void setBookSet_id(String bookSet_id) {
		this.bookSet_id = bookSet_id;
	}

	public String getStandard_ccy() {
		return standard_ccy;
	}

	public void setStandard_ccy(String standard_ccy) {
		this.standard_ccy = standard_ccy;
	}

	public String getBookset_id_tmpl() {
		return bookset_id_tmpl;
	}

	public void setBookset_id_tmpl(String bookset_id_tmpl) {
		this.bookset_id_tmpl = bookset_id_tmpl;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public String getBookset_fullname() {
		return bookset_fullname;
	}

	public void setBookset_fullname(String bookset_fullname) {
		this.bookset_fullname = bookset_fullname;
	}

	public String getBookset_name() {
		return bookset_name;
	}

	public void setBookset_name(String bookset_name) {
		this.bookset_name = bookset_name;
	}

	public String getCalendar_id() {
		return calendar_id;
	}

	public void setCalendar_id(String calendar_id) {
		this.calendar_id = calendar_id;
	}
}
