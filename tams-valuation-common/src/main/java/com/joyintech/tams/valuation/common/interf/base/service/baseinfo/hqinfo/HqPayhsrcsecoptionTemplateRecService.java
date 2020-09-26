/*
 * 文件名：HqPayhsrcsecoptionTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：dongjun
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.hqinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.HqInfoForInterfRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestPlan;

/**
 * 行情信息-债券行权计划
 * 
 * @author dongjun
 * @version 1.0 2018年10月11日
 * @see HqPayhsrcsecoptionTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class HqPayhsrcsecoptionTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public HqInfoForInterfRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                                  TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        HqInfoForInterfRecCommonResult result = new HqInfoForInterfRecCommonResult();

        List<ValSecVestPlan> valSecVestPlanList = new ArrayList<ValSecVestPlan>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 此部分处理待以后从画面入口的时候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
        // 1、文件读取解析 进行数据有效性的check,并生出进入日志表的数据结构
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        valSecVestPlanList = returnResult.getT(ValSecVestPlan.class);

        //List<ValSecVestPlan> infoList = new ArrayList<ValSecVestPlan>();
        // 如果是中债估值需要选择推荐的那条估值
//        if (acctEntity.getTmpEName().equals(PabHqContans.TMP_E_NAME_债券行权计划)) {
//            infoList = valSecVestPlanList;
//        }

        // 2、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        List<FinExercisePlan> listFinExercisePlan = (List<FinExercisePlan>)handleListToVaLInfo(
            valSecVestPlanList, acctEntity.getTmpEName()).get("finExercisePlanList");

        result.setFinExercisePlanList(listFinExercisePlan);
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
    private Map<String, Object> handleListToVaLInfo(List<ValSecVestPlan> infoList,
                                                    String tmpName) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinExercisePlan> finExercisePlanList = new ArrayList<FinExercisePlan>();
        for (ValSecVestPlan dto : infoList) {
            // 债券市场行情 处理
            FinExercisePlan finExercisePlan = getFinExercisePlan(dto, tmpName);
            finExercisePlanList.add(finExercisePlan);
        }
        resultMap.put("finExercisePlanList", finExercisePlanList);
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
    private FinExercisePlan getFinExercisePlan(ValSecVestPlan dto, String tmpName) {
        FinExercisePlan valInfo = new FinExercisePlan();
        /**modify by wujie on 20190121 start
        // 现金流代码 = InterfHelper.getMd5CashId_(A.资产代码,数据字典：金融产品类型.债券,数据字典:现金流二级类型.行权)
        valInfo.setCashId(
            InterfHelper.getMd5CashId_(dto.getAssetId() + DictCodeListExt1.金融产品类型_债券.getValue()
                                       + DictCodeListExt1.现金流二级类型_行权.getValue()));
        **/
        // 现金流代码 = InterfHelper.getMd5CashId_(A.资产代码,数据字典：金融产品类型.债券,数据字典:现金流二级类型.本金)
        valInfo.setCashId(
            InterfHelper.getMd5CashId_(dto.getAssetId() + DictCodeListExt1.金融产品类型_债券.getValue()
                                       + DictCodeListExt1.现金流二级类型_本金.getValue()));
        /**modify by wujie on 20190121**/
        
        
        // 行权方式 = A.行权方式
        valInfo.setExerciseType(dto.getExecMode());
        // 申请开始日 = A.行权申请开始日
        valInfo.setVdate(dto.getVdate());
        // 申请结束日 = A.行权申请结束日
        valInfo.setMdate(dto.getMdate());
        // 计划行权日 = A.行权日期
        valInfo.setExerciseDate(dto.getExecDate());
        // 行权价 = A.行权价格
        valInfo.setExercisePrice(dto.getExecCprice());
        // 金融产品代码 = A.资产代码
        valInfo.setFinprodId(dto.getAssetId());
        // 金融产品类型 = 数据字典：金融产品类型.债券
        valInfo.setFinprodType(DictCodeListExt1.金融产品类型_债券.getValue());
        // 分支序号 = 设置全局变量(默认为0）
        valInfo.setBranch(InterfCommonConstant.分支序号_默认);

        return valInfo;
    }
}
