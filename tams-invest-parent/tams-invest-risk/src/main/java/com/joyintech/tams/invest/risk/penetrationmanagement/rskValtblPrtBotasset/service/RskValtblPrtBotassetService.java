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

import com.google.common.collect.Lists;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.risktrial.dto.TreeNode;
import com.joyintech.tams.systemtools.util.CommonUtil;
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
    /**
     * 获取无标的信息下拉框
     * @return
     */
    public List<SelectOptionDto> selectUnTargetAsset(Map<String,Object> param){
        List<SelectOptionDto> result = simpleRestTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/selectUnTargetAsset", param, SelectOptionDto.class);
        return result;
    }

    /**
     * 以资管计划的维度获取所有含未映射资产的科目个数
     * @param parameters
     * @return
     */
    public Integer getCountOfAllUnMapped(Map<String, Object> parameters) {
        return simpleRestTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/getCountOfAllUnMapped", parameters, Integer.class);
    }
    /**
     * @Description: 查询未映射科目组装树形
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    public List<RskValtblPrtBotassetMapDto> selectUnMapSubject(Map<String, Object> paramMap) {
        return simpleRestTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/selectUnMapSubject",
                paramMap, RskValtblPrtBotassetMapDto.class);
    }
    /**
     * @Description: 组装科目树形
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    public List<TreeNode> genTreeNodeData(List<RskValtblPrtBotassetMapDto> dataList) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        if(!StringUtil.isEmptyList(dataList)){
            for (RskValtblPrtBotassetMapDto dto:dataList) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(dto.getSubCode());
                treeNode.setName(dto.getSubCode()+"("+dto.getSubName()+")");
                treeNode.setOpen(true);
                if(StringUtil.isNotEmpty(dto.getSubClass())){
                    treeNode.setpId(dto.getSubClass());
                }
                treeNodeList.add(treeNode);
            }
        }
        return treeNodeList;
    }

    /**
     * @Description: 查询已映射资产科目
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    public Page<RskValtblPrtBotassetMapDto> selectMapSubjectInfoForPage(Map<String, Object> paramMap) {
        return simpleRestTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/selectMapSubjectInfoForPage",
                paramMap, RskValtblPrtBotassetMapDto.class);
    }

    /**
     * @Description:  映射&应用
     * @Param:
     * @return:
     * @Author: liupengfei
     * @Date: 2021/3/24
     */
    public void applyMapForSubject(Map<String, Object> paramMap) {
        simpleRestTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/applyMapForSubject",
                paramMap,Integer.class);
    }

    public void cancelApplyMapForSubject(Map<String, Object> paramMap) {
        simpleRestTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME +"/RskValtblPrtBotasset/cancelApplyMapForSubject",
                paramMap,Integer.class);
    }

    public Page<SelectOptionDto> selectAllAsset1(Map<String, Object> pageParam) {
        List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
        String subClass = (String) pageParam.get("subClass");
        String finprodType = "";
        boolean flag = false;//是否无标的标识，防止重复放入
       /* List<String> subClassList = Lists.newArrayList();
        if (StringUtil.isNotEmpty(subClass)){
            subClassList = StringUtil.splitToList(",", subClass);
            if(StringUtil.isNotEmpty(subClassList.get(0))){
                if(StringUtil.equals(subClass, DictCodeListExt1.资产科目类型_债券.getValue())){
                    finprodType = DictCodeListExt1.金融产品类型_投管用_债券.getValue();
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_基金.getValue())){
                    finprodType = DictCodeListExt1.金融产品类型_投管用_基金.getValue();
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_股票.getValue())){
                    flag=true;
                    finprodType = DictCodeListExt1.金融产品类型_投管用_股票.getValue();
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_非标.getValue())){
                    finprodType = DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue();
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_项目类.getValue())){
                    finprodType = DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue();
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_存款类.getValue())){
                    finprodType = DictCodeListExt1.金融产品类型_投管用_存款.getValue();
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_股指期货类.getValue())){
                    flag=true;
                    finprodType = "F34";
                }else if(StringUtil.equals(subClass,DictCodeListExt1.资产科目类型_收益互换类.getValue())){
                    flag=true;
                    finprodType = "F35";
                }
                pageParam.put("finprodType2",finprodType);
                pageParam.put("notFinprodType2",DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
            }
        }else{pageParam.put("notFinprodType2",DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());}*/
        pageParam.put("notFinprodType2",DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
       //修改获取系统内的资产，资产通过资产要素界面维护
       // resultList.addAll(selectRskAsset(pageParam));
       // resultList.addAll(selectAsset(pageParam));
       /* if(null!=resultList&&!resultList.isEmpty()){
            for(SelectOptionDto selectOptionDto:resultList){
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
        }*/
        return selectRskAsset(pageParam);

    }

    /**
     * 获取资产维护界面维护的资产
     * @param param
     * @return
     */
    public Page<SelectOptionDto> selectRskAsset(Map<String,Object> param){
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //联社模式,查询登录人的机构
            param.put("orgCode", CommonUtil.getOrgCode());
        }
        return simpleRestTemplate.postForPage(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/selectRskAsset", param, SelectOptionDto.class);

    }
    /**
     * 获取无标的信息下拉框
     * @return
     */
   /* public List<SelectOptionDto> selectRepAssetDept(Map<String,Object> param){
        List<SelectOptionDto> result = simpleRestTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME + "/RskValtblPrtBotasset/selectRepAssetDept", param, SelectOptionDto.class);
        return result;
    }*/
}
