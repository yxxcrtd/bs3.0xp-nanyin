/*
 * 文件名：ValFinProductAcctInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValBooksetInfoDto;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSubjectInfo;

@Service
public class ValFinProductAcctInterfService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    /**
     * 接口共通Service
     */
    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 读取模板处理
     * 
     * @param templateSourceFlgDTO
     * @param acctEntity
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public OtherBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                         TemplateOrderInfoReaderResult acctEntity) {
        //定义返回变量
    	OtherBaseInfoCommonResult result = new OtherBaseInfoCommonResult();
        List<ValSubjectInfo> listValSubjectInfo = new ArrayList<ValSubjectInfo>(); 
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSubjectInfo = returnResult.getT(ValSubjectInfo.class);
        
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 判断返回结果不为空
        Map<String, Object> map = handleListValSubjectInfo(listValSubjectInfo);
        
        if (map != null) {
        	List<StlFinProductAcct> listValFinProductAcct = (List<StlFinProductAcct>)map.get("ValFinProductAcct");
        	result.setValFinProductAcctList(listValFinProductAcct);
        	
        	List<BokBooksetCapacctInfo> valBooksetCapacctInfoList = (List<BokBooksetCapacctInfo>)map.get("ValSubjectInfo");
        	result.setValBooksetCapacctInfoList(valBooksetCapacctInfoList);
        }
        return result;
    }
    
    /**
     * 
     * @param handleListValSubjectInfo 标的账户信息表
     * @return 金融产品账户表
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValSubjectInfo(List<ValSubjectInfo> listValSubjectInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<StlFinProductAcct> valFinProductAcctList = new ArrayList<StlFinProductAcct>();
        List<BokBooksetCapacctInfo> valBooksetCapacctInfoList = new ArrayList<BokBooksetCapacctInfo>();
        // 获取基础信息
        for (ValSubjectInfo dto : listValSubjectInfo) {
        	if(DictCodeListExt1.金融产品类型_理财产品.getValue().equals(dto.getSubjectType())){//金融产品类型= F16 理财产品
        		// 资金账户
                StlFinProductAcct valFinProductAcct = getValFinProductAcct(dto);
                valFinProductAcctList.add(valFinProductAcct);
                
                //调用共通设计->【详细设计-接口共通方法设计.xlsx】->账套信息(根据产品代码) _分层产品SHEET
                List<ValBooksetInfoDto> valBooksetInfoDtoList = interfCommonService.getBokBooksetByProdId_Layering(dto.getSubjectId());
                
                StlFinProductAcct obj = new StlFinProductAcct();
                obj.setFinprodId(dto.getSubjectId());
                obj.setFinprodType(dto.getSubjectType());
                obj.setAcctNo(dto.getActNo());
                obj.setCcy(dto.getCcy());
                obj.setAcctType(dto.getActType());
                List<BokBooksetCapacctInfo> valBooksetCapacctInfos = interfCommonService.booksetCapacctInfoInit(obj,valBooksetInfoDtoList);
                valBooksetCapacctInfoList.addAll(valBooksetCapacctInfos);
                
                //托管户-同时初始化清算款账户
                if(DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(dto.getActType())){
                	StlFinProductAcct valFinProductAcctQSK = getValFinProductAcct(dto);
                	valFinProductAcctQSK.setAcctNo(InterfCommonConstant.清算款账户号);
                	valFinProductAcctQSK.setAcctType(DictCodeListExt1.资金账户类型_待清算款.getValue());
                	valFinProductAcctList.add(valFinProductAcctQSK);
                	
                    obj.setAcctNo(InterfCommonConstant.清算款账户号);
                    obj.setAcctType(DictCodeListExt1.资金账户类型_待清算款.getValue());
                    List<BokBooksetCapacctInfo> vBokBooksetCapacctInfoQSKList = interfCommonService.booksetCapacctInfoInit(obj,valBooksetInfoDtoList);
                	valBooksetCapacctInfoList.addAll(vBokBooksetCapacctInfoQSKList);
                }
               
        	}
        }
        resultMap.put("ValSubjectInfo", valBooksetCapacctInfoList);
        resultMap.put("ValFinProductAcct", valFinProductAcctList);
        return resultMap;
    }
    
    /**
     * 金融产品账户表 接收
     * 
     * @param dto 标的账户信息
     * @return 金融产品账户表
     * @see
     * @since 1.0
     */
    private StlFinProductAcct getValFinProductAcct(ValSubjectInfo dto) {

        StlFinProductAcct valFinProductAcct = new StlFinProductAcct();
        // 金融产品代码 = A.标的代码
        valFinProductAcct.setFinprodId(dto.getSubjectId());
        // TODO 金融产品类型 = 待定
        valFinProductAcct.setFinprodType(dto.getSubjectType());
        // 账号 = A.账号
        valFinProductAcct.setAcctNo(dto.getActNo());
        // 币种 = A.币种
        valFinProductAcct.setCcy(dto.getCcy());
        // 账户类型 = A.账户类型
        valFinProductAcct.setAcctType(dto.getActType());
        return valFinProductAcct;
    }
}
