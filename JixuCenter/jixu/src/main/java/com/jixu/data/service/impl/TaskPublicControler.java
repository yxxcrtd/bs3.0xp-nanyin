package com.jixu.data.service.impl;

/** 
 * @ClassName 		PublicTaskControler
 * @author  		Pan Wei 
 * @Date 			2018年4月2日 下午2:51:27 	
 * @Description		[]  
 */

public class TaskPublicControler implements Runnable{
	
	private Object entityBean;
	
	private PublicDataFactory publicDataFactory;
	
	public TaskPublicControler(PublicDataFactory publicDataFactory, Object entityBean) {
		this.setPublicDataFactory(publicDataFactory);
		this.setEntityBean(entityBean);
	}
	
	public PublicDataFactory getPublicDataFactory() {
		return publicDataFactory;
	}

	public void setPublicDataFactory(PublicDataFactory publicDataFactory) {
		this.publicDataFactory = publicDataFactory;
	}



	public Object getEntityBean() {
		return entityBean;
	}

	public void setEntityBean(Object entityBean) {
		this.entityBean = entityBean;
	}

	@Override
	public void run() {
		getPublicDataFactory().sendMsg(getEntityBean());
	}
}
