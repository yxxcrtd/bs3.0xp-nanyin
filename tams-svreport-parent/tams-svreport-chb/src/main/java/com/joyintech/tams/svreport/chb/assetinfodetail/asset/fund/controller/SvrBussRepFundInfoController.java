package com.joyintech.tams.svreport.chb.assetinfodetail.asset.fund.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.fund.service.SvrBussRepFundInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.project.impl.SvrBussRepEquityInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 公募基金/私募基金--中债报送
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月14日
 * @see SvrBussRepFundInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/project/SvrBussRepFundInfo")
@SubPageId("SvrBussRepFundInfo")
public class SvrBussRepFundInfoController extends BaseController{

    @Autowired
    private SvrBussRepFundInfoImplService bussAssetInfoService;

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
        setSelectOptionToModel(model,   new DictCode[] {DictCode.政府投资基金投向,
                DictCode.是否_中债, DictCode.登记备案机构, DictCode.资产_负债二级类别,
                DictCode.营业日准则,DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类
                ,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,DictCode.发行机构类型_按技术领域划分
                ,DictCode.投资阶段,DictCode.交易流通场所,DictCode.人行机构规模,DictCode.受让形式}
         );

        //2、当入参assetType2资产/负债二级类别 为【公募基金/私募基金】进行以下处理
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_公募基金.getValue()).contains(assetType)||
                assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_私募基金.getValue()).contains(assetType)){

              //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }

        //3、返回 子组件对应的页面
        return "report/chb/assetinfodetail/asset/project/SvrBussRepFundInfo";
    }

}
