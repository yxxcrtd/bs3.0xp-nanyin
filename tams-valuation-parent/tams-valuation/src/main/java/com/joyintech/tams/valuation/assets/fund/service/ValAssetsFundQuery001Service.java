/*
 * 文件名：ValAssetFundQueryService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.fund.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assets.fund.dto.ValAssetsFundQuery001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;

/**
 * 基金类资产查询
 * 
 * @author zhangwei
 * @version 1.0 2018年7月19日
 * @see ValAssetFundQuery001Service
 * @since 1.0
 */
@Service
public class ValAssetsFundQuery001Service extends CommonExportService<ValAssetsFundQuery001Dto> {

//    @Autowired
//    private ValAssetsFundQuery001Mapper valAssetsFundQuery001Mapper;

    /**
     * 根据条件获取表格信息
     * 
     * @param params 查询参数
     * @return 分页的表格信息
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<ValAssetsFundQuery001Dto> findPage(Map<String, Object> params)
        throws Exception {
        params.put("finprodType", "F06");// 仅查询基金型资产信息
//        return super.queryForPage(() -> valAssetsFundQuery001Mapper.selectList(params));
        return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery001/findPage",
                params, ValAssetsFundQuery001Dto.class);
    }

    /**
     * 根据条件获取表格信息
     * 
     * @param params 查询参数
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<ValAssetsFundQuery001Dto> findAll(Map<String, Object> params)
        throws Exception {
        params.put("finprodType", "F06");// 仅查询基金型资产信息
//        return valAssetsFundQuery001Mapper.selectList(params);
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery001/findAll",
                params, ValAssetsFundQuery001Dto.class);
    }

    /**
     * 下拉框获取：资产
     * 
     * @param params
     * @return select节点对象
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAssets(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery001/getAssets",
                params, SelectOptionDto.class);
    }

    /**
     * 根据资产代码查询该资产是否存在交易（修改、删除校验）
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public boolean checkFindprodId(Map<String, Object> params) {

    	
    	
    	boolean flag= restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery001/checkFindprodId",
                params, boolean.class);
    	 
        return flag;
    }

    /**
     * 删除
     * 
     * @param finprodId
     * @param fundType
     * @return
     * @see
     * @since 1.0
     */
//    public void delete(String finprodId, String fundType) {
//    	Map<String,Object> map = new HashMap<>();
//    	map.put("finprodId", finprodId);
//    	map.put("fundType", fundType);
//        // 删除标的类金融产品扩展表信息
//        valAssetsFundQuery001Mapper.deleteByFindprodId2(finprodId);
//        // 删除金融产品分类表
//        valAssetsFundQuery001Mapper.deleteByFindprodId3(finprodId);
//        if (fundType.equals(DictCodeList.基金类型_货币基金.getValue())) {
//            // 删除现金流信息表
//            valAssetsFundQuery001Mapper.deleteByFindprodId4(finprodId);
//            // 删除现金流支付规则表
//            valAssetsFundQuery001Mapper.deleteByFindprodId5(finprodId);
//            // // 删除现金流计划表
//            // valAssetsFundQuery001Mapper.deleteByFindprodId6(finprodId);
//        }

//        	restTemplate.postForEntity(  
//        		ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetsFundQuery001/delete",
//        		map,Integer.class);
        // 删除标的类金融产品表信息
//        return valAssetsFundQuery001Mapper.deleteByFindprodId(finprodId);
//    }

//    @DataSource(DataSourceType.OTHER)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public List<String> getBusinessDataInFlow(ProcessMark processMark) {
//        return valAssetsFundQuery001Mapper.getBusinessDataInFlow(processMark);
//    }


}
