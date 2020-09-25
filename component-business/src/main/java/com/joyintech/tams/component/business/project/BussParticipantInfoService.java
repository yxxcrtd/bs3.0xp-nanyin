/*
 * 文件名：BussParticipantInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.project.dto.BussParticipantInfoDto;

/**
 * 参与方信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see BussParticipantInfoService
 * @since 1.0
 */
public interface BussParticipantInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussParticipantInfoDto";
    
    
    BussParticipantInfoDto queryAllinfoByfinprodId(Map<String,Object> paramMap);
    
    List<BussParticipantInfoDto> queryBussParticipantInfo(Map<String,Object> paramMap);
    
    BigDecimal sumChannelFee(List<BussParticipantInfoDto> list);
    
    List<SelectOptionDto> selectAcctNoByCounterId(Map<String,Object> paramMap);
}
