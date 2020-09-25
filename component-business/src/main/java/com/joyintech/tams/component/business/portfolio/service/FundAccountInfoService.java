/*
 * 文件名：FundAccountInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.portfolio.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.portfolio.dto.FundAccountInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * 资金账户业务组件Service
 * @author fanshuanghu
 * @version 1.0 2018年9月13日
 * @see FundAccountInfoService
 * @since 1.0
 */
@Service
public class FundAccountInfoService extends CommonExportService<FundAccountInfoDto>{
    /**
     * 获取资金账户信息
     * @return 
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctNo(String param){
        List<SelectOptionDto> list =  restTemplate.getForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getAcctNo?param="+param,
                SelectOptionDto.class);
        for(SelectOptionDto dto : list ){
            dto.setId(dto.getId() + "_" + dto.getObject());
        }
        return list;
    }
    /**
     * 获取账号信息
     * @return 清算账户表信息
     * @see
     * @since 1.0
     */
    public StlCapacctInfo getCctInfo(String acctNo){
        return restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getCctInfo?acctNo="+acctNo,
            StlCapacctInfo.class);
    }
}
