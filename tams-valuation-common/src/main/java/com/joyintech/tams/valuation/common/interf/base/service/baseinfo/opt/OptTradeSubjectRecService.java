/*
 * 文件名：OptTradeSubjectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：TanXuan
 * 修改时间：2020年01月08日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.interf.base.dto.OptTradeSubjectRecProcessorRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValOptTradeSubject;

/**
 * 期权交易标的信息（VAL_OPT_TRADE_SUBJECT）
 * 
 * @author TanXuan
 * @version 1.0 2020年01月08日
 * @see OptTradeSubjectRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class OptTradeSubjectRecService {

    /**
     * TemplateAnalysisForXMLService
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;
    
    @Autowired
    private InterfCommonService interfCommonService;
    
    @SuppressWarnings({"unchecked", "rawtypes"})
    public OptTradeSubjectRecProcessorRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                          TemplateOrderInfoReaderResult acctEntity) {
    	OptTradeSubjectRecProcessorRecCommonResult result = new OptTradeSubjectRecProcessorRecCommonResult();
        List<ValOptTradeSubject> listValOptTradeSubject = new ArrayList<ValOptTradeSubject>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValOptTradeSubject = returnResult.getT(ValOptTradeSubject.class);
        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = this.handleValOptTradeSubject(listValOptTradeSubject);
        if (!map.isEmpty()) {
            result.setListValFinTradeProduct((List<FinTradeProduct>)map.get("listValFinTradeProduct"));
            result.setListValFinProductType((List<FinProductType>)map.get("listValFinProductType"));
            result.setListValFinTradeProductAdd((List<FinTradeProductAdd>)map.get("listValFinTradeProductAdd"));
        }
        // 添加日志
        result.setListValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 有效数据，进行数据的编辑，并生成进入业务表的数据结构
     * 
     * @param listValOptTradeSubject List<ValOptTradeSubject>
     * @return Map<String,Object>
     * @see
     * @since 1.0
     */
    private Map<String, Object> handleValOptTradeSubject(List<ValOptTradeSubject> listValOptTradeSubject) {
        Map<String, Object> mapResult = new HashMap<>();
        List<FinTradeProduct> listValFinTradeProduct = new ArrayList<FinTradeProduct>();
        List<FinProductType> listValFinProductType = new ArrayList<FinProductType>();
        List<FinTradeProductAdd> listValFinTradeProductAdd = new ArrayList<FinTradeProductAdd>();
        
        for (ValOptTradeSubject item : listValOptTradeSubject) {
        	
        	//接收 交易类金融产品 T1
            FinTradeProduct ValFinTradeProduct = this.getValFinProduct(item);
            listValFinTradeProduct.add(ValFinTradeProduct);
            //接收 金融产品分类 T2
            FinProductType valFinProductType = this.getValFinProductType(item);
            listValFinProductType.add(valFinProductType);
            //接收 交易类金融产品附属表
            FinTradeProductAdd valFinTradeProductAdd = this.getValFinTradeProductAdd(item);
            listValFinTradeProductAdd.add(valFinTradeProductAdd);
        }
        mapResult.put("listValFinTradeProduct", listValFinTradeProduct);
        mapResult.put("listValFinProductType", listValFinProductType);
        mapResult.put("listValFinTradeProductAdd", listValFinTradeProductAdd);

        return mapResult;
    }

    /**
     * 接收 交易类金融产品 T1
     * 
     * @param dto
     * @return result
     * @see
     * @since 1.0
     */
    private FinTradeProduct getValFinProduct(ValOptTradeSubject dto) {
        FinTradeProduct result = new FinTradeProduct();
        // 金融产品代码 = A.期权交易标的代码
        result.setFinprodId(dto.getFinprodId());
        // 金融产品类型 = A.期权交易标的类型
        result.setFinprodType2(dto.getFinprodType());
        // 分支序号 = A.分支序号
        result.setBranch(dto.getBranch());
        // 合同编号 = A.合同编号
        result.setContractNo(dto.getContractNo());
        // 合同名称 = A.合同名称
        result.setContractName(dto.getContractName());
        // 起息日 = A.起息日
        result.setVdate(dto.getVdate());
        // 到期日 = A.起息日+ A.期限
        result.setMdate(DateUtil.parse(DateUtil.plusDays(DateUtil.format(dto.getVdate()), dto.getTerm().intValue())));
        // 期限 = A.期限
        result.setTerm(dto.getTerm());
        // 本金币种 = A.本金币种
        result.setCcy(dto.getCcy());
        //名义本金 = A.名义本金
        result.setPrin(dto.getPrin());
        //交易场所 = A.交易场所
        result.setTradeMarket(dto.getTradeMarket());
        //交易日历 = 调用共通设计->【详细设计-接口共通方法设计.xlsx】->交易日历映射关系SHEET
        result.setCalendarId(interfCommonService.getCalendarId(dto.getTradeMarket()));
        // 创建时间 = 系统时间
        result.setCreateTime(dto.getCreateTime());
        result.setCreateDept(null);
        // 所属机构 = A.所属机构   add by tanxuan on 20210310 多级法人权限控制改造
        result.setBelongInstitutionCode(dto.getBelongInstitutionCode());
        return result;
    }

    /**
     * 接收 金融产品分类 T2
     * 
     * @param dto ValOptTradeSubject
     * @return ValFinProductType
     * @see
     * @since 1.0
     */
    private FinProductType getValFinProductType(ValOptTradeSubject dto) {
        FinProductType result = new FinProductType();
        // 金融产品代码 = T1.金融产品代码 = A.期权交易标的代码
        result.setFinprodId(dto.getFinprodId());
        // 分支序号 = T1.分支序号 A.分支序号
        result.setBranch(dto.getBranch());
        // 分类6 = A.资产类型
        result.setType6(dto.getAssetClass());
        // 分类8 = 期权
        result.setType8(DictCodeListExt3.金融产品类型_投管用_期权.getValue());
        // 创建时间 = 系统时间
        result.setCreateTime(dto.getCreateTime());
        result.setCreateDept(null);
        return result;
    }

    /**
     * 接收 交易类金融产品附属表 T3
     * 
     * @param dto ValOptTradeSubject
     * @return ValFinTradeProductAdd
     * @see
     * @since 1.0
     */
    private FinTradeProductAdd getValFinTradeProductAdd(ValOptTradeSubject dto) {
        FinTradeProductAdd result = new FinTradeProductAdd();
        // 金融产品代码 = T1.金融产品代码 = A.期权交易标的代码
        result.setFinprodId(dto.getFinprodId());
        // 金融产品类型 = T1.金融产品类型 = A.期权交易标的类型
        result.setFinprodType2(dto.getFinprodType());
        // 分支序号 = T1.分支序号 A.分支序号
        result.setBranch(dto.getBranch());
        // 期权费类型 = A.期权费用类型
         result.setOptFeePayType(dto.getOptFeePayType());
        // 期权费率 = A.期权费率
        result.setOptFeeRate(dto.getOptFeeRate());
        // 登记托管机构 = A.登记托管机构REGIST_ORG
        result.setRegistOrg(dto.getRegistOrg());
        // 创建时间 = 系统时间
        result.setCreateTime(dto.getCreateTime());
        result.setCreateDept(null);
        //期权费 add by yanya on 20200208
        result.setOptFeeAmt(dto.getOptFeeAmt());
        return result;
    }
}
