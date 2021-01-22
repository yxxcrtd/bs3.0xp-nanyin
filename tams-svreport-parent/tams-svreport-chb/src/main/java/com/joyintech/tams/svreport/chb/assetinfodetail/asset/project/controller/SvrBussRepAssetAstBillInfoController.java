package com.joyintech.tams.svreport.chb.assetinfodetail.asset.project.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.project.impl.SvrBussRepAssetAstBillInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.project.impl.SvrBussRepAssetCreditNonstdInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 票据类资产/信用证类资产--中债报送
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月14日
 * @see SvrBussRepAssetAstBillInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/project/SvrBussRepAssetAstBillInfo")
@SubPageId("SvrBussRepAssetAstBillInfo")
public class SvrBussRepAssetAstBillInfoController extends BaseController{

    @Autowired
    private SvrBussRepAssetAstBillInfoImplService bussAssetInfoService;

    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

    @Override
    public String init(Model model) {
        //获取请求参数
        HttpServletRequest  requestPargram=this.getRequest();
        //UUID
        String    uuid=requestPargram.getParameter("assetUuid");
        //模式  ※err表记录：01； 资产要素表信息表：02；
        String    dataStatus=requestPargram.getParameter("dataStatus");
        //资产二级类别
        String   assetType=requestPargram.getParameter("assetType");

        //1.赋值界面数据字典
        setSelectOptionToModel(model,   new DictCode[] {DictCode.是否_中债,DictCode.票据类信用证类资产类型,
                DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类}
         );

        //2、当入参assetType2资产/负债二级类别 为【票据类资产/信用证类资产】进行以下处理
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_票据类资产_信用证类资产.getValue()).contains(assetType)){
              //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }

        //3、返回 子组件对应的页面
        return "report/chb/assetinfodetail/asset/project/SvrBussRepAssetAstBillInfo";
    }

}
