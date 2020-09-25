package com.joyintech.tams.invest.product.productduration.addreduce.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.department.dto.SysDepartmentDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce001Dto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: pingan-parent
 * @Package com.joyintech.tams.invest.product.productduration.addreduce.service
 * @Description: 强增强减service
 * @date Date : 2018年09月07日 9:15
 */
@Service
public class FinProductAddReduce001Service extends CommonExportService<FinProductAddReduce001Dto> {

    public Page<FinProductAddReduce001Dto> selectForPage(Map<String, Object> pageParam) {

        Page<FinProductAddReduce001Dto> result = restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/selectForPage", pageParam,
            FinProductAddReduce001Dto.class);

        if (result != null && result.getRoot() != null && !result.getRoot().isEmpty()) {
            result.getRoot().forEach(dto -> {
                if (!DictCodeList.收益类型_收益型.getValue().equals(dto.getProfitType())) {
                    Object dept = CacheUtil.getAppCache(GlobalConstant.SYS_DEPARTMENT_KEY, Map.class).get(dto.getSaleChannel());
                    if (dept != null) {
                        dto.setSaleChannel(((SysDepartmentDto) dept).getDeptName());
                    }
                } else {
                    dto.setSaleChannel(dto.getEntrusterName());
                }
                //设置汇总交易编号，撤销时需要使用
                dto.setTotleTradeId(dto.getBusiCode());
            });
        }
        return result;
    }

    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/product/productduration/addreduce/config/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinProductAddReduce001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getProdId01(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/getProdId01", param,
            SelectOptionDto.class);
    }
}
