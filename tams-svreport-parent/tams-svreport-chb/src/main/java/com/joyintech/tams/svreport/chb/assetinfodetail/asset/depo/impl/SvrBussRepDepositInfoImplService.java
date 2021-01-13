/*
 * 文件名：BussRepDepositInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.svreport.chb.assetinfodetail.asset.depo.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepDepositInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepDlRepoInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetail001Service;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 存款类资产中债报送
 * @author zhangnian
 * @version 1.0 2018年9月17日
 * @see SvrBussRepDepositInfoImplService
 * @since 1.0
 */
@Service
public class SvrBussRepDepositInfoImplService extends BaseService<SvrBussRepDepositInfoDto> implements BussAssetInfoService {

    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

    @Override
    public boolean getBussinessCode(String type2) {
        if(assetInfoDetailMService.getDataListLink().get(
                DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue()).contains(type2)){
            return true;
        }
        return false;
    }

    @Override
    public void setModelAttribute(Model model, HttpServletRequest req) {
        model.addAttribute("entityName", req.getParameter("entityName"));
        // 画面参数设置
        model.addAttribute("SVR_DEPO_TYPE_06", DictCodeListExt1.存款类型_结构性存款.getValue());
        model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL_1002", DictCodeListExt2.资产_负债二级类别_本行存款.getValue());
        model.addAttribute("SVR_ASSET_TYPE_SECOND_LEVEL_1003", DictCodeListExt2.资产_负债二级类别_他行存款.getValue());

        //赋值属性
        setEntity(model,req.getParameter("assetUuid"),req.getParameter("assetType"),req.getParameter("dataStatus"));
    }
    @Override
    public void setEntity(Model model,String assetUuid,String assetType,String  dataStatus){
        SvrBussRepDepositInfoDto dto = null;
        if(StringUtil.isNotEmpty(assetUuid)&&this.getBussinessCode(assetType)){
            dto = new SvrBussRepDepositInfoDto();
            //注意转换为ChbAssetInfo  否则反射不到
            ChbAssetInfo chbDto =assetInfoDetailMService.getChbAssetInfoByUUid(assetUuid);
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbDto,dto,DtoChangeUtil.target,false);
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }


    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.标的类别,DictCode.资产_负债二级类别,
                DictCode.是否_中债,DictCode.交易流通场所,DictCode.存款类型};
    }

    @Override
    public AssetInfoDetailDto setEntity(AssetInfoDetailDto info) {
        SvrBussFinBondRepInfoDto assetBond = info.getSvrBussFinBondRepInfoDto();
        DtoChangeUtil.SetChbToRep(assetBond,info,DtoChangeUtil.target,true);
        return info;
    }

}
