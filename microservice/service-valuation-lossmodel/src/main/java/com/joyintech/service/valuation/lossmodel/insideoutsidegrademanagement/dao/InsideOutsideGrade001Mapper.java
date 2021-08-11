
package com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dao;
import com.joyintech.service.valuation.lossmodel.insideoutsidegrademanagement.dto.InsideOutsideGrade001Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author 刘华明
 * @version 1.0 2021年7月6日
 * @see InsideOutsideGrade001Mapper
 * @since 1.0
 */
@Repository(value="serviceInsideOutsideGrade001Mapper")
public interface InsideOutsideGrade001Mapper {
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(InsideOutsideGrade001Dto record);

    int deleteByPrimaryKey(@Param("gradeInst") String gradeInst, @Param("oType") String oType);

    int checkData(InsideOutsideGrade001Dto dto);

    /**
     * 查询数据 <一句话功能简述> <功能详细描述>
     *
     * @param map
     * @return List<InsideOutsideGrade001Dto>
     * @see
     * @since 1.0
     */
    List<InsideOutsideGrade001Dto> getQueryData(Map<String, Object> map);

    /**
     * 根据流水号查询数据
     * @param map
     * @return
     */
    List<InsideOutsideGrade001Dto> getBySeqNo(Map<String, Object> map);
}
