/*
 * 文件名：BussParticipantInfoMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.common.dto.BussParticipantInfoDto;


/**
 * 参与方信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussParticipantInfoMapper
 * @since 1.0
 */
public interface BussParticipantInfoMapper {
    
    List<BussParticipantInfoDto> queryBussParticipantInfo(Map<String,Object> paramMap);
    
    BussParticipantInfoDto queryAllinfoByfinprodId(Map<String,Object> paramMap);
    
    /**
     * 通过通道代码获取托管账号下拉框值
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> selectAcctNoByCounterId(Map<String,Object> paramMap);
}
