package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlPldgeSummaryDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PtlRepoPledgeSearch004Service extends CommonExportService<PtlPldgeSummaryDto> {
    
    /**
     * 查询画面查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlPldgeSummaryDto> selectForPage(Map<String, Object> map){
        String portfolioId="";
        List<String> groupcode = new ArrayList<>();
        if(map.get("portfolioId")!=null){
            portfolioId = map.get("portfolioId").toString();
            // 分割组合代码
            String[] groupid = portfolioId.split(",");
            for (int i = 0; i < groupid.length; i++ ) {
                groupcode.add(groupid[i]);
            }
            map.put("groupcode", groupcode);
        }
        Page<PtlPldgeSummaryDto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlRepoPledgeSearch/ptlPledgeSummaryFindPage",
                map, PtlPldgeSummaryDto.class);
        return page;
    }

    /**
     * 导出 
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/conf/export_ptldgesummary_conf.xml").dataSource(DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlPldgeSummaryDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
}
