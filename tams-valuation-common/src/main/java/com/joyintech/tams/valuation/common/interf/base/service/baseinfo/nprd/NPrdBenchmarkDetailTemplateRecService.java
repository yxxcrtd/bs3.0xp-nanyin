/*
 * 文件名：NPrdBenchmarkDetailTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.nprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.NPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBenchmarkDetailN;
import com.joyintech.framework.util.StringUtil;

/**
 * 净值型产品业绩基准信息表（VAL_PRD_BENCHMARK_DETAIL_N）
 * 
 * @author wangqidong
 * @version 1.0 2019年2月15日
 * @see NPrdBenchmarkDetailTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class NPrdBenchmarkDetailTemplateRecService {

    /**
     * XMl文件解析Service
     */
    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public NPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 初始化返回对象
        NPrdBaseInfoValCommonResult result = new NPrdBaseInfoValCommonResult();
        List<ValPrdBenchmarkDetailN> listValPrdBenchmarkDetailN = new ArrayList<ValPrdBenchmarkDetailN>();
        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);
        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdBenchmarkDetailN = returnResult.getT(ValPrdBenchmarkDetailN.class);
        // 3、根据有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 业绩基准信息表List
        List<FinBaseRuleInfo> listFinBenchmarkDetail = handleValPrdRateAdjustN(
            listValPrdBenchmarkDetailN);
        result.setListFinBenchmarkDetail(listFinBenchmarkDetail);

        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;
    }

    /**
     * 净值型业绩基准信息
     * 
     * @param listValPrdBenchmarkDetailN
     * @return
     * @see
     * @since 1.0
     */
    private List<FinBaseRuleInfo> handleValPrdRateAdjustN(List<ValPrdBenchmarkDetailN> listValPrdBenchmarkDetailN) {
        // 业绩基准信息List
        List<FinBaseRuleInfo> listFinBenchmarkDetail = new ArrayList<FinBaseRuleInfo>();
        for (ValPrdBenchmarkDetailN dto : listValPrdBenchmarkDetailN) {
            // 现金流计算规则
        	FinBaseRuleInfo valFinBenchmarkDetail = this.getFinBenchmarkDetail(dto);
            listFinBenchmarkDetail.add(valFinBenchmarkDetail);
        }
        return listFinBenchmarkDetail;
    }

    /**
     * 业绩基准信息表接收
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private FinBaseRuleInfo getFinBenchmarkDetail(ValPrdBenchmarkDetailN dto) {

        // 业绩基准信息
    	FinBaseRuleInfo valFinBenchmarkDetail = new FinBaseRuleInfo();
        // 基准代码   自动生成
        valFinBenchmarkDetail.setBaseRuleId(InterfHelper.getUUid());
        // 生效日期 = dto.生效日
        valFinBenchmarkDetail.setEffDate(dto.getEffDate());
        // 利率类型 = dto.利率类型
        valFinBenchmarkDetail.setRateType(dto.getRateType());
        // 基准编号 =
        // valFinBenchmarkDetail.setEffDate(null);
        // 基准类型 = dto.基准类型
//        valFinBenchmarkDetail.setBmType(dto.getIntRateType());
        // 重置规则 = dto.重置规则
        valFinBenchmarkDetail.setResetType(dto.getResetType());
        // 初始利率 = dto.初始利率
        valFinBenchmarkDetail.setRate(dto.getProprate());
        // 基准利率= dto.基准利率
//        valFinBenchmarkDetail.setBasicRate(dto.getIntRateId());
        // 调整系数 = dto.调整系数
        valFinBenchmarkDetail.setCoefficient(dto.getCoefficient());
        // 利差 = dto.利差
        valFinBenchmarkDetail.setSpreadRate(dto.getSpreadRate());
        // 金融产品代码 = dto.产品代码
        valFinBenchmarkDetail.setFinprodId(dto.getProdId());
        // 金融产品类型 = dto.金融产品类型
//        valFinBenchmarkDetail.setFinprodType(dto.getFinprodType());
        // 分支序号
        Long branch = null;
        if (StringUtil.equals(dto.getFinprodType(), DictCodeListExt1.金融产品类型_理财子产品.getValue())) {
            branch = Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(dto.getProdVdate()));
        }
        else {
            branch = InterfCommonConstant.分支序号_默认;
        }
        valFinBenchmarkDetail.setBranch(branch);
        return valFinBenchmarkDetail;
    }

}
