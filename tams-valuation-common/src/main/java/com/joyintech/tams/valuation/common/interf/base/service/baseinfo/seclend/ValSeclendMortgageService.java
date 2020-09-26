/*
 * 文件名：ValSeclendMortgageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2018年10月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.seclend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinDealPledge;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.SecLendInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSeclendMortgage;

/**
 * 债券借贷 - 质押债券信息模板处理service(VAL_SECLEND_MORTGAGE)
 * 
 * @author wangqidong
 * @version 1.0 2018年10月30日
 * @see ValSeclendMortgageService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValSeclendMortgageService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 债券借贷 - 质押债券信息接口处理
     * 
     * @param templateSourceFlgDTO 文件来源和类型
     * @param acctEntity 计提基础信息DTO
     * @return SecLendInfoCommonResult
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public SecLendInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                           TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        SecLendInfoCommonResult result = new SecLendInfoCommonResult();
        List<ValSeclendMortgage> listValSeclendMortgage = new ArrayList<ValSeclendMortgage>();
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValSeclendMortgage = returnResult.getT(ValSeclendMortgage.class);
        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 定义质押债券信息List
        List<FinDealPledge> listValFinDealPledge = handleValSeclendMortgage(
            listValSeclendMortgage);
        result.setListFinDealPledge(listValFinDealPledge);
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());

        return result;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param listValSeclendMortgage
     * @return
     * @see
     * @since 1.0
     */
    private List<FinDealPledge> handleValSeclendMortgage(List<ValSeclendMortgage> listValSeclendMortgage) {
        // 定义质押品List
        List<FinDealPledge> listValFinDealPledge = new ArrayList<FinDealPledge>();
        for (ValSeclendMortgage dto : listValSeclendMortgage) {
            // 质押品
            FinDealPledge finDealPledge = getFinDealPledge(dto);
            listValFinDealPledge.add(finDealPledge);
        }
        return listValFinDealPledge;
    }

    /**
     * 质押品
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinDealPledge getFinDealPledge(ValSeclendMortgage dto) {
        // 定义 质押品dto
        FinDealPledge finDealPledge = new FinDealPledge();
        // 金融产品代码 = B.金融产品代码
        finDealPledge.setFinprodId(dto.getFinprodId());
        // 金融产品类型 = B.金融产品类型
//        finDealPledge.setFinprodType(dto.getFinprodType());
        // 分支序号 = 设置全局变量(默认为0)
        finDealPledge.setBranch(InterfCommonConstant.分支序号_默认);
        // 质押金融产品代码 = B.债券代码
        finDealPledge.setPledgeFinprodId(dto.getSecId());
        // 质押金融产品类型 = F01 债券    数据字典：金融产品类型
//        finDealPledge.setPledgeFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 质押数量 = B.券面总额
        finDealPledge.setPledgeNumber(dto.getPrinAmt());
        return finDealPledge;
    }

}



