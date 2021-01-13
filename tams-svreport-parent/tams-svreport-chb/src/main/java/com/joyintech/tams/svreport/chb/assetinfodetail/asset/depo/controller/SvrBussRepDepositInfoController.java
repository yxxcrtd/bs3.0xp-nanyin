/*
 * 文件名：BussRepDepositInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.impl.SvrBussRepDepositInfoImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 存款类--中债报送
 * @author zhangnian
 * @version 1.0 2018年9月18日
 * @see SvrBussRepDepositInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/SvrBussRepDepositInfo")
@SubPageId("SvrBussRepDepositInfo")
public class SvrBussRepDepositInfoController extends BaseController{

    @Autowired
    private SvrBussRepDepositInfoImplService bussAssetInfoService;
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussAssetInfoService.getInitDictCode());
        bussAssetInfoService.setModelAttribute(model,this.getRequest());
        return "report/chb/assetinfodetail/asset/depo/SvrBussRepDepositInfo";
    }

}
