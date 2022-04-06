package com.joyintech.tams.svreport.chb.mstbondcreditrating.dao;
import com.joyintech.tams.svreport.chb.mstbondcreditrating.dto.MstBondCreditRatingDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author 76587
* @description 针对表【MST_BOND_CREDIT_RATING(债券信用评级)】的数据库操作Mapper
* @createDate 2022-04-01 14:47:17
* @Entity generator.domain.MstBondCreditRating
*/
@Repository
public interface MstBondCreditRatingDao {


    void update(MstBondCreditRatingDto entity);

    void insert(MstBondCreditRatingDto entity);

    MstBondCreditRatingDto selcetByKey(@Param("oCode") String oCode,@Param("oObject") String oObject);

    List<MstBondCreditRatingDto> selectAll(Map<String, Object> pageParam);

    void batchInsert(List<MstBondCreditRatingDto> list);
}
