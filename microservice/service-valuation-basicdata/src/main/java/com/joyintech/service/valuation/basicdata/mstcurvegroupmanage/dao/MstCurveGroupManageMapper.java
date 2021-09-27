
package com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.dao;

import com.joyintech.service.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * @Description:收益率曲线族管理mapper
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@Repository
public interface MstCurveGroupManageMapper {

    List<MstCurveGroupManageDto> select(Map<String, Object> param);

    MstCurveGroupManageDto selectByKey(Map<String, Object> param);

    int insertSelective(MstCurveGroupManageDto dto);

    int updateByPrimaryKey(MstCurveGroupManageDto dto);

    int deleteByPrimaryKey(MstCurveGroupManageDto dto);

    int getCountCurveCode(@Param("curveGroupCode") String curveGroupCode);


}
