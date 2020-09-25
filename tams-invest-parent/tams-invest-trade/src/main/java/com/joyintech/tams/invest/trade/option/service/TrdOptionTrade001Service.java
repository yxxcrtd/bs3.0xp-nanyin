package com.joyintech.tams.invest.trade.option.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.trade.option.dto.TrdOptionTrade001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class TrdOptionTrade001Service  extends CommonExportService<TrdOptionTrade001Dto> {
    /**
     * 检索期权交易信息
     *
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<TrdOptionTrade001Dto> findPage(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        Page<TrdOptionTrade001Dto> result = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/TrdOptionTrade/findPage",
                pageParam, TrdOptionTrade001Dto.class);
        return result;
    }
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/trade/option/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     *
     * 合约标的下拉框
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> selectFinprodIdAbbr(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/TrdOptionTrade/selectFinprodIdAbbr",
                param, List.class);
    }

    @Override
    public Page<TrdOptionTrade001Dto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
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
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdOptionTrade/isInFlowDataExisted/" + tradeId,
                null, Boolean.class);
    }

    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 start
    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param tradeId 交易编号
     * @return 存续期数据是否存在
     * @see
     * @since 1.0
     */
    public Boolean selectDurationDataCount(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdOptionTrade/selectDurationDataCount/" + tradeId,
                null, Boolean.class);
    }
    // 存在存续期交易存在时不能撤销check增加 add by renping 20191017 end

    /**
     * 根据交易编号查询该交易是否已被撤销
     *
     * @param tradeId 交易编号
     * @return 该交易是否已被撤销
     * @see
     * @since 1.0
     */
    public Boolean isRevokeDataExisted(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdOptionTrade/isAlreadyRevoked/" + tradeId,
                null, Boolean.class);
    }

    /**
     * 根据交易编号查询该交易是否已做流出资金核对
     *
     * @param tradeId 交易编号
     * @return 该交易是否已做流出资金核对
     * @see
     * @since 1.0
     */
    public Boolean isAlreadyLqdPipedTrade(String tradeId) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME
                        + "/TrdOptionTrade/isAlreadyLqdPipedTrade/" + tradeId,
                null, Boolean.class);
    }

}
