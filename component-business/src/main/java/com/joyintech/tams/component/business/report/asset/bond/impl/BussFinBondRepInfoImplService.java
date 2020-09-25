/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.bond.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.bond.BussFinBondRepInfoService;
import com.joyintech.tams.component.business.report.asset.bond.dto.BussFinBondRepInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see BussFinBondRepInfoImplService
 * @since 1.0
 */
@Service
public class BussFinBondRepInfoImplService extends BaseService<BussFinBondRepInfoDto> implements BussFinBondRepInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.具体类别,
            DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,
            DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,
            DictCode.登记托管机构_中债,DictCode.交易流通场所,DictCode.人行机构规模,DictCode.受让形式};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussFinBondRepInfoDto getBussFinBondRepInfoDto(BussFinBondRepInfoDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussFinBondRepInfoDto getBussFinBondRepInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussFinBondRepInfo/getBussFinBondRepInfoByProductId?finprodId="
            + finprodId,BussFinBondRepInfoDto.class);
    }
    /**
     * 根据发行人查询发行机构类型、发行机构所属行业
     */
    @Override
    public BussFinBondRepInfoDto getInfoByIssuer(Map<String, Object> pageParam) {
        return restTemplate.postForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/getInfoByIssuer"
                ,pageParam,BussFinBondRepInfoDto.class);
    }
}
