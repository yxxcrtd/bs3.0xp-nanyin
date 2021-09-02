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

import com.joyintech.tams.common.base.entity.MstCustomerInfoAddWithBLOBs;
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

        /** add by yanya on 20210831 增加客户信息附属表 start**/
        Map<String, Object> resultMap = handleListValAgencyInfo(listValAgencyInfo);
        if (resultMap != null) {
            obcResult.setValCustomerInfoList((List<MstCustomerInfo>) resultMap.get("ValCustomerInfo"));
            obcResult.setValCustomerInfoAddList((List<MstCustomerInfoAddWithBLOBs>) resultMap.get("ValCustomerInfoAdd"));
            /** add by yanya on 20210831 增加客户信息附属表 end**/
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
        /** add by yanya on 20210831 增加客户信息附属表 start**/
        List<MstCustomerInfoAddWithBLOBs> valCustomerInfoAddList = new ArrayList<MstCustomerInfoAddWithBLOBs>();
        /** add by yanya on 20210831 增加客户信息附属表 end**/
        // 获取基础信息
        for (ValAgencyInfo dto : listValAgencyInfo) {
            // 客户信息  处理
            MstCustomerInfo valCustomerInfo = getValCustomerInfo(dto);
            valCustomerInfoList.add(valCustomerInfo);
            /** add by yanya on 20210831 增加客户信息附属表 start**/
            valCustomerInfoAddList.add(this.getValCustomerInfoAdd(dto));
            /** add by yanya on 20210831 增加客户信息附属表 end**/
        }
        resultMap.put("ValCustomerInfo", valCustomerInfoList);
        /** add by yanya on 20210831 增加客户信息附属表 start**/
        resultMap.put("ValCustomerInfoAdd", valCustomerInfoAddList);
        /** add by yanya on 20210831 增加客户信息附属表 end**/
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

    /**
     * 映射 客户信息附属表
     * add by yanya on 20210831
     * @param dto
     * @return
     */
    private MstCustomerInfoAddWithBLOBs getValCustomerInfoAdd(ValAgencyInfo dto) {
        MstCustomerInfoAddWithBLOBs result = new MstCustomerInfoAddWithBLOBs();
        // 客户代码 = A.机构代码
        result.setCustomerId(dto.getAgencyId());
        result.setPartyType3(dto.getPartyType3()); // 机构类型（技术领域），高新技术企业、其他 SVR_PARTY_TYPE3  必填
        result.setPartyType2(dto.getCorporationType()); // 企业性质 ENTERPRISE_PROPERTY 必填 //原接口就有的字段，非本次新加
        result.setPartyType1(dto.getPartyType1());  // 机构类型(规模) MARKET_INSTI_TYPE_SCA 必填
        result.setIndustry(dto.getIndustry()); //所属行业 INSTITUTION_INDUSTRY_FIRST_LEVEL 必填
        result.setIndustry2(dto.getIndustry2());// 行业二级分类 INSTITUTION_INDUSTRY_SECOND_LEVEL 必填
        result.setHsType(dto.getValClas());//原接口就有的字段，非本次新加
        result.setInsitituteCode(dto.getInsitituteCode()); // 统一社会信用代码
        result.setIsGov(dto.getIsGov());// 是否政府融资平台 isGov
        result.setMainCapital(dto.getMainCapital()); // 核心资本(万元)
        result.setRegisterCapital(dto.getRegisterCapital()); //注册资本(万元)
        result.setProvince(dto.getProvince()); //所属省 ADMINISTRATIVE_TYPE1
        result.setCity(dto.getCity()); // 所属市 ADMINISTRATIVE_TYPE2
        result.setCounty(dto.getCounty());  // 所属县 ADMINISTRATIVE_TYPE3
        result.setHoldingType(dto.getHoldingType()); // 控股类型  HOLDING_TYPE
        result.setCustomerObjType(dto.getCustomerObjType()); // 客户主体类型 CUSTOMER_PRIN_TYPE
        result.setIsListedCompany(dto.getIsListedCompany()); //是否上市公司  YES_OR_NO
        result.setIsGroupCustomer(dto.getIsGroupCustomer());// 是否集团客户 YES_OR_NO
        result.setParentCustomerName(dto.getParentCustomerName());// 所属集团客户名称
        result.setIsPrivate(dto.getIsPrivate());// 是否民营企业 YES_OR_NO
        result.setIsEscapeDept(dto.getIsEscapeDept());// 是否为逃废债企业 YES_OR_NO
        result.setCreditType(dto.getCreditType());//授信策略  CREDIT_POLICY
        result.setIsCreditRelatedTrd(dto.getIsCreditRelatedTrd());// 是否授信类关联交易 YES_OR_NO
        result.setStructAdjustType(dto.getStructAdjustType());// 产业结构调整类型 INDUSTY_STRUCT_ADJ_TYPE
        result.setIndusUpgrateType(dto.getIndusUpgrateType());//工业转型升级标识  INDUSTY_TRANSFORM_TYPE
        result.setBusiScope(dto.getBusiScope());// 经营范围
        result.setMainBusi(dto.getMainBusi());// 主营业务
        return result;
    }

}
