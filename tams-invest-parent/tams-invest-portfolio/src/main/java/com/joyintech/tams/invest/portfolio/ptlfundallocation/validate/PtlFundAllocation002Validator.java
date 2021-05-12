/*
 * 文件名：PtlFundAllocation001Validator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年2月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlfundallocation.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.joyintech.tams.component.business.trade.BussTrdTransferOrderService;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocation001Dto;
import com.joyintech.tams.invest.portfolio.ptlfundallocation.dto.PtlFundAllocation002Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;

/**
 * 资金划拨检测类
 * @author fanshuanghu
 * @version 1.0 2019年2月18日
 * @see PtlFundAllocation002Validator
 * @since 1.0
 */
@Transactional
@Component
public class PtlFundAllocation002Validator implements BeanValidator{
//    @Autowired
//    private PtlFundAllocationMapper ptlFundAllocationMapper;
    
    @Autowired
    private BussTrdTransferOrderService bussTrdTransferOrderService;
    
    public List<ValidMessage> valid(Object value){
        ArrayList<ValidMessage> errorList = new ArrayList<ValidMessage>();// 生成错误集合
        PtlFundAllocation002Dto dto = (PtlFundAllocation002Dto)value;
        LinkedHashMap<String,List<Integer>> portfolioIdMap = new LinkedHashMap<String,List<Integer>>();//用来组合代码去重 wyd 20210331
        
        if(StringUtil.equals(dto.getOperateType(), "submit") || StringUtil.equals(dto.getOperateType(), "save")){
            if(!StringUtil.isEmptyList(dto.getPtlFundAllocation001Dtos())){
                for (int i = 0; i < dto.getPtlFundAllocation001Dtos().size(); i++ ) {
                	//校验逻辑修改，同时增加账户、余额的校验  wyd 20210331 start
                	PtlFundAllocation001Dto tempDto=dto.getPtlFundAllocation001Dtos().get(i);
                	if(tempDto==null){
                		errorList.add(new ValidMessage("明细(第"+(i+1)+"行)数据为空！"));
                		continue;
                	}
                	String portfolioId =tempDto.getPortfolioId();
                	
                	if(StringUtil.isBlank(portfolioId)){
                		errorList.add(new ValidMessage("明细(第"+(i+1)+"行)组合代码为空！"));
                		continue;
                	}
                	
                	if(portfolioIdMap.get(portfolioId)!=null){
                		portfolioIdMap.get(portfolioId).add(i+1);
                	}else{
                		List<Integer> list =  new ArrayList<Integer>();
                		list.add(i+1);
                		portfolioIdMap.put(portfolioId,list);
                	}
                	
                	//账户号和账户余额
                	if(StringUtil.isBlank(tempDto.getAcctNo())||tempDto.getAmount()==null){
                		errorList.add(new ValidMessage("明细(第"+(i+1)+"行)账户信息不全！"));
                	}
                	
                	//划款金额
                    BigDecimal settleAmt=tempDto.getSettleAmt();
                    if (settleAmt==null||settleAmt.equals("")){
                        errorList.add(new ValidMessage("明细(第"+(i+1)+"行)划款金额不能为空！"));
                    }else{
                        if(settleAmt.compareTo(BigDecimal.ZERO)<=0){
                            errorList.add(new ValidMessage("明细(第"+(i+1)+"行)划款金额必须大于0！"));
                        }
                    }
                	//校验逻辑修改，同时增加账户、余额的校验  wyd 20210331 start                    
                }
            	//组合代码重复校验 wyd 20210331
                for(Entry<String, List<Integer>> temp:portfolioIdMap.entrySet()){
                	if(temp.getValue().size()>1){
                		String tempId = temp.getKey();
                		String indexListStr = temp.getValue().toString();
                		errorList.add(new ValidMessage(String.format("明细(第%s行)组合代码重复(%s)！",indexListStr,tempId)));
                	}
                }

            }
//            if(dto.getBussTrdTransferOrder() == null || dto.getBussTrdTransferOrder().size() == 0){
//                errorList.add(new ValidMessage(getMessage("FW_E_0006",getMessage("PtlActivityPeriod001.stlPayOrders"))));
//            }
            // 划款指令
            if (bussTrdTransferOrderService != null) {
                List<ValidMessage> list = bussTrdTransferOrderService.check(dto.getBussTrdTransferOrder());
                errorList.addAll(list);
            }
        }
        return errorList;
    }
}
