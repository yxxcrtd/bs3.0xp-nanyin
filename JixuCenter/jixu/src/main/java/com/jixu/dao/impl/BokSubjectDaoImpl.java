package com.jixu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jixu.dao.intf.BokSubjectDao;
import com.jixu.framework.dao.impl.CommFuncDAOImpl;
import com.orm.obj.BokSubject;
import com.orm.obj.Coaa;

/**
 * @ClassName 		BkInteRatioDaoImpl
 * @author  		Pan Wei 
 * @Date 			2018年10月10日 下午23:29:03 	
 * @Description		[]  
 */

@Repository("bokSubjectDao")
public class BokSubjectDaoImpl extends CommFuncDAOImpl implements BokSubjectDao{
	@Override
	public List<BokSubject> loadBokSubjectBySetID(String bookSetID) {
		String hql="from BokSubject where bokSubjectPK.bookset_id=:bookSetID order by bokSubjectPK.subject_no asc";
		return this.executeHQL(hql, "bookSetID", bookSetID);
	}
	
	@Override
	public List<BokSubject> loadBokSubjectLevel3BySetID(String bookSetID) {
		String hql="from BokSubject where bokSubjectPK.bookset_id=:bookSetID and subject_level=3 order by bokSubjectPK.subject_no asc";
		return this.executeHQL(hql, "bookSetID", bookSetID);
	}
	
	@Override
	public List<BokSubject> loadBokSubjectsByPortnum(String portnum) {
		String hql="select ca from BokSubject ca, PortfolioHdr po where ca.bokSubjectPK.bookset_id=po.coaahdr and po.id=:portnum and ca.subject_level=3 order by ca.id asc";
		String[] params={"portnum"};
		Object[] values={portnum};
		List<BokSubject> subjectList=this.executeHQL(hql, params, values);
		return subjectList;
	}
}
