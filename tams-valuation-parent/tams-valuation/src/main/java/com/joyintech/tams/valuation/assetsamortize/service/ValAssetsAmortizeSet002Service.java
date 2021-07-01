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

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2018年12月6日
 * @see ValAssetsAmortizeSet002Service
 * @since 1.0
 */
@Service
public class ValAssetsAmortizeSet002Service extends CommonExportService<ValAssetsAmortizeSet001Dto> implements 
    BaseProcessService<ValAssetsAmortizeSet001Dto> {

    /**
     * ValAssetsAmortizeSet002Mapper
     */
// 


    /**
     * bokAmoConfigMapper
     */
//    @Autowired
//    private BokAmoConfigMapper bokAmoConfigMapper;

    /**
     * 新建表格加载
     * 
     * @param map Map
     * @return List
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public List<ValAssetsAmortizeSet001Dto> findAll(Map<String, Object> map)
        throws Exception {
//        map.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
//        return
//        // super.queryForPage(() ->
//        ValAssetsAmortizeSet002Mapper.findPage(map);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet002/findAll",
                map, ValAssetsAmortizeSet001Dto.class);

    }

    /**
     * 表格下拉框加载
     * 
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getRecalType() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("RECAL_TYPE", DictCode.重算方式.getValue());
//        return ValAssetsAmortizeSet002Mapper.getRecalType(map);
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet002/getRecalType",
                null, SelectOptionDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValAssetsAmortizeSet001Dto entity, OperationType type) {
        // 新建操作
        if (OperationType.NEW.equals(type)) {
//            BokAmoConfig dto = new BokAmoConfig();
//            dto.setBooksetId(entity.getBooksetId());
//            dto.setBusiId(entity.getBusiId());
//            dto.setCdate(DateUtil.parse(entity.getCdate()));
//            dto.setRecalType(entity.getRecalTypeVal());
//            if (DictCodeList.重算方式_指定实际利率.getValue().equals(entity.getRecalTypeVal())) {
////                ArithmeticUtil.round(entity.getDailydscYield(), 12,null);
//                dto.setDailydscYield(ArithmeticUtil.round(entity.getDailydscYield(), 12,null));
//            }
//            else {
//                dto.setDailydscYield(null);
//            }
//            bokAmoConfigMapper.insert(dto);
        	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet002/insert",
        			entity,Integer.class);

        }

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
//        return ValAssetsAmortizeSet002Mapper.checkBokAmoConfig(dto);
    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet002/checkBokAmoConfig",
    			dto,boolean.class);
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
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsAmortizeSet002/getFinprodId2",
                map, SelectOptionDto.class);
    }
}
