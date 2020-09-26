package com.joyintech.tams.svreport.chb.investtradereg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chb.investtradereg.dto.ChbInvestTradeSubHisDto;
import com.joyintech.tams.svreport.chb.investtradereg.dto.InvestTradeRegist001Dto;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;

@Repository
public interface InvestTradeRegist001Mapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(InvestTradeRegist001Dto record);

    int insertSelective(InvestTradeRegist001Dto record);

    InvestTradeRegist001Dto selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(InvestTradeRegist001Dto record);

    int updateByPrimaryKey(InvestTradeRegist001Dto record);
    
    /**
     * @Description 分页查询
     * @param pageParam
     * @return
     * @return List<InvestTradeRegist001Dto>
     * @version 3.0 2019年1月25日
     */
    List<InvestTradeRegist001Dto> selectAllData(Map<String, Object> pageParam);
    /**
     * @Description 主键查询
     * @param dataList
     * @return
     * @return List<InvestTradeRegist001Dto>
     * @version 3.0 2019年1月25日
     */
    List<InvestTradeRegist001Dto>  selectAllDataByList(@Param("keyList") List<InvestTradeRegist001Dto> dataList);
    
    void updateSubmitStatusToOk(@Param("list") List<String> registSeqList,@Param("sendStatus") String sendStatus);
    /**
	 * @Description 手工处理
	 * @param sendStatus
	 * @param prodRaiseTotalList
	 * @return void
	 * @version 3.0 2019年1月25日
	 */
	void updateInterfSubmitAndSeqBatch(@Param("sendStatus")String sendStatus, @Param("registList") List<InvestTradeRegist001Dto> nvestTradeRegistList);
	/**
	 * @Description 更新所有检查通过的未报送数据，状态修改为接口报送
	 * @param sendStatus
	 * @return void
	 * @version 3.0 2019年1月25日
	 */
	void updateAllInterfSubmitAndSeqBatch(@Param("sendStatus") String sendStatus);
	/**
	 * @Description 取消手工处理
	 * @param registSeqList
	 * @return void
	 * @version 3.0 2019年1月25日
	 */
	void updateInvestTradeRegist(@Param("registSeqList") List<String> registSeqList);
	
	/**
	 * 功能:查询跑批日当天的要登记的投资交易信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	void selectInvestTradeForRegist(@Param("registDate")String registDate,
			@Param("tradenoList")List<String> tradenoList,@Param("prodList")List<String> prodList);
	
	/**
	 * 功能:批量插入投资交易登记记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int insertBatch(@Param("insertList")List<InvestTradeRegist001Dto> insertList);

	/**
	 * 功能:查询投资交易是否已经登记(手工处理、接口处理)
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	Integer selectIsExistAlreadyRegist(InvestTradeRegist001Dto dto);

	/**
	 * 功能:查询投资交易是否已经登记(手工处理、接口处理)
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	Integer selectIsExistNotRegist(InvestTradeRegist001Dto dto);
	
	/**
	 * 功能:批量更新投资交易登记记录
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月29日
	 */
	int updateBatch(@Param("updateList")List<InvestTradeRegist001Dto> updateList);

	/**
	 * 功能:根據產品id判斷產品是否是淨值型產品
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年1月30日
	 */
	String selectProdIsNProd(@Param("prodid")String prodid);

    /**
     * 获取行内交易编码下拉菜单
     * @param pageParam 查询参数
     * @return dto数据
     * @see
     * @since 1.6.0
     */
	List<SelectOptionDto> selectTradeNoCodeList(Map<String, Object> pageParam);
	
	/**
	 * 功能:获取交易编号序列
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月15日
	 * @return 
	 * String
	 */
	String getTradeNo();

	/**
	 * 功能:根据交易编号删除未报送的投资交易信息
	 * @since 1.6.0
	 * @version 1.6.0
	 * @author：weiyuqiang 2019年2月20日
	 * @param deleteList 删除的数据
	 * @return 
	 * int
	 */
	int deleteByTradeCode(@Param("deleteList")List<InvestTradeRegist001Dto> deleteList);

    /**
     * 功能:根据登记序号删除的投资交易信息
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
     * 功能:根据交易编号查询交易登记入履历表数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月21日
     * @param assetList
     * @return 
     * List<ChbAssetInfoSubHisDto>
     */
    List<ChbInvestTradeSubHisDto> selectInvestTradeRegHisByTradeCode(@Param("tradeList")List<String> tradeList);

    /**
     * 功能:查询所有的已登记的交易编码
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月21日
     * @return 
     * List<String>
     */
    List<InvestTradeRegist001Dto> selectAllTradeReg(@Param("registList")List<InvestTradeRegist001Dto> assetList);
    /**
     * 功能:将已报送数据移入履历表
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int insertReportHis(@Param("registList")List<InvestTradeRegist001Dto> registList);
    /**
     * 功能:删除登记表中的已报送数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int deleteLastSubmitRegist(@Param("registList")List<InvestTradeRegist001Dto> registList);
    /**
     * 取消手工处理时将履历表中的数据移回登记表
     * @param list
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList")List<InvestTradeRegist001Dto> registList);
    /**
     * 功能:删除履历表中的最新已报送数据
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年5月23日
     * @param registList
     * @return 
     * int
     */
    int deleteLastSubmitHis(@Param("registList")List<InvestTradeRegist001Dto> registList);
    
    /**
     * 根据数据主键查询数据
     * @param map 查询条件
     * @return 结果列表
     * @see
     * @since 1.0
     */
     List<InvestTradeRegist001Dto> selectDataListByRegList(@Param("registList")List<InvestTradeRegist001Dto> keys);
}