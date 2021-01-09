/*

 * 文件名：BussFinBondRepInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.controller;

import com.joyintech.framework.annotation.SubPageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoController;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.dto.SvrBussFinBondRepInfoDto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 债券类--中债报送
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussFinBondRepInfoController
 * @since 1.0
 */
@Controller
@RequestMapping("/chb/assetinfodetail/asset/SvrBussFinBondRepInfo")
@SubPageId("SvrBussFinBondRepInfo")
public class SvrBussFinBondRepInfoController extends BaseController implements BussAssetInfoController {

    @Autowired
    private BussAssetInfoService bussAssetInfoService;

    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, bussAssetInfoService.getInitDictCode());
        setSelectOptionToModel(model, DictCode.债券性质);
        // 设置画面初始值
        setEntity(model);
        // 画面参数设置
        model.addAttribute("entityName", StringUtil.defaultString(this.getParam("entityName"), bussAssetInfoService.getDeftultEntity()));
        model.addAttribute("INT_ASSET_TYPE", this.getParam("int_asset_type"));
        model.addAttribute("issuerElement", this.getParam("issuerElement"));
        model.addAttribute("PARTY_TYPE2_01",DictCodeListExt2.发行机构类型_按经济类型划分_国有企业.getValue());
        model.addAttribute("PARTY_TYPE2_02",DictCodeListExt2.发行机构类型_按经济类型划分_集体企业.getValue());
        model.addAttribute("PARTY_TYPE2_03", DictCodeListExt2.发行机构类型_按经济类型划分_私营企业.getValue());
        model.addAttribute("PARTY_TYPE2_99",DictCodeListExt2.发行机构类型_按经济类型划分_其他.getValue());
        //项目类资产中债报送专用
        if(StringUtil.equals(this.getParam("int_asset_type"), DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL", createChbAssetType());
        }
        model.addAttribute("FINPROD_TYPE_TG_F18",DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        return "report/chb/assetinfodetail/asset/bond/SvrBussFinBondRepInfo";
    }

    @Override
    public void setEntity(Model model){
//        String finprodId = this.getParam("");
//            //不来自于代办
//        if(StringUtil.isNotEmpty(finprodId)) {
//            //根据主键获取业务实体-中债报送基本信息
//            SvrBussFinBondRepInfoDto bussFinBondRepInfoDto = bussAssetInfoService.getBussFinBondRepInfoByProductId(finprodId);
//            if(null!=bussFinBondRepInfoDto){
//                model.addAttribute("AssetTypeInit", bussFinBondRepInfoDto.getRepAssetType());
//            }
//            model.addAttribute(GlobalConstant.ENTITY, bussFinBondRepInfoDto);
//        }
    }
    
    private String createChbAssetType(){
        SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.资产_负债二级类别, null);
        if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
            List<SelectOptionDto> options = selectDto.getOptions();
            for (int index = options.size() - 1; index >= 0; index-- ) {
                SelectOptionDto option = options.get(index);
                if(!StringUtil.equals(option.getId(), DictCodeListExt2.资产_负债二级类别_理财直接融资工具.getValue())){
                    options.remove(option);
                }
            }
            return JsonUtils.objectArray2Json(options.toArray());
        }
        return null;
    }

    /**
     * 重写pageId {@inheritDoc}
     */
    @Override
    public String getPageId() {
        return this.getParam(GlobalConstant.PAGE_ID);
    }
    @ResponseBody
    @RequestMapping("getInfoByIssuer")
    public SvrBussFinBondRepInfoDto getInfoByIssuer(@RequestBody Map<String, Object> param){
        return bussAssetInfoService.getInfoByIssuer(param);
    }
}
