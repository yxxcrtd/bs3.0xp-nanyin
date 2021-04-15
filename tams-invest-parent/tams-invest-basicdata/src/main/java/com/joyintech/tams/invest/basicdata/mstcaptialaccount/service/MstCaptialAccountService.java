/*
 * 文件名：MstCaptialAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.basicdata.mstcaptialaccount.service;

import java.util.*;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.system.base.entity.SysUploadFileInfo;
import com.joyintech.system.base.entity.SysWflBusinessList;
import com.joyintech.tams.invest.basicdata.mstcaptialaccount.dto.MstCaptialAccountDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 
 * 资金账户管理
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月20日
 * @see MstCaptialAccountService
 * @since 1.0
 */
@Service
public class MstCaptialAccountService extends CommonExportService<MstCaptialAccountDto> implements BaseProcessService<MstCaptialAccountDto> {

    /**
     * 使用给定的acctName检索分页数据。返回类型是Service的泛型。
     *
     * @param pageParam 查询方法
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<MstCaptialAccountDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/selectForPage",
            pageParam, MstCaptialAccountDto.class);
    }

    /**
     * 联想文本框service层实现
     *
     * @param param 检索参数
     * @return SuggestDto
     */
    public List<SuggestDto> getAcctName(String param) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/getAcctName?acctName="+param,
            SuggestDto.class);
    }

    /**
     * 根据网点账号与币种查询详细信息 双击修改页面
     * 
     * @param map 网点账号与币种
     * @return 资金账户实体
     * @see
     * @since 1.0
     */
    public MstCaptialAccountDto selectDetail(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/selectDetail",
            map, MstCaptialAccountDto.class);
    }

    /**
     * 实现docomplement方法
     */
    @Override
    public void doComplete(MstCaptialAccountDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/doComplete",
            entity, Integer.class); 
        // 二级法人权限处理
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //创建机构代码
            String createInst =   MultilevelDataAuthorityUtil.getService().queryOrganizationCode(entity.getCreateUser());
            entity.setCreateInst(createInst);
   
            // 有查询权限的机构代码
            List<String> searchOrgCodeList = null;
            // 有编辑权限的机构代码
            List<String> editOrgCodeList = null;
            // 新建或修改时重新设置二级法人权限，删除时则清空
            if (!OperationType.DELETE.equals(type)) {
                //获取所有父级机构。  拥有查看权限
                searchOrgCodeList = CommonUtil.getParentInstitution(createInst);
                // 仅资产的创建机构（当前登录用户所在机构）有编辑权限
                editOrgCodeList = Arrays.asList(entity.getCreateInst());
            }
            // 记录二级法人权限
            insertDataAuthTypeInst(entity.getSeqNo(), searchOrgCodeList, editOrgCodeList, DictCodeList.共通业务类型_资金账户管理.getValue());
        } 
    }

    @Override
    public void doUpdate(MstCaptialAccountDto entity) {
        //BSTOT-1123 资金账户-修改为外币账户，流程中币种未更新 guyunfei
        String workflowId = entity.getWorkflowId();
        String tradeId = entity.getLargePayno() + entity.getBusiAcctNo() + entity.getCcy();
        String title = "网点账号"+ "(" + entity.getLargePayno()+entity.getBusiAcctNo()+ ")"
                +  "-" + "账户名称"+ "(" + entity.getAcctName() + ")"  +  "-" + "币种"+
                "(" + SysDataUtil.getNameFromDataDict(DictCode.币种,entity.getCcy()) + ")";
        Map<String,Object> map = new HashMap<>();
        map.put("workFlowId",workflowId);
        map.put("title",title);
        map.put("updateUser",LoginUserUtil.getUserName());
        map.put("updateTime",DateUtil.format(new Date()));
        map.put("tradeId",tradeId);
        restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/updateTitle",
                map, Integer.class);
    }

    /**
     * 删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param dto 业务实体
     * @return 查询条数
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstCaptialAccountDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/checkCodeIsUse",
            dto, Integer.class);
    }

    /**
     * 批量删除检查数据是否被使用过 <功能详细描述>
     * 
     * @param item 资金账户实体集合
     * @return 返回查询的所有条数
     * @see
     * @since 1.0
     */
    public List<MstCaptialAccountDto> checkAllCodeIsUse(List<MstCaptialAccountDto> item) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/checkAllCodeIsUse",
            item, MstCaptialAccountDto.class);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/basicdata/mstcaptialaccount/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MstCaptialAccountDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    /**
     * 
     * 查询上传文件
     * <功能详细描述>
     * @param businessId businessId
     * @return List<FileArray>
     * @see
     * @since 1.0
     */
    public List<SysUploadFileInfo> selectFileByBusinessId(String businessId){
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/selectFileByBusinessId?businessId="+businessId,
            SysUploadFileInfo.class);
    }

    /**
     * 查询
     * <功能详细>
     * @param map Map
     * @return list
     * @see
     * @since 1.0
     */
    public List<MstCaptialAccountDto> select(Map<String, Object> map) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/select",
            map, MstCaptialAccountDto.class);
    }
    public List<MstCaptialAccountDto> selectCheck(Map<String, Object> map) {
    	return restTemplate.postForList(
    			ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/selectCheck",
    			map, MstCaptialAccountDto.class);
    }

    public int getUniqueAcctNoPayNo(Map<String, Object> map) {

        return restTemplate.postForObject(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/getUniqueAcctNoPayNo",
                map, Integer.class);

    }
	
	    /**
     * 
     * 获取当前节点信息
     * <功能详细描述>
     * @param workflowId
     * @return
     * @see
     * @since 1.0
     */
    public SysWflBusinessList getSysWflBusinessList(String workflowId) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/getSysWflBusinessList", workflowId,
            SysWflBusinessList.class);
    }
	
	/**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getOwnDept(Map<String, Object> map){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstCaptialAccount/getOwnDept", map,
            SelectOptionDto.class);        
    }
    
}
