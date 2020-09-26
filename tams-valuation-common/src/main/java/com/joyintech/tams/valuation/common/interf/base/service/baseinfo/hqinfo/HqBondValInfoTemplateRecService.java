/*
 * 文件名：NPrdBaseinfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangwei1
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.HqBondValuationInfo;

/**
 * 行情信息-债券估值行情
 * 
 * @author wangwei1
 * @version 1.0 2018年6月28日
 * @see HqBondValInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqBondValInfoTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                  TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();

        List<HqBondValuationInfo> bondValInfo = new ArrayList<HqBondValuationInfo>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 此部分处理待以后从画面入口的时候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
        // 1、文件读取解析 进行数据有效性的check,并生出进入日志表的数据结构
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        bondValInfo = returnResult.getT(HqBondValuationInfo.class);

        List<HqBondValuationInfo> infoList = new ArrayList<HqBondValuationInfo>();
        Map<String, HqBondValuationInfo> map = new HashMap<String, HqBondValuationInfo>();
        // 如果是中债估值需要选择推荐的那条估值
        if (acctEntity.getTmpEName().equals(TempConstant.中债估值行情)) {
            for (HqBondValuationInfo info : bondValInfo) {
                if ("推荐".equals(info.getRelibity())) {
                    infoList.add(info);
                }
            }
        } else if (acctEntity.getTmpEName().equals(TempConstant.中债登估值_平安)) {//add by chendijun
            for (HqBondValuationInfo info : bondValInfo) {
                DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                String key = info.getSecId() + "," + info.getValueource() + ","
                             + df.format(info.getValueDate());
                if (map.get(key) == null) {
                    map.put(key, info);
                }
                else {
                    if ("推荐".equals(info.getRelibity())) {
                        map.put(key, info);
                    }
                }
            }
            for (String key : map.keySet()) {// 遍历map的键
                infoList.add(map.get(key));
            }
        } 
        /** add by dongjun on 20180921 start **/
        else if(acctEntity.getTmpEName().equals(TempConstant.中证估值)){
            for (HqBondValuationInfo info : bondValInfo) {
                if(map.get(info.getSecId()) == null){
                    map.put(info.getSecId(), info);                   
                }else{
                    if ("推荐".equals(info.getRelibity())) {
                        map.put(info.getSecId(), info);
                    }
                }
            }
            for(String key : map.keySet()){
                infoList.add(map.get(key));
            }
        } else if(acctEntity.getTmpEName().equals(TempConstant.中国债券上海清算所债券估值)){
            for (HqBondValuationInfo info : bondValInfo) {
                if(map.get(info.getSecId()) == null){
                    map.put(info.getSecId(), info);                   
                }else{
                    if ("推荐".equals(info.getRelibity())) {
                        map.put(info.getSecId(), info);
                    }
                }
            }
            for(String key : map.keySet()){
                infoList.add(map.get(key));
            }
        }
        /** add by dongjun on 20180921 end **/
        else {
            
            infoList = bondValInfo;
        }

        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        List<MstBondValuationInfo> listBondValInfo = (List<MstBondValuationInfo>)handleListToVaLInfo(
            infoList, acctEntity.getTmpEName()).get("valBondValInfo");

        result.setMstBondValuationInfoList(listBondValInfo);
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param hqShanghaiStockExchangeValuation
     * @return
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListToVaLInfo(List<HqBondValuationInfo> infoList,
                                                    String tmpName) {
        Map<String, Object> resultMap = new HashMap<>();
        List<MstBondValuationInfo> valBondMarketInfoList = new ArrayList<MstBondValuationInfo>();
        for (HqBondValuationInfo dto : infoList) {
            // 债券市场行情 处理
            MstBondValuationInfo bondValInfo = getValBondValInfo(dto, tmpName);
            valBondMarketInfoList.add(bondValInfo);
        }
        resultMap.put("valBondValInfo", valBondMarketInfoList);
        return resultMap;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private MstBondValuationInfo getValBondValInfo(HqBondValuationInfo dto, String tmpName) {
        MstBondValuationInfo valInfo = new MstBondValuationInfo();
        // 债券代码
        // 估值来源
        if (tmpName.equals(TempConstant.中债估值行情)) {
            if (DictCodeInterfList.中债估值交易场所_银行间.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".IB");
            }
            else if (DictCodeInterfList.中债估值交易场所_上交所.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".SH");
            }
            else if (DictCodeInterfList.中债估值交易场所_深交所.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".SZ");
            }
            else if (DictCodeInterfList.中债估值交易场所_柜台.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".BC");
            }
            else {
                valInfo.setSecId(dto.getSecId() + ".OF");
            }
            valInfo.setValueSource(DictCodeList.估值市价_中债登.getValue());
        }
        else if (tmpName.equals(TempConstant.中证估值行情)) {
            if (DictCodeList.交易流通场所_银行间债券市场.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".IB");
            }
            else if (DictCodeList.交易流通场所_上海证券交易所.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".SH");
            }
            else if (DictCodeList.交易流通场所_深圳证券交易所.getValue().equals(dto.getValueource())) {
                valInfo.setSecId(dto.getSecId() + ".SZ");
            }
            valInfo.setValueSource(DictCodeList.估值市价_中证登.getValue());
        }
        /** 平安银行-特殊处理 add by wujie on 20180809 start **/
        else if (tmpName.equals(TempConstant.中债登估值_平安)) {
            valInfo.setValueSource(DictCodeList.估值来源_中债登_CBC.getValue());
            valInfo.setSecId(dto.getSecId());
        }
        /** add by wujie on 20180908 end **/
        /** add by dongjun on 20180921 start **/
        else if (tmpName.equals(TempConstant.中证估值)){
            valInfo.setValueSource(DictCodeList.估值来源_中证登_CSD.getValue());
            valInfo.setSecId(dto.getSecId());
        }
        else if (tmpName.equals(TempConstant.中国债券上海清算所债券估值)){
            valInfo.setValueSource(DictCodeList.估值来源_上清所_SCH.getValue());
            valInfo.setSecId(dto.getSecId());
        }
        /** add by dongjun on 20180921 end **/
        // 估值日
        valInfo.setValueDate(dto.getValueDate());
        // 剩余期限
        valInfo.setLastPeriod(dto.getLastPeriod());
        // 市价全价
        valInfo.setPrice(dto.getPrice());
        // 市价净价
        valInfo.setNetprice(dto.getNetprice());
        // 市价久期
        valInfo.setmDuration(dto.getmDuration());
        // 市价凸性
        valInfo.setmConvexity(dto.getmConvexity());
        // 基点价值
        valInfo.setBpvalue(dto.getBpvalue());
        // 利差久期
        valInfo.setSduration(dto.getSduration());
        // 利差凸性
        valInfo.setScnvxty(dto.getScnvxty());
        // 利率久期
        valInfo.setInterestDuration(dto.getInterestDuration());
        // 利率凸性
        valInfo.setInterestCnvxty(dto.getInterestCnvxty());
        // 市价收益率
        valInfo.setBpyield(dto.getBpyield());
        return valInfo;
    }

}
