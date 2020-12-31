package com.joyintech.tams.invest.portfolio.actualtimecapital.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.actualtimecapital.dto.ActualTimeCapital001Dto;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeCapital001Service
 * @author: yanya
 * @create: 2019-10-15
 **/
@Service
public class ActualTimeCapital001Service extends CommonExportService<ActualTimeCapital001Dto> {

    /**
     * 资金账户的下拉框事件
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcctInfo(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/ActualTimeCapital/getAcctInfo" , map,
                SelectOptionDto.class);
    }


    /**
     * 查询画面查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<ActualTimeCapital001Dto> selectForPage(Map<String, Object> map){
        // add by caiqun  工单YHZGCPB20200806001实时资金头寸里不能同时查询多个产品的现金 20200813 start
        String portfolioId = (String) map.get("portfolioId");
        if (StringUtil.isNotEmpty(portfolioId)) {
            map.put("portfolioIdList", StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
        }
        String acctNo = (String) map.get("acctNo");
        if (StringUtil.isNotEmpty(acctNo)) {
            map.put("acctNoList", StringUtil.splitToList(GlobalConstant.COMMA, acctNo));
        }
        // add by caiqun 工单YHZGCPB20200806001实时资金头寸里不能同时查询多个产品的现金 20200813 end
        Page<ActualTimeCapital001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ActualTimeCapital/findPage",
                map, ActualTimeCapital001Dto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/portfolio/actualtimecapital/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ActualTimeCapital001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

}
