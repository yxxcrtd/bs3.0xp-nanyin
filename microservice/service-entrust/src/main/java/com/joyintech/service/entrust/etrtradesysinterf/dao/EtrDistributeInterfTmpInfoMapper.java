/*
 * 文件名：EtrProjectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrtradesysinterf.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.entrust.etrtradesysinterf.dto.EtrDistributeInterfTmp;

/**
 * 委托指令分发接口临时表mapper
 * 〈功能详细描述〉
 * @author chaowei
 * @version 1.0 2020年3月16日
 * @see EtrDistributeInterfTmpMapper
 * @since 1.0
 */
@Repository
public interface EtrDistributeInterfTmpInfoMapper {

    /**
     * 指令分发查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<EtrDistributeInterfTmp> selectList(@Param("entrustId") String entrustId);
    
    /**
     * 插入指令分发数据
     * @param etrDistributeInterfTmp
     * @return
     */
    int insert(EtrDistributeInterfTmp etrDistributeInterfTmp);
    
    /**
     * 删除指令分发数据
     * @param entrustId
     * @param entrustStatus
     * @return
     */
    int delete(@Param("entrustId") String entrustId);
    
    /**
     * 删除指令分发数据
     * @param entrustId
     * @param entrustStatus
     * @return
     */
    int deleteBydate(@Param("date") Date date);
   
}
