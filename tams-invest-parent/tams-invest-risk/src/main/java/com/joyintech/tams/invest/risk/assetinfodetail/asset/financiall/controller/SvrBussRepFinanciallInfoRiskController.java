/*

 * 文件名：BussFinBondRepInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.assetinfodetail.asset.financiall.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.financiall.impl.SvrBussRepFinanciallInfoRiskImplService;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 金融衍生品--中债报送
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年1月15日
 * @see SvrBussRepFinanciallInfoRiskController
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/risk/assetinfodetail/asset/financiall/SvrBussRepFinanciallInfoRisk")
@SubPageId("SvrBussRepFinanciallInfoRisk")
public class SvrBussRepFinanciallInfoRiskController extends BaseController{

    @Autowired
    private SvrBussRepFinanciallInfoRiskImplService bussAssetInfoService;

    @Autowired
    private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

    @Override
    public String init(Model model) {

        //获取请求参数
        HttpServletRequest requestPargram=this.getRequest();
        //UUID
        String    uuid=requestPargram.getParameter("assetUuid");
        //模式  ※err表记录：01； 资产要素表信息表：02；
        String    dataStatus=requestPargram.getParameter("dataStatus");
        //资产二级类别
        String   assetType=requestPargram.getParameter("assetType");

        //1.赋值界面数据字典
        setSelectOptionToModel(model,  new DictCode[] {DictCode.标的类别,DictCode.持有目的}
        );
        //2、当入参assetType2资产/负债二级类别 为【资产_负债类别_金融衍生品】进行以下处理
        if(assetInfoDetailRisk001Service.getDataListLink().get(DictCodeListExt2.资产_负债类别_金融衍生品.getValue()).contains(assetType)){
            //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }
        model.addAttribute("entityName",this.getParam("entityName"));


        return "invest/risk/assetinfodetail/asset/financiall/SvrBussRepFinanciallInfoRisk";
    }


}
