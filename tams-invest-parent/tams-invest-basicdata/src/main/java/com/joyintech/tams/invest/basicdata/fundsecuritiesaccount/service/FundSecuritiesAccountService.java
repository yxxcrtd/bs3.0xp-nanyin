/*
 * 文件名：MstSecuritiesAccountSerice.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lizhi
 * 修改时间：2020年1月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.tams.constant.DictCodeListExt3;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.basicdata.fundsecuritiesaccount.dto.FundSecuritiesAccountDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 基金账户管理Service
 * 〈功能详细描述〉
 * @author lizhi
 * @version 1.0 2020年1月14日
 * @see FundSecuritiesAccountService
 * @since 1.0
 */
@Service
public class FundSecuritiesAccountService extends CommonExportService<FundSecuritiesAccountDto> implements BaseProcessService<FundSecuritiesAccountDto>{

    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<FundSecuritiesAccountDto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/FundSecuritiesAccountInfo/findPage",
            pageParam,FundSecuritiesAccountDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/fundsecuritiesaccount/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    /**
     * 
     * 导出数据查询
     */
    @Override
    public Page<FundSecuritiesAccountDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    /**
     * 双击修改界面
     * @param secAcctId String
     * @return Page类
     * @throws Exception 异常信息
     */
    public FundSecuritiesAccountDto selectBySecAcctId(String secAcctId) {
        return restTemplate.getForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/FundSecuritiesAccountInfo/selectBySecAcctId?secAcctId="
                                         + secAcctId,
            FundSecuritiesAccountDto.class);
    }
    /**
     * 基金账户账号联想文本框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String secAcctId) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/FundSecuritiesAccountInfo/getSecAcctId?secAcctId="+secAcctId,
            SuggestDto.class);
    }
    /**
     * 基金管理人联想文本框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecManager(String manager) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/FundSecuritiesAccountInfo/getSecManager?manager="+manager,
            SuggestDto.class);
    }
    /**
     * 基金管理人下拉框service层实现
     *
     * @param secAcctId String
     * @return SuggestDto
     */
    public List<SelectOptionDto> getSecManagerSelect(String search) {
        List<SelectOptionDto> tempList = restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/FundSecuritiesAccountInfo/getSecManagerSelect?manager="+search,
            SelectOptionDto.class);
        return tempList ;
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
    public FundSecuritiesAccountDto fileUpload(HttpServletRequest request) 
        throws Exception{
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/FundSecuritiesAccountInfo/uploadFile",
            request,FundSecuritiesAccountDto.class);
    }
    /**
     * 工作流（留痕处理进行的操作）
     */
    @Override
    public void doComplete(FundSecuritiesAccountDto entity, OperationType type) {
        String action=StringUtil.EMPTY;
        if(OperationType.DELETE.equals(type)){
            action="delete";
        }
        if(OperationType.NEW.equals(type)){
            action="insert";
        }
        if(OperationType.MODIFY.equals(type)){
            action="update";
        }
        restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME+"/FundSecuritiesAccountInfo/"+action, entity, Integer.class);
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
            insertDataAuthTypeInst(entity.getSecAcctId(), searchOrgCodeList, editOrgCodeList, DictCodeListExt3.共通业务类型_基金账户管理.getValue());
        }  
    }
    /**
     * 检查基金账户账号是否用过
     * @param dto FundSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkBySecAcctId(FundSecuritiesAccountDto dto) {
        int count=0;
        count= restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
            + "/FundSecuritiesAccountInfo/checkBySecAcctId",
            dto,Integer.class);
        return count;
    }
    /**
     * 检查基金账户名称是否用过
     * @param dto FundSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkBySecAcctName(FundSecuritiesAccountDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/FundSecuritiesAccountInfo/checkBySecAcctName",
                                         dto,
                                         Integer.class);
    }
    /**
     * 检查资金结算账户是否存在
     * @param dto FundSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkByTrdAcctId(FundSecuritiesAccountDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/FundSecuritiesAccountInfo/checkByTrdAcctId",
                                         dto,
                                         Integer.class);
    }
    /**
     * 删除时检查数据是否被使用过
     * @param dto FundSecuritiesAccountDto
     * @return Page类
     * @throws Exception 异常信息
     */
    public int checkCodeIsUse(FundSecuritiesAccountDto dto) {
        return restTemplate.postForObject(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/FundSecuritiesAccountInfo/checkCodeIsUse",
                                         dto,
                                         Integer.class);
    }
    /**
     * 批量删除时检查数据是否被使用过
     * @param item List<FundSecuritiesAccountDto>
     * @return Page类
     * @throws Exception 异常信息
     */
    public List<FundSecuritiesAccountDto> checkAllCodeIsUse(List<FundSecuritiesAccountDto> item) {
        return restTemplate.postForList(ServiceApplication.BASICDATA_SERVICE_NAME
                                         + "/FundSecuritiesAccountInfo/checkAllCodeIsUse",
                                         item,
                                         FundSecuritiesAccountDto.class);
    }
   
}
