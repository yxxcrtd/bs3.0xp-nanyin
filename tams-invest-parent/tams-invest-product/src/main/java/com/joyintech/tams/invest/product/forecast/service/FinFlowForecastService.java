/*
 * 文件名：MaintainProductService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2019年7月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.forecast.service;

  
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastConfig;
import com.joyintech.tams.invest.product.forecast.dto.FinFlowForecastDealAmontDto;


 
/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2019年7月31日
 * @see FinFlowForecastService
 * @since 1.0
 */
@Service
public class FinFlowForecastService extends BaseService<FinFlowForecastConfig>{
    

    
    /**
     * 获取流动性储备的标题
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public FinFlowForecastConfig getTargetNameOfReserve() {
        FinFlowForecastConfig flowForecastConfig = new FinFlowForecastConfig();
        flowForecastConfig.setTargetName1(DictCodeListExt2.流动性预测维护_指标种类_日日升.getCodeName());
        flowForecastConfig.setTargetName2(DictCodeListExt2.流动性预测维护_指标种类_月月升.getCodeName());
        flowForecastConfig.setTargetName3(DictCodeListExt2.流动性预测维护_指标种类_鑫鑫向荣.getCodeName());
        flowForecastConfig.setTargetName4(DictCodeListExt2.流动性预测维护_指标种类_鑫鑫向荣B.getCodeName());
        flowForecastConfig.setTargetName5(DictCodeListExt2.流动性预测维护_指标种类_理财宝.getCodeName());
        flowForecastConfig.setTargetName6("定开净值型产品");
        return flowForecastConfig;
    }
    
    /**
     * 流动性储备信息
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinFlowForecastConfig> findReserveDetail(Map<String, Object> params) throws Exception {
        params.put("flowType", DictCodeListExt2.流动性预测维护_流动性种类_流动性储备.getValue());
        params.put("depType", DictCodeListExt2.流动性预测维护_部门类型_产品管理处.getValue());
        return restTemplate.postForPage(
        		 ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecast/findReserveDetail",
            params, FinFlowForecastConfig.class); 
    }
    
    /**
     * 
     * 流动性预测信息
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinFlowForecastConfig> findForecast(Map<String, Object> params) throws Exception {
        String depType = (String)params.get("depType");
        params.put("flowType", DictCodeListExt2.流动性预测维护_流动性种类_流动性预测.getValue());
        params.put("targetType", DictCode.流动性预测维护_指标种类.getValue());
        List<String> itemCodeList = new ArrayList<String>();
        if(StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_产品管理处.getValue(), depType)) {
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_日日升.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_月月升.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_鑫鑫向荣.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_鑫鑫向荣B.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_理财宝.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_净值型产品开放期净申赎.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_个人期次型产品.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_净值型产品_新发.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_机构期次型产品.getValue());
            params.put("itemCodeList", itemCodeList); 
            params = setDateSlotparams(params);
        } else if(StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_投融资处.getValue(), depType)){
        	 
        	itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_非标资产.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_非标资产_结构化团队.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_非标资产_资本市场团队.getValue());
             
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_投融资处.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_投融资处_结构化团队.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_投融资处_资本市场团队.getValue());
            params.put("itemCodeList", itemCodeList); 
        } else if(StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_固收处.getValue(), depType)){
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_新增走款安排_固收处.getValue());
            params.put("itemCodeList", itemCodeList);
        } else if(StringUtil.equals(DictCodeListExt2.流动性预测维护_部门类型_组合投资处.getValue(), depType)){
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_新增走款安排_组合投资处.getValue());
            itemCodeList.add(DictCodeListExt2.流动性预测维护_指标种类_资产到期回款_其他.getValue());
            params.put("itemCodeList", itemCodeList);
        }
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecast/findForecast",
            params, FinFlowForecastConfig.class);   
    }
    /**
     * 
     * 净值型产品开放期净申赎预测
     * <功能详细描述>
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<FinFlowForecastDealAmontDto> findNetValueOpenforecast(Map<String, Object> params) throws Exception {

        params = setDateSlotparams(params);
        return restTemplate.postForPage(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecast/findNetValueOpenforecast",
            params, FinFlowForecastDealAmontDto.class);           
    }
    /**
     * 保存
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public String save(FinFlowForecastConfig dto) {
        return restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecast/save",
            dto, String.class);           
    }
    
    
    /**
     * 获取查询日后的预测6个时间段
     * @param date
     * @return FlowForecastModel
     */
    public FinFlowForecastConfig dateTransfore(Date date,FinFlowForecastConfig model){
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,6);
        Date date1 = cal.getTime();      
        model.setTargetName1(sdf.format(date)+"-"+sdf.format(date1));
        
        
        cal.add(Calendar.DATE,1);
        Date date2 = cal.getTime();
        cal.add(Calendar.DATE,6);
        Date date3 = cal.getTime();
        model.setTargetName2(sdf.format(date2)+"-"+sdf.format(date3));
        
