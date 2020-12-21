package com.joyintech.tams.svreport.pbc.prod.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PInterfProCustomDealDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:人行报表-产品头寸批处理
 * @since 1.6.0
 * @version 1.6.0
 * @param <PbcReportProdTotalService>
 * @作者：lingxq
 * @创建时间：2019年3月11日
 * @修改记录：
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class PbcMonthProdPositionService  extends BaseService{
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
	
	@Autowired
	private PbcMonthReportMapper pbcMonthReportMapper;
	
	
	@Autowired
	private List<PbcReportProdTotalService> abstractsevices;
    
	@Autowired
	private SqlSessionTemplate template;
    
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 功能:接入产品头寸信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：lingxq 2019年3月11日
     * @param batchDate 跑批日
     * @prodList 产品代码
     * @param isSynchronizeTask 是否为轮循批处理
     * void
     */
    public void generateProdPositionData(Date batchDate,List<String> prodList,
    		boolean isSynchronizeTask,boolean isEdit) {	
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchDate);
        
//        if ( calendar.get(Calendar.DATE)!=1 ) {  //每月1日生成上月的数据
//        	return;
//        }
        
        // 获取上月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date reportStartDate = calendar.getTime();
        
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date reportEndDate = calendar.getTime();
         
        /*        
         * 生成人行报表头寸,chenxc updated on 2019-05-09 begin
         * 1.修改算法：头寸的剩余份额以上周期累计值＋本周期变动值；期末余额＝剩余份额*最新的产品净值
         * 2.性能优化：原先的查询方法效率低；
         * 3.查询和插入头寸表，方法合并；
         */
        //        //获取产品头寸所需信息
        //        List<PbcMonthPrdPostionDto> resultList = getProdDealList(reportStartDate,
        //        															   reportEndDate,
        //        		                                                       batchDate,
        //        		                                                       prodList);
        //        
        //        //先删后插
        //        pbcMonthPrdPostionMapper.deleteByReportDay(reportEndDate,prodList);
        //        calPositionData(reportEndDate,prodList,resultList);
        pbcMonthPrdPostionMapper.deleteByReportDay(reportEndDate,prodList);
        //参数传递 汇率取值 用于净值币种折算
        String exchangeSource=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_EXCHANGE_SOURCE_PBC);
        String bankCode=SvrDataUtil.getSysParam(SVReportGlobalConstant.CUSTOMER_BANK_CODE);
        // 计算兑付状态到期日需要修正的
        Integer days = 0;
        String daysStr = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_PAY_STATUS);
        String dividendReinvestmentFlag = SvrDataUtil.getSysParam(SVReportGlobalConstant.DIVIDEND_REINVESTMENT_FLAG);
        if (StringUtil.isNotEmpty(daysStr)) {
        	days = Integer.parseInt(daysStr);
        }
		// 资金募集保留是否四舍五入保留五位,01(截断)，02(四舍五入)
		String  jsbankStr=SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_JSBK_IS_ROUND);
		
		
		String modelType = null;
		if(CommonUtil.isBAmodel()){
			modelType = DictCodeList.权限模式_联社模式.getValue();
		}
		
		
        if(StringUtil.equals(bankCode, "001")){
           pbcMonthPrdPostionMapper.genPsbcPosition(reportStartDate,reportEndDate, batchDate,prodList,exchangeSource, dividendReinvestmentFlag,jsbankStr);
        }else{
           pbcMonthPrdPostionMapper.genPosition(reportStartDate,reportEndDate,
                                                                       batchDate,
                                                                       prodList,
                                                                       exchangeSource,
                                                                       days,
                                                                       dividendReinvestmentFlag,
				   														jsbankStr);
        }
        /* 生成人行报表头寸,chenxc updated on 2019-05-09 end */ 
        List<PbcMonthPrdPostionDto> posList =
                    pbcMonthPrdPostionMapper.selectByDate(DateUtil.getLastDayOfLayMonth(batchDate),modelType,prodList);
        List<PbcMonthPrdPostionDto> posPsbcList;
