package com.joyintech.tams.invest.risk.assetinfodetail.asset.placement.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepPlacementInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 拆放同业/买入返售（资产）||同业拆入/卖出回购（负债） Service
 * @author
 * @version 1.0 2021年01月15日
 * @see SvrBussRepPlacementInfoRiskImplService
 * @since 1.0
 */
@Service
public class SvrBussRepPlacementInfoRiskImplService extends BaseService<SvrBussRepPlacementInfoRiskDto>{

    @Autowired
    private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

    /**
     * Service主类方法
     * @param assetUUid
     * @param dataStatus
     * @param model
     */
    public void  setModle(String  assetUUid,String dataStatus,Model model){
        //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
        RskAssetInfo  rskAssetInfo=assetInfoDetailRisk001Service.BackChbAssetInfoMethod(dataStatus,assetUUid);

        //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
        SvrBussRepPlacementInfoRiskDto dto = new SvrBussRepPlacementInfoRiskDto();

        if (rskAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(rskAssetInfo,dto,DtoChangeUtil.target,false);
        }

        //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（拆放同业/买入返售（资产）||同业拆入/卖出回购（负债））);
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }

    public SvrBussRepPlacementInfoRiskDto getInfoByIssuer(@RequestBody Map<String, Object> param){
        SvrBussRepPlacementInfoRiskDto  dto=new  SvrBussRepPlacementInfoRiskDto();
        return dto;
    }

    /**
     * 回购交易的交易本金金额
     */
    public BigDecimal  getValue(String uuid){
        return  new BigDecimal(100);
    }

}
