/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.asset.trust.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepAssetMngProdInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepOutInvestAgrInfoDto;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * 〈一句话功能简述〉
 * 委外投资—协议方式 Service
 * @author
 * @version 1.0 2021年01月14日
 * @see SvrBussRepOutInvestAgrInfoImplService
 * @since 1.0
 */
@Service("SvrBussRepOutInvestAgrInfoImplService")
public class SvrBussRepOutInvestAgrInfoImplService extends BaseService<SvrBussRepOutInvestAgrInfoDto>  {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private RechbAassetInfoUtilService rechbAassetInfoUtilService;

    /**
     *Service 主类方法
     * @param assetUUid
     * @param dataStatus
     * @param model
     */
    public void  setModle(String  assetUUid,String dataStatus,Model model){
         //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
        ChbAssetInfo  chbAssetInfo=rechbAassetInfoUtilService.BackChbAssetInfoMethod(dataStatus,assetUUid);

       //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
        SvrBussRepOutInvestAgrInfoDto dto=new    SvrBussRepOutInvestAgrInfoDto();
        if(chbAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbAssetInfo,dto,DtoChangeUtil.target,false);
        }
       //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（委外投资—协议方式）);
        model.addAttribute(GlobalConstant.ENTITY, dto);

    }


}
