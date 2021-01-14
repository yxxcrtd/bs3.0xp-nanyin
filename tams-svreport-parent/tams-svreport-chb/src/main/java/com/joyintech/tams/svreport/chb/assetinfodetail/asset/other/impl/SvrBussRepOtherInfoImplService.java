/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.asset.other.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepOtherInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussRepOtherInfoImplService
 * @since 1.0
 */
@Service("SvrBussRepOtherInfoImplService")
public class SvrBussRepOtherInfoImplService extends BaseService<SvrBussRepOtherInfoDto> implements BussAssetInfoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;


    @Override
    public boolean getBussinessCode(String type2) {
        return false;
    }

    @Override
    public void setModelAttribute(Model model, HttpServletRequest req) {

    }

    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.所属国家或地区};
    }

    @Override
    public void setEntity(Model model, String assetUuid, String assetType2, String dataStatus) {

    }

    @Override
    public AssetInfoDetailDto setEntity(AssetInfoDetailDto info) {
        return null;
    }
}
