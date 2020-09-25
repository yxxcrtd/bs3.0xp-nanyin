/*
 * 文件名：CstpAutoSelectService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoxue
 * 修改时间：2020年01月09日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.cstp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.util.DateUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.cstp.dao.CstpAutoSelectMapper;
import com.joyintech.service.trade.cstp.dto.CstpSelectDto;
import com.joyintech.service.trade.investtrade.commandreceive.dto.TrdCommandReceive001Dto;
import com.joyintech.tams.common.base.dao.TrdMatchInfoMapper;
import com.joyintech.tams.common.base.entity.TrdMatchInfo;
import com.joyintech.tams.constant.DictCodeList;

/**
 * cstp下行交易自动匹配Service
 * @author caoxue
 * @version 1.0 2020年01月09日
 * @see CstpAutoSelectService
 * @since 1.0
 */
@Service
public class CstpAutoSelectService extends BaseService<CstpSelectDto>{
    /**
     * 注入Mapper
     */
    @Autowired 
    private CstpAutoSelectMapper cstpAutoSelectMapper;
    /**
     * 注入Mapper
     */
    @Autowired 
    private TrdMatchInfoMapper trdMatchInfoMapper;
    
    /**
     * 查询预录入交易匹配结果列表
     * @param map
     * @return
     */
    public List<TrdMatchInfo> selectTrdProductDealPre(Map<String, Object> map){
    	return cstpAutoSelectMapper.selectTrdProductDealPre(map);
    }

    public int insertSelective(TrdMatchInfo record){
    	record.setSeqno(IDGenerator.generate());
    	return trdMatchInfoMapper.insertSelective(record);
    }
    
