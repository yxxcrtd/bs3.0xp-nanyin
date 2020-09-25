package com.jixu.job.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.AssetTypes;
import com.jixu.common.type.JobsType;
import com.jixu.common.type.JournalTypes;
import com.jixu.common.type.LongShortType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.ReturnResult;
import com.jixu.common.util.RoundUtil;
import com.jixu.dao.intf.JobsHisDao;
import com.jixu.dao.intf.TranCloDao;
import com.jixu.dao.intf.VoucherDao;
import com.jixu.job.obj.JobParam;
import com.jixu.job.service.AutoJobService;
import com.jixu.job.service.AutoMaturityService;
import com.jixu.job.service.CalNavService;
import com.jixu.job.service.CloseControlService;
import com.jixu.job.service.ExecuteJobService;
import com.jixu.job.service.JNLJob;
import com.jixu.secTran.SecurityCoreFactory;
import com.joyintech.tams.common.base.entity.PtlSecValution;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.jixu.positionmake.service.JxPtlSecValutionService;
import com.orm.obj.IssueInfo;
import com.orm.obj.JobsHis;
import com.orm.obj.PortfolioHdr;
import com.orm.obj.TranClo;

/** 
 * @ClassName 		ExecuteJob
 * @author  		Pan Wei 
 * @Date 			2016年10月11日 下午10:34:16 	
 * @Description		[]  
 */

@Service("executeJob")
public class ExecuteJob implements ExecuteJobService{
	
	@Resource(name="closeService")
	private CloseControlService closeJob;
	
	@Autowired
	private AutoJobService autoJobService;
	
	@Resource(name="executeJob")
	private ExecuteJobService executeJob;
	
	@Resource(name="calNavService")
	private CalNavService calNavJob;
	
	@Resource(name="subredJNLJob")
	private JNLJob subredJNLJob;
	
	@Resource(name="pursaleJnljob")
	private JNLJob pursaleJnljob;
	
	@Resource(name="cashjnlJob")
	private JNLJob cashjnlJob;

	@Resource(name="incomejnlJob")
	private JNLJob incomejnlJob;
	
	@Resource(name="expenseJNLJob")
	private JNLJob expenseJNLJob;
	
	@Resource(name="generalJnljob")
	private JNLJob generalJnlJob;
	
	@Resource(name="periodEndSetJob")
	private JNLJob periodEndSetJob;
	
	@Autowired
	private JNLJob afterJob;
	
	@Resource(name="secCoreFactory")
	protected SecurityCoreFactory secCoreFactory;
	
	@Resource(name="commfuncService")
	protected CommFuncService commfuncService;
	
	@Autowired
	private JobsHisDao jobsHisDao;
	
	@Autowired
	private VoucherDao voucherDao;
	
	@Autowired
	private TranCloDao tranCloDao;
	
	@Resource(name="taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired(required=false)
	private JxPtlSecValutionService jxPtlSecValutionService;
	
	@Resource(name="inteSetService")
	private AutoMaturityService inteSetService;
	
	@Value("${jixu.genValreport.flag:false}")
	private boolean genReportsFalg;
	
	private static final Logger logger = LoggerFactory.getLogger(ExecuteJob.class);
	
	//一次性执行的线程数量
	private int processNum=30;
	
	//并发自动作业job
	@Override
	public ResultContainer executeAutoJob(int userID, String[] portnums, Date ledgerDate) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		try {
			List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
			List<String> portnumList=new ArrayList<String>();
			for(int i=0;i<portnums.length;i++){
				//判断投资组合是否存在(衍生品没有投资通道产品)
				PortfolioHdr portfolio=commfuncService.loadPortfolio(portnums[i]);
				if(portfolio==null){
					continue;
				}
				portnumList.add(portnums[i]);
				if(i%processNum==0 && i !=0 || i==portnums.length-1){
			
					for(String portnum:portnums){
						JobAutoTaskControl task=new JobAutoTaskControl(autoJobService, userID, portnum, ledgerDate, RequestContextHolder.getRequestAttributes());
						Future<ResultContainer> future=taskExecutor.submit(task);
						resultList.add(future);
					}
		
					for(Future<ResultContainer> future:resultList){
						ResultContainer container=future.get();
						containerList.add(container);
						
						if(!container.isCorrectFlag()){
							mainContainer.setCorrectFlag(false);
							mainContainer.setErrorCode(container.getErrorCode());
							mainContainer.setErrorMsg(container.getErrorMsg());
						}
						
					}
					if(i%processNum==0 && i!=0 && i!=portnums.length-1){
						//当前还不是最后一条投资组合的时候，休息100L,防止组合过多并发执行导致线程池过多
						portnumList.clear();
						resultList.clear();
						Thread.sleep(100L);
					}
				}
			}
			
			
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error", e);
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorCode(e.hashCode());
			mainContainer.setErrorMsg(e.getMessage());
		} finally {
			mainContainer.setEntityObj(containerList);
		}
		
		return mainContainer;
	}
	
