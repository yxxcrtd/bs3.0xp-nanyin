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
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepFinanciallInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepPlacementInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 金融衍生品
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年 1月 15日
 * @see SvrBussRepFinanciallInfoImplService
 * @since 1.0
 */
@Service
public class SvrBussRepFinanciallInfoImplService extends BaseService<SvrBussRepFinanciallInfoDto> {


    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

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
        SvrBussRepFinanciallInfoDto dto=new    SvrBussRepFinanciallInfoDto();
        if(chbAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbAssetInfo,dto,DtoChangeUtil.target,false);
        }
        //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（债券类资产/理财直接融资工具/同业存单）);
        model.addAttribute(GlobalConstant.ENTITY, dto);

    }

}
