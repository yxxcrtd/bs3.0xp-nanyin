package com.joyintech.service.entrust.etrinstruction.dao;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrinstruction.dto.EtrInstructionDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EtrInstructionMapper {
    /**
     *
     * 页面查询
     * <功能详细描述>
     * @param map 查询参数
     * @return List<EtrBrowDepoInfoDto>
     * @see
     * @since 1.0
     */
    List<EtrInstructionDto> select(Map<String, Object> map);


    /**
     *
     * 资产代码联想文本框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodMarketId(Map<String, Object> map);


    /**
     *
     * 融资人/管理人 联想文本框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getRepFinancierList(Map<String, Object> map);

    /**
     * 根据主键获取指令信息
     * @param entrustId
     * @return
     */
    EtrInstructionDto selectByKey(@Param("entrustId") String entrustId);

    /**
     * 合同签署
     */
     void qshtbyId(EtrInstructionDto dto);

    /**
     * 签署合同修改资产代码
     */
    void qshtfinprodMarketIdbyId(EtrInstructionDto dto);
}