	//executeAutoJob对返回的错误信息进行了两次包装   一次并发   一次证券类型     jobcontroller里面只对消息进行一层解析 导致取不到正确的错误信息
	@Override
	public ResultContainer returnAutoJobFalseMsg(ResultContainer mainContainer) {
		// 解析并发对信息的封装 
		List<ResultContainer> containerList=(List<ResultContainer>)mainContainer.getEntityObj();
		List<ResultContainer> entityList=new ArrayList<ResultContainer>();
		List<ResultContainer> returnFalseMsg=new ArrayList<ResultContainer>();
		for(ResultContainer container:containerList){
			//解析证券类型对信息的封装       entityList 包含返回错误信息的list
			entityList=(List<ResultContainer>) container.getEntityObj();
			for (ResultContainer entity : entityList) {
				//如果返回信息错误  则放入returnFalseMsg中  最后放入mainContainer 传到控制层解析
				if(!entity.isCorrectFlag()){
					returnFalseMsg.add(entity);
				}
			}
		}
		mainContainer.setEntityObj(returnFalseMsg);
		return mainContainer;
	}
	
	
	@Override
	public ResultContainer executeCloseTranJob(int userID, String[] portnums, Date closeDate){
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		try {
			List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
			List<String> portnumList=new ArrayList<String>();
			for(int i=0;i<portnums.length;i++){
				//判断投资组合是否存在(衍生品没有投资通道产品)
				PortfolioHdr portfolio=commfuncService.loadPortfolio(portnums[i]);
				if(portfolio==null){
					continue;
				}
				portnumList.add(portnums[i]);
				if(i%processNum==0 && i !=0 || i==portnums.length-1){
					for(String portnum:portnumList){
						JobCloseTaskControl task=new JobCloseTaskControl(closeJob, userID, portnum, closeDate, RequestContextHolder.getRequestAttributes());
						Future<ResultContainer> future=taskExecutor.submit(task);
						resultList.add(future);
					}
		
					for(Future<ResultContainer> future:resultList){
						ResultContainer container=future.get();
						containerList.add(container);
						
						if(!container.isCorrectFlag()){
							mainContainer.setCorrectFlag(false);
							mainContainer.setErrorCode(container.getErrorCode());
							mainContainer.setErrorMsg(container.getErrorMsg());
						}
					}
					if(i%processNum==0 && i!=0 && i!=portnums.length-1){
						//当当前还不是最后一条投资组合的时候，休息100L,防止组合过多并发执行导致线程池过多
						portnumList.clear();
						resultList.clear();
						Thread.sleep(100L);
					}
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error", e);
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorCode(e.hashCode());
			mainContainer.setErrorMsg(e.getMessage());
		} finally {
			mainContainer.setEntityObj(containerList);
		}
		
		return mainContainer;
	}
	
	//并发计算净值job
	@Override
	public ResultContainer executeCreateNavJob(int userID, String[] portnums, Date closeDate){
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		try {
			List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
			List<String> portnumList=new ArrayList<String>();
			for(int i=0;i<portnums.length;i++){
				//判断投资组合是否存在(衍生品没有投资通道产品)
				PortfolioHdr portfolio=commfuncService.loadPortfolio(portnums[i]);
				if(portfolio==null){
					continue;
				}
				portnumList.add(portnums[i]);
				if(i%processNum==0 && i !=0 || i==portnums.length-1){
					for(String portnum:portnumList){
						JobCalNavTaskControl task=new JobCalNavTaskControl(calNavJob, userID, portnum, closeDate, RequestContextHolder.getRequestAttributes());
						Future<ResultContainer> future=taskExecutor.submit(task);
						resultList.add(future);
					}
		
					for(Future<ResultContainer> future:resultList){
						ResultContainer container=future.get();
						containerList.add(container);
						
						if(!container.isCorrectFlag()){
							mainContainer.setCorrectFlag(false);
							mainContainer.setErrorCode(container.getErrorCode());
							mainContainer.setErrorMsg(container.getErrorMsg());
						}
					}
					if(i%processNum==0 && i!=0 && i!=portnums.length-1){
						//当当前还不是最后一条投资组合的时候，休息100L,防止组合过多并发执行导致线程池过多
						portnumList.clear();
						resultList.clear();
						Thread.sleep(100L);
					}
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error", e);
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorCode(e.hashCode());
			mainContainer.setErrorMsg(e.getMessage());
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	//并发关总账job
	@Override
	public ResultContainer executeCloseGLJob(int userID, String[] portnums, Date closeDate){
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		List<PtlSecValution> ptlSecValutionList = new ArrayList<PtlSecValution>();
		try {
			List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
			List<String> portnumList=new ArrayList<String>();
			for(int i=0;i<portnums.length;i++){
				//判断投资组合是否存在(衍生品没有投资通道产品)
				PortfolioHdr portfolio=commfuncService.loadPortfolio(portnums[i]);
				if(portfolio==null){
					continue;
				}
				portnumList.add(portnums[i]);
				if(i%processNum==0 && i !=0 || i==portnums.length-1){
					for(String portnum:portnumList){
						JobCloseGLTaskControl task=new JobCloseGLTaskControl(closeJob, userID, portnum, closeDate, RequestContextHolder.getRequestAttributes());
						Future<ResultContainer> future=taskExecutor.submit(task);
						resultList.add(future);
					}
		
					for(Future<ResultContainer> future:resultList){
						ResultContainer container=future.get();
						containerList.add(container);
						
						if(!container.isCorrectFlag()){
							mainContainer.setCorrectFlag(false);
							mainContainer.setErrorCode(container.getErrorCode());
							mainContainer.setErrorMsg(container.getErrorMsg());
						}
					}
					
					//头寸信息入PtlSecValution类
					if(mainContainer.isCorrectFlag()){
						if(JixuUserUtil.isJoyinTablesFlag())
							ptlSecValutionExecuteJob(ptlSecValutionList,portnumList,closeDate);
					}
					
					if(i%processNum==0 && i!=0 && i!=portnums.length-1){
						//当当前还不是最后一条投资组合的时候，休息100L,防止组合过多并发执行导致线程池过多
						portnumList.clear();
						resultList.clear();
						Thread.sleep(100L);
					}
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error", e);
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorCode(e.hashCode());
			mainContainer.setErrorMsg(e.getMessage());
		} finally {
			mainContainer.setEntityObj(containerList);
		}
		//头寸信息入兆尹PtlSecValution
		if(mainContainer.isCorrectFlag()){
			if(JixuUserUtil.isJoyinTablesFlag())
				jxPtlSecValutionService.insertBatch(ptlSecValutionList, closeDate);
		}
		return mainContainer;
	}
	
	//头寸信息入兆尹PtlSecValution
	protected void ptlSecValutionExecuteJob(List<PtlSecValution> ptlSecValutionList,List<String> portnumList, Date closeDate){
		for(String portnum:portnumList){
			List<TranClo> list=tranCloDao.loadTranClose(portnum, closeDate);
			//投资组合id 调用兆尹根据账套代码查询组合代码方法
			String portfolioId=jxPtlSecValutionService.queryPortFolioId(portnum);
			PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
			for (TranClo tranClo : list) {
				//判断如果市值为0 跳过(掉期是生成两笔tranclo)
				if(tranClo.getgMkValue()==0){
					continue;
				}
				IssueInfo sec=commfuncService.loadSecByCusip(tranClo.getCusip());
				double prinFxRate = commfuncService.loadFxrate(portfolio.getId(), portfolio.getCurID(), sec.getPriCur(), closeDate);
				PtlSecValution pvt=new PtlSecValution();
				//外汇相关头寸成本都设为0
				if(AssetTypes.getFxAsset().containsKey(sec.getIssueType())){
					//成本原币
					pvt.setDscCostAmt(new BigDecimal(0));
					//成本本币
					pvt.setDscCostAmtB(new BigDecimal(0));
				}else{
					//成本原币
					pvt.setDscCostAmt(new BigDecimal(tranClo.getgCost()));
					//成本本币
					pvt.setDscCostAmtB(new BigDecimal(tranClo.getgCostFunc()));
				}
				
				//公允价值变动原币
				double gyjz=RoundUtil.roundMoney2Dec(tranClo.getgMkValue()-pvt.getDscCostAmt().doubleValue());
				pvt.setTdyFloatIngpl(new BigDecimal(gyjz));
				//公允价值变动本币
				double gMkValueFunc=RoundUtil.roundMoney2Dec(tranClo.getgMkValue()*prinFxRate);
				double gyjzFunc=RoundUtil.roundMoney2Dec(gMkValueFunc-pvt.getDscCostAmtB().doubleValue());
				pvt.setTdyFloatIngplB(new BigDecimal(gyjzFunc));
				//投资组合id
				pvt.setPortfolioId(portfolioId);
				//金融产品id
				pvt.setFinprodId(sec.getSecurityid());
				//投资目的
				pvt.setInvAim("999999");
				//日期
				pvt.setCdate(closeDate);
				//证券账户id
				pvt.setSecAcctId(String.valueOf(tranClo.getSecacct()));
				//数量
				pvt.setShareAmt(new BigDecimal(tranClo.getAmount()));
				//币种本币
				pvt.setbCcy(portfolio.getCurID());
				//币种原币
				pvt.setCcy(sec.getPriCur());
				//当日应计利息   UPDATE BY XR 应取当日累计利息
				pvt.setTdyIntincexp((new BigDecimal(tranClo.getAccrued())));
				//add jinmaodong 2019-08-23 增加生成方式-几许生成
				pvt.setGenType(DictCodeListExt1.生成方式_几许生成.getValue());
				
				if(sec.getIssueType().equals(AssetTypes.SWAP_INTEREST)) {
					//利率互换需要查出两条clo，把收入端和支出端利息放入到头寸里
					for(TranClo clotmp:list) {
						if(tranClo.getCusip().equals(clotmp.getCusip()) && clotmp.getLsindicator()==LongShortType.ShortTerm) {
							pvt.setInTdyIntincexp(new BigDecimal(tranClo.getAccrued()));
							pvt.setOutTdyIntincexp(new BigDecimal(clotmp.getAccrued()));
							break;
						}
					}
				}
				ptlSecValutionList.add(pvt);
			}
		}
	}
	
	/**
	 * 运行指定的job
	 * @param portnums
	 * @param closeDate
	 * @param jobIDs
	 * @return
	 */
	public ResultContainer executeAppointedDailyJob(String[] portnums, Date closeDate, int[] jobIDs) {
		List<JNLJob> jobList=new ArrayList<JNLJob>();
		boolean alfterjobfalg=false;
		for(int jobid:jobIDs) {
			JNLJob jnlJob=null;
			if(jobid==JobsType.CASH)
				jnlJob=cashjnlJob;
			else if(jobid==JobsType.SUBRED)
				jnlJob=subredJNLJob;
			else if(jobid==JobsType.PURSALEJNL)
				jnlJob=pursaleJnljob;
			else if(jobid==JobsType.INCOME)
				jnlJob=incomejnlJob;
			else if(jobid==JobsType.GENERAL)
				jnlJob=generalJnlJob;
			else if(jobid==JobsType.EXPEND)
				jnlJob=expenseJNLJob;
			else if(jobid==JobsType.AfterJob)
				alfterjobfalg=true;
			else 
				continue;
			
			jobList.add(jnlJob);
		}
		
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		int userID=JixuUserUtil.getUserID();
		
		try {
			List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
			List<String> portnumList=new ArrayList<String>();
			for(int i=0;i<portnums.length;i++){
				//判断投资组合是否存在(衍生品没有投资通道产品)
				PortfolioHdr portfolio=commfuncService.loadPortfolio(portnums[i]);
				if(portfolio==null){
					continue;
				}
				portnumList.add(portnums[i]);
				if(i%processNum==0 && i !=0 || i==portnums.length-1){
					//多线程执行所有job，并限制每次最多并发执行processNum个线程
					for(String portnum:portnums){
						JobParam jobParam = new JobParam();
						jobParam.setDate(closeDate);
						jobParam.setPortfolio(portnum);
						jobParam.setUserID(userID);
						for(JNLJob job:jobList){
							JobTaskControl task=new JobTaskControl(job, jobParam, RequestContextHolder.getRequestAttributes());
							Future<ResultContainer> future=taskExecutor.submit(task);
							resultList.add(future);
						}
					}

					for(Future<ResultContainer> future:resultList){
						ResultContainer container=future.get();
						containerList.add(container);
						
						if(!container.isCorrectFlag()){
							mainContainer.setCorrectFlag(false);
							mainContainer.setErrorCode(container.getErrorCode());
							mainContainer.setErrorMsg(container.getErrorMsg());
						}
					}
					
					//最后执行AfterJob-----------------------------------------------
					if(alfterjobfalg) {
						List<Future<ResultContainer>> afterResultList=new ArrayList<Future<ResultContainer>>();
						for(String portnum:portnums){
							JobParam jobParam = new JobParam();
							jobParam.setDate(closeDate);
							jobParam.setPortfolio(portnum);
							jobParam.setUserID(userID);
							JobTaskControl task=new JobTaskControl(afterJob, jobParam, RequestContextHolder.getRequestAttributes());
							Future<ResultContainer> future=taskExecutor.submit(task);
							afterResultList.add(future);
						}
						
						for(Future<ResultContainer> future:afterResultList){
							ResultContainer container=future.get();
							containerList.add(container);
							
							if(!container.isCorrectFlag()){
								mainContainer.setCorrectFlag(false);
								mainContainer.setErrorCode(container.getErrorCode());
								mainContainer.setErrorMsg(container.getErrorMsg());
							}
						}
					}
				}
				
				if(i%processNum==0 && i!=0 && i!=portnums.length-1){
					//当当前还不是最后一条投资组合的时候，休息100L,防止组合过多并发执行导致线程池过多
					portnumList.clear();
					resultList.clear();
					Thread.sleep(100L);
				}
			}
			
			
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error", e);
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorCode(e.hashCode());
			mainContainer.setErrorMsg(e.getMessage());
		}finally {
			mainContainer.setEntityObj(containerList);
		}
		return mainContainer;
	}
	
	public ResultContainer executeDailyJob(int userID, String[] portnums, Date closeDate) {
		int[] jobs= {JobsType.PURSALEJNL, JobsType.GENERAL, JobsType.INCOME, JobsType.CASH, JobsType.SUBRED, JobsType.EXPEND};
		return executeAppointedDailyJob(portnums, closeDate, jobs);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer executeJob(int userID, String portnum, Date closeDate, int jobType){
		JNLJob jnlJob=null;
		
		switch(jobType){
		case JobsType.PeriodSet:
			jnlJob=periodEndSetJob;
			break;
		case JobsType.CASH:
			jnlJob=cashjnlJob;
			break;
		case JobsType.SUBRED:
			jnlJob=subredJNLJob;
			break;
		case JobsType.PURSALEJNL:
			jnlJob=pursaleJnljob;
			break;
		case JobsType.INCOME:
			jnlJob=incomejnlJob;
			break;
		case JobsType.GENERAL:
			jnlJob=generalJnlJob;
			break;
		case JobsType.EXPEND:
			jnlJob=expenseJNLJob;
			break;
		}
		
		if(jnlJob==null){
			ResultContainer container=new ResultContainer();
			container.setCorrectFlag(false);
			container.setErrorCode(ReturnResult.jobType_not_exist);
			container.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.jobType_not_exist));
			return container;
		}
		
		JobParam jobParam = new JobParam();
		jobParam.setDate(closeDate);
		jobParam.setPortfolio(portnum);
		jobParam.setUserID(userID);
		return jnlJob.execute(jobParam);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer removeJob(int userID, String[] portnums, Date closeDate) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		
		int[] jobTypes=new int[]{JobsType.PURSALEJNL,JobsType.INCOME,JobsType.SUBRED,JobsType.EXPEND,JobsType.GENERAL,JobsType.CASH};
		
		boolean status=true;
		String errorMsg="";
		try{
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				voucherDao.deleteAllVouchers(portnums, closeDate);
				jobsHisDao.deleteJobsHists(portnums, closeDate, jobTypes);
			}
		}catch(Exception e){
			logger.error("Error", e);
			errorMsg=e.getMessage();
			status=false;
		}
		
		for(String portnum:portnums){
			for(int jobType:jobTypes){
				ResultContainer container=new ResultContainer();
				PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
				container.setHeader(JobsType.jobsTypeHM.get(jobType));
				container.setEntityObj(portfolio);
				if(!status){
					container.setCorrectFlag(false);
					container.setErrorMsg("请联系管理员");
					container.setEntityObj(portfolio);
				}
				containerList.add(container);
			}
		}
		
		if(!status){
			mainContainer.setCorrectFlag(false);
			mainContainer.setErrorMsg(errorMsg);
		}
		
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	private ResultContainer removeAllJob(int userID, String portnum, Date closeDate) {
		int[] jobTypes=new int[]{JobsType.PURSALEJNL,JobsType.INCOME,JobsType.SUBRED,JobsType.EXPEND,JobsType.GENERAL,JobsType.CASH};
		
		boolean status=true;
		String errorMsg="";
		try{
			if(!JixuUserUtil.isJoyinTablesFlag()) {
				String[] ports=new String[] {portnum};
				voucherDao.deleteAllVouchers(ports, closeDate);
				jobsHisDao.deleteJobsHists(ports, closeDate, jobTypes);
			}
		}catch(Exception e){
			logger.error("Error", e);
			errorMsg=e.getMessage();
			status=false;
		}
		
		ResultContainer container=new ResultContainer();
		PortfolioHdr portfolio=commfuncService.loadPortfolio(portnum);
		container.setHeader("凭证");
		container.setEntityID(portfolio.getId() + " | " + portfolio.getShortCode());
		container.setEntityObj(portfolio);
		if(!status){
			container.setCorrectFlag(false);
			container.setErrorMsg("请联系管理员");
			container.setEntityObj(portfolio);
		}
		
		if(!status){
			container.setCorrectFlag(false);
			container.setErrorMsg(errorMsg);
		}
		return container;
	}
	
	public ResultContainer reExecuteJob(int userID, String portnum, Date ledgerDate, int jobHisID){
		JobsHis jobhis=(JobsHis)jobsHisDao.loadEntity(JobsHis.class, jobHisID);
		return executeJob(userID, portnum, ledgerDate, jobhis.getJobID());
	}
	
	@Override
	public ResultContainer executeJob(int userID, String[] portnums, Date closeDate, int jobType) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		
		for(String portnum:portnums){
			ResultContainer container=executeJob(userID, portnum, closeDate, jobType);
			container.setEntityID(portnum);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public ResultContainer removeJob(int userID, String[] portnums, Date closeDate, int journalType) {
		ResultContainer mainContainer=new ResultContainer();
		List<ResultContainer> containerList=new ArrayList<ResultContainer>();
		JNLJob jnlJob=null;
		
		if(journalType==JournalTypes.SETDOWN){
			jnlJob=periodEndSetJob;
		}
		
		for(String portnum:portnums){
			ResultContainer container=new ResultContainer();
			jnlJob.removeJob(portnum, closeDate, container);
			if(!container.isCorrectFlag()){
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorCode(container.getErrorCode());
				mainContainer.setErrorMsg(container.getErrorMsg());
			}
			containerList.add(container);
		}
		mainContainer.setEntityObj(containerList);
		return mainContainer;
	}
	
	@Override
	public void genValuationReports(String[] portnumsArr, Date closeDate) {
		if(!genReportsFalg){
			//是否需要生成估值表等报表
			return;
		}
		
		try {
			List<Future<ResultContainer>> resultList=new ArrayList<Future<ResultContainer>>();
			List<String> portnums=new ArrayList<String>();
			for(int i=0;i<portnumsArr.length;i++){
				portnums.add(portnumsArr[i]);
				if(i%processNum==0 && i !=0 || i==portnumsArr.length-1){
			
					for(String portnum:portnums){
						JobValReportTaskControl task=new JobValReportTaskControl(calNavJob, portnum, closeDate, RequestContextHolder.getRequestAttributes());
						Future<ResultContainer> future=taskExecutor.submit(task);
						resultList.add(future);
					}
		
					for(Future<ResultContainer> future:resultList){
						ResultContainer container=future.get();
					}
					if(i%processNum==0 && i!=0 && i!=portnumsArr.length-1){
						//当当前还不是最后一条投资组合的时候，休息100L,防止组合过多并发执行导致线程池过多
						portnums.clear();
						resultList.clear();
						Thread.sleep(100L);
					}
				}
			}
			
			
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error", e);
		}
	}
	
	@Override
	public ResultContainer executeJobByJobID(String portnum, Date ledgerDate, int jobnum) {
		int userid=JixuUserUtil.getUserID();
		JobParam jobParam = new JobParam();
		jobParam.setDate(ledgerDate);
		jobParam.setPortfolio(portnum);
		jobParam.setUserID(userid);
		
		ResultContainer container=null;
		if(jobnum==JobsType.ContractCash) {
			//自动结转
			container=autoJobService.executeAutoJob(userid, portnum, ledgerDate);
		}else if(jobnum==JobsType.closeTran) {
			//交易关账
			container=closeJob.closeTran(userid, portnum, ledgerDate);
		}else if(jobnum==JobsType.PURSALEJNL) {
			//交易凭证
			container=pursaleJnljob.execute(jobParam);
		}else if(jobnum==JobsType.INCOME) {
			//收入凭证
			container=incomejnlJob.execute(jobParam);
		}else if(jobnum==JobsType.EXPEND) {
			//费用凭证
			container=expenseJNLJob.execute(jobParam);
		}else if(jobnum==JobsType.GENERAL) {
			//估增凭证
			container=generalJnlJob.execute(jobParam);
		}else if(jobnum==JobsType.SUBRED) {
			//申购赎回凭证
			container=subredJNLJob.execute(jobParam);
		}else if(jobnum==JobsType.CASH) {
			//申购赎回凭证
			container=cashjnlJob.execute(jobParam);
		}else if(jobnum==JobsType.calNav) {
			//计算净值
			container=calNavJob.createNav(userid, portnum, ledgerDate);
		}else if(jobnum==JobsType.clostGL) {
			//关总账
			container=closeJob.closeGL(userid, portnum, ledgerDate);
		}else if(jobnum==JobsType.removeDailyVoucherJob) {
			//撤账，删除凭证
			container=removeAllJob(userid, portnum, ledgerDate);
		}
		
		return container;
	}
}
