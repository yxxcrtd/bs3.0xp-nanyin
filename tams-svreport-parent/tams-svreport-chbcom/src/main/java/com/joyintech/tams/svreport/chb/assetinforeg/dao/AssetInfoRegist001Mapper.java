package com.joyintech.tams.svreport.chb.assetinforeg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbAssetSubStatus;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.AssetInfoRegist001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.ChbAssetInfoSub001Dto;
import com.joyintech.tams.svreport.chb.assetinforeg.dto.ChbAssetInfoSubHisDto;

@Repository
public interface AssetInfoRegist001Mapper {
	int deleteByPrimaryKey(String registSeqno);

	int insert(AssetInfoRegist001Dto record);

    int insertBatch(ChbAssetInfoSub001Dto record);

	int insertSelective(AssetInfoRegist001Dto record);

	AssetInfoRegist001Dto selectByPrimaryKey(String registSeqno);

	int updateByPrimaryKeySelective(AssetInfoRegist001Dto record);

	int updateByPrimaryKey(AssetInfoRegist001Dto record);

	/**
	 * 资产要素登记分页列表查询
	 * <p>
	 * 根据指定条件查询列表数据
	 * </p>
	 * 
	 * @param param
	 *            查询参数
	 * @return 查询结果列表
	 */
	List<AssetInfoRegist001Dto> selectAssetInfoByPage(Map<String, Object> param);
	
	
	/**
	 * 资产要素登记分页列表查询
	 * <p>
	 * 根据指定条件查询列表数据
	 * </p>
	 * 
	 * @param param
	 *            查询参数
	 * @return 查询结果列表
	 */
	List<AssetInfoRegist001Dto> selectAssetInfoByPageEx(Map<String, Object> param);

	/**
	 * 功能:根据主键查询资产要素信息
	 * 
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月24日
	 */
	List<AssetInfoRegist001Dto> selectExportSelectList(@Param("assetList") List<AssetInfoRegist001Dto> assetList);

	/**
	 * 功能:根据主键查询资产要素信息
	 * 
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月24日
	 */
	List<AssetInfoRegist001Dto> selectExportSelectListSeq(Map<String, Object> map);
	
	
	/**
	 * 功能:根据主键查询资产要素信息
	 * update by leipeng 20200701 资产多法人改造
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月24日
	 */
	List<AssetInfoRegist001Dto> selectExportSelectListSeqEx(Map<String, Object> map);

	void updateSubmitStatusToOk(@Param("list") List<String> registSeqList,@Param("sendStatus") String sendStatus);

	/**
	 * @Description 手工处理
	 * @param sendStatus
	 * @param prodRaiseTotalList
	 * @return void
	 * @version 3.0 2019年1月24日
	 */
	void updateInterfSubmitAndSeqBatch(@Param("sendStatus") String sendStatus,
			@Param("registList") List<AssetInfoRegist001Dto> assetInfoRegist001Dto);

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
	void updateAssetInfoRegist(@Param("registSeqList") List<String> registSeqList);

    /**
     * 获取资产代码下拉菜单
     * @param pageParam 查询参数
     * @return dto数据
     * @see
     * @since 1.6.0
     */
	List<SelectOptionDto> selectAssetCodeList(Map<String, Object> pageParam);
	
	/**
	 * 功能:查询跑批日当天的要登记的资产要素信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
//	List<ChbAssetInfoSub001Dto> selectAssetInfoForRegist(@Param("registDate")String registDate,
//			@Param("assetList")List<String> assetList);
	List<ChbAssetInfoSub001Dto> selectAssetInfoForRegist(Map<String,Object>  paramMap);
	
	/**
	 * 功能:批量插入资产要素登记记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int insertBatch(@Param("insertList")List<AssetInfoRegist001Dto> insertList);

	/**
	 * 功能:查询资产要素是否已经登记(手工处理、接口处理)
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	Integer selectIsExistAlreadyRegist(AssetInfoRegist001Dto dto);

	/**
	 * 功能:查询资产要素是否已经登记(手工处理、接口处理)
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	Integer selectIsExistNotRegist(AssetInfoRegist001Dto dto);
	
	/**
	 * 功能:批量更新资产要素登记记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int updateBatch(@Param("updateList")List<AssetInfoRegist001Dto> updateList);
    
    /**
     * 功能:根据资产代码删除未报送的投资交易信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月20日
     * @param deleteList 删除的数据
     * @param sendStatusList 报送状态
     * @return 
     * int
     */

    int deleteByAssetCode(@Param("deleteList")List<AssetInfoRegist001Dto> deleteList);
	
	/**
	 * 功能:根据资产代码删除未报送的投资交易信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月20日
	 * @param deleteList 删除的数据
     * @param sendStatusList 报送状态
	 * @return 
	 * int
	 */
	int deleteByAssetCodeBatch(@Param("deleteList")List<ChbAssetInfoSub001Dto> deleteList);
    /**
     * 功能:根据履历序号删除的投资交易信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月20日
     * @param deleteList 删除的数据
     * @param sendStatusList 报送状态
     * @return 
     * int
     */
    int deleteByRegistseqno(@Param("deleteList")List<String> deleteList);
    
    /**
     * 功能:根据资产代码查询资产要素入履历表数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月21日
     * @param assetList
     * @return 
     * List<ChbAssetInfoSubHisDto>
     */
    List<ChbAssetInfoSubHisDto> selectAssetInfoRegHisByAssetCode(@Param("assetList")List<String> assetList);

    /**
     * 功能:查询所有的已登记的资产信息
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月21日
     * @return 
     * List<String>
     */
    List<ChbAssetInfoSub001Dto> selectAllAssetReg(@Param("registList")List<ChbAssetInfoSub001Dto> assetList);
    /**
     * 功能:将已报送数据移入履历表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int insertReportHis(@Param("registList")List<AssetInfoRegist001Dto> registList);
    /**
     * 功能:删除登记表中的已报送数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int deleteLastSubmitRegist(@Param("registList")List<AssetInfoRegist001Dto> registList);
    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<AssetInfoRegist001Dto> registList);
    
    
    /**
     * 取消手工处理时删除记录表数据 
     * update by leipeng 20200701 资产多法人改造
     * @param list
     * @see
     * @since 1.0
     */
    void deleteSubStausEx(@Param("newDataList")List<ChbAssetSubStatus> newDataList);
    
    
    /**
     * 功能:删除履历表中的最新已报送数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int deleteLastSubmitHis(@Param("registList")List<AssetInfoRegist001Dto> registList);
    
    
    /**
     * 功能:删除履历表中的最新已报送数据
     * update by leipeng 20200701 资产多法人改造
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int mergeSubStatusList(@Param("newDataList")List<ChbAssetSubStatus> newDataList);

	/**
     * 根据数据主键查询数据
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
     List<AssetInfoRegist001Dto> selectDataListByRegList(@Param("registList")List<AssetInfoRegist001Dto> keys);

 	 /**资产要素信息登记自动检查报送查询所有符合的数据
 	 * @param param  状态=新建||修改    报送状态=未报送    到期日<=跑披日   
 	 * @return
 	 */
 	List<AssetInfoRegist001Dto> selectAutoAssetInfo(Map<String, Object> param);
}