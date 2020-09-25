package com.jixu.dao.intf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.CoaaGroup;

/**
 * @ClassName 		CoaaGroupDao
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

public interface CoaaGroupDao extends CommFuncDAO{
	
	public CoaaGroup loadCoaaGroup(@Param("groupCode")int groupCode);
	
	public CoaaGroup loadFxTranCoaaGroup(@Param("fxcur")String fxcur);
	
	/**
	 * 获取总账组
	 * @return
	 */
	public List<CoaaGroup> loadAllCoaaGroup();
	
	/**
	 * 自动获取即期换汇的总账组
	 * @param shortCur
	 * @param longCur
	 * @return
	 */
	public CoaaGroup loadFxOpenGlGroup(String shortCur, String longCur);
	
	/**
	 * 自动获取外汇掉期的总账组
	 * @param shortCur
	 * @param longCur
	 * @return
	 */
	public CoaaGroup loadSwapFxOpenGlGroup(String shortCur, String longCur);
	
	/**
	 * 自动获取外汇远期的总账组
	 * @param shortCur
	 * @param longCur
	 * @return
	 */
	public CoaaGroup loadForwardFxOpenGlGroup(String shortCur, String longCur);
}
