/*
 * 文件名：DisRepTreadInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月7日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */
 
package com.joyintech.tams.svreport.interf.dis.tradeinfo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.tams.svreport.interf.dis.common.DisInterfDecimalUtil;
import com.joyintech.tams.svreport.interf.dis.tradeinfo.dao.DisRepTreadInfoMapper;
import com.joyintech.tams.svreport.interf.dis.tradeinfo.dto.DisRepTreadInfo;


/**
 * 投资交易信息接口数据
 *      投资交易信息接口数据，存入REP_TREAD_INFO
 *      
 * @author ZhangYangFei
 * @version 1.0 2019年5月9日
 * @see DisRepTreadInfo
 * @since 1.0
 */
@Service
public class DisRepTreadInfoService extends FileImpService {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    public int reportInsertCount;
    
    /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public int reportInsertLongCount;
    
    @Autowired
    private DisRepTreadInfoMapper disRepTreadInfoMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
        if (StringUtil.isEmptyList(listData)) {
            logger.info("saveData方法参数listData为空。");
            return "success";
        }
        // 解析文件数据到dto
        DisRepTreadInfo dto = null;
        List<DisRepTreadInfo> dtoList = new ArrayList<DisRepTreadInfo>();
        for(Map<String, String> data : listData){
            //只取:交易种类TRADE_TYPE = 01资产交易
            if(StringUtil.equals("01", data.get("TRADE_TYPE"))){
                //将map转换为dto
            	try{
            		 dto = transMap2Dto(data);
                     dtoList.add(dto);
           	}catch(Exception  e){
           		logger.error("解析数据错误：",e.getMessage(),e);
           		return  "fail";
           	}
            }
        }
        int success=0;
        //存表
        if(dtoList!=null&&dtoList.size()!=0){
        	success = this.mergeRepTradeInfoByPart(dtoList);
        }
        logger.info("saveData方法成功存入或更新数据到REP_TREAD_INFO：" + success + "条");
        return "success";
    }

    /**
     * 将map转换为dto
     * 
     * @param data
     * @return
     * @see
     * @since 1.0
     */
    private DisRepTreadInfo transMap2Dto(Map<String, String> data) {
        DisRepTreadInfo dto = new DisRepTreadInfo();
        dto.setId(data.get("TRADE_CODE"));//id
        dto.setAssetId(data.get("ASSET_CODE"));//资产代码
        dto.setAssetName(data.get(""));//资产名称
        dto.setTradeCounty(data.get("TRADE_COUNTER"));//交易对手// 交易对手id，若交易对手表中没有，该字段为交易对手名称
        dto.setTradeDate(DateUtil.parse(data.get("DEALDATE")));//交易日期
        dto.setTradeAmt(DisInterfDecimalUtil.str2BigDecimal(data.get("PRIN_AMT")));//交易金额
        dto.setTradeType(data.get("CUR_ASSET_TYPE"));//交易类型
        dto.setBuyPart(data.get("BUY_PART"));//买入方
        dto.setSalePart(data.get("SALE_PART"));//卖出方
        dto.setPs(data.get("PS"));//交易方向
        dto.setIsRelationTrade(data.get("IS_REL_TRADE"));//是否关联交易
        dto.setExpireRate(DisInterfDecimalUtil.str2BigDecimal(data.get("END_RATE")));//到期收益率
        dto.setContractNo(data.get("CONTRACT_CODE"));//合同号
        dto.setProdId(data.get("PROD_ID"));//产品代码
        dto.setPrinAmt(DisInterfDecimalUtil.str2BigDecimal(data.get("PRIN_AMT")));//交易本金
        dto.setStatus(data.get("STATUS"));//状态
        dto.setCreateUser("interf");
        dto.setCreateDept("");
        dto.setUpdateUser("interf");
        try {
            if(StringUtil.isNotEmpty(data.get("CREATE_TIME"))){
                dto.setCreateTime(DateUtil.parseDate(data.get("CREATE_TIME"),new String[] {DateUtil.DEFAULT_DATETIME_FORMAT_ODS}));
            }else{
                dto.setCreateTime(new Date());
            }
            if(StringUtil.isNotEmpty(data.get("UPDATE_TIME"))){
                dto.setUpdateTime(DateUtil.parseDate(data.get("UPDATE_TIME"),new String[] {DateUtil.DEFAULT_DATETIME_FORMAT_ODS}));
            }else{
                dto.setUpdateTime(new Date());
            }
        } catch (ParseException e) {
            logger.error("transMap2Dto方法转换CreateTime或UpdateTime异常");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        //表中被废弃的字段：资产一级分类、资产二级分类、资产三级分类、是否外部交易、剩余融资期限、投资比例
        return dto;
    }

    /**
     * 存表
     *      全删全插（删除条件：INVEST_ADJUST_DATE）
     * 
     * @param dtoList
     * @return
     * @see
     * @since 1.0
     */
    private int mergeRepTradeInfoByPart(List<DisRepTreadInfo> dtoList) {
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertLongCount;
        //插入list大小
        int dataCount = dtoList.size();
        int mergeCount= 0;
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            mergeCount = disRepTreadInfoMapper.mergeRepTradeInfo(dtoList);
            return mergeCount;
        }
        
        List<DisRepTreadInfo> newDataList = new ArrayList<DisRepTreadInfo>();
        newDataList.addAll(dtoList);
        
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            mergeCount += disRepTreadInfoMapper.mergeRepTradeInfo(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            mergeCount += disRepTreadInfoMapper.mergeRepTradeInfo(newDataList);
        }
        return mergeCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String importDB(List<Map<String, String>> dataList, InterfFile interfFile) {
        String rs = "success";
        // 对数据list进行处理
        if (!CollectionUtils.isEmpty(dataList)) {
            checkSaveData(dataList, interfFile);
            // 数据入库
            ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(
                InterfDefine.投资交易信息接口数据.getCode());
            if (interfConfig == null) {
                logger.info("CHB_INTERF_CONFIG表中无配置项！");
                return "fail";
            }
            String saveFlag = interfConfig.getContentType();
            rs = saveData(dataList, saveFlag, interfFile.getDataSource());
        }
        return rs;
    }
    
    /**
     * 数据校验
     * 
     * @param dataList 入库的数据
     * @param interfFile 文件名
     * @see
     * @since 1.0
     */
    private void checkSaveData(List<Map<String, String>> dataList, InterfFile interfFile) {
        //接口类型
        String inerfType = InterfDefine.投资交易信息接口数据.getCode();
        //检查数据类型
        String dataType = InterfDefine.数据检查类型_投资交易信息接口.getCode();
        //重复性检查的keys
        String[] checkEuplicatedKeys = {"TRADE_CODE"};
        String filename = interfFile == null ? null : interfFile.getFilename();
        List<Map<String, String>>  listA = super.checkData(dataList, filename, inerfType, dataType,null, checkEuplicatedKeys);
//        dataList.addAll(listA);// listA为错误数据，在checkData时被remove，需要重新放回dataList
    }
}
