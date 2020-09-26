/*
 * 文件名：ChbAstValueSub001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */
 
package com.joyintech.tams.svreport.chb.astvalueregist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.astvalueregist.dto.ChbAstValueRegist001Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年1月11日
 * @see ChbAstValueRegist001Mapper
 * @since 1.0
 */
@Repository
public interface ChbAstValueRegist001Mapper {
    /**
    *
    * 查询页面数据
    * @param map 查询条件
    * @return 结果列表
    * @see
    * @since 1.0
    */
   List<ChbAstValueRegist001Dto> searchPage(Map<String,Object> map);
   
   
   /**
   *
   * 查询页面数据
   * @param map 查询条件
   * @return 结果列表
   * @see
   * @since 1.0
   */
  List<ChbAstValueRegist001Dto> searchPageEx(Map<String,Object> map);
   

   /**
   *
   * 重新查询导出数据
   * @param map 查询条件
   * @return 结果列表
   * @see
   * @since 1.0
   */
   List<ChbAstValueRegist001Dto> queryExportListByKeys(@Param("registList")List<ChbAstValueRegist001Dto> registList);
    
   /**
   *update by leipeng 20200701 资产多法人改造
   * 重新查询导出数据-联社版
   * @param map 查询条件
   * @return 结果列表
   * @see
   * @since 1.0
   */
   List<ChbAstValueRegist001Dto> queryExportListByKeysEx(@Param("registList")List<ChbAstValueRegist001Dto> registList,@Param("orgCode")List<String> orgCode);
   
   
   void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<ChbAstValueRegist001Dto> registList);
   
   Integer cancelMannultRegist(List<ChbAstValueRegist001Dto> list);

	List<String> selectRegistSeqnoList(@Param("list")List<ChbAstValueRegist001Dto> chbBotAstPositionRegistList, @Param("flag")String string);
	
	/**
	 * 单选数据进行导出
	 * update by leipeng 20200701 资产多法人改造
	 */
	List<String> selectRegistSeqnoListEx(@Param("list")List<ChbAstValueRegist001Dto> chbBotAstPositionRegistList, @Param("flag")String string,@Param("orgCode")String orgCode);
	
	void deleteRegistSeqnoList(@Param("regList")List<String> list);

	void insertSeletForSub(@Param("regList")List<String> list);
}
