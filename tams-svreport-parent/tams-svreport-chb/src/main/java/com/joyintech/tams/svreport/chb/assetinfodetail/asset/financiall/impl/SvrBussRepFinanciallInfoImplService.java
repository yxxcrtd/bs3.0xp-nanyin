/*
 * 文件名：BussRepDlRepoInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.financiall.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepFinanciallInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepPlacementInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月5日
 * @see SvrBussRepFinanciallInfoImplService
 * @since 1.0
 */
@Service
public class SvrBussRepFinanciallInfoImplService extends BaseService<SvrBussRepFinanciallInfoDto> implements BussAssetInfoService {


    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;


    @Override
    public boolean getBussinessCode(String type2) {
        if(assetInfoDetailMService.getDataListLink().get(
                DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue()).contains(type2)){
            return true;
        }
        return false;
    }

    @Override
    public void setModelAttribute(Model model, HttpServletRequest req) {
        model.addAttribute("entityName", req.getParameter("entityName"));
        model.addAttribute("type6_01", DictCodeList.存款类资产类型_同业借款.getValue());
        model.addAttribute("type6_02",DictCodeList.存款类资产类型_存放同业.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        //中债报送关联上级页面数据字典
        if (StringUtil.isNotEmpty(req.getParameter("svr_asset_type_second_level"))){
            model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL",req.getParameter("svr_asset_type_second_level"));
        }
        model.addAttribute("ASSET_TYPE_SECOND_LEVEL_INIT", DictCodeListExt2.资产_负债二级类别_拆放同业.getValue());
        // 设置画面初始值
        //赋值属性
        setEntity(model,req.getParameter("assetUuid"),req.getParameter("assetType"),req.getParameter("dataStatus"));
    }
    @Override
    public void setEntity(Model model,String assetUuid,String assetType,String  dataStatus){

        SvrBussRepPlacementInfoDto dto = null;
        if(StringUtil.isNotEmpty(assetUuid)&&this.getBussinessCode(assetType)){
            dto = new SvrBussRepPlacementInfoDto();
            //注意转换为ChbAssetInfo  否则反射不到
            ChbAssetInfo chbDto =assetInfoDetailMService.getChbAssetInfoByUUid(assetUuid);
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbDto,dto,DtoChangeUtil.target,false);
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }

    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.标的类别,DictCode.持有目的
                };
    }

    @Override
    public AssetInfoDetailDto setEntity(AssetInfoDetailDto info) {
        SvrBussRepPlacementInfoDto assetPlace = info.getSvrBussRepPlacementInfoDto();
        DtoChangeUtil.SetChbToRep(assetPlace,info,DtoChangeUtil.target,true);
        //赋值资产类型
        info.setAssetType(DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue());
        return info;
    }


}
