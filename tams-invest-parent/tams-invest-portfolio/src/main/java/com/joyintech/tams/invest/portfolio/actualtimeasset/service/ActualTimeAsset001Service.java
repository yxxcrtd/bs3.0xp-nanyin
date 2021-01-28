package com.joyintech.tams.invest.portfolio.actualtimeasset.service;

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
import com.joyintech.tams.invest.portfolio.actualtimeasset.dto.ActualTimeAssetDto;

/**
 * @program: tams-invest-parent
 * @description: ActualTimeAsset001Service
 * @author: yanya
 * @create: 2019-10-15
 **/
@Service
public class ActualTimeAsset001Service extends CommonExportService<ActualTimeAssetDto> {

    /**
     * 证券账户下拉框事件
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcct(Map<String,Object> map) {
        //组合代码
        Object portfolioId = map.get("portfolioId");
        if (portfolioId != null) {
            map.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioId));
        }
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/ActualTimeAsset/getSecAcct" , map,
                SelectOptionDto.class);
    }

    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssetsInfo(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/ActualTimeAsset/getAssetsInfo" , map,
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
    public Page<ActualTimeAssetDto> selectForPage(Map<String, Object> map){
        //组合代码
        Object portfolioId = map.get("portfolioId");
        Object secManageAcctId = map.get("secManageAcctId");
        if (portfolioId != null) {
            map.put("portfolioIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)portfolioId));
        }
        if (secManageAcctId != null) {
            map.put("secManageAcctIdList",
                    StringUtil.splitToList(GlobalConstant.COMMA, (String)secManageAcctId));
        }
        Page<ActualTimeAssetDto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ActualTimeAsset/findPage",
                map, ActualTimeAssetDto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/portfolio/actualtimeasset/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ActualTimeAssetDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

}
