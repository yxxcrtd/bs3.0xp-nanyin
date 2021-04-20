package com.joyintech.tams.invest.bankuplink.basic.cfetshisdealquery.service;

import java.util.Date;
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
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.invest.bankuplink.repo.dto.RepoQuoteInfoDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

@Service
public class CfetsHisDealQuery002Service extends CommonExportService<RepoQuoteInfoDto> {

    @Autowired
    IPlatformDate iPlatformDate;

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
     * 现券买卖历史报价查询 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/bankuplink/basic/cfetshisdealquery/conf/export_conf.xml").dataSource(
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
    public Page<RepoQuoteInfoDto> selectPageData(Map<String, Object> selectParam) {
        return queryPledgeRepoPage(selectParam);
    }
}
