/*
 * 文件名：MstBankTranFeeManageService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年3月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年3月22日
 * @see MstBankTranFeeManageService
 * @since 1.0
 */
@Service
public class MstBankTranFeeManageService extends CommonExportService<MstBankTranFeeManageDto> implements BaseProcessService<MstBankTranFeeManageDto> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(MstBankTranFeeManageDto entity, OperationType type) {
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
            insertDataAuthTypeInst(entity.getBatchSeq(), searchOrgCodeList, editOrgCodeList, DictCodeList.共通业务类型_银行间交易费用设置.getValue());
        }
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
        if (StringUtil.equals(entity.getChargeType(),BussinessDictCodeList.计费类型_固定费率.getValue())){
            entity.setFeeRate(StringUtil.remove(entity.getFeeRate(),"%"));
        }else {
            entity.setFeeRate(StringUtil.remove(entity.getFeeRate(),"元"));
        }
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBankTranFeeManage/" + action, entity,
            Integer.class);
    }

    /**
     * 根据页面条件查询数据
     * <功能详细描述>
     * @param parameters 查询参数
     * @return 数据
     * @see
     * @since 1.0
     */
    public Page<MstBankTranFeeManageDto> selectForPage(Map<String, Object> parameters) {
        Page<MstBankTranFeeManageDto> result = restTemplate.postForPage(ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBankTranFeeManage/selectForPage", parameters, MstBankTranFeeManageDto.class);
        List<MstBankTranFeeManageDto> dtoList = result.getRoot();
        for (MstBankTranFeeManageDto dto:dtoList){
            if (StringUtil.isNotEmpty(dto.getTradeMarketMode())){
                dto.setTradeMarketModeStr(SysDataUtil.getNameFromDataDict(DictCode.交易市场, dto.getTradeMarketMode()));
            }
            if (StringUtil.equals(dto.getChargeType(), BussinessDictCodeList.计费类型_固定费率.getValue())){
                dto.setFeeRate(ArithmeticUtil.round(new BigDecimal(dto.getFeeRate()),8,RoundingMode.HALF_UP).toString()+"%");
            }else {
                dto.setFeeRate(ArithmeticUtil.round(new BigDecimal(dto.getFeeRate()),2,RoundingMode.HALF_UP).toString()+"元");
            }
        }
        return result;
    }

    /**
     * 编辑页面根据流水号查询数据
     * <功能详细描述>
     * @param map 流水号
     * @return MstBankTranFeeManageDto
     * @see
     * @since 1.0
     */
    public MstBankTranFeeManageDto getEntity(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBankTranFeeManage/getEntity",
            map, MstBankTranFeeManageDto.class);
    }

    /**
     * 查询要删除的数据是否存在于数据库
     * <功能详细描述>
     * @param batchSeq 批次号
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean isExit(String batchSeq) {
        return restTemplate.getForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBankTranFeeManage/isExit?batchSeq="+batchSeq
            , boolean.class);
    }

    /**
     * 查询数据库中的所有数据
     * <功能详细描述>
     * @return MstBankTranFeeManageDto集合
     * @see
     * @since 1.0
     */
    public List<MstBankTranFeeManageDto> getAllDto() {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBankTranFeeManage/getAllDto", MstBankTranFeeManageDto.class);
    }

    /**
     * 根据pageId查询所有流程中的数据逐条进行对比
     * <功能详细描述>
     * @param parameter parameter
     * @return MstBankTranFeeManageDto
     * @see
     * @since 1.0
     */
    public List<MstBankTranFeeManageDto> getBusinessData(WflValidInputParameter parameter) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME +
            "/MstBankTranFeeManage/getBusinessData",parameter,
            MstBankTranFeeManageDto.class);
    }
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/basicdata/mstbanktranfeemanage/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MstBankTranFeeManageDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 费用设置查重
     * @param param 入参 tradeType:交易品种，ps:交易方向，termType:期限品种
     * @return 重复条数
     * @see
     * @since 1.0
     */
    public Integer selExistCnt(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstBankTranFeeManage/selExistCnt1",
            param, Integer.class);
    }

}
