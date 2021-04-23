/*
 * 文件名：CfetsPortfolioAcctRelaService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yufei
 * 修改时间：2020年5月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.bankuplink.basic.cfetsportfolioacctrela.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.IbuPortfolioAcctRela;
import com.joyintech.tams.invest.bankuplink.basic.cfetsportfolioacctrela.dto.CfetsPortfolioAcctRela001Dto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * CFETS账户关联
 * 
 * @author yufei
 * @version 1.0 2020年5月12日
 * @see CfetsPortfolioAcctRelaService
 * @since 1.0
 */
@Service
public class CfetsPortfolioAcctRelaService extends CommonExportService<CfetsPortfolioAcctRela001Dto>
    implements BaseProcessService<CfetsPortfolioAcctRela001Dto> {

    /**
     * 查询机构信息
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> selectCommonSelect(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("userName", user.getUserName());
        return restTemplate.postForPage(ServiceApplication.BANKUPLINK_SERVICE_NAME
                                        + "/CfetsPortfolioAcctRela/selectMstCfetsCustomerInfo",
            pageParam, SelectOptionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(CfetsPortfolioAcctRela001Dto entity, OperationType type) {
        if (type == OperationType.NEW) {
            restTemplate.postForObject(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsPortfolioAcctRela/doInsert",
                entity, Integer.class);
        }
        else if (type == OperationType.DELETE) {
            restTemplate.postForObject(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsPortfolioAcctRela/doDelete",
                entity, Integer.class);
        }
        else if (type == OperationType.MODIFY) {
            restTemplate.postForObject(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsPortfolioAcctRela/doUpdate",
                entity, Integer.class);
        }
    }

    /**
     * CFETS账户关联-查询
     * 
     * @param pageParam 查询参数
     * @return 查询结果
     */
    public Page<CfetsPortfolioAcctRela001Dto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsPortfolioAcctRela/selectForPage",
            pageParam, CfetsPortfolioAcctRela001Dto.class);
    }

    /**
     * 根据主键查询数据 <功能详细描述>
     * 
     * @param portfolioId 组合代码
     * @param partyId 机构代码
     * @param cfetsAcctType 
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public CfetsPortfolioAcctRela001Dto queryInfoByPrimaryKey(String portfolioId, String partyId, String cfetsAcctType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("portfolioId", portfolioId);
        param.put("partyId", partyId);
        param.put("cfetsAcctType", cfetsAcctType);
        return restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
                                          + "/CfetsPortfolioAcctRela/queryInfoByPrimaryKey",
            param, CfetsPortfolioAcctRela001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/bankuplink/basic/cfetsportfolioacctrela/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CfetsPortfolioAcctRela001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * -根据组合代码和账户类型查询记录是否存在
     * @param ibuPortfolioAcctRela cfets账户关联对象
     * @return 记录数量
     */
    public int checkPortfolioIdAcctTypeExists(IbuPortfolioAcctRela ibuPortfolioAcctRela) {
    	return restTemplate.postForObject(ServiceApplication.BANKUPLINK_SERVICE_NAME
                + "/CfetsPortfolioAcctRela/checkPortfolioIdAcctTypeExists",
                ibuPortfolioAcctRela, Integer.class);
    }
}
