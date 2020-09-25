package com.jixu.adapter.control.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.service.export.ExpAssembleOutput;
import com.jixu.adapter.service.impl.DataServiceCenter;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.JobModel;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;

public class AutoJobAction extends QuartzJobBean {

//	@Resource(name = "expAssembleOutput")
//	private ExpAssembleOutput exeExport;

	@Resource(name = "interafaceDataCenter")
	private DataServiceCenter exeImport;
	
	@Resource(name="expAssembleOutput")
	private ExpAssembleOutput exeExport;
	
	@Autowired
	private InterfaceAdapterDao interfaceAdapterDao;

	@Autowired
	private SessionFactory jixuSessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(AutoJobAction.class);

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		Session session = jixuSessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(jixuSessionFactory, new SessionHolder(session));

		try{
			JobModel scheduleJob = (JobModel) context.getMergedJobDataMap().get("scheduleJob");
			logger.info(scheduleJob.getJobName());
			int sourceid = scheduleJob.getJobID();
			
			ResultContainer container=null;
			InterfaceSource source=interfaceAdapterDao.loadEntity(InterfaceSource.class, sourceid);
			if(source!=null) {
				if(source.getSourceType() == InterfaceSourceType.exportType) {
					container= exeExport.executeExport(sourceid, null);
				} else {
					container= exeImport.importData(-99, sourceid, new Date(), 0);
					if (container != null) {
						List<MsgContainer> containerList = (List<MsgContainer>) container.getEntityObj();
						if (containerList != null) {
							for (MsgContainer tmpContainer : containerList) {
								if (!tmpContainer.isCorrectFlag())
									logger.error(tmpContainer.getErrorMsg());
							}
						}
					}
				}
			}

			
		}catch(Exception e){
			logger.error("Eror", e);
		}finally{
			session.close();
			TransactionSynchronizationManager.unbindResource(jixuSessionFactory);
		}
	}
}
