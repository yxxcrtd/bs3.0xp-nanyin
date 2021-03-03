package com.joyintech.tams.svreport.chbhz.inBottomAssetPosition.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbBotAstPositionSubTmp;

@Repository
public interface InBottomAstPositionSvrMapper {
	/**
	 * 查询底层持仓接口临时表list
	 * @param batchdate
	 * @param prodIdList
	 * @return
	 */
	List<ChbBotAstPositionSubTmp>  genChbInBottomAstPosTmpList(@Param("batchdate")String  batchdate);
    /**
     * 根据接口信息查询底层持仓信息表
     * @return
     */
    List<ChbBotAstPositionSubTmp>   genChbInBottomAstPosSubList(@Param("list")List<ChbBotAstPositionSubTmp> list);
    
    /**
     * 批量删除数据
     * @param dtoList
     * @param flag 报送状态
     * @return
     */
    int deleteBatch(@Param("deleteList") List<ChbBotAstPositionSubTmp> dtoList,@Param("flag")String flag);
    
    int insertBatch(@Param("insertList")List<ChbBotAstPositionSubTmp> dtoList);
    
}
