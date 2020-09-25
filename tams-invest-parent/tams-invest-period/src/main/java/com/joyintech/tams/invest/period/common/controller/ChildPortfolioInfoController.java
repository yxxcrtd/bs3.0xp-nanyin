package com.joyintech.tams.invest.period.common.controller;

import com.jixu.common.util.JsonUtil;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.autoperiod.secover.AutoPeriodSecOverCommonService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.period.common.service.PeriodCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageId("ChildPortfolioInfo")
@RequestMapping("/invest/period/common/ChildPortfolioInfo")
@Controller
public class ChildPortfolioInfoController extends BaseController{
    /**
     * 存续期共通service
     */
    @Autowired
    private AutoPeriodCommonService commonService;
    
    /**
     * 资产逾期存续期共通service
     */
    @Autowired
    private AutoPeriodSecOverCommonService autoPeriodSecOverCommonService;
    
    @Override
    public String init(Model model) {
        Map<String,Object> map = RequestUtil.getPageParam();
        model.addAttribute("cols",map.get("cols"));
        model.addAttribute("subAconShareAmt",map.get("subAconShareAmt"));
        List<FinAssetIntInfo>  childPortfolioInfo = new ArrayList<>();
        if(StringUtil.isNotEmpty(map.get("secOver"))){
        	model.addAttribute("secOver",map.get("secOver"));
        	//逾期资产
        	if(StringUtil.isNotEmpty(map.get("childPortfolioInfo"))){
        		try {
					childPortfolioInfo =JsonUtils.json2List((String)map.get("childPortfolioInfo"),FinAssetIntInfo.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}else{
        		Map<String,Object>orgMap = JsonUtils.json2Object((String)map.get("preMap"),Map.class);
                Map<String,BigDecimal>preMap = new HashMap<>();
                for(String key : orgMap.keySet()){
                    preMap.put(key, StringUtil.isNotEmpty(orgMap.get(key))?new BigDecimal(orgMap.get(key).toString()):BigDecimal.ZERO);
                }
            	childPortfolioInfo =  autoPeriodSecOverCommonService.distributeDataByPercent(preMap,map,childPortfolioInfo);
        	}
        	model.addAttribute("CHANNEL",map.get("CHANNEL"));//是否通道
        	model.addAttribute("COLLECT_TYPE",map.get("COLLECT_TYPE"));//通道性质
            model.addAttribute("finprodType2",map.get("finprodType2"));//资产类型
            model.addAttribute("COLLECT_TYPE_01", DictCodeListExt2.收取方式_回款前扣除.getValue());
            model.addAttribute("IS_CAL_LOADRATE", CacheUtil.getSysParam(BussinessConstant.IS_CAL_LOADRATE));
            model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            model.addAttribute("FINPROD_TYPE_TG_F07", DictCodeListExt1.金融产品类型_存款.getValue());
            model.addAttribute("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
            model.addAttribute("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
        }else{
        	childPortfolioInfo =  commonService.distributeDataByPercent(map);
        }
        model.addAttribute("childPortfolioInfo", JsonUtil.objToString(childPortfolioInfo));
        model.addAttribute("portfolioId",map.get("portfolioId"));
        return "invest/period/trdplancurrduration/ChildPortfolioInfo";
    }
}
