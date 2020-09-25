/*
 * 文件名：PtlAccountCurrentInterest002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlaccountcurrentinterest.dto.PtlAccountCurrentInterest001Dto;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年10月12日
 * @see PtlAccountCurrentInterest002Service
 * @since 1.0
 */
@Service
public class PtlAccountCurrentInterest002Service  extends CommonExportService<PtlAccountCurrentInterest001Dto> 
implements BaseProcessService<PtlAccountCurrentInterest001Dto>{
    public List<SelectOptionDto> getcurrentRate(){
        return restTemplate.getForList(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/getcurrentRate",
            SelectOptionDto.class);
    }
    
    @Override
    public void doComplete(PtlAccountCurrentInterest001Dto entity, OperationType type) {
        setEntity(entity);
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/doCompelete", entity,
            Integer.class);
    }
    
    public PtlAccountCurrentInterest001Dto getInitInfo(Map<String,Object> map){
        return restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/getInitInfo", map,
            PtlAccountCurrentInterest001Dto.class);
    }
    
    public void setEntity(PtlAccountCurrentInterest001Dto dto){
        dto.setCashType1(DictCodeListExt1.现金流一级类型_利息.getValue());
        dto.setCashType2(DictCodeListExt1.现金流二级类型_利息.getValue());
        dto.setFinprodId(dto.getBusiAcctNo());
        dto.setBranch("0");
        dto.setFinprodType(DictCodeListExt1.现金流业务类型_资金账户.getValue());
    }
    
    public Date getLastEffDate(String acctNo){
        return restTemplate.getForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/getLastEffDate?acctNo="+acctNo,
            Date.class);
    }

    /**
     * 判断是否存在对应组合的信息
     * <功能详细描述>
     * @param dto 数据
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int countExistsData(PtlAccountCurrentInterest001Dto dto) {
        return restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/countExistsData",dto,
            Integer.class);
    }

    /**
     * 根据金融产品代码查询生效日最新的活期利率信息
     * @param map
     * @return
     */
    public PtlAccountCurrentInterest001Dto getInitInfoLastFinprodId(Map<String,Object> map){
        return restTemplate.postForObject(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlaccountcurrentinterest/getInitInfoLastFinprodId", map,
            PtlAccountCurrentInterest001Dto.class);
    }
}
