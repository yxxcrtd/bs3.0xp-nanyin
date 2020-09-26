package com.joyintech.tams.svreport.interf.chb.proddividenddeal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.common.base.dao.ChbPrdBonusDealMapper;
import com.joyintech.tams.common.base.entity.ChbInvestTrade;
import com.joyintech.tams.common.base.entity.ChbPrdBonusDeal;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.investtrade.dao.ChbInvestTradeInterfMapper;
import com.joyintech.tams.svreport.interf.chb.proddividenddeal.dao.SvrChbProdDividentDealDBInterfMapper;


/**
 * 产品分红交易信息数据接口
 * @author zhangzhao
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class SvrChbProdDividendDealDBInterf extends BaseService{
	@Autowired
	private SvrChbProdDividentDealDBInterfMapper svrChbProdDividentDealDBInterfMapper;
	
	@Autowired
	private SqlSessionTemplate template;
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    public void generateData(Date batchTime) {
    	logger.info("产品分红交易数据DB接口批处理开始");
    	long startTime=System.currentTimeMillis();
    	//定义批量插入mapper
    	SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	ChbPrdBonusDealMapper batchMapper =  session.getMapper(ChbPrdBonusDealMapper.class);
    	
    	//获取上一次更新时间
    	Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品分红交易接口.getInterId());
		//根据日期去上游查询数据
    	List<ChbPrdBonusDeal>  listChbPrdfeeBasisData=getDataFromInterf(lastUpdatetime);
    	if(CollectionUtils.isNotEmpty(listChbPrdfeeBasisData)){
    	
    		for(ChbPrdBonusDeal  data:listChbPrdfeeBasisData){
    			//主键
    			data.setSeqno(IDGenerator.generate(36));
    			data.setCreateUser("system");
        		data.setUpdateUser("system");
        		data.setCreateTime(new  Date());
        		data.setUpdateTime(new  Date());
        		if(data.getUpdateTime().compareTo(lastUpdatetime)>0)
            		lastUpdatetime =data.getUpdateTime();
    		}

        	logger.info("插入"+DateUtil.format(new Date())+"日的产品分红交易数据，开始");
        	insertDataMerge(listChbPrdfeeBasisData);
        	logger.debug("插入"+DateUtil.format(new Date())+"日的产品分红交易数据，结束");
    	
   
    	//新的最后更新时间
    	if(lastUpdatetime!=null){
    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品分红交易接口.getInterId(), lastUpdatetime);
    	}
    	logger.info("产品费用头寸接口导入批处理结束。用时："+(System.currentTimeMillis()-startTime)/1000+"s");
    }
    }
	
		@Transactional(propagation=Propagation.REQUIRES_NEW)
	    @DataSource(DataSourceType.INTERF)
		public List<ChbPrdBonusDeal> getDataFromInterf(Date batchTime){
			List<ChbPrdBonusDeal> rs = new ArrayList<ChbPrdBonusDeal>();
			template.select("com.joyintech.tams.svreport.interf.chb.proddividenddeal.dao.SvrChbProdDividentDealDBInterfMapper.selectByValuedate",batchTime,
	                new ResultHandler() {
	            @Override
	            public void handleResult(ResultContext resultContext) {
	            	ChbPrdBonusDeal pos = (ChbPrdBonusDeal) resultContext.getResultObject();
	                rs.add(pos);
	            }
	        });
	    	return rs;
	    }
	
		/**
	     * 功能:数据入库
	     * @param investTradeList 产品分红交易信息
	     * @param 
	     */
	    public void insertDataMerge(List<ChbPrdBonusDeal> investTradeList) {
	        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	        try {
	            int count = 0;
	            logger.debug("投资交易插入开始--------------" + new Date());
	            SvrChbProdDividentDealDBInterfMapper batchMapper = session.getMapper(SvrChbProdDividentDealDBInterfMapper.class);
	            int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"50" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));//每批commit的个数
	            int batchLastIndex = batchCount;//每批最后一个的下标
	            for(int index = 0; index < investTradeList.size();){
	                if(batchLastIndex > investTradeList.size()-1){
	                    batchLastIndex = investTradeList.size();
	                    batchMapper.insertChbPrdBonusDeal(investTradeList.subList(index, batchLastIndex));
	                    break;//数据插入完毕,退出循环
	                }else{
	                	 batchMapper.insertChbPrdBonusDeal(investTradeList.subList(index, batchLastIndex));
	                    index = batchLastIndex;//设置下一批下标
	                    batchLastIndex = index + batchCount;                     
	                }
	                count ++;
	                // 每20次提交
	                if (count % 20 == 0) {
	                    session.commit();
	                    // 清理缓存，防止溢出
	                    session.clearCache();
	                }
	            }
	            logger.debug("产品分红交易插入结束--------------" + new Date());
	            session.commit();
	            // 清理缓存，防止溢出
	            session.clearCache();
	        }
	        finally {
	            session.close();
	        }
	        logger.debug("产品分红交易插入结束--------------" + new Date());
	    }
		
}
