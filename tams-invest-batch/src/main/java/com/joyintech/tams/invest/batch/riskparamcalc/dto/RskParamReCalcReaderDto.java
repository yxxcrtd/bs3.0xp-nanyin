/*
 * 文件名：RskParamReCalcReaderDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2020年4月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.riskparamcalc.dto;

import java.util.List;
import java.util.Map;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 批处理-指标参数重新更新-ReaderDto
 * 
 * @author jjpei
 * @version 1.0 2020年7月24日
 * @see RskParamReCalcReaderDto
 * @since 1.0
 */
public class RskParamReCalcReaderDto extends NeedOperateEntity {

    /**
     * 实占信息
     */
    private List<Map<String, Object>> dataMapList;

    /**
     * 获取 实占信息
     * 
     * @return 实占信息.
     */
    public List<Map<String, Object>> getDataMapList() {
        return dataMapList;
    }

    /**
     * 设置 实占信息
     * 
     * @param dataMapList 实占信息
     */
    public void setDataMapList(List<Map<String, Object>> dataMapList) {
        this.dataMapList = dataMapList;
    }
}
