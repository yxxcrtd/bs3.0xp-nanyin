/*
 * 文件名：FlowRiskStressTestService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年9月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.collections4.ListUtils;
import org.bouncycastle.pqc.math.linearalgebra.IntUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.jixu.common.tool.JixuUserUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.conf.DataSourceType;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dao.FlowRiskFactroManage002Mapper;
import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskSceneManage002Dto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dao.FlowRiskStressTestMapper;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRiskStressTest001Dto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstest.dto.FlowRskSupervisionIndicatorDto;
import com.joyintech.tams.common.base.dao.SttStressIndicatorMapper;
import com.joyintech.tams.common.base.dao.SttStressSceneMapper;
import com.joyintech.tams.common.base.dao.SttStressTestMapper;
import com.joyintech.tams.common.base.dao.SttTestObjMapper;
import com.joyintech.tams.common.base.entity.SttStressIndicator;
import com.joyintech.tams.common.base.entity.SttStressScene;
import com.joyintech.tams.common.base.entity.SttStressTest;
import com.joyintech.tams.common.base.entity.SttTestObj;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.workflow.engine.parse.JoyinUserTaskParseHandler;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年9月5日
 * @see FlowRiskStressTestService
 * @since 1.0
 */
@Service
public class FlowRiskStressTestService extends BaseService<FlowRiskStressTest001Dto> {
    
    @Autowired
    private FlowRiskStressTestMapper flowRiskStressTestMapper;
    
    @Autowired
    private SttStressSceneMapper sttStressSceneMapper;
    
    private final static String NET_OUT_FLOW = "1002"; // 申赎净流出基础
    private final static String NET_OUT_FLOW_LMH = "10028"; // 申赎净流出，轻中重
    private final static String PRO_COM = "10011"; // 产品赎回
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
    private FlowRiskFactroManage002Mapper flowRiskFactroManage002Mapper;
    
