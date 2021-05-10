/*
 * 文件名：AssetLiabilityStatementQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valbatchexport.service;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.valbatchexport.dao.ValBatchExport001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.valbatchexport.dto.ValBatchExport001Dto;
import com.joyintech.service.valuation.accounadjustmanage.valbatchexport.dto.ValBatchExport001SearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 资产负债表
 * 
 * @author huanghao
 * @version 1.0 2020年5月31日
 * @see ValBatchExport001Service
 * @since 1.0
 */
@Service(value="serviceValBatchExport001Service")
public class ValBatchExport001Service extends CommonExportService<ValBatchExport001Dto> {
    @Autowired
    private ValBatchExport001Mapper valBatchExport001Mapper;
    /**
     * 通用联动查询
     *
     * @param param
     *            : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return valBatchExport001Mapper.getLinkSelectOption(param);
    }

    /**
     * 查询科目余额表
     * @param dto
     * @return
     */
    public List<ValBatchExport001Dto> selectBokBalance(ValBatchExport001SearchDto dto) {
        return valBatchExport001Mapper.selectBokBalance(dto);
    }
    /**
     * 查询科目余额表
     *
     * @param dto
     * @return
     */
    public List<ValBatchExport001Dto> selectBokBalanceAll(ValBatchExport001SearchDto dto) {
        return valBatchExport001Mapper.selectBokBalanceAll(dto);
    }
}
