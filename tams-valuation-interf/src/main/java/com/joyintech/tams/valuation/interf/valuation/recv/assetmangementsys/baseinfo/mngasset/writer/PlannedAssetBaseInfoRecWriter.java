/*
 * 文件名：PlannedAssetBaseInfoRecWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.interf.valuation.recv.common.service.AssetmanageRecvCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValCashCalcRuleBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValCashInfoBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValCashPayRuleBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValCashPlanBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValFinProductBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValFinProductTypeBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValInterflogBatchMapper;
import com.joyintech.tams.valuation.interf.core.InterfBaseWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngasset.entity.PlannedAssetBaseInfoForInterfRecProcessorResult;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;

/**
 * 计划类资产（利率型）基本信息 数据库写入
 * 
 * @author ruanhongming
 * @version 1.0 2018年6月25日
 * @see PlannedAssetBaseInfoRecWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class PlannedAssetBaseInfoRecWriter extends InterfBaseWriter<BaseEntity> {

    /**
     * 标的类金融产品Mapper
     */
    @Autowired
    private ValFinProductBatchMapper valFinProductBatchMapper;
    
    /**
     * 现金流信息Mapper
     */
    @Autowired
    private ValCashInfoBatchMapper valCashInfoBatchMapper;
    
    /**
     * 现金流计算规则BatchMapper
     */
    @Autowired
    private ValCashCalcRuleBatchMapper valCashCalcRuleBatchMapper;
    
    /**
     * 现金流支付规则BatchMapper
     */
    @Autowired
    private ValCashPayRuleBatchMapper valCashPayRuleBatchMapper;
   
    /**
     * 现金流计划BatchMapper
     */
    @Autowired
    private ValCashPlanBatchMapper valCashPlanBatchMapper;
    
    /**add by wujie on 20180822 start**/
    /**
     * 金融产品分类
     */
    @Autowired
    private  ValFinProductTypeBatchMapper  valFinProductTypeBatchMapper;
    /**add by wujie on 20180822 end**/

    //add by liupengfei for 多级法人改造 S
    @Autowired
    private AssetmanageRecvCommonService assetmanageRecvCommonService;
    //add by liupengfei for 多级法人改造 E
    
    @Value("${interf.properties.maxSqlNum}")
    private int maxSqlNum;
    
    /**
     * 日志
     */
    @Autowired
    private ValInterflogBatchMapper valInterflogBatchMapper;
   
    /**
     * 更新数据 {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends BaseEntity> items)
        throws Exception {
        // 获取process返回组合的list值 
        List<PlannedAssetBaseInfoForInterfRecProcessorResult> resultList = (List<PlannedAssetBaseInfoForInterfRecProcessorResult>)items;

        // 循环计划资产类processor结果DTO，读取所有的入库数据结构LIST，并进行入库操作。
        for (PlannedAssetBaseInfoForInterfRecProcessorResult result : resultList) {
            //标的类金融产品表
            List<FinProduct> listValFinProduct = result.getListValFinProduct();
            Map<String,FinProduct> mapValFinProduct = new HashMap<String,FinProduct>();
            List<FinProduct> rstValFinProduct= new ArrayList<FinProduct>();
            for(FinProduct obj :listValFinProduct){
                if(mapValFinProduct.get(obj.getFinprodId()+obj.getFinprodType()) ==null){
                    rstValFinProduct.add(obj);
                }
                mapValFinProduct.put(obj.getFinprodId()+obj.getFinprodType(), obj);
            }
            // 入库
            if (CollectionUtils.isNotEmpty(rstValFinProduct)) {
                // 定义一个固定大小插入存放集合
                List<FinProduct> listInsert = new ArrayList<FinProduct>();
                for (FinProduct e : rstValFinProduct) {
                    // 小于最大值
                    if (listInsert.size() < maxSqlNum) {
                        listInsert.add(e);
                    }
                    else if (listInsert.size() >= maxSqlNum) {
                        valFinProductBatchMapper.deleteBatch(listInsert);
                        valFinProductBatchMapper.insertBatch(listInsert);
                        listInsert.clear();
                        listInsert.add(e);
                    }
                    //add by liupengfei for 多级法人改造 S
                    // 记录二级法人权限
                    if(MultilevelDataAuthorityUtil.isEnabled()){
                        assetmanageRecvCommonService.insertDataAuthInst(e.getFinprodId(),e.getBelongInstitutionCode(), DictCodeList.共通业务类型_计划类资产_利率型_信息.getValue());
                    }
                    //add by liupengfei for 多级法人改造 E
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    valFinProductBatchMapper.deleteBatch(listInsert);
                    valFinProductBatchMapper.insertBatch(listInsert);
                }
            }
            

            // 现金流信息表
            List<FinCashInfo> listValCashInfo = result.getListValCashInfo();
            Map<String,FinCashInfo> mapValCashInfo = new HashMap<String,FinCashInfo>();
            List<FinCashInfo> rstValCashInfo= new ArrayList<FinCashInfo>();
            for(FinCashInfo obj :listValCashInfo){
                if(mapValCashInfo.get(obj.getCashId()) ==null){
                    rstValCashInfo.add(obj);
                }
                mapValCashInfo.put(obj.getCashId(), obj);
            }
            // 入库
            if (CollectionUtils.isNotEmpty(rstValCashInfo)) {
                // 定义一个固定大小插入存放集合
                List<FinCashInfo> listInsert = new ArrayList<FinCashInfo>();
                for (FinCashInfo e : rstValCashInfo) {
                    // 小于最大值
                    if (listInsert.size() < maxSqlNum) {
                        listInsert.add(e);
                    }
                    else if (listInsert.size() >= maxSqlNum) {
                        valCashInfoBatchMapper.deleteBatch(listInsert);
                        valCashInfoBatchMapper.insertBatch(listInsert);
                        listInsert.clear();
                        listInsert.add(e);
                    }
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    valCashInfoBatchMapper.deleteBatch(listInsert);
                    valCashInfoBatchMapper.insertBatch(listInsert);
                }
                
            }

            // 现金流计算规则表
            List<FinCashCalcRule> listValCashCalcRule = result.getListValCashCalcRule();
            Map<String,FinCashCalcRule> mapValCashCalcRule = new HashMap<String,FinCashCalcRule>();
            List<FinCashCalcRule> rstValCashCalcRule= new ArrayList<FinCashCalcRule>();
            for(FinCashCalcRule obj :listValCashCalcRule){
                if(mapValCashCalcRule.get(obj.getCashId()+obj.getEffDate()) ==null){
                    rstValCashCalcRule.add(obj);
                }
                mapValCashCalcRule.put(obj.getCashId()+obj.getEffDate(), obj);
            }





            // 入库
            if (CollectionUtils.isNotEmpty(rstValCashCalcRule)) {
                /**add by wujie on 20210507 start**/
                String TMP_E_NAME = null;
                if(result.getParamMap()!=null && result.getParamMap().get("TMP_E_NAME")!=null){
                    TMP_E_NAME = (String)result.getParamMap().get("TMP_E_NAME");
                }
                //KEY值删除
                if(TempConstant.计划类资产利率型基础信息表.equals(TMP_E_NAME)){
                    // 定义一个固定大小插入存放集合
                    List<FinCashCalcRule> listInsert = new ArrayList<FinCashCalcRule>();
                    for (FinCashCalcRule e : rstValCashCalcRule) {
                        // 小于最大值
                        if (listInsert.size() < maxSqlNum) {
                            listInsert.add(e);
                        }
                        else if (listInsert.size() >= maxSqlNum) {
                            valCashCalcRuleBatchMapper.deleteBatch(listInsert);
                            valCashCalcRuleBatchMapper.insertBatch(listInsert);
                            listInsert.clear();
                            listInsert.add(e);
                        }
                    }
                    // 如果循环结束，小于最大值
                    if (CollectionUtils.isNotEmpty(listInsert)) {
                        valCashCalcRuleBatchMapper.deleteBatch(listInsert);
                        valCashCalcRuleBatchMapper.insertBatch(listInsert);
                    }
                }
                //批量删除
                else{
                    List<FinCashCalcRule> listInsert = new ArrayList<FinCashCalcRule>();
                    for (FinCashCalcRule e : rstValCashCalcRule) {
                        // 小于最大值
                        if (listInsert.size() < maxSqlNum) {
                            listInsert.add(e);
                        }
                        else if (listInsert.size() >= maxSqlNum) {
                            valCashCalcRuleBatchMapper.deleteBatchC(listInsert);
                            listInsert.clear();
                            listInsert.add(e);
                        }
                    }
                    // 如果循环结束，小于最大值
                    if (CollectionUtils.isNotEmpty(listInsert)) {
                        valCashCalcRuleBatchMapper.deleteBatchC(listInsert);
                    }

                    listInsert = new ArrayList<FinCashCalcRule>();
                    for (FinCashCalcRule e : rstValCashCalcRule) {
                        // 小于最大值
                        if (listInsert.size() < maxSqlNum) {
                            listInsert.add(e);
                        }
                        else if (listInsert.size() >= maxSqlNum) {
                            valCashCalcRuleBatchMapper.insertBatch(listInsert);
                            listInsert.clear();
                            listInsert.add(e);
                        }
                    }
                    // 如果循环结束，小于最大值
                    if (CollectionUtils.isNotEmpty(listInsert)) {
                        valCashCalcRuleBatchMapper.insertBatch(listInsert);
                    }
                }
                /**add by wujie on 20210507 end**/
            }




            // 现金流支付规则表
            List<FinCashPayRule> listValCashPayRule = result.getListValCashPayRule();
            Map<String,FinCashPayRule> mapValCashPayRule = new HashMap<String,FinCashPayRule>();
            List<FinCashPayRule> rstValCashPayRule= new ArrayList<FinCashPayRule>();
            for(FinCashPayRule obj :listValCashPayRule){
                if(mapValCashPayRule.get(obj.getCashId()+obj.getEffDate()) ==null){
                    rstValCashPayRule.add(obj);
                }
                mapValCashPayRule.put(obj.getCashId()+obj.getEffDate(), obj);
            }
            // 入库
            if (CollectionUtils.isNotEmpty(rstValCashPayRule)) {
                // 定义一个固定大小插入存放集合
                List<FinCashPayRule> listInsert = new ArrayList<FinCashPayRule>();
                for (FinCashPayRule e : rstValCashPayRule) {
                    // 小于最大值
                    if (listInsert.size() < maxSqlNum) {
                        listInsert.add(e);
                    }
                    else if (listInsert.size() >= maxSqlNum) {
                        valCashPayRuleBatchMapper.deleteBatch(listInsert);
                        valCashPayRuleBatchMapper.insertBatch(listInsert);
                        listInsert.clear();
                        listInsert.add(e);
                    }
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    valCashPayRuleBatchMapper.deleteBatch(listInsert);
                    valCashPayRuleBatchMapper.insertBatch(listInsert);
                }
            }
            
            // 现金流计划表
            List<FinCashPlan> listValCashPlan = result.getListValCashPlan();
            Map<String,FinCashPlan> mapValCashPlan = new HashMap<String,FinCashPlan>();
            List<FinCashPlan> rstValCashPlan= new ArrayList<FinCashPlan>();
            
            /**add by wujie on 20190107 start**/
            List<FinCashPlan> delFinCashPlanList = new ArrayList<FinCashPlan>();
            Map<String,FinCashPlan> delFinCashPlanMap = new HashMap<String,FinCashPlan>();
            if (CollectionUtils.isNotEmpty(listValCashPlan)) {
            /**add by wujie on 20190107 end**/
            	
	            for(FinCashPlan obj :listValCashPlan){
	                if(mapValCashPlan.get(obj.getCashId()+obj.getCashNum()) ==null){
	                    rstValCashPlan.add(obj);
	                }
	                mapValCashPlan.put(obj.getCashId()+obj.getCashNum(), obj);
	                
	                delFinCashPlanMap.put(obj.getCashId(), obj);//add by wujie on 20190107
	            }
	            
	        /**add by wujie on 20190107 start**/
	            
	            for(String key :delFinCashPlanMap.keySet()){
	            	delFinCashPlanList.add(delFinCashPlanMap.get(key));
	            }
	            valCashPlanBatchMapper.deleteFinCashPlanByCashId(delFinCashPlanList);
            }
            /**add by wujie on 20190107 end**/
            
            // 入库
            if (CollectionUtils.isNotEmpty(rstValCashPlan)) {
                // 定义一个固定大小插入存放集合
                List<FinCashPlan> listInsert = new ArrayList<FinCashPlan>();
                for (FinCashPlan e : rstValCashPlan) {
                    // 小于最大值
                    if (listInsert.size() < maxSqlNum) {
                        listInsert.add(e);
                    }
                    else if (listInsert.size() >= maxSqlNum) {
                    	/**modify by wujie on 20190107 
                        valCashPlanBatchMapper.deleteBatch(listInsert);
                        **/
                        valCashPlanBatchMapper.insertBatch(listInsert);
                        listInsert.clear();
                        listInsert.add(e);
                    }
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                	/**modify by wujie on 20190107 
                    valCashPlanBatchMapper.deleteBatch(listInsert);
                    **/
                    valCashPlanBatchMapper.insertBatch(listInsert);
                }
            }
            
            /**add by wujie on 20180822 start**/
            // 金融产品分类
            List<FinProductType> listValFinProductType = result.getListValFinProductType();
            Map<String,FinProductType> mapValFinProductType = new HashMap<String,FinProductType>();
            List<FinProductType>   rstValFinProductType= new ArrayList<FinProductType>();
