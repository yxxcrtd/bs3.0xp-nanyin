/*
 * 文件名：SecuritiesAccountInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.portfolio.dto.SecuritiesAccountInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年9月14日
 * @see SecuritiesAccountInfoService
 * @since 1.0
 */
@Service
public class SecuritiesAccountInfoService extends CommonExportService<SecuritiesAccountInfoDto>{
    /**
     * 获取资金账户信息
     * @return 
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcctId(String param){
        if(param==null || "null".equals(param)){
            param="";
        }
        List<SelectOptionDto> list =  restTemplate.getForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getSecAcctId?param="+ param,
                SelectOptionDto.class);
        return list;
    }
    
    public SecuritiesAccountInfoDto getSecAcctInfo(String secAcctNo){
        SecuritiesAccountInfoDto dto = restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getSecAcctInfo?secAcctNo="+secAcctNo,
            SecuritiesAccountInfoDto.class);
        return dto;
    }
}
