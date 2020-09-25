/*
 * 文件名：PtlInvPortfolioStrategyService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinveststrategypool.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlinveststrategypool.dao.PtlCommonSearchMapper;
import com.joyintech.service.portfolio.ptlinveststrategypool.dao.PtlInvestStrategyPoolMapper;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvStrategyDetailDto;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPoolDto;
import com.joyintech.tams.common.base.dao.PtlInvStrategyDetailClassMapper;
import com.joyintech.tams.common.base.dao.PtlInvStrategyDetailMapper;
import com.joyintech.tams.common.base.dao.PtlInvStrategyMapper;
import com.joyintech.tams.common.base.dao.PtlStrategyEvalIndexMapper;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.common.base.entity.PtlInvStrategyDetail;
import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClass;
import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClassKey;
import com.joyintech.tams.common.base.entity.PtlStrategyEvalIndex;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tools.collectors.JoyinCollectors;

@Service
public class PtlInvestStrategyPoolService extends CommonExportService<PtlInvestStrategyPoolDto> {

    @Autowired
    private PtlInvestStrategyPoolMapper ptlInvestStrategyPoolMapper;

    @Autowired
    private PtlInvStrategyDetailMapper ptlInvStrategyDetailMapper;

    @Autowired
    private PtlInvStrategyMapper ptlInvStrategyMapper;

    @Autowired
    private PtlStrategyEvalIndexMapper ptlStrategyEvalIndexMapper;

    /**
     * 共通查询Mapper
     */
    @Autowired
    private PtlCommonSearchMapper ptlCommonSearchMapper;

    /**
     * 投资策略明细分类Mapper
     */
    @Autowired
    private PtlInvStrategyDetailClassMapper ptlInvStrategyDetailClassMapper;

    /**
     * 业务编号生成类
     */
    @Autowired(required = false)
    public BaseGetBusinessId baseGetBussinessId;

    /**
     * 画面查询方法
     * 
     * @param page
     * @param length
     * @param pageParam
     * @return
     */
    public Page<PtlInvestStrategyPoolDto> selectForPage(int page, int length,
                                                        Map<String, Object> pageParam) {
        String parentId = (String)pageParam.get("parentId");
        if (!StringUtil.equals("root", parentId) && StringUtil.isNotEmpty(parentId)) {
            List<String> treeId = StringUtil.splitToList(null, parentId);
            pageParam.put("treeSearchId", treeId.get(0));
            pageParam.put("treeSearchLevel", treeId.get(1));
            if (!StringUtil.equals("1", treeId.get(1))) {
                pageParam.put("typeId", treeId.get(2));
            }
            if (StringUtil.equals("2", treeId.get(1))) {
                pageParam.put("batchId", treeId.get(2));
            }
        }
        Page<PtlInvestStrategyPoolDto> pages =  super.queryForTree(() -> ptlInvestStrategyPoolMapper.selectForPage(pageParam),
            PtlInvestStrategyPoolDto::getAssetTypeId, PtlInvestStrategyPoolDto::getpAssetTypeId,
            PtlInvestStrategyPoolDto::getStrategyName, getFilter(pageParam), Integer.MAX_VALUE,
            parentId.equals("root") ? null : parentId);
        // return super.queryForPage(page, length, () ->
        // ptlInvestStrategyPoolMapper.selectForPage(pageParam));
        return pages;
    }

    private Predicate<PtlInvestStrategyPoolDto> getFilter(Map<String, Object> pageParam) {
        return (item -> {
            if (StringUtil.equals(pageParam.get("parentId"), "root")) {
                return true;
            }
            return true;
        });
    }

    /**
     * 画面查询方法
     * 
     * @param pageParam
     * @return
     */
    public Integer selectIsPtlPortfolioCount(Map<String, Object> pageParam) {
        return ptlInvestStrategyPoolMapper.selectIsPtlPortfolioCount(pageParam);
    }

    /**
     * 删除方法
     * 
     * @param entity
     * @return
     */
    public Integer delete(PtlInvestStrategyPoolDto entity) {
        // 删除策略详情表
        ptlInvestStrategyPoolMapper.deleteStrategyDetailByStrategyId(entity.getStrategyId());
        // 删除策略指标表
        ptlInvestStrategyPoolMapper.deleteStrategyEveIndexByStrategyId(entity.getStrategyId());

        return ptlInvStrategyMapper.deleteByPrimaryKey(entity.getStrategyId());
    }

    /**
     * 获取策略池详细信息
     * 
     * @param pageParam
     * @return
     */
    public PtlInvestStrategyPoolDto getPtlInvestStrategyPool(String strategyId) {
        List<PtlInvestStrategyPoolDto> list = ptlInvestStrategyPoolMapper.getPtlInvestStrategyPool(
            strategyId);
        String evalIndex = "";
        for (PtlInvestStrategyPoolDto temp : list) {
            evalIndex += temp.getEvalIndex() + GlobalConstant.COMMA;
        }
        PtlInvestStrategyPoolDto dto = new PtlInvestStrategyPoolDto();
        BeanUtil.copyPropertiesIgnoreNull(dto, list.get(0));
        dto.setEvalIndex(evalIndex.substring(0, evalIndex.length() - 1));
        return dto;
    }

    /**
     * 获取策略池详细信息
     * 
     * @param pageParam列表
     * @return
     */
    public List<PtlInvStrategyDetailDto> getPtlInvestStrategyPoolDetail(String strategyId) {
        List<PtlInvStrategyDetailDto> list = ptlInvestStrategyPoolMapper.getPtlInvestStrategyPoolDetail(
            strategyId);
        return list;
    }

    /**
     * Excel导出方法
     * 
     * @param page
     * @param length
     * @param pageParam
     * @return
     */
    public List<PtlInvestStrategyPoolDto> selectForExport(Map<String, Object> pageParam) {
        return ptlInvestStrategyPoolMapper.selectForExport(pageParam);
    }

    /**
     * 更新策略组合表
     * 
     * @param pageParam
     * @return
     */
    public Integer updateStrategyEveIndexByStrategyId(Map<String, Object> pageParam) {
        return ptlInvestStrategyPoolMapper.updateStrategyEveIndexByStrategyId(pageParam);
    }

    /**
     * 修改相关功能
     * 
     * @param pageParam
     * @return
     */
    public Integer editStrategyPoolByEntity(PtlInvestStrategyPoolDto entity) {
        PtlInvStrategy ptlInvStrategy = new PtlInvStrategy();
        List<PtlInvStrategyDetailDto> detailList = entity.getPtlInvestStrategyPoolDetailList();
        PtlStrategyEvalIndex ptlStrategyEvalIndex = new PtlStrategyEvalIndex();
        // 处理数据
        // genBasePtlInvStrategy(entity, ptlInvStrategy, detailList, ptlStrategyEvalIndex);
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), entity.getIsNew())) {
        	// add by xingxiaokang 2020-03-10 start ZGBUG-180-新建报错
        	ptlInvStrategy.setStrategyClass(DictCodeListExt3.策略分类_规划策略.getValue());
        	// add by xingxiaokang 2020-03-10 end ZGBUG-180-新建报错
            // 【投资策略】入库
            // 转换投资策略表
            BeanUtil.copyPropertiesIgnoreNull(ptlInvStrategy, entity);
            ptlInvStrategyMapper.insert(ptlInvStrategy);
            // 【投资策略明细】入库
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_投资策略池管理);
            for (PtlInvStrategyDetailDto detail : detailList) {
                PtlInvStrategyDetail ptlInvStrategyDetail = new PtlInvStrategyDetail();
                ptlInvStrategyDetail.setBatchId(detail.getBatchId());
                ptlInvStrategyDetail.setStrategyId(ptlInvStrategy.getStrategyId());
                ptlInvStrategyDetail.setRatio(
                    ArithmeticUtil.divide(detail.getRatio(), new BigDecimal(100), 4));
                ptlInvStrategyDetailMapper.insert(ptlInvStrategyDetail);
                // 将【投资策略明细】拆分为【投资策略明细分类】,并入库
                splitDetailClass(detail, detail.getBatchId());
            }
            int count = 0;
            if (StringUtil.equals(DictCodeList.是否_是.getValue(), entity.getIsNew())) {
                if (StringUtil.isNotEmpty(entity.getEvalIndex())) {
                    List<String> evalIndexs = StringUtil.splitToList(GlobalConstant.COMMA,
                        entity.getEvalIndex());
                    for (String evalIndex : evalIndexs) {
                        ptlStrategyEvalIndex.setStrategyEvalId(
                            baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                                bussinessCodeParameter) : IDGenerator.generate());
                        ptlStrategyEvalIndex.setStrategyId(entity.getStrategyId());
                        ptlStrategyEvalIndex.setEvalIndex(evalIndex);
                        ptlStrategyEvalIndexMapper.insert(ptlStrategyEvalIndex);
                        count++ ;
                    }
                }
                else {
                    ptlStrategyEvalIndex.setStrategyEvalId(
                        baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                            bussinessCodeParameter) : IDGenerator.generate());
                    ptlStrategyEvalIndex.setStrategyId(entity.getStrategyId());
                    ptlStrategyEvalIndexMapper.insert(ptlStrategyEvalIndex);
                }
            }
            return count;
        }
        else {
        	//add by chenxiaoqiu 20191130 
            BeanUtil.copyPropertiesIgnoreNull(ptlInvStrategy, entity);
            // add by xingxiaokang 2020-03-10 start ZGBUG-180-新建报错
            ptlInvStrategy.setStrategyClass(DictCodeListExt3.策略分类_规划策略.getValue());
            // add by xingxiaokang 2020-03-10 end ZGBUG-180-新建报错
            ptlInvStrategyMapper.updateByPrimaryKey(ptlInvStrategy);
            // 针对投资策略明细,先整表删除
            ptlInvestStrategyPoolMapper.deleteStrategyDetailByStrategyId(entity.getStrategyId());
            detailList.forEach(ptlInvStrategyDetail -> {
            	ptlInvStrategyDetail.setStrategyId(entity.getStrategyId());
            	if(ptlInvStrategyDetail.getRatio()!=null){
            		ptlInvStrategyDetail.setRatio(ArithmeticUtil.divide(ptlInvStrategyDetail.getRatio(), new BigDecimal(100), 4));
            	}
                ptlInvStrategyDetailMapper.insert(ptlInvStrategyDetail);
             // 将【投资策略明细】拆分为【投资策略明细分类】,并入库  add by chenxiaoqiu 20191206
                //先删除再插入
                ptlInvestStrategyPoolMapper.delsteStrategyDetailClassByBatchId(ptlInvStrategyDetail.getBatchId());
                splitDetailClass(ptlInvStrategyDetail, ptlInvStrategyDetail.getBatchId());
            });
            //更改修改時评价指标的逻辑错误 by chenxiaoqiu 20191210
            int count = 0;
            BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
            if (StringUtil.equals(DictCodeList.是否_否.getValue(), entity.getIsNew())) {
            	ptlInvestStrategyPoolMapper.deleteStrategyEveIndexByStrategyId(entity.getStrategyId());
                if (StringUtil.isNotEmpty(entity.getEvalIndex())) {
                    List<String> evalIndexs = StringUtil.splitToList(GlobalConstant.COMMA,
                        entity.getEvalIndex());
                    for (String evalIndex : evalIndexs) {
                        ptlStrategyEvalIndex.setStrategyEvalId(
                            baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                                bussinessCodeParameter) : IDGenerator.generate());
                        ptlStrategyEvalIndex.setStrategyId(entity.getStrategyId());
                        ptlStrategyEvalIndex.setEvalIndex(evalIndex);
                        ptlStrategyEvalIndexMapper.insert(ptlStrategyEvalIndex);
                        count++ ;
                    }
                }
                else {
                    ptlStrategyEvalIndex.setStrategyEvalId(
                        baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                            bussinessCodeParameter) : IDGenerator.generate());
                    ptlStrategyEvalIndex.setStrategyId(entity.getStrategyId());
                    ptlStrategyEvalIndexMapper.insert(ptlStrategyEvalIndex);
                }
            }
            return count;
        }
    }

    /**
     * 将【投资策略明细】拆分为【投资策略明细分类】
     * 
     * @param detail 【投资策略明细】
     * @param string 
     * @param batchId 
     * @return 【投资策略明细分类】
     * @see
     * @since 1.0
     */
    public void splitDetailClass(PtlInvStrategyDetailDto detail, String batchId) {
        List<String> mulType2List = StringUtil.splitToList(GlobalConstant.COMMA,
            detail.getType2());
        List<String> mulType3List = StringUtil.splitToList(GlobalConstant.COMMA,
            detail.getType3());
        List<String> mulType4List = StringUtil.splitToList(GlobalConstant.COMMA,
            detail.getType4());
        List<String> mulType5List = StringUtil.splitToList(GlobalConstant.COMMA,
            detail.getType5());
        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_投资策略池管理);

        // 组装联合资产分类
        List<String> uniteTypeList = new ArrayList<>();
        for (String type2 : mulType2List) {
            if (StringUtil.isEmptyList(mulType3List)) {
                uniteTypeList.add(type2 + "|");
            }
            else {
                for (String type3 : mulType3List) {
                    if (StringUtil.isEmptyList(mulType4List)) {
                        uniteTypeList.add(type2 + "|" + type3);
                    }
                    else {
                        for (String type4 : mulType4List) {
                            if (StringUtil.isEmptyList(mulType5List)) {
                                uniteTypeList.add(type2 + "|" + type3 + "|" + type4);
                            }
                            else {
                                for (String type5 : mulType5List) {
                                    uniteTypeList.add(type2 + "|" + type3 + "|" + type4 + "|" + type5);
                                }
                            }
                        }
                    }
                }
            }
        }
        // 查询联合资产类型
        List<PtlInvStrategyDetailDto> uniteTypes = ptlCommonSearchMapper.queryUniteTypes();
        Map<Object, List<PtlInvStrategyDetailDto>> uniteTypesMap = uniteTypes.stream().collect(
            JoyinCollectors.groupingBy(entity -> entity.getUniteType()));
        List<String> aaa = new ArrayList<>();
        for (String uniteType : uniteTypeList) {
            while (uniteType.indexOf("|") > 0) {
                if (uniteTypesMap.containsKey(uniteType)&& !aaa.contains(uniteType)) {
                    while(uniteType.indexOf("|") > 0) {
                        if(!aaa.contains(uniteType)) {
                            aaa.add(uniteType);
                        }
                        uniteType = uniteType.substring(0, uniteType.lastIndexOf("|"));
                    }
                    break;
                }
                else {
                    uniteType = uniteType.substring(0, uniteType.lastIndexOf("|"));
                }
            }
            
        }
        List<String> result = new ArrayList<>();
        for (String a : aaa) {
            int x = 0;
            for (String b : aaa) {
                if (b.indexOf(a) == 0) {
                    x++ ;
                }
            }
            if (x == 1) {
                result.add(a);
            }
        }
        
        for(String a:result) {
            PtlInvStrategyDetailClass dto = new PtlInvStrategyDetailClass();
            BeanUtil.copyPropertiesIgnoreNull(dto, uniteTypesMap.get(a).get(0));
            dto.setBatchId(batchId);
            dto.setStrategyDetId(
                baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
                    bussinessCodeParameter) : IDGenerator.generate());
            ptlInvStrategyDetailClassMapper.insertSelective(dto);
        }

    }

