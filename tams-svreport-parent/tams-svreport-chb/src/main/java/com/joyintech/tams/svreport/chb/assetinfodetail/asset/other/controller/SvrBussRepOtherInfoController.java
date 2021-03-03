package com.joyintech.tams.svreport.chb.assetinfodetail.asset.other.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.other.impl.SvrBussRepOtherInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 其他（负债）类资产--中债报送
 * 〈功能详细描述〉
 * @author wangqi
 * @version 1.0 2021年1月15日
 * @see SvrBussRepOtherInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/other/SvrBussRepOtherInfo")
@SubPageId("SvrBussRepOtherInfo")
public class SvrBussRepOtherInfoController extends BaseController {

    @Autowired
    private SvrBussRepOtherInfoImplService bussAssetInfoService;

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
        setSelectOptionToModel(model,  new DictCode[] {DictCode.所属国家或地区}
        );

        //2、当入参assetType2资产/负债二级类别 为【资产_负债类别_其他_负债_类资产】进行以下处理
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_其他_负债_类资产.getValue()).contains(assetType)){
            //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }
        model.addAttribute("entityName",this.getParam("entityName"));

        //3、返回 子组件对应的页面
        return "report/chb/assetinfodetail/asset/other/SvrBussRepOtherInfo";
    }



}
