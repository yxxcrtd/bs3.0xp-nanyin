/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repreportcheck.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.information.config.repreportcheck.dao.ReportCheckMapper;
import com.joyintech.tams.disclosure.information.config.repreportcheck.dto.ReportCheckDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 报表模板管理新建service
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ReportCheckService
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportCheckService extends CommonExportService<ReportCheckDto> implements BaseProcessService<ReportCheckDto> {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportCheckService.class);


    @Autowired
    private ReportCheckMapper reportCheckMapper;


    public List<ReportCheckDto> findAll(Map<String, Object> params) {
        List<ReportCheckDto> result = reportCheckMapper.searchList(params);
        return result;
    }


    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    public Page<ReportCheckDto> findByPage(Map<String, Object> params) {
        String prodId = (String) params.get("prodId");
        String[] prodIdArr = null;
        if (StringUtil.isNotEmpty(prodId)) {
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);

        String reportType = (String) params.get("reportType");
        String[] reportTypeArr = null;
        if (StringUtil.isNotEmpty(reportType)) {
            reportTypeArr = reportType.split(",");
        }
        params.put("reportTypeArr", reportTypeArr);

        Page<ReportCheckDto> pages = super.queryForPageForSelectReturn(Integer.parseInt((String) params.get("page")),
                Integer.parseInt((String) params.get("length")),
                () -> reportCheckMapper.searchList(params));
        return pages;
    }


    @Override
    public void doComplete(ReportCheckDto reportCheckDto, OperationType operationType) {
       /* entity.setOperateType(type.toString());
        entity.setUpdateTime(DateUtil.now());
        if (StringUtil.equals(entity.getOperateType(), "NEW")) {
            entity.setDataStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
            entity.setDataSource(DictCodeListExt3.信披_数据来源_系统新建.getValue());
            this.insert(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "MODIFY")) {
            this.update(entity);
        } else if (StringUtil.equals(entity.getOperateType(), "DELETE")) {
            this.delete(entity);
        }*/
    }


}
