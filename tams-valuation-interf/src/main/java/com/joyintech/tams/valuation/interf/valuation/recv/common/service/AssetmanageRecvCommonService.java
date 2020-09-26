/*
 * 文件名：AssetmanageCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.recv.common.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.interf.core.dao.InterfBaseMapper;
import com.joyintech.tams.valuation.interf.core.entity.InterfExecuteInfo;

@Service("assetmanageCommonService")
@Scope("prototype")
public class AssetmanageRecvCommonService extends BaseService {
    @Autowired
    private InterfBaseMapper interfBaseMapper;
    
    public List<InterfExecuteInfo> getInterfExceuteInfo(Map<String, Object> params) {
        return interfBaseMapper.getInterfExecuteInfo(params);
    }
    
    public List<String> getOrderNosByTempENames(Map<String, Object> params) {
        return interfBaseMapper.getOrderNosByTempENames(params);
    }
    //add by liupengfei for 多级法人改造 S
    public void insertDataAuthInst(String businessId,String institutionCode,String dataType){
            // 二级法人权限处理
            if(MultilevelDataAuthorityUtil.isEnabled()) {
                // 有查询权限的机构代码
                List<String> searchOrgCodeList = null;
                // 有编辑权限的机构代码
                List<String> editOrgCodeList = null;
                // 新建或修改时重新设置二级法人权限，删除时则清空
//                if (!OperationType.DELETE.equals(type)) {
                    //获取所有父级机构。  拥有查看权限
                    searchOrgCodeList = CommonUtil.getParentInstitution(institutionCode);
                    // 资产所属机构有编辑权限
                    editOrgCodeList = Arrays.asList(institutionCode);
//                }
                //入权限表
                insertDataAuthTypeInst(businessId, searchOrgCodeList, editOrgCodeList, dataType);
            }
    }
    //add by liupengfei for 多级法人改造 E
}
