package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.feedeal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.FeeDealCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.feedeal.FeeDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.feedeal.ValChannelProdFeeDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.feedeal.ValFeeDrawDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.feedeal.dto.FeeDealProcessorResult;
/**
 * 
 * 批处理启动文件接收批处理 对费用交易 文件系列机型处理，供给Writer使用
 * @author huwei
 * @version 1.0 2018年10月16日
 * @see FeeDealRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class FeeDealRecService {
    
    /**
     * 费用交易表 模板处理Service
     */
    @Autowired
    private FeeDealTemplateRecService feeDealTemplateRecService;
    
    @Autowired
    private ValChannelProdFeeDealTemplateRecService valChannelProdFeeDealTemplateRecService;
    
    @Autowired
    private ValFeeDrawDealTemplateRecService valFeeDrawDealTemplateRecService;
    /**
     * 批处理启动文件接收批处理
     * @param acctEntity
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public FeeDealProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
        //初始化 模板解析对象
    	FeeDealCommonResult result = new FeeDealCommonResult();
        
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
        if (TempConstant.费用交易表.equals(acctEntity.getTmpEName())) {
            result = feeDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_产品费用交易.getValue());
            ps.add(DictCodeList.交易方向_付费.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        } 
        else if (TempConstant.通道产品费用交易.equals(acctEntity.getTmpEName())) {
            result = valChannelProdFeeDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
            
            /**add by wujie on 20190321 start**/
            paramMap.put("busiType",DictCodeList.业务类型_货币型TA交易.getValue());
            
            paramMap.put("finprodType", DictCodeListExt1.金融产品类型_通道.getValue());
            
            ps.add(DictCodeList.交易方向_产品申购.getValue());
            paramMap.put("pslist", ps);
            /**add by wujie on 20190321 end**/

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
            
        } 
        else if(TempConstant.费用计提交易.equals(acctEntity.getTmpEName())){
            result = valFeeDrawDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);

            List<String> busiTypes = new ArrayList<String>();
            busiTypes.add(DictCodeListExt3.业务类型_费用计提交易.getValue());
            busiTypes.add(DictCodeListExt2.业务类型_浮动管理费挂账交易.getValue());
            paramMap.put("busiTypelist", busiTypes);

            ps.add(DictCodeListExt3.交易方向_费用计提.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, result.getParamMap()==null?null:result.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else {
            result.setDatanum(0);
        }
        result.setParamMap(paramMap);
        return new FeeDealProcessorResult(result);
    }
}
