/*
 * 文件名：ValAssetBook001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.inertfLog.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.interf.inertfLog.dto.ValInertfLog001Dto;

/**
 * 接口日志
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValInertfLog001Mapper
 * @since 1.0
 */
@Repository
public interface ValInertfLog001Mapper {

    /**
     * 加载表格
     * 
     * @param fileType 文件类型
     * @param fileName 文件名称
     * @param exDate 日期
     * @param check 是否通过
     * @return List<ValInertfLog001Dto>
     * @see
     * @since 1.0
     */
    List<ValInertfLog001Dto> search(@Param("tmpName") String tmpName, @Param("fileName") String fileName,
                           @Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("check") String check);

}
