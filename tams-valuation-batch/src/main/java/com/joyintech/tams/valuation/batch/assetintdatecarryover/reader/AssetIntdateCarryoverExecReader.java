/*
 * 文件名：AssetIntdateCarryoverReader.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverExecReaderResult;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.service.AssetIntdateCarryoverExecReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资产理论结息日结转 - 行权
 * @author yanya
 * @version 1.0 2021年3月11日
 * @see AssetIntdateCarryoverExecReader
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetIntdateCarryoverExecReader extends BaseReader<AssetIntdateCarryoverExecReaderResult> {

    /**
     * reader方法的 Service
     */
    @Autowired
    private AssetIntdateCarryoverExecReaderService assetIntdateCarryoverExecReaderService;

    /**
     * 读取数据 {@inheritDoc}
     */
    @Override
    public List<AssetIntdateCarryoverExecReaderResult> createList() {
        // 获取reader返回值
        List<AssetIntdateCarryoverExecReaderResult> resultList = assetIntdateCarryoverExecReaderService.execute(this.getParams());
        return resultList;
    }

}
