/*
 * 文件名：ReconcileSchemeInfo001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年11月13日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tools.collectors.JoyinCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.dao.BokSchemeInfoDetailMapper;
import com.joyintech.tams.common.base.dao.BokSchemeInfoMapper;
import com.joyintech.tams.common.base.entity.BokSchemeInfo;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dao.ValReconcileSchemeInfo001Mapper;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeAssetConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo001Dto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeNatureConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeStructConfigDto;
import com.joyintech.tams.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeSubjectConfigDto;
import org.springframework.util.CollectionUtils;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValReconcileSchemeInfo001Service extends CommonExportService<ValReconcileSchemeInfo001Dto> {

    /** 对账规则方案配置Mapper */
    @Autowired
    private ValReconcileSchemeInfo001Mapper reconcileSchemeInfo001Mapper;

    /** 对账方案表Mapper */
    @Autowired
    private BokSchemeInfoDetailMapper bokSchemeInfoDetailMapper;
    
    @Autowired
    private BokSchemeInfoMapper bokSchemeInfoMapper;
    
    /**
     * 附件表Mapper
     */
//    @Autowired
//    private MstAttMapper mstAttMapper;
    
    @Autowired
    private DBUtil dBUtil;

    /**
     * 检索方案信息
     * 
     * @param param 参数
     * @return 方案信息List
     * @throws Exception 错误
     * @since 1.0
     */
    public List<SelectOptionDto> getSchemeInfoList(Map<String, Object> params){
        return reconcileSchemeInfo001Mapper.getSchemeInfoList(params);
    }
    
    /**
     * 检索方案名称左侧列表
     * 
     * @param param 参数
     * @return 方案信息List
     * @throws Exception 错误
     * @since 1.0
     */
    public Page<ValReconcileSchemeInfo001Dto> findSchemeList(Map<String, Object> params) throws Exception{
//        return super.queryForPage(()-> reconcileSchemeInfo001Mapper.findSchemeList(params)); 
    	return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/findSchemeList",
                params, ValReconcileSchemeInfo001Dto.class);
    }

    /**
     * 页面初始化所属账套下拉框数据
     * 
     * @return 账套数据
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBokBookSet() {
        return reconcileSchemeInfo001Mapper.getBokBookSet();
    }
    
    public ValReconcileSchemeInfo001Dto getSchemeInfo(String booksetSchemeId){
//        return reconcileSchemeInfo001Mapper.getSchemeInfo(booksetSchemeId);
    	return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/getSchemeInfo",
                booksetSchemeId, ValReconcileSchemeInfo001Dto.class);
    }

    /**
     * 保存或更新方案信息
     * 
     * @param schemeDto 方案信息
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @since 1.0
     * @throws Exception 数据库操作错误
     */
    public void doSaveOrUpdate(ValReconcileSchemeInfo001Dto schemeDto)
        throws Exception {
//        // 账套估值方案
//        BokBooksetPlan bokBooksetPlan = new BokBooksetPlan();
//        BeanUtil.copyPropertiesIgnoreNull(bokBooksetPlan, schemeDto);
//        // 账套税务配置
//        BokSchemeInfo bokSchemeInfo = new BokSchemeInfo();
//        BeanUtil.copyPropertiesIgnoreNull(bokSchemeInfo, schemeDto);
//        // 账套税务配置明细（对账方案）
//        BokSchemeInfoDetail schemeInfo = new BokSchemeInfoDetail();
//        BeanUtil.copyPropertiesIgnoreNull(schemeInfo, schemeDto);
//        String schemeId = schemeDto.getSchId();
//        // 若查询库结果为空，则是新建
//        if (bokSchemeInfoDetailMapper.selectByPrimaryKey(schemeId) == null) {
//            schemeInfo.setSchId(schemeId);
//            bokSchemeInfoDetailMapper.insert(schemeInfo);
//        }
//        else {
//            // 方案代码不为空时，主表做更新，明细表做全删全插操作
//            bokSchemeInfoDetailMapper.updateByPrimaryKey(schemeInfo);
//            reconcileSchemeInfo001Mapper.deleteStructBySchemeId(schemeId);
//            reconcileSchemeInfo001Mapper.deleteSubjectBySchemeId(schemeId);
//            reconcileSchemeInfo001Mapper.deleteNatureBySchemeId(schemeId);
//            reconcileSchemeInfo001Mapper.deleteAssetBySchemeId(schemeId);
//        }
//
//        // 附件
////        MstAtt mstAtt = getMstAtt(schemeDto);
////        if (mstAtt != null) {
////            mstAttMapper.insert(mstAtt);
////        }
//
//        List<ValReconcileSchemeStructConfigDto> structList = schemeDto.getStructConfigList();
//        // 插入明细表
//        for (ValReconcileSchemeStructConfigDto structConfig : structList) {
//            structConfig.setSeqNo(dBUtil.getSequenceIndexData(DataIndexSequenceType.处理序列));
//            structConfig.setSchemeId(schemeId);
//        }
//        reconcileSchemeInfo001Mapper.insertStructConfigBatch(structList);
//        List<ValReconcileSchemeSubjectConfigDto> subjectList = schemeDto.getSubjectConfigList();
//        // 插入明细表
//        for (ValReconcileSchemeSubjectConfigDto subjectConfig : subjectList) {
//            subjectConfig.setSchemeId(schemeId);
//            // subjectConfig.setSeqNo(getSequenceIndexData(DataIndexSequenceType.估值管理));
//        }
//        reconcileSchemeInfo001Mapper.insertSubjectConfigBatch(subjectList);
//        
//        // 对账方案科目性质表
//        List<ValReconcileSchemeNatureConfigDto> natureList = schemeDto.getNatureConfigList();
//        if(!CollectionUtils.isEmpty(natureList)){
//            // 插入明细表
//            for (ValReconcileSchemeNatureConfigDto natureConfig : natureList) {
//                natureConfig.setSchemeId(schemeId);
//                // subjectConfig.setSeqNo(getSequenceIndexData(DataIndexSequenceType.估值管理));
//            }
//            reconcileSchemeInfo001Mapper.insertNatureConfigBatch(natureList);
//        }
//
//        List<ValReconcileSchemeAssetConfigDto> assetList = schemeDto.getAssetConfigList();
//        if (!CollectionUtils.isEmpty(assetList)) {
//            // 插入明细表
//            for (ValReconcileSchemeAssetConfigDto assetConfig : assetList) {
//                assetConfig.setSchemeId(schemeId);
//                // assetConfig.setSeqNo(getSequenceIndexData(DataIndexSequenceType.估值管理));
//            }
//            reconcileSchemeInfo001Mapper.insertAssetConfigBatch(assetList);
//        }

    	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/doSaveOrUpdate",
                schemeDto, Integer.class);
    }

    /**
     * 获取附件信息
     * 
     * @param schemeDto 对账规则方案配置DTO
     * @return 附件信息
     * @since 1.0
     */
