/*
 * 文件名：InvestFeePay001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：malinlin
 * 修改时间：2019年12月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.feepay.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.feepay.dto.InvestFeePay001Dto;

/**
 * 投资端费用支付管理
 * 
 * @author malinlin
 * @version 1.0 2019年12月8日
 * @see InvestFeePay001Service
 * @since 1.0
 */
@Service
public class InvestFeePay001Service extends CommonExportService<InvestFeePay001Dto> {

    /**
     * 查询产品费用支付信息
     * 
     * @param param 查询条件
     * @return 费用支付信息
     * @see
     * @since 1.0
     */
    public Page<InvestFeePay001Dto> selectForPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        param.put("flag", DictCodeList.是否_是.getValue());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/InvestFeePay/selectForPage", param,
            InvestFeePay001Dto.class);
    }
    
    /**
     * 证券账户账号联想文本框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String secAcctId) {
        return restTemplate.getForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/InvestFeePay/getSecAcctId?secAcctId="+secAcctId,
            SuggestDto.class);
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
        return restTemplate.getForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
                                         + "/InvestFeePay/checkUndoPlanExists?cashId=" + cashId
                                         + "&cashNum=" + cashNum,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/portfolio/feepay/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<InvestFeePay001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 根据交易编号查询流程中数据是否存在
     *
     * @param tradeId 交易编号
     * @return 流程中数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean isInFlowDataExisted(String tradeId) {
        return restTemplate.getForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/InvestFeePay/isInFlowDataExisted?tradeId="
                                         + tradeId,
            Boolean.class);
    }

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isRevokeDataExisted(String tradeId) {
        return restTemplate.getForObject(ServiceApplication.PORTFOLIO_SERVICE_NAME
            + "/InvestFeePay/isAlreadyRevoked?tradeId=" + tradeId,
            Boolean.class);
    }
}
