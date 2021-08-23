package com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tams.invest.riskredis.quota.rsksupervisionindicatorparam.dto.RskSupervisionIndicatorParamDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 指数参数管理service
 *
 * @author liuwei
 * @version 1.0 2018年10月23日
 * @see RskSupervisionIndicatorParam001Service
 * @since 1.0
 */
@Service
public class RskSupervisionIndicatorParam001Service extends CommonExportService<RskSupervisionIndicatorParamDto> implements BaseProcessService<RskSupervisionIndicatorParamDto> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RskSupervisionIndicatorParam001Service.class);

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 查询界面询事件
     *
     * @param map 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public Page<RskSupervisionIndicatorParamDto> selectForPage(Map<String, Object> map) {
        Page<RskSupervisionIndicatorParamDto> page = restTemplate.postForPage(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/findPage", map,
            RskSupervisionIndicatorParamDto.class);
        return page;
    }

    /**
     * 删除事件
     *
     * @param dto 页面参数
     * @return 调用微服务
     * @see
     * @since 1.0
     */
    public int delete(RskSupervisionIndicatorParamDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/delete", dto,
            Integer.class);
    }

    /**
     * 导出
     */
    @Override
    public void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/riskredis/quota/rsksupervisionindicatorparam/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * 设置日期格式 {@inheritDoc}
     */
    /*
     * @Override public void setCellValue(String beanName, Cell cell, String propName, Object
     * value) { if (("updateTime".equals(propName)) && value != null) {
     * cell.setCellValue(DateUtil.format((Date)value)); } }
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<RskSupervisionIndicatorParamDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }

    @Override
    public void doComplete(RskSupervisionIndicatorParamDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
        	entity.setParaType(BussinessDictCodeListRds.指标参数实现类型_SQL.getValue());
            action = "delete";
        }
        else {
            setEntity(entity);
            if (OperationType.NEW.equals(type)) {
                action = "insert";
            }
            else {
                action = "update";
            }
        }

        restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/" + action,
            entity, Integer.class);

        String pageRedisFlag = CacheUtil.getSysParam("pageRedisFlag");
        if (CommonUtil.isType(pageRedisFlag, BussinessDictCodeListRds.是否_是)) {
            final String actionRedis=action;
            threadPoolTaskExecutor.execute(()->{
                try {
                    logger.info("===========指标参数更新进入redis开始=============");
                    Thread.sleep(6000);
                    // 更新Redis
                    ImplCond cond = new ImplCond();
                    cond.setIsUpdParVal(OperationType.NEW.equals(type)?true:false);
                    cond.setParamCodeArray(entity.getParaCode());
                    cond.setDel(StringUtil.equals(actionRedis, "delete"));
                    cond.setPlateDate(DateUtil.parse(DateUtil.getSysDate()));
                    redisRriter.dealRiskIndParam(cond);
                    logger.info("===========指标参数更新进入redis结束=============");
                } catch (Exception e) {
                    logger.error("===========指标参数更新进入redis异常=============");
                }

            });

        }
    }

    private void setEntity(RskSupervisionIndicatorParamDto entity) {
        if (entity != null) {
            List<RskIsupIndicatorParaDtl> list = new ArrayList<RskIsupIndicatorParaDtl>();

           if (StringUtil.equals(entity.getParaDisplayType(),
                BussinessDictCodeListRds.指标参数实现类型_SQL.getValue())) {
                RskSupervisionIndicatorParamDto dto1 = new RskSupervisionIndicatorParamDto();
                BeanUtil.copyPropertiesIgnoreNull(dto1, entity);
                // 设置买入参数
                dto1.setParaGranularity(BussinessDictCodeListRds.参数粒度_买入.getValue());
                dto1.setContent(entity.getMrContent());
                list.add(dto1);
                RskSupervisionIndicatorParamDto dto2 = new RskSupervisionIndicatorParamDto();
                BeanUtil.copyPropertiesIgnoreNull(dto2, entity);
                // 设置卖出参数
                dto2.setParaGranularity(BussinessDictCodeListRds.参数粒度_卖出.getValue());
                dto2.setContent(entity.getMcContent());
                list.add(dto2);
            }
            RskSupervisionIndicatorParamDto dto3 = new RskSupervisionIndicatorParamDto();
            BeanUtil.copyPropertiesIgnoreNull(dto3, entity);
            // 设置实时参数
            dto3.setParaGranularity(BussinessDictCodeListRds.参数粒度_实时.getValue());
            dto3.setContent(entity.getContent());
            list.add(dto3);
            RskSupervisionIndicatorParamDto dto4 = new RskSupervisionIndicatorParamDto();
            BeanUtil.copyPropertiesIgnoreNull(dto4, entity);
            // 设置日间参数
            dto4.setParaGranularity(BussinessDictCodeListRds.参数粒度_事后_日间.getValue());
            dto4.setContent(entity.getRjContent());
            list.add(dto4);
            // 设置实时参数
            RskSupervisionIndicatorParamDto dto5 = new RskSupervisionIndicatorParamDto();
            BeanUtil.copyPropertiesIgnoreNull(dto5, entity);
            dto5.setParaGranularity(BussinessDictCodeListRds.参数粒度_事后_日终.getValue());
            dto5.setContent(entity.getRzContent());
            list.add(dto5);
            entity.setRskIsupIndicatorParaDtlList(list);
        }
    }

    /**
     * 插入事件
     *
     * @param dto 保存参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public int save(RskSupervisionIndicatorParamDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/save", dto,
            Integer.class);
    }

    /**
     * 修改事件
     *
     * @param dto 保存参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public int update(RskSupervisionIndicatorParamDto dto) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/update", dto,
            Integer.class);
    }

    /**
     * 修改或查看界面
     *
     * @param paraCode 参数代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public List<RskSupervisionIndicatorParamDto> selectInfo(String paraCode) {
        List<RskSupervisionIndicatorParamDto> listRet = restTemplate.getForList(ServiceApplication.RISK_RDS_SERVICE_NAME
                                             + "/RskSupervisionIndicatorParam/selectInfo?paraCode="
                                             + paraCode,
            RskSupervisionIndicatorParamDto.class);
        listRet.forEach(en->{
            //特殊字符转换
            en.setContent(RiskUtil.specialCharacterConversion(en.getContent(),true));
        });
        return listRet;
    }

    /**
     * 查询参数代码
     *
     * @param paraCode 参数代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public int selectParaCode(String paraCode) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionIndicatorParam/selectParaCode?paraCode="
                                               + paraCode,
            Integer.class);
    }

    /**
     * 是否在监督指标里配置
     *
     * @param paraCode 参数代码
     * @return 结果
     * @see
     * @since 1.0
     */
    public int isConfigure(String paraCode) {
        return restTemplate.getForObject(ServiceApplication.RISK_RDS_SERVICE_NAME
                                               + "/RskSupervisionIndicatorParam/isConfigure?paraCode="
                                               + paraCode,
            Integer.class);
    }

    public boolean isExistParaName(Map<String, Object> param) {
        return restTemplate.postForObject(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/isExistParaName",
            param, boolean.class);
    }

	public boolean isInWflProcess(WflValidInputParameter params) {
		return restTemplate.postForObject(
	            ServiceApplication.RISK_RDS_SERVICE_NAME + "/RskSupervisionIndicatorParam/isInWflProcess",
	            params, boolean.class);
	}
    /**
     * 设置日期格式
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(("updateTime".equals(propName)) && value != null){
            if(value instanceof Long){
                cell.setCellValue(DateUtil.format(new Date((long)value)));
            }else {
                cell.setCellValue(DateUtil.format((Date) value));
            }
        }
    }

}
