/*
 * 文件名：PtlChlFundAllocation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年4月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlchlfundallocation.service;

import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.StlPayOrdersMapper;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlchlfundallocation.dto.PtlChlFundAllocationDto;

/**
 * 通道资金划拨管理Service
 *
 * @author etliu
 * @version 1.0 2019年4月4日
 * @see PtlChlFundAllocation001Service
 * @since 1.0
 */
@Service("PtlChlFundAllocation001Service")
@Transactional(rollbackFor = Exception.class)
public class PtlChlFundAllocation001Service extends CommonExportService<PtlChlFundAllocationDto> {
    
    /**
     * StlPayOrdersMapper
     */
    @Autowired
    private StlPayOrdersMapper stlPayOrdersMapper;

    /**
     * 分页查询
     * 
     * @return Page<>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<PtlChlFundAllocationDto> findPage(Map<String, Object> map) {
        // 登录用户名
        map.put("userId", LoginUserUtil.getUserName());
        return restTemplate.postForPage(
            ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlChlFundAllocation/findPage", map,
            PtlChlFundAllocationDto.class);
    }
    
    /**
     * 指令是否已发送成功
     *
     * @param orderId
     * @return 指令是否已发送
     * @see
     * @since 1.0
     */
    public boolean isOrderSendOK(String orderId) {
        StlPayOrders stlPayOrders = stlPayOrdersMapper.selectByPrimaryKey(orderId);
        if (stlPayOrders != null && StringUtil.equals(DictCodeList.成功失败标志_成功.getValue(),
            stlPayOrders.getSendStatus())) {
            return true;
        }
        return false;
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/portfolio/ptlchlfundallocation/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PtlChlFundAllocationDto> selectPageData(Map<String, Object> selectParam) {
        return findPage(selectParam);
    }
    
    /**
     * 设置单元格值
     * <p>
     * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
     * </p>
     * 
     * @param beanName sheet对应的JavaBean
     * @param cell 单元格
     * @param propName 属性名
     * @param value 单元格的值
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if("settleDate".equals(propName)){
            cell.setCellValue(DateUtil.format((Date)value, "yyyy-MM-dd"));
        }
    }
}
