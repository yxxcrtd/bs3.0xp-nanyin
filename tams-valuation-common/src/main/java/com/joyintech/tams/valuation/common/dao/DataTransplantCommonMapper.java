package com.joyintech.tams.valuation.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.TrsSubjectRelation;
import com.joyintech.tams.valuation.common.interf.base.dto.BalanceTransplantDto;

@Repository
public interface DataTransplantCommonMapper {

    /**
     * 获取金融产品/投资组合对应的名称 <功能详细描述>
     * 
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public String findFinprodName(@Param("busiId") String busiId,
                                  @Param("busiDetailType") String busiDetailType);

    /**
     * 获取理财子产品对应的名称 <功能详细描述>
     * 
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public String findFinChildprodName(@Param("busiId") String busiId,
                                  @Param("busiDetailType") String busiDetailType,
                                  @Param("branch") Long branch);
    
    /**
     * 获取期限品种 <功能详细描述>
     * 
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public String findTermType(@Param("busiId") String busiId,
                               @Param("busiDetailType") String busiDetailType);

    /**
     * 根据分层代码获取对应的四级科目号名称 <功能详细描述>
     * 
     * @param booksetId
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public String findDetailSubnameTail(@Param("booksetId") String booksetId,
                                        @Param("busiId") String busiId,
                                        @Param("busiDetailType") String busiDetailType);

    /**
     * 投资组合：根据分层代码获取对应的四级科目号名称 <功能详细描述>
     * 
     * @param booksetId
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public String findDetailSubnameTailBySubject(@Param("booksetId") String booksetId,
                                                 @Param("busiId") String busiId,
                                                 @Param("busiDetailType") String busiDetailType);

    /**
     * 根据产品代码，获取产品类型<功能详细描述>
     * 
     * @param busiId
     * @return
     * @see
     * @since 1.0
     */
    public String findFinprodType(@Param("busiId") String busiId);

    /**
     * 获取对应的分层代码 <功能详细描述>
     * 
     * @param booksetId
     * @param busiId
     * @param busiDetailType
     * @return
     * @see
     * @since 1.0
     */
    public String findLayeringId(@Param("booksetId") String booksetId,
                                 @Param("busiId") String busiId,
                                 @Param("busiDetailType") String busiDetailType);

    /**
     * 获取科目信息
     * 
     * @param bookSetId
     * @param subjectNo
     * @return
     * @see
     * @since 1.0
     */
    public BokSubject findSubjectInfo(@Param("booksetId") String bookSetId,
                                      @Param("subjectNo") String subjectNo);

    /**
     * 判断账套表中是否存在该账套
     * 
     * @param booksetId
     * @return
     * @see
     * @since 1.0
     */
    public boolean checkBooksetId(@Param("booksetId") String booksetId);

    /**
     * 判断核算分层中是否存在该分层
     * 
     * @param booksetId
     * @return
     * @see
     * @since 1.0
     */
    public boolean checkLayering(@Param("layeringId") String layeringId);

    /**
     * 判断对应的标的是否存在
     * 
     * @param booksetId
     * @return
     * @see
     * @since 1.0
     */
    public boolean checkUnderlying(Map<String, Object> map);
  
    /**
     * 判断是否是成本类科目
     * 
     * @param subjectNo
     * @return
     * @see
     * @since 1.0
     */
    public boolean checkSubjectNature(@Param("subjectNo")String subjectNo);
    
    /**
     * 查询数据移植科目对应关系
     * add by wujie on 20181220
     */
    public List<TrsSubjectRelation> getTrsSubjectRelationAll();
    
    /**
     * 转换数据导入 add by wujie on 20181228
     * @param balanceList
     * @return
     */
    public int insertTrsBalanceTransplant(@Param("list")List<BalanceTransplantDto> list);
    
    /**
     * 转换后的数据查询 add by wujie on wujie on 20181228
     * @param batchSeqno
     * @return
     */
    public List<BalanceTransplantDto> queryBalanceTransplantList(@Param("batchSeqno")String batchSeqno);

}