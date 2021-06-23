
package com.joyintech.tams.cebbank.bondptlfeepay.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.cebbank.bondptlfeepay.dto.BondPtlFeePay001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 证券组合费支付管理
 * 
 * @author hechuan
 * @version 1.0 2021年4月21日
 * @see BondPtlFeePay001Service
 * @since 1.0
 */
@Service
public class BondPtlFeePay001Service extends CommonExportService<BondPtlFeePay001Dto> {

    /**
     * 查询产品费用支付信息
     * 
     * @param param 查询条件
     * @return 费用支付信息
     * @see
     * @since 1.0
     */
    public Page<BondPtlFeePay001Dto> selectForPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        param.put("flag", DictCodeList.是否_是.getValue());
        String secAcctId = (String) param.get("secAcctId");
        if (StringUtil.isNotEmpty(secAcctId)) {
        	param.put("secAcctIdList", StringUtil.splitToList(GlobalConstant.COMMA, secAcctId));
        }
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/BondPtlFeePay/selectForPage", param,
            BondPtlFeePay001Dto.class);
    }
    
    /**
     * 证券账户账号联想文本框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String secAcctId) {
        return restTemplate.getForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/BondPtlFeePay/getSecAcctId?secAcctId="+secAcctId,
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
                                         + "/BondPtlFeePay/checkUndoPlanExists?cashId=" + cashId
                                         + "&cashNum=" + cashNum,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/cebbank/bondptlfeepay/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<BondPtlFeePay001Dto> selectPageData(Map<String, Object> selectParam) {
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
            + "/BondPtlFeePay/isInFlowDataExisted?tradeId="
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
            + "/BondPtlFeePay/isAlreadyRevoked?tradeId=" + tradeId,
            Boolean.class);
    }
}
