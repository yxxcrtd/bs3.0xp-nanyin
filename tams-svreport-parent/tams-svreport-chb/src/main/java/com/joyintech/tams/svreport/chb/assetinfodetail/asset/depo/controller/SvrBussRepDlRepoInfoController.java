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
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.impl.SvrBussRepDlRepoInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 本行/他行存款||大额存单
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月15日
 * @see SvrBussRepDlRepoInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/SvrBussRepDlRepoInfo")
@SubPageId("SvrBussRepDlRepoInfo")
public class SvrBussRepDlRepoInfoController extends BaseController{

    @Autowired
    private SvrBussRepDlRepoInfoImplService bussAssetInfoService;

    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

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
        setSelectOptionToModel(model, new DictCode[] {
                DictCode.资产_负债二级类别,DictCode.交易流通场所});

        //2、当入参assetType2资产/负债二级类别 为【资产_负债类别_本行_他行存款_大额存单】进行以下处理
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue()).contains(assetType)){
            //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }

        return "report/chb/assetinfodetail/asset/depo/SvrBussRepDlRepoInfo";
    }

}
