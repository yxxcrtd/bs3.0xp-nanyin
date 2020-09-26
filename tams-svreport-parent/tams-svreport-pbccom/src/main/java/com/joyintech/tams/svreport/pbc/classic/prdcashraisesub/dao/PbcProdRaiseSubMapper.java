package com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.dto.PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSubVo;
public interface PbcProdRaiseSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(PbcProdRaiseSub record);

    int insertSelective(PbcProdRaiseSub record);

    PbcProdRaiseSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(PbcProdRaiseSub record);

    int updateByPrimaryKey(PbcProdRaiseSub record);

    /**
     * 功能:批量删除
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月11日
     * @param deletelist 
     * void
     */
	void deleteBatch(@Param("list")List<PbcProdRaiseSub> deletelist);

	/**
	 * 功能:根据月末日期和产品id查询相应的头寸信息
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年6月11日
	 * @param cdate 月末
	 * @param prodIdList 产品代码List
	 * @return 
	 * List<PbcProdRaiseSub>
	 */
	List<PbcProdRaiseSub> selectPrdCashRaise(@Param("ifpsbc")String ifpsbc,@Param("cdate")String cdate, @Param("prodIdList")List<String> prodIdList,@Param("pbcProdRaising") String pbcProdRaising);
	
	/**
	 * 功能:根据月末日期查询已经生成登记的数据
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年6月12日
	 * @param cdate
	 * @return 
	 * List<PbcProdRaiseSub>
	 */
	List<PbcProdRaiseSub> selectPrdCashRaiseRegist(@Param("cdate")String cdate);


	/**
	 * 功能:人行单产品报表查询-产品资金募集信息组合所有并排序
	 * @author：zhangmingming 2020年02月16日
	 * @param map
	 * @return
	 * List<PbcProdRaiseSub>
	 */
	List<PbcProdRaiseSub> selectPCD1All(Map<String,Object> map);

    /**
     * 功能:人行单产品报表查询-产品资金募集信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月13日
     * @param map
     * @return 
     * List<PbcMonthReport>
     */
    List<PbcProdRaiseSub> searchPageForOnePrd(Map<String,Object> map);
    
    /**
     * 手工处理
     * @param updateList
     * @return
     */
    int cancelManul(List<PbcProdRaiseSub> updateList);
	
    /**
     * 取消手工处理
     * @param updateList
     * @return
     */
	int doManul(@Param("list")List<PbcProdRaiseSub> updateList,@Param("submitadd")String submitadd);
	
	int insetBatchSubFromHisByUpdateTime(List<PbcProdRaiseSub> insertList);
	
	int deleteBatchSubFromHisByUpdateTime(List<PbcProdRaiseSub> insertList);	
	
	int insertHisBatchBySendStatus(List<PbcProdRaiseSub> insertList);
	
	void deleteBatchBySendStatus(List<PbcProdRaiseSub> deleteList);
	
    List<PbcProdRaiseSub> selectByDimension(Map<String,Object> map);
    
    List<PbcProdRaiseSub> selectByDimension2(Map<String,Object> map);

    /**
     * 功能:根据需要登记的数据查询已登记的数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月17日
     * @param raisingSubList
     * @return 
     * List<PbcProdRaiseSub>
     */
    List<PbcProdRaiseSub> selectRegList(@Param("registList")List<PbcProdRaiseSub> raisingSubList);
    
    /**
     * 根据登记序列获取资金募集信息
     * @param checkKeys 登记序列列表
     * @return 资金募集信息列表
     */
    List<PbcProdRaiseSub> queryListByKeys(@Param("list")List<String> checkKeys);
    
    List<String> selectRegistSeqnoList(@Param("list")List<PbcProdRaiseSub> registList,@Param("flag")String flag);

    //资金募集信息页面导出人行Excel的第一个sheet页 当期申购的明细数据
    List<PbcInitProdRaisingSubVo> searchListDetailSheet1(Map<String, Object> searchParam);
	
    //资金募集信息页面导出人行Excel的第二个sheet页 当期兑付的明细数据
	List<PbcInitProdRaisingSubVo> searchListDetailSheet2(Map<String, Object> searchParam);
	
	//资金募集信息页面导出人行Excel的第三个sheet页 期末产品的明细数据
	List<PbcInitProdRaisingSubVo> searchListDetailSheet3(Map<String, Object> searchParam);
	
	//资金募集信息页面导出人行Excel的第四个sheet页 净值的明细数据
	List<PbcProdRaiseSub> searchListDetailSheet4(Map<String, Object> searchParam);

	List<PbcProdRaiseSub> searchListDetailSheet4Yield(Map<String, Object> searchParam);

	/**
	 * 根据ID获取运作模式
	 * @param pbcPrdId 登记序列列表
	 * @return 运作模式
	 */
	List<String> selectOperateMode(String pbcProdId);
    
    
}