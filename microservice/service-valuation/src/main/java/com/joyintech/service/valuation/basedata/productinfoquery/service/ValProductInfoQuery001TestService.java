/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.basedata.productinfoquery.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.productinfoquery.dao.ValProductInfoQuery001Mapper;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoAcctDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoFeeDto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001Dto;
import com.joyintech.service.valuation.basedata.productinfoquery.dto.ValProductInfoQuery001TestDto;
import com.joyintech.tams.common.base.entity.BokBooksetExt;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service(value="serviceValProductInfoQuery001TestService")
public class ValProductInfoQuery001TestService extends CommonExportService<ValProductInfoQuery001TestDto> {
	
    /**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ValProductInfoQuery001Mapper mapper;
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValProductInfoQuery001TestDto> findPage(Map<String, Object> params) throws Exception {
        return super.queryForPage(()->{
			try {
				this.findAll(params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodId(Map<String,Object> map) {
       
        return mapper.getFinprodId(map);
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ValProductInfoQuery001Dto queryProductByfinprodId(Map<String, Object> map) {
        String finprodId = (String)map.get("finprodId");
        String[] finprodIdArr = null;
        if(StringUtil.isNotEmpty(finprodId)){
            finprodIdArr = finprodId.split(",");
        }
        map.put("finprodIdArr", finprodIdArr);
        List<ValProductInfoQuery001Dto> list=mapper.queryPageLists(map);
        ValProductInfoQuery001Dto dto=new ValProductInfoQuery001Dto();
        if(list.size()>0){
            dto=list.get(0);
        }
        return dto;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoFeeDto> queryFeeList(Map<String, Object> pageParam) {
        return mapper.queryFeeList(pageParam);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @SuppressWarnings("static-access")
	public List<ValProductInfoQuery001TestDto> findAll(Map<String, Object> params) throws Exception {
//  	  String  prodId=(String)params.get("finprodId");
//  	  String sDate=(String)params.get("vdateStart");
//  	  String eDate=(String)params.get("vdateEnd");
//  	  SimpleDateFormat  sim=new SimpleDateFormat("yyyy-MM-dd");
//  	  Date startDate=new Date();
//  	  Date endDate=new Date();
//	try {
//		startDate = sim.parse(sDate);
//	    endDate=sim.parse(eDate);
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  	  
//  	  ValProductInfoQuery001TestDto  valDto=new ValProductInfoQuery001TestDto();
//  	  List<ValProductInfoQuery001TestDto>  list=new ArrayList<>();
//  	  //根据产品id判断
//  	  if(prodId.startsWith("DK")){
//  		  //净值型(表二)
//  		  Map<String,Object>  mapzjTb2=targetIndexService.printTargetNetTypeTbTwo(startDate, endDate, prodId,null);
//  		  //ResultOneDto resultOneDto=targetIndexService.printTargetNetTypeTbTwo(startDate, endDate, prodId);
//  		  //加权平均产品份额本期利润
//  		  valDto.setMavalue((BigDecimal)mapzjTb2.get("mavalue"));
//  		  //产品份额累计净值增长率
//  		  valDto.setProductShareValue((BigDecimal)mapzjTb2.get("productShareValue"));
//  		  //净值型(表三)
//  		  List<Map<String,Object>>  listTb3=targetIndexService.printTargetNetTypeTbThree(startDate, endDate, prodId,null);
//  	      //阶段净值增长率
//  		  valDto.setProShareRate((BigDecimal)listTb3.get(0).get("stageRate"));
//  		  //份额净值增长率标准差
//  		  valDto.setProductGrowth((BigDecimal)listTb3.get(0).get("productGrowth"));
//  		  //业绩比较基准收益率
//  		  valDto.setFloatRate((BigDecimal)listTb3.get(0).get("claCompareRule"));
//  		  //业绩比较基准收益率标准差
//  		  valDto.setFloatRateStandard((BigDecimal)listTb3.get(0).get("floatRateStandard"));
//  	  }else{
//  		  //货币性(表二)
//  		 Map<String,Object>  maphbTb2=targetIndexService.printTargetCurrencyTypeTbTwo(startDate, endDate, prodId,null);
//  		   //加权平均单位本期净收益计算
//  		  valDto.setWavlue((BigDecimal)maphbTb2.get("wavlue"));
//  		  //累积净值收益率(跑批出来的结果)
//  		  valDto.setProfit((BigDecimal)maphbTb2.get("profit"));
//  		  //货币性(表三)
//  		  List<Map<String,Object>>  listhb3=targetIndexService.printTargetCurrencyTypeTbThree(startDate, endDate, prodId,null);
//  		   //份额净值收益率->
//  		  valDto.setCurrentValue((BigDecimal)listhb3.get(0).get("currentValue"));
//  		  //收益率标准差
//  		  valDto.setProfitStandard((BigDecimal)listhb3.get(0).get("standard"));
//  		   //业绩比较基准收益率
//  		  valDto.setFloatRate((BigDecimal)listhb3.get(0).get("claCompareRule"));
//  		  //业绩比较基准收益率标准差
//  		  valDto.setFloatRateStandard((BigDecimal)listhb3.get(0).get("floatRateStandard"));
//  	  }
//  	  list.add(valDto);
  	return null;
    	
    	
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public List<ValProductInfoAcctDto> getFinprodAcct(Map<String, Object> map) {
        return mapper.getFinprodAcct(map);
    }
    
    /**
     * 浮动费用查询（原方法把固定费用与浮动管理费放到一起，由于浮动费用不需要合并，所以重写一个方法）
     * @param map
     * @return
     * @see
     * @since 1.0
     */
	public List<ValProductInfoFeeDto> queryfloatFeeList(Map<String, Object> pageParam) {
		return mapper.queryfloatFeeList(pageParam);
	}

	/**
     * 查询账套配置表
     * @param finprodId 金融产品代码
     * @param finprodType 金融产品类型
     * @return
     * @see
     * @since 1.0
     */
	public List<BokBooksetExt> queryBooksetExt(Map<String, Object> map){
        return mapper.queryBooksetExt(map);
    }
}
