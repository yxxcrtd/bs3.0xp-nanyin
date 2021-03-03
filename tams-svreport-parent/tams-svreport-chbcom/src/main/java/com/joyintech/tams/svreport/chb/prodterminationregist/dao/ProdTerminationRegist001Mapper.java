package com.joyintech.tams.svreport.chb.prodterminationregist.dao;

import com.joyintech.tams.common.base.entity.ChbPrdDeal;
import com.joyintech.tams.common.base.entity.ChbPrdFeeDeal;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdForTerminationDto;
import com.joyintech.tams.svreport.chb.prodterminationregist.dto.ProdTerminationRegist001Dto;
import com.joyintech.tams.svreport.position.dto.ChbPrdFeePosition;
import com.joyintech.tams.svreport.position.dto.ChbPrdPosition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public interface ProdTerminationRegist001Mapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ProdTerminationRegist001Dto record);

    int insertSelective(ProdTerminationRegist001Dto record);

    ProdTerminationRegist001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ProdTerminationRegist001Dto record);

    int updateByPrimaryKey(ProdTerminationRegist001Dto record);

	List<ProdTerminationRegist001Dto> selectAllDataByList(@Param("keyList") List<ProdTerminationRegist001Dto> dataList);
	
	/**   
	* @Function: ProdTerminationRegist001Mapper.java
	* @Description: 用REGIST_SEQNO查询产品终止登记数据
	*
	* @param: keyList
	* @return：产品终止登记数据
	*
	* @author: 王冠/wangguan
	* @date: 2019年6月25日 下午3:48:23 
	*/
	List<ProdTerminationRegist001Dto> selectAllDataByKeyList(@Param("keyList") List<String> keyList);
	
	List<ProdTerminationRegist001Dto> selectAllData(Map<String, Object> pageParam);

	void updateSubmitStatusToOk(@Param("list") List<String> registSeqList);

	/**
	 * @Description 手工处理
	 * @param sendStatus
	 * @param prodTerminationList
	 * @return void
	 * @version 3.0 2019年1月24日
	 */
	void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList") List<ProdTerminationRegist001Dto> prodTerminationList);
	/**
	 * @Description 更新所有检查通过的未报送数据，状态修改为接口报送
	 * @param sendStatus
	 * @return void
	 * @version 3.0 2019年1月24日
	 */
	void updateAllInterfSubmitAndSeqBatch(@Param("sendStatus") String sendStatus);
	/**
	 * @Description 取消手工处理
	 * @param registSeqList
	 * @return void
	 * @version 3.0 2019年1月24日
	 */
	void updateTerminationRegist(@Param("list") List<String> registSeqList);
	/**
     * 获取需要进行批处理的产品及机构集合
     * @param date 批处理时间
     * @param tradType 交易类型
     * @param prodidlist 产品代码集合
     * @return  机构及产品集合
     */
    List<ProdForTerminationDto> getProdIdsByEndSub(@Param("batchDate") String date,
                                              @Param("tradType") String tradType,@Param("prodidlist") List<String> prodidlist);

	/**
     * 获取需要进行批处理的产品及机构集合
     * @param date 批处理时间
     * @param tradType 交易类型
     * @param prodidlist 产品代码集合
     * @return  机构及产品集合
     */
    List<ProdForTerminationDto> getProdIdsByEndSubTotal(@Param("batchDate") String date,
                                              @Param("tradType") String tradType,@Param("prodidlist") List<String> prodidlist);

    /**
     * 
     * 查询产品基本信息
     * @param tradType 交易类型
     * @param sendStatus 报送状态
     * @param issuingStatus 发行状态
     * @param list 产品id信息
     * @return 产品基本信息list
     */
    List<ProdForTerminationDto> getProdInfoByEndSub(@Param("tradType") String tradType,
                                               @Param("sendStatus") String sendStatus,
                                               @Param("issuingStatus") String issuingStatus,
                                               @Param("list") List<ProdForTerminationDto> list);
 
    /**
     * 
     * 查询产品基本信息
     * @param tradType 交易类型
     * @param sendStatus 报送状态
     * @param issuingStatus 发行状态
     * @param list 产品id信息
     * @return 产品基本信息list
     */
    List<ProdForTerminationDto> getProdInfoByEndSubTotal(@Param("tradType") String tradType,
                                               @Param("sendStatus") String sendStatus,
                                               @Param("issuingStatus") String issuingStatus,
                                               @Param("list") List<ProdForTerminationDto> list);

    /**
     * 获取产品费用信息
     * @param status 数据状态
     * @param list 产品id信息
     * @return 费用列表
     */
    List<ChbPrdFeeDeal> getPrdEndFeeList(@Param("status") String status,
                                         @Param("list") List<ProdForTerminationDto> list);
    /**
     * 获取交易信息
     * @param status 状态
     * @param list   产品相关信息
     * @return 产品信息列表
     */
    List<ChbPrdDeal> getPrdEndDeal(@Param("status") String status,@Param("list") List<ProdForTerminationDto> list);
    /**
     * 产品终止登记批处理批量插入
     * @param insertList
     * @return int
     */
    int insertBatch(@Param("insertList")List<ProdTerminationRegist001Dto> dtolist);
    /**
     * 产品终止登记批处理批量更新
     * @param updateList
     * @return int
     */
    int updateBatch(@Param("updateList")List<ProdTerminationRegist001Dto> dtolist);
    /**
     * 查询该产品是否已有登记数据
     * @param dto
     * @return String
     */
	String selectIsExistAlreadyRegist(ProdForTerminationDto dto);
	
	List<ChbPrdPosition> selectCustomProfit(@Param("prodid")String prodid,@Param("cdate")Date cdate); 
	
	int deleteBatch(@Param("list")List<String> list);
	
	int updateBymodel(@Param("list")List<String> list);

	List<ChbPrdFeePosition> getPrdFeePositionList(@Param("list")List<ProdForTerminationDto> prodIds);

	List<ChbPrdPosition> getPrdDealPositionList(@Param("list")List<ProdForTerminationDto> prodIds);

	List<String> selectRegistSeqnoList(@Param("list")List<ProdTerminationRegist001Dto> prodTerminationList,@Param("flag") String flag);

	void deleteRegistSeqnoList(@Param("regList")List<String> list);

	void insertSeletForSub(@Param("regList")List<String> list);

	List<ProdTerminationRegist001Dto> selectRegPerProdList(@Param("list")List<ProdForTerminationDto> list);

	BigDecimal isAssetPosZero(@Param("mdate")Date mdate, @Param("prodid")String prodid);
}