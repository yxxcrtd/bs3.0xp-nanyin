/*
 * 文件名：MstOpicsParaManteMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwei
 * 修改时间：2018年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.basicdata.mstopicsparamante.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.mstopicsparamante.dto.MstOpicsParaManteDto;


/**
 * OPICS参数维护Mapper
 * @author huwei
 * @version 1.0 2018年8月15日
 * @see MstOpicsParaManteMapper
 * @since 1.0
 */
public interface MstOpicsParaManteMapper {

    /**
     * 查询按钮的查询方法
     * 
     * @param param map
     * @return 用户信息
     */
    List<MstOpicsParaManteDto> selectOpicsParaManteInfo(Map<String, Object> param);

    /**
     * 通过主键查询方法 <一句话功能简述> <功能详细描述>
     * 
     * @param param map
     * @return MstOpicsParaManteDto
     * @see
     * @since 1.0
     */
    MstOpicsParaManteDto selectByPrimaryKey(Map<String, Object> param);

}
