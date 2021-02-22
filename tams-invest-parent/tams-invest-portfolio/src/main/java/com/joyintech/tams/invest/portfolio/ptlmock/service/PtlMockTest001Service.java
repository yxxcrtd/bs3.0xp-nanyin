package com.joyintech.tams.invest.portfolio.ptlmock.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlmock.dto.PtlMockTest001Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class PtlMockTest001Service extends CommonExportService<PtlMockTest001Dto> implements BaseProcessService<PtlMockTest001Dto> {

    /**
     * 分页查询
     * 
     * @param
     * @return
     */
    public Page<PtlMockTest001Dto> selectForPage(Map<String, Object> pageParam) {
        // 组合代码
        String portfolioId = (String)pageParam.get("portfolioId");
        pageParam.put("portfolioId", portfolioId);
        // 获取登录用户
        pageParam.put("loginUserName", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/selectForPage", pageParam,
            PtlMockTest001Dto.class);
    }

    /**
     * 组合代码下拉框选项获取
     */
    public List<SelectOptionDto> getPtlInfo(Map<String, Object> param) {
        List<SelectOptionDto> list = restTemplate.postForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/getPtlInfo", param,
            SelectOptionDto.class);
        return list;
    }

    /**
     * 导出 {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/ptlmock/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * 导出
     */
    @Override
    public Page<PtlMockTest001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    @Override
    public void doComplete(PtlMockTest001Dto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlMockTest/delete", entity,
            Integer.class);
    }
}
