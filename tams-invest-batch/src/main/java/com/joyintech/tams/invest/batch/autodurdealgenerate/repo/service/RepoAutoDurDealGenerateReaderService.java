/*
 * 文件名：RepoAutoDurDealGenerateReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autodurdealgenerate.repo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.batch.core.dto.SysBatchExecInfoDto;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.batch.autodurdealgenerate.repo.dto.RepoAutoDurDealGenerateReaderResult;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;

/**
 * reader相关业务处理
 *
 * @author etliu
 * @version 1.0 2018年12月17日
 * @see RepoAutoDurDealGenerateReaderService
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
@Service
@Scope("prototype")
public class RepoAutoDurDealGenerateReaderService extends BaseService {

    /**
     * 获取待处理数据
     *
     * @param param 传递参数
     * @return List<RepoAutoDurDealGenerateReaderResult>
     * @see
     * @since 1.0
     */
    public List<RepoAutoDurDealGenerateReaderResult> execute(Map<String, Object> param) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("statusValid", Arrays.asList(DictCodeListExt1.画面数据状态_待处理.getValue()));
        paramMap.put("ps", Arrays.asList(DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue()));
        paramMap.put("notChannelFlag", "1");
        SysBatchExecInfoDto dto = (SysBatchExecInfoDto)param.get(BatchConstant.BATCH_INFO);
        String mdate = DateUtil.format(dto.getExecuteDate());
        paramMap.put("mdateStart", mdate);
        paramMap.put("mdateEnd", mdate);
        List<String> productIdList = (List<String>)param.get("productIdList");
        if (productIdList.size() < 1000) {
            paramMap.put("productIdList", productIdList);
        }
        else {
            paramMap.put("productIdList", null);
        }

        List<RepoAutoDurDealGenerateReaderResult> resultList = new ArrayList<RepoAutoDurDealGenerateReaderResult>();

        List<TrdRepoDurationDto> tradeDataList = restTemplate.postForList(
            ServiceApplication.PERIOD_SERVICE_NAME + "/TrdRepoDuration/selectAllData", paramMap,
            TrdRepoDurationDto.class);

        if (!StringUtil.isEmptyList(tradeDataList)) {
            for (TrdRepoDurationDto durationDto : tradeDataList) {
                RepoAutoDurDealGenerateReaderResult resultDto = new RepoAutoDurDealGenerateReaderResult();
                resultDto.setTradeDataDto(durationDto);
                resultList.add(resultDto);
            }
        }

        return resultList;
    }
}
