/*
 * 文件名：BussFinBondRepInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.risk.assetinfodetail.asset.qdiiBond.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskAssetInfo;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.invest.risk.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.invest.risk.assetinfodetail.dto.asset.SvrBussRepQDIIBondInfoRiskDto;
import com.joyintech.tams.invest.risk.assetinfodetail.service.AssetInfoDetailRisk001Service;

import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author WANGQI
 * @version 1.0 2021年01月18日
 * @see SvrBussQDIIBondRepInfoRiskImplService
 * @since 1.0
 */
@Service
public class SvrBussQDIIBondRepInfoRiskImplService extends BaseService<SvrBussRepQDIIBondInfoRiskDto>{

    private Logger logger = LoggerFactory.getLogger(getClass());


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
            SvrBussRepQDIIBondInfoRiskDto dto = mstWorkflowCommonService.getBusinessEntityByWorkFlowId(
                    workFlowId, entityName, SvrBussRepQDIIBondInfoRiskDto.class);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }else {
            //2、入参【UUID】不为空的情况下，进入以下处理：   //调用共通处理【画面展示数据处理共通】
            RskAssetInfo rskAssetInfo = assetInfoDetailRisk001Service.BackChbAssetInfoMethod(dataStatus, assetUUid);

            //3、以【2】取得的实体类为基础，进行子组件页面DTO组装
            SvrBussRepQDIIBondInfoRiskDto dto = new SvrBussRepQDIIBondInfoRiskDto();
            if (rskAssetInfo != null) {
                //实体类转换方法
                DtoChangeUtil.SetChbToRep(rskAssetInfo, dto, DtoChangeUtil.target, false);
            }
            //4、model.addAttribute(GlobalConstant.ENTITY, 子组件DTO（资产_负债类别_其他_负债_类资产）);
            model.addAttribute(GlobalConstant.ENTITY, dto);
        }
    }
}
