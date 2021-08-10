package com.joyintech.tams.model.impairment.batch.impairmentdataload.service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.ModelDictCodeList;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.dto.ViewRatingInfoDto;
import com.joyintech.tams.model.impairment.batch.common.searchinfo.service.ImpairmentViewCommonService;
import com.joyintech.tams.model.impairment.batch.common.utils.DataHandleUtil;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dao.ImpairmentDataLoadMapper;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep1ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ImpairmentDataLoadStep2ReaderDto;
import com.joyintech.tams.model.impairment.batch.impairmentdataload.dto.ModStandardGradeMapping;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.component.batch.service.ModParamsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImpairmentDataLoadReaderStep2Service {

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
    public List<ImpairmentDataLoadStep2ReaderDto> createReaderList(Map<String, Object> params) {
        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)params.get("BATCH_INFO");
        //1.读取需要进行处理的资产
        //跑批日
        Date platDate = (Date)params.get("PLATE_DATE");
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("searchDate", platDate);
        para.put("process", ModelDictCodeList.模型_减值过程_数据加载.getValue());
        para.put("ps", ModelDictCodeList.交易方向_买入.getValue());
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
            if (StringUtil.isNotEmpty(dto.getMarketId())) {
                // 市场代码主体评级
                ViewRatingInfoDto dto1 = new ViewRatingInfoDto();
                //查询评级参数Dto.Set评级对象(交易数据i.市场代码)
                dto1.setOcode(dto.getMarketId());
                //查询评级参数Dto.Set评级机构(交易数据i.评级机构)
                dto1.setGradeorgid(dto.getGradeOrgId());
                //查询评级参数Dto.Set投管评级类型("01 主体评级")
                dto1.setOtype(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                //查询评级参数Dto.Set查询日(交易数据i.交易日)
                dto1.setSearchDate(dto.getTradeDate());
                paraList.add(dto1);
                // 市场代码债项评级
                ViewRatingInfoDto dto2 = new ViewRatingInfoDto();
                //查询评级参数Dto.Set评级对象(交易数据i.市场代码)
                dto2.setOcode(dto.getMarketId());
                //查询评级参数Dto.Set评级机构(交易数据i.评级机构)
                dto2.setGradeorgid(dto.getGradeOrgId());
                //查询评级参数Dto.Set投管评级类型("02 债项评级")
                dto2.setOtype(ModelDictCodeList.模型_评级类型_债项评级.getValue());
                //查询评级参数Dto.Set查询日(交易数据i.交易日)
                dto2.setSearchDate(dto.getTradeDate());
                paraList.add(dto2);
            }
            if (StringUtil.isNotEmpty(dto.getIssuer())) {
                ViewRatingInfoDto dto1 = new ViewRatingInfoDto();
                //查询评级参数Dto.Set评级对象(交易数据i.发行人)
                dto1.setOcode(dto.getIssuer());
                //查询评级参数Dto.Set评级机构(交易数据i.评级机构)
                dto1.setGradeorgid(dto.getGradeOrgId());
                //查询评级参数Dto.Set投管评级类型("01 主体评级")
                dto1.setOtype(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                //查询评级参数Dto.Set查询日(交易数据i.交易日)
                dto1.setSearchDate(dto.getTradeDate());
                paraList.add(dto1);
            }
            if (StringUtil.isNotEmpty(dto.getFinancier())) {
                ViewRatingInfoDto dto1 = new ViewRatingInfoDto();
                //查询评级参数Dto.Set评级对象(交易数据i.融资人)
                dto1.setOcode(dto.getFinancier());
                //查询评级参数Dto.Set评级机构(交易数据i.评级机构)
                dto1.setGradeorgid(dto.getGradeOrgId());
                //查询评级参数Dto.Set投管评级类型("01 主体评级")
                dto1.setOtype(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                //查询评级参数Dto.Set查询日(交易数据i.交易日)
                dto1.setSearchDate(dto.getTradeDate());
                paraList.add(dto1);
            }
            if (StringUtil.isNotEmpty(dto.getSponsor())) {
                ViewRatingInfoDto dto1 = new ViewRatingInfoDto();
                //查询评级参数Dto.Set评级对象(交易数据i.担保人)
                dto1.setOcode(dto.getSponsor());
                //查询评级参数Dto.Set评级机构(交易数据i.评级机构)
                dto1.setGradeorgid(dto.getGradeOrgId());
                //查询评级参数Dto.Set投管评级类型("01 主体评级")
                dto1.setOtype(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                //查询评级参数Dto.Set查询日(交易数据i.交易日)
                dto1.setSearchDate(dto.getTradeDate());
                paraList.add(dto1);
            }
            if (StringUtil.isNotEmpty(dto.getActualCredit())) {
                ViewRatingInfoDto dto1 = new ViewRatingInfoDto();
                //查询评级参数Dto.Set评级对象(交易数据i.实际授信主体)
                dto1.setOcode(dto.getActualCredit());
                //查询评级参数Dto.Set评级机构(交易数据i.评级机构)
                dto1.setGradeorgid(dto.getGradeOrgId());
                //查询评级参数Dto.Set投管评级类型("01 主体评级")
                dto1.setOtype(ModelDictCodeList.模型_评级类型_主体评级.getValue());
                //查询评级参数Dto.Set查询日(交易数据i.交易日)
                dto1.setSearchDate(dto.getTradeDate());
                paraList.add(dto1);
            }
        }

        //3、查询未匹配交易的评级结果数据（查视图）
        Map<Date,List<ViewRatingInfoDto>>  searchMap =  new HashMap<>();
        //以查询日为key,对查询评级参数List进行分组，构建查询评级参数Map，数据类型如下：
        paraList.stream().collect(Collectors.groupingBy(ViewRatingInfoDto::getSearchDate,Collectors.toList()))
                .forEach(searchMap::put);
        Map<Date,Map<String, String>>  ratingMap =  new HashMap<>();
        for (Date key : searchMap.keySet()) {
            List<ViewRatingInfoDto> listViewPara = searchMap.get(key);
            List<ViewRatingInfoDto> listRating = impairmentViewCommonService.selectViewRatingInfoByList(listViewPara, key);
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
                Map<String, String> rstMap = new HashMap<String, String>();
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
                        rstMap.put(dto.getOcode() + GlobalConstant.UNDER_LINE + dto.getOtype() + GlobalConstant.UNDER_LINE + dto.getGradeorgid(), dto.getMstratingresult01());
                    }
                }

                //将上述查询结果与内部评级结果列表组成买入时点评级结果列表
                if (innerList != null && innerList.size() > 0) {
                    for (ViewRatingInfoDto dto : innerList) {
                        rstMap.put(dto.getOcode() + GlobalConstant.UNDER_LINE + dto.getOtype() + GlobalConstant.UNDER_LINE + dto.getGradeorgid(), dto.getMstratingresult01());
                    }
                }
                ratingMap.put(key, rstMap);
            }
        }
        CacheUtil.putAppCache(GlobalConstant.RATING_INFO, ratingMap);
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
