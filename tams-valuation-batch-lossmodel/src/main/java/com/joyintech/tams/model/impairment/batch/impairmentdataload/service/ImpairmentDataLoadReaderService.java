package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentCommonService;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImpairmentDataLoadReaderService {

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
        para.put("SEC_TYPE_01", DictCodeListExt1.证券品种_债券.getValue());
        para.put("SEC_TYPE_13", DictCodeListExt1.证券品种_其他债权类.getValue());
        para.put("finprodIdList", finprodIdList);
        List<ImpairmentDataLoadStep1ReaderDto> list = new ArrayList<>();
        // 减值方案系统参数
        String lossType = CacheUtil.getSysParam(DictCode.减值方案类型.getValue());
        if (DictCodeListExt3.减值方案类型_普华.getValue().equals(lossType)) {
            //1、获取需要当天处理的初始数据列表
            List<ImpairmentDataLoadStep1ReaderDto> listAsset = impairmentCommonService.selectAssetTradeList(para);
            list.addAll(listAsset);
        }
        else{
            para.put("SEC_TYPE_07", DictCodeListExt1.证券品种_存款.getValue());
            para.put("SEC_TYPE_09", DictCodeListExt1.证券品种_回购.getValue());
            //1、获取需要当天处理的初始数据列表
            // 1.1 查询昨日仍然持仓资产信息
            List<ImpairmentDataLoadStep1ReaderDto> listAsset = impairmentCommonService.selectAssetInfo(para);
            if (CollectionUtils.isNotEmpty(listAsset)) {
                //  查询资产对应首次买入日期
                for(ImpairmentDataLoadStep1ReaderDto item : listAsset){
                    Map<String, Object> searchPara = new HashMap<String, Object>();
                    searchPara.put("prodId", item.getProdId());
                    searchPara.put("portfolioId",item.getPortfolioId());
                    searchPara.put("finprodId",item.getFinprodId());
                    // 调用方法查询首次买入日期，存入交割日，后续查询买入时点评级使用
                    item.setSettleDate(impairmentCommonService.queryFirstBuyDate(searchPara));
                }
                list.addAll(listAsset);
            }
            // 查询昨日无持仓，今日买入资产
            List<ImpairmentDataLoadStep1ReaderDto> listTdyAsset = impairmentCommonService.selectTdyAssetInfo(para);
            if(CollectionUtils.isNotEmpty(listTdyAsset)){
                list.addAll(listTdyAsset);
            }
        }
        if (!CollectionUtils.isNotEmpty(list)) {
            return list;
        }

        //2、查询外部机构评级结果，映射评级机构对应的内部评级
        //清除缓存中评级信息数据
        CacheUtil.clearAppCache(GlobalConstant.RATING_INFO);
        CacheUtil.clearAppCache(GlobalConstant.RATING_INFO_LATEST);
        //2.1 查询最新评级结果
        List<ViewRatingInfoDto> listRating = impairmentCommonService.selectViewRatingInfo(para);
        if(CollectionUtils.isNotEmpty(listRating)){
            // 定义 变量.指定评级机构结果Map：每个评级类型对应的指定评级机构的主体评级的较高值
            Map<String, ViewRatingInfoDto> mapRating = impairmentCommonService.getRatingInfoMap(listRating);
            // 定义 变量.最新评级机构结果Map：每个评级类型对应的最新评级机构的主体评级的较高值
            Map<String, ViewRatingInfoDto> latestMapRating = impairmentCommonService.getLatestRatingInfoMap(listRating);
            // 指定评级机构信息Map放入缓存
            CacheUtil.putAppCache(GlobalConstant.RATING_INFO, mapRating);
            // 最新评级机构信息Map放入缓存
            CacheUtil.putAppCache(GlobalConstant.RATING_INFO_LATEST, latestMapRating);
        }
        return list;
    }

}
