package com.joyintech.invest.interf.s055.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * 市场机构接口
 * 
 * @author wm
 * @version 1.0 2020年2月7日
 * @see SomdMstMarketInstitutionsService
 * @since 1.0
 */
@Service
@INF("S055")
public class SomdMstMarketInstitutionsService extends InterfSendBaseService<String> {

    /**
     * 条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

    /**
     * 转换为标准输出报文 {@inheritDoc}
     */
    @Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        // 消息体
        Map<String, Object> body = response.getBody();
        // 市场信息Map
        Map<String, Object> mstCustomerInfo = (Map<String, Object>)request.get("mstCustomerInfo");
        //机构代码
        body.put("CUSTOMER_ID", mstCustomerInfo.get("customerId"));
        //市场机构名称
        body.put("INSTITUTION_NAME", mstCustomerInfo.get("customerName"));
        //备注
        body.put("REMARK", mstCustomerInfo.get("remark"));
        //市场信息附属表
        Map<String, Object> mstCustomerInfoAddWithBLOBs = (Map<String, Object>)request.get("mstCustomerInfoAddWithBLOBs");
        //机构类型（规模） 
        body.put("PARTY_TYPE1", mstCustomerInfoAddWithBLOBs.get("partyType1"));
        // 机构类型（经济类型）
        body.put("PARTY_TYPE2", mstCustomerInfoAddWithBLOBs.get("partyType2"));
        //机构类型（技术领域）
        body.put("PARTY_TYPE3", mstCustomerInfoAddWithBLOBs.get("partyType3"));
        
        //所属行业 
        body.put("REP_INDUSTRY", mstCustomerInfoAddWithBLOBs.get("industry"));
        //行业二级分类 
        body.put("REP_INDUSTRY_LEVEL2", mstCustomerInfoAddWithBLOBs.get("industry2"));
        //组织机构代码/统一社会信用代码 
        body.put("INSTITUTION_CODE", mstCustomerInfoAddWithBLOBs.get("insitituteCode"));
        
        // 是否政府融资平台
        body.put("IS_GOV", mstCustomerInfoAddWithBLOBs.get("isGov"));
        // 融资人所属省
        body.put("PROVINCE", mstCustomerInfoAddWithBLOBs.get("province"));
        //融资人所属市
        body.put("CITY", mstCustomerInfoAddWithBLOBs.get("city"));
        // 融资人所属县
        body.put("COUNTY", mstCustomerInfoAddWithBLOBs.get("county"));
        // 控股类型 
        body.put("HOLDING_TYPE", mstCustomerInfoAddWithBLOBs.get("holidingType"));
        // 客户主体类型 
        body.put("CUSTOMER_OBJ_TYPE", mstCustomerInfoAddWithBLOBs.get("customerObjType"));
        //是否上市公司 
        body.put("IS_LISTED_COMPANY", mstCustomerInfoAddWithBLOBs.get("isListedCompany"));
        // 是否集团客户 
        body.put("IS_GROUP_CUSTOMER", mstCustomerInfoAddWithBLOBs.get("isGroupCustomer"));
        // 所属集团客户编号 
        body.put("PARENT_CUSTOMER_ID", mstCustomerInfoAddWithBLOBs.get("parentCustomerId"));
        //所属集团客户名称  
        body.put("PARENT_CUSTOMER_NAME", mstCustomerInfoAddWithBLOBs.get("parentCustomerName"));
        //  是否民营企业 
        body.put("IS_PRIVATE", mstCustomerInfoAddWithBLOBs.get("isPrivate"));
        // 是否为逃废债企业 
        body.put("IS_ESCAPE_DEPT", mstCustomerInfoAddWithBLOBs.get("isEscapeDept"));
        
        // 产业结构调整类型  
        body.put("STRUCT_ADJUST_TYPE", mstCustomerInfoAddWithBLOBs.get("structAdjustType"));
        //工业转型升级标识 
        body.put("INDUS_UPGRATE_TYPE", mstCustomerInfoAddWithBLOBs.get("indusUpgrateType"));
        //经营范围 
        body.put("BUSI_SCOPE", mstCustomerInfoAddWithBLOBs.get("busiScope"));
        //主营业务 
        body.put("MAIN_BUSI", mstCustomerInfoAddWithBLOBs.get("mainBusi"));
        //客户评级
//        body.put("CUSTOMER_LEVEL", mstCustomerInfoAddWithBLOBs.get("partyType1"));
        //注册资本
        body.put("REGIST_CAPITAL", mstCustomerInfoAddWithBLOBs.get("registCapital"));
        //实收资本 
        body.put("ACTUAL_CAPITAL", mstCustomerInfoAddWithBLOBs.get("actualCapital"));
        return response;
    }

    /**
     * 接口预留先决处理操作 {@inheritDoc}
     */
    @Override
    protected void beforeHandle(Map<String, Object> request, ClientData response) {

    }

    /**
     * 接口预留后置处理操作 {@inheritDoc}
     */
    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }
}
