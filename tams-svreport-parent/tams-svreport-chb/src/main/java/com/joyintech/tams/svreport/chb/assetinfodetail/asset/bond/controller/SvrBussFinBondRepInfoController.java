package com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.impl.SvrBussFinBondRepInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 债券类资产/理财直接融资工具/同业存单--中债报送
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月14日
 * @see SvrBussFinBondRepInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/SvrBussFinBondRepInfo")
@SubPageId("SvrBussFinBondRepInfo")
public class SvrBussFinBondRepInfoController extends BaseController{

    @Autowired
    private SvrBussFinBondRepInfoImplService bussAssetInfoService;

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
        setSelectOptionToModel(model,  new DictCode[] {DictCode.资产_负债二级类别,DictCode.具体类别,
                DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,
                DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,DictCode.债券类资产发行方式,
                DictCode.登记托管机构_中债,DictCode.交易流通场所,DictCode.人行机构规模,DictCode.受让形式,DictCode.债券性质,DictCode.发行方式,DictCode.主体评级_中债,DictCode.是否_中债}
         );


        //2、当入参assetType2资产/负债二级类别 为【01：债券类资产/理财直接融资工具/同业存单】进行以下处理
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue()).contains(assetType)){
              //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
        }
        model.addAttribute("entityName",this.getParam("entityName"));
        //3、返回 子组件对应的页面
        return "report/chb/assetinfodetail/asset/bond/SvrBussFinBondRepInfo";
    }

}
