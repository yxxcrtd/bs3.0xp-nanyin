/*
 * 文件名：PtlAnalyzeAfterInvest001Service
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：李淑萍
 * 修改时间：2019/10/23
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
package com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlanalyzeafterinvest.dto.PtlAnalyzeAfterInvest001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author 李淑萍
 * @version 1.0 2019/10/23
 * @see
 * @since 1.0
 */
@Service
public class PtlAnalyzeAfterInvest001Service extends CommonExportService<PtlAnalyzeAfterInvest001Dto>
        implements BaseProcessService<PtlAnalyzeAfterInvest001Dto> {
    /**
     * 分页查询
     * @param pageParam
     * @return
     */
    public Page<PtlAnalyzeAfterInvest001Dto> selectForPage(Map<String, Object> pageParam){
        if (StringUtil.isNotEmpty(pageParam.get("finprodId"))) {
            pageParam.put("finprodId", StringUtil.splitToList(null, pageParam.get("finprodId").toString()));
        }else{
            pageParam.remove("finprodId");
        }
        Page<PtlAnalyzeAfterInvest001Dto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlAnalyzeAfterInvest/findPage",
                pageParam, PtlAnalyzeAfterInvest001Dto.class);
        return page;
    }

    /**
     * 导出
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/portfolio/ptlanalyzeafterinvest/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    @Override
    public Page<PtlAnalyzeAfterInvest001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    @Override
    public void doComplete(PtlAnalyzeAfterInvest001Dto ptlAnalyzeAfterInvest001Dto, OperationType operationType) {

    }
}