    /**
     * 查询已下达的委托指令并匹配
     * 一笔下行交易可匹配多笔委托指令
     * @param map
     * @return
     */
    public int matchEntrust(Map<String, Object> map){
    	map.put("cstpType","cstp");
    	map.put("entrustReceive", DictCodeList.委托状态_已接收.getValue());
    	map.put("entrustCompleted", DictCodeList.委托状态_已完成.getValue());
    	map.put("entrustClose", DictCodeList.委托状态_已关闭.getValue());
    	//按时间顺序查询出可匹配的委托指令
    	List<TrdCommandReceive001Dto> etr_list=cstpAutoSelectMapper.selectEntrust(map);
    	//满足匹配的委托集合
    	List<TrdMatchInfo> match_list=new ArrayList<TrdMatchInfo>();
    	//根据下行交易的券面总额和委托指令对比,直到委托的券面总额大于下行交易的剩余待匹配券面总额结束
    	BigDecimal lastqty=new BigDecimal(map.get("lastqty").toString());//初始下行交易券面总额
    	for(TrdCommandReceive001Dto trdCommandReceive001Dto:etr_list){
    		if("F01".equals(map.get("finprodType2").toString())){
        		//净价/到期收益率筛选满足条件的委托
        		//净价
        		if(!priceAndmatureYield(trdCommandReceive001Dto.getPriceMode(),trdCommandReceive001Dto,trdCommandReceive001Dto.getPs(),"price",new BigDecimal(map.get("price").toString()))){
        			continue;
        		}
        		//到期收益率
        		if(!priceAndmatureYield(trdCommandReceive001Dto.getPriceMode(),trdCommandReceive001Dto,trdCommandReceive001Dto.getPs(),"matureYield",new BigDecimal(map.get("yield2").toString()))){
        			continue;
        		}
        		//行权收益率
        		if(StringUtil.isNotEmpty(map.get("strikeyield"))){
    	    		if(!priceAndmatureYield(trdCommandReceive001Dto.getPriceMode(),trdCommandReceive001Dto,trdCommandReceive001Dto.getPs(),"exerYield",new BigDecimal(map.get("strikeyield").toString()))){
    	    			continue;
    	    		}
        		}
        	}else{
        		//到期收益率
        		if(!priceAndmatureYield(trdCommandReceive001Dto.getPriceMode(),trdCommandReceive001Dto,trdCommandReceive001Dto.getPs(),"zysPrice",new BigDecimal(map.get("price").toString()))){
        			continue;
        		}
        	}
    		//下行交易剩余待匹配券面额度与委托可匹配券面额度对比
    		BigDecimal match_num=new BigDecimal(0);
    		if(lastqty.compareTo(trdCommandReceive001Dto.getProAvailableCash())>0){
    			match_num=trdCommandReceive001Dto.getProAvailableCash();
    			lastqty=lastqty.subtract(trdCommandReceive001Dto.getProAvailableCash());
    		}else{
    			match_num=lastqty;
    			lastqty=new BigDecimal(0);
    		}
    			
			TrdMatchInfo match = new TrdMatchInfo();
			match.setSeqno(IDGenerator.generate());
			match.setCstpTradeId(map.get("execID").toString());
			match.setEntrustId(trdCommandReceive001Dto.getEntrustId());
			match.setMatchNum(match_num);
			match.setMatchType("1");
			match.setTradeDate(DateUtil.convertStr2Date2(map.get("tradeDate").toString()));
			match.setfSysId("01");
			match_list.add(match);
			if(lastqty.compareTo(BigDecimal.ZERO)==0){
				break;
			}
    	}
    	//保存匹配情况
    	for(TrdMatchInfo match:match_list){
    		trdMatchInfoMapper.insertSelective(match);
    	}
    	return match_list.size();
    }
    public boolean priceAndmatureYield(String priceMode, TrdCommandReceive001Dto trdCommandReceive001Dto, String ps, String type, BigDecimal value){
        if("price".equals(type)){//净价
            if(priceMode!=null&&"PRM01".equals(priceMode)){//指定价格的时候
                if(value!=null &&trdCommandReceive001Dto.getPrice()!=null){
                    if(value.compareTo(trdCommandReceive001Dto.getPrice())!=0){//净价不一致
                        return false;
                    }
                }else{
                    return false;
                }
            }else if(priceMode!=null&&"PRM02".equals(priceMode)){//限价指令
                if("T01".equals(ps)){//买入
                    if(trdCommandReceive001Dto.getPrice()!=null&&value!=null){
                        if(trdCommandReceive001Dto.getPrice().compareTo(value)>0){
                            return false;
                        }
                    }else{
                        return false;
                    }

                }else{
                    if(trdCommandReceive001Dto.getPrice()!=null&&value!=null) {
                        if (trdCommandReceive001Dto.getPrice().compareTo(value)<0) {
                            return false;
                        }
                    }else{
                    	return false;
                    }
                }
            }
        }else if("matureYield".equals(type)){//到期收益率
        	if(priceMode!=null&&"PRM01".equals(priceMode)){//指定价格的时候
                if(value!=null &&trdCommandReceive001Dto.getMatureYield()!=null){
                    if(value.compareTo(trdCommandReceive001Dto.getMatureYield())!=0){//净价不一致
                        return false;
                    }
                }else{
                    return false;
                }
            }else if(priceMode!=null&&"PRM02".equals(priceMode)){//限价指令
                if("T01".equals(ps)){//买入
                    if(trdCommandReceive001Dto.getMatureYield()!=null&&value!=null){
                        if(trdCommandReceive001Dto.getMatureYield().compareTo(value)<0){
                            return false;
                        }
                    }else{
                        return false;
                    }

                }else{
                    if(trdCommandReceive001Dto.getMatureYield()!=null&&value!=null) {
                        if (trdCommandReceive001Dto.getMatureYield().compareTo(value)>0) {
                            return false;
                        }
                    }else{
                    	return false;
                    }
                }
            }
        }else if("exerYield".equals(type)){//行权收益率
        	if(priceMode!=null&&"PRM01".equals(priceMode)){//指定价格的时候
                if(value!=null &&trdCommandReceive001Dto.getExerYield()!=null){
                    if(value.compareTo(trdCommandReceive001Dto.getExerYield())!=0){//净价不一致
                        return false;
                    }
                }else{
                    return false;
                }
            }else if(priceMode!=null&&"PRM02".equals(priceMode)){//限价指令
                if("T01".equals(ps)){//买入
                    if(trdCommandReceive001Dto.getExerYield()!=null&&value!=null){
                        if(trdCommandReceive001Dto.getExerYield().compareTo(value)<0){
                            return false;
                        }
                    }else{
                        return false;
                    }

                }else{
                    if(trdCommandReceive001Dto.getExerYield()!=null&&value!=null) {
                        if (trdCommandReceive001Dto.getExerYield().compareTo(value)>0) {
                            return false;
                        }
                    }else{
                    	return false;
                    }
                }
            }
        }else if("zysPrice".equals(type)){//质押式的回购利率
            if(priceMode!=null&&"PRM01".equals(priceMode)){//指定价格的时候
            	if(value!=null &&trdCommandReceive001Dto.getPrice()!=null){
                    if(value.compareTo(trdCommandReceive001Dto.getPrice())!=0){//回购利率不一致
                        return false;
                    }
                }else{
                    return false;
                }
            }else if(priceMode!=null&&"PRM02".equals(priceMode)) {//限价指令
                if("03".equals(ps)){//正回购
                	if(value!=null &&trdCommandReceive001Dto.getPrice()!=null){
                        if(trdCommandReceive001Dto.getPrice().compareTo(value)>0){
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                	if(value!=null &&trdCommandReceive001Dto.getPrice()!=null){
                        if(trdCommandReceive001Dto.getPrice().compareTo(value)>0){
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
