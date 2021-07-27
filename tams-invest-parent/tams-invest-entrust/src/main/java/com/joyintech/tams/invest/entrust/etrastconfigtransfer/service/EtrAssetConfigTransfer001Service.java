package com.joyintech.tams.invest.entrust.etrastconfigtransfer.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 资产配置快速转仓指令service
 * 产品信息查询
 * @author 裴家俊
 * @version 2021年7月20日
 * @see EtrAssetConfigTransfer001Service
 * @since 1.0
 */

@Service
public class EtrAssetConfigTransfer001Service extends CommonExportService<PtlPortfolio>{

    public Page<PtlPortfolio> selectForPage(Map<String, Object> param) {
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrAssetConfigTransfer/findPage", param, PtlPortfolio.class);
    }

    /**
     * 导出指向配置文件
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrastconfigtransfer/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }

    @Override
    public Page<PtlPortfolio> selectPageData(Map<String, Object> selectParam) {
        Page<PtlPortfolio> page=this.selectForPage(selectParam);
        page.getRoot().stream().forEach(item->{
            if(StringUtil.isNotEmpty(item.getMdate())) {
                item.setRemark(String.valueOf(DateUtil.getBetweenDayNumber(DateUtil.platDateFromCache(), item.getMdate())));
            }
        });
        return page;
    }

    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(Arrays.asList("vdate","mdate").contains(propName)){
            cell.setCellValue((DateUtil.format((Date)value)));
        }
    }

}
