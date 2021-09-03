
package com.joyintech.service.valuation.basicdata.mstcurvedefmanage.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * 收益率曲线管理mapper
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveDefManage001Mapper
 * @since 1.0
 */
@Repository
public interface MstCurveDefManage001Mapper {

    /**
     *
     * @param param
     * @return
     */
    List<MstCurveDefManageDto> select(Map<String, Object> param);

    List<SelectOptionDto> getCurveCode(Map<String,Object> param);

    void updateEffStatus(MstCurveDefManageDto dto);

    /**
     * 查询收益率曲线定义是否存在
     * @param dto
     * @return
     */
    List<MstCurveDefManageDto> getCurveDefCount(MstCurveDefManageDto dto);

    MstCurveDefManageDto selectByKey(Map<String,Object> param);
}
