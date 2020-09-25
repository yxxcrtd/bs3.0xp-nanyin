package com.jixu.adapter.dao.impl;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.jixu.common.pojo.BasicEntity;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.util.LoginUserRequest;
import com.jixu.framework.dao.intf.CommFuncDAO;

@Repository
public class TargetDAO extends TargetDAOUtil implements CommFuncDAO {

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
}
