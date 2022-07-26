package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

import com.joyintech.tams.common.base.dao.DisNostdCreditorMapper;
import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UnClaimAssetDetailMapper  {
    /**
     *页面查询方法
     * @param pageParam
     * @return
     */
    List<UnClaimAssetDetailDto> selectForPage(Map<String, Object> pageParam);

    /**
     * 获取产品代码和名称联想文本框
     * @param param
     * @return
     */
    List<SelectOptionDto> getProdInfo(Map<String, Object> param);


    /**
     * 导出选中的数据
     * @param investList
     * @return
     */
    List<UnClaimAssetDetailDto> selectForExport(@Param("list") List<UnClaimAssetDetailDto> investList);

    /**
     * 根据产品代码查询产品
     * @param prodId
     * @return
     */
    int selectByProdId(String prodId);


    /**
     *
     * 双击查看，修改
     * @param map
     * @return
     */
    UnClaimAssetDetailDto selectDetail(Map<String, Object> map);

    /**
     *
     * 新建修改非标资产明细列表查询
     * @param map
     * @return
     */
   List<UnClaimAssetDetailDto>  selectDetailList(Map<String, Object> map);

    /**
     * 根据产品主代码查询非标资产信息
     * @param ProdId
     * @return
     */
    List<UnClaimAssetDetailDto> selectNostdByProdId(String ProdId);

    List<UnClaimAssetDetailDto> checkNostdExistByProdId(Map<String, Object> map);
    /**
     *
     * 导入（新建）
     * <功能详细描述>
     * @param listN 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int insertBtachEquityInfo(@Param("listN") List<UnClaimAssetDetailDto> listN);

    /**
     *
     * 导入（删除）
     * <功能详细描述>
     * @param listA 导入数据
     * @return 导入结果
     * @see
     * @since 1.0
     */
    int deleteBtachEquityInfo(@Param("listA") List<UnClaimAssetDetailDto> listA);

    int deleteByKey(String nostdAssetId);
    int insert(UnClaimAssetDetailDto unClaimAssetDetailDto);

    int updateByKey(UnClaimAssetDetailDto dto);
}
