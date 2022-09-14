package com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dao;

import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.select.SelectOptionDto;

import com.joyintech.tams.disclosure.information.config.unclaimassetdetail.dto.UnClaimAssetDetailDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UnClaimAssetDetailMapper  {
    /**
     * 页面查询方法
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
     * 查询当前系统维护的非标资产的数量
     * @return
     */
    int selectNostdCount();

    /**
     *
     * 双击查看，编辑
     * @param map
     * @return
     */
    UnClaimAssetDetailDto selectDetail(Map<String, Object> map);

    /**
     *
     * 编辑查看非标资产明细列表查询方法
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

    /**
     * 逻辑删除
     * @param record
     * @return
     */
    int deleteOnStatus(Map map);

    /**
     * 物理删除（以产品维度，删除多个非标资产）
     * @param record
     * @return
     */
    int deleteOnReal(UnClaimAssetDetailDto record);


    int insert(UnClaimAssetDetailDto record);

    int updateByKey(UnClaimAssetDetailDto record);


    int selectCount(Map<String, Object> map);

    int updateImport(UnClaimAssetDetailDto existDataDto);

    List<SelectOptionDto> getProdId(Map<String, Object> map);

    List<UnClaimAssetDetailDto> selectUnClaimAssetList(@Param("list") List<UnClaimAssetDetailDto> list);

    List<ProcessMark> getWorklowData(UnClaimAssetDetailDto detailDto);

    int deleteByNostdAssetId(@Param("list") List<UnClaimAssetDetailDto> list);
}
