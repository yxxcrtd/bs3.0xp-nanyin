/*
 * 文件名：CPrdPerformanceBenchmarkInfoTemplateRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huangjian
 * 修改时间：2019年2月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.cprd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.joyintech.tams.valuation.common.interf.base.dto.CPrdBaseInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdBenchmarkDetailC;

/**
 * 货币型型产品业绩基准信息模板处理 (VAL_PRD_BENCHMARK_DETAIL_C)
 * 
 * @author huangjian
 * @version 1.0 2019年2月14日
 * @see CPrdPerformanceBenchmarkInfoTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class CPrdPerformanceBenchmarkInfoTemplateRecService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

//    @Autowired
//    private InterfCommonService interfCommonService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public CPrdBaseInfoValCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                               TemplateOrderInfoReaderResult acctEntity) {
        // 定义返回变量
        CPrdBaseInfoValCommonResult result = new CPrdBaseInfoValCommonResult();
        List<ValPrdBenchmarkDetailC> listValPrdBenchmarkDetailC = new ArrayList<ValPrdBenchmarkDetailC>();

        // 解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();

        // 1、文件读取解析
        returnResult = templateAnalysisForXMLService.execute(acctEntity);

        // 2、进行数据有效性的check,并生出进入日志表的数据结构
        listValPrdBenchmarkDetailC = returnResult.getT(ValPrdBenchmarkDetailC.class);

        // 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        Map<String, Object> map = handleValPrdBenchmarkDetailC(listValPrdBenchmarkDetailC);

        // 判断返回结果不为空
        if (map != null) {
            // 标的类金融产品表
            List<FinBaseRuleInfo> valFinBenchmarkDetailList = (List<FinBaseRuleInfo>)map.get("FinBenchmarkDetail");
            result.setFinBenchmarkDetailList(valFinBenchmarkDetailList);

        }
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
        return result;

    }

    private Map<String, Object> handleValPrdBenchmarkDetailC(List<ValPrdBenchmarkDetailC> listValPrdBenchmarkDetailC) {
        Map<String, Object> resultMap = new HashMap<>();
        List<FinBaseRuleInfo> valFinBenchmarkDetailList = new ArrayList<FinBaseRuleInfo>();
        for (ValPrdBenchmarkDetailC item : listValPrdBenchmarkDetailC) {
            FinBaseRuleInfo dto = new FinBaseRuleInfo();
            /** modify by yanya on 20210222 业绩基准代码 start**/
            // 基准代码   自动生成
            dto.setBaseRuleId(InterfHelper.getUUid());
            /** modify by yanya on 20210222 业绩基准代码 end**/
            // modify by wangzhiang 错误代码注释 20190920 start
            // 基准代码 自动生成
//            dto.setBmCode(InterfHelper.getUUid());
            // 生效日期 J.生效日利率类型
            dto.setEffDate(item.getEffDate());
            // 利率类型 J.利率类型
//            dto.setIntType(item.getRateType());
            // 基准类型 J.基准类型
//            dto.setBmType(item.getIntRateType());
            // 重置规则 J.重置规则
            dto.setResetType(item.getResetType());
            // 初始利率 初始利率
//            dto.setInitRate(item.getProprate());
            // 基准利率 J.基准利率
//            dto.setBasicRate(item.getIntRateId());
            // 调整系数 J.调整系数
            dto.setCoefficient(item.getCoefficient());
            // 利差 J.利差
            dto.setSpreadRate(item.getSpreadRate());
            // 金融产品代码 J.产品代码
            dto.setFinprodId(item.getProdId());
            // 金融产品类型 J.金融产品类型
//            dto.setFinprodType(item.getFinprodType());
            // modify by wangzhiang 错误代码注释 20190920 end
            // 分支序号
            if (DictCodeListExt1.金融产品类型_理财子产品.getValue().equals(item.getFinprodType())) {
                dto.setBranch(
                    Long.valueOf(new SimpleDateFormat("yyyyMMdd").format(item.getProdVdate())));
            }
            else {
                dto.setBranch(InterfCommonConstant.分支序号_默认);
            }
            valFinBenchmarkDetailList.add(dto);
        }
        resultMap.put("FinBenchmarkDetail", valFinBenchmarkDetailList);
        return resultMap;
    }

}
