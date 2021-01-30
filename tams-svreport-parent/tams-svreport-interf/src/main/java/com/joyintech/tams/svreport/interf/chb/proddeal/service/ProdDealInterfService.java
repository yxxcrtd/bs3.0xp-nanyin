package com.joyintech.tams.svreport.interf.chb.proddeal.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.dao.SaleCustSvrreportMapper;
import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.common.svreport.chb.constant.ChbInterfConfig;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.salecustsvrreportfordeal.dao.SaleCustSvrreportVoMapper;
import com.joyintech.tams.common.svreport.salecustsvrreportfordeal.dto.SaleCustSvrreportVo;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.interf.chb.conf.DataSourceType;
import com.joyintech.tams.svreport.interf.chb.proddeal.dao.ChbPrdDealInterfMapper;
import com.joyintech.tams.svreport.interf.chb.prodinfo.dao.ChbPrdBaseinfoInterfMapper;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 产品存续交易表接口
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdDealInterfService extends BaseService{
	/**认购类型**/
	public final static String TRADE_TYPE_RG = "C1";
	/**申购类型**/
	public final static String TRADE_TYPE_SG = "C2";
	/**份额强增类型**/
	public final static String TRADE_TYPE_FEQZ = "C3";
	/**赎回类型**/
	public final static String TRADE_TYPE_SH = "C4";
	/**到期类型**/
	public final static String TRADE_TYPE_DQ = "C5";
	/**份额强减类型**/
	public final static String TRADE_TYPE_FEQJ = "C6";
	/**中间付息类型**/
	public final static String TRADE_TYPE_FX = "C7";
	/**现金分红类型**/
	public final static String TRADE_TYPE_XJFH = "C9";
	/**红利再投类型**/
	public final static String TRADE_TYPE_HLZT = "C10";
	/**费用类型**/
	public final static String TRADE_TYPE_FEE = "C11";
	/**产品到期分红类型**/
	public final static String TRADE_TYPE_DQFH = "C14";
	/**回补类型**/
	public final static String TRADE_TYPE_HB = "C16";
	/**提前终止类型**/
	public final static String TRADE_TYPE_TQZZ = "C20";
	/**赎回利息确认类型**/
	public final static String TRADE_TYPE_SHLXQR = "C15";
	/**提前赎回类型**/
	public final static String TRADE_TYPE_TQSH = "C17";
	/**违约赎回类型**/
	public final static String TRADE_TYPE_WYSH = "C18";
	/**接口数据开放计划变动数据**/
	public final static String DEAL_INTER_OPENSCH = "OPENSCH";
	/**接口数据产品报价变动数据**/
	public final static String DEAL_INTER_YIELD = "YIELD";
	/**接口数据产品费用交易变动数据**/
	public final static String DEAL_INTER_DURADEAL = "DURADEAL";
	/**接口数据产品交易变动数据**/
	public final static String DEAL_INTER_FEEDEAL = "FEEDEAL";
	
	@Autowired
	private ChbPrdDealInterfMapper chbPrdDealInterfMapper;

	@Autowired
	private ChbPrdBaseinfoInterfMapper chbPrdBaseinfoInterfMapper;
	
	@Autowired
	private SaleCustSvrreportMapper saleCustSvrreportMapper;

	@Autowired
	private SaleCustSvrreportVoMapper saleCustSvrreportVoMapper;
	
	@Autowired
	private SvrProdDealFileImpService svrProdDealFileImpService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private SqlSessionTemplate template;

	/**
     * 数据批量一次性insert条数(单条数据字段较多)
     */
    @Value("${application.reportInsertLongCount}")
    private int reportInsertLongCount;
	
	/**
	 * 基础数据接入处理
	 * @param batchTime
	 * @param prodIdList
	 * @param isFromSelect
	 */
	public HashMap<String,List<String>> generatePrdDealData(Date batchTime,List<String> prodIdList,boolean isSynchronizeTask) {	
        //数据从文件中读取入库
		logger.debug("上游产品存续期交易批处理开始:----开始------" +  df.format(new Date()));
	    HashMap<String,List<String>> mapPara = new HashMap<String,List<String>>();
		//处理存续交易信息
		ProdDealInterfService prodDealInterfService =  SpringContextUtil.getBean(ProdDealInterfService.class);
		Date last_updatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品存续交易信息接口.getInterId());
		List<ChbPrdDeal> resultList = prodDealInterfService.getPrdDealFromInterf(last_updatetime,prodIdList);
		//当前轮循时查询到的存续期交易更新的产品
//		List<String> updateProdList = new ArrayList<String>();
		if(!StringUtil.isEmptyList(resultList)){
//			updateProdList =
		    List<String> dealList = calPrdDealData(batchTime, resultList,last_updatetime,isSynchronizeTask);
		    mapPara.put(DEAL_INTER_DURADEAL, dealList);
		} 
        logger.debug("上游产品存续期交易批处理结束:----结束------" +  df.format(new Date()));
		
		return mapPara;
	}
	/**   
	* @Function: ProdDealInterfService.java
	* @Description: 阜新投资交易文件接入，直接从SALE_CUST_SVRREPORT抽取交易数据入中债CHB_PRD_DEAL表
	*
	* @param: batchTime 跑批日
	* @return：入库数据
	*
	* @author: 王冠/wangguan
	* @date: 2019年9月27日 下午3:50:20 
	*/
	public String generatePrdDealDataFx(Date batchTime) {	
        //数据从文件中读取入库
		logger.debug("上游产品存续期交易批处理开始:----开始------" +  df.format(new Date()));
		List<Map<String, String>> listData = new ArrayList<Map<String,String>>();
		List<SaleCustSvrreportVo> resultList = saleCustSvrreportVoMapper.getSaleCustSvrreportOfFx(batchTime);
		if(!resultList.isEmpty()) {
			for(SaleCustSvrreportVo deal : resultList) {
				if(deal != null) {
					// 查询父产品代码
					if(StringUtils.isNotBlank(deal.getFinprodId())) {
						// 从系统缓存中获取是否中债子系统(01：是 02：否)
						String isSubSystem = SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_IS_SUB_SYSTEM);
						String newFinprodId = deal.getFinprodId();
						if(StringUtil.equals(DictCodeListExt1.是或否_否.getValue(), isSubSystem)) {
							 newFinprodId = "F16_" + deal.getFinprodId();
						}
						String fProdId = chbPrdBaseinfoInterfMapper.getParentCode(newFinprodId);
						if(StringUtils.isNotBlank(fProdId)) {
					    	deal.setFPROD_ID(fProdId);
					    	//add 阜新接口接入分支信息时,产品编号与一体化接入保持一致都用F16
					    	deal.setFinprodId(newFinprodId);
						}
					}

					Map<String, String> map = new HashMap<>();
					BeanMap beanMap = BeanMap.create(deal);
					for (Object key : beanMap.keySet()) {
						Object object = beanMap.get(key);
						if(object!=null) {
							if(object instanceof Date) {
								map.put(key + "", DateUtil.format((Date) object));
							}else if (object instanceof BigDecimal) {
								map.put(key + "", ((BigDecimal) object).toPlainString());
							}else {
								map.put(key + "", object.toString());
							}
						}else {
							map.put(key + "", "");
						}
					}
					listData.add(map);
				}
			}
		}
		String result = "success";
		InterfFile interfile = new InterfFile();
		interfile.setDataSource("16");
		interfile.setFilename("CHB_PRD_DEAL_阜新文件接入" + df.format(batchTime));
		if(!listData.isEmpty()){
        	result = svrProdDealFileImpService.importDB(listData, interfile);
        }
        logger.debug("上游产品存续期交易批处理结束:----结束------" +  df.format(new Date()));
        
        return result;
	}
	
	public HashMap<String,List<String>> generatePrdFeeDealData(Date batchTime,List<String> prodIdList,boolean isSynchronizeTask) {
		//处理费用交易信息
		HashMap<String,List<String>> mapPara = new HashMap<String,List<String>>();
		Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品费用交易信息接口.getInterId());
		ProdDealInterfService prodDealInterfService =  SpringContextUtil.getBean(ProdDealInterfService.class);
		List<ChbPrdFeeDeal> listChbPrdFeeDeal = prodDealInterfService.getPrdFeeDealFromInterf(lastUpdatetime,prodIdList);
		
		logger.debug("上游费用交易信息入库:----开始------" +  df.format(new Date()));
		List<String> feeDealProdList = calPrdFeeDealData(batchTime, lastUpdatetime, listChbPrdFeeDeal,isSynchronizeTask);
		logger.debug("上游费用交易信息入库:----结束------" +  df.format(new Date()));
		if(isSynchronizeTask){
//			mapPara.put(DEAL_INTER_DURADEAL, updateProdList);
			mapPara.put(DEAL_INTER_FEEDEAL, feeDealProdList);
		}
        logger.debug("上游产品存续期交易批处理结束:----结束------" +  df.format(new Date()));
		
		return mapPara;
	}
	/**
	 * 获取最新的交易数据
	 * @param batchTime
	 * @param prodIdList
	 * @param isSynchronizeTask
	 * @return
	 */
	public HashMap<String,List<String>> getLastDealData(Date batchTime,List<String> prodIdList,boolean isSynchronizeTask) {
		logger.debug("上游产品存续期交易批处理开始:----开始------" +  df.format(new Date()));
	    HashMap<String,List<String>> mapPara = new HashMap<String,List<String>>();
		//处理存续交易信息
		Date lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品存续交易信息接口.getInterId());
		
		//获取最新交易数据
		List<ChbPrdDeal> resultList = chbPrdDealInterfMapper.selectDealList(lastUpdatetime);
		//当前轮循时查询到的存续期交易更新的产品
		List<String> updateProdList = new ArrayList<String>();
		if(!StringUtil.isEmptyList(resultList)){
			for(ChbPrdDeal dl : resultList){
				updateProdList.add(dl.getProdId());
				if(dl.getUpdateTime().compareTo(lastUpdatetime) > 0){
				    lastUpdatetime = dl.getUpdateTime();
			   }
			}
			//更新轮询接口表时间
			if(lastUpdatetime!=null){
	    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品存续交易信息接口.getInterId(), lastUpdatetime);
	    	}
		}

		
		//处理费用交易信息
		lastUpdatetime = ChbInterfUtil.queryUpdateTime(ChbInterfConfig.产品费用交易信息接口.getInterId());
		ProdDealInterfService prodDealInterfService =  SpringContextUtil.getBean(ProdDealInterfService.class);
		List<ChbPrdFeeDeal> listChbPrdFeeDeal = prodDealInterfService.getPrdFeeDealFromInterf(lastUpdatetime,prodIdList);
		
		logger.debug("上游费用交易信息入库:----开始------" +  df.format(new Date()));
		List<String> feeDealProdList = calPrdFeeDealData(batchTime, lastUpdatetime, listChbPrdFeeDeal,isSynchronizeTask);
		logger.debug("上游费用交易信息入库:----结束------" +  df.format(new Date()));
		if(isSynchronizeTask){
			mapPara.put(DEAL_INTER_DURADEAL,updateProdList);
			mapPara.put(DEAL_INTER_FEEDEAL, feeDealProdList);
		}
        logger.debug("上游产品存续期交易批处理结束:----结束------" +  df.format(new Date()));
		
		return mapPara;
	}


	/**
	 * 费用交易处理
	 * @param batchTime
	 * @param lastUpdatetime
	 * @param listChbPrdFeeDeal
	 * @return
	 */
	private List<String> calPrdFeeDealData(Date batchTime, Date lastUpdatetime,
			List<ChbPrdFeeDeal> listChbPrdFeeDeal,
			boolean isSynchronizeTask) {
		List<String> feeDealProdList = new ArrayList<String>();
		if(!StringUtil.isEmptyList(listChbPrdFeeDeal)){
		   for(ChbPrdFeeDeal dl : listChbPrdFeeDeal){
			   dl.setSeqno(IDGenerator.generate(36));
			   dl.setCreateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
			   dl.setUpdateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
			   if(isSynchronizeTask){
				   if(null != dl.getFprodId()){
					   if(!feeDealProdList.contains(dl.getFprodId())){
						   feeDealProdList.add(dl.getFprodId());
					   }
				   }else{
					   if(!feeDealProdList.contains(dl.getProdId())){
						   feeDealProdList.add(dl.getProdId());
					   }
				   }
			   }
			   if(dl.getUpdateTime().compareTo(lastUpdatetime) > 0){
			       lastUpdatetime = dl.getUpdateTime();
			   }
			   
		   }
		   insertPrdFeeDealDataMerge(listChbPrdFeeDeal);
//		   deletePrdFeeDealBatch(listChbPrdFeeDeal);
//		   insertPrdFeeDealBatch(listChbPrdFeeDeal);
		}
		//更新轮询接口表时间
		if(lastUpdatetime!=null){
    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品费用交易信息接口.getInterId(), lastUpdatetime);
    	}
		return feeDealProdList;
	}

	
	/**
	 * 费用交易处理
	 * @param listChbPrdFeeDeal
	 */
	public void insertPrdFeeDealBatch(List<ChbPrdFeeDeal> listChbPrdFeeDeal) {
		
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	//批量插入mapper
		ChbPrdDealInterfMapper chbPrdDealInterfMapper =  session.getMapper(ChbPrdDealInterfMapper.class);
		try{
			int count = 0;
			for(ChbPrdFeeDeal dl : listChbPrdFeeDeal){
				chbPrdDealInterfMapper.insertPrdFeeDeal(dl);
        		count++;
        		if(count % 1000==0){
            		session.commit();
            		session.clearCache();
        		}
        	}
			session.commit();
    		session.clearCache();
		}
//		catch (Exception e) {
//            logger.debug("批量插入费用交易出错:" + e.getMessage());
//			session.rollback();
//        } 
		finally {
    		session.close();
    	}
		//批量插入
	}


	public void deletePrdFeeDealBatch(List<ChbPrdFeeDeal> listChbPrdFeeDeal) {
		//先删后插
		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?
                							"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		List<List<ChbPrdFeeDeal>> bigList = createPrdFeeDealList(listChbPrdFeeDeal,singalCount);
		for(int i=0;i < bigList.size();i++){
			chbPrdDealInterfMapper.deletePrdFeeDealBatchList(bigList.get(i));
		}
	}


	private List<List<ChbPrdFeeDeal>> createPrdFeeDealList(List<ChbPrdFeeDeal> targe,int size) {
		List<List<ChbPrdFeeDeal>> listArr = new ArrayList<List<ChbPrdFeeDeal>>();
		//获取被拆分的数组个数
		int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;
		for(int i=0;i<arrSize;i++) {
			List<ChbPrdFeeDeal> sub = new ArrayList<ChbPrdFeeDeal>();
			//把指定索引数据放入到list中
			for(int j = i*size;j <= size*(i+1)-1;j++) {
				if(j <= targe.size()-1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}


	/**
	 * 产品端存续交易处理
	 * @param batchTime
	 * @param listChbPrdDeal
	 */
	private List<String> calPrdDealData(Date batchTime, List<ChbPrdDeal> listChbPrdDeal,Date last_updatetime,boolean isSynchronizeTask) {
		//认购数据需要更新要素表存续状态为01 存续，到期数据需要更新02 终止
		HashMap<String,ChbPrdDeal> dlMap = new HashMap<String,ChbPrdDeal>(); 
		List<String> duraDealList = new ArrayList<String>();
		String key = "";
		for(ChbPrdDeal dl : listChbPrdDeal){
			dl.setSeqno(IDGenerator.generate(36));
			dl.setIssuePartyId(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID));
			dl.setCreateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
			dl.setUpdateUser(SecurityUtil.getLoginUser() == null ?"system" :SecurityUtil.getLoginUser().getUid());
			if(DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue().equals(dl.getTradeType())
				|| DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(dl.getTradeType())){
				key = (null == dl.getFprodId() ? dl.getProdId() : dl.getFprodId());
				if(dlMap.containsKey(key)){
					if(DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(dl.getTradeType())){
						dlMap.put(key, dl);
					}
				}else{
					dlMap.put(key, dl);
				}
			}
			// 交易成本逻辑处理 add by gaojian 2019-07-23 begin
			// 交易类型为 03 存续期赎回、 16 到期兑付、 17 提前赎回时，交易成本有值，其余无值
			if (DictCodeListExt2.产品存续期登记交易类型_存续期赎回.getValue().equals(dl.getTradeType()) ||
					DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue().equals(dl.getTradeType()) ||
					DictCodeListExt2.产品存续期登记交易类型_提前赎回.getValue().equals(dl.getTradeType())) {
				// 预期收益型、货币型的交易成本等于交易份额,其余为已查询的赎回成本，无需重新赋值
				if (StringUtil.equals("N", dl.getBusiType())) {
					dl.setTradeCost(dl.getRedCost());
					dl.setIntAmt(dl.getRedprofit());
				} else {
					dl.setTradeCost(dl.getTradeQty());
				}
				// 交易类型为 01 募集期认购、 02 存续期申购、 05 收益再投资交易成本等于交易金额
			} else if (DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue().equals(dl.getTradeType()) ||
					DictCodeListExt2.产品存续期登记交易类型_存续期申购.getValue().equals(dl.getTradeType()) ||
					DictCodeListExt2.产品存续期登记交易类型_收益再投资.getValue().equals(dl.getTradeType())){
				dl.setTradeCost(dl.getTradePrince());
			}
			// 交易成本逻辑处理 add by gaojian 2019-07-23 end
			if(isSynchronizeTask){
				if(null != dl.getFprodId()){
				   if(!duraDealList.contains(dl.getFprodId())){
					   duraDealList.add(dl.getFprodId());
				   }
			    }else{
				   if(!duraDealList.contains(dl.getProdId())){
					   duraDealList.add(dl.getProdId());
				   }
			   }
			}
			
		   if(dl.getUpdateTime().compareTo(last_updatetime) > 0){
				last_updatetime = dl.getUpdateTime();
		   }
		   
		}
		
		//入库
		insertDataMerge(listChbPrdDeal);
//		deletePrdDealBatch(listChbPrdDeal);
//		insertPrdDealBatch(listChbPrdDeal);
//		insertPrdDeal(batchTime, listChbPrdDeal);
		
		logger.debug("反向更新要素信息:----开始-----" + df.format(new Date()));
		//反向更新要素信息
		ArrayList<ChbPrdDeal> updList = new ArrayList<ChbPrdDeal>();
		for(Map.Entry<String, ChbPrdDeal> entry: dlMap.entrySet()){
			ChbPrdDeal dl = entry.getValue();
			if(null != dl.getFprodId()){
				dl.setProdId(dl.getFprodId());
			}
			updList.add(dl);
        }
		
		if(!StringUtil.isEmptyList(updList)){
			updatePrdBaseInfo(updList);
		}
		logger.debug("反向更新要素信息:----结束-----" + df.format(new Date()));
		
		//更新轮询接口表时间
		if(last_updatetime!=null){
    		ChbInterfUtil.updateLastupdatetime(ChbInterfConfig.产品存续交易信息接口.getInterId(), last_updatetime);
    	}
		return duraDealList;
	}


	public void deletePrdDealBatch(List<ChbPrdDeal> listChbPrdDeal) {
		logger.debug("上游交易信息入库前删除:----开始------" + df.format(new Date()));
		int singalCount = Integer.parseInt(null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?
                							"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		List<List<ChbPrdDeal>> bigList = createPrdDealList(listChbPrdDeal,singalCount);
		for(int i=0;i < bigList.size();i++){
			chbPrdDealInterfMapper.deletePrdDealBatchList(bigList.get(i));
		}
		logger.debug("上游交易信息入库前删除:----结束------" + df.format(new Date()));
	}


	public void insertPrdDealBatch(List<ChbPrdDeal> listChbPrdDeal) {
		logger.debug("上游交易信息入库:----开始------" + df.format(new Date()));
		SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
    	//批量插入mapper
		ChbPrdDealInterfMapper chbPrdDealInterfMapper =  session.getMapper(ChbPrdDealInterfMapper.class);
		try{
			int count = 0;
			for(ChbPrdDeal dl : listChbPrdDeal){
				chbPrdDealInterfMapper.insertPrdDeal(dl);
        		count++;
        		if(count % 1000==0){
            		session.commit();
            		session.clearCache();
        		}
        	}	
			session.commit();
    		session.clearCache();
		}
//		catch (Exception e) {
//            logger.debug("批量插入交易出错:" + e.getMessage());
//			session.rollback();
//        } 
		finally {
    		session.close();
    	}
		logger.debug("上游交易信息入库:----结束------" + df.format(new Date()));
		
	}
	 /**
     * 功能:数据入库
     * @since 1.7.0
     * @version 1.7.0
     * @author yuluan 2019年5月5日 
     * @param assetInfoList 产品交易
     * @param 
     */
    public void insertDataMerge(List<ChbPrdDeal> prdDealList) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("产品交易插入开始--------------" + new Date());
            ChbPrdDealInterfMapper batchMapper = session.getMapper(ChbPrdDealInterfMapper.class);
            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;//每批commit的个数
            int batchLastIndex = batchCount;//每批最后一个的下标
            for(int index = 0; index < prdDealList.size();){
                if(batchLastIndex > prdDealList.size()-1){
                    batchLastIndex = prdDealList.size();
                    batchMapper.insertDataMerge(prdDealList.subList(index, batchLastIndex));
                    break;//数据插入完毕,退出循环
                }else{
                    batchMapper.insertDataMerge(prdDealList.subList(index, batchLastIndex));
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
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        finally {
            session.close();
        }
        logger.debug("产品交易插入结束--------------" + new Date());
    }
    
    private void insertPrdFeeDealDataMerge(List<ChbPrdFeeDeal> listNE) {
		 SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
			try {
					 	int count = 0;
			            logger.debug("产品费用交易插入开始--------------" + new Date());
			            ChbPrdDealInterfMapper batchMapper = session.getMapper(ChbPrdDealInterfMapper.class);
			            int batchCount = reportInsertLongCount==0?50:reportInsertLongCount;//每批commit的个数
			            int batchLastIndex = batchCount;//每批最后一个的下标
			            for(int index = 0; index < listNE.size();){
			                if(batchLastIndex > listNE.size()-1){
			                    batchLastIndex = listNE.size();
			                    batchMapper.insertChbPrdFeeDealData(listNE.subList(index, batchLastIndex));
			                    break;//数据插入完毕,退出循环
			                }else{
			                    batchMapper.insertChbPrdFeeDealData(listNE.subList(index, batchLastIndex));
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
			            session.commit();
			            // 清理缓存，防止溢出
			            session.clearCache();
			} finally {
				session.close();
			}
	 }
	/**
	 * 产品认购、到期后则更新状态
	 * @param dlList
	 */
	public void updatePrdBaseInfo(ArrayList<ChbPrdDeal> dlList) {
		chbPrdDealInterfMapper.updatePrdBaseinfo(dlList);
	}

	/**
	 * 将集合均分成几个大小一致的子集合
	 * @param 集合
	 * @param 子集合大小
	 * @return
	 */
	public static List<List<ChbPrdDeal>>  createPrdDealList(List<ChbPrdDeal> targe,int size) {
		List<List<ChbPrdDeal>> listArr = new ArrayList<List<ChbPrdDeal>>();
		//获取被拆分的数组个数
		int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;
		for(int i=0;i<arrSize;i++) {
			List<ChbPrdDeal> sub = new ArrayList<ChbPrdDeal>();
			//把指定索引数据放入到list中
			for(int j = i*size;j <= size*(i+1)-1;j++) {
				if(j <= targe.size()-1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}

	/**
	 * 持久化Data
	 * @param batchTime
	 * @param listChbPrdDeal
	 */
	/*private void insertPrdDeal(Date batchTime, List<ChbPrdDeal> listChbPrdDeal) {
		//批量插入
		int singalCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?
                							"500" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));
		int totalCount = listChbPrdDeal.size();
		if(totalCount > 0){
			if(totalCount<=singalCount){
				//先删后插
				chbPrdDealInterfMapper.deletePrdDealBatchList(listChbPrdDeal);
				chbPrdDealInterfMapper.insertPrdDealBatchList(listChbPrdDeal);
			}
			else{
				for(int i = 0; i < totalCount/singalCount ; i++){
					//先删后插
					chbPrdDealInterfMapper.deletePrdDealBatchList(listChbPrdDeal.subList(0, singalCount));
					chbPrdDealInterfMapper.insertPrdDealBatchList(listChbPrdDeal.subList(0, singalCount));
					listChbPrdDeal.removeAll(listChbPrdDeal.subList(0, singalCount));
				}
	            if(CollectionUtils.isNotEmpty(listChbPrdDeal)){
					//先删后插
					chbPrdDealInterfMapper.deletePrdDealBatchList(listChbPrdDeal);
	            	chbPrdDealInterfMapper.insertPrdDealBatchList(listChbPrdDeal);
	            }
			}
		}
	}*/
	

	/**
	 * 切换数据源 查询基础数据
	 * @param batchTime
	 * @param prodIdList
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@DataSource(DataSourceType.INTERF)
	public List<ChbPrdDeal> getPrdDealFromInterf(Date batchTime,List<String> prodIdList){
		logger.debug("开始读取上游交易信息:----开始------" + df.format(new Date()));
		//全量数据太慢
//		List<ChbPrdDeal> list = chbPrdDealInterfMapper.selectPrdDealByDate(batchTime,prodIdList);
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("batchTime", batchTime);
		paraMap.put("prodIdList", prodIdList);
		List<ChbPrdDeal> list = new ArrayList<ChbPrdDeal>();
		template.select("com.joyintech.tams.svreport.interf.chb.proddeal.dao.ChbPrdDealInterfMapper.selectPrdDealByDate",paraMap,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                list.add((ChbPrdDeal) resultContext.getResultObject());
            }
        });
		logger.debug("开始读取上游交易信息:----结束------" + df.format(new Date()));
		//赎回、赎回利息确认、到期、到期分红汇总编号相同的记录合并
		HashMap<String,ChbPrdDeal> map = new HashMap<String,ChbPrdDeal>();
		String key = "";
		logger.debug("处理上游交易信息:----开始------" + df.format(new Date()));
		for(ChbPrdDeal dl : list){
			if(null == dl.getProdId()){
				continue;
			}
			key = dl.getProdId() + "-" + (null == dl.getTotalTradeId() ? dl.getTradeId() : dl.getTotalTradeId());
			if(map.containsKey(key)){
				ChbPrdDeal  tmp = map.get(key);
				//相同TotalTradeId的取最大的UpdateTime
				if(dl.getUpdateTime()!=null&&tmp.getUpdateTime()!=null){
					if(dl.getUpdateTime().after(tmp.getUpdateTime())){
						tmp.setUpdateTime(dl.getUpdateTime());
					}
				}
				if(TRADE_TYPE_SHLXQR.equals(dl.getTradeType())
					|| TRADE_TYPE_DQFH.equals(dl.getTradeType())
					|| TRADE_TYPE_FX.equals(dl.getTradeType())){

				}else{
					tmp.setTradeType(dl.getTradeType());
				}
				tmp.setIntAmt((null == tmp.getIntAmt() ? BigDecimal.ZERO : tmp.getIntAmt())
						.add(null == dl.getIntAmt()?BigDecimal.ZERO:dl.getIntAmt()));
				tmp.setTradePrince((null == tmp.getTradePrince() ? BigDecimal.ZERO : tmp.getTradePrince())
						.add(null == dl.getTradePrince()?BigDecimal.ZERO:dl.getTradePrince()));


				map.put(key, tmp);
			}else{
				map.put(key,dl);
			}
		}
		
		ArrayList<ChbPrdDeal> result = new ArrayList<ChbPrdDeal>();
		for(Map.Entry<String, ChbPrdDeal> entry: map.entrySet()){
			ChbPrdDeal dl = entry.getValue();
			changeDealType(dl);
			result.add(dl);
        }

		logger.debug("处理上游交易信息:----结束------" + df.format(new Date()));
		return result;
	}

	/**
	 * 切换数据源,查询基础数据
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@DataSource(DataSourceType.INTERF)
	public List<ChbPrdFeeDeal> getPrdFeeDealFromInterf(Date batchTime,List<String> prodIdList){
		logger.debug("开始读取上游费用交易信息:----开始------" + df.format(new Date()));
		//全量数据太慢
//		List<ChbPrdFeeDeal> list =  chbPrdDealInterfMapper.selectPrdFeeDealByDate(batchDate,prodIdList);
		HashMap<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("batchTime", batchTime);
		paraMap.put("prodIdList", prodIdList);
		List<ChbPrdFeeDeal> list = new ArrayList<ChbPrdFeeDeal>();
		template.select("com.joyintech.tams.svreport.interf.chb.proddeal.dao.ChbPrdDealInterfMapper.selectPrdFeeDealByDate",paraMap,
                new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                list.add((ChbPrdFeeDeal) resultContext.getResultObject());
            }
        });
		logger.debug("开始读取上游费用交易信息:----结束------" + df.format(new Date()));
		return list;
	}

	/**
	 * 类型常量转换
	 * @param dl
	 */
	private void changeDealType(ChbPrdDeal dl) {
		if(TRADE_TYPE_RG.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_募集期认购.getValue());
		}else if(TRADE_TYPE_SG.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_存续期申购.getValue());
		}else if(TRADE_TYPE_FEQZ.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_份额增强.getValue());
		}else if(TRADE_TYPE_FEQJ.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_份额减弱.getValue());
		}else if(TRADE_TYPE_SH.equals(dl.getTradeType())
				||TRADE_TYPE_SHLXQR.equals(dl.getTradeType())
                ||TRADE_TYPE_WYSH.equals(dl.getTradeType())
                ||TRADE_TYPE_TQSH.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_存续期赎回.getValue());
		}else if(TRADE_TYPE_FX.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_收益分配.getValue());
		}else if (TRADE_TYPE_XJFH.equals(dl.getTradeType())) {
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_收益分配.getValue());
			//现金分红，int_amt需要写值
			if(null != dl.getIntAmt()){
				dl.setTradePrince(dl.getIntAmt());
			}else if(null != dl.getTradePrince()){
				dl.setIntAmt(dl.getTradePrince());
			}
		}else if(TRADE_TYPE_HLZT.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_收益再投资.getValue());
			if(null != dl.getIntAmt()){
				dl.setTradePrince(dl.getIntAmt());
			}else if(null != dl.getTradePrince()){
				dl.setIntAmt(dl.getTradePrince());
			}
		}else if(TRADE_TYPE_DQ.equals(dl.getTradeType())
				|| TRADE_TYPE_TQZZ.equals(dl.getTradeType())
				|| TRADE_TYPE_DQFH.equals(dl.getTradeType())){
			dl.setTradeType(DictCodeListExt2.产品存续期登记交易类型_到期兑付.getValue());
		}
	}
}
