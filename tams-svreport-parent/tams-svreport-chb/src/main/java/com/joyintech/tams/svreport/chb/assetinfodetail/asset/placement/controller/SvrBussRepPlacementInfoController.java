package com.joyintech.tams.svreport.chb.assetinfodetail.asset.placement.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.placement.impl.SvrBussRepPlacementInfoImplService;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepPlacementInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）--中债报送
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月15日
 * @see SvrBussRepPlacementInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/plancement/SvrBussRepPlacementInfo")
@SubPageId("SvrBussRepPlacementInfo")
public class SvrBussRepPlacementInfoController extends BaseController {

    @Autowired
    private SvrBussRepPlacementInfoImplService bussAssetInfoService;

    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

    @Override
    public String init(Model model) {
        //获取请求参数
        HttpServletRequest requestPargram = this.getRequest();
        //UUID
        String uuid = requestPargram.getParameter("assetUuid");
        //模式  ※err表记录：01； 资产要素表信息表：02；
        String dataStatus = requestPargram.getParameter("dataStatus");
        //资产二级类别
        String assetType = requestPargram.getParameter("assetType");
        BigDecimal value=BigDecimal.ZERO;
        //1.赋值界面数据字典
        setSelectOptionToModel(model, new DictCode[] {DictCode.币种_中债,DictCode.交易流通场所,DictCode.交易对手类型,
                DictCode.计息基础_中债,DictCode.回购标的类型,DictCode.对手方类型});

        //2、当入参assetType2资产/负债二级类别 为【拆放同业/买入返售（资产）||同业拆入/卖出回购（负债）】进行以下处理
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue()).contains(assetType)){
            //调用service
            bussAssetInfoService.setModle(uuid, dataStatus, model);
            value=bussAssetInfoService.getValue(uuid);
        }
        model.addAttribute("entityName",this.getParam("entityName"));
        model.addAttribute("value",value);

        //3、返回 子组件对应的页面
        return "report/chb/assetinfodetail/asset/plancement/SvrBussRepPlacementInfo";
    }

    @ResponseBody
    @RequestMapping(value="getInfoByIssuer",method = RequestMethod.POST)
    public SvrBussRepPlacementInfoDto getInfoByIssuer(@RequestBody Map<String, Object> param){
        return bussAssetInfoService.getInfoByIssuer(param);
    }

}
