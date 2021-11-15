package com.joyintech.tams.invest.batch.extrdautosettle.processor;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.ExTrdAutoSettleInfoDto;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.ExTrdAutoSettleInfoReaderResult;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.ExTrdAutoSettleInfoResult;
import com.joyintech.tams.invest.batch.extrdautosettle.dto.SettleStatusDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
public class ExTrdAutoSettleInfoProcessor extends BaseProcessor<NeedOperateEntity, ExTrdAutoSettleInfoResult> {

    @Override
    public ExTrdAutoSettleInfoResult process(NeedOperateEntity item) throws Exception {
        List<ExTrdAutoSettleInfoDto> resultAutoSettleInfoDtos = new ArrayList<>();
        // 获取reader方法的返回值
        ExTrdAutoSettleInfoReaderResult readerResult = (ExTrdAutoSettleInfoReaderResult) item;
        List<ExTrdAutoSettleInfoDto> autoSettleInfoDtos = readerResult.getAutoSettleInfoDtos();
        //map<交易委托编号, List<手工交收数据>>
        Map<String, List<ExTrdAutoSettleInfoDto>> orderNoMap = autoSettleInfoDtos.stream().collect(
                Collectors.toMap(ExTrdAutoSettleInfoDto::getOrderNo,
                s -> {
                    List<ExTrdAutoSettleInfoDto> dtos = new ArrayList<>();
                    dtos.add(s);
                    return dtos;
                },
                (List<ExTrdAutoSettleInfoDto> v1, List<ExTrdAutoSettleInfoDto> v2) -> {
                    v1.addAll(v2);
                    return v1;
                }));
        //map<成交编号, List<手工交收数据>>
        Map<String, List<ExTrdAutoSettleInfoDto>> dealNoMap = autoSettleInfoDtos.stream().collect(
                Collectors.toMap(ExTrdAutoSettleInfoDto::getDealNo,
                        s -> {
                            List<ExTrdAutoSettleInfoDto> dtos = new ArrayList<>();
                            dtos.add(s);
                            return dtos;
                        },
                        (List<ExTrdAutoSettleInfoDto> v1, List<ExTrdAutoSettleInfoDto> v2) -> {
                            v1.addAll(v2);
                            return v1;
                        }));
        // 已处理成交编号列表
        Set<String> handledDealNoSet = new HashSet<>();
        for(ExTrdAutoSettleInfoDto autoSettleInfoDto : autoSettleInfoDtos){
            // 如已处理过该成交编号,则跳过
            if(handledDealNoSet.contains(autoSettleInfoDto.getDealNo())){
                continue;
            }
            // todo 调用交易端交收状态查询接口
            List<SettleStatusDto> settleStatusList = new ArrayList<>();
            for(SettleStatusDto settleStatus : settleStatusList){
                String curSettleDealNo = settleStatus.getDealNo();
                String dealNo = null;
                if(StringUtil.isNotEmpty(curSettleDealNo)){
                    dealNo = curSettleDealNo;
                }else if(StringUtil.isNotEmpty(curSettleDealNo) && orderNoMap.containsKey(curSettleDealNo)){
                    dealNo = orderNoMap.get(curSettleDealNo).get(0).getDealNo();
                }
                if(StringUtil.isEmpty(dealNo)){
                    continue;
                }
                if(handledDealNoSet.contains(dealNo)){
                    continue;
                }
                handledDealNoSet.add(dealNo);
                List<ExTrdAutoSettleInfoDto> settleInfos = dealNoMap.get(dealNo);
                if(CollectionUtils.isEmpty(settleInfos)){
                    continue;
                }
                resultAutoSettleInfoDtos.addAll(settleInfos);
            }
        }
        ExTrdAutoSettleInfoResult result = new ExTrdAutoSettleInfoResult();
        result.setAutoSettleInfoDtos(resultAutoSettleInfoDtos);
        return result;
    }
}
