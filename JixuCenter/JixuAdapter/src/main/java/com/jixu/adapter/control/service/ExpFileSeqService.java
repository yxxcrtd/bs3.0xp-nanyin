package com.jixu.adapter.control.service;

public interface ExpFileSeqService {
	
	/**
	 * 创建文件序列
	 * @param seqname	序列名
	 * @param rolltype	序列规则
	 */
	public void updateSequence(int sourceID, String rolltype);
	
	/**
	 * 获取该序列的下一个值
	 * @param seqname
	 * @return
	 */
	public int getNextSeq(int sourceID);
	
	/**
	 * 动态获取下一个序列号，第一次用或者序列类型改变，重新计数
	 * @param sourceID
	 * @return
	 */
	public int genNextSeq(int sourceID, String rollType);
}
