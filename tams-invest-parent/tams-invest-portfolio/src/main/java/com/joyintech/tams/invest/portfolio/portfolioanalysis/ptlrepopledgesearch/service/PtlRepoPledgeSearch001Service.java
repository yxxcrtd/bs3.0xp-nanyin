package com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinPledgeDisplaceDetail;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: tams-invest-parent
 * @description: PtlRepoPledgeSearch001Service
 * @author: liuwei
 * @create: 2018-11-27 16:47
 **/
@Service
public class PtlRepoPledgeSearch001Service extends CommonExportService<PtlRepoPledgeSearchDto> {

    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    /**
     * 组合代码的联想下拉框事件
     *
     * @param map 输入的参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getCounterId(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/PtlRepoPledgeSearch/getCounterId" , map,
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
    public List<SelectOptionDto> getFinprodMarketId(Map<String,Object> map) {
        return restTemplate.postForList(ServiceApplication.PORTFOLIO_SERVICE_NAME
                        + "/PtlRepoPledgeSearch/getFinprodMarketId" , map,
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
    public Page<PtlRepoPledgeSearchDto> selectForPage(Map<String, Object> map){
        map.put("sysDate",iPlatformDate!=null?iPlatformDate.getSysDateStr():DateUtil.format(DateUtil.now()));
        Page<PtlRepoPledgeSearchDto> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlRepoPledgeSearch/findPage",
                map, PtlRepoPledgeSearchDto.class);
        return page;
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/portfolio/portfolioanalysis/ptlrepopledgesearch/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("vdate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
        if(("mdate".equals(propName)) && value != null){
            cell.setCellValue(DateUtil.format((Date)value));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlRepoPledgeSearchDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 根据交易编号查询质押券置换明细
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Page<FinPledgeDisplaceDetail> getPledgeDetail(Map<String, Object> map) {
        Page<FinPledgeDisplaceDetail> page = restTemplate.postForPage(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlRepoPledgeSearch/getPledgeDetail",
                map, FinPledgeDisplaceDetail.class);
        return page;
    }
}
