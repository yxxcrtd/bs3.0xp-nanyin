/*
 * 文件名：BussRepDlRepoInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.assetinfodetail.asset.financiall.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepFinanciallInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * 金融衍生品
 * 〈功能详细描述〉
 * @author
 * @version 1.0 2021年 1月 15日
 * @see SvrBussRepFinanciallInfoRiskImplService
 * @since 1.0
 */
@Service
public class SvrBussRepFinanciallInfoRiskImplService extends BaseService<SvrBussRepFinanciallInfoRiskDto> {


    @Autowired
    private AssetInfoDetailRisk001Service assetInfoDetailRisk001Service;

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
            SvrBussRepFinanciallInfoRiskDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                    workFlowId, entityName, SvrBussRepFinanciallInfoRiskDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }else {
            //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
            RskAssetInfo rskAssetInfo = assetInfoDetailRisk001Service.BackChbAssetInfoMethod(dataStatus, assetUUid);

            //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
            SvrBussRepFinanciallInfoRiskDto dto = new SvrBussRepFinanciallInfoRiskDto();
            if (rskAssetInfo != null) {
                //实体类转换方法
                DtoChangeUtil.SetChbToRep(rskAssetInfo, dto, DtoChangeUtil.target, false);
            }
            //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（债券类资产/理财直接融资工具/同业存单）);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
    }

}
