/*
 * 文件名：ValAssetBook001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年5月24日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.assetbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dao.ValAssetBook001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dao.ValAssetBook002Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.assetbook.dto.ValAssetbook001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 账套管理
 * 
 * @author 黄健
 * @version 1.0 2018年5月10日
 * @see ValAssetBook001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValAssetBook001Service extends CommonExportService<ValAssetbook001Dto> implements BaseProcessService<ValAssetbook001Dto>{

    /**
     * ValAssetBook001Mapper
     */
    @Autowired
    private ValAssetBook001Mapper mapper;
    
    @Autowired
    private ValAssetBook002Mapper valAssetBook002Mapper;

    /**
     * ValBokBooksetMapper
     */
    @Autowired
    private BokBooksetMapper booksetMapper;
    
    @Autowired
    private com.joyintech.tams.common.base.dao.BokBalanceMapper bokBalanceMapper;

    /**
     * 检索账套代码列表
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetId",
                param, SelectOptionDto.class);
    }
    
    
    /**
     * 检索账套代码列表
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetIdForSelect() {
        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetIdForSelect", SelectOptionDto.class);     
        
//    	List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();
//        String booksetType = DictCodeList.账套类别_理财账套.getValue();
//
//        List<SuggestDto> listBookset = mapper.getBooksetId2(booksetType,"");
//        listBookset.forEach(item -> {
//            SelectOptionDto dto=new SelectOptionDto();
//            dto.setId(item.getCode());
//            dto.setText(item.getName());
//            listSelectDto.add(dto);
//        });
//        return listSelectDto;
    }

    /**
     * 检索账套代码列表(分页下拉框)
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findBooksetIdByPage(Map<String, Object> param) {
        
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/findBooksetIdByPage", param, SelectOptionDto.class);
        
//        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        return super.queryForPageForSelectReturn(()->mapper.findBooksetId(param));
    }
    
    /**
     * 检索账套代码列表(分页下拉框)
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findBooksetIdByType(Map<String, Object> param) {
        
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/findBooksetIdByType", param, SelectOptionDto.class);
        
//        param.put("booksetType", DictCodeList.账套类别_理财账套.getValue());
//        return super.queryForPageForSelectReturn(()->mapper.findBooksetId(param));
    }
    
    
    /**
     * 检索账套代码列表
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetIdForSelectByAll() {
        List<SelectOptionDto> listSelectDto=new ArrayList<SelectOptionDto>();

        List<SuggestDto> listBookset = mapper.getBooksetId2("","");
        listBookset.forEach(item -> {
            SelectOptionDto dto=new SelectOptionDto();
            dto.setId(item.getCode());
            dto.setText(item.getName());
            listSelectDto.add(dto);
        });
        return listSelectDto;
    }
    /**
     * 检索账套代码列表（只取理财账套）
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId2(Map<String, Object> params) {

        /*return queryForSuggest(() -> {
            return mapper.getBooksetId2(booksetType);
        }, null, param);*/
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetId2", params, SuggestDto.class);     
    }
    
    /**
     * 检索账套代码列表（取理财账套和银行账套）
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetIdAll(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetIdAll", params, SuggestDto.class);     
    }
    
    /**
     * 根据账套组中账套ID查询账套 add by yanya 20190929
     * 
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId3(Map<String, Object> params) {

        /*return queryForSuggest(() -> {
            return mapper.getBooksetId2(booksetType);
        }, null, param);*/
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetId3", params, SuggestDto.class);     
    }
    
    /**
     * 根据套帐类型与套帐名称获取数据
     * 
     * @param type 套帐类型
     * @param name 套帐名称
     * @return List<ValAssetbook001SearchDto>
     * @see
     * @since 1.0
     */
    public List<ValAssetbook001Dto> getAllAssetbook(Map<String, Object> params) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getAllAssetbook", params, ValAssetbook001Dto.class);
    }
    
    
    /**
     * 根据套帐类型与套帐名称获取数据
     * 
     * @param type 套帐类型
     * @param name 套帐名称
     * @return List<ValAssetbook001SearchDto>
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<ValAssetbook001Dto> getAllAssetbookPage(Map<String,Object> params) throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getAllAssetbookPage", params, ValAssetbook001Dto.class);
    }

    /**
     * 获取账套的名称
     * 
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBookSetName() {
        List<SelectOptionDto> listSelectDto = new ArrayList<SelectOptionDto>();
        List<ValAssetbook001Dto> list = mapper.getBookSetName();
        list.forEach(item -> {
            listSelectDto.add(createSelect(item.getBooksetName(), item.getBooksetName()));
        });
        return listSelectDto;
    }

    /**
     * 格式化创建SelectOptionDto对象
     * 
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }
    
    public int queryVoucherCount(String booksetId) {
    	
    	 return restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/queryVoucherCount?booksetId="+booksetId, Integer.class);
    
    	
//    	return valAssetBook002Mapper.queryVoucherCount(booksetId);
	}

	@Override
	public void doComplete(ValAssetbook001Dto dto, OperationType operationType) {
		
		//【一体化修改】：begin========================================================================= yangjian 2018/1/3 
		
		//【一体化修改】：delete todo: 一体化刷出来的com.joyintech.tams.common.base.dao.BokBalanceMapper无删除方法。目测是人自动加的,故进行方法迁移
		//【一体化修改】：old code
		// 删除科目余额
//		BokBalanceKey condition = new BokBalanceKey();
//		condition.setBooksetId(dto.getBooksetId());
//		bokBalanceMapper.deleteByBooksetId(condition);
		//【一体化修改】：new code
//		mapper.deleteByBooksetId(dto.getBooksetId());
		
		//【一体化修改】：end========================================================================= yangjian 2018/1/3 
		
		// 删除账套
//		booksetMapper.deleteByPrimaryKey(dto.getBooksetId());
		
		restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/doComplete?booksetId="+dto.getBooksetId(), Integer.class);
	    
		
	}


	/**
     * 查询流程中数据
     * <功能详细描述>
     * @param processMark
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
//	@DataSource(DataSourceType.OTHER)
//    public int workflowCheck(ValAssetbook001Dto dto) throws Exception {
//        //判断当前流程是否存在
//        ProcessMark processMark = WorkflowContext.getProcessMarkService().getParameterByAnnotation(dto);
//        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getDataInWorklow", processMark, Integer.class);
//        return valAssetBook002Mapper.getDataInWorklow(processMark);
//    }

    /**
     * 批量删除
     * 
     * @param valAssetbook001Dto 科目信息列表
     * @see
     * @since 1.0
     */
    public void delete(String datas) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/delete", datas, boolean.class);
    }

//
//    /**
//     * 检索账套模板
//     * 
//     * @param param 过滤参数
//     * @return 账套模板列表
//     * @see
//     * @since 1.0
//     */
//    public List<SuggestDto> getBooksetIdByBooksetType(String param) {
////        不能模糊查询，没有复用代码。（方法引用使用不当）
////        List<SuggestDto> listDto = queryForSuggest(mapper::getBooksetIdByBooksetType, null, param);
//        String booksetType = DictCodeList.账套类别_账套模板.getValue();
//        return mapper.getBooksetId2(booksetType, param);
//    }


    /**
     * 获取产品组下的账套
     * @param object
     * @return
     */
    public List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params) {
        return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetBook001/findAllProduct",
                params, SysBatchGrpPrdlink.class);
    }

    /**
     * 产品组
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> findBooksetGroup(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/findBooksetGroup",
                pageParam, SelectOptionDto.class);
    }

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBooksetIdNew(Map<String, Object> pageParam) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetIdNew",
                pageParam, SelectOptionDto.class);
    }

}