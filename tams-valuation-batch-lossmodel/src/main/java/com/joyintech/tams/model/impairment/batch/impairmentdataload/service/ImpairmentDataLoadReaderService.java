package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.batchtask.service.ModParamsService;
import com.joyintech.tams.common.base.entity.ModAseetMapping;
import com.joyintech.tams.common.base.entity.ModConfigUseage;
import com.joyintech.tams.common.base.entity.ModStandardGradeMapping;
import com.joyintech.tams.common.base.entity.ModValBatchParams;
import com.joyintech.tams.common.utils.DataHandleUtil;
import com.joyintech.tams.constant.GlobalConstant;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentViewCommonService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImpairmentDataLoadReaderService {

    @Autowired
    private ImpairmentViewCommonService impairmentViewCommonService;
    @Autowired
    private ImpairmentDataLoadMapper impairmentDataLoadMapper;
    @Autowired
    private ModParamsService modParamsService;
    /**
     * 二叉树模型-reader
     * @param params
     * @return
     */
    public List<ImpairmentDataLoadStep1ReaderDto> createReaderList(Map<String, Object> params) {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        List<ModValBatchParams> listBatch = modParamsService.selectByBatchExecSeqno(batchExecuteInfo.getBatchExedetailSeqNo());
        //1.读取需要进行处理的资产
        //资产代码集合
        List<String> finprodIdList = new ArrayList<String>();
        if (listBatch != null && listBatch.size() > 0) {
            for (ModValBatchParams params1 : listBatch) {
                finprodIdList.add(params1.getFinprodId());
            }
        }
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("searchDate", platDate);
        para.put("INV_AIM_AC", ModelDictCodeList.投资目的_摊余成本类.getValue());
        para.put("MODEL_SEC_TYPE_F01", ModelDictCodeList.模型_证券品种_债券.getValue());
        para.put("MODEL_SEC_TYPE_F06", "F06");
        para.put("TYPE_08", "08");
        para.put("TYPE_09", "09");
        para.put("finprodIdList", finprodIdList);
        //1、获取需要当天处理的初始数据列表
        List<ImpairmentDataLoadStep1ReaderDto> list = impairmentViewCommonService.selectAssetTradeList(para);
        if (list == null || list.size() == 0) {
            return list;
        }
        //1.2 设置资产类型和资产细分类
        Map<String, Object> paraType = new HashMap<String, Object>();
        paraType.put("MODEL_MAPPING_TYPE_01", ModelDictCodeList.模型_映射类型_资产分类映射.getValue());
        List<ModAseetMapping> listType = impairmentDataLoadMapper.selectModAseetMapping(paraType);
        if (listType != null && listType.size() > 0) {
            for (ImpairmentDataLoadStep1ReaderDto dataLoadStep1ReaderDto : list) {
                for (ModAseetMapping modAseetMapping : listType) {
                    if (modAseetMapping.getOutput1().equals(dataLoadStep1ReaderDto.getAssetOne())
                            && modAseetMapping.getOutput2().equals(dataLoadStep1ReaderDto.getAssetTwo())
                            && modAseetMapping.getOutput3().equals(dataLoadStep1ReaderDto.getAssetThree())
                            && modAseetMapping.getOutput4().equals(dataLoadStep1ReaderDto.getAssetFour())
                            && modAseetMapping.getOutput5().equals(dataLoadStep1ReaderDto.getAssetFive()) ) {
                        dataLoadStep1ReaderDto.setSecTypeModel(modAseetMapping.getInput1());
                        dataLoadStep1ReaderDto.setSecDetailTypeModel(modAseetMapping.getInput2());
                        break;
                    }
                }
            }
        }

        //2、通过投管提供的视图，查询最新的评级结果
        //清除缓存中评级信息数据
        CacheUtil.clearAppCache(GlobalConstant.RATING_INFO);
        //2.1 查询最新评级结果
        List<ViewRatingInfoDto> listRating = impairmentViewCommonService.selectViewRatingInfo(para);
        //2.2 查询评级映射，将评级映射为模型数据字典
        if (listRating != null && listRating.size() > 0) {
            //定义 内部评级结果列表
            List<ViewRatingInfoDto> innerList = new ArrayList<ViewRatingInfoDto>();
            //定义 外部评级结果列表
            List<ViewRatingInfoDto> outList = new ArrayList<ViewRatingInfoDto>();
            //所有评级机构为"资管内部"的数据组成内部评级结果列表，所有评级机构为"非资管内部"的数据组成外部评级结果列表
            for (ViewRatingInfoDto dto : listRating) {
                if (ModelDictCodeList.评级机构_资管内部.getValue().equals(dto.getGradeorgid())) {
                    innerList.add(dto);
                } else {
                    outList.add(dto);
                }
            }
            Map<String, String> MapRating = new HashMap<String, String>();
            if (innerList != null && innerList.size() > 0) {
                for (ViewRatingInfoDto dto : innerList) {
                    MapRating.put(dto.getOcode() + GlobalConstant.UNDER_LINE + dto.getOtype() + GlobalConstant.UNDER_LINE + dto.getGradeorgid(), dto.getMstratingresult01());
                }
            }
            //根据外部评级结果列表，查询标准评级映射，将外部评级映射成模型使用的评级
            if (outList != null && outList.size() > 0) {
                List<ModStandardGradeMapping> listMapping = selectViewRatingInfoMapping(outList);
                for (ViewRatingInfoDto dto : outList) {
                    for (ModStandardGradeMapping mappingDto : listMapping) {
                        if (StringUtil.isNotEmpty(dto.getGradeorgid()) && dto.getGradeorgid().equals(mappingDto.getGradeInst())
                                && StringUtil.isNotEmpty(dto.getOtype()) && dto.getOtype().equals(mappingDto.getoType())
                                && StringUtil.isNotEmpty(dto.getMstratingresult01()) && dto.getMstratingresult01().equals(mappingDto.getGradeResult())) {
                            dto.setMstratingresult01(mappingDto.getGradeStandardResult());
                            break;
                        }
                    }
                }
                for (ViewRatingInfoDto dto : outList) {
                    MapRating.put(dto.getOcode() + GlobalConstant.UNDER_LINE + dto.getOtype() + GlobalConstant.UNDER_LINE + dto.getGradeorgid(), dto.getMstratingresult01());
                }
            }
            //将上述查询结果与内部评级结果列表组成最新评级结果列表，然后以"评级主体_评级类型_评级机构"作为key，以"评级结果"作为value，组成最新评级结果Map
            CacheUtil.putAppCache(GlobalConstant.RATING_INFO, MapRating);
        }
        return list;
    }

    private List<ModStandardGradeMapping> selectViewRatingInfoMapping(List<ViewRatingInfoDto> outList) {
        List<ModStandardGradeMapping> rstList = new ArrayList<ModStandardGradeMapping>();
        // 定义一个固定大小插入存放集合
        List<List<ViewRatingInfoDto>> list = DataHandleUtil.splitList(outList);
        for (List<ViewRatingInfoDto> listInsert : list) {
            if (CollectionUtils.isNotEmpty(listInsert)) {
                List<ModStandardGradeMapping> listMapping = impairmentDataLoadMapper.selectViewRatingInfoMapping(listInsert);
                if (listMapping != null && listMapping.size() > 0) {
                    rstList.addAll(listMapping);
                }
            }
        }
        return rstList;
    }

}