//    private MstAtt getMstAtt(ValReconcileSchemeInfo001Dto schemeDto) {
//        // 附件表保存
//        MstAtt mstAtt = null;
//        List<String> fileList = new ArrayList<String>();
//        String fileIds = schemeDto.getFileIds();
//        if (!StringUtils.isEmpty(fileIds)) {
//
//            Collections.addAll(fileList, fileIds.split(GlobalConstant.COMMA));
//            for (String fileId : fileList) {
//                mstAtt = new MstAtt();
//                mstAtt.setAttId(getSequenceIndexData(DataIndexSequenceType.系统管理));
//                mstAtt.setBusinessType(DictCodeListOther.共通业务类型_估值对账规则管理.getValue());
//                mstAtt.setBussId(schemeDto.getSchId());
//                mstAtt.setBussType2(StringUtils.EMPTY);
//                mstAtt.setBussIdType2(StringUtils.EMPTY);
//                mstAtt.setFileId(fileId);
//                // mstAtt.setWorkflowId(dto.getWorkflowId());
//                mstAtt.setStatus(DictCodeList.数据状态_有效.getValue());
//                mstAtt.setCreateUser(this.getUserFromSession().getUserId());
//                mstAtt.setCreateDept(this.getUserFromSession().getDeptCode());
//                mstAtt.setUpdateUser(this.getUserFromSession().getUserId());
//            }
//        }
//        return mstAtt;
//    }

    /**
     * 获取所有科目信息map, key:科目号,value:科目名称
     * 
     * @return 科目信息map
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public Map<String, BokSubject> getAllBokSubjectMap(String booksetId) {

        Map<String,Object> params = new HashMap<>();
        params.put("booksetId", booksetId);

        // 获取科目性质映射List
        List<BokSubject> bokSubjectList = restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValReconcileSchemeInfo001/getAllBokSubjectMap",
                params,BokSubject.class);

        // list转map，科目号为KEY
        Map<String, BokSubject> bokSubjectMap = new HashMap<String, BokSubject>();
        if (!CollectionUtils.isEmpty(bokSubjectList)) {
            bokSubjectMap = bokSubjectList.stream().collect(JoyinCollectors.toMap(x -> x.getSubjectNo(), x -> x));
        }
        return bokSubjectMap;
    }

    /**
     * 删除方案信息
     * 
     * @param schemeId 方案代码
     * @since 1.0
     */
    public void deleteBySchemeId(String schemeId, String booksetId) {
//        reconcileSchemeInfo001Mapper.deleteStructBySchemeId(schemeId);
//        reconcileSchemeInfo001Mapper.deleteSubjectBySchemeId(schemeId);
//        reconcileSchemeInfo001Mapper.deleteAssetBySchemeId(schemeId);
//        bokSchemeInfoDetailMapper.deleteByPrimaryKey(schemeId);
//        bokSchemeInfoMapper.deleteByPrimaryKey(schemeId);
//        reconcileSchemeInfo001Mapper.deleteBooksetPlanBySchemeId(schemeId, booksetId);
    	Map<String,Object> params = new HashMap<>();
    	params.put("schemeId", schemeId);
    	params.put("booksetId", booksetId);
    	restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/deleteBySchemeId",
                params, Integer.class);
    }

    /**
     * 初始化结构映射
     * 
     * @param schemeId 方案代码
     * @return 结构映射明细
     * @since 1.0
     */
    public List<ValReconcileSchemeStructConfigDto> initStructGrid(Map<String, Object> params) {
//        return reconcileSchemeInfo001Mapper.initStructGrid(params);
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/initStructGrid",
                params, ValReconcileSchemeStructConfigDto.class);
    	
    }

    /**
     * 初始化科目映射
     * 
     * @param schemeId 方案代码
     * @return 科目映射明细
     * @since 1.0
     */
    public List<ValReconcileSchemeSubjectConfigDto> initSubjectGrid(Map<String, Object> params) {
//        return reconcileSchemeInfo001Mapper.initSubjectGrid(params);
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/initSubjectGrid",
                params, ValReconcileSchemeSubjectConfigDto.class);
    }
    
    /**
     * 初始化科目性质映射
     * 
     * @param schemeId 方案代码
     * @return 科目映射明细
     * @since 1.0
     */
    public List<ValReconcileSchemeNatureConfigDto> initNatureGrid(Map<String, Object> params) {
//        return reconcileSchemeInfo001Mapper.initNatureGrid(params);
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/initNatureGrid",
                params, ValReconcileSchemeNatureConfigDto.class);
    }

    /**
     * 初始化资产映射
     * 
     * @param schemeId 方案代码
     * @return 资产映射明细
     * @since 1.0
     */
    public List<ValReconcileSchemeAssetConfigDto> initAssetGrid(Map<String, Object> params) {
//        return reconcileSchemeInfo001Mapper.initAssetGrid(params);
    	return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                + "/ValReconcileSchemeInfo001/initAssetGrid",
                params, ValReconcileSchemeAssetConfigDto.class);
    }

    /**
     * 更新原默认方案为否
     * 
     * @param defaultSchemeId 原默认方案ID
     * @param yesNo 是否
     * @since 1.0
     */
    public void updateDefaultScheme(String defaultSchemeId, String yesNo) {

        BokSchemeInfo info = new BokSchemeInfo();
        info.setSchId(defaultSchemeId);
        info.setIsDefault(yesNo);
        bokSchemeInfoMapper.updateByPrimaryKeySelective(info);
    }
    
}
