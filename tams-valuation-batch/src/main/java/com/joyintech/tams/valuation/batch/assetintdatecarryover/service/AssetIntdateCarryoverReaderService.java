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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.entity.SysBatchExecInfo;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dao.AssetIntdateCarryoverMapper;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderDetail;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderResult;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 资产理论结息日结转 - reader相关业务处理
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverReaderService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverReaderService {

    /**
     * reader方法的 Service
     */
    @Autowired
    private AssetIntdateCarryoverMapper assetIntdateCarryoverMapper;

    /**
     * 获取数据库数据
     * 
     * @param param 传递参数
     * @return List<BookDetailCompareIntoReadResult>
     * @see
     * @since 1.0
     */
    public List<AssetIntdateCarryoverReaderResult> execute(Map<String, Object> param) {

        // 获取task传递参数
        /** modify by dongjun on 20190328 通道子账套对应修改  start**/
//        @SuppressWarnings("unchecked")
//        List<String> booksetIdList = (List<String>)param.get(GlobalConstant.BOOKSETS);
//        SysBatchExecInfo batchExecuteInfo = (SysBatchExecInfo)param.get("BATCHINFO");

        param.put("finprodType1", DictCodeListExt1.金融产品类型_存款.getValue());
        param.put("finprodType2", DictCodeListExt1.金融产品类型_债券.getValue());
        param.put("finprodType3", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        param.put("finprodType4", DictCodeListExt1	.金融产品类型_同业借款.getValue());

        // 生成整体任务调度信息
        List<AssetIntdateCarryoverReaderDetail> detailList = assetIntdateCarryoverMapper.queryAssetIntdateCarryoverReaderDetail(param);
        /** modify by dongjun on 20190328 通道子账套对应修改  end**/

        // 按照金融产品代码和类型分类
        Map<String, Object> resultMap = new HashMap<>();
        String strKey = "";
        for (AssetIntdateCarryoverReaderDetail detail : detailList) {
            // 定义当前key
            strKey = detail.getFinprodId() + "-" + detail.getFinprodType();
            // 判断map中是否已含有该key的值
            if (resultMap.get(strKey) == null) {
                // 定义返回实体类
                AssetIntdateCarryoverReaderResult info = new AssetIntdateCarryoverReaderResult();
                info.setFinprodId(detail.getFinprodId());// 金融产品代码
                info.setFinprodType(detail.getFinprodType());// 金融产品类型
                info.getAssetIntdateCarryoverReaderDetailList().add(detail);// 添加到
                                                                            // 资产理论结息日结转明细信息List

                // 带入map中
                resultMap.put(strKey, info);
            }
            else {
                AssetIntdateCarryoverReaderResult info = (AssetIntdateCarryoverReaderResult)resultMap.get(
                    strKey);
                info.getAssetIntdateCarryoverReaderDetailList().add(detail);// 添加到
                                                                            // 资产理论结息日结转明细信息List

                // 带入map中
                resultMap.put(strKey, info);
            }
        }

        // 定义返回list
        List<AssetIntdateCarryoverReaderResult> resultList = new ArrayList<AssetIntdateCarryoverReaderResult>();

        // 将map装换为list
        for (String skey : resultMap.keySet()) {
            resultList.add((AssetIntdateCarryoverReaderResult)resultMap.get(skey));
        }

        return resultList;
    }

}
