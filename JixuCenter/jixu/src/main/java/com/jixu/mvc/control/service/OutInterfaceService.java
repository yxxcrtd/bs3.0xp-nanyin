package com.jixu.mvc.control.service;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;

import com.jixu.mvc.control.model.PortParams;

/**
 * 
 * @author Pan Wei
 *
 */
public interface OutInterfaceService {
	
	//获取最新的单位净值
	public double getLatestNav(String portnum, Date navDate);
	
	
	public String getPortnumByCode(String portcode);
	
	/**
	 * 获取银行账户
	 * @param extacctcode	外部银行代码
	 * @return
	 */
	public int getBkAccIDByExtacct(String extacctcode);
	
	/**
	 * 获取投资组合默认
	 * @param extacctcode	外部银行代码
	 * @return
	 */
	public String getSecAcctByBkAcct(int bkacctid);
	
	/**
	 * 获取根据科目名获取科目号
	 * @param extacctcode	外部银行代码
	 * @return
	 */
	public String getCoaacctByPortnumAcctName(String portnum, String acctname);
	
	public String getCusipByName(String securityname);
	
	public String getCusipBySecid(String secid);
}
