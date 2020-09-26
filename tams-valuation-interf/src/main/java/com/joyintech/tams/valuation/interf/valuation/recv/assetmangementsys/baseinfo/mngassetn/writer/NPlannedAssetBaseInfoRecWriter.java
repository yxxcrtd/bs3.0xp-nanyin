/*
 * 文件名：BookDetailSubjectAllWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huangjian
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngassetn.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.interf.valuation.recv.common.service.AssetmanageRecvCommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValFinProductBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValFinProductTypeBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValInterflogBatchMapper;
import com.joyintech.tams.valuation.interf.core.InterfBaseWriter;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.mngassetn.dto.NPlannedAssetBaseInfoRecResult;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.ValInterflog;


/**
 * 计划类资产（净值型）基础信息接口 Writer
 * 
 * @author chufengjie
 * @version 1.0 2018年8月3日
 * @see NPlannedAssetBaseInfoRecWriter
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPlannedAssetBaseInfoRecWriter extends InterfBaseWriter<BaseEntity> {


    /**
     * 标的类金融产品
     */
    @Autowired
    private ValFinProductBatchMapper valFinProductBatchMapper;

    /**
     * 金融产品分类
     */
    @Autowired
    private  ValFinProductTypeBatchMapper  valFinProductTypeBatchMapper;

    /**
     * 日志
     */
    @Autowired
    private ValInterflogBatchMapper valInterflogBatchMapper;
    //add by liupengfei for 多级法人改造 S
    @Autowired
    private AssetmanageRecvCommonService assetmanageRecvCommonService;
    //add by liupengfei for 多级法人改造 E
    
    @Value("${interf.properties.maxSqlNum}")
    private int maxSqlNum;
    
    /**
     * 更新数据 {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends BaseEntity> items)
        throws Exception {
        // 获取process返回组合的list值
        List<NPlannedAssetBaseInfoRecResult> resultList = (List<NPlannedAssetBaseInfoRecResult>)items;

        // 循环基金类processor结果DTO，读取所有的入库数据结构LIST，并进行入库操作。
        for (NPlannedAssetBaseInfoRecResult result : resultList) {
           
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
//            // 入库
//            if (CollectionUtils.isNotEmpty(rstValFinProduct)) {
//                if(rstValFinProduct.size()<1000){
//                    valFinProductBatchMapper.deleteBatch(rstValFinProduct);
//                    valFinProductBatchMapper.insertBatch(rstValFinProduct);
//                }else{
//                    int times = (int)Math.ceil( rstValFinProduct.size()/1000.0 );
//                    for(int i=0; i<times; i++ ){
//                        valFinProductBatchMapper.deleteBatch(rstValFinProduct.subList(i*1000, Math.min((i+1)*1000, rstValFinProduct.size()-1)));
//                        valFinProductBatchMapper.insertBatch(rstValFinProduct.subList(i*1000, Math.min((i+1)*1000, rstValFinProduct.size()-1)));
//                    }
//                }
//            } 
            
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
                        assetmanageRecvCommonService.insertDataAuthInst(e.getFinprodId(),e.getBelongInstitutionCode(), DictCodeList.共通业务类型_计划类资产_净值型_信息.getValue());
                    }
                    //add by liupengfei for 多级法人改造 E
                }
                // 如果循环结束，小于最大值
                if (CollectionUtils.isNotEmpty(listInsert)) {
                    valFinProductBatchMapper.deleteBatch(listInsert);
                    valFinProductBatchMapper.insertBatch(listInsert);
                }
            }
            
    
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
//            // 入库
//            if (CollectionUtils.isNotEmpty(rstValFinProductType)) {
//                if(rstValFinProductType.size()<1000){
//                    valFinProductTypeBatchMapper.deleteBatch(rstValFinProductType);
//                    valFinProductTypeBatchMapper.insertBatch(rstValFinProductType);
//                }else{
//                    int times = (int)Math.ceil( rstValFinProductType.size()/1000.0 );
//                    for(int i=0; i<times; i++ ){
//                        valFinProductTypeBatchMapper.deleteBatch(rstValFinProductType.subList(i*1000, Math.min((i+1)*1000, rstValFinProductType.size()-1)));
//                        valFinProductTypeBatchMapper.insertBatch(rstValFinProductType.subList(i*1000, Math.min((i+1)*1000, rstValFinProductType.size()-1)));
//                    }
//                }
//            }
            
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
            

            // 日志
            List<ValInterflog> valInterflog = result.getValInterflog();
//            // 入库
//            if (CollectionUtils.isNotEmpty(valInterflog)) {
//                if(valInterflog.size()<1000){
//                    valInterflogBatchMapper.insertBatch(valInterflog);
//                }else{
//                    int times = (int)Math.ceil( valInterflog.size()/1000.0 );
//                    for(int i=0; i<times; i++ ){
//                            valInterflogBatchMapper.insertBatch(valInterflog.subList(i*1000, Math.min((i+1)*1000, valInterflog.size()-1)));
//                        
//                    }
//                }
//            }
            
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
