package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlSubstitutionRepoPledgeDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

/**
 * @program: 质押券置换查询
 * @description: PtlRepoPledgeSearch002Service
 * @author: nixiukuan
 * @create: 2019-11-12
 **/
@Service
public class PtlRepoPledgeSearch002Service extends CommonExportService<PtlSubstitutionRepoPledgeDto> {


    /**
     * 查询画面查询方法
     *
     * @param map 画面参数
     * @return 调用微服务
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlSubstitutionRepoPledgeDto> selectForPage(Map<String, Object> map){
        Page<PtlSubstitutionRepoPledgeDto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlRepoPledgeSearch/substitutionFindPage",
                map, PtlSubstitutionRepoPledgeDto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/conf/export_substitution_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("vPayDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
        if(("mPayDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
        if(("pledgeSubstitutionDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
        if(("tradeDate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlSubstitutionRepoPledgeDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

}
