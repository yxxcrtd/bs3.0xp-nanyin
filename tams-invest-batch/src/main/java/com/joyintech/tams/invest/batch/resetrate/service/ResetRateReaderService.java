/*
 * 文件名：ResetRateReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年2月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.batch.resetrate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.resetrate.dao.ResetRateMapper;
import com.joyintech.tams.invest.batch.resetrate.dto.ResetRateReaderResult;
import com.joyintech.tams.invest.common.asset.dto.FinRateInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * reader相关业务处理
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年2月21日
 * @see ResetRateReaderService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class ResetRateReaderService extends BaseService{

    @Autowired
    private ResetRateMapper resetRateMapper;
    /*@Autowired(required = false)
    private PinganBatchPlatformDateImpl platformDateService;*/
    /**
     * 获取待处理数据
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<ResetRateReaderResult> execute(Map<String, Object> param) {
     // 获取待处理任务信息
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get(BatchConstant.BATCH_INFO);
        if(null==batchExecuteInfo){
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("rateType02", DictCodeList.利率类型_浮动利率.getValue());
        //paramMap.put("sysDate",DateUtil.format(platformDateService.getSysDate()));
        paramMap.put("sysDate",DateUtil.format(batchExecuteInfo.getExecuteDate()));
        //update by  zhangnian 20191022 start
        paramMap.put("productIdList",(List<String>)param.get("productIdList"));
        //update by  zhangnian 20191022 end

        List<ResetRateReaderResult> resultList = new ArrayList<ResetRateReaderResult>();
        List<FinRateInfoDto> finRateInfoDtoList = resetRateMapper.selectAllRateInfo(paramMap);
        if (!StringUtil.isEmptyList(finRateInfoDtoList)) {
            for (FinRateInfoDto finRateInfoDto : finRateInfoDtoList) {
                ResetRateReaderResult resultDto = new ResetRateReaderResult();
                resultDto.setFinRateInfoDto(finRateInfoDto);
                resultList.add(resultDto);
            }
        }
        return resultList;
    }
}
