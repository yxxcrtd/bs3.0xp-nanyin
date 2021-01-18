package com.joyintech.tams.svreport.chb.assetinfodetail.asset.placement.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepPlacementInfoDto;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * 〈一句话功能简述〉
 * 拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） Service
 * @author
 * @version 1.0 2021年01月15日
 * @see SvrBussRepPlacementInfoImplService
 * @since 1.0
 */
@Service
public class SvrBussRepPlacementInfoImplService extends BaseService<SvrBussRepPlacementInfoDto>{

    @Autowired
    private RechbAassetInfoUtilService rechbAassetInfoUtilService;

    /**
     * Service主类方法
     * @param assetUUid
     * @param dataStatus
     * @param model
     */
    public void  setModle(String  assetUUid,String dataStatus,Model model){
        //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
        ChbAssetInfo  chbAssetInfo=rechbAassetInfoUtilService.BackChbAssetInfoMethod(dataStatus,assetUUid);

        //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
        SvrBussRepPlacementInfoDto dto = new SvrBussRepPlacementInfoDto();

        if (chbAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbAssetInfo,dto,DtoChangeUtil.target,false);
        }

        //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（拆放同业/买入返售（资产）||同业拆入/卖出回购（负债））);
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }


}
