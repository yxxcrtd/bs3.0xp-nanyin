package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkInteAcct;

/**
 * @ClassName 		BkInteAcctDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkInteAcctDao extends CommFuncDAO{
	
	public boolean checkInteAcctNameExist(@Param("bankID")String bankID, @Param("inteAcct") String inteAcct);
	
	/**
	 * 获取利息账户
	 * @param bankID
	 * @return
	 */
	public List<BkInteAcct> loadBankInteAcctByBank(@Param("bankID")String bankID);
	
	/**
	 * 获取银行利息配置
	 * @param bkInteAcctID	银行利息配置编号
	 * @return	银行利息配置实例
	 */
	public BkInteAcct loadBkInteAcct(@Param("bkInteAcctID")int bkInteAcctID);
}