//            for(FinProductType obj :listValFinProductType){
//                if(mapValFinProductType.get(obj.getFinprodId()+obj.getFinprodType()) ==null){
//                    rstValFinProductType.add(obj);
//                }
//                mapValFinProductType.put(obj.getFinprodId()+obj.getFinprodType(), obj);
//            }
            for(FinProductType obj :listValFinProductType){
                if(mapValFinProductType.get(obj.getFinprodId()) ==null){
                    rstValFinProductType.add(obj);
                }
                mapValFinProductType.put(obj.getFinprodId(), obj);
            }
            // 入库
            if (CollectionUtils.isNotEmpty(rstValFinProductType)) {
                // 定义一个固定大小插入存放集合
                List<FinProductType> listInsert = new ArrayList<FinProductType>();
                for (FinProductType e : rstValFinProductType) {
                    // 小于最大值
                    if (listInsert.size() < maxSqlNum) {
                        listInsert.add(e);
                    }
                    else if (listInsert.size() >= maxSqlNum) {
                        valFinProductTypeBatchMapper.deleteBatch(listInsert);
                        valFinProductTypeBatchMapper.insertBatch(listInsert);
                        listInsert.clear();
                        listInsert.add(e);
                    }
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    valFinProductTypeBatchMapper.deleteBatch(listInsert);
                    valFinProductTypeBatchMapper.insertBatch(listInsert);
                }
            }
            /**add by wujie on 20180822 end**/

            // 日志
            List<ValInterflog> valInterflog = result.getValInterflog();
            // 入库
            if (CollectionUtils.isNotEmpty(valInterflog)) {
                // 定义一个固定大小插入存放集合
                List<ValInterflog> listInsert = new ArrayList<>();
                for (ValInterflog e : valInterflog) {
                    // 小于最大值
                    if (listInsert.size() < maxSqlNum) {
                        listInsert.add(e);
                    }
                    else if (listInsert.size() >= maxSqlNum) {
                        valInterflogBatchMapper.insertBatch(listInsert);
                        listInsert.clear();
                        listInsert.add(e);
                    }
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    valInterflogBatchMapper.insertBatch(listInsert);
                }
            }

        }
        
    }

}
