/*
 * 文件名：BussRepDlRepoInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月6日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.impl.SvrBussRepDlRepoInfoImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月6日
 * @see SvrBussRepDlRepoInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/SvrBussRepDlRepoInfo")
@SubPageId("SvrBussRepDlRepoInfo")
public class SvrBussRepDlRepoInfoController extends BaseController{

    @Autowired
    private SvrBussRepDlRepoInfoImplService bussAssetInfoService;
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussAssetInfoService.getInitDictCode());

        bussAssetInfoService.setModelAttribute(model,this.getRequest());

        return "report/chb/assetinfodetail/asset/depo/SvrBussRepDlRepoInfo";
    }

}
