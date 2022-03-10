/*
 * 文件名：FieldEtrInfoQueryMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：msk
 * 修改时间：2021年12月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.business.common.dao;

import com.joyintech.tams.business.common.dto.CommonSearchInfoOperateListResponse;
import com.joyintech.tams.business.common.dto.CommonSearchQueryWorkflowResponse;
import com.joyintech.tams.business.common.dto.CommonSearchRskInfoResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 场内指令查询
 * @author msk
 * @version 1.0 2021年12月22日
 * @see EtrTrdCommonMapper
 * @since 1.0
 */
@Repository
public interface EtrTrdCommonMapper {


    List<Map<String,Object>> getPtlAcctInfo(String portfolioId);
}
