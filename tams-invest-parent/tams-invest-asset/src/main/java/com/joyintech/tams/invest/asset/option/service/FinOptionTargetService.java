package com.joyintech.tams.invest.asset.option.service; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luo
 * 修改时间：2019-12-19
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.asset.option.dto.FinOptionTargetDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class FinOptionTargetService extends CommonExportService<FinOptionTargetDto> implements BaseProcessService<FinOptionTargetDto> {

    @Override public void doComplete(FinOptionTargetDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
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
            insertDataAuthTypeInst(entity.getTargetId(), searchOrgCodeList, editOrgCodeList, DictCodeListExt3.共通业务类型_期权标的管理.getValue());
        }
        restTemplate.postForObject(ServiceApplication.ASSET_SERVICE_NAME + "/FinOptionTarget/" + action, entity, Integer.class);
    }


    public List<SuggestDto> getFinprodcodeList(String param) {
        return restTemplate.getForList(
            ServiceApplication.ASSET_SERVICE_NAME +
            "/FinOptionTarget/getFinprodcodeList?getFinprodcodeList=" + param,
            SuggestDto.class);
    }

    /**
     * 根据id获取期权标的信息
     * @param code
     * @return
     */
    public FinOptionTargetDto getFinprodcodebyId(String code){
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME +"/FinOptionTarget/getFinprodcodebyId?code=" + code,
            FinOptionTargetDto.class);
    }



    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/asset/option/conf/export_conf.xml").dataSource(
            DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FinOptionTargetDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     * 分页获取期权标的信息
     * @param pageParam
     * @return
     */
    public Page<FinOptionTargetDto> selectForPage(Map<String, Object> pageParam) {
        return restTemplate.postForPage(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinOptionTarget/findPage",
            pageParam, FinOptionTargetDto.class);
    }
}
