package com.joyintech.tams.invest.risk.assetinfodetail.asset.project.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.project.impl.SvrBussRepEquityInfoRiskImplService;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 权益类资产--中债报送
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月14日
 * @see SvrBussRepEquityInfoRiskController
 * @since 1.0
 */
@Controller
@RequestMapping("/invest/risk/assetinfodetail/asset/project/SvrBussRepEquityInfoRisk")
@SubPageId("SvrBussRepEquityInfoRisk")
public class SvrBussRepEquityInfoRiskController extends BaseController{

    @Autowired
    private SvrBussRepEquityInfoRiskImplService bussAssetInfoService;

    @Autowired
    private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

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
        setSelectOptionToModel(model,   new DictCode[] {DictCode.股票类型,DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,
                DictCode.投资阶段,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,DictCode.发行机构类型_按技术领域划分,
                DictCode.是否_中债
               }
         );

        //2、当入参assetType2资产/负债二级类别 为【资产_负债类别_权益类资产】进行以下处理
        if(assetInfoDetailRisk001Service.getDataListLink().get(DictCodeListExt2.资产_负债类别_权益类资产.getValue()).contains(assetType)){
              //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }
        model.addAttribute("entityName",this.getParam("entityName"));

        //3、返回 子组件对应的页面
        return "invest/risk/assetinfodetail/asset/project/SvrBussRepEquityInfoRisk";
    }

}
