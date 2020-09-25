package com.jixu.adapter.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.util.DAOUtil;
import com.jixu.framework.dao.intf.CommFuncDAO;

public class TargetDAOUtil extends DAOUtil implements CommFuncDAO {

	@Override
	public <T extends BasicEntity> void saveAllEntity(List<T> entityList) {
		for(int i=0;i<entityList.size();i++){
			saveEntity(entityList.get(i));
		}
	}

	@Override
	public <T extends BasicEntity> void saveEntity(T entity) {
		Timestamp stamp=new Timestamp(new Date().getTime());
		String username=JixuUserUtil.getLoginUserName();
		entity.setCreateUser(username);
		entity.setCreateDate(stamp);
		entity.setUpdateUser(username);
		entity.setUpdateDate(stamp);
		saveEntityBean(entity);
	}

	@Override
	public <T extends BasicEntity> void updateEntity(T entity) {
		Timestamp stamp=new Timestamp(new Date().getTime());
		String username=JixuUserUtil.getLoginUserName();
		entity.setUpdateUser(username);
		entity.setUpdateDate(stamp);
		updateEntityBean(entity);
	}
	
	@Override
	public <T extends BasicEntity> void mergeEntity(T entity) {
		Timestamp stamp=new Timestamp(new Date().getTime());
		String username=JixuUserUtil.getLoginUserName();
		entity.setUpdateUser(username);
		entity.setUpdateDate(stamp);
		mergeEntityBean(entity);
	}
	
	@Override
	public <T> T loadEntity(Class<T> obj, Serializable id) {
		return (T)loadEntityBean(obj, id);
	}
}
