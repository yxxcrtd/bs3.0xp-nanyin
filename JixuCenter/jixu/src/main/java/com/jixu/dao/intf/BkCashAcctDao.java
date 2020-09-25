package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkCashAcct;

/**
 * @ClassName 		BkCashAcctDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkCashAcctDao extends CommFuncDAO{
	
	
	/**
	 * 获取银行账户详情
	 * @param acctID
	 * @return
	 */
	public BkCashAcct loadBankAcct(@Param("acctID")int acctID);
	
	
	public List<BkCashAcct> loadBankAcctList(@Param("bankID")String bankID);
	
	public List<BkCashAcct> unMapBankAccountList(@Param("bankID")String bankID);
	
	public BkCashAcct loadDefaultCashAcctByCur(@Param("curCode")String curCode);
	
	public BkCashAcct loadBankCashByBkCode(@Param("bankcashCode")String bankcashCode);
	
	/**
	 * 根据银行机构代码获取银行账户
	 * @param seatNumber
	 * @return
	 */
	public BkCashAcct loadBankAcctByBankSeatNumber(@Param("seatNumber")String seatNumber);
	
	/**加载有余额的现金银行**/
	public List<BkCashAcct> loadBankCashAccts(@Param("portnum")String portnum);
	
	/**
	 * 
	 * @param acctID
	 * @return
	 */
	public BkCashAcct loadBankCashAcct(@Param("acctID")int acctID);
	
	/**
	 * 根据投资组合获取银行列表
	 * @param portnum
	 * @return
	 */
	public List<BkCashAcct> getBankCashAcctByPortNum(@Param("portnum")String portnum);
	
	public boolean checkBkCashAcctNameExist(@Param("portnum")String portnum, @Param("bkCashAcct") String bkCashAcct);
	
	public String loadCurIDByCashAcctID(@Param("cashid")int cashid);
	
	/**
	 * 获取银行现金账户
	 * @param bankcashNo	银行现金账户编号
	 * @return	银行现金账户
	 */
	@Cacheable(value="portDataCache", key="'BkCashAcct'+#bankcashNo")
	public BkCashAcct loadBankCash(@Param("bankcashNo")int bankcashNo);
	
	/**
	 * 获取默认的银行现金账户
	 * @param secacctid
	 * @return 银行现金账户实例
	 */
	public BkCashAcct loadCashAcctBySecAcct(@Param("secacctid")String secacctid);
	
	/**
	 * 获取投资组合相关的银行现金账户
	 * @param portnum 投资组合编号	
	 * @return	该投资组合下的银行现金账户集
	 */
	public List<BkCashAcct> loadInteBankCashAcctInfo(@Param("portnum")String portnum);

	public List<BkCashAcct> loadCashAcctByPortnumCur(@Param("portnum")String portnum, @Param("cur") String cur);
	
	public List<BkCashAcct> loadBkCashAcctByPortnum(@Param("portnum")String portnum);
	
	public List<BkCashAcct> loadBkCashAcctBySecAcct(@Param("secAcctID")String secAcctID);
	
	@CacheEvict(value="portDataCache", key="'BkCashAcct'+#cashAcct")
	public void unMapCashSecAcct(@Param("cashAcct")int cashAcct);
	
	public BkCashAcct loadCashAcctWithNoSecAcct(@Param("cashacct")int cashacct);
	
	/**
	 * 默认找id和acct
	 * @param acctID
	 * @return
	 */
	public BkCashAcct loadBankCashAcctByExAcct(String acctID, String portnum);
	
	public int countCashAcctsBySecAcct(String secacctid);
	
}
