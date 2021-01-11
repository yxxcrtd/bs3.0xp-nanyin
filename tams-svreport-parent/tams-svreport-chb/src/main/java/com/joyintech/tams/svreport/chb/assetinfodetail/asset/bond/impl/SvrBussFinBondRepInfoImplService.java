/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.dto.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.financiall.dto.SvrBussRepFinanciallInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.placement.dto.SvrBussRepPlacementInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.qdiidemolition.dto.SvrBussRepQDIIdemolitionInfoDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussFinBondRepInfoImplService
 * @since 1.0
 */
@Service("SvrBussFinBondRepInfoImplService")
public class SvrBussFinBondRepInfoImplService extends BaseService<SvrBussFinBondRepInfoDto> implements BussAssetInfoService {

    @Override
    public String getDeftultEntity() {
        return "bussFinBondRepInfoDto";
    }

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
    public SvrBussFinBondRepInfoDto getBussFinBondRepInfoDto(SvrBussFinBondRepInfoDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SvrBussFinBondRepInfoDto getBussFinBondRepInfoByProductId(String finprodId) {
        return null;
    }
    /**
     * 根据发行人查询发行机构类型、发行机构所属行业
     */
    @Override
    public SvrBussFinBondRepInfoDto getInfoByIssuer(Map<String, Object> pageParam) {
        return null;
    }

    @Override
    public SvrBussRepPlacementInfoDto getplanceInfoByIssuer(Map<String, Object> pageParam) {
        return null;
    }

    @Override
    public SvrBussRepFinanciallInfoDto getFinanciallInfoByIssuer(Map<String, Object> pageParam) {
        return null;
    }

    @Override
    public SvrBussRepQDIIdemolitionInfoDto getQDIIdemolitionInfoByIssuer(Map<String, Object> pageParam) {
        return null;
    }
}
