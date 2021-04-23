/*
 * 文件名：CSTPSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.cstp.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.bankuplink.cstp.dao.BankUpCstpSelectMapper;
import com.joyintech.service.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;

/**
 * cstp下行交易查询Service
 * @author fanshuanghu
 * @version 1.0 2018年12月3日
 * @see BankUpCstpSelectService
 * @since 1.0
 */
@Service
public class BankUpCstpSelectService extends BaseService<CstpSelectDto>{
    /**
     * 注入Mapper
     */
    @Autowired 
    private BankUpCstpSelectMapper cstpSelectMapper;
    /**
     * 分页查询
     * @param page 页面数
     * @param length 长度
     * @param map 请求参数
     * @return 分页查询结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public Page<CstpSelectDto> findPage(int page,int length,Map<String,Object> map) throws Exception{
    	if(StringUtil.isNotEmpty((String)map.get("finprodType"))){
    		switch ((String)map.get("finprodType")){
    			case "T01": map.replace("finprodType", "BOND");
    				break;
    			case "T02": {
//				    map.replace("finprodType", "REPO");
			    	map.put("marketindicator", "9");
			    	map.remove("finprodType");
			    };
			    break;
				case "T03": 
			 	    {
	    				map.put("marketindicator", "10");
	    				map.remove("finprodType");
					}
					;
					break;
				case "T04": 
                {
                    map.put("marketindicator", "8");
                    map.remove("finprodType");
                }
                ;
                break;
				case "T05": 
                {
                    map.put("marketindicator", "2");
                    map.remove("finprodType");
                }
                ;
                break;
				}
    	}
    	if(StringUtil.isNotEmpty((String)map.get("ps"))){
    	    String psList =null;
    	    switch ((String)map.get("ps")){
    			case "T01": 
    			    psList = "1";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "BOND");
    				break;
    			case "T02": 
    			    psList = "4";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "BOND");
    				break;
    			case "03": 
    			    psList = "4";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "REPO");
					break;
    			case "04": 
    			    psList = "1";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "REPO");
					break;
    			case "05": 
    			    psList = "1";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "ISLD");
                    break;
    			case "06": 
    			    psList = "4";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "ISLD");
                    break;
    			case "01": 
    			    psList = "4";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "IRS");
                	break;
    			case "02": 
    			    psList = "1,B";
    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "IRS");
                	break;
                    }
    	}
    	map.put("TRADE_STATUS", BussinessDictCodeList.交易状态_初始.getValue());
    	map.put("acctType",BussinessDictCodeList.资金账户类型_托管账户.getValue());
        return super.queryForPage(page, length, () -> cstpSelectMapper.findPage(map));
    }
    
    /**
     * 分页查询
     * @param page 页面数
     * @param length 长度
     * @param map 请求参数
     * @return 分页查询结果
     * @throws Exception 
     * @see
     * @since 1.0
     */
    public List<CstpSelectDto> getCstpQryTableList(Map<String,Object> map) throws Exception{
    	if(StringUtil.isNotEmpty((String)map.get("finprodType"))){
    		switch ((String)map.get("finprodType")){
    			case "T01": map.replace("finprodType", "BOND");
    				break;
    			case "T02": {
//				    map.replace("finprodType", "REPO");
			    	map.put("marketindicator", "9");
			    	map.remove("finprodType");
			    };
			    break;
				case "T03": 
			 	    {
	    				map.put("marketindicator", "10");
	    				map.remove("finprodType");
					}
					;
					break;
				case "T04": 
                {
                    map.put("marketindicator", "8");
                    map.remove("finprodType");
                }
                ;
                break;
				case "T05": 
                {
                    map.put("marketindicator", "2");
                    map.remove("finprodType");
                }
                ;
                break;
				}
    	}
    	// modify by etliu 20210322 银行间上下行多组合 start
//    	if(StringUtil.isNotEmpty((String)map.get("ps"))){
//    	    String psList =null;
//    	    switch ((String)map.get("ps")){
//    			case "T01": 
//    			    psList = "1";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "BOND");
//    				break;
//    			case "T02": 
//    			    psList = "4";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "BOND");
//    				break;
//    			case "03": 
//    			    psList = "4";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "REPO");
//					break;
//    			case "04": 
//    			    psList = "1";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "REPO");
//					break;
//    			case "05": 
//    			    psList = "1";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "ISLD");
//                    break;
//    			case "06": 
//    			    psList = "4";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "ISLD");
//                    break;
//    			case "01": 
//    			    psList = "4";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "IRS");
//                	break;
//    			case "02": 
//    			    psList = "1,B";
//    			    map.replace("ps", StringUtil.splitToList(",",psList));map.put("finprodType", "IRS");
//                	break;
//                    }
//    	}
    	if(StringUtil.isNotEmpty((String)map.get("ps"))){
    	    map.replace("ps", StringUtil.splitToList(",", (String)map.get("ps")));
    	}
    	// modify by etliu 20210322 银行间上下行多组合 end
    	map.put("TRADE_STATUS", BussinessDictCodeList.交易状态_初始.getValue());
    	map.put("PARTMATCH", BussinessDictCodeList.匹配状态_部分匹配.getValue());
    	map.put("acctType",BussinessDictCodeList.资金账户类型_托管账户.getValue());
        return cstpSelectMapper.findPage(map);
    }
    /**
     * 获取组合代码下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getPortfolioId(Map<String,Object> map){
        return cstpSelectMapper.getPortfolioId(map);
    }
    /**
     * 获取成交编号下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getExecid(Map<String,Object> map){
        return cstpSelectMapper.getExecid(map);
    }
    /**
     * 获取资产代码下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodId(Map<String,Object> map){
        return cstpSelectMapper.getFinprodId(map);
    }
}
