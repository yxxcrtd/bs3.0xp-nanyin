/*
 * 文件名：RskValSumname002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.penetrationmanagement.rskvalsumname.dto.RskValSumnameDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年12月17日
 * @see RskValSumname002Service
 * @since 1.0
 */
@Service
public class RskValSumname002Service extends CommonExportService<RskValSumnameDto>{

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public RskValSumnameDto selectById(RskValSumnameDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValSumname002/selectById",dto,
            RskValSumnameDto.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public int insert(RskValSumnameDto dto) {
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
            insertDataAuthTypeInst(dto.getId(), searchOrgCodeList, editOrgCodeList, DictCodeList.共通业务类型_估值表汇总科目映射.getValue());
        }
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValSumname002/insert",
            dto,Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public int update(RskValSumnameDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValSumname002/update",
            dto,Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public int delete(RskValSumnameDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_SERVICE_NAME +"/RskValSumname002/delete",
            dto,Integer.class);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param name
     * @return
     * @see
     * @since 1.0
     */
    public List<RskValSumnameDto> selectByName(String name) {
//        return restTemplate.getForList(
//            ServiceApplication.RISK_SERVICE_NAME +"/RskValSumname002/selectByName?name="+name,
//            RskValSumnameDto.class);
        
        Map<String,Object> params = new HashMap<>();
        params.put("name", name);
		return restTemplate.postForList(ServiceApplication.RISK_SERVICE_NAME+"/RskValSumname002/selectByName", params , RskValSumnameDto.class);     

    }

}
