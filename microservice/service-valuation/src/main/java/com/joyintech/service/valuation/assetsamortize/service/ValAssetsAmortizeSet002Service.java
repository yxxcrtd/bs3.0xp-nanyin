/*
 * 文件名：ValAssetsAmortizeSet001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assetsamortize.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.assetsamortize.dao.ValAssetsAmortizeSet002Mapper;
import com.joyintech.service.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.common.base.dao.BokAmoConfigMapper;
import com.joyintech.tams.common.base.entity.BokAmoConfig;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年12月6日
 * @see ValAssetsAmortizeSet002Service
 * @since 1.0
 */
@Service(value="serviceValAssetsAmortizeSet002Service")
public class ValAssetsAmortizeSet002Service extends CommonExportService<ValAssetsAmortizeSet001Dto> {

    /**
     * ValAssetsAmortizeSet002Mapper
     */
    @Autowired
    private ValAssetsAmortizeSet002Mapper ValAssetsAmortizeSet002Mapper;

    /**
     * bokAmoConfigMapper
     */
    @Autowired
    private BokAmoConfigMapper bokAmoConfigMapper;

    /**
     * 新建表格加载
     * 
     * @param map Map
     * @return List
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<ValAssetsAmortizeSet001Dto> findAll(Map<String, Object> map){
        map.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return
        // super.queryForPage(() ->
        ValAssetsAmortizeSet002Mapper.findPage(map);

    }

    /**
     * 表格下拉框加载
     * 
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getRecalType() {
        Map<String, Object> map = new HashMap<>();
        map.put("RECAL_TYPE", DictCode.重算方式.getValue());
        return ValAssetsAmortizeSet002Mapper.getRecalType(map);
    }

   

    public int doComplete(ValAssetsAmortizeSet001Dto entity, OperationType type) {
        // 新建操作
    	int i=0;
        if (OperationType.NEW.equals(type)) {
            BokAmoConfig dto = new BokAmoConfig();
            dto.setBooksetId(entity.getBooksetId());
            dto.setBusiId(entity.getBusiId());
            dto.setCdate(DateUtil.parse(entity.getCdate()));
            dto.setRecalType(entity.getRecalTypeVal());
            if (DictCodeList.重算方式_指定实际利率.getValue().equals(entity.getRecalTypeVal())) {
//                ArithmeticUtil.round(entity.getDailydscYield(), 12,null);
                dto.setDailydscYield(ArithmeticUtil.round(entity.getDailydscYield(), 12,null));
            }
            else {
                dto.setDailydscYield(null);
            }
           i = bokAmoConfigMapper.insert(dto);

        }
        return i;
    }

    /**
     * check 摊销表主键数据是否存在
     * 
     * @param dto ValAssetsAmortizeSet001Dto
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean checkBokAmoConfig(ValAssetsAmortizeSet001Dto dto) {
        return ValAssetsAmortizeSet002Mapper.checkBokAmoConfig(dto);
    }

    /**
     * 资产代码下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId2(int page,int length,Map<String, Object> param) {
        param.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return super.queryForPageForSelectReturn(page,length,()->ValAssetsAmortizeSet002Mapper.getFinprodId2(param));
    }
}
