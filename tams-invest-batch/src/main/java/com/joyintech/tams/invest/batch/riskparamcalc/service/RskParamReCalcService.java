/*
 * 文件名：RskParamReCalcService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：jjpei
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.service;

import com.google.common.collect.Maps;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;
import com.joyintech.tams.common.base.entity.RskOccupyInfo;
import com.joyintech.tams.invest.batch.riskparamcalc.dao.RskParamReCalcMapper;
import com.joyintech.tams.invest.common.batchList.dao.RskIndicatorParaCalcdetailListMapper;
import com.joyintech.tams.invest.common.batchList.service.BatchListService;
import com.joyintech.tools.collectors.JoyinCollectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 批处理-指标重新计算-Service
 * 
 * @author jjpei
 * @version 1.0 2020年7月23日
 * @see RskParamReCalcService
 * @since 1.0
 */
@Service
public class RskParamReCalcService {

    @Autowired
    private  BatchListService<RskIndicatorParaCalcdetail> detailsService;
    
    @Autowired
    private RskIndicatorParaCalcdetailListMapper detailsMapper;

    /**
     * 批处理-指标重新计算-Mapper
     */
    @Autowired
    private RskParamReCalcMapper mapper;

    /**
     * 取得 所有预占下的实占信息
     * 
     * @param prodIdList 产品代码
     * @return 所有预占下的实占信息
     * @see
     * @since 1.0
     */
    public List<RskOccupyInfo> getOcpInfoList(List<String> prodIdList, Date sysDate) {
        return mapper.queryOcpInfo(prodIdList,sysDate);
    }

    /**
     * 批量保存参数计算流水
     * 
     * @param details 参数计算流水
     */
    @Transactional(rollbackFor = Throwable.class)
    public void saveDetails(List<RskIndicatorParaCalcdetail> details){
        Map<String, Object> param = Maps.newHashMap();
        if (!StringUtil.isEmptyList(details)) {
            param.put("supDate", DateUtil.format(details.get(0).getSupDate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
            param.put("createUser", details.get(0).getCreateUser()); 
            param.put("tradeIds", details.stream().map(item->item.getTradeId()).distinct().collect(Collectors.toList()));
        }
    	mapper.deleteRskIndParaDtlTwo(param);
    	detailsService.setBatchListMapper(detailsMapper);
    	detailsService.insertBatchList(details);
    }
}
