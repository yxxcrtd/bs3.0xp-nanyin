package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 名单管理--资产名单新建
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagement006Controller
 * @since 1.0
 */
@Controller
@PageId("AssetNameManagement006")
@RequestMapping("/invest/risk/namelistmanage/assetnamelist/AssetNameManagement006")
public class AssetNameManagement006Controller extends BaseController {

    @Override
    public String init(Model model) {
        return super.init(model);
    }
}