//        if(StringUtil.equals(bankCode, "001")){
//        	//邮储个性化 方法查询不带其中的销售区域（邮储接的销售区域1级及部分2级）
        	 posPsbcList= pbcMonthPrdPostionMapper.selectByDatePsbc(DateUtil.getLastDayOfLayMonth(batchDate),modelType,prodList);
//        }else{
//        	posPsbcList=posList;
//        }
        	 
        //生成产品端1.1~1.6以及3.1~3.2的报表数据
        for(PbcReportProdTotalService service : abstractsevices){
        	if(service instanceof PbcReportProd001Sub004Service){
        		service.generateReport(batchDate, reportStartDate, reportEndDate,posList,isEdit);
        	}else{
        		service.generateReport(batchDate, reportStartDate, reportEndDate,posPsbcList,isEdit);
        	}
        }
        
	}

    
    
    /**
     * 数据持久化
     * @param reportEndDate
     * @param prodList
     * @param resultList
     */
	private void calPositionData(Date reportEndDate,List<String> prodList,List<PbcMonthPrdPostionDto> resultList) {
		logger.debug("产品头寸信息入库:----开始------" + df.format(new Date()));
		
		 if (CollectionUtils.isNotEmpty(resultList)) {
			 
			int count = 0;
            SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            try{
            	PbcMonthPrdPostionMapper batchMapper =  session.getMapper(PbcMonthPrdPostionMapper.class);
                for(PbcMonthPrdPostionDto pos : resultList){
                    batchMapper.insert(pos);
                    count++;
                    if(count%1000==0){
                        session.commit();
                        // 清理缓存，防止溢出
                        session.clearCache();
                        logger.debug("产品头寸信息入库-插入1000条第"+ count/1000 +"次:----结束------" +  df.format(new Date())); 
                    }
                }
                session.commit();
                // 清理缓存，防止溢出
                session.clearCache();
            }
            finally {
                session.close();
            }
		 }
		  
		logger.debug("产品头寸信息入库:----结束------" + df.format(new Date()));
	}

	/**
	 * 获取产品头寸前期数据
	 * @param calendar
	 * @return
	 */
	private List<PbcMonthPrdPostionDto> getProdDealList(Date reportStartDate,
			                                                  Date reportEndDate,
			                                                  Date batchDate,
			                                                  List<String> prodList) {
		
		List<PbcMonthPrdPostionDto> result = new ArrayList<PbcMonthPrdPostionDto>();
		
        
        logger.debug("获取当日要生成的产品交易信息:----开始------" + df.format(new Date()));
        
        HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("reportStartDate", reportStartDate);
		paraMap.put("reportEndDate", reportEndDate);
		paraMap.put("batchDate", batchDate);
		paraMap.put("prodList", prodList);
		template.select("com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper.selectProdDealList",paraMap,
				new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
            	result.add((PbcMonthPrdPostionDto) resultContext.getResultObject());
            }
        });
		
        logger.debug("获取当日要生成的产品交易信息:----结束------" + df.format(new Date()));
        
        return result;
	} 
	
	public String checkSendStatus(Date batchDate,List<String> reporttypeList){
		//检查已报送
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(batchDate);
        if (calendar.get(Calendar.DATE)!=1 ) {  //每月1日生成上月的数据
            return null;
        }
        // 获取上月的最后一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date reportEndDate = calendar.getTime();

		// 已报送列表
		StringBuffer msg = new StringBuffer("");
		Map<String,Object> param = new HashMap<String,Object>();
		List<String> sendStatusList = new ArrayList<String>();
		sendStatusList.add(DictCodeListExt2.报送状态_接口报送.getValue());
		sendStatusList.add(DictCodeListExt2.报送状态_手工处理.getValue());
		param.put("sendStatusList", sendStatusList);
		param.put("reporttypeList",reporttypeList);
		param.put("enddate",DateUtil.format(reportEndDate));
		List<PbcMonthReport> listRegisted = pbcMonthReportMapper.searchPage(param);
		for(PbcMonthReport sub : listRegisted){
			msg.append(sub.getReportname()).append(GlobalConstant.SEMICOLON);
		}
		// 如果有已登记的资产要素则抛异常
		if (!StringUtil.isEmpty(msg.toString())){
			return "存在已报送的人行报表:"+msg+"\n";
		}else{
			return null;
		}
	}
}
