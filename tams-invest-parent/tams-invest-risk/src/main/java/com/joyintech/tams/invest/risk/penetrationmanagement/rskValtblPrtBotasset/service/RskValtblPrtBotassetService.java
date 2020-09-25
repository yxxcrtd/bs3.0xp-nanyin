/*
 * 文件名：RskValtblPrtBotassetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.BussCommonSelectService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskValtblPrtBotasset.dto.RskValtblPrtBotassetMapDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValtblPrtBotassetService
 * @since 1.0
 */
@Service
public class RskValtblPrtBotassetService extends CommonExportService<RskValtblPrtBotassetMapDto>{

    @Autowired
    private BussCommonSelectService bussCommonSelectService;
    public Page<RskValtblPrtBotassetMapDto> selectForPage(Map<String, Object> pageParam) {
    	String mappingStat = (String) pageParam.get("mappingStat");
    	if(StringUtil.equals(mappingStat,DictCodeList.映射状态_未映射.getValue())){
    		pageParam.put("mappingStat", "UNMAP");
    	}else if(StringUtil.equals(mappingStat,DictCodeList.映射状态_已映射.getValue())){
    		pageParam.put("mappingStat", "MAPPED");
    	}
    	
//        if(StringUtil.equals(pageParam.get("mappingStat"), DictCodeList.映射状态_未映射.getValue())){
//            pageParam.put("mappingStat", "UNMAP");
//        }else{
//            pageParam.put("mappingStat", "MAPPED");
//        }
    	Page<RskValtblPrtBotassetMapDto> res = restTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/findPage",
            pageParam, RskValtblPrtBotassetMapDto.class);
    	if(res!=null && res.getRoot()!=null) {
    		for(RskValtblPrtBotassetMapDto dto : res.getRoot()) {
//    			if(!SecurityUtil.isPermitted("RskValtblPrtBotasset001_btn_edit")){
    			if(!SecurityUtil.isPermitted("RskValtblPrtBotasset001_TBLROW_edit")){
    				dto.setFlg("02");
                }else {
                	dto.setFlg("01");
                }
    		}
    	}
        return res;
    }
    
    /**
     * 通过组合代码获取底层资产映射
     * <功能详细描述>
     * @param ptlId
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValtblPrtBotassetMapDto> selectBotAssetMapByPtlId(String ptlId){
        return restTemplate.getForList(ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/selectBotAssetMapByPtlId?ptlId="+ptlId
            , RskValtblPrtBotassetMapDto.class);
    }
    
    /**
     * 更新(取消)映射关系
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void update(RskValtblPrtBotassetMapDto dto){
        restTemplate.postForObject(
           ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/update",
           dto,Integer.class);
    }
    
    /**
     * 获取未映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCountForValUnmapped(Map<String,Object> paramMap){
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/getCountForValUnmapped",
            paramMap,Integer.class);
    }
    
    /**
     * 获取不同映射个数
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public Integer getCountForValDiffmapped(Map<String,Object> paramMap){
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/getCountForValDiffmapped",
            paramMap,Integer.class);
    }
    
    /**
     * 应用未映射
     * <一句话功能简述>
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void applyMapForValUnmapped(Map<String,Object> paramMap){
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/applyMapForValUnmapped",
            paramMap,Integer.class);
    }
    
    /**
     * 应用不同映射
     * <功能详细描述>
     * @param paramMap
     * @see
     * @since 1.0
     */
    public void applyMapForValDiffmapped(Map<String,Object> paramMap){
        restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/applyMapForValDiffmapped",
            paramMap,Integer.class);
    }
    
    
    /**
     * 查找资产的其他信息
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public RskValtblPrtBotassetMapDto selectAssetInfoById(@RequestBody RskValtblPrtBotassetMapDto dto){
        if(StringUtil.equals(dto.getAssetSource(), DictCodeListExt1.估值资产来源_系统内.getValue())){
            return selectAssetById(dto.getFinprodId());
        }
       return null;
    }
    
    /**
     * 通过id获取系统内资产其他信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public RskValtblPrtBotassetMapDto selectAssetById(String finprodId){
        return restTemplate.getForObject(ServiceApplication.RISK_SERVICE_NAME
            + "/RskValtblPrtBotasset/selectAssetById?finprodId="
            + finprodId,
            RskValtblPrtBotassetMapDto.class);
    }
    
    /**
     * 通过市场id获取wind 债券资产其他信息
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    public RskValtblPrtBotassetMapDto selectWindBondAssetById(String finprodId){
        return restTemplate.getForObject(ServiceApplication.RISK_SERVICE_NAME
            + "/RskValtblPrtBotasset/selectWindBondAssetById?finprodId="
            + finprodId,
            RskValtblPrtBotassetMapDto.class);
    }
    
    /**
     * 选择资产下拉框处理
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> selectAllAsset(Map<String, Object> param){
        Page<SelectOptionDto> resultPage = new Page<SelectOptionDto>(); 
        if(StringUtil.equals(param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
            //是否排除系统内资产
            param.put("isExclude", true);
            resultPage=selectWindBondAsset(param);
        }else if(StringUtil.equals(param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
            //是否排除系统内资产
            param.put("isExclude", true);
            resultPage=selectWindFundAsset(param);
        }
        if(StringUtil.isNotEmpty(param.get("finprodType2")) ){
//            resultPage.addAll(selectAsset(param).getRoot()); 
            resultPage.setRoot(selectAsset(param));
            resultPage.setTotal(resultPage.getRoot().size());
        }
        if(StringUtil.isNotEmpty(resultPage) && StringUtil.isNotEmpty(resultPage.getRoot())){
            for(SelectOptionDto selectOptionDto:resultPage.getRoot()){
                if(null!=selectOptionDto.getObject()){
                    String obj = selectOptionDto.getObject().toString();
                    if(obj.contains(",")){
                        String[] objArr = selectOptionDto.getObject().toString().split(",");
                        selectOptionDto.setText(objArr[1]+":"+selectOptionDto.getText());
                    }else{
                        selectOptionDto.setText(selectOptionDto.getId()+":"+selectOptionDto.getText());
                    }
                }
            }
        }
        return resultPage;
    }
    
    /**
     * 标的信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    private List<SelectOptionDto> selectAsset(Map<String, Object> param){
        //return bussCommonSelectService.selectCommonSelectList("Asset", param);
        return bussCommonSelectService.selectCommonSelect("Asset", param);
    }
    
    /**
     * wind bond资产
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    private Page<SelectOptionDto> selectWindBondAsset(Map<String, Object> param){
        /*return bussCommonSelectService.selectCommonSelectList("WindBondAsset", param);*/
        return bussCommonSelectService.selectCommonSelectForPage("WindBondAsset", param);
    }
    
    /**
     * wind fund资产
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    private Page<SelectOptionDto> selectWindFundAsset(Map<String, Object> param){
        return bussCommonSelectService.selectCommonSelectForPage("WindFundAsset", param);
    }
    
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/risk/penetrationmanagement/rskValtblPrtBotasset/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskValtblPrtBotassetMapDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
}
