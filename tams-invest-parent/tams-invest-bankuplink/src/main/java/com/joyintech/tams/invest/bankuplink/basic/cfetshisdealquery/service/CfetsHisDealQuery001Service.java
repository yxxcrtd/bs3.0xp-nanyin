package com.joyintech.tams.invest.bankuplink.basic.cfetshisdealquery.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.bankuplink.basic.cfetshisdealquery.dto.CfetsHisDealQuery001Dto;
import com.joyintech.tams.invest.bankuplink.bond.dto.BondQuoteInfoDto;
import com.joyintech.tams.invest.bankuplink.repo.dto.RepoQuoteInfoDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class CfetsHisDealQuery001Service extends CommonExportService<BondQuoteInfoDto>
    implements BaseProcessService<CfetsHisDealQuery001Dto> {

    @Autowired
    IPlatformDate iPlatformDate;

    /**
     * 现券买卖历史报价查询 list
     * 
     * @param param
     * @return
     */
    public List<BondQuoteInfoDto> queryHisDealList(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("loginUserId", user.getUserName());
        param.put("isAdmin", user.isAdmin());
        // 待回复报价是否所有交易员可见（01：不允许，02：允许）
        param.put("unAnswerFlag", CacheUtil.getSysParam("ibuFlag_allTraderCanShowUnAnswerQuote"));
        return restTemplate.postForList(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsHisDealQuery/queryHisDealList",
            param, BondQuoteInfoDto.class);
    }

    /**
     * 现券买卖历史报价查询 page
     * 
     * @param pageParam
     * @return
     */
    public Page<BondQuoteInfoDto> queryHisDealPage(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("loginUserId", user.getUserName());
        pageParam.put("isAdmin", user.isAdmin());
        // 待回复报价是否所有交易员可见（01：不允许，02：允许）
        pageParam.put("unAnswerFlag",
            CacheUtil.getSysParam("ibuFlag_allTraderCanShowUnAnswerQuote"));
        return restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsHisDealQuery/queryHisDealPage",
            pageParam, BondQuoteInfoDto.class);
    }

    /**
     * 质押式回购历史报价查询 list
     * 
     * @param param
     * @return
     */
    public List<RepoQuoteInfoDto> queryPledgeRepoList(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("loginUserId", user.getUserName());
        param.put("isAdmin", user.isAdmin());
        // 待回复报价是否所有交易员可见（01：不允许，02：允许）
        param.put("unAnswerFlag", CacheUtil.getSysParam("ibuFlag_allTraderCanShowUnAnswerQuote"));
        return restTemplate.postForList(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsHisDealQuery/queryPledgeRepoList",
            param, RepoQuoteInfoDto.class);
    }

    /**
     * 质押式回购历史报价查询 page
     * 
     * @param pageParam
     * @return
     */
    public Page<RepoQuoteInfoDto> queryPledgeRepoPage(Map<String, Object> pageParam) {
        User user = SecurityUtil.getLoginUser();
        pageParam.put("loginUserId", user.getUserName());
        pageParam.put("isAdmin", user.isAdmin());
        // 待回复报价是否所有交易员可见（01：不允许，02：允许）
        pageParam.put("unAnswerFlag",
            CacheUtil.getSysParam("ibuFlag_allTraderCanShowUnAnswerQuote"));
        return restTemplate.postForPage(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsHisDealQuery/queryPledgeRepoPage",
            pageParam, RepoQuoteInfoDto.class);
    }

    /**
     * 查询保价编号list
     * 
     * @param param
     * @return
     */
    public List<SuggestDto> queryQuoteIdList(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsHisDealQuery/queryQuoteIdList",
            param, SuggestDto.class);
    }

    /**
     * 现券买卖历史报价查询 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        // 现券历史报价
        exportParam.config(
            "/com/joyintech/tams/invest/bankuplink/basic/cfetshisdealquery/conf/export_confDvp.xml").dataSource(
                DataSourceType.MASERT);
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName,"quoteDate")){
            if(value instanceof Date){
                cell.setCellValue(DateUtil.format((Date)value));
            }else if (value instanceof Long){
                cell.setCellValue(DateUtil.format(new Date((Long) value)));
            }
        }
    }
    @Override
    public Page<BondQuoteInfoDto> selectPageData(Map<String, Object> selectParam) {
        Page<BondQuoteInfoDto> page = queryHisDealPage(selectParam);
        return page;
    }

    /**
     * CFETS历史报价查询 撤销
     */
    @Override
    public void doComplete(CfetsHisDealQuery001Dto entity, OperationType type) {
        // 历史报价撤销
        if (OperationType.DELETE.equals(type)) {
            restTemplate.postForObject(
                ServiceApplication.BANKUPLINK_SERVICE_NAME + "/CfetsHisDealQuery/doComplete",
                entity, CfetsHisDealQuery001Dto.class);
        }
    }

}
