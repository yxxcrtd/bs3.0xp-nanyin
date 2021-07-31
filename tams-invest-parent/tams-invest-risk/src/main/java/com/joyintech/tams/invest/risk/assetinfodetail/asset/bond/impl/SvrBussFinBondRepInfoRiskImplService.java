/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.assetinfodetail.asset.bond.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussFinBondRepInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * 〈一句话功能简述〉
 * 债券类资产/理财直接融资工具/同业存单 Service
 * @author
 * @version 1.0 2021年01月14日
 * @see SvrBussFinBondRepInfoRiskImplService
 * @since 1.0
 */
@Service
public class SvrBussFinBondRepInfoRiskImplService extends BaseService<SvrBussFinBondRepInfoRiskDto>  {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

    /**
     *Service 主类方法
     * @param assetUUid
     * @param dataStatus
     * @param model
     */
    public void  setModle(String  assetUUid,String dataStatus,Model model){
        //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
        RskAssetInfo  rskAssetInfo=assetInfoDetailRisk001Service.BackChbAssetInfoMethod(dataStatus,assetUUid);

       //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
        SvrBussFinBondRepInfoRiskDto dto=new    SvrBussFinBondRepInfoRiskDto();
        if(rskAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(rskAssetInfo,dto,DtoChangeUtil.target,false);
        }
        dto.setRepSectype(rskAssetInfo.getChbSectype());
       //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（债券类资产/理财直接融资工具/同业存单）);
        model.addAttribute(GlobalConstant.ENTITY, dto);

    }


}
