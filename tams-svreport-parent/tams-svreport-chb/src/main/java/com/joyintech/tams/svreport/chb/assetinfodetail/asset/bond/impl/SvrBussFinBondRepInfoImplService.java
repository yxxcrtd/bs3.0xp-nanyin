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
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetail001Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public String getDeftultEntity() {
        return "SvrBussFinBondRepInfoDto";
    }
    @Autowired
    private AssetInfoDetail001Service assetInfoDetail001Service;

    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.具体类别,
            DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,
            DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,
            DictCode.登记托管机构_中债,DictCode.交易流通场所,DictCode.人行机构规模,DictCode.受让形式};
    }

    @Override
    public AssetInfoDetailDto setEntity(AssetInfoDetailDto info) {
        SvrBussFinBondRepInfoDto assetBond = info.getSvrBussFinBondRepInfoDto();
//        info.setChbSectype(assetBond.getRepSectype());
//        info.setIssueMode(assetBond.getRepIssueMode());
//        info.setPartyGrade(assetBond.getRepPartyGrade());
//        info.setPartyType1(assetBond.getRepPartyType1());
//        info.setPartyType2(assetBond.getRepPartyType2());
//        info.setPartyType3(assetBond.getRepPartyType3());
//        info.setIndustry(assetBond.getRepIndustry());
//        info.setIndustryLevel2(assetBond.getRepIndustryLevel2());
//        info.setRegistOrg(assetBond.getRepRegistOrg());
//        info.setRegistorgComment(assetBond.getRepRegistorgComment());

        DtoChangeUtil.SetChbToRep(assetBond,info,DtoChangeUtil.target,true);
        return info;
    }

    @Override
    public Object getEntity(String assetUUid) {
        SvrBussFinBondRepInfoDto assetBond = new SvrBussFinBondRepInfoDto();
        //注意转换为ChbAssetInfo  否则反射不到
        ChbAssetInfo dto =assetInfoDetail001Service.getChbAssetInfoByUUid(assetUUid);
        //实体类转换方法
        DtoChangeUtil.SetChbToRep(dto,assetBond,DtoChangeUtil.target,false);
        return assetBond;
    }


}
