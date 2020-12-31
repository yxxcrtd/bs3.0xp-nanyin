package com.joyintech.tams.invest.portfolio.feeprovisionsetting.service;

import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.portfolio.feeprovisionsetting.dto.FeeProvisionSetting001Dto;

import java.util.Map;

/**
 * 费用预提设置 service层
 * @author
 *
 */
@Service
public class FeeProvisionSetting001Service extends CommonExportService<FeeProvisionSetting001Dto>{

    /**
     * 查询费用预提
     *
     * @param param 查询条件
     * @return 费用预提信息
     * @see
     * @since 1.0
     */
    public Page<FeeProvisionSetting001Dto> selectForPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/selectForPage", param,
                FeeProvisionSetting001Dto.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/portfolio/feeprovisionsetting/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FeeProvisionSetting001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 证券账户账号联想文本框service层实现
     * @return SuggestDto
     */
    public Page<SelectOptionDto> getSecAcctId(Map<String, Object> param) {
        return restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/FeeProvisionSetting/getSecAcctId",param,
                SelectOptionDto.class);
    }

}
