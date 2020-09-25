package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.NewFundContentDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.NewFundContent;

/**
 * @ClassName 		NewFundContentDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("newFundContentDao")
public class NewFundContentDaoImpl extends CommFuncDAOImpl implements NewFundContentDao{
	
	@Override
	public NewFundContent loadNewFundContent(int newFundID) {
		String hql="from NewFundContent where newFundID=:newFundID";
		String[] params={"newFundID"};
		Object[] values={newFundID};
		List<NewFundContent> contentList=this.executeHQL(hql, params, values);
		if(contentList.size()>0){
			return contentList.get(0);
		}else{
			return null;
		}
	}

}