    //多线程
    /**
     * 邮储压测最优线程数
     */
    @Value("${application.riskTestMaxCorePoolSize:20}")
    private int riskTestMaxCorePoolSize;
    /**
     * 邮储压测每个线程处理产品的个数(最多不要超过950)
     */
    @Value("${application.riskTestmaxSubmitProductSize:50}")
    private int riskTestmaxSubmitProductSize;
    
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    
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
        Integer count = flowRiskStressTestMapper.stressTestNameCount(param);
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
        //wangyang1 压力测试二级法人改造 start
        if (MultilevelDataAuthorityUtil.isEnabled()) {
            List<String> institutionList = LoginUserUtil.getTreeInstCode(); 
            param.put("institutionList",institutionList); 
        }
        //wangyang1 压力测试二级法人改造 end
        List<SelectOptionDto> list = flowRiskStressTestMapper.querySceneOption(param);
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
    public FlowRiskStressTest001Dto asset(FlowRiskStressTest001Dto dto){
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
        int count = flowRiskStressTestMapper.queryStrTesCount();
        //压力测试表
        SttStressTest streTesEnt = new SttStressTest();
        String username=JixuUserUtil.getLoginUserName();
        String testId =username+"-"+(dft.format(sysDate))+"-"+count+ IDGenerator.generate(5).substring(0, 8);
        //压测代码
        streTesEnt.setTestId(testId);
        //压测名称
        streTesEnt.setTestName(dto.getTestName());
        //分析开始日期=基准日期+1
        Calendar c = Calendar.getInstance();
        c.setTime(dto.getBaseDate());
        c.add(Calendar.DAY_OF_MONTH, 1);
        streTesEnt.setAnalysisVdate(c.getTime());
        //分析结束日期
        streTesEnt.setAnalysisMdate(DateUtil.parse(dto.getAnalysisMdate()));
        //基准日期（T+1）
        streTesEnt.setBaseDate(dto.getBaseDate());
        streTesEnt.setRemark(dto.getRemark());
        //执行日期
        streTesEnt.setTestDate(sysDate);
        //执行状态
        streTesEnt.setExeStatus(DictCodeListRsk.压力测试_执行状态_执行中.getValue());;
        //模板代码
        Map<String,Object> par = new HashMap<>();
        par.put("sceneId", dto.getSceneId());
        FlowRiskStressTest001Dto tmplDto = flowRiskStressTestMapper.queryTmplBySceneId(par);
        //"FlowTestModel0001"
        streTesEnt.setTmplId(tmplDto.getTpmlId());
        //wangyang1 二级法人改造 start
        streTesEnt.setInstitutionCode(MultilevelDataAuthorityUtil.getOrganizationCode(LoginUserUtil.getUserName()));
        //wangyang1 二级法人改造 end
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
        List<FlowRiskSceneManage002Dto> indList = flowRiskFactroManage002Mapper.selectTmplIndByType(param);
        sttStrInd.setBusiId(testId);
        //查找情景因子是否选择申赎净流出
        param.put("sceneId", dto.getSceneId());
        int countNetOutF = flowRiskStressTestMapper.queryCountNetOutFlowBySce(param);
        for(FlowRiskSceneManage002Dto indDto:indList){
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
        	//如果页面没选申赎因子的话，申赎净流出产品指标就不存
        	if(StringUtil.equals(indDto.getIndId(), NET_OUT_FLOW) ){
        		//界面选择申赎净流出
        		if( countNetOutF !=0 ){
        			sttStressIndicatorMapper.insertSelective(sttStrInd);
        		}
        		//如果界面选择产品赎回比例，就存产品赎回指标
        	}else{
        		sttStressIndicatorMapper.insertSelective(sttStrInd);
        	}
    		
        }
        //产品明细指标数据
        Map<String,Object> param2 = new HashMap<>();
        param2.put("paramCode", DictCode.情景风险分类.getValue());
        param2.put("paramValue", DictCodeListRsk.情景风险分类_流动性风险.getValue());
        param2.put("indType", DictCodeListRsk.压力测试指标类型_产品明细指标.getValue());
        List<FlowRiskSceneManage002Dto> indDetList = flowRiskFactroManage002Mapper.queryIndexByType(param2);
        for(FlowRiskSceneManage002Dto indDto:indDetList){
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
        	//如果页面没选申赎因子的话，申赎净流出指标就不存
        	if(StringUtil.equals(indDto.getIndId(), NET_OUT_FLOW_LMH)){
        		//界面选择申赎净流出，存明细指标申赎净流出（轻中重）
        		if(StringUtil.equals(indDto.getIndId(), NET_OUT_FLOW_LMH) && countNetOutF >0 ){
        			sttStressIndicatorMapper.insertSelective(sttStrInd);
        		}//界面选择产品赎回
//        		else if(StringUtil.equals(indDto.getIndId(), PRO_COM) && countNetOutF ==0 ){
//        			sttStressIndicatorMapper.insertSelective(sttStrInd);
//        		}
        		//如果界面选择产品赎回比例，就存产品赎回指标
        	}else{
        		sttStressIndicatorMapper.insertSelective(sttStrInd);
        	}
        }
//        //产品个数大于1000
//        if(!StringUtil.equals(list.length, 0) && Arrays.asList(list).size() > 1000 ){
//        	//压测线程数=产品数/每次线程的产品数
//        	riskTestMaxCorePoolSize = (int) Math.ceil(Arrays.asList(list).size()/riskTestmaxSubmitProductSize);
//        }
//    	//创建线程
//        ExecutorService executor = Executors.newFixedThreadPool(riskTestMaxCorePoolSize);
//        try {
//        	List<Callable<Boolean>> tasks = new ArrayList<>();
//
//        	ListUtils.partition(Arrays.asList(list), riskTestmaxSubmitProductSize).stream().forEach(p ->{
//        		tasks.add(new Callable<Boolean>() {
//
//					@Override
//					public Boolean call() throws Exception {
//						// TODO Auto-generated method stub
//						flowRiskStressTestMapper.stressTestLoad(ImmutableMap.of("in_test_id",testId,"in_object_ids",StringUtil.join(p, ",")));
//						return true;
//					}
//				});
//        	});
//        	//阻塞，等待所有线程完成
//        	executor.invokeAll(tasks);
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//			logger.error(e.getMessage());
//		}finally {
//			executor.shutdown();
//		}
        
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
        String inObjectIds = (String) param.get("in_object_ids");
        //oracle11 varchar缓冲区上限4000，存储过程中有拼接，预留100
        if(inObjectIds.length()>3900) {
        	int vObjectCount = 1;
        	int index = 0;
        	while(true) {
        		index = inObjectIds.indexOf(",",index);
        		if(index==-1) {
        			break;
        		}
        		else {
        			index++;
        			vObjectCount++;
        		}
        	}
        	param.put("v_object_count", vObjectCount);
        	flowRiskStressTestMapper.stressTestLoadClob(param);
        }
        else {
        	flowRiskStressTestMapper.stressTestLoad(param);
        }
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
    public FlowRiskStressTest001Dto judgeYesOrNoHoliday(Map<String,Object> param){
//        DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
        FlowRiskStressTest001Dto dto = flowRiskStressTestMapper.judgeYesOrNoHoliday(param);
//        DynamicDataSourceHolder.clearDataSource();
        return dto;
    }
    
    /**
     * 组合名称下拉框
     * @param param
     * @return
     */
    public List<SelectOptionDto> selectProdSelectOpt(Map<String,Object> param){
//      DynamicDataSourceHolder.putDataSource(DataSourceType.STRESSTEST);
      return flowRiskStressTestMapper.selectProdSelectOpt(param);
//      DynamicDataSourceHolder.clearDataSource();
    }

    /**
     * 指标参数下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    public List<TreeNode<FlowRskSupervisionIndicatorDto>> getAllPortfolioByProfit(String indId,String portfName,String profitType,String issueMode,String raiseType,String investType) {
        return flowRiskStressTestMapper.getAllPortfolioByProfit(indId,DictCodeListExt1.组合层次_单组合.getValue(),StringUtil.splitToList(",", portfName),profitType,issueMode,raiseType,investType,DictCodeListExt1.组合类型_投资类组合.getValue());
    }
    public List<TreeNode<FlowRskSupervisionIndicatorDto>> getAllPortfolioByOther(String fields,String indId,String portfName,String profitType,String issueMode,String raiseType,String investType) {
        return flowRiskStressTestMapper.getAllPortfolioByOther(fields,DictCodeListExt1.组合层次_单组合.getValue(),indId,StringUtil.splitToList(",", portfName),profitType,issueMode,raiseType,investType,DictCodeListExt1.组合类型_投资类组合.getValue());
    }
}
