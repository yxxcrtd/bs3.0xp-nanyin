package com.joyintech.tams.invest.risk.assetinfodetail.asset.qdiiStructure.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepQDIIStructureInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * QDII结构性票据类资产 Service
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月15日
 * @see SvrBussRepQDIIStructureInfoRiskImplService
 * @since 1.0
 */
@Service
public class SvrBussRepQDIIStructureInfoRiskImplService extends BaseService<SvrBussRepQDIIStructureInfoRiskDto> {

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
        SvrBussRepQDIIStructureInfoRiskDto dto = new SvrBussRepQDIIStructureInfoRiskDto();

        if (rskAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(rskAssetInfo,dto,DtoChangeUtil.target,false);
        }

        //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（QDII拆出/逆回购资产）);
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }



}
