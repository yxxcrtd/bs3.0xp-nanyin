/*
 * 文件名：ValAssetsAmortizeSet001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assetsamortize.service;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.dao.BokAmoConfigMapper;
import com.joyintech.tams.common.base.entity.BokAmoConfigKey;
import com.joyintech.tams.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
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
@Service
public class ValAssetsAmortizeSet001Service extends CommonExportService<ValAssetsAmortizeSet001Dto> implements 
    BaseProcessService<ValAssetsAmortizeSet001Dto> {

    /**
     * ValAssetsAmortizeSet001Mapper
     */
//    @Autowired
//    private ValAssetsAmortizeSet001Mapper ValAssetsAmortizeSet001Mapper;

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
//        Map<String, Object> map = new HashMap<>();
//        map.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
//        return ValAssetsAmortizeSet001Mapper.getFinprodId(map);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/getFinprodId",
                null, SelectOptionDto.class);
    }

    /**
     * 账套代码下拉框加载
     * 
     * @return List
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> map) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("booksetType", DictCodeList.账套类别_账套模板.getValue());
//        return ValAssetsAmortizeSet001Mapper.getBooksetId(map);
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/getBooksetId",
                map, SelectOptionDto.class);
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
    public Page<ValAssetsAmortizeSet001Dto> findPage(Map<String, Object> map)
        throws Exception {
        // return ValAssetsAmortizeSet001Mapper.findPage(map);
//        return super.queryForPage(() -> ValAssetsAmortizeSet001Mapper.findPage(map));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/findPage",
                map, ValAssetsAmortizeSet001Dto.class);
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
//        return ValAssetsAmortizeSet001Mapper.findPage(map);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/findAll",
                map, ValAssetsAmortizeSet001Dto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValAssetsAmortizeSet001Dto entity, OperationType type) {
        if (OperationType.DELETE.equals(type)) {
//            BokAmoConfigKey key = new BokAmoConfigKey();
//            key.setBooksetId(entity.getBooksetId());
//            key.setBusiId(entity.getBusiId());
//            key.setCdate(DateUtil.parse(entity.getCdate()));
//            bokAmoConfigMapper.deleteByPrimaryKey(key);
        	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/delete",
        			entity,Integer.class);
        }
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
//        return ValAssetsAmortizeSet001Mapper.getCdate(booksetId);
    	return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/getCdate?booksetId="+booksetId,
    			String.class);
    }


    /**
     * 资产代码下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId2(Map<String, Object> map) {
        map.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet001/getFinprodId2",
                map, SelectOptionDto.class);
    }

}
