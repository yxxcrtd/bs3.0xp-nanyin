/*
 * 文件名：RiskTrialDayTimeWriter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.risktrialrds.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.risktrialrds.dto.RiskTrialDayTimeResult;
import com.joyintech.tams.common.base.dao.RskIsupLogMapper;
import com.joyintech.tams.common.base.entity.RskIndividUnite;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.risktrialrds.dao.RiskTrialCommMapper;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDetailDto;
import com.joyintech.tams.invest.common.risktrialrds.dto.RiskTrialRetDto;
import com.joyintech.tams.invest.common.risktrialrds.service.SupervisionCalcSvc;
import com.joyintech.tams.invest.common.risktrialrds.util.Const;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;

/**
 * writer
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see RiskTrialDayTimeWriterService
 * @since 1.0
 */
@Service
public class RiskTrialDayTimeWriterService extends BaseService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required = false)
    private RiskTrialCommMapper riskTrialCommMapper;

    @Autowired
    private SupervisionCalcSvc supervisionCalcSvc;

    @Autowired
    private RskIsupLogMapper rskIsupLogMapper;

    public void execute(List<RiskTrialDayTimeResult> resultList,Map<String, Object> param1)
            throws Exception {
        if (!StringUtil.isEmptyList(resultList)) {
            dealTrialIndicatorDb(resultList,param1);
        }
    }

    /**
     * 处理数据到DB
     * @param resultList
     * @param mapParam
     */
    public  void dealTrialIndicatorDb(  List<RiskTrialDayTimeResult> resultList,Map<String, Object> mapParam) throws Exception {
        logger.info("--------->插入监控指标数据开始");
        //查询当天前一天所有指标的连续违规天数
        List<RiskTrialRetDto> curRiskTrialList=riskTrialCommMapper.getRiskTrialRetInfo(mapParam.get(Const.SUP_FREQUENCY).toString(), DateUtil.addDay(DateUtil.format((Date)mapParam.get(BatchConstant.PLATE_DATE)),-1));
        //查询个性化联合设置
        List<RskIndividUnite> rskIndividUniteList=riskTrialCommMapper.getRiskIndividUnite();
        //----------add by 裴家俊 添加删除当日脏数据  start--------------
        for (RiskTrialDayTimeResult resetRateResult : resultList) {
            if (!StringUtil.isNotEmpty(resetRateResult)) {
                continue;
            }
            RiskTrialRetDto dealDto = resetRateResult.getRiskTrialRetDto();
            if (!StringUtil.isNotEmpty(dealDto) || StringUtil.isEmpty(dealDto.getIndId()) || StringUtil.isEmpty(dealDto.getPortfolioId()) || StringUtil.isEmpty(dealDto.getSupStat())) {
                continue;
            }
            riskTrialCommMapper.deleteMxPointWithoutInd(dealDto);
            supervisionCalcSvc.deletePointValueWithoutInd(dealDto);
        }
        //----------add by 裴家俊 添加删除当日脏数据  end--------------
        for (RiskTrialDayTimeResult resetRateResult : resultList) {
            if (!StringUtil.isNotEmpty(resetRateResult)) {
                continue;
            }
            RiskTrialRetDto dealDto = resetRateResult.getRiskTrialRetDto();
            if (!StringUtil.isNotEmpty(dealDto) || StringUtil.isEmpty(dealDto.getIndId()) || StringUtil.isEmpty(dealDto.getPortfolioId()) || StringUtil.isEmpty(dealDto.getSupStat())) {
                continue;
            }
            try {
                //处理个性化联合指标开始
                Set<String> rskFilterList = rskIndividUniteList.stream().filter(item -> item.getPrtBagId().equals(dealDto.getPortfolioId()) && item.getIndId().equals(dealDto.getIndId())).map(entry -> entry.getUniteId()).collect(Collectors.toSet());
                if (rskFilterList.size() > 0) {
                    Set<String> indIdSet = rskIndividUniteList.stream().filter(item -> rskFilterList.contains(item.getUniteId())).map(entry -> entry.getIndId()).collect(Collectors.toSet());
                    if (indIdSet.size() > 0) {
                        List<String> supStatList = resultList.stream().filter(item -> StringUtil.isNotEmpty(item.getRiskTrialRetDto()) && indIdSet.contains(item.getRiskTrialRetDto().getIndId())).map(entry -> entry.getRiskTrialRetDto().getSupStat()).collect(Collectors.toList());
                        dealDto.setSupUniteStat(RiskUtil.getMinState(supStatList));
                        if (StringUtil.isNotEmpty(dealDto.getMathProcess())) {
                            Map<String, Object> mapProcess = JsonUtils.json2Object(dealDto.getMathProcess(), Map.class);
                            mapProcess.put(Const.UNITE, String.join(",", supStatList));
                            dealDto.setMathProcess(JsonUtils.object2Json(mapProcess));
                        }
                    }
                }
                //处理个性化联合指标结束

                //处理连续违规天数开始
                if (dealDto.getSupStat().equals(BussinessDictCodeListRds.监督状态_触发超限.getValue())) {
                    List<Long> foulDaysList = curRiskTrialList.stream().filter(item -> item.getPortfolioId().equals(dealDto.getPortfolioId()) && item.getIndId().equals(dealDto.getIndId())).map(entry -> entry.getFoulDays()).collect(Collectors.toList());
                    if (!StringUtil.isEmptyList(foulDaysList)) {
                        dealDto.setFoulDays(StringUtil.isNotEmpty(foulDaysList.get(0)) ? foulDaysList.get(0) + 1 : 1L);
                    } else {
                        dealDto.setFoulDays(1L);
                    }
                }
                //处理连续违规天数结束

                //处理监控指标DB
                //DB明细操作
                riskTrialCommMapper.deleteMxPointSupervisionBatch(dealDto);
                if (!StringUtil.isEmptyList(dealDto.getRiskTrialRetDetailDtoList())) {
                    List<RiskTrialRetDetailDto> riskTrialRetDetailDtoList = duplicateRemoval(dealDto.getRiskTrialRetDetailDtoList());
                    List<RiskTrialRetDetailDto> list = new ArrayList<>();
                    int count = 0;
                    for (RiskTrialRetDetailDto info : riskTrialRetDetailDtoList) {
                        count++;
                        list.add(info);
                        if (count % 1000 == 0) {
                            riskTrialCommMapper.insertMxPointSupervisionBatch(list);
                            list.clear();
                        }
                    }
                    riskTrialCommMapper.insertMxPointSupervisionBatch(list);
                }
                //DB操作
                supervisionCalcSvc.deletePointSupervisionValue(dealDto);
                supervisionCalcSvc.addPointSupervisionValue(dealDto);
            }catch (Exception e){
                Exception re=new Exception("组合代码："+dealDto.getPortfolioId()+ GlobalConstant.COMMA+"指标代码："+dealDto.getIndId()+GlobalConstant.分号+e.getMessage(),e);
                throw re;
            }
        }
        logger.info("--------->插入监控指标数据结束");

    }
 
    /**
     * 数据去重<br>
     * 
     * @param srcList 原始列表数据
     * @return 去重后数据
     */
    private List<RiskTrialRetDetailDto> duplicateRemoval(List<RiskTrialRetDetailDto> srcList) {
        if (StringUtil.isEmptyList(srcList)) {
            return srcList;
        }
        List<RiskTrialRetDetailDto> resList = new ArrayList<>();
        // 按主键过滤
        Map<String, List<RiskTrialRetDetailDto>> map = srcList.stream().collect(
            Collectors.groupingBy(
            dto -> dto.getPortfolioId() + dto.getIndId() + DateUtil.format(dto.getSupDate())
                   + dto.getSupFrequency() + dto.getAssetCode()));
        // 去重
        map.entrySet().forEach(en -> {
            List<RiskTrialRetDetailDto> valList = en.getValue();
            RiskTrialRetDetailDto item = valList.stream().sorted(
                (dto1, dto2) -> DateUtil.compareDate(dto2.getUpdateTime(),
                    dto1.getUpdateTime())).findFirst().get();
            if (item != null) {
                resList.add(item);
            }
        });
        return resList;
    } 
}
