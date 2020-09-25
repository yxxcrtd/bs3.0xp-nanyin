/*
 * 文件名：BondAutoDurDealGenerateReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.bond.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.autodurdealgenerate.bond.dto.BondAutoDurDealGenerateReaderResult;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.trdbondduration.TrdBondDurationDto;

/**
 * reader相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月11日
 * @see BondAutoDurDealGenerateReaderService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class BondAutoDurDealGenerateReaderService extends BaseService {

    /**
     * 获取待处理数据
     *
     * @param param 传递参数
     * @return List<BondAutoDurDealGenerateReaderResult>
     * @see
     * @since 1.0
     */
    public List<BondAutoDurDealGenerateReaderResult> execute(Map<String, Object> param) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("statusValid", Arrays.asList(DictCodeListExt1.画面数据状态_待处理.getValue()));

        List<BondAutoDurDealGenerateReaderResult> resultList = new ArrayList<BondAutoDurDealGenerateReaderResult>();

        List<TrdBondDurationDto> tradeDataList = restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdBondDuration/findAllData", paramMap,
            TrdBondDurationDto.class);

        if (!StringUtil.isEmptyList(tradeDataList)) {
            for (TrdBondDurationDto trdBondDurationDto : tradeDataList) {
                BondAutoDurDealGenerateReaderResult resultDto = new BondAutoDurDealGenerateReaderResult();
                resultDto.setTradeDataDto(trdBondDurationDto);
                resultList.add(resultDto);
            }
        }

        return resultList;
    }
}
