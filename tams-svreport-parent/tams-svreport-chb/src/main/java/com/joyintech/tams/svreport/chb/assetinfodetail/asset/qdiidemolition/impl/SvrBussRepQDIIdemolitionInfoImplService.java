package com.joyintech.tams.svreport.chb.assetinfodetail.asset.qdiidemolition.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepQDIIdemolitionInfoDto;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * QDII拆出/逆回购资产 Service
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年01月15日
 * @see SvrBussRepQDIIdemolitionInfoImplService
 * @since 1.0
 */
@Service
public class SvrBussRepQDIIdemolitionInfoImplService extends BaseService<SvrBussRepQDIIdemolitionInfoDto>{

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
        SvrBussRepQDIIdemolitionInfoDto dto = new SvrBussRepQDIIdemolitionInfoDto();

        if (chbAssetInfo!=null){
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbAssetInfo,dto,DtoChangeUtil.target,false);
        }

        //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（QDII拆出/逆回购资产）);
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }

}
