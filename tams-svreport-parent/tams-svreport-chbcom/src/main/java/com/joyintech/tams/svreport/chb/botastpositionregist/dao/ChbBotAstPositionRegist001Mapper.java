package com.joyintech.tams.svreport.chb.botastpositionregist.dao;


/*
 * 文件名：ChbAstValueSub001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中债报送系统
 */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author dingzhipeng
 * @version 1.0 2018年1月11日
 * @see ChbBotAstPositionRegist001Mapper
 * @since 1.0
 */
@Repository
public interface ChbBotAstPositionRegist001Mapper {
    /**
    *
    * 查询页面数据
    * @param map 查询条件
    * @return 结果列表
    * @see
    * @since 1.0
    */
   List<ChbBotAstPositionRegist001Dto> searchPage(Map<String,Object> map);

   /**
   *
   * 重新查询导出数据
   * @param map 查询条件
   * @return 结果列表
   * @see
   * @since 1.0
   */
   List<ChbBotAstPositionRegist001Dto> queryExportListByKeys(@Param("registList")List<ChbBotAstPositionRegist001Dto> registList);
   
   
   /**
   *
   * 重新查询导出数据
   * @param map 查询条件
   * @return 结果列表
   * @see
   * @since 1.0
   */
   List<ChbBotAstPositionRegist001Dto> queryExportListByKeysEx(@Param("registList")List<ChbBotAstPositionRegist001Dto> registList,@Param("model_type")String model_type);

   void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList")List<ChbBotAstPositionRegist001Dto> registList);
    
   Integer cancelMannultRegist(List<ChbBotAstPositionRegist001Dto> list);

void insertSeletForSub(@Param("regList")List<String> list);

List<String> selectRegistSeqnoList(@Param("list")List<ChbBotAstPositionRegist001Dto> list, @Param("flag")String flag);

void deleteRegistSeqnoList(@Param("regList")List<String> list);

List<ChbBotAstPositionRegist001Dto> selectByRegistNo(@Param("registList")List<String> keys);

}
