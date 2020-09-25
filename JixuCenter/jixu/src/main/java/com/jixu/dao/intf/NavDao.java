package com.jixu.dao.intf;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.jixu.common.types.NavAssetObj;
import com.jixu.common.util.Pages;
import com.jixu.data.pojo.PortNav;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.Nav;

/**
 * @ClassName 		NavDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface NavDao extends CommFuncDAO{
	/**
	 * 查看关帐日当天，NAV是否已生成
	 * @param portnum
	 * @param cloDate
	 * @return
	 */
	public boolean checkNavExist(@Param("portnum")String portnum, @Param("cloDate") Date cloDate);
	
	public void deleteNav(@Param("portnum")String portnum, @Param("navDate") Date navDate);
	
	/**
	 * 获取最新的nav信息
	 * @param portNum
	 * @param navDate
	 * @return
	 */
	public Nav loadNAVByDate(@Param("portNum")String portNum, @Param("navDate") Date navDate);
	
	/**
	 * 获取历史净值
	 * @param portnum
	 * @param navDate
	 * @return
	 */
	public Pages<NavAssetObj> getNavList(@Param("pages")Pages<NavAssetObj> pages, @Param("portnum")String portnum, @Param("navDate") Date navDate);
	
	public Page<NavAssetObj> getPageHelperNavList(@Param("pages")Page<NavAssetObj> page, @Param("portnum")String portnum, @Param("navDate") Date navDate);
	
	public List<Nav> getNavDetails(@Param("portnum")String portnum, @Param("navDate") Date navDate);
	
	/**
	 * 获取最新的nav信息
	 * @param portNum	投资组合编号
	 * @param navDate	日期
	 * @return	投资组合净值实例
	 */
	public Nav loadLatestNAV(@Param("portNum")String portNum, @Param("navDate") Date navDate);
	
	/**
	 * 获取最新的nav
	 * @param portnum	投资组合编号
	 * @param curDate	日期
	 * @return 经过处理的投资组合净资产实例
	 */
	public NavAssetObj getLatestNAV(@Param("portnum")String portnum, @Param("curDate") Date curDate);
	
	public NavAssetObj genNavObj(@Param("nav")Nav nav);
	
	/**
	 * 获取当天的净值
	 * @param portnum
	 * @param curDate
	 * @return
	 */
	public NavAssetObj getNav(@Param("portnum")String portnum, @Param("curDate") Date curDate);
	
	/**
	 * 获取当天的净值
	 * @param portnum
	 * @param navDate
	 * @return
	 */
	public Nav loadNav(@Param("portnum")String portnum, @Param("navDate") Date navDate);
	
	public List<PortNav> genResults(@Param("portnum")String portNum, @Param("closeDate")Date closeDate);
}
