package com.joyintech.tams.invest.entrust.etrinstruction.service; /*
 * 文件名：
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：luocheng
 * 修改时间：2020/1/6
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.entrust.etrinstruction.dto.EtrInstructionDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class EtrInstructionService  extends CommonExportService<EtrInstructionDto>
    implements BaseProcessService<EtrInstructionDto> {


    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;

    /**
     * 流程完成时 进行相关的数据库操作
     */
    @Override public void doComplete(EtrInstructionDto entity,
                                     OperationType type) {
        entity.setInvManagerId(SecurityUtil.getLoginUser().getUserName());
        if("3".equals(entity.getPageModel())){
            entity.setOperateType("HTQS");
        }else{
            entity.setOperateType(type.toString());
        }
        basRiskMathCommService.insert(entity.getRiskTrialRetDto(), entity.getEntrustId());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/doComplete",
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
            insertDataAuthTypeInst(entity.getFinprodMarketId(), searchOrgCodeList, editOrgCodeList,DictCodeListExt3.共通业务类型_立项指令.getValue());
        }
    }

    /**
     * 新增操作时 保存上传附件 （不涉及指令表操作）
     */
    @Override
    public void doInsert(EtrInstructionDto entity, OperationType type){
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/doInsert",
            entity, Integer.class);
    }

    /**
     * 更新操作时 更新上传附件 （不涉及指令表操作）
     * {@inheritDoc}
     */
    @Override
    public void doUpdate(EtrInstructionDto entity){
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/doInsert",
            entity, Integer.class);
    }

    /**
     *
     * 删除、撤销、关闭委托校验方法
     * <功能详细描述>
     * @param dto EtrFundDto
     * @return List<ValidMessage>
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrInstructionDto dto) {
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/check", dto,
            ValidMessage.class);
    }

    /**
     * 立项指令查询 <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrInstructionDto> selectForPage(Map<String, Object> param) {
        User user = SecurityUtil.getLoginUser();
        param.put("userId", user.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/findPage", param,
            EtrInstructionDto.class);
    }
    /**
     * 撤回委托
     * <功能详细描述>
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(String entrustId) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/recall?entrustId="+entrustId,
            boolean.class);
    }
    /**
     * 关闭委托
     * <功能详细描述>
     * @param entrustId String
     * @param closeWay String
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId,String closeWay) {
        return restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrInstruction/close?entrustId="+entrustId+"&closeWay="+closeWay,
            boolean.class);
    }

    /**
     * 立项指令导出
     * @param exportParam
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrinstruction/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<EtrInstructionDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    /**
     *
     * 编辑、双击查看页面详情
     * 根据主键查询
     * @param entrustId String
     * @return EtrBrowDepoInfoDto
     * @see
     * @since 1.0
     */
    public EtrInstructionDto selectByKey(String entrustId){
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                         + "/EtrInstruction/selectByKey?entrustId=" + entrustId,
            EtrInstructionDto.class);
    }

    /**
     *
     * 资产代码联想文本框
     * <功能详细描述>
     * @param finprodId 资产代码
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFinprodMarketId(String finprodId){

        return restTemplate.getForList(ServiceApplication.ENTRUST_SERVICE_NAME+"/EtrInstruction/getFinprodMarketId?finprodId="+finprodId,
            SuggestDto.class);
    }

    /**
     *
     * 融资人/管理人 联想文本框
     * <功能详细描述>
     * @param repFinancier 资产代码
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getRepFinancierList(String repFinancier){
        return restTemplate.getForList(ServiceApplication.ENTRUST_SERVICE_NAME+"/EtrInstruction/getRepFinancierList?repFinancier="+repFinancier,
            SuggestDto.class);
    }
}
