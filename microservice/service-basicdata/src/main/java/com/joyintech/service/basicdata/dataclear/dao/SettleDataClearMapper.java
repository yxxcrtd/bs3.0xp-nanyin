/*
 * 文件名：SettleDataClearMapper.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年08月10日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
package com.joyintech.service.basicdata.dataclear.dao;

import com.joyintech.service.basicdata.dataclear.dto.SettleBatchResultDto;
import com.joyintech.service.basicdata.dataimport.dto.SettleTransResultDto;
import com.joyintech.tams.common.base.entity.SettleBatchResult;

import java.util.List;
import java.util.Map;

/**
 * @author 王波
 * @date 2020年08月10日
 * @see SettleDataClearMapper
 * @since 1.0
 */
public interface SettleDataClearMapper {
    List<SettleBatchResultDto> selectBatch(Map<String, Object> map);

    int checkIsBatchRunning();
}
