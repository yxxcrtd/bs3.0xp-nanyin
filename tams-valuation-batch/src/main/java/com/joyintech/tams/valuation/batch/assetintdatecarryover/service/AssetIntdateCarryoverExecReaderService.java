/*
 * 文件名：AssetIntdateCarryoverReaderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.service;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dao.AssetIntdateCarryoverMapper;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverExecReaderDetail;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverExecReaderResult;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产理论结息日结转行权 - reader相关业务处理
 * @author yanya
 * @version 1.0 2021年3月11日
 * @see AssetIntdateCarryoverExecReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverExecReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private AssetIntdateCarryoverMapper assetIntdateCarryoverMapper;

    /**
     * 获取数据库数据
     * @param param 传递参数
     * @return List<BookDetailCompareIntoReadResult>
     * @see
     * @since 1.0
     */
    public List<AssetIntdateCarryoverExecReaderResult> execute(Map<String, Object> param) {
        param.put("execCashType1", DictCodeListExt3.现金流一级类型_行权.getValue());
        param.put("cashType1", DictCodeListExt1.现金流一级类型_本金.getValue());
        param.put("exerciseType", DictCodeList.行权方式_赎回.getValue());
        param.put("finprodType", DictCodeListExt1.金融产品类型_债券.getValue());
        param.put("branch", InterfCommonConstant.分支序号_默认);
        List<AssetIntdateCarryoverExecReaderDetail> detailList = assetIntdateCarryoverMapper.selectSecExerciseList(param);
//        Map<String, List<AssetIntdateCarryoverExecReaderDetail>> resultMap = detailList.stream().collect(
//                Collectors.groupingBy(
//                        item -> item.getBooksetId()+ '_' + item.getFinprodId() + '_' + item.getFinprodType()
//                ));
        Map<String, AssetIntdateCarryoverExecReaderResult> resultMap = new HashMap<>();
        String strKey = "";
        for (AssetIntdateCarryoverExecReaderDetail detail : detailList) {
            strKey = detail.getBooksetId()+ '_' + detail.getFinprodId() + '_' + detail.getFinprodType();
            // 判断map中是否已含有该key的值
            if (resultMap.containsKey(strKey)) {
                resultMap.get(strKey).getAssetIntdateCarryoverExecReaderDetailList().add(detail);
            }
            else {
                // 定义返回实体类
                AssetIntdateCarryoverExecReaderResult info = new AssetIntdateCarryoverExecReaderResult();
                info.setBooksetId(detail.getBooksetId());// 账套代码
                info.setFinprodId(detail.getFinprodId());// 金融产品代码
                info.setFinprodType(detail.getFinprodType());// 金融产品类型
                info.getAssetIntdateCarryoverExecReaderDetailList().add(detail);// 添加到
                //add by yanya on 20210318
                resultMap.put(strKey, info);
            }
        }
        // 定义返回list
        List<AssetIntdateCarryoverExecReaderResult> resultList = new ArrayList<AssetIntdateCarryoverExecReaderResult>();
        // 将map装换为list
        for (String skey : resultMap.keySet()) {
            resultList.add(resultMap.get(skey));
        }
        return resultList;
    }

}
