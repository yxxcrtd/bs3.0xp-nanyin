/*
 * 文件名：FlowRiskStressTestService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.tool.JixuUserUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dao.MarketRiskSceneManage002Mapper;
import com.joyintech.service.risk.stresstestmanage.marketriskscenemanage.dto.MarketRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dao.MarketRiskStressTestMapper;
import com.joyintech.service.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest001Dto;
import com.joyintech.tams.common.base.dao.SttStressIndicatorMapper;
import com.joyintech.tams.common.base.dao.SttStressSceneMapper;
import com.joyintech.tams.common.base.dao.SttStressTestMapper;
import com.joyintech.tams.common.base.dao.SttTestObjMapper;
import com.joyintech.tams.common.base.entity.SttStressIndicator;
import com.joyintech.tams.common.base.entity.SttStressScene;
import com.joyintech.tams.common.base.entity.SttStressTest;
import com.joyintech.tams.common.base.entity.SttTestObj;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年9月5日
 * @see MarketRiskStressTestService
 * @since 1.0
 */
@Service
public class MarketRiskStressTestService extends BaseService<FlowRiskStressTest001Dto> {
    
    @Autowired
    private MarketRiskStressTestMapper marketRiskStressTestMapper;
    
    @Autowired
    private SttStressSceneMapper sttStressSceneMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;
    
    /**
     * 压力测试mapper
     */
    @Autowired
    private SttStressTestMapper sttStressTestMapper;
    
    /**
     * 压力对象mapper
     */
    @Autowired
    private SttTestObjMapper sttTestObjMapper;
    /**
     * 压测指标mapper
     */
    @Autowired
    private SttStressIndicatorMapper sttStressIndicatorMapper;
    /**
     *情景新建mapper 
     */
    @Autowired
    private MarketRiskSceneManage002Mapper marketRiskSceneManage002Mapper;
    
    
    /**
     * 根据压测名称判断数量
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public int stressTestNameCount(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        Integer count = marketRiskStressTestMapper.stressTestNameCount(param);
        DynamicDataSourceHolder.clearDataSource();
        return count;
    }
    
    /**
     * 情景下拉框
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> querySceneOption(Map<String, Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        List<SelectOptionDto> list = marketRiskStressTestMapper.querySceneOption(param);
        DynamicDataSourceHolder.clearDataSource();
        return list;
    }
    
    /**
     * 压力测试的执行登表
     * <一句话功能简述>
     * <功能详细描述>
     * @see
     * @since 1.0
     */
    public MarketRiskStressTest001Dto asset(MarketRiskStressTest001Dto dto){
      //基准日期（T+1）
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date sysDate = iPlatformDate.getSysDate();
        Calendar date = Calendar.getInstance();
        date.setTime(sysDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        Date endDate = new Date();
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        int count = marketRiskStressTestMapper.queryStrTesCount();
        //压力测试表
        SttStressTest streTesEnt = new SttStressTest();
        String username=JixuUserUtil.getLoginUserName();
        String testId =username+"-"+(dft.format(sysDate))+"-"+count+ IDGenerator.generate(5).substring(0, 8);
        //压测代码
        streTesEnt.setTestId(testId);
        //压测名称
        streTesEnt.setTestName(dto.getTestName());
        //分析开始日期
        streTesEnt.setAnalysisMdate(sysDate);
        streTesEnt.setRemark(dto.getRemark());
        //分析结束日期
        streTesEnt.setAnalysisVdate(DateUtil.parse(dto.getAnalysisVdate()));
        //基准日期（T+1）
        streTesEnt.setBaseDate(dto.getBaseDate());
        //执行日期
        streTesEnt.setTestDate(sysDate);
        //执行状态
        streTesEnt.setExeStatus(DictCodeListRsk.压力测试_执行状态_执行中.getValue());;
        //异常状态
        streTesEnt.setRiskFacHandling(dto.getRiskFacHandling());
        //模板代码
        Map<String,Object> par = new HashMap<>();
        par.put("sceneId", dto.getSceneId());
        MarketRiskStressTest001Dto tmplDto = marketRiskStressTestMapper.queryTmplBySceneId(par);
        //"MarketTestModel0001"
        streTesEnt.setTmplId(tmplDto.getTpmlId());

        // 二级法人改造。。。20200703
        if(MultilevelDataAuthorityUtil.isEnabled()){
            streTesEnt.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
        }
        sttStressTestMapper.insertSelective(streTesEnt);
        
        //压测情景表
        SttStressScene sttStrSce = new SttStressScene();
        sttStrSce.setBusiId(testId);
        sttStrSce.setSceneId(dto.getSceneId());
        sttStrSce.setSttBusiType(DictCodeListRsk.压力测试业务类型_压力测试.getValue());
        sttStressSceneMapper.insert(sttStrSce);
        //压力对象
        String[] list = dto.getPortfolioSingleName().split(",");
        SttTestObj sttTestObj = new SttTestObj();
        //测试代码
        sttTestObj.setTestId(testId);
        for(String portId:list){
            //单个产品代码
            sttTestObj.setObjectId(portId);
            sttTestObjMapper.insertSelective(sttTestObj);    
        }
        
        dto.setTestId(testId);
        
        //压测指标表
        SttStressIndicator sttStrInd = new SttStressIndicator();
        Map<String,Object> param = new HashMap<>();
        param.put("modelId", tmplDto.getTpmlId());
        param.put("sttBusiType", DictCodeListRsk.压力测试业务类型_压测模板.getValue());
        List<MarketRiskStressTest001Dto> indList = marketRiskStressTestMapper.selectTmplIndByType(param);
        sttStrInd.setBusiId(testId);
        for(MarketRiskStressTest001Dto indDto:indList){
        	//压测指标代码
            String seqNo = username+"-"+(dft.format(sysDate))+"-"+IDGenerator.generate(12).substring(0, 8);
        	sttStrInd.setSeqNo(seqNo);
        	//业务代码
//        	sttStrInd.setBusiId(dto.getSceneId());
        	//指标代码
        	sttStrInd.setIndId(indDto.getIndId());
        	//模型代码
        	sttStrInd.setModelId(tmplDto.getTpmlId());
        	//压测业务类型
        	sttStrInd.setSttBusiType(DictCodeListRsk.压力测试业务类型_压力测试.getValue());
        	sttStressIndicatorMapper.insertSelective(sttStrInd);
        }
        
        DynamicDataSourceHolder.clearDataSource();
        return dto;
    }
    
    /**
     * 压力测试存储过程
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @see
     * @since 1.0
     */
    public void stressTestLoad(Map<String,Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        marketRiskStressTestMapper.stressTestLoad(param);
        DynamicDataSourceHolder.clearDataSource();
    }
    
    /**
     * 判断日期是否法定节假日
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public MarketRiskStressTest001Dto judgeYesOrNoHoliday(Map<String,Object> param){
//        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        MarketRiskStressTest001Dto dto = marketRiskStressTestMapper.judgeYesOrNoHoliday(param);
//        DynamicDataSourceHolder.clearDataSource();
        return dto;
    }
    
    /**
     * 判断情景下有无为准备的因子
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public int countFacType(Map<String,Object> param){
        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        int counts = marketRiskStressTestMapper.countFacType(param);
        DynamicDataSourceHolder.clearDataSource();
        return counts;
    }
}
