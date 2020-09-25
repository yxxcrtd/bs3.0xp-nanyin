package com.joyintech.tams.invest.product.nolimit.totalMaintain.service;


import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;

import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: TotalMaintain001Service
 * @description: 非标限额
 * @author: jyl
 * @create: 2019-12-19 11:43
 **/
@Service
public class TotalMaintain001Service extends CommonExportService<TotalMaintain001Dto> {
    /**
     * 非标限额总量信查找
     *
     * @param pageParam
     * @return Page
     */
    public Page<TotalMaintain001Dto> findPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/TotalMaintain/findPage", pageParam,
                TotalMaintain001Dto.class);
    }

    /**
     * 重写导出功能的方法
     *
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> param = exportParam.getSelectParam();

        exportParam.config("/com/joyintech/tams/invest/product/nolimit/totalMaintain/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);

    }

    /**
     * 重写导出功能的方法
     *
     * @param selectParam
     * @return
     */
    @Override
    public Page selectPageData(Map<String, Object> selectParam) {

        Page<TotalMaintain001Dto> result = findPage(selectParam);
        return result;


    }
}