        cal.add(Calendar.DATE,1);
        Date date4 = cal.getTime();
        cal.add(Calendar.DATE,6);
        Date date5 = cal.getTime();
        model.setTargetName3(sdf.format(date4)+"-"+sdf.format(date5));
        
        cal.add(Calendar.DATE,1);
        Date date6 = cal.getTime();
        Date date7 = timeJudge(date6);
        model.setTargetName4(sdf.format(date6)+"-"+sdf.format(date7));
        
        cal.setTime(date7);
        cal.add(Calendar.DATE,1);
        Date date8 = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date9 = cal.getTime();
        model.setTargetName5(sdf.format(date8)+"-"+sdf.format(date9));
        
        cal.add(Calendar.DATE,1);
        Date date10 = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date11 = cal.getTime();
        model.setTargetName6(sdf.format(date10)+"-"+sdf.format(date11));
        return model; 
    }
    
    private Date timeJudge(Date date){
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          cal.add(Calendar.DATE,4);
          Date time1 = cal.getTime();
          cal.setTime(date);
          cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
          Date time2 = cal.getTime();
        if(time1.after(time2)){
          cal.setTime(date);
          cal.add(Calendar.MONTH,1);
          cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
          Date date2 = cal.getTime();
          return date2;
        }else{
          Date date2 = time2;
          return date2;
          }
        }
  
 
 
    
    private Map<String, Object> setDateSlotparams(Map<String, Object> params) throws Exception{
        params.put("PS_C2", DictCodeList.交易方向_产品申购.getValue());
        params.put("PS_C4", DictCodeList.交易方向_产品赎回.getValue());
        params.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        params.put("ISSUE_STATUS_01", DictCodeListExt1.发行状态_发行成功.getValue());
        
        //获取时间段

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = (String)params.get("queryDate");
        Date date = sdf.parse(dateString);
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(date);
        cal.add(Calendar.DATE,6);
        Date date1 = cal.getTime();
        params.put("timeonestart", sdf.format(date));
        params.put("timeoneend",  sdf.format(date1));
        
        cal.add(Calendar.DATE,1);
        Date date2 = cal.getTime();
        cal.add(Calendar.DATE,6);
        Date date3 = cal.getTime();
        params.put("timetwostart",  sdf.format(date2));
        params.put("timetwoend",  sdf.format(date3));
        
        cal.add(Calendar.DATE,1);
        Date date4 = cal.getTime();
        cal.add(Calendar.DATE,6);
        Date date5 = cal.getTime();
        params.put("timethreestart", sdf.format(date4));
        params.put("timethreeend",  sdf.format(date5));
        
        cal.add(Calendar.DATE,1);
        Date date6 = cal.getTime();
        Date date7 = timeJudge(date6);
        params.put("timefourstart", sdf.format(date6));
        params.put("timefourend",  sdf.format(date7));
        
        cal.setTime(date7);
        cal.add(Calendar.DATE,1);
        Date date8 = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date9 = cal.getTime();
        params.put("timefivestart", sdf.format(date8));
        params.put("timefiveend",  sdf.format(date9));
        
        cal.add(Calendar.DATE,1);
        Date date10 = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date11 = cal.getTime();
        params.put("timesixstart",  sdf.format(date10));
        params.put("timesixend",  sdf.format(date11));
        return params;
    }
    
    /**
     * 查询产品申赎数据
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    public Page<FinFlowForecastDealAmontDto> findMaintainProductRedemptionData(Map<String, Object> params) {
        params.put("PS_C2", DictCodeList.交易方向_产品申购.getValue());
        params.put("PS_C4", DictCodeList.交易方向_产品赎回.getValue());
        params.put("PS_C1", DictCodeList.交易方向_产品认购.getValue());
        return restTemplate.postForPage(
        		ServiceApplication.PRODUCT_SERVICE_NAME + "/FlowForecast/findMaintainProductRedemptionData",
            params, FinFlowForecastDealAmontDto.class);
    }
}
