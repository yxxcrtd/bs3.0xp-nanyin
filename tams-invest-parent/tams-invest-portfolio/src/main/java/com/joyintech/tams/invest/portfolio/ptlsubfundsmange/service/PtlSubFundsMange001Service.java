/*
 * 文件名：PtlManagementInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年9月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.portfolio.ptlsubfundsmange.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import com.joyintech.tams.invest.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysis001Dto;
import com.joyintech.tams.invest.portfolio.ptlsubfundsmange.dto.PtlSubFundsMange001Dto;

/**
 * 子组合管理Service
 * @author pancz
 * @version 1.0 2018年9月6日
 * @see PtlSubFundsMange001Service
 * @since 1.0
 */
@Service("PtlSubFundsMange001Service")
@Transactional(rollbackFor=Exception.class)
public class PtlSubFundsMange001Service extends CommonExportService<PtlSubFundsMange001Dto> {

    /**
     * 根据产品简称模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdNameInfo(Map<String, Object> map) {
//        map.put("userId", SecurityUtil.getLoginUser().getUserName());
//        map.put("qryFlag", "2");
        return restTemplate.postForList(
        		ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/queryProdNameInfo", map,
            SelectOptionDto.class);
    }
    
    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
//        map.put("userId", SecurityUtil.getLoginUser().getUserName());
//        map.put("qryFlag", "2");
        return restTemplate.postForList(
        		ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/queryProdIdInfo", map,
            SelectOptionDto.class);
    }
    
    /**
     * 查询
     * 
     * @param params Map<String, Object>
     * @return Page<ValIncomeStatisticsQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<PtlSubFundsMange001Dto> findPage(Map<String, Object> params)
        throws Exception {
    	User user = SecurityUtil.getLoginUser();
//    	params.put("userId", user.getUid());
    	params.put("userId", user.getUserName());
        return restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/findIncomeStatisticsPage",
                params, PtlSubFundsMange001Dto.class);
    }
    
    public List<PtlSubFundsMange001Dto> findPageList(Map<String, Object> params)
            throws Exception {
        	User user = SecurityUtil.getLoginUser();
//        	params.put("userId", user.getUid());
        	params.put("userId", user.getUserName());
            return restTemplate.postForList(
                    ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlSubFundsMange/findIncomeStatisticsPageList",
                    params, PtlSubFundsMange001Dto.class);
        }
    
}
