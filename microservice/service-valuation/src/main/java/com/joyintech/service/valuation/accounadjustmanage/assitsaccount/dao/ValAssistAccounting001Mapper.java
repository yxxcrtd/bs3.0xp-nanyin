package com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting001Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting002Dto;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistHandDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @program: tams-base
 * @description: 辅助核算配置微服务dao
 * @author: 殷亚
 * @version: 1.0
 * @create: 2022-01-12 17:23
 **/
@Repository(value="serviceValAssistAccounting001Mapper")
public interface ValAssistAccounting001Mapper {

    /**
     * 查询所有账套
     * <br/> 用于所属账套控件初期化
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> findBooksets();

    /**
     * @author  殷亚
     * @description 查询辅助核算配置
     * @date 2022-1-12 17:36
     * @param param:
     * @return java.util.List<com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting001Dto>
     */
    List<ValAssistAccounting001Dto> findPage(Map<String, Object> param);

    /**
     * @author  殷亚
     * @description 根据账套代码、内部科目编码获取辅助核算项
     * @date 2022-1-13 11:42
     * @return java.util.List<com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistHandDto>
     */
    List<ValAssistHandDto> findHands(@Param(value = "booksetId") String booksetId,
                                     @Param(value = "subjectInternalCode") String subjectInternalCode);

    /**
     * @author  殷亚
     * @description 根据条件获取辅助核算项
     * @date 2022-1-19 16:53
     * @param param:
     * @return java.util.List<com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistHandDto>
     */
    List<ValAssistHandDto> findHandsByCondition(Map<String, Object> param);
    /**
     * @author  殷亚
     * @description 根据条件修改辅助核算配置
     * @date 2022-1-17 15:14
     * @param list:
     * @return int
     */
    int mergeSubjectHands (List<ValAssistHandDto> list);

    /**
     * @author  殷亚
     * @description 合并修改内部科目底层科目规则
     * @date 2022-1-17 16:34
     * @param entity:
     * @return int
     */
    int mergeSubjectRule (ValAssistAccounting002Dto entity);
}
