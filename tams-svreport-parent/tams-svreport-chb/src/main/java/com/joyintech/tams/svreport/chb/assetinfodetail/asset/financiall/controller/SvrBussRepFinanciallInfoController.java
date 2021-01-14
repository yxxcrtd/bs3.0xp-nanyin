/*

 * 文件名：BussFinBondRepInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.financiall.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.impl.SvrBussRepDepositInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.financiall.impl.SvrBussRepFinanciallInfoImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 债券类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussRepFinanciallInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/financiall/SvrBussRepFinanciallInfo")
@SubPageId("SvrBussRepFinanciallInfo")
public class SvrBussRepFinanciallInfoController extends BaseController{

    @Autowired
    private SvrBussRepFinanciallInfoImplService bussAssetInfoService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussAssetInfoService.getInitDictCode());

        bussAssetInfoService.setModelAttribute(model,this.getRequest());

        return "report/chb/assetinfodetail/asset/financiall/SvrBussRepFinanciallInfo";
    }


}
