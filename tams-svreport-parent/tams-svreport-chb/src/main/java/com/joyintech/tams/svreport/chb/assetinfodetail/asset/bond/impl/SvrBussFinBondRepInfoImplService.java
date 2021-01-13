/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.svreport.chb.assetinfodetail.asset.bond.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.BussAssetInfoService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月23日
 * @see SvrBussFinBondRepInfoImplService
 * @since 1.0
 */
@Service("SvrBussFinBondRepInfoImplService")
public class SvrBussFinBondRepInfoImplService extends BaseService<SvrBussFinBondRepInfoDto> implements BussAssetInfoService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AssetInfoDetailMService assetInfoDetailMService;

    @Override
    public boolean getBussinessCode(String type2) {
        if(assetInfoDetailMService.getDataListLink().get(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue()).contains(type2)){
            return true;
        }
        return false;
    }

    @Override
    public void setModelAttribute(Model model, HttpServletRequest req) {
        // 画面参数设置
        model.addAttribute("entityName", req.getParameter("entityName"));
        model.addAttribute("PARTY_TYPE2_01",DictCodeListExt2.发行机构类型_按经济类型划分_国有企业.getValue());
        model.addAttribute("PARTY_TYPE2_02",DictCodeListExt2.发行机构类型_按经济类型划分_集体企业.getValue());
        model.addAttribute("PARTY_TYPE2_03", DictCodeListExt2.发行机构类型_按经济类型划分_私营企业.getValue());
        model.addAttribute("PARTY_TYPE2_99",DictCodeListExt2.发行机构类型_按经济类型划分_其他.getValue());

        model.addAttribute("FINPROD_TYPE_TG_F18", DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
        model.addAttribute("YES", DictCodeList.是否_中债_是.getValue());
        model.addAttribute("SVR_PARTY_TYPE1_01", DictCodeListExt3.发行机构类型_按规模划分_中小微企业.getValue());
        //赋值属性
        setEntity(model,req.getParameter("assetUuid"),req.getParameter("assetType"),req.getParameter("dataStatus"));
    }
    @Override
    public void setEntity(Model model,String assetUuid,String assetType,String dataStatus){
        SvrBussFinBondRepInfoDto dto = null;
        if(StringUtil.isNotEmpty(assetUuid)&&this.getBussinessCode(assetType)){
            dto = new SvrBussFinBondRepInfoDto();
            //注意转换为ChbAssetInfo  否则反射不到
            ChbAssetInfo chbDto =assetInfoDetailMService.getChbAssetInfoByUUid(assetUuid);
            //实体类转换方法
            DtoChangeUtil.SetChbToRep(chbDto,dto,DtoChangeUtil.target,false);
        }
        model.addAttribute(GlobalConstant.ENTITY, dto);
    }
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.具体类别,
                DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,
                DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类,
                DictCode.登记托管机构_中债,DictCode.交易流通场所,DictCode.人行机构规模,DictCode.受让形式,DictCode.债券性质};
    }

    @Override
    public AssetInfoDetailDto setEntity(AssetInfoDetailDto info) {
        SvrBussFinBondRepInfoDto assetBond = info.getSvrBussFinBondRepInfoDto();
        DtoChangeUtil.SetChbToRep(assetBond,info,DtoChangeUtil.target,true);
        info.setAssetType(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue());
        return info;
    }

}
