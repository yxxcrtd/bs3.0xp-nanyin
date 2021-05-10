/*
 * 文件名：ValManualVoucher003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年7月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dao.ValManualVoucherTemplete003Mapper;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003AddDto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete003SubjectDto;
import com.joyintech.tams.common.base.dao.ValManualVoucherTmpDetailMapper;
import com.joyintech.tams.common.base.dao.ValManualVoucherTmpMapper;
import com.joyintech.tams.common.base.entity.ValManualVoucherTmp;
import com.joyintech.tams.common.base.entity.ValManualVoucherTmpDetail;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.common.utils.DBUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 手工凭证模板新建Service
 * 
 * @author sunshuaishuai
 * @version 1.0 2021年2月9日
 * @see ValManualVoucherTemplete003Service
 * @since 1.0
 */
@Service(value="serviceValManualVoucherTemplete003Service")
@Transactional(rollbackFor = Exception.class)
public class ValManualVoucherTemplete003Service extends CommonExportService<ValManualVoucherTemplete003Dto> implements BaseProcessService<ValManualVoucherTemplete003Dto> {

    @Autowired
    private ValManualVoucherTemplete003Mapper valManualVoucherTemplete003Mapper;

    @Autowired
    private ValManualVoucherTmpMapper valManualVoucherTemplateMapper;

    @Autowired
    private ValManualVoucherTmpDetailMapper valManualVoucherTemplateDetailMapper;

    /**
     * DBUtil
     */
    @Autowired
    private DBUtil dBUtil;

    public ValManualVoucherTemplete001Dto getByTmpCode(String tmpCode) {
        return valManualVoucherTemplete003Mapper.getByTmpCode(tmpCode);
    }

    /**
     * 获取详情中列表信息
     *
     * @param tmpCode 模板编号
     * @return 详情中列表信息
     * @throws Exception 异常
     */
    public List<ValManualVoucherTemplete003AddDto> searchList(String tmpCode) {
        return valManualVoucherTemplete003Mapper.searchList(tmpCode);
    }

    /**
     *
     * 手工凭证科目号联想模糊查询
     *
     * @param para
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> querySubctNo(Map<String, Object> para){
        return valManualVoucherTemplete003Mapper.querySubjectNo(para);
    }

    /**
     * 手工凭证模板-删除功能
     * @param tmpIdList
     * @return
     */
    public boolean deleteVouch(List<String> tmpIdList) {
        if(!tmpIdList.isEmpty()){
            for(String tmpCode:tmpIdList){
                valManualVoucherTemplete003Mapper.deleteByPrimaryKey(tmpCode);
                valManualVoucherTemplete003Mapper.deleteByTmpCode(tmpCode);
            }
            return true;
        }
        return false;
    }

    @Override
    public void doComplete(ValManualVoucherTemplete003Dto entity, OperationType operationType) {
        // 新建操作
        if (OperationType.NEW.equals(operationType)) {
            restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/doSubmit",
                    entity, Integer.class);
        }
    }

    /**
     * 执行提交按钮处理
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean doSubmit(ValManualVoucherTemplete003Dto dto) {
        int count=0;
        // 手工凭证模板主表
        ValManualVoucherTmp entity=new ValManualVoucherTmp();
        entity.setTmpCode(dto.getTmpCode());
        entity.setTmpName(dto.getTmpName());
        entity.setRecordType(dto.getRecordType());
        entity.setBusiType(dto.getBusiType());
        entity.setRemark(dto.getRemark());
        valManualVoucherTemplateMapper.insert(entity);

        ValManualVoucherTmpDetail detail=new ValManualVoucherTmpDetail();
        for (ValManualVoucherTemplete003AddDto item : dto.getListAddDto()) {
            // 手工凭证模板明细表
            detail.setUuid(IDGenerator.generate());
            detail.setTmpCode(dto.getTmpCode());
            detail.setSubjectNo(item.getSubjectNo());
            detail.setSubjectName(item.getSubjectName());
            detail.setCcy(item.getCcy());
            String cdFlag = item.getCdFlag() ;
            item.setCdFlag(null);
            if(StringUtil.isNotEmpty(item.getcAmt()) && item.getcAmt()!=null){
                detail.setCdFlag(DictCodeList.借贷方向_贷.getValue());
                detail.setoHappenAmt(item.getcAmt());
            }else if (StringUtil.isNotEmpty(item.getdAmt()) && item.getdAmt()!=null){
                detail.setCdFlag(DictCodeList.借贷方向_借.getValue());
                detail.setoHappenAmt(item.getdAmt());
            }
            item.setCdFlag(cdFlag);
            detail.setHappenNumber(item.getHappenNumber());
            count+=valManualVoucherTemplateDetailMapper.insert(detail);
        }
        if(count>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 编辑
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean update(ValManualVoucherTemplete003Dto dto) {

        int count=0;
        String tmpCode =dto.getTmpCode();
        count+=valManualVoucherTemplete003Mapper.deleteByPrimaryKey(tmpCode);
        count+=valManualVoucherTemplete003Mapper.deleteByTmpCode(tmpCode);
        return doSubmit(dto);
    }

    /**
     * 科目号下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    public ValManualVoucherTemplete003SubjectDto getSubjectNo(String subjectNo) {
        // 查询科目信息
        ValManualVoucherTemplete003SubjectDto subjectdto = valManualVoucherTemplete003Mapper.getSubjectNo(subjectNo);
        if(subjectdto == null){
            subjectdto = new ValManualVoucherTemplete003SubjectDto();
        }
        return subjectdto;
    }

    public int queryPdSequences(){
        int i=valManualVoucherTemplete003Mapper.queryPdSequences();
        return i;
    }

    /**
     * 修改时，模板名称重复校验
     * @return
     */
    public int checkByTmpName(String tmpName,String tmpCode){
        int i=valManualVoucherTemplete003Mapper.checkByTmpName(tmpName,tmpCode);
        return i;
    }

    /**
     * 新建时，模板名称重复校验
     * @return
     */
    public int checkTmpName(String tmpName){
        int i=valManualVoucherTemplete003Mapper.checkTmpName(tmpName);
        return i;
    }

}
