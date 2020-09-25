/*
 * 文件名：ReportContentEnum001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.thresholdCheck.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.disclosure.information.config.reportcontentenum.dto.ReportContentEnum001Dto;
import com.joyintech.tams.disclosure.information.config.thresholdCheck.dto.ThresholdCheck001Dto;

/**
 * 自定义指标管理查询mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年11月6日
 * @see ThresholdCheck001001Mapper
 * @since 1.0
 */
@Repository
public interface ThresholdCheck001001Mapper {
   
  /**
   * 以报表名称分组
   * @param pageParam
   * @return
   */
  List<ThresholdCheck001Dto>  selectIndex(Map<String, Object> pageParam);
  
  /**
   * 全部数据总量
   * @return
   */
  Integer   selectCount(Map<String, Object> pageParam);
  /**
   * 以阈值指标分组
   * @return
   */
  
  List<ThresholdCheck001Dto>  selectIndexByIndex(Map<String, Object> pageParam);
  
  
  
  List<ThresholdCheck001Dto> selectIndexDetail(Map<String, Object> pageParam);
  
  
  List<ThresholdCheck001Dto> selectIndexDetailByIndex(Map<String, Object> pageParam);
}
