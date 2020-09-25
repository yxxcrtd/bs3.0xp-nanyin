/*
 * 文件名：FinProductPayInt001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.payint.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.payint.dto.FinProductPayInt001Dto;

/**
 * 产品中间付息查询Service
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayInt001Service
 * @since 1.0
 */
@Service
public class FinProductPayInt001Service extends CommonExportService<FinProductPayInt001Dto> {

    /**
     * 产品代码下拉框
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public Page<SelectOptionDto> queryProdIdInfo(Map<String, Object> param)
        throws Exception {
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/queryProdIdInfo", param,
            SelectOptionDto.class);
    }

    /**
     * 组合代码下拉框
     *
     * @param param 过滤参数
     * @return 下拉框列表
     * @throws Exception Exception
     */
    public Page<SelectOptionDto> selectPortfolioId(Map<String, Object> param)
        throws Exception {
        param.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/selectPortfolioId", param,
            SelectOptionDto.class);
    }

    /**
     * 检索产品存续期交易信息
     *
     * @param pageParam 查询参数
     * @return 产品存续期交易信息
     * @see
     * @since 1.0
     */
    public Page<FinProductPayInt001Dto> findPage(Map<String, Object> pageParam) {
        // 多选下拉框值处理
        // 组合代码
        Object portfolioIdObj = pageParam.get("portfolioId");
        if (portfolioIdObj != null) {
            pageParam.put("portfolioIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioIdObj));
        }
        else {
            pageParam.put("portfolioIdList", new ArrayList<>());
        }
        // 产品代码
        Object prodId = pageParam.get("prodId");
        if (prodId != null) {
            pageParam.put("prodIdList",
                StringUtil.splitToList(GlobalConstant.COMMA, (String)prodId));
        }
        else {
            pageParam.put("prodIdList", new ArrayList<>());
        }

        pageParam.put("loginUserName", LoginUserUtil.getUserName());

        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductPayInt/findPage", pageParam,
            FinProductPayInt001Dto.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/payint/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductPayInt001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
}
