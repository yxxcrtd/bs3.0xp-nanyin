package com.jixu.adapter.control.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.adapter.control.service.ExpFileSeqService;
import com.jixu.adapter.dao.intf.ExpFileSeqDao;
import com.jixu.common.constant.JixuDictCodeList;
import com.jixu.common.util.DateUtil;
import com.orm.obj.ExpFileSeq;

@Service("expFileSeqService")
public class ExpFileSeqServiceImpl implements ExpFileSeqService{

	@Autowired
	private ExpFileSeqDao expFileSeqDao;
	
	@Override
	@Transactional()
	public void updateSequence(int sourceID, String rolltype) {
		if(rolltype==null || rolltype.equals(""))
			rolltype="01";
			
		ExpFileSeq seq=expFileSeqDao.loadEntity(ExpFileSeq.class, sourceID);
		if(seq!=null) {
			if(!rolltype.equals(seq.getRolltype())) {
				//更改序列类型后，更新
				seq.setStartnum(0);
				seq.setLatestnum(0);
				seq.setMaxnum(999999999);
				seq.setRolltype(rolltype);
				expFileSeqDao.updateEntity(seq);
			}
		}else {
			//序列不存在，新建
			seq=new ExpFileSeq();
			seq.setSourceID(sourceID);
			seq.setStartnum(0);
			seq.setMaxnum(999999999);
			seq.setRolltype(rolltype);
			expFileSeqDao.saveEntity(seq);
		}
	}
	
	@Override
	@Transactional()
	public int getNextSeq(int sourceID) {
		ExpFileSeq seq=expFileSeqDao.loadEntity(ExpFileSeq.class, sourceID);
		int latestnum=seq.getLatestnum();
		//int maxnum=seq.getMaxnum();
		Date lastupdatettime=seq.getUpdateDate();
		String rolltype=seq.getRolltype();
		
		if(rolltype.equals(JixuDictCodeList.EXPSEQ_01_TYPE.getValue())) {
			//序列01类型
			//每天0点序列从1开始计数
			String curDate=DateUtil.convertD2YYYYMMDD(new Date());
			String lastDate=DateUtil.convertD2YYYYMMDD(lastupdatettime);
			if(curDate.compareTo(lastDate)>0) {
				latestnum=1;
			}else {
				latestnum++;
			}
		}else if(rolltype.equals(JixuDictCodeList.EXPSEQ_02_TYPE.getValue())) {
			//序列一直增加
			latestnum++;
		}else {
			latestnum++;
		}
		
		seq.setLatestnum(latestnum);
		expFileSeqDao.updateEntity(seq);
		return latestnum;
	}
	
	/**
	 * 动态获取下一个序列号，第一次用或者序列类型改变，重新计数
	 * @param sourceID
	 * @return
	 */
	@Override
	@Transactional()
	public int genNextSeq(int sourceID, String rollType) {
		updateSequence(sourceID, rollType);
		return getNextSeq(sourceID);
	}
}
