package com.jixu.adapter.dbexe;

import java.util.HashMap;
import java.util.List;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.MsgContainer;

public interface ExecuteDBScripts {

	public int dbType();
	
	public void execute(MsgContainer container);
	
	public MsgContainer executeBatch(List<MsgContainer> containerList);
	
	public void exeMergerData(InterfaceSource source, String tableName) throws Exception;
	
	/**
	 * 在复合主键下，发现目标数据库有数据存在，则先全部删除，再做插入。
	 * 使用了两套key， 查找时候重复，使用查询key。删除使用删除key
	 * 使用场景：撤账之后重新做账，
	 * @param source
	 */
	public void exeMergerDeleteData(InterfaceSource source, String tableName);
	
	/**
	 * 
	 * @param map
	 */
	/**
	 * 把这一次数据迁移的最后一条数据的判断值写入到记录中，下次整合以这个结点为标记位
	 * 删除判定线程正在运行的标记位	
	 * @param importDataSuccessFlag
	 * @param childExeSuccessFlag
	 * @param map
	 * @param sourceID
	 */
	public void saveMaxCycleRmRunFlag(int sourceID, boolean importDataSuccessFlag, boolean childExeSuccessFlag, HashMap<Integer, Object> map);
	
	public int getMergeDeleteNums(InterfaceSource source, String tablename);
	
	/**
	 * 获取临时表的数据总量
	 * @param tableName
	 * @return
	 */
	public int getTmpTableRecordsNum(InterfaceSource source, String tablename);
	
	public void cleanTmpTableRecords(String tableName);
}
