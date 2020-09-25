package com.jixu.mvc.control.service;

import java.util.HashMap;
import java.util.List;

import com.jixu.common.type.ResultContainer;
import com.jixu.mvc.control.model.BankInterestModel;
import com.orm.obj.BkCashAcct;
import com.orm.obj.BkInfo;
import com.orm.obj.BkInteAcct;
import com.orm.obj.BkInteRatio;

public interface BankCfgService {
	/**
	 * 获取所有银行列表
	 * @return
	 */
	public HashMap<String, String> loadBankList();
	
	public BkInfo loadBkInfoByID(String bankID);
	
	/**
	 * 更新银行基础信息
	 * @param bank
	 */
	public void updateBank(BkInfo bank);
	
	/**
	 * 新建银行
	 * @param bank
	 */
	public void createBank(BkInfo bank);
	
	/**
	 * 获取相关银行账户
	 * @param bankID
	 * @return
	 */
	public HashMap<Integer, String> loadBankAcct(String bankID);
	
	/**
	 * 获取相关银行没有被关联的银行账户
	 * @param bankID
	 * @return
	 */
	public HashMap<Integer, String> unMapBankAccountList(String bankID);
	
	/**
	 * 获取银行账户详情
	 * @param acctID
	 * @return
	 */
	public BkCashAcct loadAcctDetail(int acctID);
	
	/**
	 * 获取银行利息账户
	 * @param bankID
	 * @return
	 */
	public HashMap<Integer, String> loadBankInteAcct(String bankID);
	
	/**
	 * 获取银行利息账户详情
	 * @param acctID
	 * @return
	 */
	public BankInterestModel loadBankInteAcctDetail(int acctID);
	
	/**
	 * 创建现金账户
	 * @param bkCashAcct
	 */
	public ResultContainer createBankAcct(BkCashAcct bkCashAcct);
	
	/**
	 * 创建银行利息账户
	 * @param inteAcctParams	银行利息基本配置
	 * @return	是否创建成功。如果失败，错误信息
	 */
	public ResultContainer createBankInteAcct(List<Object> inteAcctParams);
	
	/**
	 * 根据投资组合获取银行列表
	 * @param portnum
	 * @return
	 */
	public List<BkCashAcct> getBankCashAcctByPortNum(String portnum);
	
	/**
	 * 获取银行利息账户
	 * @param acctID
	 * @return
	 */
	public BkInteAcct loadBankInteAcctByID(int acctID);
	
	public HashMap<Integer,String> loadBankAcctCOA(int accid);
	
	public void updateBankacctGL(int acctid, int glid, String coaAccount);
	
	public void removeBankacctGL(int acctid, int glid);
	
	public String loadFxCurrencyByCashAcct(int cashacctID);
	
	public int createIntereRatio(int intAcctID, List<Object> intRatioList);
	
	public int updateIntereRatio(int rateID, List<Object> intRatioList);
	
	public List<BkInteRatio> loadRatioList(int intAcctID);
	
	public void deleteInteRatio(int ratioID);
}
