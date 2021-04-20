/*
 * 文件名：CfetsRepoDealQuotedPriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.repo.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.repo.dto.CfetsRepoDealQuotedPrice003Dto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author yufei
 * @version 1.0 2020年5月14日
 * @see CfetsRepoDealQuotedPriceService
 * @since 1.0
 */
@Service
public class CfetsRepoDealQuotedPrice004Service extends CommonExportService<CfetsRepoDealQuotedPrice003Dto> {
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;


    /**
     * 查询可质押列表
     * 
     * @param param 查询参数
     * @return 可质押基本信息
     * @see
     * @since 1.0
     */
    public Page<CfetsRepoDealQuotedPrice003Dto> queryPledgeBondList(Map<String, Object> param) {
        param.put("platDate", iPlatformDate.getSysDateStr());
        param.put("loginUserId", LoginUserUtil.getUserName());
        String bondCodesStr = (String)param.get("bondCodes");
        if(StringUtil.isNotEmpty(bondCodesStr)){
            try {
                String[] bondCodes = JsonUtils.json2Array(bondCodesStr, String[].class);
                param.put("bondCodes",bondCodes);
            }
            catch (IOException e) {
                param.put("bondCodes",new String[0]);
            }
        }
        Page<CfetsRepoDealQuotedPrice003Dto> list = restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsRepoDealQuotedPrice001/queryDefyPledgeBondList",
            param, CfetsRepoDealQuotedPrice003Dto.class);
        if(list!=null && !StringUtil.isEmptyList(list.getRoot())){
            for (CfetsRepoDealQuotedPrice003Dto dto : list.getRoot()) {
                if(dto.getAvaPledgePrin() != null && dto.getPledgeNumber() !=null){
                    dto.setAvaPledgeNumber(ArithmeticUtil.subtract(dto.getAvaPledgePrin(),dto.getPledgeNumber()));
                }
            }
        }
        return list;
    }
}
