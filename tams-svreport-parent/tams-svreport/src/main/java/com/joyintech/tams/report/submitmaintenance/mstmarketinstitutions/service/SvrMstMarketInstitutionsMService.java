/*
 * 文件名：MstMarketInstitutionsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.dao.InterfCompintroductionMapper;
import com.joyintech.tams.common.base.entity.InterfCmsInstitution;
import com.joyintech.tams.common.base.entity.InterfCompintroduction;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.submitmaintenance.mstmarketinstitutions.dto.SvrMstMarketInstitutionsDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
/**
 * 
 * 市场机构管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see SvrMstMarketInstitutionsMService
 * @since 1.0
 */
@Service
public class SvrMstMarketInstitutionsMService extends CommonExportService<SvrMstMarketInstitutionsDto> 
    implements BaseProcessService<SvrMstMarketInstitutionsDto>{
    
    /**
     * wind_公司简介
     */
    @Autowired
    private InterfCompintroductionMapper interfCompintroductionMapper;
    
    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SuggestDto> getCustomerId(Map<String, Object> map) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/getCustomerId",
            map, SuggestDto.class);
    }
     
     /**
      * 客户名称联想文本框service层实现
       *
       * @param map Map<String, Object> 检索参数
       * @return SuggestDto
       */
    public List<SuggestDto> getCustomerName(Map<String, Object> map) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/getCustomerName",
            map, SuggestDto.class);
    }
    
    /**
     * 使用给定的检索分页数据。返回类型是Service的泛型。
     *  市场机构代码
     *  市场机构名称
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<SvrMstMarketInstitutionsDto> selectForPage(Map<String, Object> pageParam){
        pageParam.put("customerType", DictCodeList.客户类型_法人.getValue());
        return restTemplate.postForPage(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/selectForPage",
            pageParam, SvrMstMarketInstitutionsDto.class);
    }
    
    /**
     * selectPaRskCustomerPage
     * 根据客户代码查询市场机构详细信息
     * <功能详细描述>
     * @param map map
     * @return MstMarketInstitutionsDto
     * @see
     * @since 1.0
     */
    public SvrMstMarketInstitutionsDto selectDetail(Map<String, Object> map){
        return restTemplate.postForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/selectDetail",
            map, SvrMstMarketInstitutionsDto.class);
    }
    
    /**
     * 
     *删除时检查数据是否被使用过
     * <功能详细描述>
     * @param dto MstMarketInstitutionsDto
     * @return int
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(SvrMstMarketInstitutionsDto dto){
        return restTemplate.postForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/checkCodeIsUse",
            dto, Integer.class);
    }
    
    /**
     * 
     *批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item List<MstMarketInstitutionsDto> 
     * @return int
     * @see
     * @since 1.0
     */
    public List<SvrMstMarketInstitutionsDto> checkAllCodeIsUse(List<SvrMstMarketInstitutionsDto> item){
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/checkAllCodeIsUse",
            item, SvrMstMarketInstitutionsDto.class);
    }
    
    /**
     * 
     * 通过客户代码删除客户评级表信息 
     * <功能详细描述>
     * @param customerId String
     * @return int
     * @see
     * @since 1.0
     */
    public int deletePaRskCustomerRatingByPrimaryKey(String customerId){
        return restTemplate.postForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/deletePaRskCustomerRatingByPrimaryKey",
            customerId, Integer.class);
    }
    
    /**
     * 
     * 文件导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/report/submitmaintenance/exchangerateinfo/conf/export_conf_i.xml")
            .dataSource(DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<SvrMstMarketInstitutionsDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 
     * 实现docomplement方法
     */
    @Override
    public void doComplete(SvrMstMarketInstitutionsDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/doComplete", entity, Integer.class);
    }
    /**
     * 
     * 省市县联动
     * <功能详细描述>
     * @param param Map<String,Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getAddressSelectOption(Map<String,Object> param) {
        return restTemplate.postForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/getAddressSelectOption",
            param, SelectOptionDto.class);
    }
    
    /**
     * 
     * 查询所有的市场机构代码、名称
     * <功能详细描述>
     * @return List<MstMarketInstitutionsDto>
     * @see
     * @since 1.0
     */
    public List<SvrMstMarketInstitutionsDto> customerNameCheck(){
        return restTemplate.getForList(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/customerNameCheck",
            SvrMstMarketInstitutionsDto.class);
    }
    
    /**
     * 
     * 根据becif查询CMS_机构信息
     * <功能详细描述>
     * @param becif String
     * @return InterfCmsInstitution
     * @see
     * @since 1.0
     */
    public InterfCmsInstitution selectInterfCmsInstitution(String becif){
        return restTemplate.getForObject(
            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/selectInterfCmsInstitution?becif="+becif,
            InterfCmsInstitution.class);
    }
    
    /**
     * 
     * 根据wind编码查询wind_公司简介
     * <功能详细描述>
     * @param objectId String
     * @return InterfCompintroduction
     * @see
     * @since 1.0
     */
    public InterfCompintroduction selectInterfCompintroduction(String objectId){
        return interfCompintroductionMapper.selectByPrimaryKey(objectId);
    }

	public List<SvrMstMarketInstitutionsDto> selectForExport(Map<String, Object> param) {
		return simpleRestTemplate.postForList(
	            SvrServiceApplication.COMMON_SERVICE_NAME + "/SvrMstMarketInstitutions/selectForExport",
	            param,SvrMstMarketInstitutionsDto.class);
	}
    
}
