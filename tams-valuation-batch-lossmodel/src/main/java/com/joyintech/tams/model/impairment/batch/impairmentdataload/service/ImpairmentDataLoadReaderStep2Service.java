package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep2ReaderDto;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImpairmentDataLoadReaderStep2Service {

    @Autowired
    private ImpairmentCommonService impairmentCommonService;
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;
    @Autowired
    private ModParamsService modParamsService;
    /**
     * 二叉树模型-reader
     * @param params
     * @return
     */
    public List<ImpairmentDataLoadStep2ReaderDto> createReaderList(Map<String, Object> params) {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        //1.读取需要进行处理的资产
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("searchDate", platDate);
        para.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        para.put("ps", DictCodeList.交易方向_买入.getValue());
        para.put("batchExedetailSeqNo", batchExecuteInfo.getBatchExedetailSeqNo());
        //1、获取需要当天处理的初始数据列表
        List<ImpairmentDataLoadStep2ReaderDto> list = impairmentDataLoadMapper.selectModEclResultProcessForDeal(para);
        if (list == null || list.size() == 0) {
            return list;
        }
        //2、遍历未匹配的交易列表加载查询参数
        List<ViewRatingInfoDto> paraList = new ArrayList<ViewRatingInfoDto>();
        //2.1.加载查询参数
        for (ImpairmentDataLoadStep2ReaderDto dto : list) {
            if (StringUtil.isEmpty(dto.getGradeOrgId())) {
                continue;
            }
            // 债项评级、主体评级
//            if (StringUtil.isNotEmpty(dto.getMarketId())) {
            // 资产类型= 债券：债项评级、主体评级
            if (DictCodeListExt1.证券品种_债券.getValue().equals(dto.getModelAssetType())) {
                // 市场代码主体评级
                ViewRatingInfoDto dto1 = new ViewRatingInfoDto(dto.getMarketId(),
                    dto.getGradeOrgId(), ModelDictCodeList.模型_评级类型_主体评级.getValue(),
                    dto.getTradeDate());
                paraList.add(dto1);
                // 市场代码债项评级
                ViewRatingInfoDto dto2 = new ViewRatingInfoDto(dto.getMarketId(),
                    dto.getGradeOrgId(), ModelDictCodeList.模型_评级类型_债项评级.getValue(),
                    dto.getTradeDate());
                paraList.add(dto2);
            }
            else {
                // 其他债权、存款、回购：发行人（回购发行人对应交易对手）
                if (StringUtil.isNotEmpty(dto.getIssuer())) {
                    ViewRatingInfoDto dto1 = new ViewRatingInfoDto(dto.getIssuer(),
                        dto.getGradeOrgId(), ModelDictCodeList.模型_评级类型_主体评级.getValue(),
                        dto.getTradeDate());
                    paraList.add(dto1);
                }
                if (DictCodeListExt1.证券品种_其他债权类.getValue().equals(dto.getModelAssetType())){
                    // 融资人
                    if (StringUtil.isNotEmpty(dto.getFinancier())) {
                        ViewRatingInfoDto dto1 = new ViewRatingInfoDto(dto.getIssuer(),
                            dto.getFinancier(), ModelDictCodeList.模型_评级类型_主体评级.getValue(),
                            dto.getTradeDate());
                        paraList.add(dto1);
                    }
                    // 担保人
                    if (StringUtil.isNotEmpty(dto.getSponsor())) {
                        ViewRatingInfoDto dto1 = new ViewRatingInfoDto(dto.getIssuer(),
                            dto.getSponsor(), ModelDictCodeList.模型_评级类型_主体评级.getValue(),
                            dto.getTradeDate());
                        paraList.add(dto1);
                    }
                }
            }

        }

        //清除缓存中评级信息数据
        CacheUtil.clearAppCache(GlobalConstant.RATING_INFO);
        CacheUtil.clearAppCache(GlobalConstant.RATING_INFO_LATEST);

        //3、查询未匹配交易的评级结果数据（查视图）
        Map<Date,List<ViewRatingInfoDto>>  searchMap =  new HashMap<>();
        //以查询日为key,对查询评级参数List进行分组，构建查询评级参数Map，数据类型如下：
        paraList.stream().collect(Collectors.groupingBy(ViewRatingInfoDto::getSearchDate,Collectors.toList()))
                .forEach(searchMap::put);
        // 定义 指定机构评级信息Map
        Map<Date,Map<String, ViewRatingInfoDto>>  ratingMap =  new HashMap<>();
        // 定义 最新机构评级信息Map
        Map<Date,Map<String, ViewRatingInfoDto>>  ratingMapLatest =  new HashMap<>();
        for (Date key : searchMap.keySet()) {
            List<ViewRatingInfoDto> listViewPara = searchMap.get(key);
            List<ViewRatingInfoDto> listRating = impairmentCommonService.selectViewRatingInfoByList(listViewPara, key);
            // 定义 变量.指定评级机构结果Map：每个评级类型对应的指定评级机构的主体评级的较高值
            Map<String, ViewRatingInfoDto> mapRating = impairmentCommonService.getRatingInfoMap(listRating);
            // 定义 变量.最新评级机构结果Map：每个评级类型对应的最新评级机构的主体评级的较高值
            Map<String, ViewRatingInfoDto> latestMapRating = impairmentCommonService.getLatestRatingInfoMap(listRating);
            // 指定评级机构信息Map放入缓存
            ratingMap.put(key, mapRating);
            ratingMapLatest.put(key, latestMapRating);
        }
        // 指定评级机构信息Map放入缓存
        CacheUtil.putAppCache(GlobalConstant.RATING_INFO, ratingMap);
        // 最新评级机构信息Map放入缓存
        CacheUtil.putAppCache(GlobalConstant.RATING_INFO_LATEST, ratingMapLatest);
        return list;
    }

}
