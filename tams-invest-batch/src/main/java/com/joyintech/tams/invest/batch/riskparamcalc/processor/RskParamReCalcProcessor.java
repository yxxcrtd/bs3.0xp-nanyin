/*
 * 文件名：RskParamReCalcProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：jjpei
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.invest.batch.riskparamcalc.dao.RskParamReCalcMapper;
import com.joyintech.tams.invest.batch.riskparamcalc.dto.RskParamReCalcReaderDto;
import com.joyintech.tams.invest.batch.riskparamcalc.dto.RskParamReCalcWriterDto;
import com.joyintech.tams.invest.common.riskinfrds.base.service.CalcOccupyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 批处理-指标参数重新更新-Processor
 * 
 * @author jjpei
 * @version 1.0 2020年4月17日
 * @see RskParamReCalcProcessor
 * @since 1.0
 */
@Service
@Scope("prototype")
public class RskParamReCalcProcessor extends BaseProcessor<NeedOperateEntity, RskParamReCalcWriterDto> {

    /**
     * 预占信息计算Service
     */
    @Autowired
    private CalcOccupyInfoService calcService;
    
    @Autowired
    private RskParamReCalcMapper rskParamReCalcMapper;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public RskParamReCalcWriterDto process(NeedOperateEntity arg0)
        throws Exception {
        RskParamReCalcReaderDto dto = (RskParamReCalcReaderDto)arg0;
        // 取得实占信息数据
        List<Map<String, Object>> dataMapList = dto.getDataMapList();
        if (StringUtil.isEmptyList(dataMapList)) {
        	return new RskParamReCalcWriterDto();
        } 
        RskParamReCalcWriterDto resDto = new RskParamReCalcWriterDto();
        List<RskIndicatorParaCalcdetail> details = new ArrayList<>();
        String sysDate = DateUtil.format((Date) getParams().get("sysDate"), DateUtil.DEFAULT_OTHER_FORMAT); 
        dataMapList.sort((r1,r2)->DateUtil.compareDate((Date)r1.get("eventDate"), (Date)r2.get("eventDate")));
        dataMapList.forEach(dataMap -> {
            Map<String, Object> response = new HashMap<>();
            dataMap.put("sysDate", sysDate);
            //调用计算逻辑
            /**
             * 获取tradeAmtReal
             */
            Object tradeAmtReal = rskParamReCalcMapper.getTradeAmtByEventId((String)dataMap.get("eventId"));
            if(tradeAmtReal==null) {
            	dataMap.put("tradeAmtReal", tradeAmtReal);	
            }
            else {
            	dataMap.put("tradeAmtReal", dataMap.get("amount"));	
            }
            calcService.calcIndParam(dataMap, response);
            Object dtlObj = response.get("PARA_VAL_DETAILS");
            if (dtlObj != null) {
            	details.addAll((List<RskIndicatorParaCalcdetail>) dtlObj);
            }
        });
        if (!StringUtil.isEmptyList(details)) {
            // 参数值计算流水信息
        	resDto.setParaCalList(details);
        }
        return resDto;
    }

}