//    /**
//     * 将entity转换成
//     * 
//     * @param entity
//     * @param ptlInvStrategy
//     * @param detailList
//     * @param ptlPortfolioEval
//     */
//    private void genBasePtlInvStrategy(PtlInvestStrategyPoolDto entity,
//                                       PtlInvStrategy ptlInvStrategy,
//                                       List<PtlInvStrategyDetail> detailList,
//                                       PtlStrategyEvalIndex ptlStrategyEvalIndex) {
//        if (entity == null) {
//            return;
//        }
//        // 转换投资策略表
//        BeanUtil.copyPropertiesIgnoreNull(ptlInvStrategy, entity);
//        BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
//        bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_投资策略池管理);
//        // if(!StringUtil.isEmptyList(entity.getPtlInvestStrategyPoolDetailList())){
//        // for (PtlInvStrategyDetail ptlInvStrategyDetail :
//        // entity.getPtlInvestStrategyPoolDetailList()) {
//        // PtlInvStrategyDetail ptlInvStrategyDetailTmp = new PtlInvStrategyDetail();
//        // if(!StringUtil.equals(DictCodeList.是否_是.getValue(),entity.getIsNew())){
//        // if(StringUtil.isNotEmpty(ptlInvStrategyDetail.getStrategyDetId())){
//        // ptlInvStrategyDetailTmp.setStrategyDetId(ptlInvStrategyDetail.getStrategyDetId());
//        // }
//        // else{
//        // ptlInvStrategyDetailTmp.setStrategyDetId(baseGetBussinessId !=null ?
//        // baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());
//        // }
//        // }
//        // else{
//        // ptlInvStrategyDetailTmp.setStrategyDetId(baseGetBussinessId !=null ?
//        // baseGetBussinessId.buildBussinessCode(bussinessCodeParameter):IDGenerator.generate());
//        // }
//        // ptlInvStrategyDetailTmp.setStrategyId(entity.getStrategyId());
//        // ptlInvStrategyDetailTmp.setType2(ptlInvStrategyDetail.getType2());
//        // ptlInvStrategyDetailTmp.setType3(ptlInvStrategyDetail.getType3());
//        // ptlInvStrategyDetailTmp.setType4(ptlInvStrategyDetail.getType4());
//        // ptlInvStrategyDetailTmp.setType5(ptlInvStrategyDetail.getType5());
//        // ptlInvStrategyDetailTmp.setRatio(ptlInvStrategyDetail.getRatio().divide(new
//        // BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
//        // detailList.add(ptlInvStrategyDetailTmp);
//        // }
//        // }
//        if (StringUtil.equals(DictCodeList.是否_是.getValue(), entity.getIsNew())) {
//            ptlStrategyEvalIndex.setStrategyEvalId(
//                baseGetBussinessId != null ? baseGetBussinessId.buildBussinessCode(
//                    bussinessCodeParameter) : IDGenerator.generate());
//            ptlStrategyEvalIndex.setStrategyId(entity.getStrategyId());
//            ptlStrategyEvalIndex.setEvalIndex(entity.getEvalIndex());
//        }
//    }

    /**
     * 策略代码生成
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String createStrategyId() {
        String strategyId = null;
        while (true) {
            // 获取自增数
            String nextId = this.getNextId("STRATEGYID_SEQUENCE");
            // 拼接
            strategyId = "TZCL" + nextId;
            // 查询是否重复
            PtlInvStrategy ptlInvStrategy = ptlInvStrategyMapper.selectByPrimaryKey(strategyId);
            // 如果自增的策略代码不存在，则跳出循环
            if (null == ptlInvStrategy) {
                break;
            }
        }
        return strategyId;
    }

    /**
     * 获取自增序列值
     * 
     * @param sequenceName 序列名
     * @return
     */
    public String getNextId(String sequenceName) {
        return ptlInvestStrategyPoolMapper.getNextId(sequenceName);
    }
}
