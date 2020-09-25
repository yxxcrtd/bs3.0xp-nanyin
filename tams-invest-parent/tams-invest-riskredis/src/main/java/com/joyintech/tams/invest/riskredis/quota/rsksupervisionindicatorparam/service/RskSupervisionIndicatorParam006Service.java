package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam003Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParam006Dto;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

@Service
public class RskSupervisionIndicatorParam006Service extends CommonExportService<RskSupervisionIndicatorParam006Dto> implements BaseProcessService<RskSupervisionIndicatorParam006Dto> {
	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskSupervisionIndicatorParam006Service.class);

    @Autowired
    private RskSupervisionIndicatorParam003Service rskSupervisionIndicatorParam003Service;

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    @Override
    public void doComplete(RskSupervisionIndicatorParam006Dto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.MODIFY.equals(type)) {
            RskSupervisionIndicatorParam003Dto dto = new RskSupervisionIndicatorParam003Dto();
            BeanUtil.copyPropertiesIgnoreNull(dto, entity);
            List<RskSupervisionIndicatorParam003Dto> list = new ArrayList<RskSupervisionIndicatorParam003Dto>();
            if (!StringUtil.isEmptyList(entity.getSelectedItem())) {
                for (RskSupervisionIndicatorParam006Dto e : entity.getSelectedItem()) {
                    RskSupervisionIndicatorParam003Dto d = new RskSupervisionIndicatorParam003Dto();
                    BeanUtil.copyPropertiesIgnoreNull(d, e);
                    list.add(d);
                }
            }
            dto.setSelectedItem(list);
            action = "updateFixRecode";
            restTemplate.postForObject(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action,
                dto, Integer.class);

        }else{
        	RskSupervisionIndicatorParamDto dto = new RskSupervisionIndicatorParamDto();
        	BeanUtil.copyPropertiesIgnoreNull(dto, entity);
        	dto.setParaType(BussinessDictCodeListRds.指标参数实现类型_固定值.getValue());
        	action = "delete";
        	restTemplate.postForObject(
                    ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action, dto,
                    Integer.class);
        }
        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            try {
                logger.info("===========指标固定值参数进入redis开始=============");
                Thread.sleep(2000);
                // 更新Redis
                ImplCond cond = new ImplCond();
                cond.setParamCodeArray(entity.getParaCode());
                cond.setDel(StringUtil.equals(action, "delete"));
                cond.setIsUpdParVal(false);
                cond.setPlateDate(DateUtil.parse(DateUtil.getSysDate()));
                redisRriter.dealRiskIndParam(cond);
                logger.info("===========指标固定值参数进入redis结束=============");
            } catch (InterruptedException e) {
                logger.error("===========指标固定值参数进入redis异常=============",e);
            }
        }
        
        
    }

    public Page<RskSupervisionIndicatorParam006Dto> findPage(Map<String, Object> pageParam) {
        Page<RskSupervisionIndicatorParam006Dto> page = new Page<>();
        if (StringUtil.isNotEmpty(pageParam.get("workflowId"))) {
            RskSupervisionIndicatorParam006Dto dto = this.getBussinessEntityByWorkflowId(
                (String)pageParam.get("workflowId"));
            if(StringUtil.equals(dto.getOperateType(),"delete" )){
            	List<RskSupervisionIndicatorParam003Dto> list = rskSupervisionIndicatorParam003Service.selectFixRecode(
                        dto.getParaCode());
                    List<RskSupervisionIndicatorParam006Dto> pageList = new ArrayList<>();
                    if (!StringUtil.isEmptyList(list)) {
                        for (RskSupervisionIndicatorParam003Dto e : list) {
                            RskSupervisionIndicatorParam006Dto d = new RskSupervisionIndicatorParam006Dto();
                            BeanUtil.copyPropertiesIgnoreNull(d, e);
                            pageList.add(d);
                        }
                    }
                    page.setRoot(pageList);
                    page.setRecords(pageList.size());
            }else{
            	if (dto != null && !StringUtil.isEmptyList(dto.getSelectedItem())) {
                    page.setRoot(dto.getSelectedItem());
                    page.setRecords(dto.getSelectedItem().size());
                }
            }
            

        }
        else if (StringUtil.isNotEmpty(pageParam.get("paraCode"))) {
            List<RskSupervisionIndicatorParam003Dto> list = rskSupervisionIndicatorParam003Service.selectFixRecode(
                (String)pageParam.get("paraCode"));
            List<RskSupervisionIndicatorParam006Dto> pageList = new ArrayList<>();
            if (!StringUtil.isEmptyList(list)) {
                for (RskSupervisionIndicatorParam003Dto e : list) {
                    RskSupervisionIndicatorParam006Dto d = new RskSupervisionIndicatorParam006Dto();
                    BeanUtil.copyPropertiesIgnoreNull(d, e);
                    pageList.add(d);
                }
            }
            page.setRoot(pageList);
            page.setRecords(pageList.size());
        }
        return page;
    } 
}
