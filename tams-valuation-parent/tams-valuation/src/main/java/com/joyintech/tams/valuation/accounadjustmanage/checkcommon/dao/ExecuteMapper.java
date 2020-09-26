package com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.FileInfoDto;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.NatureSubjectInfo;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ProductValuationReconciliationShowDto;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ReconcileProductColResult;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ReconcileProductRowResult;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ReconcileSchemeInfo;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ReconcileSchemeStructConfigDto;
import com.joyintech.tams.valuation.accounadjustmanage.checkcommon.dto.ReconcileSchemeSubjectConfigDto;
import com.joyintech.tams.common.base.entity.BokValbalTableStatus;

/**
 * 对账数据获取
 * 
 * @author yangjian
 * @version 1.0 2018年11月15日
 * @see ExecuteMapper
 * @since 1.0
 */
public interface ExecuteMapper {

    /**
     * 获取对应的文件路径信息
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<FileInfoDto> getFileInfoList(Map<String, Object> params);

    /**
     * 根据产品获取对应账套
     * 
     * @param prodId
     * @return
     * @see
     * @since 1.0
     */
    String getBooksetIdByPrd(@Param("prodId") String prodId);

    /**
     * 获取该产品对应的方案信息
     * 
     * @param prodId 产品代码
     * @param schemeType 方案类型
     * @return 方案信息
     * @since 1.0
     */
    List<ReconcileSchemeInfo> getSchemeInfo(@Param("prodId") String prodId, @Param("schemeType") String schemeType);

    /**
     * 获取该产品对应的默认方案
     * 
     * @param prodId 产品代码
     * @param schemeType 方案类型
     * @return 方案信息
     * @since 1.0
     */
    List<ReconcileSchemeInfo> getSchemeInfoForDefault(@Param("prodId") String prodId, @Param("schemeType") String schemeType);

    /**
     * 获取结构映射信息
     * 
     * @param schemeId 方案代码
     * @return 结构映射信息
     * @since 1.0
     */
    List<ReconcileSchemeStructConfigDto> getStructList(@Param("schemeId") String schemeId);

    /**
     * 获取科目映射信息
     * 
     * @param schemeId 方案代码
     * @return 科目映射信息
     * @since 1.0
     */
    List<ReconcileSchemeSubjectConfigDto> getSubjectList(@Param("schemeId") String schemeId, @Param("booksetId") String booksetId,@Param("mySubjectIdList") List<String> mySubjectIdList,@Param("otherSubjectIdList") List<String> otherSubjectIdList);

    /**
     * 批量插入对账结果数据
     * 
     * @param rowResultList 对账结果数据
     * @since 1.0
     */
    int insertRowResultBatch(List<ReconcileProductRowResult> list);

    /**
     * 批量插入对账结果数据
     * 
     * @param colResultList 对账结果数据
     * @since 1.0
     */
    int insertColResultBatch(List<ReconcileProductColResult> list);

    /**
     * 删除原对账列结果
     * 
     * @param prodId 产品代码
     * @param schemeId 方案代码
     * @param compareDate 对账日期
     * @since 1.0
     */
    void deleteOldColResult(@Param("prodId") String prodId, @Param("schemeId") String schemeId, @Param("compareDate") Date compareDate);

    /**
     * 删除原对账行结果
     * 
     * @param prodId 产品代码
     * @param schemeId 方案代码
     * @param compareDate 对账日期
     * @since 1.0
     */
    void deleteOldRowResult(@Param("prodId") String prodId, @Param("schemeId") String schemeId, @Param("compareDate") Date compareDate);

    /**
     * 更新【估值表余额表状态表】对账结果
     * 
     * @param dto
     * @see
     * @since 1.0
     */
    void updateBokValbalTableStatusAndResult(BokValbalTableStatus dto);

    /**
     * 获取对应的对账结果数据
     * 
     * @param prodId 产品代码
     * @param valueDate 对账日期
     * @param schemeType 方案类型
     * @param schemeId 方案代码
     * @param compareType 对账类型
     * @param filter 过滤条件
     * @param subjectLevel 科目级别
     * @param colList 显示列
     * @return 对账明细
     * @see
     * @since 1.0
     */
    List<ProductValuationReconciliationShowDto> getResultDetailList(@Param("prodId") String prodId, @Param("valueDate") String valueDate, @Param("schemeType") String schemeType, @Param("schemeId") String schemeId, @Param("compareType") String compareType, @Param("filter") String filter, @Param("subjectLevel") Integer subjectLevel, @Param("colList") List<String> colList);

    /**
     * 通过估值表余额表上传文件信息，删除对比结果文件信息
     * 
     * @param params
     * @see
     * @since 1.0
     */
    void deleteBokFileInfoByBokValbalTableFileForCompare(Map<String, Object> params);

    /**
     * 删除【估值表余额表上传文件信息】中的对比结果信息
     * 
     * @param params
     * @see
     * @since 1.0
     */
    void deleteBokValbalTableFileForCompare(Map<String, Object> params);

    /**
     * 获取科目性质表对应的我方所有科目组合信息
     * 
     * @param schemeId 方案代码
     * @return 科目映射信息
     * @since 1.0
     */
    List<NatureSubjectInfo> getMySubjectInfoInSchemeNature(@Param("schemeId") String schemeId, @Param("booksetId") String booksetId, @Param("subjectIdList") List<String> subjectIdList);

    /**
     * 获取科目性质表对应的他方所有科目组合信息
     * 
     * @param schemeId 方案代码
     * @return 科目映射信息
     * @since 1.0
     */
    List<NatureSubjectInfo> getOtherSubjectInfoInSchemeNature(@Param("schemeId") String schemeId, @Param("booksetId") String booksetId, @Param("subjectIdList") List<String> subjectIdList);

    /**
     * 获取对应级别的科目id
     * 
     * @param booksetId
     * @param subjectLeve
     * @param subjectIdList
     * @return
     * @see
     * @since 1.0
     */
    List<String> getSubjectIdWithFillter(@Param("booksetId") String booksetId, @Param("subjectLeve") String subjectLeve, @Param("subjectIdList") List<String> subjectIdList);

    /**
     * 根据父级id获取下级id集合
     * 
     * @param booksetId
     * @param fsubjectId
     * @return
     * @see
     * @since 1.0
     */
    List<String> getSubjectIdByFsubject(@Param("booksetId") String booksetId, @Param("fsubjectId") String fsubjectId);
}
