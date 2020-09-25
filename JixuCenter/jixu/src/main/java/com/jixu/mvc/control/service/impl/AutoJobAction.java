//package com.jixu.mvc.control.service.impl;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate5.SessionHolder;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import com.jixu.adapter.service.export.ExpAssembleOutput;
//import com.jixu.common.pojo.InterfaceSource;
//import com.jixu.common.type.InterfaceSourceType;
//import com.jixu.common.type.JobModel;
//import com.jixu.common.type.MsgContainer;
//import com.jixu.common.type.ResultContainer;
//import com.jixu.common.util.DateUtil;
//import com.jixu.dao.intf.InterfaceSourceDao;
//import com.jixu.dao.intf.PortfolioInterfaceMapDao;
//import com.orm.obj.PortfolioInterfaceMap;
//
///**
// * @ClassName QuartzJobFactory
// * @author Pan Wei
// * @Date 2017年5月12日 下午2:57:30
// * @Description [通过配置,自动执行导出任务]
// */
//
//public class AutoJobAction extends QuartzJobBean {
//
//	@Resource(name = "expAssembleOutput")
//	private ExpAssembleOutput exeExport;
//
//	@Autowired
//	private SessionFactory jixuSessionFactory;
//	
//	@Autowired
//	private InterfaceSourceDao interfaceSourceDao;
//	
//	@Autowired
//	private PortfolioInterfaceMapDao portfolioInterfaceMapDao;
//
//	private static final Logger logger = LoggerFactory.getLogger(AutoJobAction.class);
//
//	@Override
//	protected void executeInternal(JobExecutionContext context)
//			throws JobExecutionException {
//		Session session = jixuSessionFactory.openSession();
//		TransactionSynchronizationManager.bindResource(jixuSessionFactory, new SessionHolder(session));
//
//		try{
//			JobModel scheduleJob = (JobModel) context.getMergedJobDataMap().get("scheduleJob");
//			logger.info(scheduleJob.getJobName());
//			int sourceid = scheduleJob.getJobID();
//			InterfaceSource source = (InterfaceSource) interfaceSourceDao.loadEntity(InterfaceSource.class, sourceid);
//			ResultContainer container = new ResultContainer();
//	
//			List<PortfolioInterfaceMap> mapList = portfolioInterfaceMapDao.loadPortInterfaceMapBySourceID(sourceid);
//			for (PortfolioInterfaceMap pi : mapList) {
//				String[] params = { String.valueOf(pi.getPortfolio().getId()), DateUtil.convertD2YYYY_MM_DD(new Date()) };
//				if (source.getSourceType() == InterfaceSourceType.exportType) {
//					container = exeExport.executeExport(sourceid, params);
//				}
////				else {
////					container = exeImport.importData(-99, sourceid, new Date(), WorkFlowSteps.tran);
////				}
//				if (container != null) {
//					List<MsgContainer> containerList = (List<MsgContainer>) container.getEntityObj();
//					if (containerList != null) {
//						for (MsgContainer tmpContainer : containerList) {
//							if (!tmpContainer.isCorrectFlag())
//								logger.error(tmpContainer.getErrorMsg());
//						}
//					}
//				}
//			}
//		}catch(Exception e){
//			logger.error("Error", e);
//		}finally{
//			session.close();
//			TransactionSynchronizationManager.unbindResource(jixuSessionFactory);
//		}
//	}
//}
