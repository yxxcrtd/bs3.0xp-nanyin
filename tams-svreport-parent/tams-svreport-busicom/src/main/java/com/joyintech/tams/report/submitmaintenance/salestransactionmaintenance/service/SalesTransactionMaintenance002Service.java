/*
 * 文件名：EquityInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：天津滨海农商行理财资管系统
 * 修改人：dingzhipeng
 * 修改时间：2018年5月17日
 * 修改内容：新建
 * 系统名称：天津滨海农商行理财资管系统
 */

package com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dao.SalesTransactionMaintenanceMapper;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.dto.SalesTransactionMaintenanceDto;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.validate.SalesTransactionMaintenanceValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 销售结算资金交易信息维护新建
 */
@Service
public class SalesTransactionMaintenance002Service extends BaseService{
    /**
     * check工具类
     */
    @Autowired
    private SalesTransactionMaintenanceValid salestransactionmaintenanceValid;
    /**
     * 001
     */
    @Autowired
    private SalesTransactionMaintenance001Service salestransactionmaintenance001Service;

    /**
     * 001
     */
    @Autowired
    private SalesTransactionMaintenanceMapper salestransactionmaintenanceMapper;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 待验证数据
     * @param isSave 是否保存
     * @return
     * @see
     * @since 1.0
     */
    public AjaxResult checkOrSave(SalesTransactionMaintenanceDto dto, boolean isSave) {

         return null;
    }
    /**
     * 双击查看详细信息查询 修改页面信息查询 info
     *
     */
    public SalesTransactionMaintenanceDto selectDetail(Map<String,Object> param) {
        return salestransactionmaintenanceMapper.selectDetail(param);
    }

    /**
     * 双击查看详细信息查询 修改页面信息查询 erro
     *
     */
    public SalesTransactionMaintenanceDto selectErroDetail(Map<String,Object> param) {
        return salestransactionmaintenanceMapper.selectErroDetail(param);
    }

    /**
     * 保存
     * @param
     */
    public void save(SalesTransactionMaintenanceDto dto) {
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setRegistSeqno(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        dto.setDataStatus("E");
        salestransactionmaintenanceMapper.insert(dto);
    }

    /**
     * 修改
     * @param
     */
    public void update(SalesTransactionMaintenanceDto dto) {
        dto.setDataStatus("M");
        if(dto.getMaintainStatus().equals("01") || dto.getRegistSeqno().isEmpty()) {
            dto.setRegistSeqno(UUID.randomUUID().toString().replace("-", "").toUpperCase());
            String institutionCode = dto.getInstitutionCode();
            String tradeNo = dto.getTradeNo();
            salestransactionmaintenanceMapper.deleteByPrimaryKey(institutionCode,tradeNo);
        }
        salestransactionmaintenanceMapper.update(dto);
    }

    /**
     * 删除erro
     */
    public boolean deleteByPrimaryKey(SalesTransactionMaintenanceDto dto){
        List<SalesTransactionMaintenanceDto> dtolist = dto.getSelectedItem();
        if (!dtolist.isEmpty() && dtolist.size() != 0) {
            for (int i = 0 ; i < dtolist.size() ; i++) {
                String institutionCode = dtolist.get(i).getInstitutionCode();
                String tradeNo = dtolist.get(i).getTradeNo();
                String maintainStatus = dtolist.get(i).getMaintainStatus();
                if(maintainStatus.equals("01")){
                    //erro表的直接物理删除
                    salestransactionmaintenanceMapper.deleteByPrimaryKey(institutionCode,tradeNo);
                }else{
                    //info表内容逻辑删除
                    this.deleteInfo(institutionCode,tradeNo);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 逻辑删除info
     */
    public void deleteInfo(String institutionCode, String tradeNo){
                salestransactionmaintenanceMapper.deleteInfo(institutionCode,tradeNo);
    }

    /**
     * 提供销售结算资金服务机构代码下拉框
     */
    public List<SelectOptionDto> getInstitutionCode(Map<String, Object> params) {
        return salestransactionmaintenanceMapper.getInstitutionCode(params);
    }

    /**
     * 销售结算资金交易流水号下拉框
     */
    public List<SelectOptionDto> getTradeNo(Map<String, Object> params) {
        return salestransactionmaintenanceMapper.getTradeNo(params);
    }
}