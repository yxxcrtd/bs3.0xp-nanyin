package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.BkInteRatio;

/**
 * @ClassName 		BkInteRatioDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface BkInteRatioDao extends CommFuncDAO{
	
	/**
	 * 获取银行利率设值，动态利率
	 * @param bkInteAcctID	银行利率配置编号
	 * @return	动态利率数据集
	 */
	public List<BkInteRatio> loadBkInteRatio(@Param("bkInteAcctID")int bkInteAcctID);

}
