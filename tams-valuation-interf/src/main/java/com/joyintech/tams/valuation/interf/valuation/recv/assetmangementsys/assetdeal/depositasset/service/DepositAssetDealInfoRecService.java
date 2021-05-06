/*
 * 文件名：NPrdBaseInfoComForInterfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wudebao
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.depositasset.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.AssetDealInfoValCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.depositasset.DepositeAssetAstDealTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.depositasset.DepositeAssetPeriodFundTemplateRecService;
import com.joyintech.tams.valuation.common.interf.base.service.assetdeal.depositasset.DepositeAssetRecTradeTemplateRecService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.assetdeal.depositasset.dto.DepositAssetDealInfoProcessorResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 批处理启动文件接收批处理 对存款类资产交易信息文件系列机型处理，供给Writer使用
 * 
 * @author 魏鹏
 * @version 1.0 2018年6月22日
 * @see DepositAssetDealInfoRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class DepositAssetDealInfoRecService {

    /**
     * 存款类资产-存款交易 模板处理Service
     */
    @Autowired
    private DepositeAssetAstDealTemplateRecService depositeAssetAstDealTemplateRecService;

    /**
     * 存款类资产-存款存续期 模板处理Service
     */
    @Autowired
    private DepositeAssetPeriodFundTemplateRecService depositeAssetPeriodFundTemplateRecService;
    
    /**
     * 存款类资产-应收款项转让交易 模板处理Service
     */
    @Autowired
    private DepositeAssetRecTradeTemplateRecService depositeAssetRecTradeTemplateRecService;
    
    @Value("${interf.properties.fourLevelSubjectKg}")
    private String fourLevelSubjectKg;

    /**
     * 存款类资产交易信息文件处理
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings({"unchecked"})
    public DepositAssetDealInfoProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {
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
        acctEntity.setFourLevelSubjectKg(fourLevelSubjectKg);
        // 01-存款交易
        if (TempConstant.存款类资产存款交易.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetAstDealTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_存款类资产交易.getValue());
            ps.add(DictCodeList.交易方向_买入.getValue());
            ps.add(DictCodeList.交易方向_卖出.getValue());
            ps.add(DictCodeList.交易方向_提前支取.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        // 02-存款存续期表
        else if (TempConstant.存款类资产存款存续期表.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetPeriodFundTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_存款类资产交易.getValue());
            ps.add(DictCodeList.交易方向_还本到账.getValue());
            ps.add(DictCodeList.交易方向_收息到账.getValue());
            /*modify by wujie on 20190223
            ps.add(DictCodeList.交易方向_收息.getValue());
            */
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        // 03-存款应收款项转让交易
        else if (TempConstant.存款类资产应收款项转让交易.equals(acctEntity.getTmpEName())) {
            rtnResult = depositeAssetRecTradeTemplateRecService.execute(templateSourceFlgDTO,
                acctEntity);
            paramMap.put("busiType", DictCodeList.业务类型_存款类资产交易.getValue());
            ps.add(DictCodeList.交易方向_应收款项转入.getValue());
            ps.add(DictCodeList.交易方向_应收款项转出.getValue());
            paramMap.put("pslist", ps);

            /**add by wujie on 20210506 start**/
            paramMap.put(InterfCommonConstant.读取文件状态, rtnResult.getParamMap()==null?null:rtnResult.getParamMap().get(InterfCommonConstant.读取文件状态));
            /**add by wujie on 20210506 end**/
        }
        else{
        	rtnResult.setDataNum(0);
        }
        rtnResult.setParamMap(paramMap);
        return new DepositAssetDealInfoProcessorResult(rtnResult);
    }

}
