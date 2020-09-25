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

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.assetsamortize.dao.ValAssetsAmortizeSet001Mapper;
import com.joyintech.service.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.common.base.dao.BokAmoConfigMapper;
import com.joyintech.tams.common.base.entity.BokAmoConfigKey;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产摊销
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet001Service
 * @since 1.0
 */
@Service(value="serviceValAssetsAmortizeSet001Service")
public class ValAssetsAmortizeSet001Service extends CommonExportService<ValAssetsAmortizeSet001Dto>{

    /**
     * ValAssetsAmortizeSet001Mapper
     */
    @Autowired
    private ValAssetsAmortizeSet001Mapper ValAssetsAmortizeSet001Mapper;

    /**
     * bokAmoConfigMapper
     */
    @Autowired
    private BokAmoConfigMapper bokAmoConfigMapper;

    /**
     * 资产代码下拉框加载
     * 
     * @return List
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId() {
        Map<String, Object> map = new HashMap<>();
        map.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return ValAssetsAmortizeSet001Mapper.getFinprodId(map);
    }

    /**
     * 账套代码下拉框加载
     * 
     * @return List
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> map) {
        map.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
        return super.queryForPageForSelectReturn(()-> ValAssetsAmortizeSet001Mapper.getBooksetId(map));
    }

    /**
     * 表格分页
     * 
     * @param map Map
     * @return Page
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ValAssetsAmortizeSet001Dto> findPage(int page, int length, Map<String, Object> params)
        throws Exception {
        // return ValAssetsAmortizeSet001Mapper.findPage(map);
        return super.queryForPageForSelectReturn(page, length, () -> ValAssetsAmortizeSet001Mapper.findPage(params));
    }

    /**
     * 导出数据
     * 
     * @param map Map
     * @return List
     * @see
     * @since 1.0
     */
    public List<ValAssetsAmortizeSet001Dto> findAll(Map<String, Object> map) {
        return ValAssetsAmortizeSet001Mapper.findPage(map);
    }

  
    public int doComplete(ValAssetsAmortizeSet001Dto entity) {
        
            BokAmoConfigKey key = new BokAmoConfigKey();
            key.setBooksetId(entity.getBooksetId());
            key.setBusiId(entity.getBusiId());
            key.setCdate(DateUtil.parse(entity.getCdate()));
            bokAmoConfigMapper.deleteByPrimaryKey(key);
            return 1;
     
    }

    /**
     * 得到比较的账套日期
     * 
     * @param booksetId 账套代码
     * @return String
     * @see
     * @since 1.0
     */
    public String getCdate(String booksetId) {
        return ValAssetsAmortizeSet001Mapper.getCdate(booksetId);
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
        return super.queryForPageForSelectReturn(page,length,()->ValAssetsAmortizeSet001Mapper.getFinprodId2(param));
    }


}
