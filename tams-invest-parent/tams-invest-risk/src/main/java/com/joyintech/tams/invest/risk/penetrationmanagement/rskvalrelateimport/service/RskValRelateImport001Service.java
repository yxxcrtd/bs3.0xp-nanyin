/*
 * 文件名：RskValRelateImport001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：dongzhiling
 * 修改时间：2018年12月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalrelateimport.dto.RskValRelateImport001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author dongzhiling
 * @version 1.0 2018年12月20日
 * @see RskValRelateImport001Service
 * @since 1.0
 */
@Service
public class RskValRelateImport001Service extends CommonExportService<RskValRelateImport001Dto> {

    /**
     * 画面查询 <功能详细描述>
     * 
     * @param parameters parameters
     * @return
     * @see
     * @since 1.0
     */
    public Page<RskValRelateImport001Dto> selecForPage(Map<String, Object> parameters) {
        if (StringUtil.isNotEmpty(parameters.get("groupId"))) {
            parameters.put("groupId", StringUtil.splitToList(null, parameters.get("groupId").toString()));
        }
        parameters.put("loginUserName", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME + "/RskValRelateImport/selectForPage",
            parameters, RskValRelateImport001Dto.class);
    }

    /**
     * <一句话功能简述>
     * 组合代码联想下拉框
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> pageParam) {
//        pageParam.put("loginUserName", SecurityUtil.getLoginUser().getUserName());
        pageParam.put("selectTarget", "PortfolioId");
        pageParam.put("portfolioType", DictCodeListExt1.组合类型_委外组合.getValue());
        return restTemplate.postForList(
            ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/select" + pageParam.get("selectTarget").toString(),
            pageParam, SelectOptionDto.class);
    }

    /**
     * <一句话功能简述>
     * 批量删除
     * @param dto
     * @see
     * @since 1.0
     */
    public int delete(RskValRelateImport001Dto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME + "/RskValRelateImport/deleteList", dto,
            Integer.class);
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/penetrationmanagement/rskvalrelateimport/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskValRelateImport001Dto> selectPageData(Map<String, Object> selectParam) {
        return selecForPage(selectParam);
    }

}
