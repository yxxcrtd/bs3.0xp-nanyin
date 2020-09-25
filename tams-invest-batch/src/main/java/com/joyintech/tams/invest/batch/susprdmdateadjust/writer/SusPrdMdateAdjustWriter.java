/*
 * 文件名：ResetBaseRuleInfoWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.susprdmdateadjust.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseWriter;
import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dao.SusPrdMdateAdjustMapper;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.InvestmentCycleEntityDto2;
import com.joyintech.tams.invest.batch.susprdmdateadjust.dto.SusPrdMdateAdjustDto;

/**
 * 对应 写入处理
 * @version 1.0 2019年9月24日
 * @since 1.0
 */
@Service
@Scope("prototype")
public class SusPrdMdateAdjustWriter extends BaseWriter<NeedToWriteEntity>{

    @Autowired
    private SusPrdMdateAdjustMapper susPrdMdateAdjustMapper;
    
    @Override
    public void write(List<? extends NeedToWriteEntity> items) throws Exception {
    	
    	@SuppressWarnings("unchecked")
		List<InvestmentCycleEntityDto2> investmentCycleEntityDto2List = (List<InvestmentCycleEntityDto2>)items; // 已按照投资周期分组
        
        Map<String,Object> paraMap = new HashMap<String,Object>();
        
        if (!StringUtil.isEmptyList(investmentCycleEntityDto2List)) {
        	
            for (InvestmentCycleEntityDto2 investmentCycleEntityDto2 : investmentCycleEntityDto2List) {
            	
            	paraMap.put("investmentCycle", investmentCycleEntityDto2.getInvestmentCycleEntityDto().getInvestmentCycle());
            	
            	List<SusPrdMdateAdjustDto> spmadList = investmentCycleEntityDto2.getInvestmentCycleEntityDto().getSusprdmdateadjustdtoList();
            	
            	paraMap.put("spmadList", spmadList);
            	
            	susPrdMdateAdjustMapper.batchUpdate(paraMap);
            }
        }
    }

}
