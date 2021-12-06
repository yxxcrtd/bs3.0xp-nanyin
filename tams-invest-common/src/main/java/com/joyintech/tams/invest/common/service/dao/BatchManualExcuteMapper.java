/*
 * 文件名：DateCalcCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.service.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author etliu
 * @version 1.0 2021年1月5日
 * @see BatchManualExcuteMapper
 * @since 1.0
 */
@Repository
public interface BatchManualExcuteMapper {

    List<String> listSysBatchTask(Map<String,Object> param);
}
