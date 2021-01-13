/*
 * 文件名：RskValAssetCode002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalassetcode.dto.RskValtblAssetSubMapDto;

/**
 * 资产科目映射
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年12月26日
 * @see RskValAssetCode002Service
 * @since 1.0
 */
@Service
public class RskValAssetCode002Service extends BaseService<RskValtblAssetSubMapDto>{
    
    /**
     * 资产科目映射修改初始化
     * <功能详细描述>
     * @param id
     * @return
     * @see
     * @since 1.0
     */
    public RskValtblAssetSubMap selectAssetCodeByPrimaryKey(String id){
        return restTemplate.getForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValAssetCode/selectAssetCodeByPrimaryKey?id="+id,
            RskValtblAssetSubMap.class);
    }
    
    /**
     * 映射名称是否重复
     * <功能详细描述>
     * @param name
     * @param id
     * @return
     * @see
     * @since 1.0
     */
    public int selecctCountByName(String name,String id){
//        return restTemplate.getForObject(
//            ServiceApplication.RISK_SERVICE_NAME + "/RskValAssetCode/selecctCountByName?id="+id+"&name="+name,
//            Integer.class);
        
        Map<String,Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
		return restTemplate.postForObject(ServiceApplication.RISK_SERVICE_NAME+"/RskValAssetCode/selecctCountByName", params , Integer.class);     

    }
    /**
     * 保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void insert(RskValtblAssetSubMapDto dto) {
        // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(dto.getCreateUser());
            dto.setCreateInst(createInst);

            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            //获取所有父级机构。  拥有查看权限
            searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
            // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
            editOrgCodeList = Arrays.asList(dto.getCreateInst());
            // 记录二级法人权限
            insertDataAuthTypeInst(dto.getId(), searchOrgCodeList, editOrgCodeList, DictCodeList.共通业务类型_估值表资产科目映射.getValue());
        }
        restTemplate.postForObject(
           ServiceApplication.RISK_SERVICE_NAME +"/RskValAssetCode/saveAssetCode",
           dto,Integer.class);
   }
    
}
