/*
 * 文件名：ValFinprodTradeQuery001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.interf.base.service.send.action.SendNetValueInterfServices;
import com.joyintech.tams.valuation.common.interf.base.service.send.dto.ValNetValueConfirmDto;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dao.ValNetValueConfirm001Mapper;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valnetvalueconfirm.dto.ValNetValueConfirm001Dto;
import com.joyintech.tams.valuation.trademanage.finprodtradequery.service.ValFinprodTradeQuery001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 
 * 金融产品交易查询Service
 * @author weipeng
 * @version 1.0 2018年5月16日
 * @see ValFinprodTradeQuery001Service
 * @since 1.0
 */
@Service
public class ValNetValueConfirm001Service extends CommonExportService<ValNetValueConfirm001Dto> implements BaseProcessService<ValNetValueConfirm001Dto> {
	
    /**
     * ValProductInfoQuery001Mapper
     */
    @Autowired
    private ValNetValueConfirm001Mapper mapper;
    
    @Autowired
    private SendNetValueInterfServices sendNetValueInterfServices;
    
    /**
     * 日志
     */
//     private static Logger log = LoggerFactory.getLogger(ValNetValueConfirm001Service.class);
    
    /**
     * 根据条件获取表格信息
     * 
     * @param params 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValNetValueConfirm001Dto> findPage(Map<String, Object> params) throws Exception {
        //获取获取理财产品类型
//        String profitType = (String)params.get("profitType");
//        String finprodId = (String)params.get("finprodId");
//        String checkResult = (String)params.get("checkResult");
//        String checkStatus = (String)params.get("checkStatus");
//        String confirmStatus = (String)params.get("confirmStatus");
//        String noticeStatus = (String)params.get("noticeStatus");
//        String[] profitTypeArr = null;
//        if(StringUtil.isNotEmpty(profitType)){
//            profitTypeArr = profitType.split(",");
//        }
//        String[] finprodIdArr = null;
//        if(StringUtil.isNotEmpty(finprodId)){
//            finprodIdArr = finprodId.split(",");
//        }
//        String[] checkResultArr = null;
//        if(StringUtil.isNotEmpty(checkResult)){
//            checkResultArr = checkResult.split(",");
//        }
//        String[] checkStatusArr = null;
//        if(StringUtil.isNotEmpty(checkStatus)){
//            checkStatusArr = checkStatus.split(",");
//        }
//        String[] confirmStatustArr = null;
//        if(StringUtil.isNotEmpty(confirmStatus)){
//            confirmStatustArr = confirmStatus.split(",");
//        }
//        String[] noticeStatusArr = null;
//        if(StringUtil.isNotEmpty(noticeStatus)){
//            noticeStatusArr = noticeStatus.split(",");
//        }
//        params.put("profitTypeArr", profitTypeArr);
//        params.put("finprodIdArr", finprodIdArr);
//        params.put("checkResultArr", checkResultArr);
//        params.put("checkStatusArr", checkStatusArr);
//        params.put("confirmStatustArr", confirmStatustArr);
//        params.put("noticeStatusArr", noticeStatusArr);
//        
//        //用于流程-我的待办-点击查看
//        params.put("finprodIdForFlow", (String)params.get("finprodIdForFlow"));
//        params.put("valDateForFlow", (String)params.get("valDateForFlow"));
//        params.put("valBalFlagCodeForFlow", (String)params.get("valBalFlagCodeForFlow"));
//        
//        return super.queryForPage(()-> mapper.queryPageList(params));
    	
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetValueConfirm001/findPage",
                params, ValNetValueConfirm001Dto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String,Object> map) {
//        return super.queryForPageForSelectReturn(()->mapper.getFinprodId(map));
    	return restTemplate.postForPage(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetValueConfirm001/getFinprodId",
                map, SelectOptionDto.class);
    }

    /**
     * 根据产品findprodid获取产品详细信息
     * <功能详细描述>
     * @param map
     * @see
     * @since 1.0
     */
    public ValNetValueConfirm001Dto queryProductByfinprodId(Map<String, Object> map) {
        return mapper.queryProductByfinprodId(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<ValNetValueConfirm001Dto> queryPageList(Map<String, Object> params) {
      //获取获取理财产品类型
//    	String profitType = (String)params.get("profitType");
//        String finprodId = (String)params.get("finprodId");
//        String checkResult = (String)params.get("checkResult");
//        String checkStatus = (String)params.get("checkStatus");
//        String confirmStatus = (String)params.get("confirmStatus");
//        String noticeStatus = (String)params.get("noticeStatus");
//        String[] profitTypeArr = null;
//        if(StringUtil.isNotEmpty(profitType)){
//            profitTypeArr = profitType.split(",");
//        }
//        String[] finprodIdArr = null;
//        if(StringUtil.isNotEmpty(finprodId)){
//            finprodIdArr = finprodId.split(",");
//        }
//        String[] checkResultArr = null;
//        if(StringUtil.isNotEmpty(checkResult)){
//            checkResultArr = checkResult.split(",");
//        }
//        String[] checkStatusArr = null;
//        if(StringUtil.isNotEmpty(checkStatus)){
//            checkStatusArr = checkStatus.split(",");
//        }
//        String[] confirmStatustArr = null;
//        if(StringUtil.isNotEmpty(confirmStatus)){
//            confirmStatustArr = confirmStatus.split(",");
//        }
//        String[] noticeStatusArr = null;
//        if(StringUtil.isNotEmpty(noticeStatus)){
//            noticeStatusArr = noticeStatus.split(",");
//        }
//        params.put("profitTypeArr", profitTypeArr);
//        params.put("finprodIdArr", finprodIdArr);
//        params.put("checkResultArr", checkResultArr);
//        params.put("checkStatusArr", checkStatusArr);
//        params.put("confirmStatustArr", confirmStatustArr);
//        params.put("noticeStatusArr", noticeStatusArr);
//        return mapper.queryPageList(params);
    	return restTemplate.postForList(
                ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetValueConfirm001/queryPageList",
                params, ValNetValueConfirm001Dto.class);
    }

//    /**
//     * <一句话功能简述>
//     * <功能详细描述>
//     * @param map
//     * @see
//     * @since 1.0
//     */
//    public int updateConfirmStatus(List<ValNetValueConfirm001Dto> listdto) {
//        int counts=0;
//        for(ValNetValueConfirm001Dto dto:listdto){
//            counts=counts+mapper.updateConfirmStatus(dto);
//        }
//        return counts;
//    }

    /**
     * 估值表余额表状态表 - 确认状态和下发状态更新
     * {@inheritDoc}
     */
    @Override
    public void doComplete(ValNetValueConfirm001Dto entity, OperationType type){
        if(entity!=null){
        	entity.setUpdateTime(new  Date());
            //估值表余额表状态表 - 确认状态更新
            if("QR".equals(entity.getConfirmOrNotice())){
                 //mapper.updateConfirmStatus(entity);
                /**modify by yanya on 20191031 修改为调用微服务格式**/
                restTemplate.postForObject(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetValueConfirm001/updateNoticeStatus",
                    entity, Integer.class);
            }else{
            	
        		//估值表余额表状态表 - 下发状态更新	
				//mapper.updateNoticeStatus(entity);
                /**modify by yanya on 20191031 修改为调用微服务格式**/
                restTemplate.postForObject(
                    ServiceApplication.VALUATION_SERVICE_NAME + "/ValNetValueConfirm001/updateNoticeStatus",
                    entity, Integer.class);
                
                List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
				Map<String,String> mapInfo =new HashMap<>();
				mapInfo.put("finprodId", entity.getFinprodId());
				mapInfo.put("valDate", entity.getValDate());
				mapInfo.put("valbalFlagCode", entity.getValBalFlagCode());
				listMap.add(mapInfo);
                //调用下发接口--暂时注释，一体化代码转化不到这里
//            	restTemplate.postForObject(
//                        ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendProdNetValueAlerttoSale", listMap,
//                        Map.class);
				
            	
//    			String bankName = CacheUtil.getSysParam("NET_SEND");
//        		if("PABANK".equals(bankName)){
//        			
//        			//组装页面信息
//        			ValNetValueConfirmDto dto = new ValNetValueConfirmDto();
//        			BeanUtil.copyPropertiesIgnoreNull(dto, entity);
//        			
//	    			//调用净值下发接口
//					  int flag  = sendNetValueInterfServices.execute(dto);
//					  if(flag==0)
//						  //回滚当前事务
//						  TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//	        	}
//        		
//        		if("001".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
//        			if(StringUtil.equals(DictCodeListExt1.下发状态_下发成功.getValue(),entity.getNoticeStatusCode())){
//    				    //组装页面信息
//    	                ValNetValueConfirmDto dto = new ValNetValueConfirmDto();
//    	                BeanUtil.copyPropertiesIgnoreNull(dto, entity);
//    	                
//    	                //调用净值下发接口
//    	                restTemplate.postForObject(
//    	                    ServiceApplication.CUSTOMER_INTERF_NAME + "/interf/FinProductInterf/sendProdNetValuetoSale",
//    	                    dto, Integer.class);
//    				}
//        		}
        		
            }        
        }
    } 
    
}
