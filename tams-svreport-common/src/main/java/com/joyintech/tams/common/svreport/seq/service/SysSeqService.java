package com.joyintech.tams.common.svreport.seq.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.svreport.seq.dao.SequenceMapper;
import com.joyintech.tams.common.svreport.seq.dao.SysSeqMapper;
import com.joyintech.tams.common.svreport.seq.dto.SysSeqModel;


/**
 * 系统序列处理
 * @description:
 * @version:1.0 2015年5月4日
 * @author:wang han-Dylan
 */
@Transactional(rollbackFor=Exception.class)
@Service("sysSeqLogic")
@Scope("prototype")
public class SysSeqService {
	@Resource
	private SysSeqMapper sysSeqMapper;
	@Resource
	private SequenceMapper sequenceMapper;
    
    public SysSeqMapper getSysSeqMapper() {
        return sysSeqMapper;
    }
    
    public void setSysSeqMapper(SysSeqMapper sysSeqMapper) {
        this.sysSeqMapper = sysSeqMapper;
    }
    
    public SequenceMapper getSequenceMapper() {
        return sequenceMapper;
    }

    public void setSequenceMapper(SequenceMapper sequenceMapper) {
		this.sequenceMapper = sequenceMapper;
	}

    /**
     * 根据系统编码和当日日期查询系统序列
     * 
     * @param seqCode 序列编码
     * @param seqDate 日期
     * @param seqType 取值类型 Y:年  M:月    D:日
     * @return
     * @return String
     */
    public synchronized long getSysSeq(String seqCode,Date seqDate,String seqType){
        SysSeqModel sysSeqModel = new SysSeqModel();
        sysSeqModel.setSeqcode(seqCode);
        
        if(seqType == null||seqType==""){
            //默认按日    
            seqType = "D";
        }
        if(seqDate == null){
            seqDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(seqDate);
        
        String strDate = "";
        if("Y".equals(seqType)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            strDate = sdf.format(seqDate);
        }else if("M".equals(seqType)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            strDate = sdf.format(seqDate);
        }else if ( "D".equals(seqType)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            strDate = sdf.format(seqDate);
        }
        sysSeqModel.setSeqdate(strDate);
        //查询序列
        SysSeqModel seqModel = sysSeqMapper.selectByPrimaryKey(sysSeqModel);
        if(seqModel == null) {
            //添加序列
            sysSeqModel.setSeqvalue(1L);
            sysSeqModel.setRemark("1");
            sysSeqMapper.insertSelective(sysSeqModel);
        } else {
            sysSeqModel.setSeqvalue(seqModel.getSeqvalue()+1);
            sysSeqModel.setSeqdate(seqModel.getSeqdate());
            //更新序列
            sysSeqMapper.updateByPrimaryKeySelective(sysSeqModel);
        }
        return sysSeqModel.getSeqvalue();
    }
    
    /**
     * 根据sequence的名称，获取sequence的值
     * @param sequenceName 序列名称
     * @return 序列值
     */
    public int getSequenceVal(String sequenceName) {
    	return sequenceMapper.getSequenceVal(sequenceName);
    }
}
