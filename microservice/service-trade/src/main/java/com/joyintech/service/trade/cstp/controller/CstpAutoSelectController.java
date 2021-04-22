/*
 * 文件名：CstpAutoSelectController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoxue
 * 修改时间：2020年01月09日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.cstp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.cstp.service.CstpAutoSelectService;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;

/**
 * CSTP下行交易自动匹配
 * @author caoxue
 * @version 1.0 2020年01月09日
 * @see CstpAutoSelectController
 * @since 1.0
 */
@RestController
@RequestMapping("${service}/CstpAutoSelect")
public class CstpAutoSelectController {
    /**
     * 注入Service
     */
    @Autowired
    private CstpAutoSelectService cstpAutoSelectService;
	/**
     * CSTP债券交易匹配
     * 交易预录入与CSTP下行交易1VS1;指令与CSTP下行交易 1VSN,NVS1
     * 债券下行交易按交易日期、资产代码、交易场所、交易方向、清算速度、证券账户、净价/到期收益率筛选满足条件的委托；
     * 其中交易日期需要在委托的有效区间内；
     * 净价/到期收益率需满足委托的限价要求；
     * 证券账户需与委托所选的相同，如委托未选，证券账户需与委托中的组合相关联；
     * 其他要素（资产代码、资产二级分类、交易场所、交易方向、清算速度）需要严格相等
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
     * CSTP回购交易匹配
     * 回购下行数据按交易日期、回购期限、交易场所、交易方向、清算速度、证券账户、利率筛选符合条件的委托；
     * 其中交易日期需要在委托的有效区间内；
     * 利率需满足委托的限价要求；
     * 证券账户需与委托所选的相同，如委托未选，证券账户需与委托中的组合相关联；
     * 其他要素（回购期限、交易场所交易方向、清算速度）需要严格相等
     * @param map 查询
     * @return json格式字符{"recvcode":"0","recvmsg":"成功或失败明细"}
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("matchSecAndRepoTrade")
    public String matchSecAndRepoTrade(@RequestBody Map<String,Object> map) throws Exception{
    	HashMap<String, String> jsonMap=new HashMap<String, String>();
    	//初始化返回值
    	jsonMap.put("recvcode", "0");
    	jsonMap.put("recvmsg", "CSTP债券交易匹配结束");
    	//下行交易：交易编号
    	Object execID=map.get("execID");
    	if(!StringUtil.isNotEmpty(execID)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "execID不能为空");
        	return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：交易日期 格式：yyyy-MM-dd
    	Object tradeDate = map.get("tradeDate");
    	if(!StringUtil.isNotEmpty(tradeDate)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "tradeDate不能为空");
        	return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：资产类型 F01:债券；F07:回购
    	Object finprodType2 = map.get("finprodType2");
    	if(!StringUtil.isNotEmpty(finprodType2)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "finprodType2不能为空");
        	return JSONObject.toJSONString(jsonMap);
    	}
    	if("F01".equals(finprodType2)){
        	//下行交易：资产代码
    		Object securityID = map.get("securityID");
    		if(!StringUtil.isNotEmpty(securityID)){
            	jsonMap.put("recvcode", "1");
            	jsonMap.put("recvmsg", "securityID不能为空");
            	return JSONObject.toJSONString(jsonMap);
    		}
        	//下行交易：净价
    		Object price = map.get("price");
        	if(!StringUtil.isNotEmpty(price)){
            	jsonMap.put("recvcode", "1");
            	jsonMap.put("recvmsg", "price不能为空");
                return JSONObject.toJSONString(jsonMap);
        	}
        	//下行交易：到期收益率
        	Object yield2 = map.get("yield2");
        	if(!StringUtil.isNotEmpty(yield2)){
            	jsonMap.put("recvcode", "1");
            	jsonMap.put("recvmsg", "yield2不能为空");
                return JSONObject.toJSONString(jsonMap);
        	}
        	//下行交易：行权收益率
//        	String strikeyield = map.get("strikeyield").toString();
//        	if(StringUtil.isEmpty(strikeyield)){
//            	jsonMap.put("recvcode", "1");
//            	jsonMap.put("recvmsg", "strikeyield不能为空");
//        	}
    	}
    	if("F07".equals(finprodType2)){
        	//下行交易：回购期限
    		Object repoTerm = map.get("repoTerm");
        	if(!StringUtil.isNotEmpty(repoTerm)){
            	jsonMap.put("recvcode", "1");
            	jsonMap.put("recvmsg", "repoTerm不能为空");
            	return JSONObject.toJSONString(jsonMap);
        	}
        	//下行交易：回购利率
        	Object price = map.get("price");
        	if(!StringUtil.isNotEmpty(price)){
            	jsonMap.put("recvcode", "1");
            	jsonMap.put("recvmsg", "price不能为空");
                return JSONObject.toJSONString(jsonMap);
        	}
    	}
    	//下行交易：交易场所 默认为01
    	Object tradeMarket = map.get("tradeMarket");
    	if(!StringUtil.isNotEmpty(tradeMarket)){
    		jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "tradeMarket不能为空");
            return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：交易方向 T01,T02,03,04
    	Object side = map.get("side");
    	if(!StringUtil.isNotEmpty(side)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "side不能为空");
            return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：清算速度 0:T+0,1:T+1
    	Object settleSpeed = map.get("settleSpeed");
    	if(!StringUtil.isNotEmpty(settleSpeed)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "settleSpeed不能为空");
            return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：证券账户
    	Object ourdepositaccountnumber = map.get("ourdepositaccountnumber");
    	if(!StringUtil.isNotEmpty(ourdepositaccountnumber)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "ourdepositaccountnumber不能为空");
            return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：券面总额
    	Object lastqty = map.get("lastqty");
    	if(!StringUtil.isNotEmpty(lastqty)){
        	jsonMap.put("recvcode", "1");
        	jsonMap.put("recvmsg", "lastqty不能为空");
            return JSONObject.toJSONString(jsonMap);
    	}
    	//下行交易：交易员
    	Object traderName = map.get("traderName");
    	if(!StringUtil.isNotEmpty(traderName)){
    		jsonMap.put("recvcode", "1");
    		jsonMap.put("recvmsg", "traderName不能为空");
    		return JSONObject.toJSONString(jsonMap);
    	}
    	
    	//优先匹配交易预录入
    	//查询预录入交易匹配结果列表
    	List<TrdMatchInfo> pre_list=cstpAutoSelectService.selectTrdProductDealPre(map);
    	if(!StringUtil.isEmptyList(pre_list)){//预录入匹配成功
    		//插入匹配结果
    		cstpAutoSelectService.insertSelective(pre_list.get(0));
        	jsonMap.put("recvmsg", "成功匹配1条预录入交易");
    	}else{//匹配委托
    		int match_count=cstpAutoSelectService.matchEntrust(map);
        	jsonMap.put("recvmsg", "成功匹配"+match_count+"条委托交易");
    	}
    	
        return JSONObject.toJSONString(jsonMap);
    }
    
}
