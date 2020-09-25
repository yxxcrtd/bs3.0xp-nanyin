
package com.joyintech.tams.invest.trade.pretrdrepotrade.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;

import com.joyintech.tams.invest.common.constant.ServiceApplication;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.trade.bond.dto.TrdBondInvestTradeDto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade002Dto;
import com.joyintech.tams.invest.trade.pretrdrepotrade.dto.PreTrdRepoTrade002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PreTrdRepoTrade001Service extends CommonExportService<PreTrdRepoTrade002Dto>{

    /**
     * 针对weblogic部署无法获取模版问题
     */
    private static final String TMP_DIR = "/tpl/";
    /**
     * 质押式回购预录入查询
     * @param pageParam 查询参数
     * @return
     * @see
     * @since 1.0
     */
    public Page<PreTrdRepoTrade002Dto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/selectForPage",
                pageParam, PreTrdRepoTrade002Dto.class);
    }

    /**
     获取交易对手列表
     * @param pageParam 请求参数
     * @return 交易对手列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterAcct(@RequestBody Map<String, Object> pageParam) {
        return  restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/getCounterAcct",
                pageParam, SelectOptionDto.class);
    }

    /**
     * 获取资金账号下拉框
     *
     * @return 资产代码代码下拉框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAcct(@RequestBody Map<String, Object> pageParam) {
        return  restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/getAcct",
                pageParam, SelectOptionDto.class);
    }

    /**
     * 获取托管账号下拉框
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSecAcct(Map<String, Object> pageParam) {
        return  restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/getSecAcct",
                pageParam, SelectOptionDto.class);
    }

    /**
     * 获取资金账号详细
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public PreTrdRepoTrade002Dto getAcctId(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/getAcctId",
                pageParam, PreTrdRepoTrade002Dto.class);
    }

    /**
     * 获取托管账号详细
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public PreTrdRepoTrade002Dto getSecAcctDto(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/getSecAcctDto",
                pageParam, PreTrdRepoTrade002Dto.class);
    }

    /**
     * 获取下行交易匹配情况
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<Map<String, Object>> findTrdMatchInfo(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/PreTrdRepoTrade001/findTrdMatchInfo",
                params, List.class);
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/trade/pretrdrepotrade/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PreTrdRepoTrade002Dto> selectPageData(Map<String, Object> selectParam) {
        Page<PreTrdRepoTrade002Dto> pageList = new Page<>();
        List<PreTrdRepoTrade002Dto> resultList = new ArrayList<>();
        List<PreTrdRepoTrade002Dto> list = JSON.parseArray(String.valueOf(selectParam.get("preTrdRepoTrade002DtoList")),PreTrdRepoTrade002Dto.class);
        list.forEach(item ->{
            String parentId = item.getChildId();
            selectParam.put("parentId",parentId);
            Page<PreTrdRepoTrade002Dto> pages = this.selectForPage(selectParam);
            resultList.add(item);
            resultList.addAll(pages.getRoot());
        });
        pageList.setRoot(resultList);
        pageList.setRecords(resultList.size());
        return pageList;
    }
}
