/*
 * 文件名：RskValAssetCode004Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDtlDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月25日
 * @see RskValAssetCode004Service
 * @since 1.0
 */
@Service
@Transactional
public class RskValAssetCode004Service extends CommonExportService<RskValtblAssetSubMapDtlDto>{

    public void insert(RskValtblAssetSubMapDtlDto dto) {
         restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValAssetCode/saveAssetCodeDtl",
            dto,Integer.class);
    }
}
