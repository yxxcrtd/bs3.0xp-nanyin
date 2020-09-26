/*
 * 文件名：BeforeBatchCheck.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年4月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.datacheck.beforesub.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckRule;
import com.joyintech.tams.common.svreport.datacheck.service.DataCheckService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.dao.ChbBeforeSubErrorCheckMapper;

/**
 * 中债一期、二期报送前检查，部分报表的强制性检查
 * 
 * @author chenxc
 * @version 1.0 2019年4月16日
 * @see BeforeSubErrorCheck
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BeforeSubErrorCheck {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ChbBeforeSubErrorCheckMapper chbBeforeSubErrorCheckMapper;
    
    @Autowired
    private DataCheckService dataCheckService;
   
    
    /**
     * 
     * 存续期登记检查
     * @param checkdate
     * @return
     * @see
     * @since 1.0
     */
    public List<CheckReport001Dto> checkProdDurationReg(Date checkdate,List<String> listProd,Map<String, List<ChbCheckRuleout>> mapRuleout) {
        logger.info("产品存续期错误检查开始.");
       List<CheckReport001Dto> resultList = new ArrayList<CheckReport001Dto>();
       
        // 读取规则
        List<CheckRule> listRules = dataCheckService.queryCheckRules("CHB_PRD_DEAL_SUB");
        
        //读取数据，查询登记表数据
        List<Map<String,Object>> listData = chbBeforeSubErrorCheckMapper.selectProdDealSub(checkdate,listProd);
        List<Map<String,String>> listDataStr=new ArrayList<Map<String,String>>();
        for(Map<String,Object> singleMap:listData){
        	Map <String,String> smap=new HashMap<String,String>();
        	for(String singlekey:singleMap.keySet()){
        		smap.put(singlekey, singleMap.get(singlekey)==null?null:singleMap.get(singlekey).toString());
        	}
        	listDataStr.add(smap);
        }
        // 数据校验
        List<Map<String, String>> listError = DataCheckService.checkDataList(listDataStr, listRules);
        
        for ( Map<String,String> m:listError ) {
            //增加例外情况过滤
            if(mapRuleout.containsKey(m.get("PROD_ID"))){
                continue;
            }
            //不合格的数据加入到不合格列表
            CheckReport001Dto chbReportCheck = new CheckReport001Dto();
            chbReportCheck.setCheckSeqno(IDGenerator.generate(32));
            chbReportCheck.setRefseqno(m.get("REGIST_SEQNO"));
            chbReportCheck.setRepCode(DictCodeListExt2.报表名称_产品存续期登记.getValue());
            chbReportCheck.setRepName(DictCodeListExt2.报表名称_产品存续期登记.getCodeName());
            chbReportCheck.setValidKey("产品代码:"+m.get("PROD_ID"));
            chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(
                DictCode.检查结果, DictCodeListExt2.检查结果_检查不通过.getCodeName()));
            chbReportCheck.setDataTime(checkdate);
            String errorInfo=m.get("ERRMSG");
            if(!errorInfo.equals("")){
                chbReportCheck.setErrorInfo(errorInfo);
                resultList.add(chbReportCheck);
            }            
            
            //将报送表中数据设置成待维护
            //chbBeforeSubErrorCheckMapper.updateProdDealSub(m.get("REGIST_SEQNO"));
        }
        logger.info("产品存续期错误检查结束.");
        return resultList;
    }
    
    /**
     * 
     * 产品终止检查
     * @param checkdate
     * @return
     * @see
     * @since 1.0
     */
    public List<CheckReport001Dto> checkProdEndReg(Date checkdate,List<String> listProd,Map<String, List<ChbCheckRuleout>> mapRuleout) {
        logger.info("产品终止登记错误检查开始");
       List<CheckReport001Dto> resultList = new ArrayList<CheckReport001Dto>();
       
        // 读取规则
        List<CheckRule> listRules = dataCheckService.queryCheckRules("CHB_PRD_END_SUB");
        
        //读取数据，查询登记表数据
        List<Map<String,Object>> listData = chbBeforeSubErrorCheckMapper.selectProdEndSub(checkdate,listProd);
        List<Map<String,String>> listDataStr=new ArrayList<Map<String,String>>();
        for(Map<String,Object> singleMap:listData){
        	Map <String,String> smap=new HashMap<String,String>();
        	for(String singlekey:singleMap.keySet()){
        		smap.put(singlekey, singleMap.get(singlekey)==null?null:singleMap.get(singlekey).toString());
        	}
        	listDataStr.add(smap);
        }
        // 数据校验
        List<Map<String, String>> listError = DataCheckService.checkDataList(listDataStr, listRules);
        
        for ( Map<String,String> m:listError ) {
            //增加例外情况过滤
            if(mapRuleout.containsKey(m.get("PROD_ID"))){
                continue;
            }
            //不合格的数据加入到不合格列表
            CheckReport001Dto chbReportCheck = new CheckReport001Dto();
            chbReportCheck.setCheckSeqno(IDGenerator.generate(32));
            chbReportCheck.setRefseqno(m.get("REGIST_SEQNO"));
            chbReportCheck.setRepCode(DictCodeListExt2.报表名称_产品终止登记.getValue());
            chbReportCheck.setRepName(DictCodeListExt2.报表名称_产品终止登记.getCodeName());
            chbReportCheck.setValidKey("产品代码:"+m.get("PROD_ID"));
            chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(
                DictCode.检查结果, DictCodeListExt2.检查结果_检查不通过.getCodeName()));
            chbReportCheck.setDataTime(checkdate);
            String errorInfo=m.get("ERRMSG");
            if(!errorInfo.equals("")){
                chbReportCheck.setErrorInfo(errorInfo);
                resultList.add(chbReportCheck);
            }            
            
            //将报送表中数据设置成待维护
            chbBeforeSubErrorCheckMapper.updateProdEndSub(m.get("REGIST_SEQNO"));
        }
        logger.info("产品终止登记错误检查结束");
        return resultList;
    }
    
    /**
     * 
     * 发行登记检查
     * @param checkdate
     * @return
     * @see
     * @since 1.0
     */
    public List<CheckReport001Dto> checkProdIssueReg(Date checkdate,List<String> listProd,Map<String, List<ChbCheckRuleout>> mapRuleout) {
        logger.info("产品发行登记错误检查开始.");
       List<CheckReport001Dto> resultList = new ArrayList<CheckReport001Dto>();
       
        // 读取规则
        List<CheckRule> listRules = dataCheckService.queryCheckRules("CHB_PRD_ISSUE_SUB");
        
        //读取数据，查询登记表数据
        List<Map<String,Object>> listData = chbBeforeSubErrorCheckMapper.selectProdIssueSub(checkdate,listProd);
        List<Map<String,String>> listDataStr=new ArrayList<Map<String,String>>();
        for(Map<String,Object> singleMap:listData){
        	Map <String,String> smap=new HashMap<String,String>();
        	for(String singlekey:singleMap.keySet()){
        		smap.put(singlekey, singleMap.get(singlekey)==null?null:singleMap.get(singlekey).toString());
        	}
        	listDataStr.add(smap);
        }
        // 数据校验
        List<Map<String, String>> listError = DataCheckService.checkDataList(listDataStr, listRules);
        
        for ( Map<String,String> m:listError ) {
            //增加例外情况过滤
            if(mapRuleout.containsKey(m.get("PROD_ID"))){
                continue;
            }
            //不合格的数据加入到不合格列表
            CheckReport001Dto chbReportCheck = new CheckReport001Dto();
            chbReportCheck.setCheckSeqno(IDGenerator.generate(32));
            chbReportCheck.setRefseqno(m.get("REGIST_SEQNO"));
            chbReportCheck.setRepCode(DictCodeListExt2.报表名称_产品发行登记.getValue());
            chbReportCheck.setRepName(DictCodeListExt2.报表名称_产品发行登记.getCodeName());
            chbReportCheck.setValidKey("产品代码:"+m.get("PROD_ID"));
            chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(
                DictCode.检查结果, DictCodeListExt2.检查结果_检查不通过.getCodeName()));
            chbReportCheck.setDataTime(checkdate);
            String errorInfo=m.get("ERRMSG");
            if(!errorInfo.equals("")){
                chbReportCheck.setErrorInfo(errorInfo);
                resultList.add(chbReportCheck);
            }            
            
            //将报送表中数据设置成待维护
            chbBeforeSubErrorCheckMapper.updateProdIssueSub(m.get("REGIST_SEQNO"));
        }
        logger.info("产品发行登记错误检查结束.");
        return resultList;
    }
    
    /**
     * 
     * 投资交易登记检查
     * @param checkdate
     * @return
     * @see
     * @since 1.0
     */
    public List<CheckReport001Dto> checkInvestTradeReg(Date checkdate,List<String> listProd,Map<String, List<ChbCheckRuleout>> mapRuleout) {
        logger.info("投资交易登记错误检查开始");
       List<CheckReport001Dto> resultList = new ArrayList<CheckReport001Dto>();
       
        // 读取规则
        List<CheckRule> listRules = dataCheckService.queryCheckRules("CHB_INVEST_TRADE_SUB");
        
        //读取数据，查询登记表数据
        List<Map<String,Object>> listData = chbBeforeSubErrorCheckMapper.selectInvestTradeSub(checkdate,listProd);
        List<Map<String,String>> listDataStr=new ArrayList<Map<String,String>>();
        for(Map<String,Object> singleMap:listData){
        	Map <String,String> smap=new HashMap<String,String>();
        	for(String singlekey:singleMap.keySet()){
        		smap.put(singlekey, singleMap.get(singlekey)==null?null:singleMap.get(singlekey).toString());
        	}
        	listDataStr.add(smap);
        }
        // 数据校验
        List<Map<String, String>> listError = DataCheckService.checkDataList(listDataStr, listRules);
        
        for ( Map<String,String> m:listError ) {
            //增加例外情况过滤
            if(mapRuleout.containsKey(m.get("PROD_ID"))){
                continue;
            }
            //不合格的数据加入到不合格列表
            CheckReport001Dto chbReportCheck = new CheckReport001Dto();
            chbReportCheck.setCheckSeqno(IDGenerator.generate(32));
            chbReportCheck.setRefseqno(m.get("REGIST_SEQNO"));
            chbReportCheck.setUniqueKey(m.get("PROD_ID")+GlobalConstant.COMMA+m.get("ASSET_CODE"));
            chbReportCheck.setRepCode(DictCodeListExt2.报表名称_资产交易信息登记.getValue());
            chbReportCheck.setRepName(DictCodeListExt2.报表名称_资产交易信息登记.getCodeName());
            chbReportCheck.setValidKey("产品代码:"+m.get("PROD_ID")+";资产代码:"+m.get("ASSET_CODE"));
            chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(
                DictCode.检查结果, DictCodeListExt2.检查结果_检查不通过.getCodeName()));
            chbReportCheck.setDataTime(checkdate);
            String errorInfo=m.get("ERRMSG");
            if(!errorInfo.equals("")){
                chbReportCheck.setErrorInfo(errorInfo);
                resultList.add(chbReportCheck);
            }            
            
            //将报送表中数据设置成待维护
            chbBeforeSubErrorCheckMapper.updateInvestTradeSub(m.get("REGIST_SEQNO"));
        }
        logger.info("投资交易登记错误检查结束.");
        return resultList;
    }
    
}
