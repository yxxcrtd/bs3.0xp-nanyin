/*
 * 文件名：MstSecuritiesAccountSerice.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.system.base.dao.SysWflBusinessListMapper;
import com.joyintech.system.base.entity.SysWflBusinessList;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.workflow.engine.entity.FlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 证券账户管理Service
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月11日
 * @see MstSecuritiesAccountService
 * @since 1.0
 */
@Service
public class MstSecuritiesAccountService extends CommonExportService<MstSecuritiesAccountDto> implements BaseProcessService<MstSecuritiesAccountDto>{

    @Autowired
    private SysWflBusinessListMapper sysWflBusinessListMapper;

    /**
     * 获取工作流当前节点信息
     * @param workflowId
     * @return
     */
    public SysWflBusinessList getWorkFlowData(String workflowId){
        return sysWflBusinessListMapper.selectByPrimaryKey(workflowId);
    }
    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<MstSecuritiesAccountDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/findPage",
            pageParam,MstSecuritiesAccountDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstsecuritiesaccount/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 
     * 导出数据查询
     */
    @Override
    public Page<MstSecuritiesAccountDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 双击修改界面
     * @param secAcctId String
     * @return Page类
     * @throws Exception 异常信息
     */
    public MstSecuritiesAccountDto selectBySecAcctId(String secAcctId) {
        return restTemplate.getForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/MstSecuritiesAccountInfo/selectBySecAcctId?secAcctId="
                                         + secAcctId,
            MstSecuritiesAccountDto.class);
    }
    /**
     * 证券账户账号联想文本框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String secAcctId) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/getSecAcctId?secAcctId="+secAcctId,
            SuggestDto.class);
    }
    
    /**
     * 
     * 组合代码联想文本框
     * <功能详细描述>
     * @param portfolioId 组合代码
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioId(String portfolioId){
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("portfolioId", portfolioId);
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME+"/MstSecuritiesAccountInfo/getPortfolioId",
            map, SuggestDto.class);
    }
    
    /**
     * 回款账户以及资金账户联想文本框service层实现
     *
     * @param busiAcctNo String
     * @return SuggestDto
     */
    public List<SelectOptionDto> getBusiAcctNo(String busiAcctNo) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/getBusiAcctNo?busiAcctNo="+busiAcctNo,
            SelectOptionDto.class);
    }
    
    /**
     * 券商名称联想下拉框
     * @param brokerName
     * @return
     */
    public List<SelectOptionDto> getBrokerName(String brokerName){
    	return restTemplate.getForList(
				ServiceApplication.BASICDATA_SERVICE_NAME+"/MstMarketInstitutions/getBrokerName?brokerName="+brokerName,
				SelectOptionDto.class);
    }
    /**
     * 文件读取
     *
     * @param request request
     * @return 导入文件信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public MstSecuritiesAccountDto fileUpload(HttpServletRequest request) 
        throws Exception{
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/uploadFile",
            request,MstSecuritiesAccountDto.class);
    }
    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(MstSecuritiesAccountDto entity, OperationType type) {
        String action=StringUtil.EMPTY;
        handleEntity(entity);
        if(OperationType.DELETE.equals(type)){
            action="delete";
        }
        if(OperationType.NEW.equals(type)){
            action="insert";
        }
        if(OperationType.MODIFY.equals(type)){
            action="update";
        }
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME+"/MstSecuritiesAccountInfo/"+action, entity, Integer.class);
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
            insertDataAuthTypeInst(entity.getSecAcctId(), searchOrgCodeList, editOrgCodeList, DictCodeList.共通业务类型_证券账户管理.getValue());
        } 
    }
	
	    private  void handleEntity(MstSecuritiesAccountDto entity){
        if(!StringUtil.equals(entity.getRegistOrg(),"03")){
            entity.setTradePlace(entity.getTradePlace2());
        }
    }
    /**
     * 检查证券账户账号是否用过
     * @param dto MstSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkBySecAcctId(MstSecuritiesAccountDto dto) {
        int count=0;
        count= restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
            + "/MstSecuritiesAccountInfo/checkBySecAcctId",
            dto,Integer.class);
        return count;
    }
    /**
     * 检查证券账户名称是否用过
     * @param dto MstSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkBySecAcctName(MstSecuritiesAccountDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/MstSecuritiesAccountInfo/checkBySecAcctName",
                                         dto,
                                         Integer.class);
    }
    /**
     * 检查资金结算账户是否存在
     * @param dto MstSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkByTrdAcctId(MstSecuritiesAccountDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/MstSecuritiesAccountInfo/checkByTrdAcctId",
                                         dto,
                                         Integer.class);
    }
    /**
     * 删除时检查数据是否被使用过
     * @param dto MstSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkCodeIsUse(MstSecuritiesAccountDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/MstSecuritiesAccountInfo/checkCodeIsUse",
                                         dto,
                                         Integer.class);
    }
    /**
     * 批量删除时检查数据是否被使用过
     * @param item List<MstSecuritiesAccountDto>
     * @return Page类
     * @throws Exception 异常信息
     */
    public List<MstSecuritiesAccountDto> checkAllCodeIsUse(List<MstSecuritiesAccountDto> item) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/MstSecuritiesAccountInfo/checkAllCodeIsUse",
                                         item,
                                         MstSecuritiesAccountDto.class);
    }
//    /**
//     * 查询上传的文件
//     * @param businessId String
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public List<FileDto> selectFileByBusinessId(String businessId) {
//        return restTemplate.getForList(ServiceApplication.BASICDATA_SERVICE_NAME
//                                         + "/MstSecuritiesAccountInfo/selectFileByBusinessId?businessId="
//                                         +businessId,
//                                         FileDto.class);
//    }
//    /**
//     * 插入附件文件
//     * @param dto FileDto
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int insertIntoFile(FileDto dto) {
//        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
//                                         + "/MstSecuritiesAccountInfo/insertIntoFile",
//                                         dto,
//                                         Integer.class);
//    }
//    /**
//     * 删除附件文件
//     * @param businessId String
//     * @return Page类
//     * @throws Exception 异常信息
//     */
//    public int deleteFileByBusinessId(String businessId) {
//        return restTemplate.getForObject(ServiceApplication.BASICDATA_SERVICE_NAME
//                                         + "/MstSecuritiesAccountInfo/deleteFileByBusinessId?businessId="
//                                         +businessId,
//                                         Integer.class);
//    }
    
    /**
     * 期货公司检索
     * @param search
     * @return
     */
    public List<SelectOptionDto> getFuturesCompany(String search) {
        return restTemplate.getForList(
                ServiceApplication.BASICDATA_SERVICE_NAME + "/MstSecuritiesAccountInfo/getFuturesCompany?companyNo="+search,
                SelectOptionDto.class);
    }

    @Override
    public String checkForSubmit(FlowEntity entity, boolean isMutiApproval) {
        if(isMutiApproval){
            return "证券账户管理,不可批量!";
        }
        return null;
    }
}
