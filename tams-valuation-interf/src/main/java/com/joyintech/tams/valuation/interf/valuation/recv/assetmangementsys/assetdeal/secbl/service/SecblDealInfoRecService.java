package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.secbl.service;

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
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.secdl.SecPeriodDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.secdl.SecSeBackDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.secdl.SecblTranDealTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.secbl.dto.SecblDealInfoProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 批处理启动文件接收批处理 对债券借贷信息文件系列机型处理，供给Writer使用
 * 
 * @author dongxiangyang
 * @version 1.0 2018年10月29日
 * @see SecblDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SecblDealInfoRecService {

	 /**
     * 债券借贷交易 模板处理Service
     */
    @Autowired
    private SecblTranDealTemplateRecService  secblTranDealTemplateRecService;
    /**
     * 债券借贷标的劵存续期交易 模板处理Service
     */
    @Autowired 
    private  SecPeriodDealTemplateRecService secPeriodDealTemplateRecService;
    
    /**
     * 债券借贷标的券卖出与购回 模板处理Service
     */
    @Autowired 
    private SecSeBackDealTemplateRecService secSeBackDealTemplateRecService;
    /**
     * 债券借贷信息文件处理
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public  SecblDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity){
    	
    	// 定义返回变量
        AssetDealInfoValCommonResult rtnResult = new AssetDealInfoValCommonResult();
        
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());
        Map<String,Object> paramMap = new HashMap<String,Object>();
        List<String> bookSetIds = (List<String>)acctEntity.getParams().get(InterfCommonConstant.接口_运行接口账套ID);
        paramMap.put(InterfCommonConstant.接口_运行接口账套ID,bookSetIds);
        paramMap.put(InterfCommonConstant.接口模式,(String)acctEntity.getParams().get(InterfCommonConstant.接口模式));
        List<String> ps = new ArrayList<String>();
       
        if (TempConstant.债券借贷交易.equals(acctEntity.getTmpEName())){
        	 rtnResult=secblTranDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        	 paramMap.put("busiType", DictCodeList.业务类型_债券借贷交易.getValue());
        	 ps.add(DictCodeList.交易方向_到期.getValue());
             ps.add(DictCodeList.交易方向_买入.getValue());
             ps.add(DictCodeList.交易方向_卖出.getValue());
             paramMap.put("pslist", ps);//删除除这些交易方向的数据
             /**add by wujie on 20210506 start**/
             paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
             /**add by wujie on 20210506 end**/
        }else if(TempConstant.债券借贷标的券存续期交易.equals(acctEntity.getTmpEName())){
        	 rtnResult=secPeriodDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        	 paramMap.put("busiType", DictCodeList.业务类型_债券借贷标的债券交易.getValue());
        	 ps.add(DictCodeList.交易方向_融入债券收息.getValue());
             ps.add(DictCodeList.交易方向_卖空债券结息.getValue());
             ps.add(DictCodeList.交易方向_利息返还.getValue());
             ps.add(DictCodeList.交易方向_本金返还.getValue());
             ps.add(DictCodeList.交易方向_融入债券还本.getValue());
             ps.add(DictCodeList.交易方向_卖空债券还本.getValue());


             ps.add(DictCodeList.交易方向_融入债券派息到账.getValue());
             ps.add(DictCodeList.交易方向_融入债券派本到账.getValue());
             /**add by wujie on 20210429 start**/
            ps.add(DictCodeList.交易方向_还本到账.getValue());
            ps.add(DictCodeList.交易方向_收息到账.getValue());
            /**add by wujie on 20210429 20210429 end**/

            paramMap.put("pslist", ps);//删除除这些交易方向的数据

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/

        }else if(TempConstant.债券借贷标的券卖出与购回.equals(acctEntity.getTmpEName())){
        	 rtnResult=secSeBackDealTemplateRecService.execute(templateSourceFlgDTO, acctEntity);
        	 paramMap.put("busiType", DictCodeList.业务类型_债券借贷标的债券交易.getValue());
        	 ps.add(DictCodeList.交易方向_融入债券卖出.getValue());
             ps.add(DictCodeList.交易方向_融入债券购回.getValue());
             ps.add(DictCodeList.交易方向_本方券交割.getValue());
             paramMap.put("pslist", ps);//删除除这些交易方向的数据

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }else {
        	rtnResult.setDataNum(0);
        }
        rtnResult.setParamMap(paramMap);
        return  new SecblDealInfoProcessorResult(rtnResult);
    }
    
}
