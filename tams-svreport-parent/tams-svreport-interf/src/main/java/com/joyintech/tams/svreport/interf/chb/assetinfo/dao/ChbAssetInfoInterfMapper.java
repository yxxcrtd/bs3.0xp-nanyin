package com.joyintech.tams.svreport.interf.chb.assetinfo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbAssetInfo;

@Repository
public interface ChbAssetInfoInterfMapper {
	/**
	 * 功能:批量更新资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int updateBatch(@Param("updateList")List<ChbAssetInfo> updateList);
	int updateBatchPsbc(@Param("updateList")List<ChbAssetInfo> updateList); 
	/**
	 * 功能:批量插入资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int insertBatch(@Param("insertList")List<ChbAssetInfo> insertList);
	int insertBatchPsbc(@Param("insertList")List<ChbAssetInfo> insertList);
	
	/**
	 * 功能:通过资产负债编码查询资产是否存在
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月18日
	 * @param assetCode
	 * @return 
	 * int
	 */
	int selectIsExistAssetByAssetCode(@Param("assetCode")String assetCode);
	
	/**
	 * 功能:根据资产代码删除的投资交易信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月20日
	 * @param insertList
	 * @return 
	 * int
	 */
	int deleteByAssetCodeList(@Param("insertList")List<ChbAssetInfo> insertList);
	
	/**
	 * 功能:根据资产负债类别和查询日期查询库中资产代码
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月20日
	 * @param assetTypeList 资产类别
	 * @param cdate  数据日期
	 * @return 
	 * List<String>
	 */
	List<String> selectAssetCodeByAssetType(@Param("assetTypeList")List<String> assetTypeList,@Param("cdate")String cdate);

	
	/**
	 * 功能:根据接入日期查询需要接入的资产要素信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月18日
	 * @param interfDate 接入日期
	 * @return 
	 */
	void selectAssetByInterfdateBatch(@Param("interfDate")Date interfDate ,
			@Param("prodList")List<String> prodList,@Param("gradeDateEnd") String gradeDateEnd,@Param("getProductRatingOrderSqlByZt") String getProductRatingOrderSqlByZt);
	void selectAssetByInterfdateBatchByPsbc(@Param("interfDate")Date interfDate ,
	                                  @Param("prodList")List<String> prodList,@Param("gradeDateEnd") String gradeDateEnd,@Param("getProductRatingOrderSqlByZt") String getProductRatingOrderSqlByZt);
	
	/**
	 * 功能:批量插入资产要素记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int insert(ChbAssetInfo assetInfo);
	int insertPsbc(ChbAssetInfo assetInfo);
	
	/**
	 * 功能:根据资产代码删除的投资交易信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月20日
	 * @param assetCode
	 * @return 
	 * int
	 */
	int deleteByAssetCode(@Param("assetCode")String assetCode);

    /**
     * 功能:清空资产要素表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月7日
     * @return 
     * int
     */
    int deleteAllData();
    
    /**
     * 功能:根据更新日期清除数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月7日
     * @param isToday 是否当天
     * @return 
     * int
     */
    int deleteBySysTime(@Param("isToday")boolean isToday);
    

    /**
     * 功能:批量插入/更新资产要素记录
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年1月29日
     * @param newDataList 新增数据
     */
    int mergeInfoList(@Param("newDataList")List<ChbAssetInfo> insertList);
    int mergeInfoListEx(@Param("newDataList")List<ChbAssetInfo> insertList);
    int mergeInfoListPsbc(@Param("newDataList")List<ChbAssetInfo> insertList);
    int mergeInfoListExPsbc(@Param("newDataList")List<ChbAssetInfo> insertList);
    
    
    /**
     * 功能:根据创建日期查询库中资产代码
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年2月20日
     * @param isToday 是否是今天创建的数据
     * @return 
     * List<String>
     */
    List<String> selectAssetCodeBySysdate(@Param("isToday")boolean isToday);

    /**
     * 功能:根据接入日期查询需要接入的通道资产要素信息
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年2月18日
     * @param interfDate 接入日期
     * @return 
     */
    void selectChlAssetByInterfdateBatch(@Param("interfDate")Date interfDate ,
            @Param("prodList")List<String> prodList);
    void selectChlAssetByInterfdateBatchPsbc(@Param("interfDate")Date interfDate ,
                                         @Param("prodList")List<String> prodList);
    
}
