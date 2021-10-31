package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.background.component.common.pack.common.util.DateUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.cfets.etrib.dao.CfetsEtrIbBondinfoMapper;
import com.joyintech.tams.cfets.etrib.dao.TempMapper;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosiInfoRequestDto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosinfo001Dto;
import com.joyintech.tams.cfets.etrib.dto.CfetsEtrIbBondPosiPage001RequestDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service("backEtrIbBondinfo001Service_temp")
public class TempService extends BaseService {

    @Autowired
    private CfetsEtrIbBondinfoMapper cfetsEtrIbBondinfoMapper;

    @Autowired
    private TempMapper tempMapper;

    public void test(){
        Pattern pattern = Pattern.compile("[0-9.]*");
        String patternStr = "123.2aaabbb";
        boolean str = pattern.matcher(patternStr).matches();
        System.out.println(str);
    }
    /**
     * 查询前一日持仓指标
     * @param cdate 交易日期
     * @param portfolioIdList 组合代码
     * @param finprodId 资产代码
     * @param firstLevelType 债券一级分类
     */
    public List<BaseMap> selectLastCostAmt(Date cdate, List<String> portfolioIdList, String finprodId, String firstLevelType){
        BaseMap reqMap = BaseMap.newMap()
                .put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue())    // 债券
                .put("SVR_TRADE_MARKET_01", DictCodeListExt1.交易场所_银行间.getValue())           // 银行间
                .put("cdate", DateUtil.addDay(cdate, -1));
        
        if(portfolioIdList != null && portfolioIdList.size()>0) reqMap.put("portfolioIdList", portfolioIdList);
        if(StringUtil.isNotEmpty(finprodId)) reqMap.put("finprodId",finprodId);
        if(StringUtil.isNotEmpty(firstLevelType)) reqMap.put("firstLevelType",firstLevelType);
        
        List<BaseMap> list = tempMapper.selectLastCostAmt(reqMap);
        System.out.println(list);
        /**
         * 返回结果 PORTFOLIO_ID、FINPROD_ID、INV_AIM、BUY_COST_AMT、SHARE_AMT
         */
        return list;
    }

    /**
     * 当日交易数据查询
     * @param cdate 交易日期
     * @param portfolioIdList 组合代码
     * @param finprodId 资产代码
     * @param firstLevelType 债券一级分类
     */
    public List<BaseMap> selectCurrTrdData(Date cdate, List<String> portfolioIdList, String finprodId, String firstLevelType){
        BaseMap reqMap = BaseMap.newMap()
                .put("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue())    // 债券
                .put("SVR_TRADE_MARKET_01", DictCodeListExt1.交易场所_银行间.getValue())           // 银行间
                .put("PS_T01", DictCodeList.交易方向_买入.getValue())
                .put("PS_T02", DictCodeList.交易方向_卖出.getValue())
                .put("PS_T03", DictCodeList.交易方向_行权_回售.getValue())
                .put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue())
                .put("YN_N", DictCodeList.是否_否.getValue())
                .put("cdate", cdate);
        
        if(portfolioIdList != null && portfolioIdList.size()>0) reqMap.put("portfolioIdList", portfolioIdList);
        if(StringUtil.isNotEmpty(finprodId)) reqMap.put("finprodId",finprodId);
        if(StringUtil.isNotEmpty(firstLevelType)) reqMap.put("firstLevelType",firstLevelType);
        
        List<BaseMap> list = tempMapper.selectCurrTrdData(reqMap);
        System.out.println(list);
        /**
         *         b.PORTFOLIO_ID ,
         *         a.FINPROD_ID ,
         *         a.INV_AIM ,
         *         a.PS ,
         *         a.UNIT_CPRICE AS PRICE,
         *         a.SHARE_AMT AS AMT
         */
        return list;
    }
    
    /**
     * 银行间指令，头寸信息
     * @param pageParam
     * @return
     */
    public Map findPositionInfo(CfetsEtrIbBondPosiInfoRequestDto requestDto) {
//        Map map = cfetsEtrIbBondinfoMapper.queryPositionInfo(requestDto);
//        if (map != null){
//            // 卖空模式
//            map.put("SHORT_SELLING_SUPPORTED", CacheUtil.getSysParam("SHORT_SELLING_SUPPORTED"));
//        }
        return null;
    }
}
