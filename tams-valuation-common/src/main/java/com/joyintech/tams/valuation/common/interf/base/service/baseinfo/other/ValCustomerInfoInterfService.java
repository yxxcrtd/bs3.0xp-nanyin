/*
 * 文件名：ValCustomerInfoInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.MstCustomerInfo;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAgencyInfo;    

@Service
public class ValCustomerInfoInterfService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

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
        /**modify by dongjun on 20181012 start 返回结果集修改**/
        //定义返回变量
        OtherBaseInfoCommonResult obcResult = new OtherBaseInfoCommonResult();
        List<ValAgencyInfo> listValAgencyInfo = new ArrayList<ValAgencyInfo>();
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);
        
        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValAgencyInfo = returnResult.getT(ValAgencyInfo.class);
        
        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 判断返回结果不为空
        if (handleListValAgencyInfo(listValAgencyInfo) != null) {
            obcResult.setValCustomerInfoList((List<MstCustomerInfo>)handleListValAgencyInfo(
                listValAgencyInfo).get("ValCustomerInfo"));
        }
        if(returnResult.getValInterflog() != null){
            obcResult.setValInterflog(returnResult.getValInterflog());
        }
        return obcResult;
        /**modify by dongjun on 20181012 end 返回结果集修改**/
    }
    
    /**
     * 
     * @param handleListValAgencyInfo 机构信息表
     * @return 客户信息表
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleListValAgencyInfo(List<ValAgencyInfo> listValAgencyInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        List<MstCustomerInfo> valCustomerInfoList = new ArrayList<MstCustomerInfo>();
        // 获取基础信息
        for (ValAgencyInfo dto : listValAgencyInfo) {
            // 客户信息  处理
            MstCustomerInfo valCustomerInfo = getValCustomerInfo(dto);
            valCustomerInfoList.add(valCustomerInfo);
        }
        resultMap.put("ValCustomerInfo", valCustomerInfoList);
        return resultMap;
    }
    
    /**
     * 客户信息表 接收
     * 
     * @param dto 机构信息
     * @return 客户信息表
     * @see
     * @since 1.0
     */
    private MstCustomerInfo getValCustomerInfo(ValAgencyInfo dto) {

        MstCustomerInfo valCustomerInfo = new MstCustomerInfo();
        // 客户代码 = A.机构代码
        valCustomerInfo.setCustomerId(dto.getAgencyId());
        // 客户名称 = A.机构名称
        valCustomerInfo.setCustomerName(dto.getAgencyName());
        // 客户简称 = A.机构简称
        valCustomerInfo.setCustomerAbbr(dto.getAgencyAbbr());
        // 是否法人机构 = A.是否法人机构
//        valCustomerInfo.setIsCorporation(dto.getIsCorporation());
        // 是否交易对手= A.是否交易对手
//        valCustomerInfo.setIsCounterParty(dto.getIsCounterParty());
        // 是否发行人 = A.是否发行人
        valCustomerInfo.setIsIssuer(dto.getIsIssuer());
        // 是否资产管理人 = A.是否管理人
        valCustomerInfo.setIsAssteManager(dto.getIsManager());
        // 是否托管人 = A.是否托管人
        valCustomerInfo.setIsTruster(dto.getIsTruster());
        // 是否承销商 = A.是否承销商
        valCustomerInfo.setIsSaler(dto.getIsSaler());
        // TODO 上级客户代码 = 待定
//        valCustomerInfo.setpCustomerId("");
        // 客户类型 = A.机构类型
        valCustomerInfo.setCustomerType(dto.getAgencyType());
        // 企业性质= A.企业性质
//        valCustomerInfo.setCorporationType(dto.getCorporationType());
        return valCustomerInfo;
    }

}
