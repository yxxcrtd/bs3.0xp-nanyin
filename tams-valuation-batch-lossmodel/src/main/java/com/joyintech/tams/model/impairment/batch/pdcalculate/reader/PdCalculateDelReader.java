/*
 * 文件名：AssetIntdateCarryoverReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.impairment.batch.pdcalculate.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.AssetImpairmentPdCalculateDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:PD计算的减值结果 - 读入
 * @auther: yuzj
 * @date: 2021/7/14
 * @param:
 * @return:
 */
@Service
@Scope("prototype")
public class PdCalculateDelReader extends BaseReader<AssetImpairmentPdCalculateDto> {

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<AssetImpairmentPdCalculateDto> createList() {

        // 获取reader返回值
        List<AssetImpairmentPdCalculateDto> resultList = new ArrayList<AssetImpairmentPdCalculateDto>();
        AssetImpairmentPdCalculateDto assetImpairmentPdCalculateDto =  new AssetImpairmentPdCalculateDto();
        resultList.add(assetImpairmentPdCalculateDto);
        return resultList;
    }

}
