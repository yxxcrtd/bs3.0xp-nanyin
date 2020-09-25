/*
 * 文件名：FinProductFeePay001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.feepay.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.feepay.dto.FinProductFeePay001Dto;

/**
 * 产品费用支付管理
 * 
 * @author zhangliang
 * @version 1.0 2018年11月8日
 * @see FinProductFeePay001Service
 * @since 1.0
 */
@Service
public class FinProductFeePay001Service extends CommonExportService<FinProductFeePay001Dto> {

    /**
     * 查询产品费用支付信息
     * 
     * @param param 查询条件
     * @return 费用支付信息
     * @see
     * @since 1.0
     */
    public Page<FinProductFeePay001Dto> selectForPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        if (StringUtil.isNotEmpty(param.get("finprodId"))) {
            param.put("finprodId",
                StringUtil.splitToList(null, param.get("finprodId").toString()));
        }
        if (StringUtil.isNotEmpty(param.get("ptlId"))) {
            param.put("ptlId", StringUtil.splitToList(null, param.get("ptlId").toString()));
        }
        if (StringUtil.isNotEmpty(param.get("feeAccount"))) {
            param.put("feeAccount", StringUtil.splitToList(null, param.get("feeAccount").toString()));
        }
        if (StringUtil.isNotEmpty(param.get("ccy"))) {
            param.put("ccy", StringUtil.splitToList(null, param.get("ccy").toString()));
        }
        param.put("flag", DictCodeList.是否_是.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductFeePay/selectForPage", param,
            FinProductFeePay001Dto.class);
    }

    /**
     * 查询是否有小于当前日期的待处理的计划
     * @param cashId 现金流代码
     * @param cashNum 现金流序号
     * @return 待处理的计划个数
     * @see
     * @since 1.0
     */
    public int checkUndoPlanExists(String cashId, String cashNum) {
        return restTemplate.getForObject(ServiceApplication.PRODUCT_SERVICE_NAME
                                         + "/FinProductFeePay/checkUndoPlanExists?cashId=" + cashId
                                         + "&cashNum=" + cashNum,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/feepay/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductFeePay001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
}
