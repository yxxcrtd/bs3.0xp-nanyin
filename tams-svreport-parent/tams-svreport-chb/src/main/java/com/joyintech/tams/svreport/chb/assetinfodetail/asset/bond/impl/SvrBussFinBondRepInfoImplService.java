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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbAssetInfo;
import com.joyintech.tams.disclosure.common.common.service.MstWorkflowCommonService;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussFinBondRepInfoDto;
import com.joyintech.tams.svreport.chb.common.util.asset.RechbAassetInfoUtilService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
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
 * @see SvrBussFinBondRepInfoImplService
 * @since 1.0
 */
@Service("SvrBussFinBondRepInfoImplService")
public class SvrBussFinBondRepInfoImplService extends BaseService<SvrBussFinBondRepInfoDto>  {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private RechbAassetInfoUtilService rechbAassetInfoUtilService;

    @Autowired
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     *Service 主类方法
     * @param assetUUid
     * @param dataStatus
     * @param model
     */
    public void  setModle(String  assetUUid,String dataStatus,Model model,String workFlowId,String entityName){
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workFlowId)) {// 判断是否存在于工作流中
            SvrBussFinBondRepInfoDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                    workFlowId, entityName, SvrBussFinBondRepInfoDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }else {
            //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
            ChbAssetInfo chbAssetInfo = rechbAassetInfoUtilService.BackChbAssetInfoMethod(dataStatus, assetUUid);

            //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
            SvrBussFinBondRepInfoDto dto = new SvrBussFinBondRepInfoDto();
            if (chbAssetInfo != null) {
                //实体类转换方法
                DtoChangeUtil.SetChbToRep(chbAssetInfo, dto, DtoChangeUtil.target, false);
            }
            dto.setRepSectype(chbAssetInfo.getChbSectype());
            //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（债券类资产/理财直接融资工具/同业存单）);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
    }


}
