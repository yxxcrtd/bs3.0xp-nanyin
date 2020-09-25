/*
 * 文件名：BussRepEquityInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.project.BussRepEquityInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepEquityInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 权益类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月31日
 * @see BussRepEquityInfoImplService
 * @since 1.0
 */
@Service
public class BussRepEquityInfoImplService extends BaseService<BussRepEquityInfoDto> implements BussRepEquityInfoService{
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.交易流通场所,DictCode.投资阶段,
            DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分
            ,DictCode.股票类型,DictCode.人行机构规模,DictCode.受让形式};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepEquityInfoDto getBussRepEquityInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussRepEquityInfo/getBussRepEquityInfoByProductId?finprodId="
            + finprodId,BussRepEquityInfoDto.class);
    }
}
