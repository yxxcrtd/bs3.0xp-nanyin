/*
 * 文件名：BussRepAssetCreditNonstdInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.project.BussRepAssetCreditNonstdInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepAssetCreditNonstdInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 信贷及非标资产中债报送 
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepAssetCreditNonstdInfoImplService
 * @since 1.0
 */
@Service
public class BussRepAssetCreditNonstdInfoImplService extends BaseService<BussRepAssetCreditNonstdInfoDto> implements BussRepAssetCreditNonstdInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.交易流通场所,DictCode.收受益权类型,
            DictCode.重点监控行业和领域类别,DictCode.担保方式,DictCode.资产评级,
            DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分
            ,DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,DictCode.行政区划_市,DictCode.资产评级,DictCode.人行机构规模,DictCode.受让形式};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepAssetCreditNonstdInfoDto getBussRepAssetCreditNonstdInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussRepAssetCreditNonstdInfo/getBussRepAssetCreditNonstdInfoByProductId?finprodId="
            + finprodId,BussRepAssetCreditNonstdInfoDto.class);
    }

}
