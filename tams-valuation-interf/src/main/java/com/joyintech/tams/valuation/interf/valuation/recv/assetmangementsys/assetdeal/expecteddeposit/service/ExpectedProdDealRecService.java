package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.expecteddeposit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.ExpectedProdDealValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.expectedprddeal.ExpectedPrdDealFeeDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.expectedprddeal.ExpectedPrdDealPeriodDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.expecteddeposit.dto.ExpectedProdDealProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
/**
 * 
 * 批处理启动文件接收批处理 对预期型产品-产品交易 文件系列机型处理，供给Writer使用
 * @author wangqidong
 * @version 1.0 2018年10月9日
 * @see ExpectedProdDealRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ExpectedProdDealRecService {
    
    /**
     * 预期型产品存续期交易表 模板处理Service
     */
    @Autowired
    private ExpectedPrdDealPeriodDealTemplateRecService expectedPrdDealPeriodDealTemplateRecService;
    
    /**
     * 预期型产品费用交易表 模板处理Service
     */
    @Autowired
    private ExpectedPrdDealFeeDealTemplateRecService expectedPrdDealFeeDealTemplateRecService;


    /**
     * 批处理启动文件接收批处理
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public ExpectedProdDealProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //初始化 模板解析对象
        ExpectedProdDealValCommonResult result = new ExpectedProdDealValCommonResult();
        
        //设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        
        //用于去重数据
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("TMP_E_NAME", acctEntity.getTmpEName());
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
        
        if(TempConstant.预期型产品存续期交易表.equals(acctEntity.getTmpEName())) {
            result = expectedPrdDealPeriodDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType",DictCodeList.业务类型_预期收益型TA交易.getValue());
            ps.add(DictCodeList.交易方向_产品申购.getValue());
            ps.add(DictCodeList.交易方向_产品认购.getValue());
            ps.add(DictCodeList.交易方向_产品赎回.getValue());
            ps.add(DictCodeList.交易方向_产品到期.getValue());
            ps.add(DictCodeList.交易方向_中间付息.getValue());
            ps.add(DictCodeList.交易方向_赎回收益确认.getValue());
            paramMap.put("pslist", ps);
        } 
        else if (TempConstant.预期型产品费用交易表.equals(acctEntity.getTmpEName())) {
            result = expectedPrdDealFeeDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_产品费用交易.getValue());
            ps.add(DictCodeList.交易方向_产品付费.getValue());
            
            paramMap.put("pslist", ps);
            
            /**modify by wujie on 20190123 remark:重复导入删除逻辑调整
            if(result.getParamMap()!=null){
                paramMap.put("cqFtradeIds", result.getParamMap().get("cqFtradeIds"));
            }**/
            paramMap.put("profitType", DictCodeList.收益类型_收益型.getValue());
            /**modify by wujie on 20190123 end**/
        } else {
            result.setDatanum(0);
        }
        result.setParamMap(paramMap);
        return new ExpectedProdDealProcessorResult(result);
    }

}
